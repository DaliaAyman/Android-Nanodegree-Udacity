package com.nanodegree.dalia.bakingapp;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.nanodegree.dalia.bakingapp.Adapters.RecipesGridMainAdapter;
import com.nanodegree.dalia.bakingapp.Models.Ingredient;
import com.nanodegree.dalia.bakingapp.Models.Recipe;
import com.nanodegree.dalia.bakingapp.Models.Step;
import com.nanodegree.dalia.bakingapp.Utilities.Constants;
import com.nanodegree.dalia.bakingapp.Utilities.RecyclerItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class RecipesMainFragment extends Fragment {

    RecyclerView recipesRecyclerView;
    RecipesGridMainAdapter recipesAdapter;
    RecyclerView.LayoutManager recipesLayoutManager;
    List<Recipe> recipesList;

    public RecipesMainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recipes_main, container, false);

        recipesRecyclerView = (RecyclerView) view.findViewById(R.id.grid_recycler_view);

        recipesLayoutManager = new GridLayoutManager(getContext(), 2);

        recipesRecyclerView.setLayoutManager(recipesLayoutManager);

        recipesList = new ArrayList<>();

        loadRecipes();

        recipesAdapter = new RecipesGridMainAdapter(getContext(), recipesList);

        recipesRecyclerView.setAdapter(recipesAdapter);

        recipesRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), recipesRecyclerView,
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent intent = new Intent(getContext(), RecipeDetailActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onItemLongClick(View view, int position) {

                    }
                }));

        return view;
    }

    private void loadRecipes(){
        Ion.with(getContext())
                .load(Constants.RECIPES_URL)
                .asJsonArray()
                .setCallback(new FutureCallback<JsonArray>() {
                    @Override
                    public void onCompleted(Exception e, JsonArray result) {
                        Log.d("Test", "json done");
                        for(int i=0; i<result.size(); i++){
                            JsonObject recipeObject = result.get(i).getAsJsonObject();
                            int id = recipeObject.get("id").getAsInt();
                            String name = recipeObject.get("name").getAsString();

                            JsonArray ingredientsJsonArray = recipeObject.get("ingredients").getAsJsonArray();
                            List<Ingredient> ingredients = loadIngredientsArray(ingredientsJsonArray);

                            JsonArray stepsJsonArray = recipeObject.get("steps").getAsJsonArray();
                            List<Step> steps = loadStepsArray(stepsJsonArray);

                            int servings = recipeObject.get("servings").getAsInt();
                            String imageURL = recipeObject.get("image").getAsString();
                            Recipe recipe = new Recipe(id, name, ingredients, steps, servings, imageURL);

                            recipesList.add(recipe);
                        }

                        recipesAdapter.setRecipesList(recipesList);
                    }
                });
    }

    private List<Ingredient> loadIngredientsArray(JsonArray ingredientsJsonArray){
        List<Ingredient> ingredients = new ArrayList<>();
        for(int i=0; i<ingredientsJsonArray.size(); i++){
            JsonObject ingredientObject = ingredientsJsonArray.get(i).getAsJsonObject();

            double quantity = ingredientObject.get("quantity").getAsDouble();
            String measure = ingredientObject.get("measure").getAsString();
            String ingredient = ingredientObject.get("ingredient").getAsString();

            Ingredient ingredientObj = new Ingredient(quantity, measure, ingredient);

            ingredients.add(ingredientObj);
        }
        return ingredients;
    }

    private List<Step> loadStepsArray(JsonArray stepsJsonArray){
        List<Step> steps = new ArrayList<>();
        for(int i=0; i<stepsJsonArray.size(); i++){
            JsonObject stepObject = stepsJsonArray.get(i).getAsJsonObject();

            int id = stepObject.get("id").getAsInt();
            String shortDescription = stepObject.get("shortDescription").getAsString();
            String description = stepObject.get("description").getAsString();
            String videoURL = stepObject.get("videoURL").getAsString();
            String thumbnailURL = stepObject.get("thumbnailURL").getAsString();

            Step stepObj = new Step(id, shortDescription, description, videoURL, thumbnailURL);

            steps.add(stepObj);
        }
        return steps;
    }
}
