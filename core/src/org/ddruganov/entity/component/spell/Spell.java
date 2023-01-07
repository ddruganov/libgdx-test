package org.ddruganov.entity.component.spell;

import com.badlogic.gdx.math.Vector2;
import org.ddruganov.Game;
import org.ddruganov.entity.Entity;

public abstract class Spell extends Entity {

    protected final int damage;
    protected final int cooldown;

    protected Spell(int damage, int cooldown) {
        this.damage = damage;
        this.cooldown = cooldown;
    }

    public abstract Entity cast(Game game, Entity sender, Vector2 direction);
}
