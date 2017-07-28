package com.mqueiroz.pong.engine;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;

import com.mqueiroz.pong.PongApplication;
import com.mqueiroz.pong.R;
import com.mqueiroz.pong.objects.Ball;
import com.mqueiroz.pong.objects.Paddle;

import java.util.Random;

/**
 * Provides information for both players, for the human in form of rendered graphs,
 * for the AI calling callbacks. Also handles the game physics.
 */
public class PongEngine
{
    private final Context mContext;

    private final Game mGame;
    private final AI   mAI;

    private Rect   mBackground;
    private Paddle mPlayerPaddle;
    private Paddle mAIPaddle;
    private Ball   mBall;



    interface Game
    {
        int getState( );


        int getScore( int playerId );


        float getPaddleMovement( int playerId );


        void onPlayerScore( int playerId );
    }



    public interface AI
    {
        void onUpdate( float ballX, float ballY, float paddleY );
    }



    public PongEngine( Context context, Game game, AI ai )
    {
        mContext = context;

        mGame = game;
        mAI = ai;

        reset( );
    }



    /**
     * Updates all relevant objects business logic
     */
    void update( )
    {
        if( mGame.getState( ) == PongGame.RUNNING )
        {
            updateLeftPaddle( );
            updateRightPaddle( );
            updateBall( );
        }
        else if( mGame.getState( ) == PongGame.PAUSED )
        {
            reset( );
        }
        else if( mGame.getState( ) == PongGame.FINISHED )
        {
            reset( );
        }
    }



    /**
     * Draws all objects
     *
     * @param canvas canvas to be drawn
     */
    void render( Canvas canvas )
    {
        renderBackground( canvas );
        renderLeftPaddle( canvas );
        renderRightPaddle( canvas );
        renderBall( canvas );
        renderScores( canvas );

        if( mGame.getState( ) == PongGame.PAUSED )
        {
            renderPauseMessage( canvas );
        }
        else if( mGame.getState( ) == PongGame.FINISHED )
        {
            renderFinishMessage( canvas );
        }
    }



    /**
     * Draws background
     *
     * @param canvas canvas on which will be drawn the bitmap
     */
    private void renderBackground( Canvas canvas )
    {
        Drawable background = ContextCompat.getDrawable( mContext, R.drawable.background );
        background.setBounds( 0, 0, canvas.getWidth( ), canvas.getHeight( ) );
        background.draw( canvas );
    }



    /**
     * Draws left paddle
     *
     * @param canvas canvas to be drawn
     */
    private void renderLeftPaddle( Canvas canvas )
    {
        Drawable paddle = ContextCompat.getDrawable( mContext, R.drawable.paddle );
        paddle.setBounds( mPlayerPaddle.getBounds( ) );
        paddle.draw( canvas );
    }



    /**
     * Draws right paddle
     *
     * @param canvas canvas to be drawn
     */
    private void renderRightPaddle( Canvas canvas )
    {
        Drawable paddle = ContextCompat.getDrawable( mContext, R.drawable.paddle );
        paddle.setBounds( mAIPaddle.getBounds( ) );
        paddle.draw( canvas );
    }



    /**
     * Draws ball
     *
     * @param canvas canvas to be drawn
     */
    private void renderBall( Canvas canvas )
    {
        Drawable ball = ContextCompat.getDrawable( mContext, R.drawable.ball );
        ball.setBounds( mBall.getBounds( ) );
        ball.draw( canvas );

        mAI.onUpdate( mBall.getX( ), mBall.getY( ), mAIPaddle.getY( ) );
    }



    /**
     * Draws scores
     *
     * @param canvas canvas to be drawn
     */
    private void renderScores( Canvas canvas )
    {
        int leftScore = mGame.getScore( PongPlayer.PLAYER );
        int rightScore = mGame.getScore( PongPlayer.AI );

        Paint score = new Paint( );
        score.setColor( ContextCompat.getColor( mContext, R.color.white ) );
        score.setTextSize( mBackground.height( ) / 8 );

        float widthL = score.measureText( String.valueOf( leftScore ) );
        float widthR = score.measureText( String.valueOf( rightScore ) );

        int xL = ( mBackground.width( ) / 4 ) - ( int ) ( widthL / 2 );
        int yL = mBackground.height( ) / 7;

        int xR = ( 3 * mBackground.width( ) / 4 ) - ( int ) ( widthR / 2 );
        int yR = mBackground.height( ) / 7;

        canvas.drawText( String.valueOf( leftScore ), xL, yL, score );
        canvas.drawText( String.valueOf( rightScore ), xR, yR, score );
    }



    private void renderPauseMessage( Canvas canvas )
    {
        renderMessage( canvas, "Toque na tela para jogar!" );
    }



    private void renderFinishMessage( Canvas canvas )
    {
        if( mGame.getScore( PongPlayer.PLAYER ) > mGame.getScore( PongPlayer.AI ) )
        {
            renderMessage( canvas, "VITÓRIA!!!" );
        }
        else
        {
            renderMessage( canvas, "Você perdeu =(" );
        }
    }



