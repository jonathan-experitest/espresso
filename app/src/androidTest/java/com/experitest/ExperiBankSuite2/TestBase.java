package com.experitest.ExperiBankSuite2;

import android.support.test.espresso.ViewInteraction;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.experitest.ExperiBank.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.After;

import static android.support.test.espresso.Espresso.onData;
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
import static org.hamcrest.Matchers.allOf;

/**
 * Created by ido.shoshani on 06/04/2018.
 */

public abstract class TestBase {
    ViewInteraction userNameField = onView(
            withId(R.id.usernameTextField));


    ViewInteraction passwordField = onView(
            withId(R.id.passwordTextField));

    ViewInteraction LoginButton = onView(
            allOf(withId(R.id.loginButton), withText("Login"),
                    withParent(allOf(withId(R.id.makePaymentView),
                            withParent(withId(R.id.scrollView1))))));

    ViewInteraction MakePaymentButton = onView(
            allOf(withId(R.id.makePaymentButton),
                    childAtPosition(
                            childAtPosition(
                                    withId(R.id.scrollView1),
                                    0),
                            2),
                    isDisplayed()));

    //interactions for Making payments
    ViewInteraction PhoneNumberField = onView(withId(R.id.phoneTextField));
    ViewInteraction nameField = onView(withId(R.id.nameTextField));
    ViewInteraction amountField = onView(withId(R.id.amountTextField));
    ViewInteraction CountryField = onView(withId(R.id.countryButton));
    ViewInteraction USButton = onView(withText("USA"));
    ViewInteraction completePayment = onView(withId(R.id.sendPaymentButton));
    ViewInteraction yes = onView(withText("Yes"));

    //Interactions for Mortgage requests
    ViewInteraction MortgageButton = onView(withId(R.id.mortageRequestButton));
    ViewInteraction firstNameField = onView(withId(R.id.nameTextField));
    ViewInteraction lastNameField = onView(withId(R.id.lastNameTextField));
    ViewInteraction ageField = onView(withId(R.id.ageTextField));
    ViewInteraction Address1Field = onView(withId(R.id.addressOneTextField));
    ViewInteraction nextButton = onView(withId(R.id.nextButton));

    ViewInteraction SaveButton = onView(withId(R.id.saveButton));

    //Interacitons for Expense Reports
    ViewInteraction ExpenseButton = onView(withId(R.id.expenseReportButton));
    ViewInteraction AddButton = onView(withId(R.id.addButton));



    ViewInteraction logoutButton = onView(
            allOf(withId(R.id.logoutButton), withText("Logout")));

    ViewInteraction loginHint = onView(
            allOf(withText("Hint:company"),
                    childAtPosition(
                            allOf(withId(R.id.makePaymentView),
                                    childAtPosition(
                                            withId(R.id.scrollView1),
                                            0)),
                            4),
                    isDisplayed()));

    ViewInteraction currentAmount = onView(withId(R.id.balanceWebView));
    public void login(){ //The basic login step we always have to do anyways.
        userNameField.perform(scrollTo(), replaceText("company"), closeSoftKeyboard());
        passwordField.perform(scrollTo(), replaceText("company"), closeSoftKeyboard());
        LoginButton.check(matches(isDisplayed()));
        LoginButton.perform(scrollTo(), click());
        MakePaymentButton.check(matches(isDisplayed()));

    }


    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }


}
