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
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.math.MathUtils;

import static java.lang.Math.abs;
import static java.lang.Math.asin;

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
	Vector2 TouchPos;
	Vector2 Cal;
	Vector2 Cal2;
	ShapeRenderer shapeRenderer;

	ImageButton arrowUp;
	ImageButton arrowDown;
	ImageButton arrowLeft;
	ImageButton arrowRight;



	@Override
	public void create () {
		ScreenViewport viewport = new ScreenViewport();
		stage = new Stage(viewport);

		batch = new SpriteBatch();
		TouchPos = new Vector2(Gdx.input.getX(),Gdx.input.getY());
		Cal = new Vector2();
		Cal2 = new Vector2();
		shapeRenderer = new ShapeRenderer();


		// Beginning of the arrow keys code.
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

		// End of arrow keys code.

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




		if(Gdx.input.isTouched()){
			//Gdx.app.log("Is Touched?", "YES");
			//Gdx.app.log("Input X?", Float.toString(Gdx.input.getX()));
			//Gdx.app.log("Input Y?", Float.toString(Gdx.input.getY()));


			TouchPos = new Vector2(Gdx.input.getX(),Gdx.input.getY());

			float xrecal = actor.SnakeVector.x + actor.getWidth() /2;
			float yrecal = actor.SnakeVector.y + actor.getHeight() /2;
			float angle = 0;

			float hyp = Cal.dst(xrecal,yrecal,TouchPos.x,stage.getHeight() - TouchPos.y);



			float oppX = abs(TouchPos.x - actor.SnakeVector.x);
			float oppY = abs(stage.getHeight() - TouchPos.y - actor.SnakeVector.y);


			if(TouchPos.x > xrecal && stage.getHeight() - TouchPos.y > yrecal) {
				angle = (float) Math.toDegrees(Math.asin(oppX / hyp));
			}
			if(TouchPos.x < xrecal && stage.getHeight() - TouchPos.y < yrecal) {
				angle = (float) Math.toDegrees(Math.asin(oppY / hyp));
			}
			if(TouchPos.x > xrecal && stage.getHeight() - TouchPos.y < yrecal) {
				angle = (float) Math.toDegrees(Math.asin(oppX / hyp));
			}
			if(TouchPos.x < xrecal && stage.getHeight() - TouchPos.y > yrecal) {
				angle = (float) Math.toDegrees(Math.asin(oppY / hyp));
			}



			batch.begin();
			shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
			shapeRenderer.setColor(1, 1, 0, 1);
			shapeRenderer.line(xrecal, yrecal, TouchPos.x, stage.getHeight() - TouchPos.y);
			shapeRenderer.end();
			batch.end();

			/*if(TouchPos.x > xrecal && stage.getHeight() - TouchPos.y > yrecal)
			{
				angle += 270;
			}*/

			actor.sprite.setRotation(angle);
			Gdx.app.log("Angle?", Float.toString(angle));
			Gdx.app.log("OppX?", Float.toString(oppX));
			Gdx.app.log("Hyp?", Float.toString(hyp));
/*
			Gdx.app.log("Angle?", Float.toString(angle));
			Gdx.app.log("hyp?", "Angle = arcsin ("+ Float.toString(hyp));
			Gdx.app.log("SnakeX?", Float.toString(actor.SnakeVector.x));
			Gdx.app.log("SnakeY?", Float.toString(actor.SnakeVector.y));
			Gdx.app.log("SpriteOrX?", Float.toString(actor.sprite.getOriginX()));
			Gdx.app.log("SpriteOrY?", Float.toString(actor.sprite.getOriginY()));

*/
			/*
			float width = TouchPos.add(actor.SnakeVector).len(); // length of resultant vector
			float angle = TouchPos.angle(); // and angle what you are looking for


			Gdx.app.log("Distance", Float.toString(width));
			Gdx.app.log("Angle", Float.toString(angle));*/
		}



		/**					Move with arrow keys
		 * 	Image buttons have been added to move the snake's head.
		 * **/

		// Beginning of the arrow keys code.

		if(arrowUp.isPressed() && arrowDown.isPressed()){
			actor.setPosition(0,0);
		}

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

		// End of arrow keys code.


		stage.draw();

	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}

}
