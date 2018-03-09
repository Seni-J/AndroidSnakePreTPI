package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.files.*;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Author: Senistan Jegarajasingam
 * Project Name: Android Snake Pré-TPI
 * Last update date: 06.03.2018
 *
 * Description: Make a snake game on Android. The gameplay is pretty different from the original snake.
 * The snake car move around freely without any restriction (can move in diagonal i.e.).
 * This class is the main where I call all my other classes.
 *
 * */


public class SnakeDevTest extends ApplicationAdapter {
	SpriteBatch batch;
	Stage stage;
	Texture bg;
	SnakeHead actor;


	ImageButton arrowUp;
	ImageButton arrowDown;
	ImageButton arrowLeft;
	ImageButton arrowRight;



	@Override
	public void create () {
		ScreenViewport viewport = new ScreenViewport();
		stage = new Stage(viewport);

		batch = new SpriteBatch();
		arrowUp = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("arrow_up.png"))));
		arrowDown = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("arrow_down.png"))));
		arrowLeft = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("arrow_left.png"))));
		arrowRight = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("arrow_right.png"))));

		arrowUp.setOrigin(arrowUp.getHeight()/2,arrowUp.getWidth()/2);
		arrowDown.setOrigin(arrowDown.getHeight()/2,arrowDown.getWidth()/2);
		arrowLeft.setOrigin(arrowLeft.getHeight()/2,arrowLeft.getWidth()/2);
		arrowRight.setOrigin(arrowRight.getHeight()/2,arrowRight.getWidth()/2);

		arrowUp.setPosition(150,150);
		arrowDown.setPosition(150,0);
		arrowLeft.setPosition(50,75);
		arrowRight.setPosition(250,75);


		actor = new SnakeHead();
		stage.addActor(actor);

		stage.addActor(arrowUp);
		stage.addActor(arrowDown);
		stage.addActor(arrowLeft);
		stage.addActor(arrowRight);

		Gdx.input.setInputProcessor(stage);

		bg = new Texture("grass_bg.jpg");

	}

	@Override
	public void render () {
		//Gdx.app.log("Input OrgX?", Float.toString(actor.getOriginX()));
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act(Gdx.graphics.getDeltaTime());
		stage.getBatch().begin();
		stage.getBatch().draw(bg,0,0);
		stage.getBatch().end();



		if(arrowRight.isPressed() && actor.sprite.getRotation() != 90){
			if(actor.sprite.getRotation() != -90){
				actor.sprite.setRotation(-90);
			}
			actor.setPosition(actor.getX() + 2f, actor.getY());
		}
		if(arrowLeft.isPressed() && actor.sprite.getRotation() != -90){
			if(actor.sprite.getRotation() != 90){
				actor.sprite.setRotation(90);
			}
			actor.setPosition(actor.getX() - 2f, actor.getY());
		}
		if(arrowUp.isPressed() && actor.sprite.getRotation() != -180){
			if(actor.sprite.getRotation() != 0){
				actor.sprite.setRotation(0);
			}
			actor.setPosition(actor.getX(),actor.getY() + 2f);
		}
		if(arrowDown.isPressed() && actor.sprite.getRotation() != 0){
			if(actor.sprite.getRotation() != -180){
				actor.sprite.setRotation(-180);
			}
			actor.setPosition(actor.getX(),actor.getY() - 2f);
		}

		if(!arrowUp.isPressed() && !arrowDown.isPressed() && !arrowLeft.isPressed() && !arrowRight.isPressed()){
			if(actor.sprite.getRotation() == -90){
				actor.setPosition(actor.getX() + 2f, actor.getY());
			}
			if(actor.sprite.getRotation() == -180){
				actor.setPosition(actor.getX(),actor.getY() - 2f);
			}
			if(actor.sprite.getRotation() == 0){
				actor.setPosition(actor.getX(),actor.getY() + 2f);
			}
			if(actor.sprite.getRotation() == 90){
				actor.setPosition(actor.getX() - 2f, actor.getY());
			}
		}


		stage.draw();

	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}

}
