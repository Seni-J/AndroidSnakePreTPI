package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.files.*;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.math.MathUtils;

import static com.badlogic.gdx.math.MathUtils.cosDeg;
import static com.badlogic.gdx.math.MathUtils.sinDeg;
import static java.lang.Math.abs;
import static java.lang.Math.asin;
import static java.lang.Math.cos;
import static java.lang.Math.sin;

/**
 * Author: Senistan Jegarajasingam
 * Project Name: Android Snake Pré-TPI
 * Last update date: 16.03.2018
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
	Apple actorApple;
	Array positions;

	Vector2 TouchPos;
	Vector2 target;
	Vector2 speed;

	ShapeRenderer shapeRenderer;
	ScreenViewport viewport;



	@Override
	public void create () {
		viewport = new ScreenViewport();
		shapeRenderer = new ShapeRenderer();
		stage = new Stage(viewport);

		batch = new SpriteBatch();
		TouchPos = new Vector2(Gdx.input.getX(),Gdx.input.getY());
		target = new Vector2();
		speed = new Vector2();
		positions = new Array();


		actor = new SnakeHead();
		actorApple = new Apple();
		stage.addActor(actorApple);
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




		if(Gdx.input.isTouched()){
			TouchPos = new Vector2(Gdx.input.getX(),Gdx.input.getY());
			//positions.add(TouchPos.x,TouchPos.y); // Array to add positions.

			float xrecal = actor.SnakeVector.x + actor.getWidth() /2;	// Set the center of the sprite.
			float yrecal = actor.SnakeVector.y + actor.getHeight() /2;	// Set the center of the sprite.
			float angle = 0;


			target.set(TouchPos.x - xrecal, (stage.getHeight() - TouchPos.y) - yrecal); // Set the target vector for the angle.

			angle = target.angle();	// Find the rotation angle for the sprite.
			actor.sprite.setRotation(angle - 90);

			// LIBGDX WAY //
			speed.x = 1f * cosDeg(angle);	//Calculate Velocity for X.
			speed.y = 1f * sinDeg(angle);	//Calculate Velocity for Y.


			// JAVA WAY //
			/*
			speed.x = 1f * (float) cos(angle);	//Calculate Velocity for X.
			speed.y = 1f * (float) sin(angle);	//Calculate Velocity for Y.
			*/
			actor.moveBy(speed.x,speed.y);	// If the user still have his finger on the screen, the snake will still be moving.

			/* // DRAWING LINE //
			batch.begin();
			shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
			shapeRenderer.setColor(1, 1, 0, 1);
			shapeRenderer.line(xrecal, yrecal, TouchPos.x, stage.getHeight() - TouchPos.y);
			shapeRenderer.end();
			batch.end();
			*/
		}

		if(!Gdx.input.isTouched()){
			actor.moveBy(speed.x % viewport.getScreenWidth(),speed.y);
		}

		stage.draw();

	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}

}
