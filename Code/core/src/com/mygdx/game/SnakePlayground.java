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

import java.lang.annotation.Target;

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
	int i = 0;

	SnakeHead snakeHead;
	SnakeTail snakeTail;
	Apple apple;

	Array<Vector2> coordinates;

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
		coordinates = new Array<Vector2>();

		snakeHead = new SnakeHead();
		snakeTail = new SnakeTail(snakeHead.getX(),snakeHead.getY());
		apple = new Apple();

		stage.addActor(apple);
		stage.addActor(snakeHead);
		stage.addActor(snakeTail);


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
			TouchPos = new Vector2(Gdx.input.getX(),stage.getHeight() - Gdx.input.getY());
			coordinates.add(snakeHead.snakeVector); // Array to add positions.
			snakeHead.NewTarget(TouchPos.x,TouchPos.y);
		}
/*
		if(snakeHead.TargetReached()){
			if(coordinates.get(i) != coordinates.peek()){
				i += 1;
				snakeHead.NewTarget(coordinates.get(i).x, coordinates.get(i).y);
				Gdx.app.log("Coordinate",Integer.toString(i) + "has been reached.");
			}
		}
*/
		snakeHead.Move(stage);
/*
		// DRAWING LINE //
			batch.begin();
			shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
			shapeRenderer.setColor(1, 1, 0, 1);
			shapeRenderer.line(TouchPos.x,TouchPos.y,snakeHead.getX(),snakeHead.getY());
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
