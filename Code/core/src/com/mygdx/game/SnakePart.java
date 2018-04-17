package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.*;


/**
 * Created by Senistan Jegarajasingam on 06.03.2018.
 * Description: Class for the snake's head and body.
 */

public class SnakePart extends Actor {

    // Variables //
    Sprite sprite;
    Vector2 snakeVector = new Vector2();
    static int targcoor = 0;
    Rectangle bounds = new Rectangle();
    Vector2 target = new Vector2();
    Vector2 speed = new Vector2();
    float linearSpeed = 150f;
    float moduloX;
    float moduloY;
    float frozen;
    // End Variables //

    public SnakePart(String partType, float x, float y) {
        this.sprite = new Sprite(new Texture(partType + ".png"));
        setBounds(x, y, this.sprite.getWidth(), this.sprite.getHeight());
        snakeVector = new Vector2(this.sprite.getX(), this.sprite.getY());
        frozen = 50;
        sprite.setScale(.5f);
        sprite.rotate(-90);
    }

    // Moving Method for the snake's head.
    public void Move(Stage stage) {
        if (frozen <= 0) {
            this.moveBy(speed.x, speed.y);

            moduloX = this.getX() % stage.getWidth();
            moduloY = this.getY() % stage.getHeight();
            // Java keep the minus sign with modulo so we have to change it for a positive value instead of a negative value.
            if (moduloX < 0) {
                moduloX += stage.getWidth();
            }
            if (moduloY < 0) {
                moduloY += stage.getHeight();
            }
            this.setX(moduloX);
            this.setY(moduloY);
        } else {
            frozen--;
        }
    }

    public void NewTarget() {
        target.x = SnakePlayground.coordinatesSnake.get(targcoor).x;
        target.y = SnakePlayground.coordinatesSnake.get(targcoor).y;

        Vector2 delta = target.cpy();
        delta.sub(snakeVector);
        float dt = delta.len();

        speed.x = linearSpeed / dt * delta.x * Gdx.graphics.getDeltaTime();
        speed.y = linearSpeed / dt * delta.y * Gdx.graphics.getDeltaTime();

        this.sprite.setRotation(speed.angle() - 90);
    }

    public void FollowHead(float SnakeHeadX, float SnakeHeadY) {
        target.x = SnakeHeadX;
        target.y = SnakeHeadY;

        Vector2 delta = target.cpy();
        delta.sub(snakeVector);
        float dt = delta.len();

        speed.x = linearSpeed / dt * delta.x * Gdx.graphics.getDeltaTime();
        speed.y = linearSpeed / dt * delta.y * Gdx.graphics.getDeltaTime();

        this.sprite.setRotation(speed.angle() - 90);
    }

    public void NoNewTarget(float SnakeHeadX, float SnakeHeadY) {
        target.x = SnakeHeadX;
        target.y = SnakeHeadY;

        Vector2 delta = target.cpy();
        delta.sub(snakeVector);
        float dt = delta.len();

        speed.x = linearSpeed / dt * delta.x * Gdx.graphics.getDeltaTime();
        speed.y = linearSpeed / dt * delta.y * Gdx.graphics.getDeltaTime();

        this.sprite.setRotation(speed.angle() - 90);
    }

    /*
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
    */
    public boolean TargetReached() {
        Vector2 delta = target.cpy();
        delta.sub(snakeVector);
        float deltaAngle = delta.angle() - speed.angle();

        if (Math.abs(deltaAngle) < 2) {
            return false;
        } else {
            if (SnakePlayground.coordinatesSnake.get(targcoor) != SnakePlayground.coordinatesSnake.peek()) {
                targcoor += 1;
            }
            return true;
        }
    }

    public Rectangle getBounds() {
        bounds = new Rectangle(this.sprite.getX(), this.sprite.getY(), this.sprite.getWidth(), this.sprite.getHeight());
        return bounds;
    }

    @Override
    protected void positionChanged() {
        snakeVector = new Vector2(getX(), getY());
        sprite.setPosition(getX(), getY());
        super.positionChanged();
    }

    public void draw(Batch batch, float parentAlpha) {
        sprite.draw(batch);
    }

    public void act(float delta) {
        super.act(delta);
    }

}
