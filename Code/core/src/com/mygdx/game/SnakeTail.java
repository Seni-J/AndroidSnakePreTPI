package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by Senistan Jegarajasingam on 16.03.2018.
 * Description: Class for the snake's tail.
 */

public class SnakeTail extends Actor{

    // Variables //
    Sprite sprite = new Sprite(new Texture("snake_body.png"));


    public SnakeTail(float headX,float headY){
        sprite.setScale(.5f,.5f);
        sprite.setPosition(headX -45,headY + 5);
    }

    public void TargetCoordReached(){

    }

    @Override
    protected void positionChanged() {
        sprite.setPosition(getX() -45,getY() + 5);
        super.positionChanged();
    }

    public void draw(Batch batch, float parentAlpha) {
        sprite.draw(batch);
    }

}
