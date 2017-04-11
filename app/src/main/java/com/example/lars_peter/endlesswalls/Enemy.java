package com.example.lars_peter.endlesswalls;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by Shark on 10-04-2017.
 */

public class Enemy implements LevelObject
{

    private int color;
    private Rect rect1;

    public void Enemy(int _color, int _startY, int _startX, int _rectheight)
    {
        this.color = _color;
        rect1 = new Rect(0, _startY, _startX, _startY +_rectheight);
    }

    @Override
    public void draw(Canvas _canvas)
    {
        Paint paint = new Paint();
        paint.setColor(color);
        _canvas.drawRect(rect1, paint);
    }

    @Override
    public void update()
    {

    }
}
