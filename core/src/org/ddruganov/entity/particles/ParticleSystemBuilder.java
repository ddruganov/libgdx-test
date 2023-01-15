package org.ddruganov.entity.particles;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import org.ddruganov.layer.GameplayLayer;

public class ParticleSystemBuilder {
    private GameplayLayer layer;
    private Vector2 position;
    private float emitCooldown;
    private float ttl;
    private Color color;
    private int size;

    public ParticleSystemBuilder setLayer(GameplayLayer layer) {
        this.layer = layer;
        return this;
    }

    public ParticleSystemBuilder setPosition(Vector2 position) {
        this.position = position;
        return this;
    }

    public ParticleSystemBuilder setEmitCooldown(float emitCooldown) {
        this.emitCooldown = emitCooldown;
        return this;
    }

    public ParticleSystemBuilder setTtl(float ttl) {
        this.ttl = ttl;
        return this;
    }

    public ParticleSystem createParticleSystem() {
        return new ParticleSystem(layer, position, emitCooldown, ttl, color, size);
    }

    public ParticleSystemBuilder setColor(Color value) {
        this.color = value;
        return this;
    }

    public ParticleSystemBuilder setSize(int value) {
        this.size = value;
        return this;
    }
}