package org.ddruganov.entity.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.BodyDef;
import org.ddruganov.Game;
import org.ddruganov.entity.Entity;
import org.ddruganov.entity.component.RenderComponent;
import org.ddruganov.entity.component.health.HealthComponent;
import org.ddruganov.entity.component.physics.PhysicsComponent;
import org.ddruganov.entity.component.physics.PhysicsComponentBuilder;
import org.ddruganov.entity.component.physics.TransformTracker;
import org.ddruganov.entity.component.player.UserInputControllerComponent;
import org.ddruganov.entity.component.spell.SpellCasterComponent;
import org.ddruganov.entity.enemy.Zombie;
import org.ddruganov.physics.PhysicsBodyBuilder;
import org.ddruganov.render.SpriteStack;

public class Player extends Entity {
    public Player(Game game) {
        SpriteStack spriteStack = new SpriteStack(new Sprite[]{
                new Sprite(new Texture(Gdx.files.internal("player.png")))
        });
        RenderComponent renderer = new RenderComponent(this, spriteStack);
        addComponent(renderer);

        SpellCasterComponent spellCaster = new SpellCasterComponent(this);
        addComponent(spellCaster);

        UserInputControllerComponent controller = new UserInputControllerComponent(this, spellCaster);
        addComponent(controller);

        HealthComponent health = new HealthComponent(
                this,
                100,
                null
        );
        addComponent(health);

        PhysicsComponent physicsComponent = new PhysicsComponentBuilder()
                .setEntity(this)
                .setBody(
                        new PhysicsBodyBuilder(game.getWorld())
                                .setRenderable(spriteStack)
                                .setBodyType(BodyDef.BodyType.DynamicBody)
                                .createBody()
                )
                .setTransformTrackers(new TransformTracker[]{renderer})
                .setVelocityProvider(controller)
                .setOnCollision((Entity with) -> health.damage(with.getClass() == Zombie.class ? 10 : 0))
                .createPhysicsComponent();
        addComponent(physicsComponent);
    }
}
