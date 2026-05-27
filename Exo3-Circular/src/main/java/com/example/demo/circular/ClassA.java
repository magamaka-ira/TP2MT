package com.example.demo.circular;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class ClassA {
    
    private ClassB classB;
    
    @Autowired
    public ClassA(@Lazy ClassB classB) {
        this.classB = classB;
        System.out.println("✓ ClassA créée avec injection de ClassB");
    }
    
    public void displayInfo() {
        System.out.println("🔵 ClassA - Je dépends de ClassB: " + classB.getClass().getSimpleName());
    }
    
    public ClassB getClassB() {
        return classB;
    }
}
