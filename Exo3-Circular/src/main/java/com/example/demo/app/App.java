package com.example.demo.app;

import com.example.demo.circular.ClassA;
import com.example.demo.circular.ClassB;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication(scanBasePackages = "com.example.demo")
public class App {
    
    public static void main(String[] args) {
        System.out.println("\n🔄 ========== EXO3 - INJECTION CIRCULAIRE ==========\n");
        
        ApplicationContext context = SpringApplication.run(App.class, args);
        
        System.out.println("\n📌 TEST: Afficher les infos des classes");
        
        ClassA classA = context.getBean(ClassA.class);
        ClassB classB = context.getBean(ClassB.class);
        
        classA.displayInfo();
        classB.displayInfo();
        
        System.out.println("\n✅ ========== INJECTION CIRCULAIRE RÉSOLUE AVEC @Lazy ==========\n");
    }
}
