package com.leeway.templapp.MainScreens.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.leeway.templapp.R;
import com.leeway.templapp.SessionManager.SessionManager;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class SplashScreen extends Activity {

    /** Duration of wait **/

    private final int SPLASH_DISPLAY_LENGTH = 1000;
    SessionManager sessionManager;


    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.splash_layout);
        sessionManager=new SessionManager(this);

        /* New Handler to start the Menu-Activity
         * and close this Splash-Screen after some seconds.*/
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                String userid=sessionManager.getUserId();
                if(userid.equals("")) {

                    Intent mainIntent = new Intent(SplashScreen.this, Login_Page.class);
                    mainIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(mainIntent);
                    finish();
                }
                else
                {

                    String role=sessionManager.getRole();
                    if(role.equals("1")) {
                        Intent intent = new Intent(SplashScreen.this, AdminActivity.class);//user
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        finish();
                    }
                    else
                    {

                        Intent intent = new Intent(SplashScreen.this, UserSide.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        finish();
                    }

                }
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}
