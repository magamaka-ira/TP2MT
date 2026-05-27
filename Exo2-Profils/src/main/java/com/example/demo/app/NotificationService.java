package com.example.demo.app;

import com.example.demo.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NotificationService {
    
    private MessageService messageService;
    
    @Autowired
    public void setMessageService(MessageService messageService) {
        this.messageService = messageService;
    }
    
    public void send() {
        System.out.println("📢 " + messageService.getMessage());
    }
    
    public MessageService getMessageService() {
        return messageService;
    }
}
