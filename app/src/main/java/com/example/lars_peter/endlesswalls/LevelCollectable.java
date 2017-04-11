package com.example.lars_peter.endlesswalls;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Color;
import java.util.Random;

/**
 * Created by SharkGaming on 10/04/2017.
 */

public class LevelCollectable extends Enemy implements LevelObject
{

    private int color;

    private Random rnd = new Random();
    private TileManager tileManager;
    private int lifetime;

    public LevelCollectable(int _color)
    {
        super();
        tileManager = TileManager.getInstance();
        this.color = _color;
        lifetime = 2000;

        int rndLeft = rnd.nextInt(Constants.SCREEN_WIDTH-25)+25;
        int rndTop = rnd.nextInt(Constants.SCREEN_HEIGHT-150)+100;
        int right = rndLeft + 50;
        int bottom = rndTop + 50;

        // left, top, right, bottom
        rect = new Rect(rndLeft, rndTop, right, bottom);
    }

    public void update()
    {
        lifetime--;

        if(lifetime <= 0)
        {
            destroy();
        }
    }

    public void destroy()
    {
        tileManager.GetCollectables().remove(this);

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
