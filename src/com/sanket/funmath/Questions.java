package com.sanket.funmath;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Random;



public class Questions extends ActionBarActivity {
    String sign[]={" ","+","-","*","/"};
    float percenttime;

    int Qsign1=0,Qsign2=0;
    long[]pattern={100,100,100,100,100,100,100};
    int flagforvibrate=0;
    public static final String DIFFICULTY="difficulty";
    public static final String HIGHSCOREFILE="highhhh";
    public static final String SCORE="score";
    public static final String HIGHFOREASY="HighScoreForEasy";
    public static final String HIGHFORHARD="HighScoreForHard";
    public static final String HIGHFORHARDEST="HighScoreForHardest";
    int flag=0;
    SharedPreferences high_score;
    Vibrator vibratee;
    int counter=0;
    ProgressBar timer;
    TextView ViewQuestion,score;
    ImageButton add,sub,mul,div;
    CountDownTimer tim;
    int time_elapsed=0;
    int max_time=8000;
    public class quese
    {
        private int ans=0;
        int diff;
        Random rn = new Random();Random rs = new Random();
        private int num1=0,num2=0,num3=0,psign1=0,psign2=0;
        public String ques="";
        public String answer="";
        quese(int Difficulty_Level)
        {
            diff=Difficulty_Level;
        }
        public void setDifficulty(int a)
        {
            diff=a;
        }
        public void set()
        {

            num1 = rn.nextInt(7) + diff;
            num2 = rn.nextInt(7) + diff;
            num3 = rn.nextInt(7) + diff;
            psign1=rs.nextInt(4)+1;
            psign2=rs.nextInt(4)+1;
            if(psign2==4)
            {
                num2=num2-(num2%num3)+(2*num3);
            }
            if(psign1==4)
            {
                num1=num1-(num1%num2)+(2*num2);
            }
            if (psign1==1&&psign2==1)
                ans=num1+num2+num3;
            else if (psign1==1&&psign2==2)
                ans=num1+num2-num3;
            else if (psign1==1&&psign2==3)
                ans=num1+num2*num3;
            else if (psign1==1&&psign2==4)
                ans=num1+num2/num3;
            else if (psign1==2&&psign2==1)
                ans=num1-num2+num3;
            else if (psign1==2&&psign2==2)
                ans=num1-num2-num3;
            else if (psign1==2&&psign2==3)
                ans=num1-num2*num3;
            else if (psign1==2&&psign2==4)
                ans=num1-num2/num3;
            else if (psign1==3&&psign2==1)
                ans=num1*num2+num3;
            else if (psign1==3&&psign2==2)
                ans=num1*num2-num3;
            else if (psign1==3&&psign2==3)
                ans=num1*num2*num3;
            else if (psign1==3&&psign2==4)
                ans=num1*num2/num3;
            else if (psign1==4&&psign2==1)
                ans=num1/num2+num3;
            else if (psign1==4&&psign2==2)
                ans=num1/num2-num3;
            else if (psign1==4&&psign2==3)
                ans=num1/num2*num3;
            else if (psign1==4&&psign2==4)
                ans=num1/(num2/num3);
            ques=(""+num1+"_"+num2+"_"+num3+"="+ans);
            answer=(""+num1+sign[psign1]+num2+sign[psign2]+num3+"="+ans);
        }

        public String getq()
        {
            return ques;
        }

        public String getanswer()
        {
            return answer;
        }

        public int getsign1()
        {
            return psign1;
        }

        public int getsign2()
        {
            return psign2;
        }

