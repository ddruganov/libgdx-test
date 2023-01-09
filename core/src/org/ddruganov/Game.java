package org.ddruganov;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.ScreenUtils;
import org.ddruganov.entity.Entity;
import org.ddruganov.entity.component.physics.PhysicsComponent;
import org.ddruganov.entity.enemy.Zombie;
import org.ddruganov.entity.enemy.spawner.Spawner;
import org.ddruganov.entity.player.Player;
import org.ddruganov.entity.stationary.Wall;
import org.ddruganov.physics.ContactFilter;
import org.ddruganov.physics.ContactListener;

import java.util.concurrent.CopyOnWriteArrayList;

public class Game extends ApplicationAdapter {

    private final CopyOnWriteArrayList<Entity> entities = new CopyOnWriteArrayList<>();
    private World world;
    private Box2DDebugRenderer box2DDebugRenderer;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Player player;

    public Camera getCamera() {
        return camera;
    }

    @Override
    public void create() {

        world = new World(Vector2.Zero, true);
        world.setContactFilter(new ContactFilter());
        world.setContactListener(new ContactListener());
        box2DDebugRenderer = new Box2DDebugRenderer();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 400, 240);
        batch = new SpriteBatch();
        player = new Player(this);
        this.addEntity(new Spawner(this, new Vector2(50f, 50f), 10f, Zombie::new, "zombie.png"));
        this.addEntity(new Wall(this, new Vector2(-50f, 50f), 16, 16));
        this.addEntity(new Wall(this, new Vector2(50f, -50f), 32, 32));
        this.addEntity(new Wall(this, new Vector2(-50f, -50f), 8, 8));
    }

    public Player getPlayer() {
        return player;
    }

    @Override
    public void render() {

        world.step(1 / 60f, 6, 2);

        ScreenUtils.clear(1, 1, 1, 1);

        camera.position.set(player.getComponent(PhysicsComponent.class).getPosition(), 0);
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();

        if (Gdx.input.isKeyPressed(Input.Keys.E)) {
            box2DDebugRenderer.render(world, camera.combined);
        }

        player.update(this);
        this.entities.forEach((e) -> e.update(this));

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

    public void addEntity(Entity value) {
        this.entities.add(value);
    }

    public void removeEntity(Entity value) {
        this.entities.remove(value);
    }
}
