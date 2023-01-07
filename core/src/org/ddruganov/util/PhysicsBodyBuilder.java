package org.ddruganov.util;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import org.ddruganov.render.Renderable;

public final class PhysicsBodyBuilder {

    private final World world;
    private final BodyDef bodyDef;
    private final FixtureDef fixtureDef;

    public PhysicsBodyBuilder(World world) {
        this.world = world;
        this.bodyDef = new BodyDef();
        this.fixtureDef = new FixtureDef();
    }

    public PhysicsBodyBuilder setRenderable(Renderable value) {
        this.setShape(new PolygonShape() {{
            setAsBox((float) value.getWidth() / 2, (float) value.getHeight() / 2);
        }});
        return this;
    }

    public PhysicsBodyBuilder setBodyType(BodyDef.BodyType value) {
        this.bodyDef.type = value;
        return this;
    }

    public Body createBody() {
        Body output = world.createBody(this.bodyDef);
        output.createFixture(this.fixtureDef);
        output.setLinearDamping(5f);

        return output;
    }

    public PhysicsBodyBuilder setPosition(Vector2 value) {
        this.bodyDef.position.set(value);
        return this;
    }

    public PhysicsBodyBuilder setShape(Shape value) {
        this.fixtureDef.shape = value;
        return this;
    }

    public PhysicsBodyBuilder setAngle(float value) {
        this.bodyDef.angle = value;
        return this;
    }
}
