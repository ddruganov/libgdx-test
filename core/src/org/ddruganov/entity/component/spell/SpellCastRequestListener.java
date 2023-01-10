package org.ddruganov.entity.component.spell;

import com.badlogic.gdx.math.Vector2;
import org.ddruganov.layer.GameplayLayer;

public interface SpellCastRequestListener {
    void onSpellCastRequested(GameplayLayer layer, Vector2 direction);
}
