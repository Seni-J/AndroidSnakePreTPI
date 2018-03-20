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

import java.awt.geom.Arc2D;

import static com.badlogic.gdx.math.MathUtils.cosDeg;
import static com.badlogic.gdx.math.MathUtils.sinDeg;
import static java.lang.Math.abs;
import static java.lang.Math.asin;
import static java.lang.Math.cos;
import static java.lang.Math.sin;

/**
 * Author: Senistan Jegarajasingam
 * Project Name: Android SnakePlayground Pré-TPI
 * Last update date: 16.03.2018
 *
 * Description: Make a snake game on Android. The gameplay is pretty different from the original snake.
 * The snake car move around freely without any restriction (can move in diagonal i.e.).
 * This class is the main where I call all my other classes.
 *
 * */


public class SnakePlayground extends ApplicationAdapter {
	SpriteBatch batch;
	Stage stage;
	Texture bg;
	//test

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
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act(Gdx.graphics.getDeltaTime());
		stage.getBatch().begin();
		stage.getBatch().draw(bg,0,0);
		stage.getBatch().end();




		if(Gdx.input.justTouched()){
			TouchPos = new Vector2(Gdx.input.getX(),Gdx.input.getY());
			positions.add(TouchPos.x,TouchPos.y); // Array to add positions.

			Gdx.app.log("Positions: ", positions.first().toString());

			float xrecal = actor.SnakeVector.x + actor.getWidth() /2;	// Set the center of the sprite.
			float yrecal = actor.SnakeVector.y + actor.getHeight() /2;	// Set the center of the sprite.
			float angle = 0;


			target.set(TouchPos.x - xrecal, (stage.getHeight() - TouchPos.y) - yrecal); // Set the target vector for the angle.

			angle = target.angle();	// Find the rotation angle for the sprite.
			actor.sprite.setRotation(angle - 90);

			// LIBGDX WAY //
			speed.x = 1f * cosDeg(angle);	//Calculate Velocity for X.
			speed.y = 1f * sinDeg(angle);	//Calculate Velocity for Y.
		}

		actor.moveBy(speed.x,speed.y);

		float moduloX = actor.getX() % viewport.getScreenWidth();
		float moduloY =  actor.getY() % viewport.getScreenHeight();
		if (moduloX < 0)
		{
			moduloX += viewport.getScreenWidth();
		}
		if (moduloY < 0)
		{
			moduloY += viewport.getScreenHeight();
		}
		actor.setX(moduloX);
		actor.setY(moduloY);

		 // DRAWING LINE //
			batch.begin();
			shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
			shapeRenderer.setColor(1, 1, 0, 1);
			shapeRenderer.line((float) viewport.getScreenX(),(float) viewport.getScreenY(),actor.getX() + actor.getWidth() /2,actor.getY() + actor.getHeight() /2);
			shapeRenderer.end();
			batch.end();



		Gdx.app.log("X: ", Float.toString(actor.getX()));
		Gdx.app.log("Y: ", Float.toString(actor.getY()));
		Gdx.app.log("Modulo X: ", Float.toString(moduloX));
		Gdx.app.log("Modulo Y: ", Float.toString(moduloY));

		stage.draw();

	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}

}
