package com.bat.man.cbm.feign.hystrix;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SystemHystrixConfiguration {

    @Bean
    SystemHystrixConcurrencyStrategy hystrixConcurrencyStrategyCustom() {
        return new SystemHystrixConcurrencyStrategy();
    }
}
