package com.ekuy.webtools.utils;

/**
 * Discuz authcode java 实现
 */

import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;

import java.util.ArrayList;
import java.util.List;

public class AuthCodeUtil {
    /**
     * 加解密
     *
     * @param text
     * @param decrypt    是否解密 默认 decrypt = True 默认解密
     * @param key
     * @param expiry
     * @param ckeyLength
     * @return
     */
    public static String code(String text, boolean decrypt, String key, int expiry, int ckeyLength) {
        if (StrUtil.isBlank(text)) {
            return "";
        }
        // 动态密钥长度
        ckeyLength = ckeyLength == 0 ? 8 : ckeyLength;
        // 生成密钥
        key = SecureUtil.md5(key == null ? "abcdefghijklmnopqrstuvwxyz0123456789" : key);
        // 密钥A用于加密
        String keyA = SecureUtil.md5(key.substring(0, 16));
        // 密钥B用于验证
        String keyB = SecureUtil.md5(key.substring(16));
        // 密钥C，生成动态密码部分
        // 解密的时候获取需要解密的字符串前面的ckey_length长度字符串
        // 加密的时候，用当前时间戳的微妙数md5加密的最后ckey_length长度字符串
        String keyC = decrypt ? text.substring(0, ckeyLength) : SecureUtil.md5("" + System.currentTimeMillis()).substring(32 - ckeyLength);
        // 用于计算的密钥
        String cryptKey = keyA + SecureUtil.md5(keyA + keyC);
        int keyLength = cryptKey.length();

        if (decrypt) {
            text = AuthCodeUtil.base64Decode(text.substring(ckeyLength));
        } else {
            long timestamp = System.currentTimeMillis() / 1000;
            text = String.format("%010d", expiry > 0 ? timestamp + expiry : 0L)
                    + SecureUtil.md5(text + keyB).substring(0, 16) + text;
        }

        int textLength = text.length();
        int[] box = ArrayUtil.range(256);

        String result = "";
        List<Integer> rndKey = new ArrayList<Integer>();
        for (int i: ArrayUtil.range(256)) {
            rndKey.add(Integer.valueOf(cryptKey.charAt(i%keyLength)));
        }

        int j = 0;
        for (int i : ArrayUtil.range(256)) {
            j = (j + box[i] + rndKey.get(i)) % 256;
            int temp = box[i];
            box[i] = box[j];
            box[j] = temp;
        }

        int a = 0;
        j = 0;

        for (int i : ArrayUtil.range(textLength)) {
            a = (a + 1) % 256;
            j = (j + box[a]) % 256;
            int temp = box[a];
            box[a] = box[j];
            box[j] = temp;
            result += (char)(Integer.valueOf(text.charAt(i)) ^ (box[(box[a] + box[j])%256]));
        }

        if (decrypt) {
            int t = Integer.parseInt(result.substring(0, 10));
            long timestamp = System.currentTimeMillis() / 1000;
            if ((t == 0 || (t - timestamp) > 0)
                && (SecureUtil.md5(result.substring(26) + keyB).substring(0, 16)
                    .equals(result.substring(10, 26)))) {
                return result.substring(26);
            }

            return "";
        }

        return keyC + AuthCodeUtil.base64Encode(result);
    }

    public static String encode(String text, String key, int expiry, int cKeyLength) {
        return AuthCodeUtil.code(text, false, key, expiry, cKeyLength);
    }

    public static String encode(String text, String key, int expiry) {
        return AuthCodeUtil.code(text, false, key, expiry, 0);
    }

    public static String encode(String text, String key) {
        return AuthCodeUtil.code(text, false, key, 0, 0);
    }

    public static String encode(String text, int expiry) {
        return AuthCodeUtil.code(text, false, null, expiry, 0);
    }

    public static String encode(String text) {
        return AuthCodeUtil.code(text, false, null, 0, 0);
    }

    public static String decode(String text, String key, int cKeyLength) {
        return AuthCodeUtil.code(text, true, key, 0, cKeyLength);
    }

    public static String decode(String text, String key) {
        return AuthCodeUtil.code(text, true, key, 0, 0);
    }

    public static String decode(String text) {
        return AuthCodeUtil.code(text, true, null, 0, 0);
    }

    public static String base64Encode(String text) {
        return Base64.encode(text, "ISO-8859-1");
    }

    public static String base64Decode(String text) {
        return Base64.decodeStr(text, "ISO-8859-1");
    }
}
