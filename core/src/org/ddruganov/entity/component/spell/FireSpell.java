package org.ddruganov.entity.component.spell;

import com.badlogic.gdx.math.Vector2;
import org.ddruganov.Game;
import org.ddruganov.entity.Entity;
import org.ddruganov.entity.projectile.FireProjectile;

import java.util.Date;

public class FireSpell extends Spell {

    private Date lastCast;

    public FireSpell() {
        super(100, 1000);
        this.lastCast = new Date();
    }

    @Override
    public Entity cast(Game game, Entity sender, Vector2 direction) {

        Date now = new Date();

        long diff = now.getTime() - lastCast.getTime();

        if (diff < this.cooldown) {
            return null;
        }

        this.lastCast = now;

        return new FireProjectile(game, sender, direction);
    }
}
