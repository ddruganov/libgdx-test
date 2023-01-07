package org.ddruganov.entity.stationary;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import org.ddruganov.Game;
import org.ddruganov.entity.Entity;
import org.ddruganov.entity.component.RenderComponent;
import org.ddruganov.entity.component.physics.PhysicsComponent;
import org.ddruganov.entity.component.physics.PhysicsComponentBuilder;
import org.ddruganov.entity.component.physics.TransformTracker;
import org.ddruganov.physics.PhysicsBodyBuilder;
import org.ddruganov.render.SpriteStack;

public class Wall extends Entity {
    public Wall(Game game, Vector2 position, int width, int height) {
        SpriteStack spriteStack = new SpriteStack(new Sprite[]{
                new Sprite(new Texture(new Pixmap(width, height, Pixmap.Format.RGB888)))
        });

        RenderComponent renderer = new RenderComponent(this, spriteStack);
        addComponent(renderer);

        PhysicsComponent physicsComponent = new PhysicsComponentBuilder()
                .setEntity(this)
                .setBody(
                        new PhysicsBodyBuilder(game.getWorld())
                                .setRenderable(spriteStack)
                                .setPosition(position)
                                .setBodyType(BodyDef.BodyType.StaticBody)
                                .createBody()
                )
                .setTransformTrackers(new TransformTracker[]{renderer})
                .createPhysicsComponent();
        addComponent(physicsComponent);
    }
}
