package com.lawrence254.moringa.activities;

import android.support.test.espresso.ViewAction;
import android.support.test.espresso.ViewInteraction;
import android.support.test.filters.LargeTest;
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
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withInputType;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class firebaseLoginActivityTest {
    @Rule
    public ActivityTestRule<firebaseLoginActivity> mActivityTestRule =
            new ActivityTestRule<>(firebaseLoginActivity.class);

    private String username = "TestUser";
    private String correctPass = "Test123";
    private String wrongPass = "test1234";

    @Test
    public void loadLogin(){

        onView(withId(R.id.login)).perform(closeSoftKeyboard());
        onView(withId(R.id.login)).check(matches(isDisplayed()));
    }

//    @Test
//    public void load_home_page(){
////        onView(withId(R.id.login)).check(matches(isDisplayed()));
//        onView(withId(R.id.login)).perform(closeSoftKeyboard(),scrollTo(),click());
//        onView(withId(R.id.navigation)).check(matches(isDisplayed()));
//    }
}
