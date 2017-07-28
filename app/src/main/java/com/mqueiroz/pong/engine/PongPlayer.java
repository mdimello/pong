package com.mqueiroz.pong.engine;


public class PongPlayer
{
    public static final int PLAYER = 1;
    public static final int AI     = 2;


    private final PongGame.GameController controller;

    private final int id;

    private int score;



    public PongPlayer( PongGame.GameController controller, int id )
    {
        this.controller = controller;
        this.id = id;
        this.score = 0;
    }



    int getId( )
    {
        return id;
    }



    int getScore( )
    {
        return score;
    }



    void setScore( int score )
    {
        this.score = score;
    }



    PongGame.GameController getController( )
    {
        return controller;
    }
}
