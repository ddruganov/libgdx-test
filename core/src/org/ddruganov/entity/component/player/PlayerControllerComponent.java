package org.ddruganov.entity.component.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import org.ddruganov.Game;
import org.ddruganov.entity.component.EntityComponent;
import org.ddruganov.entity.component.physics.VelocityProvider;

public class PlayerControllerComponent implements EntityComponent, VelocityProvider {

    private static final int SPEED = 200;
    private Vector2 velocity = Vector2.Zero;

    @Override
    public void update(Game game) {

        this.velocity = Vector2.Zero;

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            this.velocity.x = -SPEED;
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            this.velocity.x = SPEED;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            this.velocity.y = SPEED;
        } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            this.velocity.y = -SPEED;
        }
    }

    @Override
    public Vector2 getVelocity() {
        return this.velocity;
    }
}
