package com.android.dalia.popularmovies.data.remote;

import com.android.dalia.popularmovies.models.Movie;
import com.android.dalia.popularmovies.utils.Constants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Dalia on 11/2/2017.
 */

public class MoviesRemoteDataSource {

    public MoviesRemoteDataSource() {
    }

    public MoviesAPIService getAPI(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.MOVIES_BASE_URL)
                .addConverterFactory(new EnvelopeConverterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(MoviesAPIService.class);
    }
}
