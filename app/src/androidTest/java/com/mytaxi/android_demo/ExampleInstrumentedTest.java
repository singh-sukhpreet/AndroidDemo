package com.mytaxi.android_demo;

// android import statements
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import static android.support.test.InstrumentationRegistry.getContext;
import com.mytaxi.android_demo.activities.MainActivity;
// junit import statements
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
// espresso import statements
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import android.support.test.espresso.matcher.RootMatchers;

/**
 * Instrumented test, which will execute on an Android device.
 */
@RunWith(AndroidJUnit4.class)

public class ExampleInstrumentedTest {
    private String[] loginData;
    private String textToSearch;
    private String selectedDriver;

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
            MainActivity.class);


    @Before
    public void setUp() throws InterruptedException {
        // initializing instance of userInfo and loginData array
        UserInfo credentials = new UserInfo(getContext());
        loginData= credentials.getLoginDetails();
        Thread.sleep(3000);

    }

    @Test
    // to login into the App
    public void login() throws InterruptedException {
        onView(withId(R.id.edt_username)).perform(typeText(loginData[0]));
        onView(withId(R.id.edt_password)).perform(typeText(loginData[1]), closeSoftKeyboard());
        onView(withId(R.id.btn_login)).perform(click());
        textToSearch="sa";
        selectedDriver="Sarah Scott";

        // searching text and selecteing driver by name
        searchingDriver(textToSearch, selectedDriver);
    }

  private void searchingDriver(String textToSearch, String selectedDriver) throws InterruptedException {
        Thread.sleep(2000);
        onView(withId(R.id.textSearch)).perform(typeText(textToSearch),closeSoftKeyboard());
        onView(withText(selectedDriver)).inRoot(RootMatchers.isPlatformPopup()).perform(click());
        onView(withId(R.id.fab)).perform(click());
    }
}
