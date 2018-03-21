package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import static com.badlogic.gdx.math.MathUtils.cosDeg;
import static com.badlogic.gdx.math.MathUtils.sinDeg;
import static java.lang.Math.abs;

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

	SnakeHead snakeHead;
	Apple apple;
	Array positions;

	Vector2 TouchPos;

	ShapeRenderer shapeRenderer;
	ScreenViewport viewport;



	@Override
	public void create () {
		viewport = new ScreenViewport();
		shapeRenderer = new ShapeRenderer();
		stage = new Stage(viewport);

		batch = new SpriteBatch();
		TouchPos = new Vector2(Gdx.input.getX(),Gdx.input.getY());
		positions = new Array();


		snakeHead = new SnakeHead();
		apple = new Apple();
		stage.addActor(apple);
		stage.addActor(snakeHead);


		Gdx.input.setInputProcessor(stage);

		bg = new Texture("grass_bg.jpg");

	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act(Gdx.graphics.getDeltaTime());
		stage.getBatch().begin();
		stage.getBatch().draw(bg,0,0,viewport.getWorldWidth(),viewport.getWorldHeight());
		stage.getBatch().end();

		if(Gdx.input.justTouched()){
			TouchPos = new Vector2(Gdx.input.getX(),Gdx.input.getY());
			positions.add(TouchPos.x,TouchPos.y); // Array to add positions.

			Gdx.app.log("Positions", positions.first().toString());

			snakeHead.Move(TouchPos.x,TouchPos.y,stage); // Use the method to move from the class SnakeHead
		}

		snakeHead.moveBy(snakeHead.speedX(),snakeHead.speedY());

		if(snakeHead.SnakeVector.x == TouchPos.x && snakeHead.SnakeVector.y == TouchPos.y){
			Gdx.app.log("I have reached", "The Objective.");
		}
		/*Gdx.app.log("Sub Vector X: ", Float.toString(snakeHead.SnakeVector.sub(TouchPos).x));
		Gdx.app.log("Sub Vector Y: ", Float.toString(snakeHead.SnakeVector.sub(TouchPos).y));*/
		if(TouchPos.sub(snakeHead.SnakeVector).x == 0 && TouchPos.sub(snakeHead.SnakeVector).y == 0){
			Gdx.app.log("I have reached", "The Objective.");
		}

		// Calculate modulo for Snake's head when he goes out of the screen so he can come back from the opposite side.
		float moduloX = snakeHead.getX() % viewport.getScreenWidth();
		float moduloY =  snakeHead.getY() % viewport.getScreenHeight();

		// Java keep the minus sign with modulo so we have to change it for a positive value instead of a negative value.
		if (moduloX < 0)
		{
			moduloX += viewport.getScreenWidth();
		}
		if (moduloY < 0)
		{
			moduloY += viewport.getScreenHeight();
		}

		snakeHead.setX(moduloX);
		snakeHead.setY(moduloY);


		/*		 // DRAWING LINE //
			batch.begin();
			shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
			shapeRenderer.setColor(1, 1, 0, 1);
			shapeRenderer.line((float) viewport.getScreenX(),(float) viewport.getScreenY(),snakeHead.getX() + snakeHead.getWidth() /2,snakeHead.getY() + snakeHead.getHeight() /2);
			shapeRenderer.end();
			batch.end();
		*/

		stage.draw();

	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}

}
