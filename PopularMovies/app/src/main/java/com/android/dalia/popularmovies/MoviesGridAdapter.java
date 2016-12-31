package com.android.dalia.popularmovies;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dalia on 12/31/2016.
 */

public class MoviesGridAdapter extends BaseAdapter {
    List<Movie> moviesList = new ArrayList<>();
    Context context;

    public MoviesGridAdapter(Context context, List<Movie> moviesList) {
        this.context = context;
        this.moviesList = moviesList;
    }

    @Override
    public int getCount() {
        return moviesList.size();
    }

    @Override
    public Object getItem(int i) {
        return moviesList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ImageView imageView = (ImageView) view.findViewById(R.id.movies_grid_image_item);

        Picasso.with(context)
                .load("http://image.tmdb.org/t/p/w185/" + moviesList.get(i))
                .into(imageView);

        return view;
    }
}
