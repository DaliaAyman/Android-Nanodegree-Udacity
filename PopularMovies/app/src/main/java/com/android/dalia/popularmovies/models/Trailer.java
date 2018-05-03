package com.android.dalia.popularmovies.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Dalia on 11/23/2017.
 */

public class Trailer {
    @SerializedName("id") @Expose
    String id;
    @SerializedName("key") @Expose
    String key;
    @SerializedName("name") @Expose
    String name;
    @SerializedName("type") @Expose
    String type;

    public String getId() {
        return id;
    }

    public String getKey() {
        return key;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }
}
