package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ScreenViewport;


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
	int i = 2;

	static public Array<Vector2> coordinatesSnake;
	private Array<SnakePart> snake;

	Sprite snakeHead;
	Sprite snakeBody;

	Apple apple;

	Vector2 TouchPos;

	ShapeRenderer shapeRenderer;
	ScreenViewport viewport;



	@Override
	public void create () {
		viewport = new ScreenViewport();
		shapeRenderer = new ShapeRenderer();
		stage = new Stage(viewport);

		batch = new SpriteBatch();
		snakeHead = new Sprite(new Texture("snake_head.png"));
		snakeBody = new Sprite(new Texture("snake_body.png"));
		TouchPos = new Vector2(Gdx.input.getX(),Gdx.input.getY());

		coordinatesSnake = new Array<Vector2>();
		snake = new Array<SnakePart>();
		apple = new Apple();

		//add snake's head and one tail
		SnakePart snakePartHead = new SnakePart(snakeHead);
		snake.insert(0,snakePartHead);
		snake.get(0).setPosition(600,420);
		SnakePart snakePartBody = new SnakePart(snakeBody);
		snakePartBody.frozen = 0;
		snake.insert(1,snakePartBody);
		snake.get(1).setPosition(snake.get(0).getX() - 45,snake.get(0).getY() + 5);

		stage.addActor(apple);
		stage.addActor(snakePartBody);
		stage.addActor(snakePartHead);


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
			coordinatesSnake.add(snake.get(0).snakeVector); // Array to add positions.
			snake.get(0).NewTargetHead(TouchPos.x,TouchPos.y);
		}




		for(SnakePart snakePart : snake){
			if(!snakePart.sprite.equals(snakeHead)) {
				snakePart.Move(stage);
				if(coordinatesSnake.size != 0) {
					if (snakePart.TargetReached()) {
						if(coordinatesSnake.peek() != coordinatesSnake.get(SnakePart.targcoor)) {
							snakePart.NewTarget();
						}else{
							snakePart.FollowHead(snakeHead.getX(),snakeHead.getY());
						}
					}
				}
			}else{
				Gdx.app.log("snake index size", Integer.toString(snake.size));
				if (snakePart.getBounds().overlaps(apple.getBounds())) {
					Gdx.app.log("I touch the apple","!!!");
					SnakePart snakePartBody = new SnakePart(new Sprite(new Texture("snake_body.png")));
					snake.add(snakePartBody);
					apple.PlaceApple();
				}
				snakePart.MoveHead(stage);
				/*if(snakePart.TargetReachedHead()){
				}*/
			}

		}



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
