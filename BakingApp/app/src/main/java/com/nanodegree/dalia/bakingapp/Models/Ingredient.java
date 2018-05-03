package com.nanodegree.dalia.bakingapp.Models;

import java.io.Serializable;

/**
 * Created by Dalia on 5/1/2017.
 */

public class Ingredient implements Serializable{
    private double quantity;
    private String measure;
    private String ingredient;

    public Ingredient(double quantity, String measure, String ingredient) {
        this.quantity = quantity;
        this.measure = measure;
        this.ingredient = ingredient;
    }

    public String getIngredient() {
        return ingredient;
    }

    public String getMeasure() {
        return measure;
    }

    public double getQuantity() {
        return quantity;
    }
}
