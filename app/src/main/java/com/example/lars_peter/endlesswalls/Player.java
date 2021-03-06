package com.example.lars_peter.endlesswalls;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Vibrator;

/**
 * Created by Lars-Peter on 10/04/2017.
 */

public class Player {

    private Rect playerRect;
    private Point pos;
    private int colour;
    private boolean playerAlive;
    private int smallDelay;


    public boolean GetplayerAlive()
    {
        return playerAlive;
    }

    public void SetplayerAlive(boolean _playerAlive)
    {
        playerAlive = _playerAlive;
    }

    public float xSpeed;
    public float ySpeed;

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
        smallDelay = 1500;
        colour = new Color().RED;
        pos = _pos;
        playerRect = new Rect(100,100,200,200);
        update();
    }

    public void PlayerCollision(Enemy _other)
    {
        if(_other.active) {
            if (_other instanceof LevelWave) {
                LevelWave tempObj = (LevelWave) _other;
                Rect tempRect = null;

                if (Rect.intersects(playerRect, tempObj.getRect1())) {
                    tempRect = tempObj.getRect1();
                    WallCollision(tempObj.getRect1());
                } else if (Rect.intersects(playerRect, tempObj.getRect2())) {
                    tempRect = tempObj.getRect2();
                    WallCollision(tempObj.getRect2());
                } else if (Rect.intersects(playerRect, tempObj.getRect3())) {
                    tempRect = tempObj.getRect3();
                    WallCollision(tempObj.getRect3());
                }

                if (tempRect != null) {
                    while (!Wallforce(tempRect, pos)) {
                        if (Wallforce(tempRect, pos)) {
                            break;
                        }
                    }
                }
            } else if (Rect.intersects(playerRect, _other.GetRect())) {
                DoCollision(_other);
            }
        }
    }

    private void DoCollision(Enemy _other)
    {
        if(_other.active) {
            if (_other instanceof LevelCollectable) {
                HighScore.counter += 1000;
                ((LevelCollectable) _other).destroy();
            }

            if (_other instanceof Traps) {
                HighScore.counter -= 500;
                ((Traps) _other).destroy();
            }
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
        else if(playerRect.left > _wallRect.right)
        {
            pos.x += _wallRect.width()/5;
        }
    }
    public void Reset(Point _point)
    {
        pos = _point;
        smallDelay = 1500;
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
            if(smallDelay <= 0) {
                GameView.gameRunning = false;
            }
        }
        if(smallDelay > 0)
        {
            smallDelay--;
        }
        playerRect.set(pos.x-playerRect.width()/2,pos.y -playerRect.height()/2,
                pos.x+playerRect.width()/2,pos.y+playerRect.height()/2);



    }

    public boolean Wallforce(Rect _enemyRect, Point _point)
    {
        if(_enemyRect.contains(_point.x,_point.y))
        {
            WallCollision(_enemyRect);
            return false;
        }

        return true;
    }

    public void Draw(Canvas _canvas)
    {
        Paint paint = new Paint(colour);
        _canvas.drawRect(playerRect,paint);
    }
}