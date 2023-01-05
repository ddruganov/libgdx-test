package org.ddruganov.entity.component.physics;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import org.ddruganov.Game;
import org.ddruganov.entity.component.EntityComponent;

public class PhysicsComponent implements EntityComponent {

    private final Body body;
    private final PositionTracker[] positionTrackers;
    private final VelocityProvider velocityProvider;

    public PhysicsComponent(Body body, PositionTracker[] positionTrackers, VelocityProvider velocityProvider) {
        this.body = body;
        this.positionTrackers = positionTrackers;
        this.velocityProvider = velocityProvider;
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
}
