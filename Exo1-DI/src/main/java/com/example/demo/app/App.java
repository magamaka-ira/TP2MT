package com.example.demo.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication(scanBasePackages = "com.example.demo")
public class App {
    
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(App.class, args);
        
        NotificationService notificationService = context.getBean(NotificationService.class);
        
        notificationService.sendNotification();
        
        System.out.println("\n✅ Service injecté: " + notificationService.getMessageService().getClass().getSimpleName());
    }
}
