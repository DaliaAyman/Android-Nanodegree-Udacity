package com.android.dalia.popularmovies.movies;

import android.util.Log;

import com.android.dalia.popularmovies.data.remote.MoviesRemoteDataSource;
import com.android.dalia.popularmovies.models.Movie;
import com.android.dalia.popularmovies.utils.Constants;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by Dalia on 10/31/2017.
 */

public class MoviesPresenter implements MoviesContract.Presenter{
    MoviesContract.View mMoviesView;
    MoviesPresenterNotifyViewListener mListener;
    MoviesRemoteDataSource moviesRemoteDataSource;

    public MoviesPresenter(MoviesContract.View mMoviesView, MoviesPresenterNotifyViewListener mListener) {

        mMoviesView = checkNotNull(mMoviesView);
        mMoviesView.setPresenter(this);

        this.mListener = mListener;
        moviesRemoteDataSource = new MoviesRemoteDataSource();

    }

    @Override
    public void loadMovies(String popularity) {
        moviesRemoteDataSource.getAPI()
                .listMovies(popularity, Constants.MOVIES_DB_API_KEY_VALUE)
                .enqueue(new Callback<List<Movie>>() {
            @Override
            public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {
                mListener.moviesLoaded(response.body());
            }

            @Override
            public void onFailure(Call<List<Movie>> call, Throwable t) {
                Log.d("MoviesPresenter", "onFailure loading movies " + t);
            }
        });
    }

    public interface MoviesPresenterNotifyViewListener{
        void moviesLoaded(List<Movie> movies);
    }
}
