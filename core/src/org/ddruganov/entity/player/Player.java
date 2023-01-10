package org.ddruganov.entity.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.BodyDef;
import org.ddruganov.entity.Entity;
import org.ddruganov.entity.component.RenderComponent;
import org.ddruganov.entity.component.health.HealthComponent;
import org.ddruganov.entity.component.physics.PhysicsComponent;
import org.ddruganov.entity.component.physics.PhysicsComponentBuilder;
import org.ddruganov.entity.component.player.UserInputControllerComponent;
import org.ddruganov.entity.component.spell.SpellCasterComponent;
import org.ddruganov.layer.GameplayLayer;
import org.ddruganov.physics.PhysicsBodyBuilder;
import org.ddruganov.render.SpriteStack;

public class Player extends Entity {
    public Player(GameplayLayer layer) {
        super(layer);

        SpriteStack spriteStack = new SpriteStack(
                new Sprite(new Texture(Gdx.files.internal("player.png")))
        );

        SpellCasterComponent spellCaster = new SpellCasterComponent(this);
        addComponent(spellCaster);

        UserInputControllerComponent controller = new UserInputControllerComponent(this, spellCaster);
        addComponent(controller);

        HealthComponent health = new HealthComponent(
                this,
                100,
                this::destroy
        );
        addComponent(health);

        PhysicsComponent physicsComponent = new PhysicsComponentBuilder()
                .setEntity(this)
                .setBody(
                        new PhysicsBodyBuilder(layer.getPhysicsWorld())
                                .setRenderable(spriteStack)
                                .setBodyType(BodyDef.BodyType.DynamicBody)
                                .createBody()
                )
                .setVelocityProvider(controller)
                .createPhysicsComponent();
        addComponent(physicsComponent);

        RenderComponent renderer = new RenderComponent(this, spriteStack, physicsComponent);
        addComponent(renderer);
    }
}
