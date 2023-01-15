package org.ddruganov.entity.component.particle;

import com.badlogic.gdx.math.Vector2;
import org.ddruganov.entity.Entity;
import org.ddruganov.entity.component.EntityComponent;
import org.ddruganov.entity.particles.ParticleSystemBuilder;
import org.ddruganov.layer.GameplayLayer;

public class OnDestructionParticleSystemSpawnerComponent extends EntityComponent {

    private final ParticleSystemBuilder particleSystemBuilder;

    private Vector2 position;

    public OnDestructionParticleSystemSpawnerComponent(Entity entity, ParticleSystemBuilder particleSystemBuilder) {
        super(entity);
        this.particleSystemBuilder = particleSystemBuilder;
    }

    public void setPosition(Vector2 value) {
        this.position = value;
    }

    @Override
    public void destroy(GameplayLayer layer) {
        super.destroy(layer);

        layer.addEntity(this.particleSystemBuilder.setPosition(this.position).createParticleSystem());
    }
}
