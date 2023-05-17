package com.example.educationapplication;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.educationapplication.util.views.LoginView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class LoginViewTests {

    @Before
    public void setUp() {
        ActivityScenario.launchActivityForResult(LoginView.class);
    }

    @Test
    public void incorrect_password_message_surfaces() {
        onView(withId(R.id.editTextEmail)).perform(
                typeText("u7499989@anu.edu.au"),
                closeSoftKeyboard()
        );
        onView(withId(R.id.editTextPassword)).perform(
                typeText("IncorrectPassword"),
                closeSoftKeyboard()
        );
        onView(withId(R.id.loginButton)).perform(click());

        onView(withId(R.id.errorText)).check(matches(withText("Could not find the user specified. Check your spelling and try again.")));
    }

    @Test
    public void correct_password_changes_views_to_main() {
        onView(withId(R.id.editTextEmail)).perform(
                typeText("not even an email"),
                closeSoftKeyboard()
        );
        onView(withId(R.id.editTextPassword)).perform(
                typeText("IncorrectPassword"),
                closeSoftKeyboard()
        );
        onView(withId(R.id.loginButton)).perform(click());

        onView(withId(R.id.errorText)).check(matches(withText("Invalid email. Check your spelling and try again.")));
    }
}