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
import com.badlogic.gdx.utils.viewport.ScreenViewport;


/**
 * Created by Senistan Jegarajasingam on 06.03.2018.
 * Description: Class for the snake's head.
 *
 */

public class SnakeHead extends Actor {

    // Variables //

    Sprite sprite = new Sprite(new Texture("snake_head.png"));
    Vector2 SnakeVector = new Vector2(sprite.getX(),sprite.getY());


    // End Variables //

    public SnakeHead(){
        setBounds(sprite.getX(),sprite.getY(),sprite.getWidth(),sprite.getHeight());
        sprite.setScale(.5f,.5f);
        sprite.rotate(-90);


    }


    public void draw(Batch batch, float parentAlpha){
        sprite.draw(batch);
        sprite.setPosition(sprite.getX() + 1f,sprite.getY());


    }

    public void act(float delta){
        super.act(delta);
    }

}
