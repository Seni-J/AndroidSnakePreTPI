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
    int targcoor = 0;
    Rectangle bounds = new Rectangle();
    float linearSpeed = 150f;
    float speedX;
    float speedY;
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
        float targetX;
        float targetY;

        if (frozen <= 0) {
            if (targcoor >= 0) { // Compute speed according to target
                targetX = SnakePlayground.coordinatesSnake.get(targcoor).x;
                targetY = SnakePlayground.coordinatesSnake.get(targcoor).y;

                Vector2 delta = new Vector2(targetX, targetY);
                Vector2 snakePartVector = new Vector2(this.getX(), this.getY());
                delta.sub(snakePartVector);
                float dt = delta.len();

                speedX = linearSpeed / dt * delta.x;
                speedY = linearSpeed / dt * delta.y;

                // Now that we know where we're going, let's see where it will put us with respect to our target
                if (quadrant(this.getX(), this.getY(), targetX, targetY) != quadrant(this.getX()+speedX * Gdx.graphics.getDeltaTime(), this.getY()+speedY * Gdx.graphics.getDeltaTime(), targetX, targetY)) // the move will put us beyond the target
                    this.NextTarget();
            }
            this.sprite.setRotation(new Vector2(speedX, speedY).angle() - 90);

            this.moveBy(speedX * Gdx.graphics.getDeltaTime(), speedY * Gdx.graphics.getDeltaTime());

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

    public void NewTarget(int i) {
        targcoor = i;
    }


    public void NextTarget() {
        if (targcoor < SnakePlayground.coordinatesSnake.size - 1) {
            targcoor++;
            NewTarget(targcoor);
        } else
            targcoor = -1; // no target
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

    public int quadrant(float x, float y, float tx, float ty) {
        if (x > tx)
            if (y > ty)
                return 1;
            else
                return 4;
        else if (y > ty)
            return 3;
        else
            return 2;
    }

}
