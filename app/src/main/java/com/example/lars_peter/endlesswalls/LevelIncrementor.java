package com.example.lars_peter.endlesswalls;

/**
 * Created by SharkGaming on 08/04/2017.
 */

public class LevelIncrementor extends Thread
{
    private long incrementLvlTimer;
    private long incrementDelay = 1000;
    private boolean countingUp;
    private boolean running;
    private TileManager tileManager;

    public LevelIncrementor()
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
        while(GameView.gameRunning)
        {
            countingUp = true;
            long currentTime = System.currentTimeMillis();
            incrementLvlTimer = currentTime + incrementDelay;

            while(countingUp)
            {
                currentTime = System.currentTimeMillis();

                    if(currentTime > incrementLvlTimer)
                    {
                        tileManager.incrementLevel();
                        countingUp = false;
                    }
            }
        }
    }
}

