package com.mqueiroz.pong.objects;

import android.graphics.Point;
import android.graphics.Rect;


/**
 * @author Matheus Queiroz
 */

public abstract class Sprite
{
    private float mX;
    private float mY;

    private final int mWidth;
    private final int mHeight;



    Sprite( int x, int y, int width, int height )
    {
        mX = x;
        mY = y;

        mWidth = width;
        mHeight = height;
    }



    /**
     * Sets coordinates to new value
     *
     * @param x new x axis value
     * @param y new y axis value
     */
    public void setCoordinates( float x, float y )
    {
        mX = x;
        mY = y;
    }



    /**
     * Gets X coordinate value
     *
     * @return X coordinate value
     */
    public float getX( )
    {
        return mX;
    }



    /**
     * Gets Y coordinate value
     *
     * @return Y coordinate value
     */
    public float getY( )
    {
        return mY;
    }



    /***
     * Calculates Rect Object according to sprite
     * dimensions in order to center the sprite in
     * X and Y coordinates.
     *
     * @return Rect object
     *
     **/
    public Rect getBounds( )
    {
        int left = ( int ) mX - mWidth / 2;
        int top = ( int ) mY - mHeight / 2;
        int right = left + mWidth;
        int bottom = top + mHeight;

        return new Rect( left, top, right, bottom );
    }
}
