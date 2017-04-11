package com.example.lars_peter.endlesswalls;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

/**
 * Created by Lars-Peter on 10/04/2017.
 */

public class Player {

    private Rect playerRect;
    private Point pos;
    private int colour;

    public Rect GetRect()
    {
        return playerRect;
    }

    public Point GetPos()
    {
        return pos;
    }

    public Player(Point _pos)
    {
        colour = new Color().RED;
        pos = _pos;
        playerRect = new Rect(100,100,200,200);
    }

    public boolean PlayerCollision(Enemy _other)
    {

        if(_other instanceof LevelWave)
        {
            LevelWave tempObj = (LevelWave)_other;
            Rect tempRect;

            if(Rect.intersects(playerRect,tempObj.getRect1()))
            {
                WallCollision(tempObj.getRect1());
                return true;
            }
            else if(Rect.intersects(playerRect,tempObj.getRect2()))
            {
                WallCollision(tempObj.getRect2());
                return true;
            }
            else if(Rect.intersects(playerRect,tempObj.getRect3()))
            {

               WallCollision(tempObj.getRect3());
                return true;
            }
            return false;
        }

        else if(playerRect.intersect(_other.GetRect()))
        {
//            DoCollision(_other);
            return true;
        }




        return false;
    }

    private void DoCollision(Enemy _other)
    {
        if(_other instanceof LevelWave)
        {

        }


    }

    private void WallCollision(Rect _wallRect)
    {
        if(playerRect.top < _wallRect.bottom)
        {
            pos.y += (_wallRect.height()/5);
        }
        else if(playerRect.bottom > _wallRect.top)
        {
            pos.y -= (_wallRect.height()/5);
        }
        //LEFT
        if(playerRect.right < _wallRect.left)
        {
            pos.x -= _wallRect.width()/5;
        }
        //Right
        else if(playerRect.left > _wallRect.left)
        {
            pos.y -= _wallRect.width()/5;
        }
    }


    public void update()
    {
        if(pos.x < 0) {
            pos.x = 0;
        }
        else if(pos.x > Constants.SCREEN_WIDTH) {
            pos.x = Constants.SCREEN_WIDTH;
        }
        if(pos.y < 0) {
            pos.y = 0;
        }
        else if(pos.y > Constants.SCREEN_HEIGHT) {
            pos.y = Constants.SCREEN_HEIGHT;
        }
        playerRect.set(pos.x-playerRect.width()/2,pos.y -playerRect.height()/2,
                pos.x+playerRect.width()/2,pos.y+playerRect.height()/2);

    }

    public void Draw(Canvas _canvas)
    {
        Paint paint = new Paint(colour);
        _canvas.drawRect(playerRect,paint);
    }





}