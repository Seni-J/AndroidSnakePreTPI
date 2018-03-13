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
import com.sun.org.apache.xpath.internal.operations.Bool;


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
        this.setOrigin(sprite.getWidth()/2,sprite.getHeight()/2);
        setBounds(sprite.getX(),sprite.getY(),sprite.getWidth(),sprite.getHeight());
        setTouchable(Touchable.enabled);
        sprite.setScale(.5f,.5f);
        sprite.rotate(-90);


/*
        Gdx.app.log("test X?", Float.toString(TouchPos.x));

        addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

                SnakeHead.this.clearActions();
                if (x < sprite.getX()) {
                    MoveByAction mba = new MoveByAction();
                    sprite.rotate(-180);
                    mba.setAmount(-500f,0f);
                    mba.setDuration(10f);
                    SnakeHead.this.addAction(mba);
                }
                else if(x > sprite.getX()){
                    MoveByAction mba = new MoveByAction();
                    sprite.rotate(-180);
                    mba.setAmount(500f,0f);
                    mba.setDuration(10f);
                    SnakeHead.this.addAction(mba);
                }

                return true;
            }
        });

*/
    }

    @Override
    protected void positionChanged() {
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
