package org.ddruganov.entity.component.spell;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import org.ddruganov.render.Renderable;
import org.ddruganov.render.SpriteStack;

public class FireSpell extends Spell {

    @Override
    protected int getCooldown() {
        return 250;
    }

    @Override
    protected float getBaseDamage() {
        return 5;
    }

    @Override
    protected Renderable getRenderable() {
        return new SpriteStack(
                new Sprite(new Texture(Gdx.files.internal("fireball.png")))
        );
    }
}
