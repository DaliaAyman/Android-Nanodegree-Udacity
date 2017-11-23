package com.android.dalia.popularmovies.moviedetails;

import com.android.dalia.popularmovies.BasePresenter;
import com.android.dalia.popularmovies.BaseView;
import com.android.dalia.popularmovies.models.Movie;
import com.android.dalia.popularmovies.movies.MoviesContract;

import java.util.List;

/**
 * Created by Dalia on 11/13/2017.
 */

public interface MovieDetailContract {
    interface View extends BaseView<MovieDetailContract.Presenter> {
        void showSelectedMovie(Movie movie);
    }

    interface Presenter extends BasePresenter {
        void loadTrailers(String movieId);
        void loadReviews(String movieId);
    }
}
