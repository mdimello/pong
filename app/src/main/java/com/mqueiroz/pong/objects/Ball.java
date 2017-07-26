package com.mqueiroz.pong.objects;


import android.content.Context;

import com.mqueiroz.pong.R;
import com.mqueiroz.pong.utils.DimensionProvider;

public class Ball extends BaseSprite
{
    public Ball( int x, int y, Context context )
    {
        super( x,
                y,
                DimensionProvider.provide( context, R.dimen.ball_width ),
                DimensionProvider.provide( context, R.dimen.ball_height ) );
    }
}
