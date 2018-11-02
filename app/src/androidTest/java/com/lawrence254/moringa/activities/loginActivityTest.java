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
import static android.support.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withInputType;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class loginActivityTest {
    @Rule
    public ActivityTestRule<loginActivity> mActivityTestRule =
            new ActivityTestRule<>(loginActivity.class,true,true);

    private String username = "TestUser";
    private String correctPass = "Test123";
    private String wrongPass = "test1234";

    @Test
    public void login_to_home(){

        onView(withId(R.id.login)).perform(click(),closeSoftKeyboard());

        onView(withId(R.id.frame_container)).check(matches(isCompletelyDisplayed()));
    }
}
