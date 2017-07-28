package com.mqueiroz.pong.utils;

import android.content.Context;
import android.support.annotation.DimenRes;


public class DimensionProvider
{
    public static int provide( Context context, @DimenRes int id )
    {
        return ( int ) context.getResources( ).getDimension( id );
    }
}
