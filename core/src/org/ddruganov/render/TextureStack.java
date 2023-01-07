package org.ddruganov.render;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class TextureStack implements Renderable {

    private final Texture[] textures;

    public TextureStack(Texture[] textures) {
        this.textures = textures;
    }

    @Override
    public void render(SpriteBatch spriteBatch, Vector2 origin) {
        for (Texture texture : textures) {
            float x = origin.x - ((float) texture.getWidth() / 2);
            float y = origin.y - ((float) texture.getHeight() / 2);
            spriteBatch.draw(texture, x, y);
        }
    }

    @Override
    public int getWidth() {
        int value = 0;
        for (Texture texture : textures) {
            value = Math.max(value, texture.getWidth());
        }

        return value;
    }

    @Override
    public int getHeight() {
        int value = 0;
        for (Texture texture : textures) {
            value = Math.max(value, texture.getHeight());
        }

        return value;
    }
}
