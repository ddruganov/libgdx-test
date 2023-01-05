package org.ddruganov.entity;

import org.ddruganov.Game;
import org.ddruganov.entity.component.EntityComponent;

import java.util.HashMap;

public class Entity {
    private final HashMap<String, EntityComponent> components = new HashMap<>();

    public void update(Game game) {
        this.components.forEach((key, value) -> value.update(game));
    }

    public Entity addComponent(EntityComponent component) {
        this.components.put(component.getClass().toString(), component);
        return this;
    }

    public <T extends EntityComponent> T getComponent(Class<T> className) {
        return (T) this.components.get(className.toString());
    }
}
