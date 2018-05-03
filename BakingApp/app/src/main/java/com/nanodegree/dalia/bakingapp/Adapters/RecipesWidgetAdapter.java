package com.nanodegree.dalia.bakingapp.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.nanodegree.dalia.bakingapp.Models.Recipe;
import com.nanodegree.dalia.bakingapp.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Dalia on 5/15/2017.
 */

public class RecipesWidgetAdapter extends BaseAdapter {
    @BindView(R.id.widget_ingredient_item_details)
    TextView recipeItemText;

    private List<Recipe> recipes;

    public RecipesWidgetAdapter(List<Recipe> recipes) {
        this.recipes = recipes;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_widget_ingredient, viewGroup, false);

        ButterKnife.bind(this, view);
        recipeItemText.setText(recipes.get(i).getName());

        return view;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public Recipe getItem(int i) {
        return recipes.get(i);
    }

    @Override
    public int getCount() {
        return recipes.size();
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
        notifyDataSetChanged();
    }
}
