package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by Senistan Jegarajasingam on 16.03.2018.
 */

public class Apple extends Actor{
    // Variables
    Sprite sprite = new Sprite(new Texture("apple.png"));
    int randomX;
    int randomY;

    public Apple(){
        randomX = MathUtils.random(0,1280);
        randomY = MathUtils.random(0,800);

        sprite.setPosition(randomX,randomY);
        sprite.setScale(.5f,.5f);

    }

    public void draw(Batch batch, float parentAlpha) {
        sprite.draw(batch);
    }

}
