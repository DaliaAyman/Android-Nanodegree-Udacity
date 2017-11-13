package com.android.dalia.popularmovies.movies;

import com.android.dalia.popularmovies.BasePresenter;
import com.android.dalia.popularmovies.BaseView;
import com.android.dalia.popularmovies.models.Movie;

import java.util.List;

/**
 * Created by Dalia on 10/31/2017.
 */

public interface MoviesContract {
    interface View extends BaseView<Presenter>{
        void setupRecyclerView();
        void showMovies(List<Movie> movies);
        void showSortByMenu();
    }

    interface Presenter extends BasePresenter{
        void loadMovies(String popularity);
    }
}
