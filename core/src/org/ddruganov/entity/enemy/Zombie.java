package org.ddruganov.entity.enemy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import org.ddruganov.Game;
import org.ddruganov.entity.Entity;
import org.ddruganov.entity.component.RenderComponent;
import org.ddruganov.entity.component.enemy.FollowEntityControllerComponent;
import org.ddruganov.entity.component.health.HealthComponent;
import org.ddruganov.entity.component.physics.PhysicsComponent;
import org.ddruganov.entity.component.physics.PhysicsComponentBuilder;
import org.ddruganov.physics.PhysicsBodyBuilder;
import org.ddruganov.render.SpriteStack;

public class Zombie extends Entity {

    public static final int SPEED = 5;

    public Zombie(Game game, Vector2 position) {
        SpriteStack spriteStack = new SpriteStack(
                new Sprite(new Texture(Gdx.files.internal("zombie.png")))
        );

        FollowEntityControllerComponent controller = new FollowEntityControllerComponent(this, game.getPlayer(), SPEED);
        addComponent(controller);

        HealthComponent healthComponent = new HealthComponent(this, 10, this::destroy);
        addComponent(healthComponent);

        PhysicsComponent physicsComponent = new PhysicsComponentBuilder()
                .setEntity(this)
                .setBody(
                        new PhysicsBodyBuilder(game.getWorld())
                                .setRenderable(spriteStack)
                                .setBodyType(BodyDef.BodyType.DynamicBody)
                                .setPosition(position)
                                .createBody()
                )
                .setVelocityProvider(controller)
                .createPhysicsComponent();
        addComponent(physicsComponent);

        RenderComponent renderer = new RenderComponent(this, spriteStack, physicsComponent);
        addComponent(renderer);
    }
}
