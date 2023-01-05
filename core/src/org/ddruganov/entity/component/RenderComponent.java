package org.ddruganov.entity.component;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import org.ddruganov.Game;
import org.ddruganov.entity.component.physics.PositionTracker;

public class RenderComponent implements EntityComponent, PositionTracker {

    private final Texture texture;
    private Vector2 position;

    public RenderComponent(Texture texture) {
        this.texture = texture;
    }

    @Override
    public void update(Game game) {
        game.getSpriteBatch().draw(texture, position.x, position.y);
    }

    @Override
    public void setPosition(Vector2 value) {
        this.position = value;
    }
}
