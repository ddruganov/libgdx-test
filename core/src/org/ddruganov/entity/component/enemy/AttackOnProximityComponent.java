package org.ddruganov.entity.component.enemy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import org.ddruganov.entity.Entity;
import org.ddruganov.entity.component.EntityComponent;
import org.ddruganov.entity.component.health.HealthComponent;
import org.ddruganov.entity.component.physics.PhysicsComponent;
import org.ddruganov.layer.GameplayLayer;

public class AttackOnProximityComponent extends EntityComponent {
    private final Entity entityToAttack;
    private final float range;
    private final float damage;
    private final float cooldown;
    private float cooldownLeft;

    public AttackOnProximityComponent(Entity entity, Entity entityToAttack, float range, float damage, float cooldown) {
        super(entity);
        this.entityToAttack = entityToAttack;
        this.range = range;
        this.damage = damage;
        this.cooldown = cooldown;
        this.cooldownLeft = this.cooldown;
    }

    @Override
    public void update(GameplayLayer layer) {
        super.update(layer);

        if (this.entityToAttack == null || this.entityToAttack.isDestroyed()) {
            return;
        }

        this.cooldownLeft -= Gdx.graphics.getDeltaTime();
        if (this.cooldownLeft > 0) {
            return;
        }
        this.cooldownLeft = this.cooldown;

        Vector2 start = this.entity.getComponent(PhysicsComponent.class).getPosition();
        Vector2 end = this.entityToAttack.getComponent(PhysicsComponent.class).getPosition();

        float distance = Math.abs(start.cpy().sub(end.cpy()).len());
        if (distance > this.range) {
            return;
        }

        layer.getPhysicsWorld().rayCast((fixture, point, normal, fraction) -> {

            Entity hitEntity = ((PhysicsComponent) fixture.getBody().getUserData()).getEntity();

            if (!hitEntity.equals(AttackOnProximityComponent.this.entityToAttack)) {
                return 0;
            }

            HealthComponent health = hitEntity.getComponent(HealthComponent.class);
            if (health == null) {
                return 0;
            }

            health.damage(AttackOnProximityComponent.this.damage);

            return 0;
        }, start, end);
    }
}
