package com.android.dalia.popularmovies;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.android.dalia.popularmovies.models.Movie;
import com.android.dalia.popularmovies.movies.MovieItemListener;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by Dalia on 9/28/2017.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> {

    private List<Movie> MovieList;
    private MovieItemListener clickListener;

    public RecyclerViewAdapter(List<Movie> movieList, MovieItemListener clickListener) {
        MovieList = movieList;
        this.clickListener = clickListener;
    }

    public RecyclerViewAdapter(List<Movie> MovieList) {
        this.MovieList = MovieList;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecyclerViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_grid_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        Movie Movie = MovieList.get(position);

        //holder.movieImageView
//        Glide.with(this)
//                .lo

        holder.itemView.setTag(Movie);
//        holder.itemView.setOnLongClickListener(longClickListener);
    }

    @Override
    public int getItemCount() {
        return MovieList.size();
    }

    public void addItems(List<Movie> MovieList){
        this.MovieList = MovieList;
        notifyDataSetChanged();
    }

    static class RecyclerViewHolder extends RecyclerView.ViewHolder{
        private ImageView movieImageView;

        public RecyclerViewHolder(View itemView) {
            super(itemView);

            movieImageView = (ImageView) itemView.findViewById(R.id.movies_grid_item_image);
        }
    }
}
