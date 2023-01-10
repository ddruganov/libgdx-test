package org.ddruganov;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import org.ddruganov.layer.GameplayLayer;
import org.ddruganov.layer.Layer;
import org.ddruganov.layer.UiLayer;

import java.util.HashMap;

public class Game extends ApplicationAdapter {

    private final HashMap<String, Layer> layers = new HashMap<>();
    private SpriteBatch batch;

    @Override
    public void create() {
        this.batch = new SpriteBatch();
        this.layers.put(GameplayLayer.class.getSimpleName(), new GameplayLayer(this, this.batch));
        this.layers.put(UiLayer.class.getSimpleName(), new UiLayer(this, this.batch));
    }

    @Override
    public void render() {
        ScreenUtils.clear(1, 1, 1, 1);

        this.layers.forEach((key, value) -> value.update());
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
