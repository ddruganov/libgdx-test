package org.ddruganov.entity.component.physics;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import org.ddruganov.Game;
import org.ddruganov.entity.component.EntityComponent;

public class PhysicsComponent implements EntityComponent {

    private final Body body;
    private final PositionTracker positionTracker;
    private final VelocityProvider velocityProvider;

    public PhysicsComponent(Body body, PositionTracker positionTracker, VelocityProvider velocityProvider) {
        this.body = body;
        this.positionTracker = positionTracker;
        this.velocityProvider = velocityProvider;
    }

    @Override
    public void update(Game game) {
        Vector2 velocity = this.velocityProvider.getVelocity();
        System.out.println(velocity.toString());
        this.body.applyLinearImpulse(velocity, this.body.getPosition(), true);

        this.positionTracker.setPosition(getCenter());
    }

    private Vector2 getCenter() {
        Vector2 size = (Vector2) this.body.getUserData();
        return new Vector2(
                this.body.getPosition().x - (size.x / 2),
                this.body.getPosition().y - (size.y / 2)
        );
    }
}
