package com.nanodegree.dalia.bakingapp;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.hamcrest.CoreMatchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by Dalia on 5/12/2017.
 */

@RunWith(AndroidJUnit4.class)
public class StepDetailInstrumentedTest {
    @Rule
    public ActivityTestRule<RecipesMainActivity> recipesMainActivityTestRule
            = new ActivityTestRule<RecipesMainActivity>(RecipesMainActivity.class);

    @Test
    public void loadDetailsOfSelectedRecipe(){
        onView(withId(R.id.grid_recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        onView(withId(R.id.ingredients_title)).check(matches(withText(CoreMatchers.containsString("Ingredients"))));

        onView(withId(R.id.stepsRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

    }

    @Test
    public void loadStepDetailsAndCheckInstructions(){
        onView(withId(R.id.grid_recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        onView(withId(R.id.stepsRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));

        onView(withId(R.id.incrementStep)).perform(click());

        onView(withId(R.id.step_instructions)).check(matches(withText(CoreMatchers.startsWith("3. "))));
    }
}
