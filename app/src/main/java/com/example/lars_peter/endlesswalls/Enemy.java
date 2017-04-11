package com.example.lars_peter.endlesswalls;

import android.graphics.Rect;

/**
 * Created by LP on 10-04-2017.
 */

public class Enemy {
    protected Rect rect;
    protected boolean active = true;

    public Rect GetRect()
    {
        return rect;
    }
}
