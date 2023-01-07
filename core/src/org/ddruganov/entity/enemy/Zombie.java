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
import org.ddruganov.entity.component.physics.PhysicsComponent;
import org.ddruganov.entity.component.physics.PhysicsComponentBuilder;
import org.ddruganov.entity.component.physics.TransformTracker;
import org.ddruganov.physics.PhysicsBodyBuilder;
import org.ddruganov.render.SpriteStack;

public class Zombie extends Entity {

    public static final int SPEED = 5;

    public Zombie(Game game, Vector2 position) {
        SpriteStack spriteStack = new SpriteStack(new Sprite[]{
                new Sprite(new Texture(Gdx.files.internal("zombie.png")))
        });

        RenderComponent renderer = new RenderComponent(this, spriteStack);
        addComponent(renderer);

        FollowEntityControllerComponent controller = new FollowEntityControllerComponent(this, game.getPlayer(), SPEED);
        addComponent(controller);

        PhysicsComponent physicsComponent = new PhysicsComponentBuilder()
                .setEntity(this)
                .setBody(
                        new PhysicsBodyBuilder(game.getWorld())
                                .setRenderable(spriteStack)
                                .setBodyType(BodyDef.BodyType.DynamicBody)
                                .setPosition(position)
                                .createBody()
                )
                .setTransformTrackers(new TransformTracker[]{renderer, controller})
                .setVelocityProvider(controller)
                .createPhysicsComponent();
        addComponent(physicsComponent);
    }
}
