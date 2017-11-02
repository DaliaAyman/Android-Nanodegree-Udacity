package com.android.dalia.popularmovies.movies;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import static com.google.common.base.Preconditions.checkNotNull;
import com.android.dalia.popularmovies.R;
import com.android.dalia.popularmovies.RecyclerViewAdapter;
import com.android.dalia.popularmovies.models.Movie;
import com.android.dalia.popularmovies.utils.Constants;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MoviesFragment extends Fragment implements MoviesContract.View, MoviesPresenter.MoviesPresenterNotifyViewListener{

    MoviesContract.Presenter mPresenter;

    RecyclerViewAdapter moviesAdapter;

    @BindView(R.id.movies_grid_recycler)
    RecyclerView moviesGridRecycler;

    public MoviesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        ButterKnife.bind(this, view);

        mPresenter = new MoviesPresenter(this, this);
        setupRecyclerView();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.loadMovies(Constants.POPULARITY_DESC_SORT_BY);
    }

    @Override
    public void showMovies(List<Movie> movies) {
        Log.d("movies", "movies size = " + movies.size());
        moviesAdapter.addItems(movies);
    }

    @Override
    public void moviesLoaded(List<Movie> movies) {
        showMovies(movies);
    }

    @Override
    public void setPresenter(MoviesContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void setupRecyclerView() {
        moviesAdapter = new RecyclerViewAdapter(new ArrayList<Movie>());
        moviesGridRecycler.setAdapter(moviesAdapter);

        moviesGridRecycler.setLayoutManager(new GridLayoutManager(getContext(), 2));
    }
}
