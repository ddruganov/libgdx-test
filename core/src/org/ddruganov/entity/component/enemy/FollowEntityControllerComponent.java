package org.ddruganov.entity.component.enemy;

import com.badlogic.gdx.math.Vector2;
import org.ddruganov.Game;
import org.ddruganov.entity.Entity;
import org.ddruganov.entity.component.EntityComponent;
import org.ddruganov.entity.component.physics.PhysicsComponent;
import org.ddruganov.entity.component.physics.PositionTracker;
import org.ddruganov.entity.component.physics.VelocityProvider;

public class FollowEntityControllerComponent implements EntityComponent, VelocityProvider, PositionTracker {

    private final float speed;
    private Vector2 velocity = Vector2.Zero;
    private Vector2 position = Vector2.Zero;
    private Entity entity;

    public FollowEntityControllerComponent(Entity entity, float speed) {
        this.setEntity(entity);
        this.speed = speed;
    }

    @Override
    public void update(Game game) {
        this.velocity = this.entity.getComponent(PhysicsComponent.class).getPosition()
                .sub(this.position)
                .nor()
                .scl(this.speed);
    }

    @Override
    public Vector2 getVelocity() {
        return this.velocity;
    }

    public void setEntity(Entity value) {
        if (value.getComponent(PhysicsComponent.class) == null) {
            throw new IllegalArgumentException(value.getClass().toString() + " does not have a physics component attached");
        }
        this.entity = value;
    }

    @Override
    public void setPosition(Vector2 value) {
        this.position = value;
    }
}
