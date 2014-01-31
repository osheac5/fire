package com.example.fire;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.TextView;

public class HighScores extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_high_scores);
	}

	@Override  
	public void onBackPressed()   
	{    
		startActivity(new Intent(this,SplashScreen.class));
		finish();
		return;  
	}
	
	private void Stuff(){
        
		SharedPreferences scores = getSharedPreferences("Score", 0);
		int score = scores.getInt("Score1", 0);
		
		String highScores[] = {"Score1", "Score2", "Score3", "Score4", "Score5", "Score0"};
		System.out.println("Name: _1 = "  + scores.getInt(highScores[0], 0));
		System.out.println("Name: _2 = "  + scores.getInt(highScores[1], 0));
		System.out.println("Name: _3 = "  + scores.getInt(highScores[2], 0));
		System.out.println("Name: _4 = "  + scores.getInt(highScores[3], 0));
		System.out.println("Name: _5 = "  + scores.getInt(highScores[4], 0));
        
		TextView score0 = (TextView)findViewById(R.id.score1);
		score0.setText("1: " + scores.getInt(highScores[0], 0));
		
		TextView score1 = (TextView)findViewById(R.id.score2);
		score1.setText("2: " + scores.getInt(highScores[1], 0));	    

		TextView score2 = (TextView)findViewById(R.id.score3);
		score2.setText("3: " + scores.getInt(highScores[2], 0));
		
		TextView score3 = (TextView)findViewById(R.id.score4);
		score3.setText("4: " + scores.getInt(highScores[3], 0));
		
		TextView score4 = (TextView)findViewById(R.id.score5);
		score4.setText("5: " + scores.getInt(highScores[4], 0));
		
		int height = 0;
		int width = 0;
		int textSize = (49 * height)/720;
		
		ViewGroup.MarginLayoutParams myMargins = (MarginLayoutParams) score1.getLayoutParams();		
		myMargins.setMargins((int) ((width/5)),(int) (height/3.5),0,0);
		
		myMargins = (MarginLayoutParams) score2.getLayoutParams();
		myMargins.setMargins((int) ((width/5)),(int) (height/3.5 + textSize),0,0);
		
		myMargins = (MarginLayoutParams) score3.getLayoutParams();
		myMargins.setMargins((int) ((width/5)),(int) (height/3.5 + textSize*2),0,0);
		
		myMargins = (MarginLayoutParams) score4.getLayoutParams();
		myMargins.setMargins((int) ((width/5)),(int) (height/3.5 + textSize*3),0,0);
		
		myMargins = (MarginLayoutParams) score0.getLayoutParams();
		myMargins.setMargins((int) ((width/5)),(int) (height/3.5 + textSize*4),0,0);
			
		score1.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
		score2.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
		score3.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
		score4.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
		score0.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
	}
}