package org.ddruganov.entity;

import org.ddruganov.Game;
import org.ddruganov.entity.component.EntityComponent;

import java.util.HashMap;
import java.util.UUID;

public class Entity {
    private final HashMap<String, EntityComponent> components = new HashMap<>();

    private final UUID uuid;

    public Entity() {
        this.uuid = UUID.randomUUID();
    }

    public void update(Game game) {
        this.components.forEach((key, value) -> value.update(game));
    }

    public void addComponent(EntityComponent component) {
        this.components.put(component.getClass().toString(), component);
    }

    public <T extends EntityComponent> T getComponent(Class<T> className) {
        return (T) this.components.get(className.toString());
    }

    public UUID getUuid() {
        return this.uuid;
    }
}
