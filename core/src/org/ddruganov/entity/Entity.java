package org.ddruganov.entity;

import org.ddruganov.entity.component.EntityComponent;
import org.ddruganov.layer.GameplayLayer;

import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;

public class Entity {

    private final HashMap<String, EntityComponent> components = new HashMap<>();
    private final UUID uuid;
    private final OnDestructionCallback onDestruction;
    private boolean isDestroyed = false;

    public Entity(GameplayLayer ignoredLayer) {
        this(ignoredLayer, null);
    }

    public Entity(GameplayLayer ignoredLayer, OnDestructionCallback onDestruction) {
        this.uuid = UUID.randomUUID();
        this.onDestruction = onDestruction;
    }

    public boolean isDestroyed() {
        return isDestroyed;
    }

    public void update(GameplayLayer layer) {

        if (this.isDestroyed) {
            if (this.onDestruction != null) {
                this.onDestruction.invoke(this);
            }

            this.components.values().forEach(c -> c.destroy(layer));
            this.components.clear();

            layer.removeEntity(this);
            return;
        }

        this.components.forEach((key, value) -> value.update(layer));
    }

    public void addComponent(EntityComponent component) {
        this.components.put(component.getClass().getSimpleName(), component);
    }

    public <T extends EntityComponent> T getComponent(Class<T> className) {
        return (T) this.components.get(className.getSimpleName());
    }

    public UUID getUuid() {
        return this.uuid;
    }

    public void destroy() {
        this.isDestroyed = true;
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
