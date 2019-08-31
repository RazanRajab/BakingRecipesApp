package com.example.bakingrceipesapp.recipeAPI;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Ingredient {

    @SerializedName("quantity")
    int quantity;
    @SerializedName("measure")
    String measure;
    @SerializedName("ingredient")
    String ingredient;

    public int getQuantity() {
        return quantity;
    }

    public String getMeasure() {
        return measure;
    }

    public String getIngredient() {
        return ingredient;
    }
}
