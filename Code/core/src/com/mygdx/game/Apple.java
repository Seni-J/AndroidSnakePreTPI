package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

import org.w3c.dom.css.Rect;

/**
 * Created by Senistan Jegarajasingam on 16.03.2018.
 */

public class Apple extends Actor{
    // Variables
    Sprite sprite = new Sprite(new Texture("apple.png"));
    Rectangle bounds = new Rectangle();
    int randomX;
    int randomY;

    public Apple(){
        setBounds(this.sprite.getX(),this.sprite.getY(),this.sprite.getWidth(),this.sprite.getHeight());
        randomX = MathUtils.random(0,1280);
        randomY = MathUtils.random(0,800);

        sprite.setPosition(randomX,randomY);
        sprite.setScale(.5f,.5f);

    }

    public void PlaceApple() {
            sprite.setX(MathUtils.random(0, 1280));
            sprite.setY(MathUtils.random(0, 800));
    }

    public Rectangle getBounds(){
        bounds = new Rectangle(this.sprite.getX(),this.sprite.getY(),this.sprite.getWidth(),this.sprite.getHeight());
        return bounds;
    }
    public void draw(Batch batch, float parentAlpha) {
        sprite.draw(batch);
    }

}
