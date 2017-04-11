package com.example.lars_peter.endlesswalls;

import android.graphics.Canvas;
import android.graphics.Rect;

/**
 * Created by SharkGaming on 07/04/2017.
 */

public interface LevelObject
{
    void draw(Canvas _canvas);
    Rect getRect1();
}
