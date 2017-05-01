package com.nanodegree.dalia.bakingapp.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nanodegree.dalia.bakingapp.Models.Recipe;
import com.nanodegree.dalia.bakingapp.R;

import java.util.List;

/**
 * Created by Dalia on 5/1/2017.
 */

public class RecipesGridMainAdapter extends RecyclerView.Adapter<RecipesGridMainAdapter.ViewHolder>{
    Context context;
    List<Recipe> recipesList;

    public RecipesGridMainAdapter(Context context, List<Recipe> recipesList) {
        this.context = context;
        this.recipesList = recipesList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recipe_main, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return recipesList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
