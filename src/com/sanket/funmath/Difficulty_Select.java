package com.sanket.funmath;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class Difficulty_Select extends ActionBarActivity {

    public static final String DIFFICULTY="difficulty";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_difficulty__select);
        Button easy,hard,hardest;
        easy=(Button) findViewById(R.id.d_easy);
        hard=(Button)findViewById(R.id.d_hard);
        hardest=(Button)findViewById(R.id.d_hardest);
        easy.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent easy=new Intent(Difficulty_Select.this,Questions.class);
                int k=5;
                easy.putExtra(DIFFICULTY, k);
                startActivity(easy);
            }
        });
        hard.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent hard=new Intent(Difficulty_Select.this,Questions.class);
                int y=10;
                hard.putExtra(DIFFICULTY,y);
                startActivity(hard);
            }
        });
        hardest.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent hardest=new Intent(Difficulty_Select.this,Questions.class);
                int u=30;
                hardest.putExtra(DIFFICULTY, u);
                startActivity(hardest);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.difficulty__select, menu);
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
