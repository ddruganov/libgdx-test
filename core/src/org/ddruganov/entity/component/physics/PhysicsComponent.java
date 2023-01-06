package org.ddruganov.entity.component.physics;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import org.ddruganov.Game;
import org.ddruganov.entity.Entity;
import org.ddruganov.entity.component.EntityComponent;

public class PhysicsComponent extends EntityComponent {

    private final Body body;
    private final PositionTracker[] positionTrackers;
    private final VelocityProvider velocityProvider;
    private final OnCollisionCallback onCollision;

    public PhysicsComponent(Entity entity,
                            Body body,
                            PositionTracker[] positionTrackers,
                            VelocityProvider velocityProvider,
                            OnCollisionCallback onCollision) {
        super(entity);
        this.body = body;
        this.body.setUserData(this);
        this.positionTrackers = positionTrackers;
        this.velocityProvider = velocityProvider;
        this.onCollision = onCollision;
    }

    @Override
    public void update(Game game) {
        if (this.velocityProvider != null) {
            Vector2 velocity = this.velocityProvider.getVelocity();
            this.body.applyLinearImpulse(velocity, this.body.getPosition(), true);
        }

        if (this.positionTrackers != null) {
            for (PositionTracker positionTracker : this.positionTrackers) {
                positionTracker.setPosition(getPosition());
            }
        }
    }

    public Vector2 getPosition() {
        return this.body.getPosition().cpy();
    }

    public void onCollision(PhysicsComponent with) {
    }
}
