package com.mqueiroz.pong;

import android.app.Application;
import android.content.Context;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

public class PongApplication extends Application
{
    public static int   SCREEN_WIDTH;
    public static int   SCREEN_HEIGHT;
    public static float SCREEN_DENSITY;



    @Override
    public void onCreate( )
    {
        super.onCreate( );

        WindowManager wm = ( WindowManager ) this.getSystemService( Context.WINDOW_SERVICE );
        Display display = wm.getDefaultDisplay( );

        Point size = new Point( );
        display.getRealSize( size );
        PongApplication.SCREEN_WIDTH = size.x;
        PongApplication.SCREEN_HEIGHT = size.y;

        DisplayMetrics metrics = new DisplayMetrics( );
        display.getMetrics( metrics );
        PongApplication.SCREEN_DENSITY = metrics.density;
    }
}
