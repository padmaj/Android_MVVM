package com.anddev.pm.pizzame.activity;

import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.anddev.pm.pizzame.R;
import com.anddev.pm.pizzame.view.activity.HomeActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * device screen should be "ON" to pass the view test
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class HomeActivityTest {

    @Rule
    public ActivityTestRule<HomeActivity> rule = new ActivityTestRule<>(HomeActivity.class, false, true);

    @Test
    public void topLayoutIsVisible() {
        onView(withId(R.id.top_layout)).check(matches(ViewMatchers.isCompletelyDisplayed()));
    }

    @Test
    public void recyclerViewIsVisible() {
        onView(withId(R.id.rv_pizza)).check(matches(ViewMatchers.isCompletelyDisplayed()));
    }

    @Test
    public void ensureKeyboardCloses() {
        // Type zipcode and then press the enter.
        onView(withId(R.id.et_zipCode)).perform(typeText("12345"), closeSoftKeyboard());
    }

}
