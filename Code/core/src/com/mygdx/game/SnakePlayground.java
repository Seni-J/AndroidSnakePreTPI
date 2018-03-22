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
		coordinates.add(new Vector2(800,800));
		coordinates.add(new Vector2(650,400));
		coordinates.add(new Vector2(930,110));
		coordinates.add(new Vector2(120,760));
		coordinates.add(new Vector2(1,1));
		coordinates.add(new Vector2(530,230));
		coordinates.add(new Vector2(150,160));
		coordinates.add(new Vector2(1200,790));


		snakeHead = new SnakeHead();
		apple = new Apple();
		stage.addActor(apple);
		stage.addActor(snakeHead);

		snakeHead.NewTarget(coordinates.get(i).x,coordinates.get(i).y);

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


		if(snakeHead.TargetReached() == true){
			if(coordinates.get(i) == coordinates.peek()){
				Gdx.app.log("All coordinates have been reached",".");
			}else{
				i += 1;
				snakeHead.NewTarget(coordinates.get(i).x, coordinates.get(i).y);
				Gdx.app.log("Coordinate",Integer.toString(i) + "has been reached.");
			}
		}
			/*
		if(Gdx.input.justTouched()){
			TouchPos = new Vector2(Gdx.input.getX(),stage.getHeight() - Gdx.input.getY());
			coordinates.add(TouchPos); // Array to add positions.

			Gdx.app.log("Target", coordinates.first().toString());
			snakeHead.NewTarget(TouchPos.x,TouchPos.y);
		}
*/
		snakeHead.Move(stage);

		//Gdx.app.log("Target Reached: ", Boolean.toString(snakeHead.TargetReached()));

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
