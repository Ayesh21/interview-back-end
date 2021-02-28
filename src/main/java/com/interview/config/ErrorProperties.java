package com.interview.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:error.properties")
@ConfigurationProperties("error.type")
@Data
public class ErrorProperties {
    private String all;
}

