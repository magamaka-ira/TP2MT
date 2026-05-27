package org.sebsy.demo.escaperooms.bll;

import org.springframework.stereotype.Component;
import org.springframework.context.annotation.Profile;

@Component
@Profile("treasure")
public class TreasureRoomService implements RoomService {
    
    @Override
    public String play() {
        return "💎 Treasure Room - Trésor découvert!";
    }
}
