package org.ddruganov.entity.enemy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import org.ddruganov.Game;
import org.ddruganov.entity.Entity;
import org.ddruganov.entity.component.RenderComponent;
import org.ddruganov.entity.component.enemy.FollowEntityControllerComponent;
import org.ddruganov.entity.component.physics.PhysicsComponent;
import org.ddruganov.entity.component.physics.PositionTracker;
import org.ddruganov.util.PhysicsBodyBuilder;

public class Zombie extends Entity {

    public static final int SPEED = 10;

    public Zombie(Game game, Vector2 position) {
        Texture texture = new Texture(Gdx.files.internal("zombie.png"));

        RenderComponent renderer = new RenderComponent(texture);
        addComponent(renderer);

        FollowEntityControllerComponent controller = new FollowEntityControllerComponent(game.getPlayer(), SPEED);
        addComponent(controller);

        PhysicsComponent physicsComponent = new PhysicsComponent(
                new PhysicsBodyBuilder(game.getWorld())
                        .setTexture(texture)
                        .setBodyType(BodyDef.BodyType.DynamicBody)
                        .setPosition(position)
                        .get(),
                new PositionTracker[]{renderer, controller},
                controller);
        addComponent(physicsComponent);
    }
}
