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


/**
 * Created by Senistan Jegarajasingam on 06.03.2018.
 * Description: Class for the snake's head.
 *
 */

public class SnakeHead extends Actor {

    // Variables //

    Sprite sprite = new Sprite(new Texture("snake_head.png"));
    Vector2 SnakeVector = new Vector2(sprite.getX(),sprite.getY());
    Viewport viewport = new ScreenViewport();

    // End Variables //

    public SnakeHead(){
        sprite.setOrigin(sprite.getWidth()/2,sprite.getHeight()/2);
        setBounds(sprite.getX(),sprite.getY(),sprite.getWidth(),sprite.getHeight());
        setTouchable(Touchable.enabled);
        sprite.setScale(.5f,.5f);
        sprite.rotate(-90);
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
