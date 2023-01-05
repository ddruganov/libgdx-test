package org.ddruganov.util;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public final class PhysicsBodyBuilder {

    private final World world;
    private final BodyDef bodyDef;
    private final FixtureDef fixtureDef;
    private Texture texture;

    public PhysicsBodyBuilder(World world) {
        this.world = world;
        this.bodyDef = new BodyDef();
        this.fixtureDef = new FixtureDef();
    }

    public PhysicsBodyBuilder setTexture(Texture value) {
        this.texture = value;
        return this;
    }

    public PhysicsBodyBuilder setBodyType(BodyDef.BodyType value) {
        this.bodyDef.type = value;
        return this;
    }

    public Body get() {
        Body output = world.createBody(this.bodyDef);
        this.fixtureDef.shape = new PolygonShape() {{
            setAsBox(texture.getWidth() / 2, texture.getHeight() / 2);
        }};
        this.fixtureDef.density = 0.5f;
        this.fixtureDef.restitution = 0.3f;
        output.createFixture(this.fixtureDef);

        output.setUserData(new Vector2(this.texture.getWidth(), this.texture.getHeight()));

        return output;
    }
}
