package com.nanodegree.dalia.bakingapp.Utilities;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * Created by Dalia on 5/15/2017.
 */

public class Globals {

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
