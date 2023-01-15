package org.ddruganov.entity.particles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import org.ddruganov.entity.Entity;
import org.ddruganov.entity.component.RenderComponent;
import org.ddruganov.entity.component.physics.PhysicsComponent;
import org.ddruganov.entity.component.physics.PhysicsComponentBuilder;
import org.ddruganov.entity.component.projectile.DirectionBasedControllerComponent;
import org.ddruganov.layer.GameplayLayer;
import org.ddruganov.physics.PhysicsBodyBuilder;
import org.ddruganov.render.Renderable;

public class Particle extends Entity {
    private float ttl;

    public Particle(GameplayLayer layer, Renderable renderable, Vector2 position, Vector2 direction, int speed, float ttl) {
        super(layer);

        this.ttl = ttl;

        DirectionBasedControllerComponent controller = new DirectionBasedControllerComponent(this, direction.cpy(), speed);
        addComponent(controller);

        PhysicsComponent physicsComponent = new PhysicsComponentBuilder()
                .setEntity(this)
                .setBody(
                        new PhysicsBodyBuilder(layer.getPhysicsWorld())
                                .setRenderable(renderable)
                                .setBodyType(BodyDef.BodyType.DynamicBody)
                                .setPosition(position)
                                .createBody()
                )
                .setVelocityProvider(controller)
                .createPhysicsComponent();
        addComponent(physicsComponent);

        RenderComponent renderer = new RenderComponent(this, renderable, physicsComponent);
        addComponent(renderer);
    }

    @Override
    public void update(GameplayLayer layer) {
        super.update(layer);

        this.ttl -= Gdx.graphics.getDeltaTime();
        if (this.ttl > 0) {
            return;
        }

        if (this.isDestroyed()) {
            return;
        }

        this.destroy();
    }
}
