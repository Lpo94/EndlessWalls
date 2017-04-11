package com.example.lars_peter.endlesswalls;

import android.graphics.Color;

/**
 * Created by SharkGaming on 11/04/2017.
 */

public class CollectableSpawner extends Thread
{
    private long incrementLvlTimer;
    private long incrementDelay = 5000;
    private boolean countingUp;
    private boolean running;
    private TileManager tileManager;

    public CollectableSpawner()
    {
        super();
        tileManager = TileManager.getInstance();
    }

    public void setRunning(boolean _running)
    {
        running = _running;
    }

    @Override
    public void run()
    {
        while(running)
        {
            countingUp = true;
            long currentTime = System.currentTimeMillis();
            incrementLvlTimer = currentTime + incrementDelay;

            while(countingUp)
            {
                currentTime = System.currentTimeMillis();

                if(currentTime > incrementLvlTimer)
                {
                    tileManager.getCollectables().add(new LevelCollectable(Color.GREEN));
                    countingUp = false;
                }
            }
        }
    }
}