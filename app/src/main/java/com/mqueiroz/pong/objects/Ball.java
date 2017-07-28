package com.mqueiroz.pong.objects;


import android.content.Context;
import android.graphics.Rect;

import com.mqueiroz.pong.R;
import com.mqueiroz.pong.utils.DimensionProvider;

import java.util.List;

public class Ball extends Sprite
{
    private float mDeltaX = 0;
    private float mDeltaY = 0;



    public Ball( int x, int y, Context context )
    {
        super( x,
                y,
                DimensionProvider.provide( context, R.dimen.ball_width ),
                DimensionProvider.provide( context, R.dimen.ball_height ) );

        setMotionVector( 20, 20 );
    }



    /**
     * Sets Motion Vector to new value
     *
     * @param deltaX X axis derivative
     * @param deltaY Y axis derivative
     */
    public void setMotionVector( float deltaX, float deltaY )
    {
        mDeltaX = deltaX;
        mDeltaY = deltaY;
    }



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
