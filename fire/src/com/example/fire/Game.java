package com.example.fire;

import android.app.Activity;
import android.content.Intent;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.View;

public class Game extends Activity {

	GLSurfaceView ourSurface;
	
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ourSurface = new GLSurfaceView (this);
		ourSurface.setRenderer(new GLRendererEX(this));
		setContentView(ourSurface);
	}

	@Override
	protected void onPause() {
		super.onPause();
		ourSurface.onPause();
	}

	@Override
	protected void onResume() {
		super.onResume();
		ourSurface.onResume();
	}
	
	public void gameOver(View view) {
    	Intent intent = new Intent(this, GameOver.class);
    	startActivity(intent);
    }

}