    private void renderMessage( Canvas canvas, String message )
    {
        Paint fillPaint = new Paint( );
        fillPaint.setColor( ContextCompat.getColor( mContext, R.color.white ) );
        fillPaint.setTextSize( mBackground.height( ) / 8 );

        Paint strokePaint = new Paint( );
        strokePaint.setStyle( Paint.Style.STROKE );
        strokePaint.setStrokeWidth( 1 );
        strokePaint.setTextSize( mBackground.height( ) / 8 );
        strokePaint.setColor( Color.BLACK );

        float widthL = fillPaint.measureText( message );

        int x = ( mBackground.width( ) / 2 ) - ( int ) ( widthL / 2 );
        int y = mBackground.height( ) / 2;

        Drawable background = ContextCompat.getDrawable( mContext, R.drawable.message_background );
        background.setBounds( 0, 0, canvas.getWidth( ), canvas.getHeight( ) );
        background.draw( canvas );

        canvas.drawText( message, x, y, fillPaint );
        canvas.drawText( message, x, y, strokePaint );
    }



    /**
     * Resets all objects to initial conditions
     */
    private void reset( )
    {
        mBackground = new Rect( 0, 0, PongApplication.SCREEN_WIDTH, PongApplication.SCREEN_HEIGHT );

        int centerY = mBackground.height( ) / 2;

        int leftPaddleX = mBackground.width( ) / 8;
        mPlayerPaddle = new Paddle( leftPaddleX, centerY, mContext );

        int rightPaddleX = 7 * mBackground.width( ) / 8;
        mAIPaddle = new Paddle( rightPaddleX, centerY, mContext );

        Random random = new Random( );
        int quadrant = random.nextInt( 4 );
        int degree = random.nextInt( 60 ) + 15;

        int ballX = mBackground.width( ) / 2;
        mBall = new Ball( ballX, centerY, mContext );
        mBall.setMotionAngle( quadrant * 90 + degree );
    }



    /**
     * Updates ball coordinates and handle
     * ball collisions.
     */
    private void updateBall( )
    {
        Rect updatedBounds = mBall.getBounds( );
        updatedBounds.left += mBall.getMotionVectorX( );
        updatedBounds.top += mBall.getMotionVectorY( );
        updatedBounds.right += mBall.getMotionVectorX( );
        updatedBounds.bottom += mBall.getMotionVectorY( );

        if( mBackground.left >= updatedBounds.left )
        {
            /* Collision with background left bound */
            mGame.onPlayerScore( PongPlayer.AI );
        }
        else if( mBackground.right <= updatedBounds.right )
        {
            /* Collision with background right bound */
            mGame.onPlayerScore( PongPlayer.PLAYER );
        }
        else if( mBackground.top >= updatedBounds.top || mBackground.bottom <= updatedBounds.bottom )
        {
            /* Collision with background X bounds */
            mBall.mirrorMotionVectorInX( );
        }
        else if( Rect.intersects( mBall.getBounds( ), mPlayerPaddle.getBounds( ) ) )
        {
            /* Collision with Left Paddle */
            mBall.setMotionAngle( getMotionAngleAfterCollision( true,
                    mBall.getBounds( ),
                    mPlayerPaddle.getBounds( ) ) );
        }
        else if( Rect.intersects( mBall.getBounds( ), mAIPaddle.getBounds( ) ) )
        {
            /* Collision with Right Paddle */
            mBall.setMotionAngle( getMotionAngleAfterCollision( false, mBall.getBounds( ), mAIPaddle.getBounds( ) ) );
            mBall.mirrorMotionVectorInX( );
        }

        float newX = mBall.getX( ) + mBall.getMotionVectorX( );
        float newY = mBall.getY( ) + mBall.getMotionVectorY( );

        mBall.setCoordinates( newX, newY );
    }



    /**
     * Calculates ball direction after collision
     * with one of the paddles
     */
    private int getMotionAngleAfterCollision( boolean isLeftSide, Rect ball, Rect paddle )
    {
        int interval = paddle.bottom - paddle.top;
        float interpolation = ( ( float ) ( ( ball.bottom + ball.top ) / 2 ) - paddle.top ) / interval;

        int degree = ( int ) ( 140 * interpolation + 20 ) - 90;

        if( ! isLeftSide )
        {
            degree = degree + 180;
        }

        return degree;
    }



    private void updateLeftPaddle( )
    {
        updatePaddle( mPlayerPaddle, mGame.getPaddleMovement( PongPlayer.PLAYER ) );
    }



    private void updateRightPaddle( )
    {
        updatePaddle( mAIPaddle, mGame.getPaddleMovement( PongPlayer.AI ) );
    }



    /**
     * Updates paddle coordinates and handle
     * ball collisions.
     */
    private void updatePaddle( Paddle paddle, float deltaY )
    {
        Rect updatedBounds = paddle.getBounds( );
        updatedBounds.top = updatedBounds.top + ( int ) deltaY;
        updatedBounds.bottom = updatedBounds.bottom + ( int ) deltaY;

        if( mBackground.top <= updatedBounds.top && mBackground.bottom >= updatedBounds.bottom )
        {
            /* Dislocation will not cause collision */
            paddle.setCoordinates( paddle.getX( ), paddle.getY( ) + deltaY );
        }
    }
}