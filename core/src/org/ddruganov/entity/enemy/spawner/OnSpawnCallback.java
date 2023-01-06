package org.ddruganov.entity.enemy.spawner;

import com.badlogic.gdx.math.Vector2;
import org.ddruganov.Game;
import org.ddruganov.entity.Entity;

public interface OnSpawnCallback {
    Entity spawn(Game game, Vector2 position);
}
