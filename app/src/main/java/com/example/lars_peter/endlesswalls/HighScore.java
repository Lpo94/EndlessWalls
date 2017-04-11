package com.example.lars_peter.endlesswalls;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class HighScore extends AppCompatActivity {

    public static int counter = 0 ;
    public int highScore;
    private SharedPreferences settings;
    private SharedPreferences.Editor editor;
    private static final int PREFERENCE_MODE_PRIVATE=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        settings = getPreferences(PREFERENCE_MODE_PRIVATE);
        highScore = settings.getInt("number", -1);
//        Button clicker = (Button) findViewById(R.id.Start_button);
//
//        clicker.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                new CountDownTimer(100000, 100) {
//                    public void onTick(long millisUntilFinished)
//                    {
//                        counter++;
//                    }
//                    public void onFinish(){
//
//
//                        if (counter > highScore) {
//
//                            highScore = counter;
//
//                            settings = getPreferences(PREFERENCE_MODE_PRIVATE);
//                            editor = settings.edit();
//                            editor.putInt("number", highScore);
//                            editor.commit();
//
//                        }
//                    }
//                }.start();
//            }
//        });


    }

    public void update()
    {
        CountDown();
    }

    public void draw(Canvas _canvas)
    {
        Paint paint = new Paint();

        paint.setColor(Color.BLACK);
        paint.setTextSize(50);
        _canvas.drawText("SCORE :" + counter, 100, 100, paint);

        _canvas.drawText("HIGH SCORE :" + highScore, 500, 100, paint);
    }

    public void CountDown()
    {
        counter++;
    }

    public void EndGame()
    {
        if(counter > highScore)
        {
            highScore = counter;
            settings = getPreferences(PREFERENCE_MODE_PRIVATE);
            editor = settings.edit();
            editor.putInt("number", highScore);
            editor.commit();
        }
        counter = 0;
    }

}
