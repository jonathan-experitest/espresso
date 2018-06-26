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

import junit.runner.BaseTestRunner;

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
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import android.support.test.espresso.web.sugar.Web;
import android.support.test.espresso.web.webdriver.Locator;
import static android.support.test.espresso.web.assertion.WebViewAssertions.webMatches;
import static android.support.test.espresso.web.sugar.Web.onWebView;
import static android.support.test.espresso.web.webdriver.DriverAtoms.findElement;
import static android.support.test.espresso.web.webdriver.DriverAtoms.getText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class FailingTest extends TestBase {

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
        onWebView(withId(R.id.balanceWebView)).forceJavascriptEnabled();
        MakePaymentButton.perform(click());
        PhoneNumberField.perform(scrollTo(), replaceText("5555555555"), closeSoftKeyboard());
        nameField.perform(scrollTo(), replaceText("Blank Blank"), closeSoftKeyboard());
        amountField.perform(scrollTo(), replaceText("120"), closeSoftKeyboard());
        CountryField.perform(scrollTo(), click());
        USButton.perform(scrollTo(), click());
        completePayment.perform(scrollTo(), click());

        yes.perform(click());
        onWebView(withId(R.id.balanceWebView)).forceJavascriptEnabled();
        Web.WebInteraction amount = onWebView(withId(R.id.balanceWebView)).withElement(findElement(Locator.TAG_NAME, "H1"));
        amount.check(webMatches(getText(), (equalTo("Your balance is: 100.00"))));
        logoutButton.perform(scrollTo(), click());

        loginHint.check(matches(withText("Hint:company")));
    }

}
