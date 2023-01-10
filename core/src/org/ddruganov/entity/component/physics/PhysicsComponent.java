package org.ddruganov.entity.component.physics;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import org.ddruganov.entity.Entity;
import org.ddruganov.entity.component.EntityComponent;
import org.ddruganov.layer.GameplayLayer;
import org.ddruganov.physics.Transform;

public class PhysicsComponent extends EntityComponent implements TransformProvider {

    private final Body body;
    private final VelocityProvider velocityProvider;
    private final OnCollisionCallback onCollision;

    public PhysicsComponent(Entity entity,
                            Body body,
                            VelocityProvider velocityProvider,
                            OnCollisionCallback onCollision) {
        super(entity);
        this.body = body;
        this.body.setUserData(this);
        this.velocityProvider = velocityProvider;
        this.onCollision = onCollision;
    }

    @Override
    public void update(GameplayLayer layer) {
        handleVelocity();
    }

    private void handleVelocity() {
        if (this.velocityProvider == null) {
            return;
        }

        Vector2 velocity = this.velocityProvider.getVelocity();
        this.body.applyLinearImpulse(velocity, this.body.getPosition(), true);
    }

    public Vector2 getPosition() {
        return this.body.getPosition().cpy();
    }

    public void onCollision(PhysicsComponent with) {

        if (this.onCollision == null) {
            return;
        }

        this.onCollision.invoke(with.getEntity());
    }

    @Override
    public Transform getTransform() {
        return Transform.fromBox2D(this.body);
    }

    @Override
    public void destroy() {
        super.destroy();

        this.body.getWorld().destroyBody(this.body);
    }
}
