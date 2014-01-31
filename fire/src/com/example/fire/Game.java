package com.example.fire;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Display;

public class Game extends Activity {

	MyGLSurfaceView view;
	public static int Width, Height;

	@Override
	@SuppressWarnings("deprecation")
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Display display = getWindowManager().getDefaultDisplay();
		final int width = (display.getWidth());
		final int height = (display.getHeight());
		Width = width;
		Height = height;

		view = new MyGLSurfaceView(this);
		setContentView(view);

		Timer timer = new Timer();
		timer.schedule(new TimerTask() {

			public void run() {

				Intent intent = new Intent(Game.this, GameOver.class);
				intent.putExtra("score", (int) GLRendererEX.pointscounter);
				startActivity(intent);
				finish();

			}

		}, 30000);
	}

	@Override
	protected void onPause() {
		super.onPause();
		view.onPause();
	}

	@Override
	protected void onResume() {
		super.onResume();
		view.onResume();
	}

}