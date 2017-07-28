package com.mqueiroz.pong.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.mqueiroz.pong.engine.PongEngine;
import com.mqueiroz.pong.engine.PongPlayer;
import com.mqueiroz.pong.engine.PongGame;

public class PongActivity extends AppCompatActivity implements PongGame.GameController
{
    private boolean wasScreenTouched = false;
    private float mPaddleMoveY;
    private float mLastTouchedY;



    @Override
    protected void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );

        PongAI AI = new PongAI( );
        PongPlayer player = new PongPlayer( this, PongPlayer.PLAYER );

        PongGame game = new PongGame( player, AI.getPlayer( ) );

        PongEngine engine = new PongEngine( getApplicationContext( ), game, AI );

        setContentView( new GameView( this, engine ) );
    }



    @Override
    public void onWindowFocusChanged( boolean hasFocus )
    {
        super.onWindowFocusChanged( hasFocus );

        if( hasFocus )
        {
            getWindow( ).getDecorView( )
                    .setSystemUiVisibility(
                            View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
                                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                                    View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY );
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
                Log.d( "Touch", "true" );
                wasScreenTouched = true;
                setLastTouch( event.getY( ) );

                break;
            }
            case MotionEvent.ACTION_MOVE:
            {
                mPaddleMoveY += event.getY( ) - mLastTouchedY;
                setLastTouch( event.getY( ) );

                break;
            }
            case MotionEvent.ACTION_UP:
            {
                mPaddleMoveY += event.getY( ) - mLastTouchedY;
                setLastTouch( event.getY( ) );

                break;
            }
            default:
                break;
        }
        return false;
    }



    private void setLastTouch( float y )
    {
        mLastTouchedY = y;
    }



    @Override
    public boolean wasScreenTouched( )
    {
        if( wasScreenTouched )
        {
            wasScreenTouched = false;
            Log.d( "Touch", "false" );
            return true;
        }

        return false;
    }



    @Override
    public float wasPaddleMoved( )
    {
        float deltaY = mPaddleMoveY;
        mPaddleMoveY = 0;

        return deltaY;
    }
}
