package com.nanodegree.dalia.bakingapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nanodegree.dalia.bakingapp.Adapters.StepsAdapter;
import com.nanodegree.dalia.bakingapp.Models.Ingredient;
import com.nanodegree.dalia.bakingapp.Models.Recipe;
import com.nanodegree.dalia.bakingapp.Models.Step;
import com.nanodegree.dalia.bakingapp.Utilities.SimpleDividerItemDecoration;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class RecipeDetailFragment extends Fragment {
    @BindView(R.id.ingredients_title) TextView ingredientsTitle;
    @BindView(R.id.ingredients_value) TextView ingredientsValue;
    @BindView(R.id.steps_title) TextView stepsTitle;
    @BindView(R.id.stepsRecyclerView) RecyclerView stepsRecyclerView;

    private OnFragmentInteractionListener mListener;

    public RecipeDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recipe_detail, container, false);

        ButterKnife.bind(this, view);


        Bundle bundle = getArguments();
        if(bundle != null){

            ingredientsTitle.setText("Ingredients");
            stepsTitle.setText("Steps");

            Recipe recipe = (Recipe) bundle.get("recipe");
            Log.d("Recipe", "Recipe Name: " + recipe.getName());

            List<Ingredient> ingredientArrayList = recipe.getIngredientsList();
            listIngredientsInView(ingredientArrayList);

            List<Step> stepsList = recipe.getStepsList();
            listStepsInRecyclerView(stepsList);


        }

        return view;
    }

    private void listIngredientsInView(List<Ingredient> ingredientList){
        for(int i=0; i<ingredientList.size(); i++){
            Ingredient ingredient = ingredientList.get(i);

            ingredientsValue.append(ingredient.getQuantity() + " " + ingredient.getMeasure() + ", " + ingredient.getIngredient() + "\n");
        }
    }

    private void listStepsInRecyclerView(List<Step> stepsList){
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        stepsRecyclerView.setLayoutManager(layoutManager);

        stepsRecyclerView.addItemDecoration(new SimpleDividerItemDecoration(getResources()));

        StepsAdapter stepsAdapter = new StepsAdapter(getContext(), stepsList);
        stepsRecyclerView.setAdapter(stepsAdapter);
    }

    public void onButtonPressed(Recipe recipe) {
        if (mListener != null) {
            mListener.onFragmentInteraction(recipe);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Recipe recipe);
    }
}
