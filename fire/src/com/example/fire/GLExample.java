package com.example.fire;

// Project written by the new Boston on youtube
// Android Application Development Tutorial - 168 - OpenGL Introduction
// Android Application Development Tutorial - 169 - OpenGL Renderer Basics
// Android Application Development Tutorial - 170 - Renderering a Background

import com.example.fire.GLRendererEX;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;

public class GLExample extends Activity {
	
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
	
	
}
