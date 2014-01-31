package com.example.fire;

import java.util.Random;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.opengl.GLSurfaceView.Renderer;
import android.opengl.GLU;
import android.os.SystemClock;

public class GLRendererEX implements Renderer {

	// default setting for variables is public
	public Context context;
	public GL10 gl;

	private short move = 0;
	private short face = 1;
	public int height = 0;
	public int width = 0;

	public float SpeedX = 0;
	public float SpeedY = 0;
	public float PointX = 20000;
	public float PointY = 20000;
	public static int pointscounter = 0; // counter used to score how many
											// people saved

	private boolean resetwindow = false;
	public boolean changeface = false;

	public Window[] windowarray = new Window[32];
	public GLCube cube;

	public GLRendererEX(Context context) {
		this.context = context;

		cube = new GLCube();

		windowarray[0] = new Window(windowVert1);
		windowarray[1] = new Window(windowVert2);
		windowarray[2] = new Window(windowVert3);
		windowarray[3] = new Window(windowVert4);
		windowarray[4] = new Window(windowVert5);
		windowarray[5] = new Window(windowVert6);
		windowarray[6] = new Window(windowVert7);
		windowarray[7] = new Window(windowVert8);

		windowarray[8] = new Window(windowVertBack1);
		windowarray[9] = new Window(windowVertBack2);
		windowarray[10] = new Window(windowVertBack3);
		windowarray[11] = new Window(windowVertBack4);
		windowarray[12] = new Window(windowVertBack5);
		windowarray[13] = new Window(windowVertBack6);
		windowarray[14] = new Window(windowVertBack7);
		windowarray[15] = new Window(windowVertBack8);

		windowarray[16] = new Window(windowVertRight1);
		windowarray[17] = new Window(windowVertRight2);
		windowarray[18] = new Window(windowVertRight3);
		windowarray[19] = new Window(windowVertRight4);
		windowarray[20] = new Window(windowVertRight5);
		windowarray[21] = new Window(windowVertRight6);
		windowarray[22] = new Window(windowVertRight7);
		windowarray[23] = new Window(windowVertRight8);

		windowarray[24] = new Window(windowVertLeft1);
		windowarray[25] = new Window(windowVertLeft2);
		windowarray[26] = new Window(windowVertLeft3);
		windowarray[27] = new Window(windowVertLeft4);
		windowarray[28] = new Window(windowVertLeft5);
		windowarray[29] = new Window(windowVertLeft6);
		windowarray[30] = new Window(windowVertLeft7);
		windowarray[31] = new Window(windowVertLeft8);
	}

	@Override
	public void onDrawFrame(GL10 gl) {
		gl.glDisable(GL10.GL_DITHER);
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);

		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadIdentity();
		GLU.gluLookAt(gl, 0, 0, -12, 0, 0, 0, 0, 2, 0);

		randomwindows(gl);
		checksaved(gl);
		RotateCube(gl);
		GameEnd();

