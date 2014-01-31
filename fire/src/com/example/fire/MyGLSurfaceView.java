package com.example.fire;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;

public class MyGLSurfaceView extends GLSurfaceView {

	public GLRendererEX renderer;
	private float previousX = 0;
	private float previousY = 0;
	private final float TOUCH_SCALE_FACTOR = 40.0f / 320.0f;

	// private Window windarray [] = renderer.windowarray;

	public MyGLSurfaceView(Context context) {
		super(context);
		renderer = new GLRendererEX(context);
		this.setRenderer(renderer);
		this.requestFocus();
		this.setFocusableInTouchMode(true);
		renderer.getparams(this);

	}

	@Override
	public boolean onTouchEvent(final MotionEvent event) {
		float CurrX = event.getX();
		float CurrY = event.getY();
		float deltaX, deltaY;
		switch (event.getAction()) {
		case MotionEvent.ACTION_MOVE:
			deltaX = CurrX - previousX;
			deltaY = CurrY - previousY;
			renderer.SpeedX += deltaX * TOUCH_SCALE_FACTOR;
			renderer.SpeedY += deltaY * TOUCH_SCALE_FACTOR;
		case MotionEvent.ACTION_DOWN:
			// renderer.PointX = CurrX;
			// renderer.PointY = CurrY;
			renderer.PointX = (((CurrX * 2) / renderer.width) - 1);
			renderer.PointY = (((CurrX * 2) / renderer.height) - 1);
		}

		previousX = CurrX;
		previousY = CurrY;
		return true;

	}

}