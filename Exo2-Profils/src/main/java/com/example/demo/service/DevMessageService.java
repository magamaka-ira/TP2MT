package com.example.demo.service;

import org.springframework.stereotype.Component;
import org.springframework.context.annotation.Profile;

@Component
@Profile("dev")
public class DevMessageService implements MessageService {
    
    @Override
    public String getMessage() {
        return "🔧 [DEV] Mode développement - Messages de débogage activés";
    }
}
