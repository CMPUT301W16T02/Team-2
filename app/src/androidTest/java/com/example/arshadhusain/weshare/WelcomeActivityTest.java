package com.example.arshadhusain.weshare;

import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.widget.Button;
import android.widget.EditText;


/**
 * Created by hasan on 2016-03-11.
 */
public class WelcomeActivityTest extends ActivityInstrumentationTestCase2 {
    public WelcomeActivityTest(){
        super(WelcomeActivity.class);
    }

    public void testActivityExists() {
        WelcomeActivity activity = (WelcomeActivity) getActivity();
        assertNotNull(activity);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
    }


    // taken from:
    //http://evgenii.com/blog/testing-activity-in-android-studio-tutorial/
    
    public void testWelcomeActivity() throws Exception {
        WelcomeActivity activity = (WelcomeActivity) getActivity();


        //Type username in text input
        // ----------------------
        final EditText nameEditText =
                (EditText) activity.findViewById(R.id.usernameField);

        // Send string input value
        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                nameEditText.requestFocus();
            }
        });

        getInstrumentation().waitForIdleSync();
        getInstrumentation().sendStringSync("badran047");
        getInstrumentation().waitForIdleSync();

        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                nameEditText.requestFocus();
            }
        });

        getInstrumentation().waitForIdleSync();
        getInstrumentation().sendStringSync("badran047");

        // Tap "SIGN IN" button
        // ----------------------

        Button signInButton = (Button) activity.findViewById(R.id.signInButton);
        TouchUtils.clickView(this, signInButton);
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
    }
}
