package com.studio.visitsukabumi.utils.commons;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by NwP.
 */
public class PreferencesManager {
    private static PreferencesManager instance;

    /**
     * Returns singleton class instance
     */
    public static PreferencesManager getInstance() {
        if (instance == null) {
            synchronized (PreferencesManager.class) {
                if (instance == null) {
                    instance = new PreferencesManager();
                }
            }
        }
        return instance;
    }

    public static boolean saveAsString(Context context, String key, String value) {
        if (context == null || key == null || key.isEmpty()) {
            return false;
        }
        SharedPreferences prefs = PreferenceManager
                .getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(key, value.toLowerCase());
        return editor.commit();
    }

    public static String getAsString(Context context, String key) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getString(key, null);
    }

    public static boolean saveAsInteger(Context context, String key, int value) {
        if (context == null || key == null) {
            return false;
        }
        SharedPreferences prefs = PreferenceManager
                .getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(key, value);
        return editor.commit();
    }

    public static int getAsInteger(Context context, String key, int defValue) {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        return pref.getInt(key, defValue);
    }

    public static boolean saveAsBoolean(Context context, String key, Boolean value) {
        if (context == null || key == null || key.isEmpty()) {
            return false;
        }
        SharedPreferences prefs = PreferenceManager
                .getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(key, value);
        return editor.commit();
    }

    public static Boolean getAsBoolean(Context context, String key) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getBoolean(key, false);
    }

    public static void saveAuthToken(Context context, String authToken) {
        PreferencesManager.saveAsString(context, Constants.Code.TAG_PREF_AUTH_TOKEN, authToken);
    }

    public static String getAuthToken(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getString(Constants.Code.TAG_PREF_AUTH_TOKEN, null);
    }
}