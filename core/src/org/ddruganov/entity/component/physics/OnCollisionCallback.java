package org.ddruganov.entity.component.physics;

import org.ddruganov.entity.Entity;

public interface OnCollisionCallback {
    void invoke(Entity with);
}
