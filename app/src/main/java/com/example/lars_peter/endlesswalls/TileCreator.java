package com.example.lars_peter.endlesswalls;

/**
 * Created by SharkGaming on 09/04/2017.
 */

public class TileCreator implements Runnable
{
    TileManager tileManager;

    public TileCreator()
    {
        super();
        tileManager = TileManager.getInstance();
    }

    @Override
    public void run()
    {
  /*          Thread.sleep(1000);*//*
            *//*int currentY = -5 * Constants.SCREEN_HEIGHT / 4;*//*
z
        int currentY = -5 * Constants.SCREEN_HEIGHT / 4;

        while(currentY < 0)
        {
            int xStart = ((int)(Math.random()* Constants.SCREEN_WIDTH - tileManager.playerGap));
            tileManager.tiles.add(new LevelTile(tileManager.tileHeight, tileManager.color, xStart, currentY, tileManager.playerGap));
            currentY += tileManager.tileHeight + tileManager.tileGap;
        }
*/
    }
}

// Egne noter omkring: implements Runnable vs extends Thread

/*     When you extends Thread class, after that you can’t extend any other class which you required. (As you know, Java does not allow inheriting more than one class).
        When you implements Runnable, you can save a space for your class to extend any other class in future or now.
        However, one significant difference between implementing Runnable and extending Thread is that
        by extending Thread, each of your threads has a unique object associated with it, whereas implementing Runnable, many threads can share the same object instance.*/
