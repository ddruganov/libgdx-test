package org.ddruganov.physics;

import com.badlogic.gdx.physics.box2d.Fixture;
import org.ddruganov.entity.Entity;
import org.ddruganov.entity.component.physics.PhysicsComponent;
import org.ddruganov.entity.projectile.Projectile;

public class ContactFilter implements com.badlogic.gdx.physics.box2d.ContactFilter {
    @Override
    public boolean shouldCollide(Fixture fixtureA, Fixture fixtureB) {

        Entity left = ((PhysicsComponent) fixtureA.getBody().getUserData()).getEntity();
        Entity right = ((PhysicsComponent) fixtureB.getBody().getUserData()).getEntity();

        boolean isProjectile = right instanceof Projectile;
        if (!isProjectile) {
            return true;
        }

        Projectile projectile = (Projectile) right;
        return !projectile.getSender().getUuid().equals(left.getUuid());
    }
}
