package com.example.lars_peter.endlesswalls;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by SharkGaming on 07/04/2017.
 */

public class GameView extends SurfaceView implements SurfaceHolder.Callback
{
    private GameThread gameThreadThread;
    private TileManager tileManager;
    HighScore ScoreHigh = new HighScore();

    public GameView(Context _context)
    {
        super(_context);

        getHolder().addCallback(this);
        gameThreadThread = new GameThread(getHolder(), this);
        tileManager = TileManager.getInstance();

        setFocusable(true);
    }


    @Override
    public void surfaceCreated(SurfaceHolder _holder)
    {
        gameThreadThread = new GameThread(getHolder(), this);
        gameThreadThread.setRunning(true);
        gameThreadThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder _holder, int _format, int _width, int _height)
    {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder _holder)
    {
        boolean retry = true;
        while(true)
        {
            try
            {
                gameThreadThread.setRunning(false);
                gameThreadThread.join();
            }
            catch (Exception e) {e.printStackTrace();}
            retry = false;
        }
    }

    public void update()
    {
        tileManager.update();
    }

    @Override
    public void draw(Canvas _canvas)
    {
        super.draw(_canvas);
        _canvas.drawColor(Color.WHITE);

        tileManager.draw(_canvas);
        ScoreHigh.draw(_canvas);
    }
}
