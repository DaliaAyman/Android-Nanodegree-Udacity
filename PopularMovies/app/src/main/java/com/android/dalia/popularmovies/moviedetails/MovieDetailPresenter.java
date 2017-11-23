package com.android.dalia.popularmovies.moviedetails;

import android.util.Log;

import com.android.dalia.popularmovies.data.remote.MoviesRemoteDataSource;
import com.android.dalia.popularmovies.models.Review;
import com.android.dalia.popularmovies.models.Trailer;
import com.android.dalia.popularmovies.movies.MoviesContract;
import com.android.dalia.popularmovies.utils.Constants;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by Dalia on 11/13/2017.
 */

public class MovieDetailPresenter implements MovieDetailContract.Presenter{
    MovieDetailContract.View mMovieDetailView;
    MoviesRemoteDataSource moviesRemoteDataSource;

    public MovieDetailPresenter(MovieDetailContract.View movieDetailView) {
        mMovieDetailView = checkNotNull(movieDetailView);
        mMovieDetailView.setPresenter(this);

        moviesRemoteDataSource = new MoviesRemoteDataSource();
    }

    @Override
    public void loadTrailers(String movieId) {
        moviesRemoteDataSource.getAPI().fetchTrailersOfMovie(movieId, Constants.MOVIES_DB_API_KEY_VALUE)
                .enqueue(new Callback<List<Trailer>>() {
            @Override
            public void onResponse(Call<List<Trailer>> call, Response<List<Trailer>> response) {
                List<Trailer> trailers = response.body();
                Log.d("Trailers", "Trailer 1: " + trailers.get(0).getName());
            }

            @Override
            public void onFailure(Call<List<Trailer>> call, Throwable t) {

            }
        });
    }

    @Override
    public void loadReviews(String movieId) {
        moviesRemoteDataSource.getAPI().fetchReviewsOfMovie(movieId, Constants.MOVIES_DB_API_KEY_VALUE)
                .enqueue(new Callback<List<Review>>() {
                    @Override
                    public void onResponse(Call<List<Review>> call, Response<List<Review>> response) {
                        List<Review> reviews = response.body();
                        Log.d("Reviews", "Review 1: " + reviews.get(0).getContent());

                    }

                    @Override
                    public void onFailure(Call<List<Review>> call, Throwable t) {

                    }
                });
    }
}
