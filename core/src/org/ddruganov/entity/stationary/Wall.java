package org.ddruganov.entity.stationary;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import org.ddruganov.Game;
import org.ddruganov.entity.Entity;
import org.ddruganov.entity.component.RenderComponent;
import org.ddruganov.entity.component.physics.PhysicsComponent;
import org.ddruganov.entity.component.physics.PositionTracker;
import org.ddruganov.util.PhysicsBodyBuilder;

public class Wall extends Entity {
    public Wall(Game game, Vector2 position, int width, int height) {
        Texture texture = new Texture(new Pixmap(width, height, Pixmap.Format.RGB888));

        RenderComponent renderer = new RenderComponent(texture);
        addComponent(renderer);

        PhysicsComponent physicsComponent = new PhysicsComponent(
                new PhysicsBodyBuilder(game.getWorld())
                        .setTexture(texture)
                        .setPosition(position)
                        .setBodyType(BodyDef.BodyType.StaticBody)
                        .get(),
                new PositionTracker[]{renderer},
                null);
        addComponent(physicsComponent);
    }
}
