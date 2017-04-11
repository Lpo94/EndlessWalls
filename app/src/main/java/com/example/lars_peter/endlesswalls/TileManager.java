package com.example.lars_peter.endlesswalls;

import android.graphics.Canvas;
import android.graphics.Color;

import java.util.ArrayList;

/**
 * Created by SharkGaming on 07/04/2017.
 */

public class TileManager
{
    private static TileManager instance;

    private ArrayList<LevelWave> tiles;
    private ArrayList<LevelCollectable> collectables;
    private int playerGap;
    private int tileGap;
    private int tileHeight;
    private int color;
    private long startTime;
    private int level = 1;
    private static LevelIncrementor lvlIncrementor;
    private static CollectableSpawner collectableSpawner;


    public TileManager(int _playerGap, int _tileGap, int _tileHeight, int _color)
    {
        this.playerGap = _playerGap;
        this.tileGap = _tileGap;
        this.tileHeight = _tileHeight;
        this.color = _color;
        tiles = new ArrayList<>();
        createWaves();

        collectables = new ArrayList<>();
    }

    public static TileManager getInstance()
    {
        if(instance == null)
        {
            instance = new TileManager(250, 300, 35, Color.GRAY);

            lvlIncrementor = new LevelIncrementor();
            lvlIncrementor.setRunning(true);
            lvlIncrementor.start();

            collectableSpawner = new CollectableSpawner();
            collectableSpawner.setRunning(true);
            collectableSpawner.start();
        }
        return instance;
    }

    private void createWaves()
    {
        int currentY = -5 * Constants.SCREEN_HEIGHT / 4;

        while(currentY < 0)
        {
            int xStart = ((int)(Math.random()* Constants.SCREEN_WIDTH - playerGap));
            tiles.add(new LevelWave(tileHeight, color, xStart, currentY, playerGap));
            currentY += tileHeight + tileGap;
        }
    }

    public void incrementLevel()
    {
        level++;
    }
    public int getLevel()
    {
        return level;
    }
    public ArrayList getCollectables() {return collectables;}

    public void update()
    {
        int elapsedTime = (int)(System.currentTimeMillis() - startTime);
        startTime = System.currentTimeMillis();
        float speed = Constants.SCREEN_HEIGHT/100000.0f;
        speed = speed * (level / 8);

        for(LevelWave tile : tiles)
        {
            tile.incrementY(speed * elapsedTime);
        }

        if(tiles.get(tiles.size()-1).getRect1().top > Constants.SCREEN_HEIGHT)
        {
            int xStart = ((int)(Math.random()* Constants.SCREEN_WIDTH - playerGap));
            tiles.add(0, new LevelWave(tileHeight, color, xStart , tiles.get(0).getRect1().top + tileHeight - tileGap, playerGap));
            tiles.remove(tiles.size() -1);
        }

       for(LevelCollectable collectable : collectables)
        {
           // hvis player collision tileManager.getCollectables().remove(collectable); i player klassens collision
        }
    }

    public void draw(Canvas _canvas)
    {
        for(LevelWave tile : tiles)
        {
            tile.draw(_canvas);
        }

        for(LevelCollectable collectable : collectables)
        {
           collectable.draw(_canvas);
        }
    }
}
