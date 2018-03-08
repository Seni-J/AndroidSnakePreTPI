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
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
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



	@Override
	public void create () {
		ScreenViewport viewport = new ScreenViewport();
		stage = new Stage(viewport);


		actor = new SnakeHead();
		stage.addActor(actor);

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


		BitmapFont font = new BitmapFont();



		if(Gdx.input.getX() > actor.getX()){
			actor.setPosition(actor.getX() + 1f, actor.getY());
		}
		if(Gdx.input.getX() < actor.getX()){
			actor.setPosition(actor.getX() - 1f, actor.getY());
		}
		if(Gdx.input.getY() > actor.getY() && Gdx.input.getX() == actor.getX()){
			actor.setPosition(actor.getX(),actor.getY() + 1f);
		}
		if(Gdx.input.getY() < actor.getY() && Gdx.input.getX() == actor.getX()){
			actor.setPosition(actor.getX(),actor.getY() - 1f);
		}


		stage.draw();

	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}

}
