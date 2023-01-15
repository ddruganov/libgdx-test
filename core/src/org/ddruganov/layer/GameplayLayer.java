package org.ddruganov.layer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import org.ddruganov.Game;
import org.ddruganov.entity.Entity;
import org.ddruganov.entity.component.physics.PhysicsComponent;
import org.ddruganov.entity.enemy.Zombie;
import org.ddruganov.entity.enemy.spawner.Spawner;
import org.ddruganov.entity.player.Player;
import org.ddruganov.entity.stationary.Wall;
import org.ddruganov.physics.ContactFilter;
import org.ddruganov.physics.ContactListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public final class GameplayLayer extends Layer {

    private final World physicsWorld;
    private final Box2DDebugRenderer physicsDebugRenderer;
    private final HashMap<UUID, Entity> entities = new HashMap<>();

    private final ArrayList<Entity> newEntities = new ArrayList<>();
    private final ArrayList<UUID> deletedEntities = new ArrayList<>();

    private final Player player;

    public GameplayLayer(Game game, SpriteBatch spriteBatch) {
        super(game, spriteBatch, 400, 240);

        this.physicsWorld = new World(Vector2.Zero, true);
        this.physicsWorld.setContactFilter(new ContactFilter());
        this.physicsWorld.setContactListener(new ContactListener());
        this.physicsDebugRenderer = new Box2DDebugRenderer(true, true, true, true, true, true);

        this.player = new Player(this);
        this.addEntity(player);
        this.addEntity(new Spawner(this, new Vector2(50f, 50f), 10f, Zombie::new, "zombie.png"));
        this.addEntity(new Wall(this, new Vector2(-50f, 50f), 16, 16));
        this.addEntity(new Wall(this, new Vector2(50f, -50f), 32, 32));
        this.addEntity(new Wall(this, new Vector2(-50f, -50f), 8, 8));
    }

    public World getPhysicsWorld() {
        return this.physicsWorld;
    }

    public void addEntity(Entity value) {
        this.newEntities.add(value);
    }

    public void removeEntity(Entity value) {
        this.deletedEntities.add(value.getUuid());
    }

    @Override
    protected void _update() {

        this.physicsWorld.step(1 / 60f, 6, 2);

        if (!this.newEntities.isEmpty()) {
            this.newEntities.forEach(e -> this.entities.put(e.getUuid(), e));
            this.newEntities.clear();
        }

        if (!this.deletedEntities.isEmpty()) {
            this.deletedEntities.forEach(this.entities::remove);
            this.deletedEntities.clear();
        }

        this.entities.forEach((uuid, e) -> e.update(this));

        if (Gdx.input.isKeyPressed(Input.Keys.E)) {
            this.physicsDebugRenderer.render(this.physicsWorld, this.getCamera().combined);
        }
    }

    @Override
    public void update() {

        if (this.player != null) {
            this.getCamera().position.set(this.player.getComponent(PhysicsComponent.class).getPosition(), 0);
        }

        super.update();
    }

    public Player getPlayer() {
        return this.player;
    }
}