		cube.draw(gl);
		for (int i = 0; i < 32; i++) {
			windowarray[i].draw(gl);
		}

	}

	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		this.gl = gl;
		gl.glViewport(0, 0, width, height);
		float ratio = (float) width / height;
		gl.glMatrixMode(GL10.GL_PROJECTION);
		gl.glLoadIdentity();
		gl.glFrustumf(-ratio, ratio, -1, 1, 1, 25);
	}

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig arg1) {

		cube.loadGLTexture(gl, this.context);
		for (int i = 0; i < 32; i++) {
			windowarray[i].loadGLTexture(gl, this.context);
		}

		gl.glEnable(GL10.GL_TEXTURE_2D);
		gl.glShadeModel(GL10.GL_SMOOTH);

		gl.glDisable(GL10.GL_DITHER);
		gl.glEnable(GL10.GL_DEPTH_TEST);
		gl.glDisable(GL10.GL_BLEND);
		gl.glDepthFunc(GL10.GL_LEQUAL);
		gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_FASTEST);
		gl.glClearColor(0.8f, 0f, 0.2f, 0.1f);
		gl.glClearDepthf(1f);

	}

	private void GameEnd() {
		long time = SystemClock.uptimeMillis();
		if (time > 45000) {
			// end game
		}
	}

	private void checksaved(GL10 gl) {
		for (int i = 0; i < 8; i++) {
			if ((PointX >= -100 && PointX <= 100)
					&& (PointY >= -100 && PointY <= 100)) {
				if (!windowarray[i].picture1) { // saved person
					windowarray[i].loadGLTexture(gl, this.context);
					windowarray[i].picture1 = true;
					pointscounter++;
				}
			}
		}
		PointX = PointY = 1500;
	}

	private void randomwindows(GL10 gl) {
		long time = SystemClock.uptimeMillis() % 4000;
		if ((time > 2000) && (!resetwindow)) {
			Random generator = new Random();
			int rand = generator.nextInt(8);
			if (windowarray[rand].picture1) {
				windowarray[rand].loadGLTexture2(gl, this.context);
				windowarray[rand].picture1 = false;
				resetwindow = true;
			}
		} else if ((time < 2000) && (resetwindow)) {
			for (int i = 0; i < 32; i++) {
				if (!windowarray[i].picture1) {
					windowarray[i].loadGLTexture(gl, this.context);
					windowarray[i].picture1 = true;
				}
			}
			resetwindow = false;
		}

	}

	private void RotateCube(GL10 gl) {
		if (face == 1) {
			if (SpeedX > 0) {
				if (move < 90) {
					move += 4;
					gl.glRotatef(move, 0, 1, 0);
				} else {
					SpeedX = 0;
					face = 2;
				}
			}

			if (SpeedX < 0) {
				if (move > -90) {
					move -= 4;
					gl.glRotatef(move, 0, 1, 0);
				} else {
					SpeedX = 0;
					face = 4;
					move = 270;
				}
			}
		}

		if (face == 2) {
			if (SpeedX > 0) {
				if (move < 180) {
					move += 4;
					gl.glRotatef(move, 0, 1, 0);
				} else {
					SpeedX = 0;
					face = 3;
				}
			}

			if (SpeedX < 0) {
				if (move > 0) {
					move -= 4;
					gl.glRotatef(move, 0, 1, 0);
				} else {
					SpeedX = 0;
					face = 1;
				}
			}
		}

		if (face == 3) {
			if (SpeedX > 0) {
				if (move < 270) {
					move += 4;
					gl.glRotatef(move, 0, 1, 0);
				} else {
					SpeedX = 0;
					face = 4;
				}
			}

			if (SpeedX < 0) {
				if (move > 90) {
					move -= 4;
					gl.glRotatef(move, 0, 1, 0);
				} else {
					SpeedX = 0;
					face = 2;
				}
			}
		}

		if (face == 4) {
			if (SpeedX > 0) {
				if (move < 360) {
					move += 4;
					gl.glRotatef(move, 0, 1, 0);
				} else {
					SpeedX = 0;
					face = 1;
					move = 0;
				}
			}

			if (SpeedX < 0) {
				if (move > 180) {
					move -= 4;
					gl.glRotatef(move, 0, 1, 0);
				} else {
					SpeedX = 0;
					face = 3;
				}
			}
		}

	}

	// Front Face windows
	// point 0 (bottom right) point 1 (top right) point 2 (top left) point 3
	// (bottom left)
	private float windowVert1[] = { -3f, 3.5f, -4.1f, -3f, 5.5f, -4.1f, -1f,
			5.5f, -4.1f, -1f, 3.5f, -4.1f };
	private float windowVert2[] = { 1f, 3.5f, -4.1f, 1f, 5.5f, -4.1f, 3f, 5.5f,
			-4.1f, 3f, 3.5f, -4.1f };
	private float windowVert3[] = { 1f, 0.5f, -4.1f, 1f, 2.5f, -4.1f, 3f, 2.5f,
			-4.1f, 3f, 0.5f, -4.1f };
	private float windowVert4[] = { -3f, 0.5f, -4.1f, -3f, 2.5f, -4.1f, -1f,
			2.5f, -4.1f, -1f, 0.5f, -4.1f };
	private float windowVert5[] = { -3f, -5.5f, -4.1f, -3f, -3.5f, -4.1f, -1f,
			-3.5f, -4.1f, -1f, -5.5f, -4.1f };
	private float windowVert6[] = { 1f, -5.5f, -4.1f, 1f, -3.5f, -4.1f, 3f,
			-3.5f, -4.1f, 3f, -5.5f, -4.1f };
	private float windowVert7[] = { 1f, -2.5f, -4.1f, 1f, -0.5f, -4.1f, 3f,
			-0.5f, -4.1f, 3f, -2.5f, -4.1f };
	private float windowVert8[] = { -3f, -2.5f, -4.1f, -3f, -0.5f, -4.1f, -1f,
			-0.5f, -4.1f, -1f, -2.5f, -4.1f };

	// Back Face Windows
	// point 0 (bottom right) point 1 (top right) point 2 (top left) point 3
	// (bottom left)
	private float windowVertBack1[] = { -3f, 3.5f, 4.1f, -3f, 5.5f, 4.1f, -1f,
			5.5f, 4.1f, -1f, 3.5f, 4.1f };
	private float windowVertBack2[] = { 1f, 3.5f, 4.1f, 1f, 5.5f, 4.1f, 3f,
			5.5f, 4.1f, 3f, 3.5f, 4.1f };
	private float windowVertBack3[] = { 1f, 0.5f, 4.1f, 1f, 2.5f, 4.1f, 3f,
			2.5f, 4.1f, 3f, 0.5f, 4.1f };
	private float windowVertBack4[] = { -3f, 0.5f, 4.1f, -3f, 2.5f, 4.1f, -1f,
			2.5f, 4.1f, -1f, 0.5f, 4.1f };
	private float windowVertBack5[] = { -3f, -5.5f, 4.1f, -3f, -3.5f, 4.1f,
			-1f, -3.5f, 4.1f, -1f, -5.5f, 4.1f };
	private float windowVertBack6[] = { 1f, -5.5f, 4.1f, 1f, -3.5f, 4.1f, 3f,
			-3.5f, 4.1f, 3f, -5.5f, 4.1f };
	private float windowVertBack7[] = { 1f, -2.5f, 4.1f, 1f, -0.5f, 4.1f, 3f,
			-0.5f, 4.1f, 3f, -2.5f, 4.1f };
	private float windowVertBack8[] = { -3f, -2.5f, 4.1f, -3f, -0.5f, 4.1f,
			-1f, -0.5f, 4.1f, -1f, -2.5f, 4.1f };

	// Right Face Windows
	// point 0 (bottom right) point 1 (top right) point 2 (top left) point 3
	// (bottom left)
	private float windowVertRight1[] = { -4.1f, 3.5f, 3f, -4.1f, 5.5f, 3f,
			-4.1f, 5.5f, 1f, -4.1f, 3.5f, 1f };
	private float windowVertRight2[] = { -4.1f, 3.5f, -1f, -4.1f, 5.5f, -1f,
			-4.1f, 5.5f, -3f, -4.1f, 3.5f, -3f };
	private float windowVertRight3[] = { -4.1f, 0.5f, -1f, -4.1f, 2.5f, -1f,
			-4.1f, 2.5f, -3f, -4.1f, 0.5f, -3f };
	private float windowVertRight4[] = { -4.1f, 0.5f, 3f, -4.1f, 2.5f, 3f,
			-4.1f, 2.5f, 1f, -4.1f, 0.5f, 1f };
	private float windowVertRight5[] = { -4.1f, -5.5f, 3f, -4.1f, -3.5f, 3f,
			-4.1f, -3.5f, 1f, -4.1f, -5.5f, 1f };
	private float windowVertRight6[] = { -4.1f, -5.5f, -1f, -4.1f, -3.5f, -1f,
			-4.1f, -3.5f, -3f, -4.1f, -5.5f, -3f };
	private float windowVertRight7[] = { -4.1f, -2.5f, -1f, -4.1f, -0.5f, -1f,
			-4.1f, -0.5f, -3f, -4.1f, -2.5f, -3f };
	private float windowVertRight8[] = { -4.1f, -2.5f, 3f, -4.1f, -0.5f, 3f,
			-4.1f, -0.5f, 1f, -4.1f, -2.5f, 1f };

	// Left Face Windows
	// point 0 (bottom right) point 1 (top right) point 2 (top left) point 3
	// (bottom left)
	private float windowVertLeft1[] = { 4.1f, 3.5f, 3f, 4.1f, 5.5f, 3f, 4.1f,
			5.5f, 1f, 4.1f, 3.5f, 1f };
	private float windowVertLeft2[] = { 4.1f, 3.5f, -1f, 4.1f, 5.5f, -1f, 4.1f,
			5.5f, -3f, 4.1f, 3.5f, -3f };
	private float windowVertLeft3[] = { 4.1f, 0.5f, -1f, 4.1f, 2.5f, -1f, 4.1f,
			2.5f, -3f, 4.1f, 0.5f, -3f };
	private float windowVertLeft4[] = { 4.1f, 0.5f, 3f, 4.1f, 2.5f, 3f, 4.1f,
			2.5f, 1f, 4.1f, 0.5f, 1f };
	private float windowVertLeft5[] = { 4.1f, -5.5f, 3f, 4.1f, -3.5f, 3f, 4.1f,
			-3.5f, 1f, 4.1f, -5.5f, 1f };
	private float windowVertLeft6[] = { 4.1f, -5.5f, -1f, 4.1f, -3.5f, -1f,
			4.1f, -3.5f, -3f, 4.1f, -5.5f, -3f };
	private float windowVertLeft7[] = { 4.1f, -2.5f, -1f, 4.1f, -0.5f, -1f,
			4.1f, -0.5f, -3f, 4.1f, -2.5f, -3f };
	private float windowVertLeft8[] = { 4.1f, -2.5f, 3f, 4.1f, -0.5f, 3f, 4.1f,
			-0.5f, 1f, 4.1f, -2.5f, 1f };

	@SuppressWarnings("static-access")
	public void getparams(MyGLSurfaceView myGLSurfaceView) {
		Game s = null;
		this.width = s.Width;
		this.height = s.Height;
	}

}