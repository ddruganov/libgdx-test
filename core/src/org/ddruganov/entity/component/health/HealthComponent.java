package org.ddruganov.entity.component.health;

import org.ddruganov.Game;
import org.ddruganov.entity.Entity;
import org.ddruganov.entity.component.EntityComponent;

public class HealthComponent extends EntityComponent {

    private final OnDeathCallback onDeath;
    private float health;

    public HealthComponent(Entity entity, float health, OnDeathCallback onDeath) {
        super(entity);
        this.health = health;
        this.onDeath = onDeath;
    }

    @Override
    public void update(Game game) {

    }

    public void damage(float value) {
        this.health -= value;

        if (this.health > 0) {
            return;
        }

        if (this.onDeath == null) {
            return;
        }

        this.onDeath.invoke();
    }
}
