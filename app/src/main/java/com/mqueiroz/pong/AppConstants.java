package com.mqueiroz.pong;


import android.content.Context;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

public class AppConstants
{
    static PongEngine _engine;

    public static int   SCREEN_WIDTH;
    public static int   SCREEN_HEIGHT;
    public static float SCREEN_DENSITY;



    /**
     * Initiates the applciation constants
     */
    public static void Initialization( Context context )
    {
        SetScreenSize( context );

        _engine = new PongEngine( context );
    }



    /**
     * Sets screen size constants accordingly to device screen size
     */
    private static void SetScreenSize( Context context )
    {
        WindowManager wm = ( WindowManager ) context.getSystemService( Context.WINDOW_SERVICE );
        Display display = wm.getDefaultDisplay( );

        Point size = new Point( );
        display.getRealSize( size );
        AppConstants.SCREEN_WIDTH = size.x;
        AppConstants.SCREEN_HEIGHT = size.y;

        DisplayMetrics metrics = new DisplayMetrics( );
        display.getMetrics( metrics );
        AppConstants.SCREEN_DENSITY = metrics.density;
    }



    /**
     * @return PongEngine instance
     */
    public static PongEngine GetEngine( )
    {
        return _engine;
    }
}
