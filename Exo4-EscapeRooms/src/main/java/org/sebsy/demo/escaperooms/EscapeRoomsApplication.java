package org.sebsy.demo.escaperooms;

import org.sebsy.demo.escaperooms.controller.GameController;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "org.sebsy.demo.escaperooms")
public class EscapeRoomsApplication implements CommandLineRunner {
    
    private final GameController gameController;
    
    public EscapeRoomsApplication(GameController gameController) {
        this.gameController = gameController;
    }
    
    public static void main(String[] args) {
        SpringApplication.run(EscapeRoomsApplication.class, args);
    }
    
    @Override
    public void run(String... args) throws Exception {
        System.out.println("\n🎮 ========== EXO4 - ESCAPE ROOMS ==========\n");
        
        System.out.println("📌 TEST: Jeu avec profils 'passage' et 'treasure'");
        gameController.startGame();
        
        System.out.println("\n✅ ========== EXO4 TERMINÉ ==========\n");
    }
}
