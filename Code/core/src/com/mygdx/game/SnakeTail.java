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
    Vector2 targetTail = new Vector2(sprite.getX(),sprite.getY());
    Vector2 speed = new Vector2();
    Vector2 tailVector = new Vector2();


    public SnakeTail(float headX,float headY){
        sprite.setOrigin(sprite.getWidth()/2,sprite.getHeight()/2);
        setBounds(sprite.getX(),sprite.getY(),sprite.getWidth(),sprite.getHeight());
        sprite.setScale(.5f,.5f);
        sprite.setPosition(headX -45,headY + 5);
    }

    public void TailMove(float coordX,float coordY){
        targetTail.x = coordX;
        targetTail.y = coordY;


    }

    public boolean TargetCoordReached(){
        Vector2 delta = targetTail.cpy();
        delta.sub(tailVector);
        float deltaAngle = delta.angle() - speed.angle();

        if(Math.abs(deltaAngle) < 2) {
            return false;
        }else{
            return true;
        }
    }

    @Override
    protected void positionChanged() {
        sprite.setPosition(getX() - 45,getY() + 5);
        super.positionChanged();
    }

    public void draw(Batch batch, float parentAlpha) {
        sprite.draw(batch);
    }

}
