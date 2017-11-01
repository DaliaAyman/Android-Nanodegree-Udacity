package com.android.dalia.popularmovies;

/**
 * Created by Dalia on 10/31/2017.
 */

public interface BaseView<T extends BasePresenter> {
    void setPresenter(T presenter);
}
