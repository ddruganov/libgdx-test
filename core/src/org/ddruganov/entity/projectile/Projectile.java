package org.ddruganov.entity.projectile;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import org.ddruganov.Game;
import org.ddruganov.entity.Entity;
import org.ddruganov.entity.component.RenderComponent;
import org.ddruganov.entity.component.physics.PhysicsComponent;
import org.ddruganov.entity.component.physics.PhysicsComponentBuilder;
import org.ddruganov.entity.component.physics.PositionTracker;
import org.ddruganov.entity.component.projectile.ProjectileControllerComponent;
import org.ddruganov.render.Renderable;
import org.ddruganov.util.PhysicsBodyBuilder;

public abstract class Projectile extends Entity {
    private final Entity sender;

    public Projectile(Game game, Entity sender, Renderable renderable, Vector2 position, Vector2 direction) {
        this.sender = sender;

        RenderComponent renderer = new RenderComponent(this, renderable);
        addComponent(renderer);

        ProjectileControllerComponent controller = new ProjectileControllerComponent(this, direction, 500);
        addComponent(controller);

        PhysicsComponent physicsComponent = new PhysicsComponentBuilder()
                .setEntity(this)
                .setBody(
                        new PhysicsBodyBuilder(game.getWorld())
                                .setRenderable(renderable)
                                .setBodyType(BodyDef.BodyType.DynamicBody)
                                .setPosition(position)
                                .setAngle(direction.angleDeg())
                                .createBody()
                )
                .setPositionTrackers(new PositionTracker[]{renderer})
                .setVelocityProvider(controller)
                .createPhysicsComponent();
        addComponent(physicsComponent);
    }
}
