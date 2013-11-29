package com.example.fire;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import com.example.fire.GLCube;
import com.example.fire.Window;

import android.content.Context;
import android.opengl.GLU;
import android.opengl.GLSurfaceView.Renderer;
import android.os.SystemClock;

public class GLRendererEX implements Renderer {

	private GLCube cube;
	private Window W1;
	private Window W2;
	private Window W3;
	private Window W4;
	
	private Window W5;
	private Window W6;
	private Window W7;
	private Window W8;
	
	// private Window testWindow;
	private Context context;
	
	// back wall windows
	private Window WB1, WB2, WB3, WB4, WB5, WB6, WB7, WB8;
	// Right wall windows
	private Window WR1, WR2, WR3, WR4, WR5, WR6, WR7, WR8;
	// Left wall windows
	private Window WL1, WL2, WL3, WL4, WL5, WL6, WL7, WL8;
	
	/*private GLTriangleEX tri1;
	private GLTriangleEX tri2;
	private float vertices1 [] = {
			1f, 1f,  // point 0
			-1f, -1f, // point 1
			1f, -1f // point 2
	};
	private float vertices2 [] = {
			1f, 1f,  // point 0
			-1f, -1f, // point 1
			-1f, 1f // point 2
	};
	
	private float testColours [] = {
			1, 0, 0, 1, 		// Color of first vertices
			1, 0, 0, 1, 		// Color of second vertices
			1, 0, 0, 1,			// Color of third vertices
			1, 0, 0, 1,			// Color of forth Vertex
			1, 0, 0, 1,			// Color of fifth vertices
			1, 0, 0, 1			// Color of sixth Vertex
	};
	*/
	
	private float windowVert1 [] = {
			-3f, 3.5f, -4.1f,   // point 0 (bottom right)
			-3f, 5.5f, -4.1f,   // point 1 (top right)
			-1f, 5.5f, -4.1f,   // point 2 (top left)
			-1f, 3.5f,  -4.1f   // point 3 (bottom left)
	};
	
	private float windowVert2 [] = {
			1f, 3.5f, -4.1f,   // point 0 (bottom right)
			1f, 5.5f, -4.1f,   // point 1 (top right)
			3f, 5.5f, -4.1f,   // point 2 (top left)
			3f, 3.5f,  -4.1f   // point 3 (bottom left)
	};
	
	private float windowVert3 [] = {
			1f, 0.5f, -4.1f,   // point 0 (bottom right)
			1f, 2.5f, -4.1f,   // point 1 (top right)
			3f, 2.5f, -4.1f,   // point 2 (top left)
			3f, 0.5f,  -4.1f   // point 3 (bottom left)
	};
	
	private float windowVert4 [] = {
			-3f, 0.5f, -4.1f,   // point 0 (bottom right)
			-3f, 2.5f, -4.1f,   // point 1 (top right)
			-1f, 2.5f, -4.1f,   // point 2 (top left)
			-1f, 0.5f,  -4.1f   // point 3 (bottom left)
	};
	
	// Front Face Bottom 4 windows
	//						     point 0 (bottom right)   point 1 (top right)    point 2 (top left)     point 3 (bottom left)
	private float windowVert5 [] = {-3f, -3.5f, -4.1f,     -3f, -5.5f, -4.1f,     -1f, -5.5f, -4.1f,     -1f, -3.5f,  -4.1f };
	private float windowVert6 [] = {1f, -3.5f, -4.1f,      1f, -5.5f, -4.1f,      3f, -5.5f, -4.1f,      3f, -3.5f,  -4.1f  };
	private float windowVert7 [] = {1f, -0.5f, -4.1f,      1f, -2.5f, -4.1f,      3f, -2.5f, -4.1f,      3f, -0.5f,  -4.1f  };
	private float windowVert8 [] = {-3f, -0.5f, -4.1f,     -3f, -2.5f, -4.1f,     -1f, -2.5f, -4.1f,     -1f, -0.5f,  -4.1f };   
	
