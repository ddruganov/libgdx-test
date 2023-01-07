package org.ddruganov.entity.component.spell;

import com.badlogic.gdx.math.Vector2;
import org.ddruganov.Game;
import org.ddruganov.entity.Entity;
import org.ddruganov.entity.component.EntityComponent;

public class SpellCasterComponent extends EntityComponent implements SpellCastRequestListener {

    private final Spell currentSpell;

    public SpellCasterComponent(Entity entity) {
        super(entity);

        this.currentSpell = new FireSpell();
    }

    @Override
    public void onSpellCastRequested(Game game, Vector2 direction) {
        Entity casted = this.currentSpell.cast(game, this.entity, direction);
        if (casted == null) {
            return;
        }

        game.addEntity(casted);
    }
}
