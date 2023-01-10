package org.ddruganov.layer;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
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

import java.util.concurrent.CopyOnWriteArrayList;

public final class GameplayLayer extends Layer {

    private final World physicsWorld;
    private final CopyOnWriteArrayList<Entity> entities = new CopyOnWriteArrayList<>();

    public GameplayLayer(Game game, SpriteBatch spriteBatch) {
        super(game, spriteBatch, 400, 240);

        this.physicsWorld = new World(Vector2.Zero, true);
        this.physicsWorld.setContactFilter(new ContactFilter());
        this.physicsWorld.setContactListener(new ContactListener());

        this.addEntity(new Player(this));
        this.addEntity(new Spawner(this, new Vector2(50f, 50f), 10f, Zombie::new, "zombie.png"));
        this.addEntity(new Wall(this, new Vector2(-50f, 50f), 16, 16));
        this.addEntity(new Wall(this, new Vector2(50f, -50f), 32, 32));
        this.addEntity(new Wall(this, new Vector2(-50f, -50f), 8, 8));
    }

    public World getPhysicsWorld() {
        return this.physicsWorld;
    }

    public void addEntity(Entity value) {
        this.entities.add(value);
    }

    public void removeEntity(Entity value) {
        this.entities.remove(value);
    }

    @Override
    protected void _update() {
        this.physicsWorld.step(1 / 60f, 6, 2);

        this.entities.forEach((e) -> e.update(this));
    }

    @Override
    public void update() {

        Player player = this.getPlayer();
        if (player != null) {
            this.getCamera().position.set(player.getComponent(PhysicsComponent.class).getPosition(), 0);
        }

        super.update();
    }

    public Player getPlayer() {
        Entity firstEntity = this.entities.get(0);

        if (firstEntity instanceof Player) {
            return (Player) firstEntity;
        }

        return null;
    }
}
