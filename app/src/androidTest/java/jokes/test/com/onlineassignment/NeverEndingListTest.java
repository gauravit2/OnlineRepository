package jokes.test.com.onlineassignment;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import jokes.test.com.onlineassignment.view.HomeActivity;

import static android.support.test.espresso.Espresso.*;
import static android.support.test.espresso.action.ViewActions.*;
import static android.support.test.espresso.assertion.ViewAssertions.*;
import static android.support.test.espresso.matcher.ViewMatchers.*;

/**
 * Created by Gaurav on 1/20/2018.
 */
@RunWith(AndroidJUnit4.class)
public class NeverEndingListTest {

    /*Instance of HomeActivity for get access of views inside it*/
    @Rule
    public ActivityTestRule<HomeActivity> mActivityRule = new ActivityTestRule<>(HomeActivity.class);

    @Test
    public void neverEndlingListTest() {
        onView(withId(R.id.ActivityHome_buttonNeverEndingList)).perform(click());
    }
}
