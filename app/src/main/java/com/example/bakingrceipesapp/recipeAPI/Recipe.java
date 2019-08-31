package com.example.bakingrceipesapp.recipeAPI;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Recipe {

    @SerializedName("id")
    int id;
    @SerializedName("name")
    String name;
    @SerializedName("ingredients")
    List<Ingredient> ingredients;
    @SerializedName("steps")
    List<Step> steps;
    @SerializedName("servings")
    int servings;
    @SerializedName("image")
    String image;

    public Recipe(int id, String name, List<Ingredient> ingredients, List<Step> steps,
                  int servings, String image) {
        this.id = id;
        this.name = name;
        this.ingredients = ingredients;
        this.steps = steps;
        this.servings = servings;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public List<Step> getSteps() {
        return steps;
    }

    public int getServings() {
        return servings;
    }

    public String getImage() {
        return image;
    }
}
