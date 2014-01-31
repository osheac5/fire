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

public class GLCube {

	private float xscale = 4;
	private float yscale = 6.5f;
	private float zscale = 4;

	private float vertices[] = { -1 * xscale, 1 * yscale, -1 * zscale, // point
																		// 0
																		// (top
																		// right
																		// front)
			-1 * xscale, -1 * yscale, -1 * zscale, // point 1 (bottom right
													// front)
			1 * xscale, -1 * yscale, -1 * zscale, // point 2 (bottom left front)
			1 * xscale, 1 * yscale, -1 * zscale, // point 3 (top left front)
			-1 * xscale, 1 * yscale, 1 * zscale, // point 4 (top right back)
			-1 * xscale, -1 * yscale, 1 * zscale, // point 5 (bottom right back)
			1 * xscale, -1 * yscale, 1 * zscale, // point 6 (bottom left back)
			1 * xscale, 1 * yscale, 1 * zscale // point 7 (top left back)
	};

	private float texture[] = { -1f, 0f, -1f, // point 1 (top right)
			-1f, 1f, -1f, // point 0 (bottom right)
			0f, 1f, -1f, // point 3 (bottom left)
			0f, 0f, -1f, // point 2 (top left)
			0f, 0f, 1f, // point 1 (top right)
			0f, 1f, 1f, // point 0 (bottom right)
			-1f, 1f, 1f, // point 3 (bottom left)
			-1f, 0f, 1f, // point 2 (top left)
	};

	private FloatBuffer vertBuff;
	private FloatBuffer textBuff;

	private short[] pIndex = { 1, 2, 0, 1, 0, 5, 1, 5, 2, 3, 0, 2, 3, 2, 7, 3,
			7, 0, 6, 7, 2, 6, 5, 7, 6, 5, 2, 4, 5, 0, 4, 7, 5, 4, 0, 7, };

	private ShortBuffer pBuff;

	public GLCube() {

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

		ByteBuffer tbBuff = ByteBuffer.allocateDirect(texture.length * 4);
		tbBuff.order(ByteOrder.nativeOrder());
		textBuff = tbBuff.asFloatBuffer();
		textBuff.put(texture);
		textBuff.position(0);
	}

	public void draw(GL10 gl) {
		gl.glFrontFace(GL10.GL_CW);
		// gl.glEnable(GL10.GL_CULL_FACE);
		// gl.glCullFace(GL10.GL_BACK);

		gl.glBindTexture(GL10.GL_TEXTURE_2D, textures[0]);

		gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);

		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertBuff);
		gl.glTexCoordPointer(3, GL10.GL_FLOAT, 0, textBuff);

		gl.glDrawElements(GL10.GL_TRIANGLES, pIndex.length,
				GL10.GL_UNSIGNED_SHORT, pBuff);

		gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
	}

	private int[] textures = new int[1];

	public void loadGLTexture(GL10 gl, Context context) {
		Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(),
				R.drawable.building);

		// generate one texture pointer
		gl.glGenTextures(1, textures, 0);
		// bind the texture pointer to the array
		gl.glBindTexture(GL10.GL_TEXTURE_2D, textures[0]);

		// create nearest filtered texture
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER,
				GL10.GL_NEAREST);
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER,
				GL10.GL_LINEAR);

		// Use Android GLUtils to specify a two-dimensional texture image from
		// our bit map
		GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmap, 0);

		// clean up (free memory (important))
		bitmap.recycle();
	}

}
