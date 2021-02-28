package com.interview.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:pricecalculate.properties")
@ConfigurationProperties("item.type")
@Data
public class PriceCalculateProperties {
    private String penguinEars;
    private String horseShoes;
}
