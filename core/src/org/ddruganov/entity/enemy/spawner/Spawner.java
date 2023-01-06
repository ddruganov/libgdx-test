package org.ddruganov.entity.enemy.spawner;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import org.ddruganov.Game;
import org.ddruganov.entity.Entity;
import org.ddruganov.entity.component.RenderComponent;
import org.ddruganov.entity.component.physics.PhysicsComponent;
import org.ddruganov.entity.component.physics.PhysicsComponentBuilder;
import org.ddruganov.entity.component.physics.PositionTracker;
import org.ddruganov.render.TextureStack;
import org.ddruganov.util.PhysicsBodyBuilder;

public class Spawner extends Entity {

    private final float cooldown;
    private final OnSpawnCallback onSpawn;
    private float cooldownLeft;

    public Spawner(Game game, Vector2 position, float cooldown, OnSpawnCallback onSpawn, String textureName) {
        this.cooldown = cooldown;
        this.cooldownLeft = this.cooldown;
        this.onSpawn = onSpawn;

        Texture texture = new Texture(Gdx.files.internal("spawner.png"));
        Texture entityTexture = new Texture(Gdx.files.internal(textureName));

        RenderComponent renderer = new RenderComponent(this, new TextureStack(new Texture[]{texture, entityTexture}));
        addComponent(renderer);

        PhysicsComponent physicsComponent = new PhysicsComponentBuilder()
                .setEntity(this)
                .setBody(
                        new PhysicsBodyBuilder(game.getWorld())
                                .setTexture(texture)
                                .setBodyType(BodyDef.BodyType.StaticBody)
                                .setPosition(position)
                                .createBody()
                )
                .setPositionTrackers(new PositionTracker[]{renderer})
                .createPhysicsComponent();
        addComponent(physicsComponent);
    }

    @Override
    public void update(Game game) {

        super.update(game);

        cooldownLeft -= Gdx.graphics.getDeltaTime();
        if (cooldownLeft > 0) {
            return;
        }

        Vector2 spawnPos = this.getComponent(PhysicsComponent.class).getPosition().add(
                new Vector2(1, 1).scl(((float) Math.random() * 2 - 1) * 50f, ((float) Math.random() * 2 - 1) * 50f)
        );

        game.addEntity(this.onSpawn.spawn(game, spawnPos));

        cooldownLeft = cooldown;
    }
}
