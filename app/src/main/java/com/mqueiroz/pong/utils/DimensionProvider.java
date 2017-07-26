package com.mqueiroz.pong.utils;

import android.content.Context;
import android.support.annotation.DimenRes;

import com.mqueiroz.pong.PongApplication;



public class DimensionProvider
{
    public static int provide( Context context, @DimenRes int id )
    {
        return ( int ) context.getResources( ).getDimension( id );
    }
}
