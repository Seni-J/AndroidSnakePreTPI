package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.files.*;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sun.org.apache.xpath.internal.operations.Bool;

import javax.swing.text.View;

import static com.badlogic.gdx.math.MathUtils.cosDeg;
import static com.badlogic.gdx.math.MathUtils.sinDeg;


/**
 * Created by Senistan Jegarajasingam on 06.03.2018.
 * Description: Class for the snake's head.
 *
 */

public class SnakeHead extends Actor {

    // Variables //
    Sprite sprite = new Sprite(new Texture("snake_head.png"));
    Vector2 SnakeVector = new Vector2(sprite.getX(),sprite.getY());
    Vector2 target = new Vector2();
    Vector2 speed = new Vector2();
    // End Variables //

    public SnakeHead(){
        sprite.setOrigin(sprite.getWidth()/2,sprite.getHeight()/2);
        setBounds(sprite.getX(),sprite.getY(),sprite.getWidth(),sprite.getHeight());
        setTouchable(Touchable.enabled);
        sprite.setScale(.5f,.5f);
        sprite.rotate(-90);
    }

    // Moving Method for the snake's head.
    public void Move(float touchX, float touchY,Stage stage){
        float xrecal = this.SnakeVector.x + this.getWidth() /2;	// Set the center of the sprite.
        float yrecal = this.SnakeVector.y + this.getHeight() /2;	// Set the center of the sprite.
        float angle = 0;


        target.set(touchX - xrecal, (stage.getHeight() - touchY) - yrecal); // Set the target vector for the angle.

        angle = target.angle();	// Find the rotation angle for the sprite.
        this.sprite.setRotation(angle - 90);

        // LIBGDX WAY //
        speed.x = 1.5f * cosDeg(angle);	//Calculate Velocity for X.
        speed.y = 1.5f * sinDeg(angle);	//Calculate Velocity for Y.
    }

    public float speedX(){
        return speed.x;
    }

    public float speedY(){
        return speed.y;
    }

    @Override
    protected void positionChanged() {
        SnakeVector = new Vector2(getX(),getY());
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
