package com.turgayulutas.marketim.Utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Set;

public class Preferences {

    public static SharedPreferences getPrefs(Context context) {
        String SHARED_PREFS_FILE_NAME = "marketimData";
        return context.getSharedPreferences(SHARED_PREFS_FILE_NAME, Context.MODE_PRIVATE);
    }

    //Save Booleans
    public static void save(Context context, String key, boolean value) {
        getPrefs(context).edit().putBoolean(key, value).apply();
    }

    //Get Booleans
    public static boolean getBoolean(Context context, String key) {
        return getPrefs(context).getBoolean(key, false);
    }

    //Get Booleans if not found return a predefined default value
    public static boolean getBoolean(Context context, String key, boolean defaultValue) {
        return getPrefs(context).getBoolean(key, defaultValue);
    }


    //Strings
    public static void save(Context context, String key, String value) {
        getPrefs(context).edit().putString(key, value).apply();
    }

    public static String getString(Context context, String key) {
        return getPrefs(context).getString(key, "");
    }

    public static String getString(Context context, String key, String defaultValue) {
        return getPrefs(context).getString(key, defaultValue);
    }


    //Integers
    public static void save(Context context, String key, int value) {
        getPrefs(context).edit().putInt(key, value).apply();
    }

    public static int getInt(Context context, String key) {
        return getPrefs(context).getInt(key, 0);
    }

    public static int getInt(Context context, String key, int defaultValue) {
        return getPrefs(context).getInt(key, defaultValue);
    }


    //Floats
    public static void save(Context context, String key, float value) {
        getPrefs(context).edit().putFloat(key, value).apply();
    }

    public static float getFloat(Context context, String key) {
        return getPrefs(context).getFloat(key, 0);
    }

    public static float getFloat(Context context, String key, float defaultValue) {
        return getPrefs(context).getFloat(key, defaultValue);
    }


    //Longs
    public static void save(Context context, String key, long value) {
        getPrefs(context).edit().putLong(key, value).apply();
    }

    public static long getLong(Context context, String key) {
        return getPrefs(context).getLong(key, 0);
    }

    public static long getLong(Context context, String key, long defaultValue) {
        return getPrefs(context).getLong(key, defaultValue);
    }


    //StringSets
    public static void save(Context context, String key, Set<String> value) {
        getPrefs(context).edit().putStringSet(key, value).apply();
    }

    public static Set<String> getStringSet(Context context, String key) {
        return getPrefs(context).getStringSet(key, null);
    }

    public static Set<String> getStringSet(Context context, String key, Set<String> defaultValue) {
        return getPrefs(context).getStringSet(key, defaultValue);
    }


    //ClearUser
    public static boolean clearData(Context context) {
        return getPrefs(context).edit().clear().commit();
    }
}