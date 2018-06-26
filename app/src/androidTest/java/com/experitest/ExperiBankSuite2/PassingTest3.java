package com.experitest.ExperiBankSuite2;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.experitest.ExperiBank.LoginActivity;
import com.experitest.ExperiBank.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.not;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class PassingTest3 extends TestBase {

    @Rule
    public ActivityTestRule<LoginActivity> mActivityTestRule = new ActivityTestRule(LoginActivity.class);

    @Test
    public void loginActivityTest() {
        for (int i = 0; i < 1; i++) {
            runTest();
        }
    }

    private void runTest() {
        login();
        ExpenseButton.perform(click());
        for (int i = 0; i < 9; i++) {
            AddButton.perform(click());
        }
        ViewInteraction ninth = onView(withText("Expense : 9"));
        ninth.check(matches(isDisplayed()));
        ninth.perform(click());
        ninth.check(doesNotExist());
    }
}
