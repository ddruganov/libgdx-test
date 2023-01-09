package org.ddruganov.entity.component;

import org.ddruganov.Game;
import org.ddruganov.entity.Entity;

public abstract class EntityComponent {

    protected final Entity entity;

    public EntityComponent(Entity entity) {
        this.entity = entity;
    }

    public void update(Game game) {
    }

    public Entity getEntity() {
        return entity;
    }

    public void destroy() {
    }
}
