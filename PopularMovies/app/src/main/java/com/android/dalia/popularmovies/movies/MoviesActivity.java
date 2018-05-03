package com.android.dalia.popularmovies.movies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.dalia.popularmovies.R;
import com.android.dalia.popularmovies.models.Movie;

import java.util.List;

public class MoviesActivity extends AppCompatActivity{

    MoviesFragment moviesFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        moviesFragment = (MoviesFragment) getSupportFragmentManager().findFragmentById(R.id.main_fragment);

        // TODO if tablet

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemSelected = item.getItemId();
        switch (itemSelected){
            case R.id.action_sort_by:
                moviesFragment.showSortByMenu();
                break;
        }
        return true;
    }

}
