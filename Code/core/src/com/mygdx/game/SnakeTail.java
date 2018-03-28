package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 * Created by Senistan Jegarajasingam on 16.03.2018.
 * Description: Class for the snake's tail.
 */

public class SnakeTail extends Actor{

    // Variables //
    Sprite sprite = new Sprite(new Texture("snake_body.png"));
    Vector2 targetTail = new Vector2();
    Vector2 speed = new Vector2();
    Vector2 tailVector = new Vector2(sprite.getX(),sprite.getY());
    float linearSpeed = 150f;
    float moduloX;
    float moduloY;

    public SnakeTail(float headX,float headY){
        sprite.setOrigin(sprite.getWidth()/2,sprite.getHeight()/2);
        setBounds(sprite.getX(),sprite.getY(),sprite.getWidth(),sprite.getHeight());
        sprite.setScale(.5f,.5f);
        this.setX(headX);
        this.setY(headY);
    }

    public void MoveTail(Stage stage){
        this.moveBy(speed.x,speed.y);

        moduloX = this.getX() % stage.getWidth();
        moduloY = this.getY() % stage.getHeight();
        // Java keep the minus sign with modulo so we have to change it for a positive value instead of a negative value.
        if (moduloX < 0)
        {
            moduloX += stage.getWidth();
        }
        if (moduloY < 0)
        {
            moduloY += stage.getHeight();
        }
        this.setX(moduloX);
        this.setY(moduloY);
    }

    public void TailTarget(float coordX, float coordY){
        targetTail.x = coordX;
        targetTail.y = coordY;

        Vector2 delta = targetTail.cpy();
        delta.sub(tailVector);
        float dt = delta.len();

        speed.x = linearSpeed /dt * delta.x  * Gdx.graphics.getDeltaTime();
        speed.y = linearSpeed /dt * delta.y  * Gdx.graphics.getDeltaTime();

        this.sprite.setRotation(speed.angle()-90);

        Gdx.app.log("New target Tail:", Float.toString(targetTail.x) + ","+ Float.toString(targetTail.y));

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
        tailVector = new Vector2((int) getX(),(int) getY());
        sprite.setPosition(getX(),getY());
        super.positionChanged();
    }

    public void draw(Batch batch, float parentAlpha) {
        sprite.draw(batch);
    }

}
