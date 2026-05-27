package com.example.demo.service;

import org.springframework.stereotype.Component;
import org.springframework.context.annotation.Profile;

@Component
@Profile("prod")
public class ProdMessageService implements MessageService {
    
    @Override
    public String getMessage() {
        return "✅ [PROD] Mode production - Sécurisé et optimisé";
    }
}
