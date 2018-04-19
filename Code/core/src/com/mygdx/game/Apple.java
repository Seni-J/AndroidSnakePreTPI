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
 * Last update date: 19.04.2018
 *
 * Description:
 * Create randomly where the apple should appear. Bounds for the apple are sets there.
 */

public class Apple extends Actor{
    // Variables
    Sprite sprite = new Sprite(new Texture("apple.png"));
    Rectangle bounds = new Rectangle();
    int randomX;
    int randomY;

    public Apple(){
        randomX = MathUtils.random(0,1150);
        randomY = MathUtils.random(0,700);
        sprite.setPosition(randomX,randomY);
        sprite.setScale(.5f,.5f);
        setBounds(this.sprite.getX(),this.sprite.getY(),this.sprite.getWidth(),this.sprite.getHeight());
    }

    public void PlaceApple() {
            sprite.setX(MathUtils.random(0, 1150));
            sprite.setY(MathUtils.random(0, 700));
    }

    public Rectangle getBounds(){
        bounds = new Rectangle(this.sprite.getX()+16,this.sprite.getY()+15,this.sprite.getWidth()/2,this.sprite.getHeight()/2);
        return bounds;
    }
    public void draw(Batch batch, float parentAlpha) {
        sprite.draw(batch);
    }

}
