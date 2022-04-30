package com.ekuy.webtools.utils;

/**
 * 国标SM4加密
 */

import cn.hutool.crypto.SmUtil;
import cn.hutool.crypto.symmetric.SymmetricCrypto;

public class Sm4Util {
    public static String encode(String content, String key) {
        SymmetricCrypto sm4 = SmUtil.sm4(key.getBytes());
        return sm4.encryptBase64(content);
    }

    public static String decode(String raw, String key) {
        SymmetricCrypto sm4 = SmUtil.sm4(key.getBytes());
        return sm4.decryptStr(raw);
    }
}
