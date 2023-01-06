package org.ddruganov.entity.enemy.spawner;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import org.ddruganov.Game;
import org.ddruganov.entity.Entity;
import org.ddruganov.entity.component.physics.PhysicsComponent;
import org.ddruganov.entity.enemy.Zombie;
import org.ddruganov.util.PhysicsBodyBuilder;

public class ZombieSpawner extends Entity {

    private final float nextSpawn = 10.0f;
    private float nextSpawnLeft = nextSpawn;

    public ZombieSpawner(Game game) {
        PhysicsComponent physicsComponent = new PhysicsComponent(
                new PhysicsBodyBuilder(game.getWorld())
                        .setBodyType(BodyDef.BodyType.StaticBody)
                        .setShape(new CircleShape() {{
                            setRadius(1);
                        }})
                        .setPosition(new Vector2(100, 100))
                        .get(),
                null,
                null);
        addComponent(physicsComponent);
    }

    @Override
    public void update(Game game) {

        nextSpawnLeft -= Gdx.graphics.getDeltaTime();
        if (nextSpawnLeft > 0) {
            return;
        }

        Vector2 spawnPos = this.getComponent(PhysicsComponent.class).getPosition().cpy().add(
                new Vector2(1, 1).scl(((float) Math.random() * 2 - 1) * 50f, ((float) Math.random() * 2 - 1) * 50f)
        );

        game.addEntity(new Zombie(game, spawnPos));

        nextSpawnLeft = nextSpawn;
    }
}
