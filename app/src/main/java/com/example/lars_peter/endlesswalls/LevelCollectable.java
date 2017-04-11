package com.example.lars_peter.endlesswalls;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Color;
import java.util.Random;

/**
 * Created by SharkGaming on 10/04/2017.
 */

public class LevelCollectable implements LevelObject
{

    private int color;
    private Rect rect1;
    private Random rnd = new Random();
    private TileManager tileManager;
    private int lifetime;

    public LevelCollectable(int _color)
    {
        super();
        tileManager = TileManager.getInstance();
        this.color = _color;

        int rndLeft = rnd.nextInt(Constants.SCREEN_WIDTH);
        int rndTop = rnd.nextInt(Constants.SCREEN_HEIGHT);
        int right = rndLeft + 50;
        int bottom = rndTop + 50;

        // left, top, right, bottom
        rect1 = new Rect(rndLeft, rndTop, right, bottom);
    }

    @Override
    public Rect getRect1()
    {
        return rect1;
    }


    @Override
    public void draw(Canvas _canvas)
    {
        Paint paint = new Paint();
        paint.setColor(color);
        _canvas.drawRect(rect1, paint);
    }
}