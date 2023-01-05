package org.ddruganov.entity.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.BodyDef;
import org.ddruganov.Game;
import org.ddruganov.entity.Entity;
import org.ddruganov.entity.component.RenderComponent;
import org.ddruganov.entity.component.physics.PhysicsComponent;
import org.ddruganov.entity.component.physics.PositionTracker;
import org.ddruganov.entity.component.player.PlayerControllerComponent;
import org.ddruganov.util.PhysicsBodyBuilder;

public class Player extends Entity {
    public Player(Game game) {
        Texture texture = new Texture(Gdx.files.internal("player.png"));

        RenderComponent renderer = new RenderComponent(texture);
        addComponent(renderer);

        PlayerControllerComponent controller = new PlayerControllerComponent();
        addComponent(controller);

        PhysicsComponent physicsComponent = new PhysicsComponent(
                new PhysicsBodyBuilder(game.getWorld())
                        .setTexture(texture)
                        .setBodyType(BodyDef.BodyType.DynamicBody)
                        .get(),
                new PositionTracker[]{renderer},
                controller);
        addComponent(physicsComponent);
    }
}
