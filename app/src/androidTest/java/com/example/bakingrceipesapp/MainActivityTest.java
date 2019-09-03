package com.example.bakingrceipesapp;

import androidx.test.runner.AndroidJUnit4;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule=new ActivityTestRule<>(MainActivity.class);

    @Test
    public void clickRecyclerViewItem_OpenSelectStepActivity() {
        onView(withId(R.id.mainRecyclerView))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
    }

}
