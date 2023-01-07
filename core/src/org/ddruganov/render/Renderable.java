package org.ddruganov.render;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public interface Renderable {
    void render(SpriteBatch spriteBatch, Vector2 origin);

    int getWidth();

    int getHeight();
}
