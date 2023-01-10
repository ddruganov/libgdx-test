package org.ddruganov.entity.component.spell;

import com.badlogic.gdx.math.Vector2;
import org.ddruganov.entity.Entity;
import org.ddruganov.entity.component.EntityComponent;
import org.ddruganov.layer.GameplayLayer;

public class SpellCasterComponent extends EntityComponent implements SpellCastRequestListener {

    private final Spell currentSpell;

    public SpellCasterComponent(Entity entity) {
        super(entity);

        this.currentSpell = new FireSpell();
    }

    @Override
    public void onSpellCastRequested(GameplayLayer layer, Vector2 direction) {
        Entity casted = this.currentSpell.cast(layer, this.entity, direction);
        if (casted == null) {
            return;
        }

        layer.addEntity(casted);
    }
}
