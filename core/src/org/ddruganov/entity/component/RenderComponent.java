package org.ddruganov.entity.component;

import org.ddruganov.entity.Entity;
import org.ddruganov.entity.component.physics.TransformProvider;
import org.ddruganov.layer.GameplayLayer;
import org.ddruganov.physics.Transform;
import org.ddruganov.render.Renderable;

public class RenderComponent extends EntityComponent {

    private final Renderable renderable;
    private final TransformProvider transformProvider;

    public RenderComponent(Entity entity, Renderable renderable, TransformProvider transformProvider) {
        super(entity);
        this.renderable = renderable;
        this.transformProvider = transformProvider;
    }

    @Override
    public void update(GameplayLayer layer) {
        Transform transform = this.transformProvider.getTransform();
        this.renderable.render(layer.getSpriteBatch(), transform.getPosition(), transform.getRotation());
    }

    @Override
    public void destroy(GameplayLayer layer) {
        super.destroy(layer);

        this.renderable.destroy();
    }
}
