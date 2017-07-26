package com.mqueiroz.pong;

import android.app.Application;

public class PongApplication extends Application
{
    @Override
    public void onCreate( )
    {
        super.onCreate( );
        //Initialization of the AppConstants class
        AppConstants.Initialization( this.getApplicationContext( ) );
    }
}
