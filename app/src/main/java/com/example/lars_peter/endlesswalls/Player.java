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
        pos = _pos;
        playerRect = new Rect(100,100,200,200);
    }

    public void PlayerCollision(Enemy _other)
    {
        if(_other instanceof LevelTile)
        {
            LevelTile tempObj = (LevelTile)_other;

            if(playerRect.contains(tempObj.getRect1()))
            {

            }

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
        Paint paint = new Paint(Color.RED);
        _canvas.drawRect(playerRect,paint);
    }




}