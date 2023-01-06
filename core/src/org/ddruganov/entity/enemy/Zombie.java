package org.ddruganov.entity.enemy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import org.ddruganov.Game;
import org.ddruganov.entity.Entity;
import org.ddruganov.entity.component.EntityComponent;
import org.ddruganov.entity.component.enemy.FollowEntityControllerComponent;
import org.ddruganov.entity.component.physics.PhysicsComponent;
import org.ddruganov.entity.component.physics.PhysicsComponentBuilder;
import org.ddruganov.entity.component.physics.PositionTracker;
import org.ddruganov.render.TextureStack;
import org.ddruganov.util.PhysicsBodyBuilder;

public class Zombie extends Entity {

    public static final int SPEED = 5;

    public Zombie(Game game, Vector2 position) {
        Texture texture = new Texture(Gdx.files.internal("zombie.png"));

        EntityComponent.RenderComponent renderer = new EntityComponent.RenderComponent(this, new TextureStack(new Texture[]{texture}));
        addComponent(renderer);

        FollowEntityControllerComponent controller = new FollowEntityControllerComponent(this, game.getPlayer(), SPEED);
        addComponent(controller);

        PhysicsComponent physicsComponent = new PhysicsComponentBuilder()
                .setEntity(this)
                .setBody(
                        new PhysicsBodyBuilder(game.getWorld())
                                .setTexture(texture)
                                .setBodyType(BodyDef.BodyType.DynamicBody)
                                .setPosition(position)
                                .createBody()
                )
                .setPositionTrackers(new PositionTracker[]{renderer, controller})
                .setVelocityProvider(controller)
                .createPhysicsComponent();
        addComponent(physicsComponent);
    }
}
