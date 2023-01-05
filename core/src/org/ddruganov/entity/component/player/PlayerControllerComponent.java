package org.ddruganov.entity.component.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector2;
import org.ddruganov.Game;
import org.ddruganov.entity.component.EntityComponent;
import org.ddruganov.entity.component.physics.VelocityProvider;

public class PlayerControllerComponent implements EntityComponent, VelocityProvider {

    private static final int SPEED = 200;
    private final Vector2 velocity = Vector2.Zero;

    public PlayerControllerComponent() {
        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean keyDown(int keycode) {
                if (keycode == Input.Keys.UP) {
                    velocity.y = SPEED;
                } else if (keycode == Input.Keys.DOWN) {
                    velocity.y = -SPEED;
                }
                if (keycode == Input.Keys.LEFT) {
                    velocity.x = -SPEED;
                } else if (keycode == Input.Keys.RIGHT) {
                    velocity.x = SPEED;
                }
                return true;
            }

            @Override
            public boolean keyUp(int keycode) {
                if (keycode == Input.Keys.UP) {
                    velocity.y = 0;
                } else if (keycode == Input.Keys.DOWN) {
                    velocity.y = 0;
                }
                if (keycode == Input.Keys.LEFT) {
                    velocity.x = 0;
                } else if (keycode == Input.Keys.RIGHT) {
                    velocity.x = 0;
                }
                return true;
            }
        });
    }

    @Override
    public void update(Game game) {
    }

    @Override
    public Vector2 getVelocity() {
        return this.velocity;
    }
}
