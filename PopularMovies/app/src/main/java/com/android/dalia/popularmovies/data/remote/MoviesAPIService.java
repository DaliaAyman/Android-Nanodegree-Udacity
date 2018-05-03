package com.android.dalia.popularmovies.data.remote;

import com.android.dalia.popularmovies.models.Movie;
import com.android.dalia.popularmovies.models.Review;
import com.android.dalia.popularmovies.models.Trailer;
import com.android.dalia.popularmovies.utils.Constants;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Dalia on 11/1/2017.
 */

public interface MoviesAPIService {

    @GET("3/movie/{sort_by}")
    Call<List<Movie>> listMovies(@Path("sort_by") String sortBy,
                           @Query(Constants.MOVIES_API_KEY_ATTRIBUTE) String apiKey);

    @GET("3/movie/{id}/videos")
    Call<List<Trailer>> fetchTrailersOfMovie(@Path("id") String movieID,
                                             @Query(Constants.MOVIES_API_KEY_ATTRIBUTE) String apiKey);

    @GET("3/movie/{id}/reviews")
    Call<List<Review>> fetchReviewsOfMovie(@Path("id") String movieID,
                                           @Query(Constants.MOVIES_API_KEY_ATTRIBUTE) String apiKey);

}
