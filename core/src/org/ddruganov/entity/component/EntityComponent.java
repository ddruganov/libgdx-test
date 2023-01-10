package org.ddruganov.entity.component;

import org.ddruganov.entity.Entity;
import org.ddruganov.layer.GameplayLayer;

public abstract class EntityComponent {

    protected final Entity entity;

    public EntityComponent(Entity entity) {
        this.entity = entity;
    }

    public void update(GameplayLayer layer) {
    }

    public Entity getEntity() {
        return entity;
    }

    public void destroy() {
    }
}
