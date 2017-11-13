package com.android.dalia.popularmovies.movies;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialogFragment;
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
import com.android.dalia.popularmovies.moviedetails.MovieDetailActivity;
import com.android.dalia.popularmovies.movies.sortby.SortByListener;
import com.android.dalia.popularmovies.utils.Constants;
import com.android.dalia.popularmovies.movies.sortby.SortByBottomSheetDialogFragment;
import com.android.dalia.popularmovies.utils.SharedPreferencesUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MoviesFragment extends Fragment implements
        MoviesContract.View, MoviesPresenter.MoviesPresenterNotifyViewListener, SortByListener, MovieItemListener{

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
        loadMoviesAccordingToSP();
    }

    @Override
    public void showMovies(List<Movie> movies) {
        Log.d("movies", "movies size = " + movies.size());
        moviesAdapter.addItems(movies);
        moviesAdapter.notifyDataSetChanged();
    }

    @Override
    public void showSortByMenu() {
        BottomSheetDialogFragment bottomSheetDialogFragment = new SortByBottomSheetDialogFragment();
        ((SortByBottomSheetDialogFragment)bottomSheetDialogFragment).setListener(this);

        ArrayList<String> sortOptions = new ArrayList<>();
        sortOptions.add(Constants.SORT_BY_MOST_POPULAR); sortOptions.add(Constants.SORT_BY_HIGHEST_RATED);

        Bundle bundle = new Bundle();
        bundle.putStringArrayList(Constants.SORT_BY_KEY, sortOptions);
        bottomSheetDialogFragment.setArguments(bundle);

        bottomSheetDialogFragment.show(getFragmentManager(), bottomSheetDialogFragment.getTag());
    }

    @Override
    public void sortByItemClicked(String itemClicked) {
        SharedPreferencesUtils.saveSortByValueToSharedPreferences(getContext(), itemClicked);

        switch (itemClicked){
            case Constants.SORT_BY_HIGHEST_RATED:
                Log.d("Sort", Constants.SORT_BY_HIGHEST_RATED);
                break;

            case Constants.SORT_BY_MOST_POPULAR:
                Log.d("Sort", Constants.SORT_BY_MOST_POPULAR);
                break;
        }
        loadMoviesAccordingToSP();
    }

    private void loadMoviesAccordingToSP(){
        mPresenter.loadMovies(SharedPreferencesUtils.getSortByValueFromSharedPreferences(getContext()));
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
        moviesAdapter = new RecyclerViewAdapter(new ArrayList<Movie>(), this);
        moviesGridRecycler.setAdapter(moviesAdapter);

        moviesGridRecycler.setLayoutManager(new GridLayoutManager(getContext(), 2));
    }

    @Override
    public void onMovieClick(Movie clickedMovie) {
        Intent intent = new Intent(getContext(), MovieDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constants.MOVIE_KEY, clickedMovie);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void onMovieLongClick(Movie clickedMovie) {

    }
}
