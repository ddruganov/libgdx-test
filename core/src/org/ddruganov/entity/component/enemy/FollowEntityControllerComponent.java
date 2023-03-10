package org.ddruganov.entity.component.enemy;

import com.badlogic.gdx.math.Vector2;
import org.ddruganov.entity.Entity;
import org.ddruganov.entity.component.EntityComponent;
import org.ddruganov.entity.component.physics.PhysicsComponent;
import org.ddruganov.entity.component.physics.VelocityProvider;
import org.ddruganov.layer.GameplayLayer;

public class FollowEntityControllerComponent extends EntityComponent implements VelocityProvider {

    private final float speed;
    private Vector2 velocity = Vector2.Zero;
    private Entity entityToFollow;

    public FollowEntityControllerComponent(Entity entity, Entity entityToFollow, float speed) {
        super(entity);
        this.setEntityToFollow(entityToFollow);
        this.speed = speed;
    }

    @Override
    public void update(GameplayLayer layer) {

        if (this.entityToFollow == null || this.entityToFollow.isDestroyed()) {
            this.velocity = Vector2.Zero;
            return;
        }

        this.velocity = this.entityToFollow.getComponent(PhysicsComponent.class).getPosition()
                .sub(this.entity.getComponent(PhysicsComponent.class).getPosition())
                .nor()
                .scl(this.speed);
    }

    @Override
    public Vector2 getVelocity() {
        return this.velocity;
    }

    public void setEntityToFollow(Entity value) {

        if (value.isDestroyed()) {
            return;
        }

        if (value.getComponent(PhysicsComponent.class) == null) {
            throw new IllegalArgumentException(value.getClass().toString() + " does not have a physics component attached");
        }
        this.entityToFollow = value;
    }
}
