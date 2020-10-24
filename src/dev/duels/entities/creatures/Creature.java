package dev.duels.entities.creatures;

import java.awt.*;
import java.io.Serializable;

public abstract class Creature implements Serializable {
    private String name;

    Creature(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public abstract void render(Graphics g);
    public abstract void tick();
}
