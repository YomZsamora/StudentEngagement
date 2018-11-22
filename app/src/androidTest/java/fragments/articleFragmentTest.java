package fragments;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;

import com.lawrence254.moringa.R;
import com.lawrence254.moringa.activities.homeActivity;
import com.lawrence254.moringa.activities.firebaseLoginActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class articleFragmentTest {
    @Rule
    public ActivityTestRule<homeActivity> mArticlesTestRule =
            new ActivityTestRule<>(homeActivity.class,false,false);

//    @Before
//    public void setup(){
//        mArticlesTestRule.launchActivity(MY_ACTIVITY_INTENT);
//    }
    @Test
    public void profileVisibility(){
//        onData(allOf(is(instanceOf(String.class)), ("Espresso"))) .perform(click());
//        onView(allOf(withId(R.id.navigation), withText("Profile")))
//                .check(matches(isDisplayed()));
        onView(withId(R.id.profile)).check(matches(isDisplayed()));
    }
}