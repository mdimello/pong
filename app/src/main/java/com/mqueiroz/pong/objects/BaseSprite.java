package com.mqueiroz.pong.objects;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.DimenRes;
import android.support.annotation.DrawableRes;


/**
 * @author Matheus Queiroz
 */

public abstract class BaseSprite
{
    private final int mWidth;
    private final int mHeight;

    private int mX;
    private int mY;



    BaseSprite( int x, int y, int width, int height )
    {
        mX = x;
        mY = y;

        mWidth = width;
        mHeight = height;
    }



    /**
     * Sets x coordinate to new value
     *
     * @param x new x value
     */
    public void setX( int x )
    {
        mX = x;
    }



    /**
     * Sets y coordinate to new value
     *
     * @param y new y value
     */
    public void setY( int y )
    {
        mY = y;
    }



    /**
     * @return x coordinate
     */
    public int getX( )
    {
        return mX;
    }



    /**
     * @return y coordinate
     */
    public int getY( )
    {
        return mY;
    }



    /***
     * Calculates Rect object according to object dimensions
     *
     * @return Rect object
     *
     * */
    public Rect getRect( )
    {
        int left = getX( ) - mWidth / 2;
        int top = getY( ) - mHeight / 2;
        int right = left + mWidth;
        int bottom = top + mHeight;

        return new Rect( left, top, right, bottom );
    }
}
