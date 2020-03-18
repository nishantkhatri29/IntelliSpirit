package com.project.intellispirit;

import java.util.HashMap;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class UserSessionManager {

    SharedPreferences sharedPrefs;
    Editor editor;
    Context _context;
    int PRIVATE_MODE=0;
    private static final String PREFERENCE_NAME = "AppSharedPreference";
    private static final String IS_USER_LOGIN = "IsUserLoggedIn";
    public static final String KEY_USERNAME = "Username";
    public static final String KEY_PASSWORD = "Password";
    public UserSessionManager(Context context){
        this._context = context;
        sharedPrefs = _context.getSharedPreferences(PREFERENCE_NAME, PRIVATE_MODE);
        editor = sharedPrefs.edit();
    }

    public void createUserLoginSession(String username, String password){
        editor.putBoolean(IS_USER_LOGIN, true);
        editor.putString(KEY_USERNAME, username);
        editor.putString(KEY_PASSWORD, password);
    }

    public boolean checkLogin(){
        if(!this.isUserLoggedIn()){
            Intent i = new Intent(_context, LoginActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            _context.startActivity(i);
            return  true;
        }
        return false;
    }

    public HashMap<String, String> getUserDetails(){

        //Use hashmap to store user credentials
        HashMap<String, String> user = new HashMap<String, String>();

        // user name
        user.put(KEY_USERNAME, sharedPrefs.getString(KEY_USERNAME, null));

        // user email id
        user.put(KEY_PASSWORD, sharedPrefs.getString(KEY_PASSWORD, null));

        // return user
        return user;
    }

    public void logoutUser(){

        // Clearing all user data from Shared Preferences
        editor.clear();
        editor.commit();

        // After logout redirect user to Login Activity
        Intent i = new Intent(_context, LoginActivity.class);

        // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // Add new Flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // Staring Login Activity
        _context.startActivity(i);
    }

    public boolean isUserLoggedIn(){
        return sharedPrefs.getBoolean(IS_USER_LOGIN, false);
    }
}