	// Back Face Windows
	//    							point 0 (bottom right)   point 1 (top right)    point 2 (top left)     point 3 (bottom left)
	private float windowVertBack1 [] = {-3f, 3.5f, 4.1f,     -3f, 5.5f, 4.1f,     -1f, 5.5f, 4.1f,     -1f, 3.5f,  4.1f };
	private float windowVertBack2 [] = {1f, 3.5f, 4.1f,      1f, 5.5f, 4.1f,      3f, 5.5f, 4.1f,      3f, 3.5f,  4.1f  };
	private float windowVertBack3 [] = {1f, 0.5f, 4.1f,      1f, 2.5f, 4.1f,      3f, 2.5f, 4.1f,      3f, 0.5f,  4.1f  };
	private float windowVertBack4 [] = {-3f, 0.5f, 4.1f,     -3f, 2.5f, 4.1f,     -1f, 2.5f, 4.1f,     -1f, 0.5f,  4.1f };   
	private float windowVertBack5 [] = {-3f, -3.5f, 4.1f,     -3f, -5.5f, 4.1f,     -1f, -5.5f, 4.1f,     -1f, -3.5f,  4.1f };
	private float windowVertBack6 [] = {1f, -3.5f, 4.1f,      1f, -5.5f, 4.1f,      3f, -5.5f, 4.1f,      3f, -3.5f,  4.1f  };
	private float windowVertBack7 [] = {1f, -0.5f, 4.1f,      1f, -2.5f, 4.1f,      3f, -2.5f, 4.1f,      3f, -0.5f,  4.1f  };
	private float windowVertBack8 [] = {-3f, -0.5f, 4.1f,     -3f, -2.5f, 4.1f,     -1f, -2.5f, 4.1f,     -1f, -0.5f,  4.1f };   
	
	// Right Face Windows
	//    							point 0 (bottom right)   point 1 (top right)    point 2 (top left)     point 3 (bottom left)
	private float windowVertRight1 [] = {-4.1f, 3.5f, 3f,        -4.1f, 5.5f, 3f,     -4.1f, 5.5f, 1f,       -4.1f, 3.5f,  1f };
	private float windowVertRight2 [] = {-4.1f, 3.5f, -1f,       -4.1f, 5.5f, -1f,    -4.1f, 5.5f, -3f,      -4.1f, 3.5f,  -3f  };
	private float windowVertRight3 [] = {-4.1f, 0.5f, -1f,       -4.1f, 2.5f, -1f,    -4.1f, 2.5f, -3f,      -4.1f, 0.5f,  -3f  };
	private float windowVertRight4 [] = {-4.1f, 0.5f, 3f,        -4.1f, 2.5f, 3f,     -4.1f, 2.5f, 1f,       -4.1f, 0.5f,  1f };   
	private float windowVertRight5 [] = {-4.1f, -3.5f, 3f,       -4.1f, -5.5f, 3f,    -4.1f, -5.5f, 1f,      -4.1f, -3.5f,  1f };
	private float windowVertRight6 [] = {-4.1f, -3.5f, -1f,      -4.1f, -5.5f, -1f,   -4.1f, -5.5f, -3f,     -4.1f, -3.5f,  -3f  };
	private float windowVertRight7 [] = {-4.1f, -0.5f, -1f,      -4.1f, -2.5f, -1f,   -4.1f, -2.5f, -3f,     -4.1f, -0.5f,  -3f  };
	private float windowVertRight8 [] = {-4.1f, -0.5f, 3f,       -4.1f, -2.5f, 3f,    -4.1f, -2.5f, 1f,      -4.1f, -0.5f,  1f };   
	
	// Left Face Windows
	//    							point 0 (bottom right)   point 1 (top right)    point 2 (top left)     point 3 (bottom left)
	private float windowVertLeft1 [] = {4.1f, 3.5f, 3f,        4.1f, 5.5f, 3f,     4.1f, 5.5f, 1f,       4.1f, 3.5f,  1f };
	private float windowVertLeft2 [] = {4.1f, 3.5f, -1f,       4.1f, 5.5f, -1f,    4.1f, 5.5f, -3f,      4.1f, 3.5f,  -3f  };
	private float windowVertLeft3 [] = {4.1f, 0.5f, -1f,       4.1f, 2.5f, -1f,    4.1f, 2.5f, -3f,      4.1f, 0.5f,  -3f  };
	private float windowVertLeft4 [] = {4.1f, 0.5f, 3f,        4.1f, 2.5f, 3f,     4.1f, 2.5f, 1f,       4.1f, 0.5f,  1f };   
	private float windowVertLeft5 [] = {4.1f, -3.5f, 3f,       4.1f, -5.5f, 3f,    4.1f, -5.5f, 1f,      4.1f, -3.5f,  1f };
	private float windowVertLeft6 [] = {4.1f, -3.5f, -1f,      4.1f, -5.5f, -1f,   4.1f, -5.5f, -3f,     4.1f, -3.5f,  -3f  };
	private float windowVertLeft7 [] = {4.1f, -0.5f, -1f,      4.1f, -2.5f, -1f,   4.1f, -2.5f, -3f,     4.1f, -0.5f,  -3f  };
	private float windowVertLeft8 [] = {4.1f, -0.5f, 3f,       4.1f, -2.5f, 3f,    4.1f, -2.5f, 1f,      4.1f, -0.5f,  1f }; 
	
