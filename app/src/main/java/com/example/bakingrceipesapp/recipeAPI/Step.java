package com.example.bakingrceipesapp.recipeAPI;

import com.google.gson.annotations.SerializedName;

public class Step {

    @SerializedName("id")
    int id;
    @SerializedName("shortDescription")
    String shortDescription;
    @SerializedName("description")
    String description;
    @SerializedName("videoURL")
    String videoURL;
    @SerializedName("thumbnailURL")
    String thumbnailURL;

    public int getId() {
        return id;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public String getVideoURL() {
        return videoURL;
    }

    public String getThumbnailURL() {
        return thumbnailURL;
    }
}
