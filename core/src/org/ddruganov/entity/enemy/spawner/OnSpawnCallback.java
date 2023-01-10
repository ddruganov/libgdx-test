package org.ddruganov.entity.enemy.spawner;

import com.badlogic.gdx.math.Vector2;
import org.ddruganov.entity.Entity;
import org.ddruganov.layer.GameplayLayer;

public interface OnSpawnCallback {
    Entity spawn(GameplayLayer layer, Vector2 position);
}
