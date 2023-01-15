package org.ddruganov.entity.enemy.spawner;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import org.ddruganov.entity.Entity;
import org.ddruganov.entity.component.RenderComponent;
import org.ddruganov.entity.component.health.HealthComponent;
import org.ddruganov.entity.component.physics.PhysicsComponent;
import org.ddruganov.entity.component.physics.PhysicsComponentBuilder;
import org.ddruganov.layer.GameplayLayer;
import org.ddruganov.physics.PhysicsBodyBuilder;
import org.ddruganov.render.SpriteStack;

public class Spawner extends Entity {

    private final float cooldown;
    private final OnSpawnCallback onSpawn;
    private float cooldownLeft;

    public Spawner(GameplayLayer layer, Vector2 position, float cooldown, OnSpawnCallback onSpawn, String textureName) {
        super(layer);
        this.cooldown = cooldown;
        this.cooldownLeft = this.cooldown;
        this.onSpawn = onSpawn;

        SpriteStack spriteStack = new SpriteStack(
                new Sprite(new Texture(Gdx.files.internal("spawner.png"))),
                new Sprite(new Texture(Gdx.files.internal(textureName)))
        );

        HealthComponent health = new HealthComponent(this, 100, this::destroy);
        addComponent(health);

        PhysicsComponent physicsComponent = new PhysicsComponentBuilder()
                .setEntity(this)
                .setBody(
                        new PhysicsBodyBuilder(layer.getPhysicsWorld())
                                .setRenderable(spriteStack)
                                .setBodyType(BodyDef.BodyType.StaticBody)
                                .setPosition(position)
                                .createBody()
                )
                .createPhysicsComponent();
        addComponent(physicsComponent);

        RenderComponent renderer = new RenderComponent(this, spriteStack, physicsComponent);
        addComponent(renderer);
    }

    @Override
    public void update(GameplayLayer layer) {

        super.update(layer);

        cooldownLeft -= Gdx.graphics.getDeltaTime();
        if (cooldownLeft > 0) {
            return;
        }

        Vector2 spawnPos = this.getComponent(PhysicsComponent.class).getPosition().add(
                new Vector2(
                        (float) Math.random() * 2 - 1,
                        (float) Math.random() * 2 - 1
                )
                        .scl(50f)
        );

        layer.addEntity(this.onSpawn.spawn(layer, spawnPos));

        cooldownLeft = cooldown;
    }
}
