package org.ddruganov.entity.component.physics;

import com.badlogic.gdx.math.Vector2;
import org.ddruganov.entity.Entity;

public interface OnCollisionCallback {
    void invoke(Entity with, Vector2 at);
}
