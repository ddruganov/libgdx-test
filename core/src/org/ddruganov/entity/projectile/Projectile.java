package org.ddruganov.entity.projectile;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import org.ddruganov.Game;
import org.ddruganov.entity.Entity;
import org.ddruganov.entity.component.RenderComponent;
import org.ddruganov.entity.component.physics.PhysicsComponent;
import org.ddruganov.entity.component.physics.PhysicsComponentBuilder;
import org.ddruganov.entity.component.physics.TransformTracker;
import org.ddruganov.entity.component.projectile.ProjectileControllerComponent;
import org.ddruganov.physics.PhysicsBodyBuilder;
import org.ddruganov.render.Renderable;

public abstract class Projectile extends Entity {
    private final Entity sender;

    public Projectile(Game game, Entity sender, Renderable renderable, Vector2 position, Vector2 direction) {
        this.sender = sender;

        RenderComponent renderer = new RenderComponent(this, renderable);
        addComponent(renderer);

        ProjectileControllerComponent controller = new ProjectileControllerComponent(this, direction.cpy(), 500);
        addComponent(controller);

        PhysicsComponent physicsComponent = new PhysicsComponentBuilder()
                .setEntity(this)
                .setBody(
                        new PhysicsBodyBuilder(game.getWorld())
                                .setRenderable(renderable)
                                .setBodyType(BodyDef.BodyType.DynamicBody)
                                .setPosition(position)
                                .setAngle(direction.cpy().angleDeg())
                                .createBody()
                )
                .setTransformTrackers(new TransformTracker[]{renderer})
                .setVelocityProvider(controller)
                .createPhysicsComponent();
        addComponent(physicsComponent);
    }

    public Entity getSender() {
        return sender;
    }
}
