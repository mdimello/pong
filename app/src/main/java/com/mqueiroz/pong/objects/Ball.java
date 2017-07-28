package com.mqueiroz.pong.objects;


import android.content.Context;

import com.mqueiroz.pong.R;
import com.mqueiroz.pong.utils.DimensionProvider;

public class Ball extends Sprite
{
    private static final float MOTION_MODULE = 25;

    private float mDeltaX = 0;
    private float mDeltaY = 0;



    public Ball( int x, int y, Context context )
    {
        super( x,
                y,
                DimensionProvider.provide( context, R.dimen.ball_width ),
                DimensionProvider.provide( context, R.dimen.ball_height ) );
    }



    /**
     * Sets ball movement direction
     *
     * @param angle ball direction in degrees
     */
    public void setMotionAngle( double angle )
    {
        float sin = ( float ) Math.sin( Math.toRadians( angle ) );
        float cos = ( float ) Math.cos( Math.toRadians( angle ) );

        mDeltaX = cos * MOTION_MODULE;
        mDeltaY = sin * MOTION_MODULE;
    }



    public void mirrorMotionVectorInX( )
    {
        mDeltaY = - mDeltaY;
    }



    public void mirrorMotionVectorInY( )
    {
        mDeltaX = - mDeltaX;
    }



    public float getMotionVectorX( )
    {
        return mDeltaX;
    }



    public float getMotionVectorY( )
    {
        return mDeltaY;
    }
}
