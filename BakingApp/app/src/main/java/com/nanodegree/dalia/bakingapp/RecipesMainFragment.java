package com.nanodegree.dalia.bakingapp;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nanodegree.dalia.bakingapp.APIConnections.RecipesAPI;
import com.nanodegree.dalia.bakingapp.Adapters.RecipesGridMainAdapter;
import com.nanodegree.dalia.bakingapp.Models.Recipe;
import com.nanodegree.dalia.bakingapp.Utilities.Globals;
import com.nanodegree.dalia.bakingapp.Utilities.RecyclerItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipesMainFragment extends Fragment implements RecipesAPI.CommunicateWithUI{

    @BindView(R.id.grid_recycler_view) RecyclerView recipesRecyclerView;
    RecipesGridMainAdapter recipesAdapter;
    int layoutSpan;
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

        ButterKnife.bind(this, view);

        layoutSpan = Globals.setLayoutSpan(getActivity());
        recipesLayoutManager = new GridLayoutManager(getContext(), layoutSpan);

        recipesRecyclerView.setLayoutManager(recipesLayoutManager);

        recipesList = new ArrayList<>();

        RecipesAPI recipesAPI = new RecipesAPI(getContext());
        recipesAPI.setListener(this);
        recipesAPI.loadRecipes();

        recipesAdapter = new RecipesGridMainAdapter(getContext(), recipesList);

        return view;
    }

    RecyclerItemClickListener recyclerItemClickListener = new RecyclerItemClickListener(getContext(), recipesRecyclerView,
            new RecyclerItemClickListener.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    Intent intent = new Intent(getContext(), RecipeDetailActivity.class);
                    Recipe recipe = recipesList.get(position);

                    Bundle args = new Bundle();
                    args.putSerializable("recipe", recipe);

                    intent.putExtras(args);

                    startActivity(intent);
                }

                @Override
                public void onItemLongClick(View view, int position) {

                }
            });

    @Override
    public void notifyUI(List<Recipe> recipesList) {
        this.recipesList = recipesList;

        recipesAdapter.setRecipesList(recipesList);

        recipesRecyclerView.setAdapter(recipesAdapter);

        recipesRecyclerView.addOnItemTouchListener(recyclerItemClickListener);
    }
}
