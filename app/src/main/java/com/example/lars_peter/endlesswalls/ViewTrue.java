package com.example.lars_peter.endlesswalls;

import android.content.Context;
import android.view.SurfaceView;


public class ViewTrue extends SurfaceView implements Runnable {

    volatile boolean playing;
    Thread gameTread = null;


    public ViewTrue(Context context) {
        super(context);
    }

    @Override
    public void run() {
        while(playing) {

            update();
            draw();
            control();


        }

    }

    private void update() {

    }


    private void draw() {

    }


    private void control() {

    }

    public void pause(){
        playing = false;
        try{
            gameTread.join();
        } catch (InterruptedException e) {}
    }

    public void resume() {
        playing = true;
        gameTread = new Thread(this);
        gameTread.start();
    }
}

