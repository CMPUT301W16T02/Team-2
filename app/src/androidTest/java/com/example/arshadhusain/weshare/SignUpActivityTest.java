package com.example.arshadhusain.weshare;

import android.app.Activity;
import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;

/**
 * Created by hasan on 2016-03-12.
 */
public class SignUpActivityTest extends ActivityInstrumentationTestCase2 {
    private Button signupButton;

    public SignUpActivityTest(){
        super(com.example.arshadhusain.weshare.SignUpActivity.class);
    }

    public void testStart() throws Exception {
        Activity activity = getActivity();
    }

    MainActivity activity = (MainActivity)getActivity();
    signupButton = activity.getSignUpButton();


    //taken from:
    //https://developer.android.com/training/activity-testing/activity-functional-testing.html,
    // Set up an ActivityMonitor, to ensure signup activity starts
    Instrumentation.ActivityMonitor receiverActivityMonitor =
            getInstrumentation().addMonitor(SignUpActivity.class.getName(), null, false);

    activity.runOnUiThread(new Runnable() {

        public void run() {
            signupButton.performClick();
        }
    });
    getInstrumentation().waitForIdleSync();

    // Validate that ReceiverActivity is started
    SignUpActivity receiverActivity = (SignUpActivity)
            receiverActivityMonitor.waitForActivityWithTimeout(1000);
    assertNotNull("ReceiverActivity is null", receiverActivity);
    assertEquals("Monitor for ReceiverActivity has not been called",
                         1, receiverActivityMonitor.getHits());
    assertEquals("Activity is of wrong type",
                 SignUpActivity.class, receiverActivity.getClass());

    getInstrumentation().removeMonitor(receiverActivityMonitor);

    receiverActivity.finish();
}
