package com.github.signal2564.magorarest.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootConfiguration
@EnableAutoConfiguration
@EnableJpaRepositories(basePackages = "com.github.signal2564.magorarest.dao")
@ComponentScan(basePackages = {
        "com.github.signal2564.magorarest.controllers",
        "com.github.signal2564.magorarest.services",
        "com.github.signal2564.magorarest.config.auth"
})
@EntityScan(basePackages = "com.github.signal2564.magorarest.models")
public class Config {
}
