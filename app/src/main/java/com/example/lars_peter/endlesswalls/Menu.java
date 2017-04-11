package com.example.lars_peter.endlesswalls;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import java.util.Calendar;

public class Menu extends AppCompatActivity {

    static MediaPlayer music;
    RelativeLayout currentLayout;
    static Calendar c = Calendar.getInstance();
    static int hour = c.get(Calendar.HOUR_OF_DAY);
    Vibrator vibrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
    }

    public void Start(View v)
    {
        vibrator.vibrate(50);
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    public void Exit(View v)
    {
        vibrator.vibrate(50);
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(music == null) {
            music = MediaPlayer.create(this, R.raw.music);
            music.setLooping(true);
            music.setVolume(0.1f, 0.1f);
            music.start();
        }
        currentLayout = (RelativeLayout) findViewById(R.id.main_layout);

        if(hour >= 6 && hour < 20) {
            currentLayout.setBackgroundColor(Color.RED);
        }

        else if((hour >= 20 && hour < 24) || (hour >= 0 && hour < 6))
        {
            currentLayout.setBackgroundColor(Color.BLUE);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        music.start();
    }
}
