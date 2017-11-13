package com.android.dalia.popularmovies.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Dalia on 11/13/2017.
 */

public class SharedPreferencesUtils {
    static String SortByPreferences = "SortBy";

    public static void saveSortByValueToSharedPreferences(Context context, String value){
        SharedPreferences sharedpreferences = context.getSharedPreferences(SortByPreferences, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(Constants.SORT_BY_KEY, value);
        editor.commit();
    }

    public static String getSortByValueFromSharedPreferences(Context context){
        SharedPreferences sharedpreferences = context.getSharedPreferences(SortByPreferences, Context.MODE_PRIVATE);
        return sharedpreferences.getString(Constants.SORT_BY_KEY, Constants.SORT_BY_MOST_POPULAR);
    }
}
