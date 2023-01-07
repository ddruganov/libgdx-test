package org.ddruganov.physics;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

public class Transform {
    private final Vector2 position;
    private final float rotation;

    public Transform(Vector2 position, float rotation) {
        this.position = position;
        this.rotation = rotation;
    }

    public static Transform fromBox2D(Body body) {
        return new Transform(body.getPosition().cpy(), body.getAngle());
    }

    public Vector2 getPosition() {
        return position;
    }

    public float getRotation() {
        return rotation;
    }
}
