package com.mqueiroz.pong.objects;


import android.content.Context;
import android.graphics.Rect;

import com.mqueiroz.pong.R;
import com.mqueiroz.pong.utils.DimensionProvider;


public class Paddle extends Sprite
{
    public Paddle( int x, int y, Context context )
    {
        super( x,
                y,
                DimensionProvider.provide( context, R.dimen.paddle_width ),
                DimensionProvider.provide( context, R.dimen.paddle_height ) );
    }



    public void updateYCoordinate( Rect background, float deltaY )
    {
        Rect updatedBounds = this.getBounds( );
        updatedBounds.top = updatedBounds.top + ( int ) deltaY;
        updatedBounds.bottom = updatedBounds.bottom + ( int ) deltaY;

        if( background.top <= updatedBounds.top && background.bottom >= updatedBounds.bottom )
        {
            /* Dislocation will not cause collision */
            this.setCoordinates( this.getX( ), this.getY( ) + deltaY );
        }

    }
}
