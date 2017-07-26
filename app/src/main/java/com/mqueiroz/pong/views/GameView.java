package com.mqueiroz.pong.views;

import android.content.Context;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.mqueiroz.pong.WorkThread;


public class GameView extends SurfaceView implements SurfaceHolder.Callback
{
    private WorkThread mWorkThread;



    public GameView( Context context )
    {
        super( context );

        initializeView( );
    }



    void initializeView( )
    {
        SurfaceHolder holder = getHolder( );
        getHolder( ).addCallback( this );

        mWorkThread = new WorkThread( holder );

        setFocusable( true );
    }



    @Override
    public void surfaceCreated( SurfaceHolder arg0 )
    {
        mWorkThread.start( );
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