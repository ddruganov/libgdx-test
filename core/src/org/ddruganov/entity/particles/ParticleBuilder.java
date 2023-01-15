package org.ddruganov.entity.particles;

import com.badlogic.gdx.math.Vector2;
import org.ddruganov.layer.GameplayLayer;
import org.ddruganov.render.Renderable;

public class ParticleBuilder {
    private GameplayLayer layer;
    private Renderable renderable;
    private Vector2 position;
    private Vector2 direction;
    private int speed;
    private float ttl;

    public ParticleBuilder setLayer(GameplayLayer layer) {
        this.layer = layer;
        return this;
    }

    public ParticleBuilder setRenderable(Renderable renderable) {
        this.renderable = renderable;
        return this;
    }

    public ParticleBuilder setPosition(Vector2 position) {
        this.position = position;
        return this;
    }

    public ParticleBuilder setDirection(Vector2 direction) {
        this.direction = direction;
        return this;
    }

    public ParticleBuilder setSpeed(int speed) {
        this.speed = speed;
        return this;
    }

    public ParticleBuilder setTtl(float ttl) {
        this.ttl = ttl;
        return this;
    }

    public Particle createParticle() {
        return new Particle(layer, renderable, position, direction, speed, ttl);
    }
}