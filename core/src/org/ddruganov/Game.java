package org.ddruganov;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.ScreenUtils;
import org.ddruganov.entity.player.Player;

public class Game extends ApplicationAdapter {

    private World world;
    private Box2DDebugRenderer box2DDebugRenderer;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Player player;

    @Override
    public void create() {
        world = new World(Vector2.Zero, true);
        box2DDebugRenderer = new Box2DDebugRenderer();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800 / 4, 480 / 4);
        batch = new SpriteBatch();
        player = new Player(this);
    }

    @Override
    public void render() {

        world.step(1 / 60f, 6, 2);

        ScreenUtils.clear(1, 1, 1, 1);

        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();

        if (Gdx.input.isKeyPressed(Input.Keys.E)) {
            box2DDebugRenderer.render(world, camera.combined);
        }

        player.update(this);

        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }

    public SpriteBatch getSpriteBatch() {
        return this.batch;
    }

    public World getWorld() {
        return world;
    }
}
