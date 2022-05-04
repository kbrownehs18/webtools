package com.ekuy.webtools.cors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;


@Configuration
public class CorsConfig {
    @Value("${cors.origin:*}")
    private String origin;
    @Value("${cors.header:*}")
    private String header;
    @Value("${cors.method:*}")
    private String method;
    @Value("${cors.path:/**}")
    private String path;
    @Value("${cors.maxAge:3600}")
    private long maxAge;

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOriginPattern(origin);
        config.addAllowedMethod(method);
        config.addAllowedHeader(header);
        config.setAllowCredentials(true);
        config.setMaxAge(maxAge);

        UrlBasedCorsConfigurationSource configSource = new UrlBasedCorsConfigurationSource();
        configSource.registerCorsConfiguration(path, config);

        return new CorsFilter(configSource);
    }
}
