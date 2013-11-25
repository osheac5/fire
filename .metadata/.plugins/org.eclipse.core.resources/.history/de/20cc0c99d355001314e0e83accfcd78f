package com.example.fire;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SplashScreen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
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
