package com.android.dalia.popularmovies.movies;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import static com.google.common.base.Preconditions.checkNotNull;
import com.android.dalia.popularmovies.R;
import com.android.dalia.popularmovies.RecyclerViewAdapter;
import com.android.dalia.popularmovies.models.Movie;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MoviesFragment extends Fragment implements MoviesContract.View{

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

        setupRecyclerView();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void showMovies(List<Movie> movies) {

    }

    @Override
    public void setPresenter(MoviesContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void setupRecyclerView() {
        ArrayList<Movie> movies = new ArrayList<>();
        movies.add(new Movie("", "", "", "", 9));
        moviesAdapter = new RecyclerViewAdapter(movies);

        moviesGridRecycler.setLayoutManager(new GridLayoutManager(getContext(), 2));
        moviesGridRecycler.setAdapter(moviesAdapter);
    }
}
