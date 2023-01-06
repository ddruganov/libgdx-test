package org.ddruganov.entity.component.spell;

import com.badlogic.gdx.math.Vector2;
import org.ddruganov.entity.Entity;
import org.ddruganov.entity.component.EntityComponent;

public class SpellCasterComponent extends EntityComponent implements SpellCastRequestListener {

    public SpellCasterComponent(Entity entity) {
        super(entity);
    }

    @Override
    public void onSpellCastRequested(Vector2 direction) {
        System.out.println("gonna cast a spell @ " + direction.toString());
    }
}
