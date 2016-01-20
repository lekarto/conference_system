package com.epam.configs;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@Configuration
public class AppConfig {
    @Bean(name = "jsonMessageConverter")
    public MappingJackson2HttpMessageConverter getJSONMapper() {
        return new MappingJackson2HttpMessageConverter();
    }

    @Bean
    public Logger getLogger() {
        return LogManager.getLogger();
    }
}
