package org.ddruganov.entity.particles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import org.ddruganov.entity.Entity;
import org.ddruganov.entity.component.physics.PhysicsComponent;
import org.ddruganov.entity.component.physics.PhysicsComponentBuilder;
import org.ddruganov.layer.GameplayLayer;
import org.ddruganov.physics.PhysicsBodyBuilder;
import org.ddruganov.render.SpriteStack;

public class ParticleSystem extends Entity {

    private final float emitCooldown;
    private final Color particleColor;
    private final int particleSize;
    private float emitCooldownLeft;
    private float ttl;

    public ParticleSystem(GameplayLayer layer, Vector2 position, float emitCooldown, float ttl, Color particleColor, int particleSize) {
        super(layer);

        this.emitCooldown = emitCooldown;
        this.ttl = ttl;
        this.particleColor = particleColor;
        this.particleSize = particleSize;

        PhysicsComponent physicsComponent = new PhysicsComponentBuilder()
                .setEntity(this)
                .setBody(
                        new PhysicsBodyBuilder(layer.getPhysicsWorld())
                                .setShape(new CircleShape() {{
                                    setRadius(.1f);
                                }})
                                .setBodyType(BodyDef.BodyType.StaticBody)
                                .setPosition(position)
                                .createBody()
                )
                .createPhysicsComponent();
        addComponent(physicsComponent);
    }

    @Override
    public void update(GameplayLayer layer) {
        super.update(layer);

        this.ttl -= Gdx.graphics.getDeltaTime();
        if (this.ttl < 0) {
            this.destroy();
            return;
        }

        if (this.isDestroyed()) {
            return;
        }

        this.emitCooldownLeft -= Gdx.graphics.getDeltaTime();
        if (this.emitCooldownLeft > 0) {
            return;
        }

        this.emitCooldownLeft = this.emitCooldown;

        layer.addEntity(
                new ParticleBuilder()
                        .setLayer(layer)
                        .setRenderable(
                                new SpriteStack(
                                        new Sprite(new Texture(new Pixmap(ParticleSystem.this.particleSize, ParticleSystem.this.particleSize, Pixmap.Format.RGB888) {{
                                            setColor(ParticleSystem.this.particleColor);
                                            fill();
                                        }}))
                                ))
                        .setPosition(this.getComponent(PhysicsComponent.class).getPosition())
                        .setDirection(
                                new Vector2(
                                        (float) Math.random() * 2 - 1,
                                        (float) Math.random() * 2 - 1
                                )
                        )
                        .setSpeed(10)
                        .setTtl(.1f)
                        .createParticle()
        );
    }
}
