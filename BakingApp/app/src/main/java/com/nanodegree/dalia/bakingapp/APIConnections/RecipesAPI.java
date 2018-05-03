package com.nanodegree.dalia.bakingapp.APIConnections;

import android.content.Context;
import android.util.Log;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.nanodegree.dalia.bakingapp.Models.Ingredient;
import com.nanodegree.dalia.bakingapp.Models.Recipe;
import com.nanodegree.dalia.bakingapp.Models.Step;
import com.nanodegree.dalia.bakingapp.Utilities.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dalia on 5/15/2017.
 */

public class RecipesAPI {
    CommunicateWithUI listener;
    Context context;
    List<Recipe> recipesList;

    public RecipesAPI(Context context) {
        this.context = context;
    }

    public void loadRecipes(){
        recipesList = new ArrayList<>();

        Ion.with(context)
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
                        listener.notifyUI(recipesList);
                    }
                });
    }

    public interface CommunicateWithUI{
        public void notifyUI(List<Recipe> recipes);
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

    public void setListener(CommunicateWithUI listener) {
        this.listener = listener;
    }
}
