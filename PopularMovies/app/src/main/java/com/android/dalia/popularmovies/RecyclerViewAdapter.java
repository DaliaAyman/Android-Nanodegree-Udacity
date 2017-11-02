package com.android.dalia.popularmovies;

import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.android.dalia.popularmovies.models.Movie;
import com.android.dalia.popularmovies.movies.MovieItemListener;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Dalia on 9/28/2017.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> {

    private List<Movie> movieList;
    private MovieItemListener clickListener;

    public RecyclerViewAdapter(List<Movie> movieList, MovieItemListener clickListener) {
        this.movieList = movieList;
        this.clickListener = clickListener;
    }

    public RecyclerViewAdapter(List<Movie> MovieList) {
        this.movieList = MovieList;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecyclerViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_grid_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        Movie movie = movieList.get(position);

        String moviePoster = movie.getPosterImage();

        Picasso.with(holder.itemView.getContext()).load("http://image.tmdb.org/t/p/w185//" + moviePoster)
//                .placeholder(R.drawable.movie_loading)
                .into(holder.movieImageView);

        holder.itemView.setTag(movie);
//        holder.itemView.setOnLongClickListener(longClickListener);
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void addItems(List<Movie> movieList){
        this.movieList = movieList;
        notifyDataSetChanged();
    }

    class RecyclerViewHolder extends RecyclerView.ViewHolder{
        private ImageView movieImageView;

        public RecyclerViewHolder(View itemView) {
            super(itemView);

            movieImageView = (ImageView) itemView.findViewById(R.id.movies_grid_item_image);
        }
    }
}
