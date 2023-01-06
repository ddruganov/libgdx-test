package org.ddruganov.entity.component.spell;

import com.badlogic.gdx.math.Vector2;

public interface SpellCastRequestListener {
    void onSpellCastRequested(Vector2 direction);
}
