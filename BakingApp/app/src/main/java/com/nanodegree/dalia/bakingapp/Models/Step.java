package com.nanodegree.dalia.bakingapp.Models;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Dalia on 5/1/2017.
 */

public class Step implements Serializable{
    private int id;
    private String shortDescription;
    private String description;
    private String videoURL;
    private String thumbnailURL;

    public Step(int id, String shortDescription, String description, String videoURL, String thumbnailURL) {
        this.id = id;
        this.shortDescription = shortDescription;
        this.description = description;
        this.videoURL = videoURL;
        this.thumbnailURL = thumbnailURL;
    }

    public Step incrementStep(List<Step> recipeSteps){
        Step resultStep;
        int currentID = getId();
        resultStep = getStepByID(currentID+1, recipeSteps);
        return resultStep;
    }

    public Step decrementStep(List<Step> recipeSteps){
        Step resultStep;
        int currentID = getId();
        resultStep = getStepByID(currentID-1, recipeSteps);
        return resultStep;
    }

    public Step getStepByID(int id, List<Step> recipeSteps){
        for(int i=0; i<recipeSteps.size(); i++){
            Step step = recipeSteps.get(i);
            if(step.getId() == id){
                return step;
            }
        }
        return null;
    }

    public boolean isLastStep(List<Step> recipeSteps){
        Step lastStep = recipeSteps.get(recipeSteps.size() - 1);
        if(lastStep.getId() == getId()){
            return true;
        }
        return false;
    }

    public boolean isFirstStep(List<Step> recipeSteps){
        Step firstStep = recipeSteps.get(0);
        if(firstStep.getId() == getId()){
            return true;
        }
        return false;
    }

    public int getId() {
        return id;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public String getVideoURL() {
        return videoURL;
    }

    public String getThumbnailURL() {
        return thumbnailURL;
    }
}
