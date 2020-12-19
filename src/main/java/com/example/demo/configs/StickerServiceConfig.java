package com.example.demo.configs;

import com.example.demo.services.StickerService;
import com.example.demo.services.StickerServiceImp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StickerServiceConfig {

    @Bean
    public StickerService stickerService() {
        return new StickerServiceImp();
    }
}
