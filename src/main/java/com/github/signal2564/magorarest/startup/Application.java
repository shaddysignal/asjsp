package com.github.signal2564.magorarest.startup;

import com.github.signal2564.magorarest.config.Config;
import com.github.signal2564.magorarest.config.SecurityConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(new Class[]{ Config.class, SecurityConfig.class }, args);
    }
}
