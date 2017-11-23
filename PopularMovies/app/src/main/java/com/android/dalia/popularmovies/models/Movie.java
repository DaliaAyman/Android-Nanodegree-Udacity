package com.android.dalia.popularmovies.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Dalia on 12/31/2016.
 */

public class Movie implements Serializable{
    @SerializedName("id") @Expose
    String id;
    @SerializedName("title") @Expose
    String title;
    @SerializedName("poster_path") @Expose
    String posterImage;
    @SerializedName("overview") @Expose
    String overview;
    @SerializedName("vote_average") @Expose
    double voteAverage;
    @SerializedName("release_date") @Expose
    String releaseDate;

    public Movie(String title, String overview, String posterImage, String releaseDate, double voteAverage) {
        this.title = title;
        this.overview = overview;
        this.posterImage = posterImage;
        this.releaseDate = releaseDate;
        this.voteAverage = voteAverage;
    }

    public String getId() {
        return id;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPosterImage() {
        return posterImage;
    }

    public void setPosterImage(String posterImage) {
        this.posterImage = posterImage;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(double voteAverage) {
        this.voteAverage = voteAverage;
    }
}
