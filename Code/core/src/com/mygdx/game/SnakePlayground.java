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
 * <p>
 * Description: Make a snake game on Android. The gameplay is pretty different from the original snake.
 * The snake car move around freely without any restriction (can move in diagonal i.e.).
 * This class is the main where I call all my other classes.
 */


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
    public void create() {
        viewport = new ScreenViewport();
        shapeRenderer = new ShapeRenderer();
        stage = new Stage(viewport);

        batch = new SpriteBatch();
        TouchPos = new Vector2(Gdx.input.getX(), Gdx.input.getY());

        coordinatesSnake = new Array<Vector2>();
        coordinatesSnake.add(new Vector2(1000, 420));
        snake = new Array<SnakePart>();
        apple = new Apple();

        //add snake's head and one tail
        SnakePart snakePartHead = new SnakePart("snake_head", 200, 420);

        snake.insert(0, snakePartHead);
        snakePartHead.frozen = 0;
        snakePartHead.NewTarget(0);
        SnakePart snakePartBody = new SnakePart("snake_body", 155, 420);
        snakePartBody.frozen = 0;
        snakePartBody.NewTarget(0);
        snake.insert(1, snakePartBody);

        stage.addActor(apple);
        stage.addActor(snakePartBody);
        stage.addActor(snakePartHead);


        Gdx.input.setInputProcessor(stage);

        bg = new Texture("grass_bg.jpg");

    }

    @Override
    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Gdx.graphics.getDeltaTime());
        stage.getBatch().begin();
        stage.getBatch().draw(bg, 0, 0, viewport.getWorldWidth(), viewport.getWorldHeight());
        stage.getBatch().end();

        Gdx.app.log("Array:",coordinatesSnake.toString());


        if (Gdx.input.justTouched()) {
            coordinatesSnake.get(coordinatesSnake.size - 1).set(snake.get(0).getX(),snake.get(0).getY()); // all parts of the body have to go to where the head is now
            for (SnakePart part : snake) // Redefine targets for the parts that did not have one
                if (part.targcoor < 0)
                    part.targcoor = coordinatesSnake.size - 1;
            TouchPos = new Vector2(Gdx.input.getX(), stage.getHeight() - Gdx.input.getY());
            coordinatesSnake.add(TouchPos); // Array to add positions.
            snake.get(0).NewTarget(coordinatesSnake.size - 1);
        }


        boolean isHead = true;

        for (SnakePart snakePart : snake) {
            if (!isHead) {
                snakePart.Move(stage);
            } else {
                if (snakePart.getBounds().overlaps(apple.getBounds())) {
                    Gdx.app.log("SNAKE", "I touch the apple");
                    SnakePart snakePartBody = new SnakePart("snake_body", snake.get(snake.size - 1).getX(), snake.get(snake.size - 1).getY());
                    snake.add(snakePartBody);
                    apple.PlaceApple();
                }
                snakePart.Move(stage);
                isHead = false;
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
    public void dispose() {
        batch.dispose();
    }

}
