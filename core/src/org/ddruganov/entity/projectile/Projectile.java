package org.ddruganov.entity.projectile;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import org.ddruganov.entity.Entity;
import org.ddruganov.entity.component.RenderComponent;
import org.ddruganov.entity.component.health.HealthComponent;
import org.ddruganov.entity.component.particle.OnDestructionParticleSystemSpawnerComponent;
import org.ddruganov.entity.component.physics.PhysicsComponent;
import org.ddruganov.entity.component.physics.PhysicsComponentBuilder;
import org.ddruganov.entity.component.projectile.DirectionBasedControllerComponent;
import org.ddruganov.entity.particles.ParticleSystemBuilder;
import org.ddruganov.layer.GameplayLayer;
import org.ddruganov.physics.PhysicsBodyBuilder;
import org.ddruganov.render.Renderable;

public class Projectile extends Entity {
    private final Entity sender;
    private final float baseDamage;

    public Projectile(GameplayLayer layer, Entity sender, Renderable renderable, Vector2 position, Vector2 direction, float baseDamage) {
        super(layer);

        this.sender = sender;
        this.baseDamage = baseDamage;

        OnDestructionParticleSystemSpawnerComponent particleSystemSpawner = new OnDestructionParticleSystemSpawnerComponent(
                this,
                new ParticleSystemBuilder()
                        .setLayer(layer)
                        .setEmitCooldown(.01f)
                        .setColor(Color.GRAY)
                        .setSize(1)
                        .setTtl(.1f)
        );
        addComponent(particleSystemSpawner);

        DirectionBasedControllerComponent controller = new DirectionBasedControllerComponent(this, direction.cpy(), 500);
        addComponent(controller);

        PhysicsComponent physicsComponent = new PhysicsComponentBuilder()
                .setEntity(this)
                .setBody(
                        new PhysicsBodyBuilder(layer.getPhysicsWorld())
                                .setRenderable(renderable)
                                .setBodyType(BodyDef.BodyType.DynamicBody)
                                .setPosition(position)
                                .setAngle(direction.cpy().angleDeg())
                                .createBody()
                )
                .setVelocityProvider(controller)
                .setOnCollision((Entity with, Vector2 at) -> {
                    particleSystemSpawner.setPosition(at);

                    HealthComponent health = with.getComponent(HealthComponent.class);
                    if (health != null) {
                        health.damage(this.getDamage());
                    }

                    this.destroy();
                })
                .createPhysicsComponent();
        addComponent(physicsComponent);

        RenderComponent renderer = new RenderComponent(this, renderable, physicsComponent);
        addComponent(renderer);
    }

    private float getDamage() {
        return this.baseDamage;
    }

    public Entity getSender() {
        return sender;
    }
}
