package com.souza.kronos.config;

import java.math.BigDecimal;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.Formatter;
import com.souza.kronos.formatters.BigDecimalFormatter;

@Configuration
public class WebConfig {

    @Bean
    public Formatter<BigDecimal> bitDecimalFormatter() {
        return new BigDecimalFormatter();
    }

}
