
package com.sanket.funmath;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Score extends ActionBarActivity {

    public static final String DIFFICULTY="difficulty";
    public static final String HIGHFOREASY="HighScoreForEasy";
    public static final String HIGHFORHARD="HighScoreForHard";
    public static final String HIGHFORHARDEST="HighScoreForHardest";
    public static final String HIGHSCOREFILE="highhhh";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences high_score=this.getSharedPreferences(HIGHSCOREFILE,0);
        final String SCORE="score";
        setContentView(R.layout.activity_score);
        TextView final_sc,highscore;
        highscore=(TextView)findViewById(R.id.tvHighScore);
        final_sc=(TextView)findViewById(R.id.final_score);
        int scoree;
        Bundle extraa=getIntent().getExtras();


        scoree=extraa.getInt(SCORE);
        int diff=extraa.getInt(DIFFICULTY);
        final_sc.setText("Score:"+scoree);
        if(diff==5)
        {
            int high=high_score.getInt(HIGHFOREASY,0);
            highscore.setText("High:"+high);
        }
        if(diff==10)
        {
            int high=high_score.getInt(HIGHFORHARD,0);
            highscore.setText("High:"+high);
        }
        if(diff==30)
        {
            int high=high_score.getInt(HIGHFORHARDEST,0);
            highscore.setText("High:"+high);
        }

        Button new_game;

        new_game=(Button)findViewById(R.id.bNewGame);
        new_game.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent i=new Intent(Score.this,Difficulty_Select.class);
                startActivity(i);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.score, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
