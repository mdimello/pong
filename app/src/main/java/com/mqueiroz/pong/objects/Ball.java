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
     * Sets movement direction
     *
     * @param angle X axis derivative
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



    //    /**
    //     * Sets Motion Vector to new value
    //     *
    //     * @param deltaX X axis derivative
    //     * @param deltaY Y axis derivative
    //     */
    //    public void setMotionVector( float deltaX, float deltaY )
    //    {
    //        mDeltaX = deltaX;
    //        mDeltaY = deltaY;
    //    }



    /**
     * Gets Motion Vector X derivative
     *
     * @return X axis derivative
     */
    public float getMotionVectorX( )
    {
        return mDeltaX;
    }



    /**
     * Gets Motion Vector Y derivative
     *
     * @return Y axis derivative
     */
    public float getMotionVectorY( )
    {
        return mDeltaY;
    }
}
