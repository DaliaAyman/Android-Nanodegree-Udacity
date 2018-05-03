package com.android.dalia.popularmovies.moviedetails;

import static com.google.common.base.Preconditions.checkNotNull;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.dalia.popularmovies.R;
import com.android.dalia.popularmovies.models.Movie;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieDetailFragment extends Fragment implements MovieDetailContract.View{

    MovieDetailContract.Presenter mPresenter;

    @BindView(R.id.poster_movie_detail)
    ImageView posterImageIv;
    @BindView(R.id.tv_title)
    TextView titleTv;
    @BindView(R.id.tv_description)
    TextView descriptionTv;
    @BindView(R.id.tv_year)
    TextView yearTv;
    @BindView(R.id.tv_rating)
    TextView ratingTv;
    @BindView(R.id.rv_trailers)
    RecyclerView trailersRecyclerView;
    @BindView(R.id.rv_reviews)
    RecyclerView reviewsRecyclerView;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    public MovieDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void showSelectedMovie(Movie clickedMovie){
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        Picasso.with(getContext()).load("http://image.tmdb.org/t/p/w185//" + clickedMovie.getPosterImage())
                .into(posterImageIv);

        titleTv.setText(clickedMovie.getTitle());
        descriptionTv.setText(clickedMovie.getOverview());
        yearTv.setText(clickedMovie.getReleaseDate().substring(0, 4));
        ratingTv.setText(String.valueOf(clickedMovie.getVoteAverage()) + "/10");

        loadTrailersAndReviews(clickedMovie.getId());
    }

    private void loadTrailersAndReviews(String movieId) {
        mPresenter.loadTrailers(movieId);
        mPresenter.loadReviews(movieId);
    }

    private void showTrailersInRecyclerView() {
    }

    private void showReviewsInRecyclerView() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movie_detail, container, false);

        ButterKnife.bind(this, view);

        mPresenter = new MovieDetailPresenter(this);

        return view;
    }

    @Override
    public void setPresenter(MovieDetailContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

}
