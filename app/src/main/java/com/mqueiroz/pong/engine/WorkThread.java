package com.mqueiroz.pong.engine;

import android.graphics.Canvas;
import android.view.SurfaceHolder;


/**
 * Responsible for screen painting.
 */
public class WorkThread extends Thread
{
    private final long MAX_REFRESH_INTERVAL_IN_MS = 15;

    private final SurfaceHolder mSurfaceHolder;

    private final PongEngine mEngine;

    private boolean mIsRunning = false;



    public WorkThread( SurfaceHolder surfaceHolder, PongEngine engine )
    {
        mSurfaceHolder = surfaceHolder;
        mEngine = engine;

        mIsRunning = true;
    }



    /**
     * This is the main nucleus of our program.
     * From here will be called all the method that are associated with the display in PongEngine
     * object
     */
    @Override
    public void run( )
    {
        while( mIsRunning )
        {
            long start = System.currentTimeMillis( );

            mEngine.update( );

            Canvas canvas = mSurfaceHolder.lockCanvas( null );
            if( canvas != null )
            {
                synchronized( mSurfaceHolder )
                {
                    mEngine.render( canvas );
                }

                mSurfaceHolder.unlockCanvasAndPost( canvas );
            }

            long delta = System.currentTimeMillis( ) - start;
            long sleep = MAX_REFRESH_INTERVAL_IN_MS - delta;
            if( sleep > 0 )
            {
                try
                {
                    Thread.sleep( sleep );
                }
                catch( InterruptedException ex )
                {
                    ex.printStackTrace( );
                }
            }
        }
    }



    public void stopThread( )
    {
        mIsRunning = false;

        boolean retry = true;
        while( retry )
        {
            try
            {
                this.join( );
                retry = false;
            }
            catch( InterruptedException e )
            {
                e.printStackTrace( );
            }
        }
    }
}