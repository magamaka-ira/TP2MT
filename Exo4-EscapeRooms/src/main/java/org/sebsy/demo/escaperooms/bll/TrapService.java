package org.sebsy.demo.escaperooms.bll;

import org.springframework.stereotype.Component;
import org.springframework.context.annotation.Profile;

@Component
@Profile("trap")
public class TrapService implements RoomService {
    
    @Override
    public String play() {
        return "⚠️ Trap Room - Piège activé!";
    }
}
