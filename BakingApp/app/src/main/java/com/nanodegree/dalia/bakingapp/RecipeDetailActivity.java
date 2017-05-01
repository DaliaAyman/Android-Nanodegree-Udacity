package com.nanodegree.dalia.bakingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.nanodegree.dalia.bakingapp.Models.Recipe;

public class RecipeDetailActivity extends AppCompatActivity implements RecipeDetailFragment.OnFragmentInteractionListener{

    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        if(findViewById(R.id.recipeStepDetailFragment) != null){
            mTwoPane = true;

            if(savedInstanceState == null){
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.recipeStepDetailFragment, new RecipeStepDetailFragment(), "details")
                        .commit();
            }
        }else{
            mTwoPane = false;
        }

        Log.d("Main", "mTwoPane = " + mTwoPane);
    }

    @Override
    public void onFragmentInteraction(Recipe recipe) {
        if (mTwoPane) {
            // In two-pane mode, show the detail view in this activity by
            // adding or replacing the detail fragment using a
            // fragment transaction.
            Bundle args = new Bundle();
            args.putSerializable("recipe", recipe);

            RecipeStepDetailFragment fragment = new RecipeStepDetailFragment();
            fragment.setArguments(args);

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.recipeStepDetailFragment, fragment, "detail")
                    .commit();
        } else {

            Intent intent = new Intent(this, RecipeStepDetailActivity.class);

            Bundle args = new Bundle();
            args.putSerializable("recipe", recipe);

            intent.putExtras(args);

            startActivity(intent);
        }
    }
}
