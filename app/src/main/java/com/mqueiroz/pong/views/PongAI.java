package com.mqueiroz.pong.views;


import com.mqueiroz.pong.engine.PongEngine;
import com.mqueiroz.pong.engine.PongGame;
import com.mqueiroz.pong.engine.PongPlayer;

public class PongAI implements PongGame.GameController, PongEngine.AI
{
    private final PongPlayer mAI;

    private float deltaY;

    private float mLastKnownBallX = 0;
    private float mLastKnownBallY = 0;



    public PongAI( )
    {
        mAI = new PongPlayer( this, PongPlayer.AI );
    }



    public PongPlayer getPlayer( )
    {
        return mAI;
    }



    @Override
    public boolean wasScreenTouched( )
    {
        return false;
    }



    @Override
    public float wasPaddleMoved( )
    {
        return deltaY;
    }



    @Override
    public void onUpdate( float ballX, float ballY, float paddleY )
    {
        if( ballX - mLastKnownBallX < 0 )
        {
            deltaY = 0;
        }
        else
        {
            deltaY = ballY - paddleY;
        }

        if( deltaY > 15 )
        {
            deltaY = 10;
        }
        else if( deltaY < - 15 )
        {
            deltaY = - 15;
        }

        mLastKnownBallX = ballX;
        mLastKnownBallY = ballY;
    }
}
