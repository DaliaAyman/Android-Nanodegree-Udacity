package com.nanodegree.dalia.bakingapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class RecipeStepDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_step_detail);

        if(savedInstanceState == null){
            Bundle args = new Bundle();
            args.putSerializable("recipe", getIntent().getSerializableExtra("recipe"));

            RecipeStepDetailFragment detailFragment = new RecipeStepDetailFragment();
            detailFragment.setArguments(args);

            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.recipeStepDetailFragment, detailFragment, "detail")
                    .commit();
        }
    }
}
