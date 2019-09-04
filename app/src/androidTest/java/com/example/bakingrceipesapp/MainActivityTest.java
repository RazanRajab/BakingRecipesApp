package com.example.bakingrceipesapp;

import android.content.Intent;

import androidx.test.runner.AndroidJUnit4;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.rule.ActivityTestRule;

import com.example.bakingrceipesapp.recipeAPI.Step;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule=new ActivityTestRule<>(MainActivity.class);

    @Test
    public void clickRecyclerViewItem_OpenSelectStepActivity() {
        activityTestRule.launchActivity(new Intent());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withId(R.id.mainRecyclerView))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
      /*  try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onData(instanceOf(Step.class))
                .inAdapterView(withId(R.id.steps_recycler_view)).atPosition(0)
                .onChildView(withId(R.id.step_name))
                .check(matches(isDisplayed())); */
    }

}
