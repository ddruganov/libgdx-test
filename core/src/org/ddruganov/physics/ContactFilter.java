package org.ddruganov.physics;

import com.badlogic.gdx.physics.box2d.Fixture;
import org.ddruganov.entity.Entity;
import org.ddruganov.entity.component.physics.PhysicsComponent;
import org.ddruganov.entity.particles.Particle;
import org.ddruganov.entity.particles.ParticleSystem;
import org.ddruganov.entity.projectile.Projectile;

public class ContactFilter implements com.badlogic.gdx.physics.box2d.ContactFilter {
    @Override
    public boolean shouldCollide(Fixture fixtureA, Fixture fixtureB) {

        Entity left = ((PhysicsComponent) fixtureA.getBody().getUserData()).getEntity();
        Entity right = ((PhysicsComponent) fixtureB.getBody().getUserData()).getEntity();

        if (left instanceof Projectile && right instanceof Projectile) {
            return false;
        }

        if (left instanceof Particle || right instanceof Particle) {
            return false;
        }

        if (left instanceof ParticleSystem || right instanceof ParticleSystem) {
            return false;
        }

        boolean isProjectile = right instanceof Projectile;
        if (!isProjectile) {
            return true;
        }

        Projectile projectile = (Projectile) right;
        return !projectile.getSender().getUuid().equals(left.getUuid());
    }
}
