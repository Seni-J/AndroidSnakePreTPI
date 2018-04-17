package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.*;


/**
 * Created by Senistan Jegarajasingam on 06.03.2018.
 * Description: Class for the snake's head and body.
 *
 */

public class SnakePart extends Actor {

    // Variables //
    Sprite sprite;
    Vector2 snakeVector = new Vector2();
    int targcoor = 0;
    Vector2 target = new Vector2();
    Vector2 speed = new Vector2();
    Vector2 targetHead = new Vector2();
    Vector2 speedHead = new Vector2();
    float linearSpeed = 150f;
    float moduloX;
    float moduloY;
    // End Variables //

    public SnakePart(Sprite sprite){
        this.sprite = sprite;
        snakeVector = new Vector2(this.sprite.getX(),this.sprite.getY());
        sprite.setScale(.5f);
        sprite.rotate(-90);
        /*this.setX(620);
        this.setY(400);*/
        /*snakeHead.setOrigin(snakeHead.getWidth()/2, snakeHead.getHeight()/2);
        setBounds(snakeHead.getX(), snakeHead.getY(), snakeHead.getWidth(), snakeHead.getHeight());

        snakeHead.setScale(.5f,.5f);
        snakeHead.rotate(-90);
        NewTarget(snakeVector.x + 100, snakeVector.y);*/
    }

    // Moving Method for the snake's head.
    public void Move(Stage stage){
        this.moveBy(speed.x, speed.y);


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

    public void MoveHead(Stage stage){
        this.moveBy(speedHead.x, speedHead.y);

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

    public void NewTarget(){
        target.x = SnakePlayground.coordinatesSnake.get(targcoor).x;
        target.y = SnakePlayground.coordinatesSnake.get(targcoor).y;

        Vector2 delta = target.cpy();
        delta.sub(snakeVector);
        float dt = delta.len();

        speed.x = linearSpeed /dt * delta.x  * Gdx.graphics.getDeltaTime();
        speed.y = linearSpeed /dt * delta.y  * Gdx.graphics.getDeltaTime();

        this.sprite.setRotation(speed.angle()-90);

        //Gdx.app.log("New target Head:", Float.toString(target.x) + ","+ Float.toString(target.y));
    }

    public void NewTargetHead(float TouchX, float TouchY){
        targetHead.x = TouchX;
        targetHead.y = TouchY;

        Vector2 deltaHead = targetHead.cpy();
        deltaHead.sub(snakeVector);
        float dt = deltaHead.len();

        speedHead.x = linearSpeed /dt * deltaHead.x  * Gdx.graphics.getDeltaTime();
        speedHead.y = linearSpeed /dt * deltaHead.y  * Gdx.graphics.getDeltaTime();

        this.sprite.setRotation(speedHead.angle()-90);

        //Gdx.app.log("New target Head:", Float.toString(targetHead.x) + ","+ Float.toString(targetHead.y));
    }

    public boolean TargetReachedHead(){
        Vector2 deltaHead = targetHead.cpy();
        deltaHead.sub(snakeVector);
        float deltaAngle = deltaHead.angle() - speedHead.angle();

        if(Math.abs(deltaAngle) < 2) {
            return false;
        }else{
            return true;
        }
    }

    public boolean TargetReached(){
        Vector2 delta = target.cpy();
        delta.sub(snakeVector);
        float deltaAngle = delta.angle() - speed.angle();

        if(Math.abs(deltaAngle) < 2) {
            return false;
        }else{
            if(SnakePlayground.coordinatesSnake.get(targcoor) != SnakePlayground.coordinatesSnake.peek()){
                targcoor += 1;
            }else{
                speed.x = speedHead.x;
                speed.y = speedHead.y;
            }
            return true;
        }
    }

    @Override
    protected void positionChanged() {
        snakeVector = new Vector2(getX(), getY());
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
