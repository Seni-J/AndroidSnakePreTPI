package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
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
 * Last update date: 19.04.2018
 * <p>
 * Description: Make a snake game on Android. The gameplay is pretty different from the original snake.
 * The snake can move around freely without any restriction (can move in diagonal i.e.).
 * This class is the main where I call all my other classes.
 *
 * Need to fix the scaling and the body following when the snake goes out of screen (Sometimes, the rest of the body doesn't follow the head.
 * The X and Y of the head isn't on the middle of the sprite.
 * Missing: Menu, Game Over, Keep the scoring.
 */


public class SnakePlayground extends ApplicationAdapter {
    SpriteBatch batch;
    Stage stage;
    Texture bg;
    int i = 2;
    private int score;
    private BitmapFont scoreBitmap;
    private String scoreString;

    static public Array<Vector2> coordinatesSnake; //Array to keep old and new coordinates so the body can follow the head.
    private Array<SnakePart> snake; //All the snake is in an array so we can find the body easily.

    Apple apple;

    Vector2 TouchPos;

    ShapeRenderer shapeRenderer;
    static public ScreenViewport viewport;


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

        //adding part as actors so they are shown on the game.
        stage.addActor(apple);
        stage.addActor(snakePartBody);
        stage.addActor(snakePartHead);

        score = 0;
        scoreString = "score: 0";
        scoreBitmap = new BitmapFont();

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


        //If the user press somewhere, the snake is going to change his direction to where the user pressed.
        if (Gdx.input.justTouched()) {
            coordinatesSnake.get(coordinatesSnake.size - 1).set(snake.get(0).getX(),snake.get(0).getY()); // all parts of the body have to go to where the head is now
            for (SnakePart part : snake) // Redefine targets for the parts that did not have one
                if (part.targcoor < 0)
                    part.targcoor = coordinatesSnake.size - 1;
            TouchPos = new Vector2(Gdx.input.getX(), stage.getHeight() - Gdx.input.getY());
            coordinatesSnake.add(TouchPos); // Array to add positions.
            snake.get(0).NewTarget(coordinatesSnake.size - 1); // Set a new target for the head.
        }


        boolean isHead = true;
        boolean gotApple = false;
        //For each part of the snake
        for (SnakePart snakePart : snake) {
            if (isHead) {
                //Check if the head hit the apple.
                if (snake.get(0).getBounds().overlaps(apple.getBounds())) { // Only the head can eat apple.
                    SnakePart snakePartBody = new SnakePart("snake_body", snake.get(snake.size - 1).getX(), snake.get(snake.size - 1).getY());
                    snakePartBody.NewTarget(snake.get(snake.size - 1).targcoor); // The new body part has the same target as the previous one.
                    snake.add(snakePartBody);
                    stage.addActor(snakePartBody); //add as actor to show the bodypart.
                    score = score + 100; // scoring
                    scoreString = "score: " + score;
                    gotApple = true;
                    apple.PlaceApple(); // new apple position.

                }
                //Check if snakePart isn't the head or the first part of the body.
                if(snakePart != snake.get(1) && snakePart != snake.get(0)) {
                    //GAME OVER part, not complete
                    //Check if the snake had a collision with his body.
                    if (snake.get(0).getBounds().overlaps(snakePart.getBounds())) {
                        batch.begin();
                        scoreBitmap.draw(batch,"GAME OVER",550,750);
                        scoreBitmap.setColor(1.0f, 1.0f, 1.0f, 1.0f);
                        scoreBitmap.getData().setScale(1.5f,1.5f);
                        batch.end();
                    }
                }
            } else {
                isHead = false;
            }
            snakePart.Move(stage);
        }
        if(gotApple){
            for (SnakePart P : snake){
                P.linearSpeed += 10;
            }
        }


		// DRAWING LINE //
/*
			batch.begin();
			shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
			shapeRenderer.setColor(1, 1, 0, 1);
			shapeRenderer.rect(snake.get(1).getX()+25,snake.get(1).getY()+25,snake.get(1).getWidth()/2,snake.get(1).getHeight()/2);
			shapeRenderer.end();
			batch.end();
*/
        // Showing score
            batch.begin();
            scoreBitmap.draw(batch,scoreString,1150,750);
            scoreBitmap.setColor(1.0f, 1.0f, 1.0f, 1.0f);
            scoreBitmap.getData().setScale(1.5f,1.5f);
            batch.end();
            stage.draw();

    }

    @Override
    public void dispose() {
        batch.dispose();
    }

}
