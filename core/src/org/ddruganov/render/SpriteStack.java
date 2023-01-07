package org.ddruganov.render;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class SpriteStack implements Renderable {

    private final Sprite[] sprites;

    public SpriteStack(Sprite[] sprites) {
        this.sprites = sprites;
    }

    @Override
    public void render(SpriteBatch spriteBatch, Vector2 origin, float rotation) {
        for (Sprite sprite : sprites) {
            sprite.setRotation(rotation);
            sprite.setOriginBasedPosition(origin.x, origin.y);
            sprite.draw(spriteBatch);
        }
    }

    @Override
    public float getWidth() {
        float value = 0;
        for (Sprite sprite : sprites) {
            value = Math.max(value, sprite.getWidth());
        }

        return value;
    }

    @Override
    public float getHeight() {
        float value = 0;
        for (Sprite sprite : sprites) {
            value = Math.max(value, sprite.getHeight());
        }

        return value;
    }
}
