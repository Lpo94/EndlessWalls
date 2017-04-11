package com.example.lars_peter.endlesswalls;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.Random;

/**
 * Created by SharkGaming on 07/04/2017.
 */

public class LevelTile extends Enemy implements LevelObject
{
    private int color;
    private Rect rect1;
    private Rect rect2;
    private Rect rect3;
    Random rnd = new Random();

    public LevelTile(int _rectheight, int _color, int _startX, int _startY, int _playerGap)
    {
        this.color = _color;
        int rndGap = rnd.nextInt(300);

        if (rndGap<30) { rndGap = rnd.nextInt(200);}

        // left, top, right, bottom
        rect1 = new Rect(0, _startY, _startX, _startY +_rectheight);
        rect2 = new Rect(_startX + _playerGap , _startY, _startX + _playerGap + rndGap, _startY +_rectheight);
        rect3 = new Rect(_startX + (_playerGap *2) + rndGap, _startY, Constants.SCREEN_WIDTH, _startY +_rectheight);
    }

//    public boolean playerCollision(Player _player) for alle 3 rectangler
//    {
//        if(rectangle.contains(_player.getRectangle().left, _player.getRectangle().top)
//                || rectangle.contains(_player.getRectangle().right, _player.getRectangle().top)
//                || rectangle.contains(_player.getRectangle().left, _player.getRectangle().bottom)
//                || rectangle.contains(_player.getRectangle().right, _player.getRectangle().bottom))
//        {
//            return true;
//        }
//        else
//        {
//            return false;
//        }
//    }

    public void incrementY(float _y)
    {
        rect1.top += _y;
        rect1.bottom += _y;

        rect2.top += _y;
        rect2.bottom += _y;

        rect3.top += _y;
        rect3.bottom += _y;
    }

    public Rect getRect1()
    {
        return rect1;
    }

    public Rect getRect2()
    {
        return rect2;
    }

    public Rect getRect3()
    {
        return rect3;
    }


    @Override
    public void draw(Canvas _canvas)
    {
        Paint paint = new Paint();
        paint.setColor(color);
        _canvas.drawRect(rect1, paint);
        _canvas.drawRect(rect2, paint);
        _canvas.drawRect(rect3, paint);
    }

    @Override
    public void update()
    {

    }
}
