package com.example.demo.circular;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClassB {
    
    private ClassA classA;
    
    @Autowired
    public ClassB(ClassA classA) {
        this.classA = classA;
        System.out.println("✓ ClassB créée avec injection de ClassA");
    }
    
    public void displayInfo() {
        System.out.println("🟠 ClassB - Je dépends de ClassA: " + classA.getClass().getSimpleName());
    }
    
    public ClassA getClassA() {
        return classA;
    }
}
