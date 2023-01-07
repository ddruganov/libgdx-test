package org.ddruganov.entity.component;

import org.ddruganov.Game;
import org.ddruganov.entity.Entity;
import org.ddruganov.entity.component.physics.TransformTracker;
import org.ddruganov.physics.Transform;
import org.ddruganov.render.Renderable;

public class RenderComponent extends EntityComponent implements TransformTracker {

    private final Renderable renderable;
    private Transform transform;

    public RenderComponent(Entity entity, Renderable renderable) {
        super(entity);
        this.renderable = renderable;
    }

    @Override
    public void update(Game game) {
        this.renderable.render(game.getSpriteBatch(), transform.getPosition(), transform.getRotation());
    }

    @Override
    public void setTransform(Transform value) {
        this.transform = value;
    }
}
