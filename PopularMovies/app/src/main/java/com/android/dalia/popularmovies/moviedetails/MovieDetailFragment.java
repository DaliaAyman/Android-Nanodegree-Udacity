package com.android.dalia.popularmovies.moviedetails;

import static com.google.common.base.Preconditions.checkNotNull;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.dalia.popularmovies.R;

public class MovieDetailFragment extends Fragment implements MovieDetailContract.View{

    MovieDetailContract.Presenter mPresenter;

    public MovieDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_detail, container, false);
    }

    @Override
    public void setPresenter(MovieDetailContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }
}
