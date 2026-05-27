package org.sebsy.demo.escaperooms.controller;

import org.sebsy.demo.escaperooms.bll.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GameController {
    
    private RoomService roomService;
    
    @Autowired(required = false)
    public void setRoomService(RoomService roomService) {
        this.roomService = roomService;
    }
    
    public void startGame() {
        if (roomService != null) {
            System.out.println("🎮 " + roomService.play());
        } else {
            System.out.println("❌ Aucune salle disponible!");
        }
    }
}
