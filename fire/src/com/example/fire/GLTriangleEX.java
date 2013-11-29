package com.example.fire;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;

public class GLTriangleEX {

	private float vertices [] = {
			0f, 1f,  // point 0
			1f, -1f, // point 1
			-1f, -1f // point 2
	};
	
	private float rgbaVals [] = {
			1, 1, 0, 0.5f, 		// Color of first vertices
			0.25f, 0, 0.85f, 1, // Color of second vertices
			0, 1, 1, 1			// Color of third vertices
	};
	
	private FloatBuffer vertBuff;
	private FloatBuffer colorBuff;
	
	private short[] pIndex = {0, 1, 2};
	
	private ShortBuffer pBuff;
	
	// default constructor
	public GLTriangleEX () {
		
		ByteBuffer bBuff = ByteBuffer.allocateDirect(vertices.length * 4);
		bBuff.order(ByteOrder.nativeOrder());
		vertBuff = bBuff.asFloatBuffer();
		vertBuff.put(vertices);
		vertBuff.position(0);
		
		ByteBuffer pbBuff = ByteBuffer.allocateDirect(pIndex.length * 2);
		pbBuff.order(ByteOrder.nativeOrder());
		pBuff = pbBuff.asShortBuffer();
		pBuff.put(pIndex);
		pBuff.position(0);
		
		ByteBuffer cBuff = ByteBuffer.allocateDirect(rgbaVals.length * 4);
		cBuff.order(ByteOrder.nativeOrder());
		colorBuff = cBuff.asFloatBuffer();
		colorBuff.put(rgbaVals);
		colorBuff.position(0);
	}
	
	// constructor that sets location and size of triangle
	public GLTriangleEX (float [] points) {
		
		if (points.length < vertices.length) {
			for (int i = 0; i < points.length; i++) {
				vertices [i] = points [i];
			}
		}
		else {
			for (int i = 0; i < vertices.length; i++) {
				vertices [i] = points [i];
			}
		}
		
		ByteBuffer bBuff = ByteBuffer.allocateDirect(vertices.length * 4);
		bBuff.order(ByteOrder.nativeOrder());
		vertBuff = bBuff.asFloatBuffer();
		vertBuff.put(vertices);
		vertBuff.position(0);
		
		ByteBuffer pbBuff = ByteBuffer.allocateDirect(pIndex.length * 2);
		pbBuff.order(ByteOrder.nativeOrder());
		pBuff = pbBuff.asShortBuffer();
		pBuff.put(pIndex);
		pBuff.position(0);
		
		ByteBuffer cBuff = ByteBuffer.allocateDirect(rgbaVals.length * 4);
		cBuff.order(ByteOrder.nativeOrder());
		colorBuff = cBuff.asFloatBuffer();
		colorBuff.put(rgbaVals);
		colorBuff.position(0);
	}
	
	public void draw (GL10 gl) {
		gl.glFrontFace(GL10.GL_CW);
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
		// gl.glEnable(GL10.GL_BLEND);
		// gl.glEnableClientState(GL10.GL_BLEND);
		gl.glVertexPointer(2, GL10.GL_FLOAT, 0, vertBuff);
		gl.glColorPointer(4, GL10.GL_FLOAT, 0, colorBuff);
		gl.glDrawElements(GL10.GL_TRIANGLES, pIndex.length, GL10.GL_UNSIGNED_SHORT, pBuff);
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glDisableClientState(GL10.GL_COLOR_ARRAY);
		// gl.glDisable(GL10.GL_BLEND);
		// gl.glDisableClientState(GL10.GL_BLEND);
	}

	// this function may not be necessary
	public void setVertices (float [] points) {
		
		if (points.length < vertices.length) {
			for (int i = 0; i < points.length; i++) {
				vertices [i] = points [i];
			}
		}
		else {
			for (int i = 0; i < vertices.length; i++) {
				vertices [i] = points [i];
			}
		}
	}
}
