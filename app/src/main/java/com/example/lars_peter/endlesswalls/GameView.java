package com.example.lars_peter.endlesswalls;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;

/**
 * Created by SharkGaming on 07/04/2017.
 */

public class GameView extends SurfaceView implements SurfaceHolder.Callback
{
    private GameThread gameThreadThread;
    private TileManager tileManager;
    private HighScore highScore = new HighScore();
    private ArrayList<Enemy> enemyList;
    private Context context;
    private Player player;
    private OrientationData orientationData;

    private long frameTime;

    public static boolean gameRunning = true;


    public GameView(Context _context)
    {
        super(_context);
        gameRunning = true;
        getHolder().addCallback(this);
        gameThreadThread = new GameThread(getHolder(), this);
        tileManager = TileManager.getInstance();
        player = new Player(new Point(Constants.SCREEN_WIDTH/2,Constants.SCREEN_HEIGHT-(Constants.SCREEN_HEIGHT/10)));
        player.SetplayerAlive(true);
        orientationData = new OrientationData(_context);
        orientationData.register();
        setFocusable(true);
        enemyList = new ArrayList<>();
        context = _context;
    }


    @Override
    public void surfaceCreated(SurfaceHolder _holder)
    {
        gameThreadThread = new GameThread(getHolder(), this);
        gameThreadThread.setRunning(true);
        gameThreadThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height)
    {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder _holder)
    {
        while(true)
        {
            try
            {
                gameThreadThread.setRunning(false);
                gameThreadThread.join();
            }
            catch (Exception e) {e.printStackTrace();}
        }
    }

    public void update()
    {
        if(gameRunning) {
            tileManager.update();
            int elapsedTime = (int) (System.currentTimeMillis() - frameTime);
            frameTime = System.currentTimeMillis();
            if (orientationData.getOrientation() != null && orientationData.getStartOrientation() != null) {
                float pitch = orientationData.getOrientation()[1] - orientationData.getStartOrientation()[1];
                float roll = orientationData.getOrientation()[2] - orientationData.getStartOrientation()[2];

                player.xSpeed = 2 * roll * Constants.SCREEN_WIDTH / 500f;
                player.ySpeed = pitch * Constants.SCREEN_HEIGHT / 1000f;

                player.GetPos().x += Math.abs(player.xSpeed * elapsedTime) > 5 ? player.xSpeed * elapsedTime : 0;
                player.GetPos().y -= Math.abs(player.ySpeed * elapsedTime) > 10 ? player.ySpeed * elapsedTime : 0;
            }
            player.update();
            highScore.update();
            CheckCollision();
        }
//
//        if(player.GetplayerAlive() == false)
//        {
//            highScore.EndGame();
//            Intent i = new Intent(context, Menu.class);
//            context.startActivity(i);
//            gameRunning = false;
//        }

    }

    @Override
    public void draw(Canvas _canvas)
    {
        super.draw(_canvas);
        _canvas.drawColor(Color.WHITE);
        player.Draw(_canvas);
        tileManager.draw(_canvas);
        highScore.draw(_canvas);
    }

    public void CheckCollision()
    {
        for(LevelWave tile : tileManager.GetTiles())
        {
            player.PlayerCollision(tile);
        }

        for(LevelCollectable collectable : tileManager.Getcollectables())
        {
            player.PlayerCollision(collectable);
        }

        for(Enemy enemy : enemyList)
        {
            player.PlayerCollision(enemy);
        }
    }
}