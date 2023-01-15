package org.ddruganov.entity.component.projectile;

import com.badlogic.gdx.math.Vector2;
import org.ddruganov.entity.Entity;
import org.ddruganov.entity.component.EntityComponent;
import org.ddruganov.entity.component.physics.VelocityProvider;

public class DirectionBasedControllerComponent extends EntityComponent implements VelocityProvider {

    private final Vector2 velocity;

    public DirectionBasedControllerComponent(Entity entity, Vector2 direction, int speed) {
        super(entity);
        this.velocity = direction.cpy().nor().scl(speed);
    }

    @Override
    public Vector2 getVelocity() {
        return this.velocity;
    }
}
