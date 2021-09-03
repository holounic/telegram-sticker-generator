package com.example.demo.config;

import com.example.demo.service.ServiceInterface;
import com.example.demo.service.StickerService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StickerServiceConfig {

    @Bean
    public ServiceInterface stickerService() {
        return new StickerService();
    }
}
