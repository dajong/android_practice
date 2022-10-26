package com.example.tictactoe;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertEquals;

import android.content.Context;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityButtonsBehaviour {
    // Activity test rule is deprecated: more details on https://developer.android.com/reference/androidx/test/ext/junit/rules/ActivityScenarioRule
    @Rule
    public ActivityScenarioRule rule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.tictactoe", appContext.getPackageName());
    }

    @Test
    public void test_game_button_clicked() {
        onView(withId(R.id.btn_0)).perform(click());
        onView(withId(R.id.btn_0)).check(matches(withText("X")));

        onView(withId(R.id.btn_1)).perform(click());
        onView(withId(R.id.btn_1)).check(matches(withText("O")));
    }

    @Test
    public void test_new_game_triggered() {
        // Check if the button works
        onView(withId(R.id.btn_0)).perform(click());
        onView(withId(R.id.btn_new_game)).perform(click());
        onView(withId(R.id.btn_0)).check(matches(withText("")));

        // Check if menu item works
        onView(withId(R.id.btn_0)).perform(click());
        onView(withId(R.id.menu_new_game)).perform(click());
        onView(withId(R.id.btn_0)).check(matches(withText("")));
    }
}