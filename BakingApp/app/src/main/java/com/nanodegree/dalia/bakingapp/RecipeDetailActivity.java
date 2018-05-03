package com.nanodegree.dalia.bakingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.nanodegree.dalia.bakingapp.Models.Recipe;
import com.nanodegree.dalia.bakingapp.Models.Step;

public class RecipeDetailActivity extends AppCompatActivity implements RecipeDetailFragment.OnFragmentInteractionListener{

    private boolean mTwoPane;
    Recipe recipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recipe = (Recipe)getIntent().getSerializableExtra("recipe");
        getSupportActionBar().setTitle(recipe.getName());

        if(savedInstanceState == null){
            RecipeDetailFragment recipeDetailFragment = new RecipeDetailFragment();
            Bundle args = new Bundle();
            args.putSerializable("recipe", recipe);
            recipeDetailFragment.setArguments(args);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.recipeDetailMainFragment, recipeDetailFragment, "main")
                    .commit();
        }

        if(findViewById(R.id.recipeStepDetailFragment) != null){
            mTwoPane = true;

            if(savedInstanceState == null){
                RecipeStepDetailFragment recipeStepDetailFragment = new RecipeStepDetailFragment();
                Bundle args = new Bundle();
                args.putSerializable("recipe", recipe);
                recipeStepDetailFragment.setArguments(args);
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.recipeStepDetailFragment, recipeStepDetailFragment, "details")
                        .commit();
            }
        }else{
            mTwoPane = false;
        }

        Log.d("Main", "mTwoPane = " + mTwoPane);
    }

    @Override
    public void onFragmentInteraction(Step step) {
        if (mTwoPane) {
            // In two-pane mode, show the detail view in this activity by
            // adding or replacing the detail fragment using a
            // fragment transaction.
            Bundle args = new Bundle();
            args.putSerializable("step", step);

            RecipeStepDetailFragment fragment = new RecipeStepDetailFragment();
            fragment.setArguments(args);

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.recipeStepDetailFragment, fragment, "detail")
                    .commit();
        } else {

            Intent intent = new Intent(this, RecipeStepDetailActivity.class);

            Bundle args = new Bundle();
            args.putSerializable("step", step);
            args.putSerializable("recipe", recipe);

            intent.putExtras(args);

            startActivity(intent);
        }
    }
}
