package com.example.a310finalproject;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class SwapTabsInstrumentedTest {

    @Rule public ActivityScenarioRule<HomeActivity> activityScenarioRule =
            new ActivityScenarioRule<HomeActivity>(HomeActivity.class);

    @Test
    public void testSwapTabs() {
        onView(withId(R.id.meetings_button)).perform(click());
        onView(withId(R.id.meeting_grid)).check(matches(isDisplayed()));

        onView(withId(R.id.friends_button)).perform(click());
        onView(withId(R.id.friend_grid)).check(matches(isDisplayed()));

        onView(withId(R.id.profile_button)).perform(click());
        onView(withId(R.id.profile_first_name)).check(matches(isDisplayed()));
    }
}
