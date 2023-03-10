package org.ddruganov.physics;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.Manifold;
import org.ddruganov.entity.component.physics.PhysicsComponent;

public class ContactListener implements com.badlogic.gdx.physics.box2d.ContactListener {
    @Override
    public void beginContact(Contact contact) {
        PhysicsComponent left = (PhysicsComponent) contact.getFixtureA().getBody().getUserData();
        PhysicsComponent right = (PhysicsComponent) contact.getFixtureB().getBody().getUserData();

        Vector2 contactPoint = contact.getWorldManifold().getPoints()[contact.getWorldManifold().getNumberOfContactPoints() - 1];

        contact.getWorldManifold().getPoints();
        left.onCollision(right, contactPoint);
        right.onCollision(left, contactPoint);
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
