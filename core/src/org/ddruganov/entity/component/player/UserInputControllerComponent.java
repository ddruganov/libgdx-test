package org.ddruganov.entity.component.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import org.ddruganov.entity.Entity;
import org.ddruganov.entity.component.EntityComponent;
import org.ddruganov.entity.component.physics.PhysicsComponent;
import org.ddruganov.entity.component.physics.VelocityProvider;
import org.ddruganov.entity.component.spell.SpellCastRequestListener;
import org.ddruganov.layer.GameplayLayer;

public class UserInputControllerComponent extends EntityComponent implements VelocityProvider {

    private static final int SPEED = 10;
    private final Vector2 velocity = Vector2.Zero;
    private final SpellCastRequestListener spellCastRequestListener;

    public UserInputControllerComponent(Entity entity, SpellCastRequestListener spellCastRequestListener) {
        super(entity);
        this.spellCastRequestListener = spellCastRequestListener;
    }

    @Override
    public void update(GameplayLayer layer) {

        Vector2 newVelocity = Vector2.Zero;

        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            newVelocity.set(newVelocity.x, 1);
        } else if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            newVelocity.set(newVelocity.x, -1);
        } else {
            newVelocity.set(newVelocity.x, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            newVelocity.set(1, newVelocity.y);
        } else if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            newVelocity.set(-1, newVelocity.y);
        } else {
            newVelocity.set(0, newVelocity.y);
        }

        this.velocity.set(newVelocity.scl(SPEED));

        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            Vector3 unprojectedMousePos = layer.getCamera().unproject(new Vector3(
                    Gdx.input.getX(),
                    Gdx.input.getY(),
                    0
            ));
            Vector2 mousePos = new Vector2(unprojectedMousePos.x, unprojectedMousePos.y);
            Vector2 direction = mousePos.sub(this.entity.getComponent(PhysicsComponent.class).getPosition()).nor();
            this.spellCastRequestListener.onSpellCastRequested(layer, direction);
        }
    }

    @Override
    public Vector2 getVelocity() {
        return this.velocity;
    }
}
