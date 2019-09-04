package com.example.bakingrceipesapp;

import android.content.Intent;

import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.example.bakingrceipesapp.recipeAPI.Ingredient;
import com.example.bakingrceipesapp.recipeAPI.Recipe;
import com.example.bakingrceipesapp.recipeAPI.Step;
import com.google.gson.Gson;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static androidx.test.espresso.assertion.ViewAssertions.matches;

@RunWith(AndroidJUnit4.class)
public class SelectStepFragmentTest {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityTestRule =
            new ActivityTestRule<>(MainActivity.class);

    @Rule
    public ActivityTestRule<SelectStepActivity> activityTestRule =
            new ActivityTestRule<SelectStepActivity>(SelectStepActivity.class){
                @Override
                protected Intent getActivityIntent() {
                    Intent intent = new Intent(mainActivityTestRule.getActivity(),SelectStepActivity.class);
                    List<Ingredient> ingredients = new ArrayList<>();
                    ingredients.add(new Ingredient(2,"CUP","Graham Cracker crumbs"));
                    List<Step> steps = new ArrayList<>();
                    steps.add(new Step(0,"Recipe Introduction",
                            "Recipe Introduction","", ""));
                    Recipe r = new Recipe(1, "Nutella Pie",ingredients,steps,8,"");
                    Gson gson = new Gson();
                    intent.putExtra(Recipe.class.getName(),gson.toJson(r));
                    return intent;
                }
            };


    @Test
    public void clickOnStep(){

        onView(withId(R.id.steps_recycler_view))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.description)).check(matches(withText("Recipe Introduction")));
    }
}
