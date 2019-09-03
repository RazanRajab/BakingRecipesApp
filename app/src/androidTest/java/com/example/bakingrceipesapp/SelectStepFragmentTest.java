package com.example.bakingrceipesapp;

import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

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
            new ActivityTestRule<>(SelectStepActivity.class);

    @Before
    public void yourSetUPFragment() {
        activityTestRule.getActivity()
                .getFragmentManager().beginTransaction();
    }

    @Test
    public void clickOnStep(){
        onView(withId(R.id.mainRecyclerView)).perform(
                RecyclerViewActions.actionOnItemAtPosition(0,click())
        );
        onView(withId(R.id.steps_recycler_view))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.description)).check(matches(withText("Recipe Introduction")));
    }
}
