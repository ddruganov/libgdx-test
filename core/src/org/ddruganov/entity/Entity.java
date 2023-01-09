package org.ddruganov.entity;

import org.ddruganov.Game;
import org.ddruganov.entity.component.EntityComponent;

import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;

public class Entity {

    private final HashMap<String, EntityComponent> components = new HashMap<>();
    private final UUID uuid;
    private boolean isDeleted = false;

    public Entity() {
        this.uuid = UUID.randomUUID();
    }

    public void update(Game game) {

        if (this.isDeleted) {
            this.components.values().forEach(EntityComponent::destroy);
            this.components.clear();

            game.removeEntity(this);
            return;
        }

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

    public void destroy() {
        this.isDeleted = true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entity entity = (Entity) o;
        return uuid.equals(entity.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid);
    }
}
