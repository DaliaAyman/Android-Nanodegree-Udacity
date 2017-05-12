package com.nanodegree.dalia.bakingapp.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nanodegree.dalia.bakingapp.Models.Step;
import com.nanodegree.dalia.bakingapp.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Dalia on 5/6/2017.
 */

public class StepsAdapter extends RecyclerView.Adapter<StepsAdapter.ViewHolder>{
    Context context;
    List<Step> stepsList;

    public StepsAdapter(Context context, List<Step> stepsList) {
        this.context = context;
        this.stepsList = stepsList;
    }

    @Override
    public StepsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_step_description, parent, false);

        StepsAdapter.ViewHolder holder = new StepsAdapter.ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(StepsAdapter.ViewHolder holder, int position) {
        Step step = stepsList.get(position);
        holder.stepDescription.setText(step.getId() + ". " + step.getShortDescription());
    }

    public void setStepsList(List<Step> stepsList) {
        this.stepsList = stepsList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return stepsList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.stepDescription) TextView stepDescription;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
