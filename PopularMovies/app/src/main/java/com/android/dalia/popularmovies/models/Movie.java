package com.android.dalia.popularmovies.models;

/**
 * Created by Dalia on 12/31/2016.
 */

public class Movie {
    String title;
    String posterImage;
    String overView;
    double voteAverage;
    String releaseDate;

    public Movie(String title, String overView, String posterImage, String releaseDate, double voteAverage) {
        this.title = title;
        this.overView = overView;
        this.posterImage = posterImage;
        this.releaseDate = releaseDate;
        this.voteAverage = voteAverage;
    }

    public String getOverView() {
        return overView;
    }

    public void setOverView(String overView) {
        this.overView = overView;
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
