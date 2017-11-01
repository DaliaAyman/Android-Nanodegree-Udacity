package com.android.dalia.popularmovies.movies;
import com.android.dalia.popularmovies.BasePresenter;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by Dalia on 10/31/2017.
 */

public class MoviesPresenter implements MoviesContract.Presenter{
    MoviesContract.View mMoviesView;


    public MoviesPresenter(MoviesContract.View mMoviesView) {

        mMoviesView = checkNotNull(mMoviesView);
        mMoviesView.setPresenter(this);
    }

    @Override
    public void start() {
        loadMovies();
    }

    @Override
    public void loadMovies() {

    }
}
