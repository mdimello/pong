package com.mqueiroz.pong;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;

import com.mqueiroz.pong.objects.Ball;
import com.mqueiroz.pong.objects.Paddle;

/*
 * Stores all object references that relevant for the game display
 * Calls objects business logic methods, and draw them to the given Canvas from WorkThread
 * */
public class PongEngine
{
    private final Context mContext;

    /*MEMBERS*/
    static final int    BUBBLE_BOUNCE = 1;
    static final Object _sync         = new Object( );
    static float _lastTouchedX, _lastTouchedY;

    private final Paddle mLeftPaddle;
    private final Paddle mRightPaddle;

    private final Ball mBall;



    public PongEngine( Context context )
    {
        mContext = context;

        int leftPaddleX = AppConstants.SCREEN_WIDTH / 8;
        int leftPaddleY = AppConstants.SCREEN_HEIGHT / 2;
        mLeftPaddle = new Paddle( leftPaddleX, leftPaddleY, context );


        int rightPaddleX = 7 * ( AppConstants.SCREEN_WIDTH / 8 );
        int rightPaddleY = AppConstants.SCREEN_HEIGHT / 2;
        mRightPaddle = new Paddle( rightPaddleX, rightPaddleY, context );

        int ballX = AppConstants.SCREEN_WIDTH / 2;
        int ballY = AppConstants.SCREEN_HEIGHT / 2;
        mBall = new Ball( ballX, ballY, context );
    }



    private int DpsToPx( int dps )
    {
        return ( int ) ( dps * AppConstants.SCREEN_DENSITY );
    }



    /**
     * Updates all relevant objects business logic
     */
    public void Update( )
    {
        AdvanceBall( );
    }



    /**
     * Iterates through the Bubble list and advances them
     */
    private void AdvanceBall( )
    {
        synchronized( _sync )
        {
        }
    }



    /**
     * Draws all objects according to their parameters
     *
     * @param canvas canvas on which will be drawn the objects
     */
    public void Draw( Canvas canvas )
    {
        DrawBackground( canvas );
        DrawLeftPaddle( canvas );
        DrawRightPaddle( canvas );
        DrawBall( canvas );
    }



    /**
     * Draws background
     *
     * @param canvas canvas on which will be drawn the bitmap
     */
    private void DrawBackground( Canvas canvas )
    {
        Drawable background = ContextCompat.getDrawable( mContext, R.drawable.background );
        background.setBounds( 0, 0, canvas.getWidth( ), canvas.getHeight( ) );
        background.draw( canvas );
    }



    /**
     * Draws left paddle
     *
     * @param canvas canvas on which will be drawn the bitmap
     */
    private void DrawLeftPaddle( Canvas canvas )
    {
        Drawable paddle = ContextCompat.getDrawable( mContext, R.drawable.paddle );
        paddle.setBounds( mLeftPaddle.getRect( ) );
        paddle.draw( canvas );
    }



    /**
     * Draws right paddle
     *
     * @param canvas canvas on which will be drawn the bitmap
     */
    private void DrawRightPaddle( Canvas canvas )
    {
        Drawable paddle = ContextCompat.getDrawable( mContext, R.drawable.paddle );
        paddle.setBounds( mRightPaddle.getRect( ) );
        paddle.draw( canvas );
    }



    /**
     * Draws ball paddle
     *
     * @param canvas canvas on which will be drawn the bitmap
     */
    private void DrawBall( Canvas canvas )
    {
        Drawable ball = ContextCompat.getDrawable( mContext, R.drawable.ball );
        ball.setBounds( mBall.getRect( ) );
        ball.draw( canvas );
    }



    /**
     * Sets previous touch coordinates
     *
     * @param x current touch x coordinate
     * @param y current touch y coordinate
     */
    public void SetLastTouch( float x, float y )
    {
        _lastTouchedX = x;
        _lastTouchedY = y;
    }
}