        public int getans()
        {
            return ans;
        }
        public boolean iscorrect(int sign1, int sign2)
        {
            int u_ans=32999;
            if (sign1==1&&sign2==1)
                u_ans=num1+num2+num3;
            else if (sign1==1&&sign2==2)
                u_ans=num1+num2-num3;
            else if (sign1==1&&sign2==3)
                u_ans=num1+num2*num3;
            else if (sign1==1&&sign2==4)
                u_ans=num1+num2/num3;
            else if (sign1==2&&sign2==1)
                u_ans=num1-num2+num3;
            else if (sign1==2&&sign2==2)
                u_ans=num1-num2-num3;
            else if (sign1==2&&sign2==3)
                u_ans=num1-num2*num3;
            else if (sign1==2&&sign2==4)
                u_ans=num1-num2/num3;
            else if (sign1==3&&sign2==1)
                u_ans=num1*num2+num3;
            else if (sign1==3&&sign2==2)
                u_ans=num1*num2-num3;
            else if (sign1==3&&sign2==3)
                u_ans=num1*num2*num3;
            else if (sign1==3&&sign2==4)
                u_ans=num1*num2/num3;
            else if (sign1==4&&sign2==1)
                u_ans=num1/num2+num3;
            else if (sign1==4&&sign2==2)
                u_ans=num1/num2-num3;
            else if (sign1==4&&sign2==3)
                u_ans=num1/num2*num3;
            else if (sign1==4&&sign2==4)
                u_ans=num1/(num2/num3);
            return(ans==u_ans);
        }

    }
    int diffff=7;
    quese Question=new quese(diffff);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);
        high_score=this.getSharedPreferences(HIGHSCOREFILE,0);
        vibratee=(Vibrator)this.getSystemService(Context.VIBRATOR_SERVICE);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            diffff = extras.getInt(DIFFICULTY);
        }
        Question.setDifficulty(diffff);
        kaam();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.questions, menu);
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

    public void final_score()
    {

        Intent go_to_score=new Intent(Questions.this,Score.class);
        go_to_score.putExtra(SCORE, counter);
        go_to_score.putExtra(DIFFICULTY, diffff);
        if (diffff==5)
        {
            int oldScore=high_score.getInt(HIGHFOREASY, 0);
            if(counter>oldScore)
            {
                Editor edit=high_score.edit();
                edit.putInt(HIGHFOREASY, counter);
                edit.commit();

            }
        }
        else if(diffff==10)
        {
            int oldScore=high_score.getInt(HIGHFORHARD, 0);
            if(counter>oldScore)
            {
                Editor edit=high_score.edit();
                edit.putInt(HIGHFORHARD, counter);
                edit.commit();

            }
        }
        else
        {
            int oldScore=high_score.getInt(HIGHFORHARDEST, 0);
            if(counter>oldScore)
            {
                Editor edit=high_score.edit();
                edit.putInt(HIGHFORHARDEST, counter);
                edit.commit();

            }
        }

        startActivity(go_to_score);
    }



    public void kaam()
    {

        ViewQuestion=(TextView)findViewById(R.id.tvQuestion);
        add=(ImageButton)findViewById(R.id.ibPlus);
        sub=(ImageButton)findViewById(R.id.ibMinus);
        mul=(ImageButton)findViewById(R.id.ibMultiply);
        div=(ImageButton)findViewById(R.id.ibDivide);
        score=(TextView)findViewById(R.id.tvScore);
        timer=(ProgressBar)findViewById(R.id.TimerBar);
        timer.setProgress(time_elapsed);
        timer.setIndeterminate(false);
        timer.setMax(max_time);

        tim=new CountDownTimer(max_time,20) {

            @Override
            public void onTick(long millisUntilFinished) {
                // TODO Auto-generated method stub
                time_elapsed=time_elapsed+20;
                timer.setProgress(time_elapsed);
            }

            @Override
            public void onFinish() {
                // TODO Auto-generated method stub
                time_elapsed=time_elapsed+100;
                timer.setProgress(time_elapsed);
                time_elapsed=0;
                flagforvibrate=0;
                vibratee.vibrate(pattern,-1);
                ;
                ViewQuestion.setText(Question.getanswer());
                final_score();


            }
        };
        tim.start();

        Question.set();
        score.setText("Score:"+counter);
        ViewQuestion.setText(Question.getq());
        add.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                vibratee.vibrate(100);
                if(flag==0)
                {
                    Qsign1=1;
                    flag=1;
                }

                else if(flag==1)
                {
                    Qsign2=1;
                    if(Question.iscorrect(Qsign1,Qsign2))
                    {counter++;
                    ;
                    tim.cancel();

                    flagforvibrate=0;
                    ;
                    time_elapsed=0;
                    max_time*=0.80;
                    tim.start();
                    }
                    else
                    {
                        ViewQuestion.setText(Question.getanswer());
                        time_elapsed=0;;
                        ;
                        tim.cancel();

                        flagforvibrate=0;
                        ;
                        time_elapsed=0;
                        final_score();
                    }
                    score.setText("Score:"+counter);
                    Qsign1=0;
                    Qsign2=0;
                    flag=0;
                    Question.set();
                    ViewQuestion.setText(Question.getq());
                }

            }
        });
        sub.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                vibratee.vibrate(100);
                if(flag==0)
                {
                    Qsign1=2;
                    flag=1;
                }

                else if(flag==1)
                {
                    Qsign2=2;
                    if(Question.iscorrect(Qsign1,Qsign2))
                    {counter++;
                    ;
                    tim.cancel();

                    flagforvibrate=0;
                    ;
                    time_elapsed=0;
                    max_time*=0.95;
                    tim.start();
                    }
                    else
                    {


                        ;
                        tim.cancel();

                        flagforvibrate=0;
                        ;
                        time_elapsed=0;
                        time_elapsed=0;
                        ViewQuestion.setText(Question.getanswer());
                        final_score();
                    }
                    score.setText("Score:"+counter);
                    Qsign1=0;
                    Qsign2=0;
                    flag=0;
                    Question.set();
                    ViewQuestion.setText(Question.getq());
                    score.setText("Score:"+counter);
                }

            }
        });
        mul.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                vibratee.vibrate(100);
                if(flag==0)
                {
                    Qsign1=3;
                    flag=1;
                }

                else if(flag==1)
                {
                    Qsign2=3;
                    if(Question.iscorrect(Qsign1,Qsign2))
                    {counter++;

                    ;
                    tim.cancel();

                    flagforvibrate=0;
                    ;
                    time_elapsed=0;
                    max_time*=0.95;
                    tim.start();
                    }
                    else
                    {


                        ;
                        tim.cancel();

                        flagforvibrate=0;
                        ;
                        time_elapsed=0;
                        time_elapsed=0;
                        ViewQuestion.setText(Question.getanswer());
                        final_score();
                    }
                    score.setText("Score:"+counter);
                    Qsign1=0;
                    Qsign2=0;
                    flag=0;
                    Question.set();
                    ViewQuestion.setText(Question.getq());
                }
            }
        });
        div.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                vibratee.vibrate(100);
                if(flag==0)
                {
                    Qsign1=4;
                    flag=1;
                }

                else if(flag==1)
                {
                    Qsign2=4;
                    if(Question.iscorrect(Qsign1,Qsign2))
                    {counter++;
                    ;
                    tim.cancel();

                    flagforvibrate=0;
                    ;
                    time_elapsed=0;
                    max_time*=0.95;
                    tim.start();
                    }
                    else
                    {


                        ;
                        tim.cancel();

                        flagforvibrate=0;
                        ;
                        time_elapsed=0;
                        time_elapsed=0;
                        ViewQuestion.setText(Question.getanswer());
                        final_score();
                    }
                    score.setText("Score:"+counter);
                    Qsign1=0;
                    Qsign2=0;
                    flag=0;
                    Question.set();
                    ViewQuestion.setText(Question.getq());
                }

            }
        });
    }
}
