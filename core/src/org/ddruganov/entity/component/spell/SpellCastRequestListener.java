package org.ddruganov.entity.component.spell;

import com.badlogic.gdx.math.Vector2;
import org.ddruganov.Game;

public interface SpellCastRequestListener {
    void onSpellCastRequested(Game game, Vector2 direction);
}
