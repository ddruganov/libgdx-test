package org.ddruganov.entity.component.spell;

import com.badlogic.gdx.math.Vector2;
import org.ddruganov.Game;
import org.ddruganov.entity.Entity;
import org.ddruganov.entity.projectile.ProjectileBuilder;
import org.ddruganov.render.Renderable;

import java.util.Date;

public abstract class Spell {

    private Date lastCast = new Date();

    public Entity cast(Game game, Entity sender, Vector2 direction) {

        Date now = new Date();

        long diff = now.getTime() - lastCast.getTime();

        if (diff < this.getCooldown()) {
            return null;
        }

        this.lastCast = now;

        return new ProjectileBuilder(game)
                .setSender(sender)
                .setDirection(direction)
                .setBaseDamage(this.getBaseDamage())
                .setRenderable(this.getRenderable())
                .buildProjectile();
    }

    protected abstract int getCooldown();

    protected abstract float getBaseDamage();

    protected abstract Renderable getRenderable();
}
