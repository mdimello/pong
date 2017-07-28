package com.mqueiroz.pong.views;

import android.content.Context;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.mqueiroz.pong.engine.PongEngine;
import com.mqueiroz.pong.engine.WorkThread;


public class GameView extends SurfaceView implements SurfaceHolder.Callback
{
    private WorkThread mWorkThread;

    private final PongEngine mEngine;



    public GameView( Context context, PongEngine engine )
    {
        super( context );

        mEngine = engine;

        initializeView( );
    }



    void initializeView( )
    {
        getHolder( ).addCallback( this );

        setFocusable( true );
    }



    @Override
    public void surfaceCreated( SurfaceHolder holder )
    {
        mWorkThread = new WorkThread( holder, mEngine );

        if( mWorkThread.getState( ) == Thread.State.NEW )
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