package com.example.fire;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLUtils;

public class Window {
	
	private float vertices [] = {
		    -1,  -1,   0,   // point 0 (bottom right)
			-1,   1,   0,   // point 1 (top right)
			 1,   1,   0,   // point 2 (top left)
			 1,  -1,   0    // point 3 (bottom left)
	};
		
	private float texture [] = {
			-1f,   1f,   // point 1 (top right)
			-1f,  -1f,   // point 0 (bottom right)
			 1f,  -1f,   // point 3 (bottom left)
			 1f,   1f,   // point 2 (top left)	
	};
	
	private float rgbaVals [] = {
			0, 0, 0, 1, 		// Color of first vertices
			0, 0, 0, 1, 		// Color of second vertices
			0, 0, 0, 1,			// Color of third vertices
			0, 0, 0, 1,			// Color of forth Vertex
			0, 0, 0, 1,			// Color of fifth vertices
			0, 0, 0, 1			// Color of sixth Vertex
	};
	
	private FloatBuffer vertBuff;
	private FloatBuffer colorBuff;
	private FloatBuffer textBuff;
	
	private short[] pIndex = {
			1, 0, 3,
			1, 3, 2
	};
	
	private ShortBuffer pBuff;
	
	// default constructor
	public Window () {
		
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
		
		// ByteBuffer cBuff = ByteBuffer.allocateDirect(rgbaVals.length * 4);
		// cBuff.order(ByteOrder.nativeOrder());
		// colorBuff = cBuff.asFloatBuffer();
		// colorBuff.put(rgbaVals);
		// colorBuff.position(0);
		
		ByteBuffer tbBuff = ByteBuffer.allocateDirect(texture.length * 4);
		tbBuff.order(ByteOrder.nativeOrder());
		textBuff = tbBuff.asFloatBuffer();
		textBuff.put(texture);
		textBuff.position(0);
	}
	
	// constructor that sets location and size of triangle
	public Window (float [] points) {
		
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
		
		// ByteBuffer cBuff = ByteBuffer.allocateDirect(rgbaVals.length * 4);
		// cBuff.order(ByteOrder.nativeOrder());
		// colorBuff = cBuff.asFloatBuffer();
		// colorBuff.put(rgbaVals);
		// colorBuff.position(0);
		
		ByteBuffer tbBuff = ByteBuffer.allocateDirect(texture.length * 4);
		tbBuff.order(ByteOrder.nativeOrder());
		textBuff = tbBuff.asFloatBuffer();
		textBuff.put(texture);
		textBuff.position(0);
	}
	
	private int [] textures = new int[1];
	
	public void loadGLTexture(GL10 gl, Context context) {
		Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.mushroom);
		
			// generate one texture pointer
		gl.glGenTextures(1, textures, 0);
			// bind the texture pointer to the array
		gl.glBindTexture(GL10.GL_TEXTURE_2D, textures[0]);
		
			// create nearest filtered texture
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_NEAREST);
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR);

			// Use Android GLUtils to specify a two-dimensional texture image from our bit map
		GLUtils.texImage2D(GL10.GL_TEXTURE_2D,  0,  bitmap,  0);
		
			// clean up (free memory (important))
		bitmap.recycle();
	}
	
	public void loadGLTexture2(GL10 gl, Context context) {
		Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_launcher);
		
			// generate one texture pointer
		gl.glGenTextures(1, textures, 0);
			// bind the texture pointer to the array
		gl.glBindTexture(GL10.GL_TEXTURE_2D, textures[0]);
		
			// create nearest filtered texture
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_NEAREST);
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR);

			// Use Android GLUtils to specify a two-dimensional texture image from our bit map
		GLUtils.texImage2D(GL10.GL_TEXTURE_2D,  0,  bitmap,  0);
		
			// clean up (free memory (important))
		bitmap.recycle();
	}

	
	public void draw (GL10 gl) {
		gl.glFrontFace(GL10.GL_CW);
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		// gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
		gl.glBindTexture(GL10.GL_TEXTURE_2D, textures[0]);
		gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
		// gl.glEnable(GL10.GL_BLEND);
		// gl.glEnableClientState(GL10.GL_BLEND);
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertBuff);
		// gl.glColorPointer(4, GL10.GL_FLOAT, 0, colorBuff);
		gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, textBuff);
		gl.glDrawElements(GL10.GL_TRIANGLES, pIndex.length, GL10.GL_UNSIGNED_SHORT, pBuff);
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
		// gl.glDisableClientState(GL10.GL_COLOR_ARRAY);
		gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
		// gl.glDisable(GL10.GL_BLEND);
		// gl.glDisableClientState(GL10.GL_BLEND);
	}
	
	public void setColour (float [] points) {
		if (points.length < rgbaVals.length) {
			for (int i = 0; i < points.length; i++) {
				rgbaVals [i] = points [i];
			}
		}
		else {
			for (int i = 0; i < rgbaVals.length; i++) {
				rgbaVals [i] = points [i];
			}
		}
		
		ByteBuffer cBuff = ByteBuffer.allocateDirect(rgbaVals.length * 4);
		cBuff.order(ByteOrder.nativeOrder());
		colorBuff = cBuff.asFloatBuffer();
		colorBuff.put(rgbaVals);
		colorBuff.position(0);
	}
}
