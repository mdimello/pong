package com.mqueiroz.pong.views;


import com.mqueiroz.pong.engine.PongEngine;
import com.mqueiroz.pong.engine.PongGame;
import com.mqueiroz.pong.engine.PongPlayer;

public class PongAI implements PongGame.GameController, PongEngine.AI
{
    private final PongPlayer mAI;

    private float deltaY;



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
        deltaY = ballY - paddleY;
    }
}
