package com.mygdx.game;


import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.sun.org.apache.xpath.internal.operations.Bool;




public class SnakeTPI extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	private static final float MOVE_TIME = 1F;
	private float timer = MOVE_TIME;
	private int moveX = 0, moveY = 0;
	private float delta = 0;

	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("snake_body.jpg");
	}

	@Override
	public void render () {
		int height = Gdx.graphics.getHeight();
		int width = Gdx.graphics.getWidth();


		BitmapFont font = new BitmapFont();


		String widthstr = Integer.toString(width);
		String heightstr = Integer.toString(height);

		delta = Gdx.graphics.getDeltaTime();
		Vector2 touchPos = new Vector2(Gdx.input.getX(),Gdx.input.getY());


		float angle = touchPos.angle();

		String anglestr = Float.toString(angle);


		timer -= delta;



		if (timer <= 0 && touchPos.isZero() == true) {
			timer = MOVE_TIME;
			if (moveX >= Gdx.graphics.getWidth()){
				moveX = 0;
			}else {
				moveX += 32;
			}
		}else if (timer <= 0 && touchPos.isZero() == false){
			timer = MOVE_TIME;
			if (moveY >= Gdx.graphics.getHeight()) {
				moveY = 0;
			}else {
				moveY += 32;
			}
		}



		/*String timerstr = Float.toString(timer);
		font.draw(batch, timerstr, 500, 50);
		*/

		Gdx.gl.glClearColor(0, 0, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		String istrue = Boolean.toString(touchPos.isZero());

		batch.begin();
		font.draw(batch, istrue, 150, 50);
		font.draw(batch, "angle:" + anglestr, 50, 150);
		font.draw(batch, "height:" + heightstr, 50, 100);
		font.draw(batch, "width:" + widthstr, 50, 50);
		batch.draw(img, moveX, moveY);

		//batch.draw(img, touchPos.x, height - touchPos.y);


		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
