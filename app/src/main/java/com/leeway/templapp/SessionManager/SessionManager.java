package com.leeway.templapp.SessionManager;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by intellyelabs on 17/04/17.
 */

public class SessionManager {

    SharedPreferences preferences;
    Context context;
    SharedPreferences.Editor editor;
    public static final String PREFF_NAME = "DEVASAM";
    int PRIVATE_MODE = 0;

    public SessionManager(Context context) {
        this.context = context;
        if(context!=null) {
            preferences = context.getSharedPreferences(PREFF_NAME, PRIVATE_MODE);
            editor = preferences.edit();
        }


    }

    public void SaveCredentials(String username, String password) {
        editor.putString("USERNAME", username);
        editor.putString("PASSWORD", password);
        editor.commit();
    }

    public String getUsername() {
        String user = preferences.getString("USERNAME", "1");
        return user;
    }

    public String getPassword() {
        String pass = preferences.getString("PASSWORD", "1");
        return pass;
    }




    public void SaveFireBasetoken(String save) {
        editor.putString("FIREBASE_TOKEN", save);
        editor.commit();

    }

    public String GetFireBasetoken() {
        String ss = preferences.getString("FIREBASE_TOKEN", "");
        return ss;

    }  public String getUserId() {
        String ss = preferences.getString("USER_ID", "");
        return ss;

    }
    public void SaveUseId(String save) {
        editor.putString("USER_ID", save);
        editor.commit();

    }

    public String getRole() {
        String ss = preferences.getString("ROLE", "");
        return ss;

    }
    public void SaveRole(String save) {
        editor.putString("ROLE", save);
        editor.commit();

    }






    public void ClearAll() {
        editor.clear();
        editor.commit();
    }


}


