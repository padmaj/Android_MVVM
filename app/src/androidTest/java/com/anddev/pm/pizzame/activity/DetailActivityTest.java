package com.anddev.pm.pizzame.activity;

import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.anddev.pm.pizzame.R;
import com.anddev.pm.pizzame.view.activity.DetailActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * device screen should be "ON" to pass the view test
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class DetailActivityTest {

    @Rule
    public ActivityTestRule<DetailActivity> rule = new ActivityTestRule<>(DetailActivity.class, false, true);

    @Test
    public void checkMapClick() {
        onView(withId(R.id.maps)).check(matches(ViewMatchers.isCompletelyDisplayed()));
        onView(withId(R.id.maps)).perform(click());
    }

    @Test
    public void checkPhoneClick() {
        onView(withId(R.id.phone)).check(matches(ViewMatchers.isCompletelyDisplayed()));
        onView(withId(R.id.phone)).perform(click());
    }

}
