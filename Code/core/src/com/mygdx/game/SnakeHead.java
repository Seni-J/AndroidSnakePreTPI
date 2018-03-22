package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.*;


/**
 * Created by Senistan Jegarajasingam on 06.03.2018.
 * Description: Class for the snake's head.
 *
 */

public class SnakeHead extends Actor {

    // Variables //
    Sprite sprite = new Sprite(new Texture("snake_head.png"));
    Vector2 snakeVector = new Vector2(sprite.getX(),sprite.getY());
    Vector2 target = new Vector2();
    Vector2 speed = new Vector2();
    float linearSpeed = 150f;
    float moduloX;
    float moduloY;
    // End Variables //

    public SnakeHead(){
        sprite.setOrigin(sprite.getWidth()/2,sprite.getHeight()/2);
        setBounds(sprite.getX(),sprite.getY(),sprite.getWidth(),sprite.getHeight());
        this.setX(200);
        this.setY(200);
        sprite.setScale(.5f,.5f);
        sprite.rotate(-90);
        NewTarget(snakeVector.x + 100, snakeVector.y);
    }

    // Moving Method for the snake's head.
    public void Move(Stage stage){
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

    public void NewTarget(float X, float Y){
        target.x = X;
        target.y = Y;

        Vector2 delta = target.cpy();
        delta.sub(snakeVector);
        float dt = delta.len();

        speed.x = linearSpeed /dt * delta.x * Gdx.graphics.getDeltaTime();
        speed.y = linearSpeed /dt * delta.y * Gdx.graphics.getDeltaTime();

        this.sprite.setRotation(speed.angle()-90);

        Gdx.app.log("New target:", Float.toString(target.x) + ","+ Float.toString(target.y));
    }

    public boolean TargetReached(){
        Vector2 delta = target.cpy();
        delta.sub(snakeVector);
        float deltaAngle = delta.angle() - speed.angle();

        if(Math.abs(deltaAngle) < 2) {
            return false;
        }else{
            return true;
        }
    }

    @Override
    protected void positionChanged() {
        snakeVector = new Vector2(getX(),getY());
        sprite.setPosition(getX(),getY());
        super.positionChanged();
    }

    public void draw(Batch batch, float parentAlpha) {
        sprite.draw(batch);
    }

    public void act(float delta){
        super.act(delta);
    }

}
