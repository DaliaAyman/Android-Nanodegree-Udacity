package com.android.dalia.popularmovies.moviedetails;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.android.dalia.popularmovies.R;
import com.android.dalia.popularmovies.models.Movie;
import com.android.dalia.popularmovies.utils.Constants;

public class MovieDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        if(getIntent() != null){
            if(getIntent().getExtras().getSerializable(Constants.MOVIE_KEY) != null) {
                Movie clickedMovie = (Movie) getIntent().getExtras().getSerializable(Constants.MOVIE_KEY);

                Log.d("Movie", "clickedMovie: " + clickedMovie.getTitle());
            }
        }
    }
}
