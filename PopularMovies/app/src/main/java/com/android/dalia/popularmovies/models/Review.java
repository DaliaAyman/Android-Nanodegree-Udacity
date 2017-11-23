package com.android.dalia.popularmovies.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Dalia on 11/23/2017.
 */

public class Review {
    @SerializedName("id") @Expose
    String id;
    @SerializedName("author") @Expose
    String author;
    @SerializedName("content") @Expose
    String content;
    @SerializedName("url") @Expose
    String url;

    public String getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    public String getUrl() {
        return url;
    }
}
