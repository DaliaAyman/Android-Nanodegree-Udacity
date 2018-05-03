package com.android.dalia.popularmovies.moviedetails;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.android.dalia.popularmovies.R;
import com.android.dalia.popularmovies.models.Movie;
import com.android.dalia.popularmovies.utils.Constants;

public class MovieDetailActivity extends AppCompatActivity {

    MovieDetailFragment movieDetailFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        movieDetailFragment = (MovieDetailFragment) getSupportFragmentManager().findFragmentById(R.id.detail_fragment);

        initToolbar();

        if(getIntent() != null){
            if(getIntent().getExtras().getSerializable(Constants.MOVIE_KEY) != null) {
                Movie clickedMovie = (Movie) getIntent().getExtras().getSerializable(Constants.MOVIE_KEY);

                Log.d("Movie", "clickedMovie: " + clickedMovie.getTitle());
                movieDetailFragment.showSelectedMovie(clickedMovie);
            }
        }
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            //actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }
}
