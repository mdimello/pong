package com.mqueiroz.pong.views;

import android.content.Context;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.mqueiroz.pong.engine.PongEngine;
import com.mqueiroz.pong.engine.WorkThread;


public class GameView extends SurfaceView implements SurfaceHolder.Callback
{
    private WorkThread mWorkThread;



    public GameView( Context context, PongEngine engine )
    {
        super( context );

        initializeView( engine );
    }



    void initializeView( PongEngine engine )
    {
        SurfaceHolder holder = getHolder( );
        getHolder( ).addCallback( this );

        mWorkThread = new WorkThread( holder, engine );

        setFocusable( true );
    }



    @Override
    public void surfaceCreated( SurfaceHolder arg0 )
    {
        if( ! mWorkThread.isAlive( ) )
        {
            mWorkThread.start( );
        }
    }



    @Override
    public void surfaceChanged( SurfaceHolder arg0, int arg1, int arg2, int arg3 )
    {

    }



    @Override
    public void surfaceDestroyed( SurfaceHolder arg0 )
    {
        mWorkThread.stopThread( );
    }
}