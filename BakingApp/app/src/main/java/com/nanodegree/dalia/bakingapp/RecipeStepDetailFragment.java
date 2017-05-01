package com.nanodegree.dalia.bakingapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nanodegree.dalia.bakingapp.Models.Recipe;


/**
 * A simple {@link Fragment} subclass.
 */
public class RecipeStepDetailFragment extends Fragment {


    public RecipeStepDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recipe_step_detail, container, false);

        Bundle bundle = getArguments();
        if(bundle != null){

            Recipe recipe = (Recipe) bundle.get("recipe");
            Log.d("Recipe", "Recipe Name: " + recipe.getName());

        }

        return view;
    }

}
