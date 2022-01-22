package com.haulmont.den26.loanoffer.config;

import nz.net.ultraq.thymeleaf.layoutdialect.LayoutDialect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    @Bean
    public LayoutDialect layoutDialect() {
        return new LayoutDialect();
    }
}
