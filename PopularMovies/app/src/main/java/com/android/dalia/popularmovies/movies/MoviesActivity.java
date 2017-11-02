package com.android.dalia.popularmovies.movies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.android.dalia.popularmovies.R;
import com.android.dalia.popularmovies.models.Movie;

import java.util.List;

public class MoviesActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MoviesFragment moviesFragment =
                (MoviesFragment) getSupportFragmentManager().findFragmentById(R.id.main_fragment);

        // TODO if tablet

    }

}
