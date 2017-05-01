package com.nanodegree.dalia.bakingapp;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nanodegree.dalia.bakingapp.Adapters.RecipesGridMainAdapter;
import com.nanodegree.dalia.bakingapp.Models.Recipe;
import com.nanodegree.dalia.bakingapp.Utilities.RecyclerItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class RecipesMainFragment extends Fragment {

    RecyclerView recipesRecyclerView;
    Context context;
    RecyclerView.Adapter recipesAdapter;
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

        recipesLayoutManager = new GridLayoutManager(context, 2);

        recipesRecyclerView.setLayoutManager(recipesLayoutManager);

        recipesList = new ArrayList<>();
        //TODO query for recipes
        recipesList.add(new Recipe());

        recipesAdapter = new RecipesGridMainAdapter(context, recipesList);

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

}
