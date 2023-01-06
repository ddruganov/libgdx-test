package org.ddruganov.entity.component;

import com.badlogic.gdx.math.Vector2;
import org.ddruganov.Game;
import org.ddruganov.entity.Entity;
import org.ddruganov.entity.component.physics.PositionTracker;
import org.ddruganov.render.Renderable;

public class RenderComponent extends EntityComponent implements PositionTracker {

    private final Renderable renderable;
    private Vector2 position;

    public RenderComponent(Entity entity, Renderable renderable) {
        super(entity);
        this.renderable = renderable;
    }

    @Override
    public void update(Game game) {
        this.renderable.render(game.getSpriteBatch(), position);
    }

    @Override
    public void setPosition(Vector2 value) {
        this.position = value;
    }
}
