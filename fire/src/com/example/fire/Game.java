package com.example.fire;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Game extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
	}
	
	public void gameOver(View view) {
    	Intent intent = new Intent(this, GameOver.class);
    	startActivity(intent);
    }

}
