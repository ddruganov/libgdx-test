package org.ddruganov.entity.stationary;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import org.ddruganov.entity.Entity;
import org.ddruganov.entity.component.RenderComponent;
import org.ddruganov.entity.component.physics.PhysicsComponent;
import org.ddruganov.entity.component.physics.PhysicsComponentBuilder;
import org.ddruganov.layer.GameplayLayer;
import org.ddruganov.physics.PhysicsBodyBuilder;
import org.ddruganov.render.SpriteStack;

public class Wall extends Entity {
    public Wall(GameplayLayer layer, Vector2 position, int width, int height) {
        super(layer);

        SpriteStack spriteStack = new SpriteStack(
                new Sprite(new Texture(new Pixmap(width, height, Pixmap.Format.RGB888)))
        );

        PhysicsComponent physicsComponent = new PhysicsComponentBuilder()
                .setEntity(this)
                .setBody(
                        new PhysicsBodyBuilder(layer.getPhysicsWorld())
                                .setRenderable(spriteStack)
                                .setPosition(position)
                                .setBodyType(BodyDef.BodyType.StaticBody)
                                .createBody()
                )
                .createPhysicsComponent();
        addComponent(physicsComponent);

        RenderComponent renderer = new RenderComponent(this, spriteStack, physicsComponent);
        addComponent(renderer);
    }
}
