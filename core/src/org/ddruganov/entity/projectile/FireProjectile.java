package org.ddruganov.entity.projectile;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import org.ddruganov.Game;
import org.ddruganov.entity.Entity;
import org.ddruganov.entity.component.physics.PhysicsComponent;
import org.ddruganov.render.SpriteStack;

public class FireProjectile extends Projectile {

    public FireProjectile(Game game, Entity sender, Vector2 direction) {
        super(game,
                sender,
                new SpriteStack(new Sprite[]{
                        new Sprite(new Texture(Gdx.files.internal("bullet.png")))
                }),
                sender.getComponent(PhysicsComponent.class).getPosition(),
                direction);
    }
}
