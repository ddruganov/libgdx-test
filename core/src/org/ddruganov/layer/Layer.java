package org.ddruganov.layer;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.ddruganov.Game;

public abstract class Layer {

    protected final Game game;
    private final SpriteBatch spriteBatch;
    private final OrthographicCamera camera;

    public Layer(Game game, SpriteBatch spriteBatch, int width, int height) {
        this.game = game;
        this.spriteBatch = spriteBatch;
        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false, width, height);
    }

    public OrthographicCamera getCamera() {
        return this.camera;
    }

    public void update() {
        this.camera.update();
        this.spriteBatch.setProjectionMatrix(this.camera.combined);
        this.spriteBatch.begin();
        this._update();
        this.spriteBatch.end();
    }

    protected abstract void _update();

    public SpriteBatch getSpriteBatch() {
        return this.spriteBatch;
    }
}
