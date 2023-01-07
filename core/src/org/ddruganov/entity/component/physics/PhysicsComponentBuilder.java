package org.ddruganov.entity.component.physics;

import com.badlogic.gdx.physics.box2d.Body;
import org.ddruganov.entity.Entity;

public class PhysicsComponentBuilder {
    private Entity entity;
    private Body body;
    private TransformTracker[] positionTrackers = new TransformTracker[]{};
    private VelocityProvider velocityProvider = null;
    private OnCollisionCallback onCollision = null;

    public PhysicsComponentBuilder setEntity(Entity entity) {
        this.entity = entity;
        return this;
    }

    public PhysicsComponentBuilder setBody(Body body) {
        this.body = body;
        return this;
    }

    public PhysicsComponentBuilder setTransformTrackers(TransformTracker[] positionTrackers) {
        this.positionTrackers = positionTrackers;
        return this;
    }

    public PhysicsComponentBuilder setVelocityProvider(VelocityProvider velocityProvider) {
        this.velocityProvider = velocityProvider;
        return this;
    }

    public PhysicsComponentBuilder setOnCollision(OnCollisionCallback onCollision) {
        this.onCollision = onCollision;
        return this;
    }

    public PhysicsComponent createPhysicsComponent() {
        return new PhysicsComponent(entity, body, positionTrackers, velocityProvider, onCollision);
    }
}