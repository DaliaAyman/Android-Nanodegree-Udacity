package com.nanodegree.dalia.bakingapp;

import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.nanodegree.dalia.bakingapp.APIConnections.RecipesAPI;
import com.nanodegree.dalia.bakingapp.Adapters.RecipesWidgetAdapter;
import com.nanodegree.dalia.bakingapp.Models.Recipe;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.nanodegree.dalia.bakingapp.Utilities.Globals.saveRecipePref;

/**
 * The configuration screen for the {@link IngredientsListWidget IngredientsListWidget} AppWidget.
 */
public class IngredientsListWidgetConfigureActivity extends Activity implements RecipesAPI.CommunicateWithUI{

    int mAppWidgetId = AppWidgetManager.INVALID_APPWIDGET_ID;
    @BindView(R.id.widget_list_recipes) ListView recipesListView;

    List<Recipe> recipesList;
    RecipesWidgetAdapter recipesWidgetAdapter;

    AdapterView.OnItemClickListener mOnItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            final Context context = IngredientsListWidgetConfigureActivity.this;

            // When an item is clicked, store the string locally
            saveRecipePref(context, mAppWidgetId, recipesList.get(i));

            // It is the responsibility of the configuration activity to update the app widget
            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
            IngredientsListWidget.updateAppWidget(context, appWidgetManager, mAppWidgetId);

            // Make sure we pass back the original appWidgetId
            Intent resultValue = new Intent();
            resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, mAppWidgetId);
            setResult(RESULT_OK, resultValue);
            finish();
        }
    };

    public IngredientsListWidgetConfigureActivity() {
        super();
    }

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        // Set the result to CANCELED.  This will cause the widget host to cancel
        // out of the widget placement if the user presses the back button.
        setResult(RESULT_CANCELED);

        setContentView(R.layout.ingredients_list_widget_configure);

        ButterKnife.bind(this);

        recipesListView.setOnItemClickListener(mOnItemClickListener);

        // Find the widget id from the intent.
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            mAppWidgetId = extras.getInt(
                    AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
        }

        // If this activity was started with an intent without an app widget ID, finish with an error.
        if (mAppWidgetId == AppWidgetManager.INVALID_APPWIDGET_ID) {
            finish();
            return;
        }

        ButterKnife.bind(this);
        getRecipes();
    }

    private void createListViewItems(List<Recipe> recipes){
        recipesList = recipes;
        recipesWidgetAdapter = new RecipesWidgetAdapter(recipes);
        recipesListView.setAdapter(recipesWidgetAdapter);
    }

    private void getRecipes(){
        RecipesAPI recipesAPI = new RecipesAPI(this);
        recipesAPI.setListener(this);
        recipesAPI.loadRecipes();
    }

    @Override
    public void notifyUI(List<Recipe> recipesList) {
        createListViewItems(recipesList);
    }
}