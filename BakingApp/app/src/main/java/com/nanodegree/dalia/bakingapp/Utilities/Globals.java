package com.nanodegree.dalia.bakingapp.Utilities;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.DisplayMetrics;

import com.google.gson.Gson;
import com.nanodegree.dalia.bakingapp.Models.Recipe;
import com.nanodegree.dalia.bakingapp.R;

/**
 * Created by Dalia on 5/15/2017.
 */

public class Globals {

    public static void saveRecipePref(Context context, int appWidgetId, Recipe recipe) {
        SharedPreferences.Editor prefs = context.getSharedPreferences(context.getString(R.string.sp_widget), 0).edit();
        Gson gson = new Gson();
        String json = gson.toJson(recipe);
        prefs.putString("recipe", json);
        prefs.apply();
    }

    public static Recipe loadRecipePref(Context context){
        SharedPreferences prefs = context.getSharedPreferences(context.getString(R.string.sp_widget), 0);
        Gson gson = new Gson();
        String json = prefs.getString("recipe", "");
        Recipe obj = gson.fromJson(json, Recipe.class);
        return obj;
    }

    public static int setLayoutSpan(Context context) {
        int span = 0;
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float dpHeight = displayMetrics.heightPixels / displayMetrics.density;
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;


        if (dpWidth < 640) {
            span = 1;

        } else span = 3;

        return span;


    }
}
