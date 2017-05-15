package com.nanodegree.dalia.bakingapp;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.google.gson.Gson;
import com.nanodegree.dalia.bakingapp.Models.Ingredient;
import com.nanodegree.dalia.bakingapp.Models.Recipe;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dalia on 5/14/2017.
 */

public class ListWidgetService extends RemoteViewsService {
    private static final String TAG = ListWidgetService.class.getSimpleName();

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new ListRemoteViewsFactory(this.getApplicationContext(), intent);
    }


    public static class ListRemoteViewsFactory implements RemoteViewsFactory {

        private Recipe mRecipe;
        private int mAppWidgetId;
        private Context context;
        private Intent intent;
        private List<String> strings = new ArrayList<>();

        public ListRemoteViewsFactory(Context context, Intent intent) {
            this.context = context;
            mAppWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
        }


        @Override
        public boolean hasStableIds() {
            return true;
        }

        @Override
        public RemoteViews getLoadingView() {
            return null;
        }

        @Override
        public void onDestroy() {
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public void onDataSetChanged() {
        }

        @Override
        public void onCreate() {
            Gson gson = new Gson();
            SharedPreferences sharedPreferences = context.getSharedPreferences(context.getString(R.string.sp_widget),0);
            mRecipe =  gson.fromJson(sharedPreferences.getString(String.valueOf(mAppWidgetId),null),Recipe.class);
        }

        @Override
        public int getCount() {
            return mRecipe.getIngredientsList().size();
        }

        @Override
        public RemoteViews getViewAt(int position){
            Ingredient ingredient = mRecipe.getIngredientsList().get(position);
            RemoteViews rv = new RemoteViews(context.getPackageName(), R.layout.item_widget_ingredient);
            rv.setTextViewText(R.id.widget_ingredient_item_details, ingredient.toString());
            return rv;
        }

        @Override
        public int getViewTypeCount() {
            return 1;
        }
    }
}
