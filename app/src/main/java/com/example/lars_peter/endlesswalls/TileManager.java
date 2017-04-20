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
    private ArrayList<Traps> traps;
    private int playerGap;
    private int tileGap;
    private int tileHeight;
    private int color;
    private long startTime;
    private int level = 1;
    private static LevelIncrementor lvlIncrementor;
    private static CollectableSpawner collectableSpawner;

    public  ArrayList<LevelWave> GetTiles()
    {
        return tiles;
    }

    public  ArrayList<LevelCollectable> GetCollectables()
    {
        return collectables;
    }

    public  ArrayList<Traps> GetTrap() {return traps;}

    private TileManager(int _playerGap, int _tileGap, int _tileHeight, int _color)
    {
        this.playerGap = _playerGap;
        this.tileGap = _tileGap;
        this.tileHeight = _tileHeight;
        this.color = _color;
        tiles = new ArrayList<>();
        createWaves();

        collectables = new ArrayList<>();
        traps = new ArrayList<>();
    }

    public static TileManager getInstance()
    {
        if(instance == null)
        {
            instance = new TileManager(250, 300, 75, Color.GRAY);

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

        for(LevelCollectable collectable : collectables)
        {
            if(collectable.active) {
                collectable.update();
            }
        }

        for(Traps _trap : traps)
        {
            if(_trap.active) {
                _trap.update();
            }
        }

        if(tiles.get(tiles.size()-1).getRect1().top > Constants.SCREEN_HEIGHT)
        {
            int xStart = ((int)(Math.random()* Constants.SCREEN_WIDTH - playerGap));
            tiles.add(0, new LevelWave(tileHeight, color, ((int)(Math.random()* Constants.SCREEN_WIDTH - playerGap)), tiles.get(0).getRect1().top - tileHeight - tileGap, playerGap));
            tiles.remove(tiles.size() -1);
        }
    }

    public void draw(Canvas _canvas)
    {
        for(LevelWave tile : tiles)
        {
            if(tile.active) {
                tile.draw(_canvas);
            }
        }

        for(LevelCollectable collectable : collectables)
        {
            if(collectable.active) {
                collectable.draw(_canvas);
            }
        }

        for(Traps _traps : traps)
        {
            if(_traps.active) {
                _traps.draw(_canvas);
            }
        }
    }

    public void Reset()
    {


        for(LevelCollectable _collectable: collectables)
        {
            _collectable.active = false;
        }

        for(Traps _trap: traps)
        {
            _trap.active = false;
        }

        for(LevelWave _wave: tiles)
        {
            _wave.active = false;
        }

        collectables.clear();
        traps.clear();

        instance = null;
    }
}



/*
        1. Traps
        2.Collision player til walls og player til collectables
        3. Når collectables samles op skal de despawne(har lavet koden for det og commenteret det ud i tilemanager klassen)
        4. spilleren dør når han rammer budnjlien*/

