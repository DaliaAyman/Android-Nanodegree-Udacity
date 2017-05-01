package com.nanodegree.dalia.bakingapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class RecipesMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes_main);

        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.recipesMainFragment, new RecipesMainFragment())
                    .commit();
        }
    }
}
