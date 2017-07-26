package com.mqueiroz.pong;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;

import com.mqueiroz.pong.views.GameView;

public class PongActivity extends AppCompatActivity
{

    @Override
    protected void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );

        SurfaceView view = new GameView( this );

        setContentView( view );
    }



    @Override
    public void onWindowFocusChanged( boolean hasFocus )
    {
        super.onWindowFocusChanged( hasFocus );

        if( hasFocus )
        {
            getWindow( ).getDecorView( )
                    .setSystemUiVisibility( View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                            View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
                            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                            View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN |
                            View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY );
        }
    }



    @Override
    public boolean onTouchEvent( MotionEvent event )
    {
        super.onTouchEvent( event );

        switch( event.getAction( ) )
        {
            case MotionEvent.ACTION_DOWN:
            {
                OnActionDown( event );
                break;
            }
            case MotionEvent.ACTION_UP:
            {
                OnActionUp( event );
                break;
            }
            case MotionEvent.ACTION_MOVE:
            {
                OnActionMove( event );
                break;
            }
            default:
                break;
        }
        return false;
    }



    /*activates on touch move event*/
    private void OnActionMove( MotionEvent event )
    {
        int x = ( int ) event.getX( );
        int y = ( int ) event.getY( );

        if( GetIfTouchInTheZone( x, y ) )
        {
            //AppConstants.GetEngine( ).SetCannonRotation( x, y );
        }

        AppConstants.GetEngine( ).SetLastTouch( event.getX( ), event.getY( ) );
    }



    private boolean GetIfTouchInTheZone( int x, int y )
    {
        return false;
    }



    /*activates on touch up event*/
    private void OnActionUp( MotionEvent event )
    {
        int x = ( int ) event.getX( );
        int y = ( int ) event.getY( );

        if( GetIfTouchInTheZone( x, y ) )
        {
            //AppConstants.GetEngine( ).SetCannonRotation( x, y );

            //AppConstants.GetEngine( ).CreateNewBubble( x, y );
        }
    }



    /*activates on touch down event*/
    private void OnActionDown( MotionEvent event )
    {
        AppConstants.GetEngine( ).SetLastTouch( event.getX( ), event.getY( ) );
    }
}
