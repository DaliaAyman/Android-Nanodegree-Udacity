package com.android.dalia.popularmovies.moviedetails;

import static com.google.common.base.Preconditions.checkNotNull;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.dalia.popularmovies.R;
import com.android.dalia.popularmovies.models.Movie;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieDetailFragment extends Fragment implements MovieDetailContract.View{

    MovieDetailContract.Presenter mPresenter;

    @BindView(R.id.poster_movie_detail)
    ImageView posterImage;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    public MovieDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void showSelectedMovie(Movie clickedMovie){
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        Picasso.with(getContext()).load("http://image.tmdb.org/t/p/w185//" + clickedMovie.getPosterImage())
                .into(posterImage);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movie_detail, container, false);

        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void setPresenter(MovieDetailContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

}
