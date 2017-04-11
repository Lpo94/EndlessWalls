package com.example.lars_peter.endlesswalls;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.Random;

/**
 * Created by Shark on 11-04-2017.
 */

public class Traps extends Enemy implements LevelObject
{

    private int color;

    private Random rnd = new Random();
    private TileManager tileManager;
    private int lifetime;

    public Traps(int _color)
    {
        super();
        tileManager = TileManager.getInstance();
        this.color = _color;

        int rndLeft = rnd.nextInt(Constants.SCREEN_WIDTH);
        int rndTop = rnd.nextInt(Constants.SCREEN_HEIGHT);
        int right = rndLeft + 50;
        int bottom = rndTop + 50;

        // left, top, right, bottom
        rect = new Rect(rndLeft, rndTop, right, bottom);
    }

    public void destroy()
    {
        tileManager.GetTrap().remove(this);

    }

    @Override
    public void draw(Canvas _canvas)
    {
        Paint paint = new Paint();
        paint.setColor(color);
        _canvas.drawRect(rect, paint);
    }

    @Override
    public Rect getRect1() {
        return GetRect();
    }
}
