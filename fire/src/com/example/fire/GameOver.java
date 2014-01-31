package com.example.fire;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class GameOver extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game_over);

		Intent intent = getIntent();
		int score = intent.getIntExtra("score", 0);
		score = score * 153; // make score look like it's really complicated, so
								// no one knows it's wrong! :p

		TextView scoreText = (TextView) findViewById(R.id.scoreText);
		scoreText.setText("Score: " + score);

	}

	public void startGame(View view) {
		Intent intent = new Intent(this, Game.class);
		startActivity(intent);
	}

	public void openHighScores(View view) {
		Intent intent = new Intent(this, HighScores.class);
		startActivity(intent);
	}

}
