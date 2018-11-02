package com.lawrence254.moringa.activities;

import android.support.test.espresso.ViewAction;
import android.support.test.filters.SmallTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.lawrence254.moringa.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withInputType;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
@SmallTest
public class loginActivityTest {
    @Rule
    public ActivityTestRule<loginActivity> mActivityTestRule =
            new ActivityTestRule<>(loginActivity.class);

    private String username = "TestUser";
    private String correctPass = "Test123";
    private String wrongPass = "test1234";

    @Test
    public void correctLogin(){

        onView(withId(R.id.username)).perform(click()).perform(typeText(username),closeSoftKeyboard());
        onView(withId(R.id.userpass)).perform(click()).perform(typeText(correctPass),closeSoftKeyboard());

        onView(withId(R.id.login)).perform(click());

        onView(withId(R.id.articlesCard)).check(matches(isDisplayed()));
    }
}
