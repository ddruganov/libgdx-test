package org.ddruganov.entity.stationary;

import com.badlogic.gdx.graphics.Pixmap;
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

public class Wall extends Entity {
    public Wall(Game game, Vector2 position, int width, int height) {
        TextureStack textureStack = new TextureStack(new Texture[]{
                new Texture(new Pixmap(width, height, Pixmap.Format.RGB888))
        });

        RenderComponent renderer = new RenderComponent(this, textureStack);
        addComponent(renderer);

        PhysicsComponent physicsComponent = new PhysicsComponentBuilder()
                .setEntity(this)
                .setBody(
                        new PhysicsBodyBuilder(game.getWorld())
                                .setRenderable(textureStack)
                                .setPosition(position)
                                .setBodyType(BodyDef.BodyType.StaticBody)
                                .createBody()
                )
                .setPositionTrackers(new PositionTracker[]{renderer})
                .createPhysicsComponent();
        addComponent(physicsComponent);
    }
}
