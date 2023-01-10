package org.ddruganov.entity.projectile;

import com.badlogic.gdx.math.Vector2;
import org.ddruganov.entity.Entity;
import org.ddruganov.entity.component.physics.PhysicsComponent;
import org.ddruganov.layer.GameplayLayer;
import org.ddruganov.render.Renderable;

public class ProjectileBuilder {

    private final GameplayLayer layer;
    private Entity entity;
    private Renderable renderable;
    private Vector2 direction;
    private float baseDamage;

    public ProjectileBuilder(GameplayLayer layer) {
        this.layer = layer;
    }

    public ProjectileBuilder setSender(Entity value) {
        this.entity = value;
        return this;
    }

    public ProjectileBuilder setRenderable(Renderable value) {
        this.renderable = value;
        return this;
    }

    public ProjectileBuilder setDirection(Vector2 value) {
        this.direction = value;
        return this;
    }

    public ProjectileBuilder setBaseDamage(float value) {
        this.baseDamage = value;
        return this;
    }

    public Projectile buildProjectile() {
        return new Projectile(
                this.layer,
                this.entity,
                this.renderable,
                this.entity.getComponent(PhysicsComponent.class).getPosition(),
                this.direction,
                this.baseDamage
        );
    }
}
