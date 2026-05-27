package org.sebsy.demo.escaperooms.bll;

import org.springframework.stereotype.Component;
import org.springframework.context.annotation.Profile;

@Component
@Profile("passage")
public class Room2Service implements RoomService {
    
    @Override
    public String play() {
        return "🗝️ Room 2 - Clé trouvée dans le passage!";
    }
}
