package com.example.demo.service;

import org.springframework.stereotype.Component;
import org.springframework.context.annotation.Primary;

@Component
@Primary
public class EmailService implements MessageService {
    
    @Override
    public String getMessage() {
        return "Message envoyé par email.";
    }
}