	// private float testWindowVert [] = {-4.2f, -1f, 2.5f,       -4.2f, -2f, 2.5f,    -4.2f, -2f, 1.5f,      -4.2f, -1f,  1.5f };
	// private float testWindowVert [] = {-1, -1, -1,		-1, 1, -1, 		1, 1, -1,		1, -1, -1 };
	
	// private Window [] windowarray = new Window [32];
	
	public GLRendererEX(Context context) {
		// tri1 = new GLTriangleEX(vertices1);
		// tri2 = new GLTriangleEX(vertices2);
		this.context = context;
		cube = new GLCube();
		// front face windows
		
		
		W1 = new Window(windowVert1);
		W2 = new Window(windowVert2);
		W3 = new Window(windowVert3);
		W4 = new Window(windowVert4);
		W5 = new Window(windowVert5);
		W6 = new Window(windowVert6);
		W7 = new Window(windowVert7);
		W8 = new Window(windowVert8);
		
		// Back face windows
		WB1 = new Window(windowVertBack1);
		WB2 = new Window(windowVertBack2);
		WB3 = new Window(windowVertBack3);
		WB4 = new Window(windowVertBack4);
		WB5 = new Window(windowVertBack5);
		WB6 = new Window(windowVertBack6);
		WB7 = new Window(windowVertBack7);
		WB8 = new Window(windowVertBack8);
		
		// Right face windows
		WR1 = new Window(windowVertRight1);
		WR2 = new Window(windowVertRight2);
		WR3 = new Window(windowVertRight3);
		WR4 = new Window(windowVertRight4);
		WR5 = new Window(windowVertRight5);
		WR6 = new Window(windowVertRight6);
		WR7 = new Window(windowVertRight7);
		WR8 = new Window(windowVertRight8);
		
		// Left face windows
		WL1 = new Window(windowVertLeft1);
		WL2 = new Window(windowVertLeft2);
		WL3 = new Window(windowVertLeft3);
		WL4 = new Window(windowVertLeft4);
		WL5 = new Window(windowVertLeft5);
		WL6 = new Window(windowVertLeft6);
		WL7 = new Window(windowVertLeft7);
		WL8 = new Window(windowVertLeft8);
		
		// testWindow = new Window(testWindowVert);
	}
	
	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig eglConfig) {
		
		cube.loadGLTexture(gl, this.context);
		
		W1.loadGLTexture(gl, this.context);
		W2.loadGLTexture(gl, this.context);
		W3.loadGLTexture(gl, this.context);
		W4.loadGLTexture(gl, this.context);
		W5.loadGLTexture(gl, this.context);
		W6.loadGLTexture(gl, this.context);
		W7.loadGLTexture(gl, this.context);
		W8.loadGLTexture(gl, this.context);
		
		WB1.loadGLTexture(gl, this.context);
		WB2.loadGLTexture(gl, this.context);
		WB3.loadGLTexture(gl, this.context);
		WB4.loadGLTexture(gl, this.context);
		WB5.loadGLTexture(gl, this.context);
		WB6.loadGLTexture(gl, this.context);
		WB7.loadGLTexture(gl, this.context);
		WB8.loadGLTexture(gl, this.context);
		
		WR1.loadGLTexture(gl, this.context);
		WR2.loadGLTexture(gl, this.context);
		WR3.loadGLTexture(gl, this.context);
		WR4.loadGLTexture(gl, this.context);
		WR5.loadGLTexture(gl, this.context);
		WR6.loadGLTexture(gl, this.context);
		WR7.loadGLTexture(gl, this.context);
		WR8.loadGLTexture(gl, this.context);
		
		WL1.loadGLTexture(gl, this.context);
		WL2.loadGLTexture(gl, this.context);
		WL3.loadGLTexture(gl, this.context);
		WL4.loadGLTexture(gl, this.context);
		WL5.loadGLTexture(gl, this.context);
		WL6.loadGLTexture(gl, this.context);
		WL7.loadGLTexture(gl, this.context);
		WL8.loadGLTexture(gl, this.context);
		
		gl.glEnable(GL10.GL_TEXTURE_2D);
		gl.glShadeModel(GL10.GL_SMOOTH);
		
		gl.glDisable(GL10.GL_DITHER);
		gl.glEnable(GL10.GL_DEPTH_TEST);
		gl.glDisable(GL10.GL_BLEND);
		gl.glDepthFunc(GL10.GL_LEQUAL);
		gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_FASTEST);
		gl.glClearColor(0.8f,  0f,  0.2f,  0.1f);
		gl.glClearDepthf(1f);
		
	}
	
	private boolean change1 = true;
	private boolean change2 = true;
	
	@Override
	public void onDrawFrame(GL10 gl) {
		gl.glDisable(GL10.GL_DITHER);
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
		
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadIdentity();
		GLU.gluLookAt(gl, 0, 0, -12, 0, 0, 0, 0, 2, 0);
		
		long time = SystemClock.uptimeMillis() % 4000L;
		float angle = .09f * ((int)time);
		
		// gl.glRotatef(angle, 1, 0, 0);
		gl.glRotatef(angle, 0, 1, 0);
		// gl.glRotatef(angle, 0, 0, 1);
		
		// gl.glTranslatef(0, angle/10, 0);
		
		// tri1.draw(gl);
		// tri2.draw(gl);
		// emulator test
		
		// first switching algorithm
		if (time > 2000 && change1 == true) {
			W1.loadGLTexture2(gl, this.context);
			WB3.loadGLTexture2(gl, this.context);
			WR5.loadGLTexture2(gl, this.context);
			WL7.loadGLTexture2(gl, this.context);
			change1 = false;
		}
		
		if (time < 2000 && change1 == false) {
			W1.loadGLTexture(gl, this.context);
			WB3.loadGLTexture(gl, this.context);
			WR5.loadGLTexture(gl, this.context);
			WL7.loadGLTexture(gl, this.context);
			change1 = true;
		}
		
		// second switching algorithm
		if (time < 2000 && change2 == false) {
			W2.loadGLTexture2(gl, this.context);
			WB4.loadGLTexture2(gl, this.context);
			WR6.loadGLTexture2(gl, this.context);
			WL8.loadGLTexture2(gl, this.context);
			change2 = true;
		}
		
		if (time > 2000 && change2 == true) {
			W2.loadGLTexture(gl, this.context);
			WB4.loadGLTexture(gl, this.context);
			WR6.loadGLTexture(gl, this.context);
			WL8.loadGLTexture(gl, this.context);
			change2 = false;
		}

		cube.draw(gl);
		
		// Front Face Windows
		W1.draw(gl);
		W2.draw(gl);
		W3.draw(gl);
		W4.draw(gl);
		W5.draw(gl);
		W6.draw(gl);
		W7.draw(gl);
		W8.draw(gl);
		
		// Back Face Windows
		WB1.draw(gl);
		WB2.draw(gl);
		WB3.draw(gl);
		WB4.draw(gl);
		WB5.draw(gl);
		WB6.draw(gl);
		WB7.draw(gl);
		WB8.draw(gl);
		
		// Right Face Windows
		WR1.draw(gl);
		WR2.draw(gl);
		WR3.draw(gl);
		WR4.draw(gl);
		WR5.draw(gl);
		WR6.draw(gl);
		WR7.draw(gl);
		WR8.draw(gl);
		
		// Right Face Windows
		WL1.draw(gl);
		WL2.draw(gl);
		WL3.draw(gl);
		WL4.draw(gl);
		WL5.draw(gl);
		WL6.draw(gl);
		WL7.draw(gl);
		WL8.draw(gl);
		// testWindow.setColour(testColours);
		// testWindow.draw(gl);
	}

	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		gl.glViewport(0, 0, width, height);
		float ratio = (float) width/height;
		gl.glMatrixMode(GL10.GL_PROJECTION);
		gl.glLoadIdentity();
		gl.glFrustumf(-ratio, ratio, -1, 1, 1, 25);
	}



}
