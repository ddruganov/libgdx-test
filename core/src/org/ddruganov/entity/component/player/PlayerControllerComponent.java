package org.ddruganov.entity.component.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import org.ddruganov.Game;
import org.ddruganov.entity.component.EntityComponent;
import org.ddruganov.entity.component.physics.VelocityProvider;

public class PlayerControllerComponent implements EntityComponent, VelocityProvider {

    private static final int SPEED = 200;
    private final Vector2 velocity = Vector2.Zero;

    @Override
    public void update(Game game) {

        Vector2 newVelocity = Vector2.Zero;

        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            newVelocity.set(newVelocity.x, 1);
        } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            newVelocity.set(newVelocity.x, -1);
        } else {
            newVelocity.set(newVelocity.x, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            newVelocity.set(1, newVelocity.y);
        } else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            newVelocity.set(-1, newVelocity.y);
        } else {
            newVelocity.set(0, newVelocity.y);
        }

        this.velocity.set(newVelocity.scl(SPEED));
    }

    @Override
    public Vector2 getVelocity() {
        return this.velocity;
    }
}
