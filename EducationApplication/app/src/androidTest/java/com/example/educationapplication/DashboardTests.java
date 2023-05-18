package com.example.educationapplication;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.core.app.ActivityScenario;

import com.example.educationapplication.views.LoginView;

import org.junit.Before;
import org.junit.Test;

public class DashboardTests {

    @Before
    public void setUp() {
        ActivityScenario.launchActivityForResult(LoginView.class);
        onView(withId(R.id.editTextEmail)).perform(
                typeText("u7499989@anu.edu.au"),
                closeSoftKeyboard()
        );
        onView(withId(R.id.editTextPassword)).perform(
                typeText("password1"),
                closeSoftKeyboard()
        );
        onView(withId(R.id.loginButton)).perform(click());
    }

    @Test
    public void test_main_dashboard_tab() {
        onView(withId(R.id.dashboardText)).check(matches(isDisplayed()));
    }

    @Test
    public void test_search_tab() {
        onView(withId(R.id.search)).perform(click());
        onView(withId(R.id.peopleTab)).check(matches(isDisplayed()));
        onView(withId(R.id.classesTab)).check(matches(isDisplayed()));

    }

    @Test
    public void test_messages_tab() {
        onView(withId(R.id.messages)).perform(click());
        onView(withId(R.id.messages_lastmessage)).check(matches(isDisplayed()));
    }

    @Test
    public void test_profile_tab() {
        onView(withId(R.id.profile)).perform(click());
        onView(withId(R.id.profileText)).check(matches(isDisplayed()));
    }

}
