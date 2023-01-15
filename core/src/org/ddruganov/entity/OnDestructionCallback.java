package org.ddruganov.entity;

public interface OnDestructionCallback {
    void invoke(Entity target);
}
