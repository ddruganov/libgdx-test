package org.ddruganov.entity.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.BodyDef;
import org.ddruganov.Game;
import org.ddruganov.entity.Entity;
import org.ddruganov.entity.component.RenderComponent;
import org.ddruganov.entity.component.health.HealthComponent;
import org.ddruganov.entity.component.physics.PhysicsComponent;
import org.ddruganov.entity.component.physics.PhysicsComponentBuilder;
import org.ddruganov.entity.component.physics.PositionTracker;
import org.ddruganov.entity.component.player.UserInputControllerComponent;
import org.ddruganov.entity.component.spell.SpellCasterComponent;
import org.ddruganov.render.TextureStack;
import org.ddruganov.util.PhysicsBodyBuilder;

public class Player extends Entity {
    public Player(Game game) {
        Texture texture = new Texture(Gdx.files.internal("player.png"));

        RenderComponent renderer = new RenderComponent(this, new TextureStack(new Texture[]{texture}));
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
                                .setTexture(texture)
                                .setBodyType(BodyDef.BodyType.DynamicBody)
                                .createBody()
                )
                .setPositionTrackers(new PositionTracker[]{renderer})
                .setVelocityProvider(controller)
                .setOnCollision(() -> health.damage(10))
                .createPhysicsComponent();
        addComponent(physicsComponent);
    }
}
