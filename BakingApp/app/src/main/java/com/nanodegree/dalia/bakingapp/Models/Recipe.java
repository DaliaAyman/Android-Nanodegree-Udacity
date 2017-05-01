package com.nanodegree.dalia.bakingapp.Models;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Dalia on 5/1/2017.
 */

public class Recipe implements Serializable {
    private int id;
    private String name;
    private List<Ingredient> ingredientsList;
    private List<Step> stepsList;
    private int servingsNo;
    private String imageURL;

    public Recipe(int id, String name, List<Ingredient> ingredientsList, List<Step> stepsList, int servingsNo, String imageURL) {
        this.id = id;
        this.name = name;
        this.ingredientsList = ingredientsList;
        this.stepsList = stepsList;
        this.servingsNo = servingsNo;
        this.imageURL = imageURL;
    }

    public void setIngredientsList(List<Ingredient> ingredientsList) {
        this.ingredientsList = ingredientsList;
    }

    public void setStepsList(List<Step> stepsList) {
        this.stepsList = stepsList;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImageURL() {
        return imageURL;
    }

    public List<Step> getStepsList() {
        return stepsList;
    }

    public List<Ingredient> getIngredientsList() {
        return ingredientsList;
    }

    public int getServingsNo() {
        return servingsNo;
    }
}
