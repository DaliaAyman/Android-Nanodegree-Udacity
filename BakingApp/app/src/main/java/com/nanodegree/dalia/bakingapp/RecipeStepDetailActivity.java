package com.nanodegree.dalia.bakingapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.nanodegree.dalia.bakingapp.Models.Recipe;

public class RecipeStepDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_step_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(savedInstanceState == null){
            Bundle args = new Bundle();
            Recipe recipe = (Recipe)getIntent().getSerializableExtra("recipe");
            getSupportActionBar().setTitle(recipe.getName());

            args.putSerializable("recipe", getIntent().getSerializableExtra("recipe"));
            args.putSerializable("step", getIntent().getSerializableExtra("step"));

            RecipeStepDetailFragment detailFragment = new RecipeStepDetailFragment();
            detailFragment.setArguments(args);

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.recipeStepDetailFragment, detailFragment, "detail")
                    .commit();
        }
    }
}
