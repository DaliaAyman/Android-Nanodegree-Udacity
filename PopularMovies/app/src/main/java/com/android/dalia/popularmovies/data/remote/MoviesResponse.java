package com.android.dalia.popularmovies.data.remote;

import com.android.dalia.popularmovies.models.Movie;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Dalia on 11/2/2017.
 */

public class MoviesResponse {
    @SerializedName("page")
    @Expose
    public Integer page;
    @SerializedName("total_results")
    @Expose
    public Integer totalResults;
    @SerializedName("total_pages")
    @Expose
    public Integer totalPages;
    @SerializedName("results")
    @Expose
    public List<Movie> results = null;

    public List<Movie> getResults() {
        return results;
    }
}
