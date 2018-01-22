package jokes.test.com.onlineassignment;

import android.support.annotation.StringRes;
import android.support.test.espresso.action.*;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.widget.AutoCompleteTextView;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
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
 * Instrumentation test for change the name run on device
 */
@RunWith(AndroidJUnit4.class)
public class ChangeTheNameTest {
    /*Instance of HomeActivity for get access of views inside it*/
    @Rule
    public ActivityTestRule<HomeActivity> activityTestRule = new ActivityTestRule<>(HomeActivity.class);

    /**
     * Test case for check name is empty
     */
    @Test
    public void nameIsEmpty() {
        onView(withId(R.id.ActivityHome_buttonTextInput)).perform(click());
        onView(withId(R.id.ActivityChangetheName_buttonChange)).perform(click());
        onView(withId(R.id.ActivityChangetheName_autoCompleteTextViewName))
                .check(matches(withError(getString(R.string.error_empty_name))));
    }

    /**
     * Test case for check name is Invalid
     */
    @Test
    public void nameIsInvalid() {
        onView(withId(R.id.ActivityHome_buttonTextInput)).perform(click());
        onView(withId(R.id.ActivityChangetheName_autoCompleteTextViewName)).perform(clearText());
        onView(withId(R.id.ActivityChangetheName_autoCompleteTextViewName)).perform(typeText("invalid"));
        onView(withId(R.id.ActivityChangetheName_buttonChange)).perform(click());
        onView(withId(R.id.ActivityChangetheName_autoCompleteTextViewName)).check(matches(withError(getString(R.string.error_invalid_name))));
    }

    /**
     * Test case for successful change the name
     */
    @Test
    public void changeTheNameSuccessfully() {
        onView(withId(R.id.ActivityHome_buttonTextInput)).perform(click());
        onView(withId(R.id.ActivityChangetheName_autoCompleteTextViewName)).perform(typeText("gaurav pandole"));
        onView(withId(R.id.ActivityChangetheName_buttonChange)).perform(click());
    }



    /**
     * Check if any any error field called
     * @param expected error string
     * @return true if error string matches else false
     */
    private static Matcher withError(final String expected) {
        return new TypeSafeMatcher() {
            @Override
            protected boolean matchesSafely(Object item) {
                if (item instanceof AutoCompleteTextView) {
                    return ((AutoCompleteTextView)item).getError().toString().equals(expected);
                }
                return false;
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("Not found error message [" + expected + "]");
            }
        };
    }

    /**
     * Get resource id
     * @param resourceId resourceId of layout like any view id
     * @return return string of label
     */
    private String getString(@StringRes int resourceId) {
        return activityTestRule.getActivity().getString(resourceId);
    }
}
