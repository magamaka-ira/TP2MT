package org.sebsy.demo.escaperooms.bll;

import org.springframework.stereotype.Component;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Primary;

@Component
@Profile("passage")
@Primary
public class Room1Service implements RoomService {
    
    @Override
    public String play() {
        return "🚪 Room 1 - Passage secret découvert!";
    }
}
