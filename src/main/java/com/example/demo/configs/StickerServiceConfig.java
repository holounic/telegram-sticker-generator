package com.example.demo.configs;

import com.example.demo.services.ServiceInterface;
import com.example.demo.services.StickerService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StickerServiceConfig {

    @Bean
    public ServiceInterface stickerService() {
        return new StickerService();
    }
}
