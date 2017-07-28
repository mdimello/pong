package com.mqueiroz.pong.engine;


public class PongGame implements PongEngine.Game
{
    static final int RUNNING  = 0;
    static final int PAUSED   = 1;
    static final int FINISHED = 2;

    private int mState;

    private PongPlayer mPlayer;
    private PongPlayer mAI;



    public interface GameController
    {
        boolean wasScreenTouched( );


        float wasPaddleMoved( );
    }



    public PongGame( PongPlayer player, PongPlayer ai )
    {
        mState = PAUSED;

        if( player.getId( ) == PongPlayer.PLAYER )
        {
            mPlayer = player;
        }
        else
        {
            throw new IllegalArgumentException( "Player has invalid id." );
        }

        if( ai.getId( ) == PongPlayer.AI )
        {
            mAI = ai;
        }
        else
        {
            throw new IllegalArgumentException( "AI has invalid id." );
        }
    }



    @Override
    public int getState( )
    {
        boolean wasTouched = mPlayer.getController( ).wasScreenTouched( );
        if( mState == PAUSED || mState == FINISHED )
        {
            if( wasTouched )
            {
                resumeGame( );
            }
        }

        return mState;
    }



    @Override
    public int getScore( int playerId )
    {
        if( playerId == PongPlayer.PLAYER )
        {
            return mPlayer.getScore( );
        }
        else if( playerId == PongPlayer.AI )
        {
            return mAI.getScore( );
        }

        throw new IllegalArgumentException( "Invalid Player Id." );
    }



    @Override
    public float getPaddleMovement( int playerId )
    {
        if( playerId == PongPlayer.PLAYER )
        {
            return mPlayer.getController( ).wasPaddleMoved( );
        }
        else if( playerId == PongPlayer.AI )
        {
            return mAI.getController( ).wasPaddleMoved( );
        }

        throw new IllegalArgumentException( "Invalid Player Id." );
    }



    @Override
    public void onPlayerScore( int playerId )
    {
        int score;
        if( playerId == PongPlayer.PLAYER )
        {
            score = mPlayer.getScore( );
            mPlayer.setScore( ++ score );
        }
        else if( playerId == PongPlayer.AI )
        {
            score = mAI.getScore( );
            mAI.setScore( ++ score );
        }
        else
        {
            throw new IllegalArgumentException( "Invalid Player Id." );
        }

        if( score == 11 )
        {
            finishGame( );
        }
        else
        {
            pauseGame( );
        }
    }



    private void resumeGame( )
    {
        if( mState == FINISHED )
        {
            mPlayer.setScore( 0 );
            mAI.setScore( 0 );
        }

        if( isGameReady( ) )
        {
            mState = RUNNING;
        }
    }



    private void pauseGame( )
    {
        mState = PAUSED;
    }



    private void finishGame( )
    {
        mState = FINISHED;
    }



    private boolean isGameReady( )
    {
        if( mPlayer != null && mAI != null )
        {
            return true;
        }

        return false;
    }
}
