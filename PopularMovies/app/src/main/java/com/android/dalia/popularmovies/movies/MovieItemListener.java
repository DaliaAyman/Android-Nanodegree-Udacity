package com.android.dalia.popularmovies.movies;

import com.android.dalia.popularmovies.models.Movie;

/**
 * Created by Dalia on 11/1/2017.
 */

public interface MovieItemListener {
    void onMovieClick(Movie clickedMovie);
    void onMovieLongClick(Movie clickedMovie);
}
