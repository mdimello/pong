package com.mqueiroz.pong.objects;


import android.content.Context;

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
}
