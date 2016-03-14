package com.example.arshadhusain.weshare;

import android.app.Activity;
import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.test.UiThreadTest;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by hasan on 2016-03-12.
 */
/*public class SignInActivityTest extends ActivityInstrumentationTestCase2 {
    private Button signinButton;

    public SignInActivityTest(){
        super(com.example.arshadhusain.weshare.SignInActivity.class);
    }

    public void testStart() throws Exception {
        Activity activity = getActivity();
    }

    MainActivity activity = (MainActivity)getActivity();
    signinButton = activity.getSignInButton();

    username = activity.getEditText();

    activity.runOnUiThread(new Runnable() {
        public void run() {
            username.setText("HasanBadran");
        }
    });
    getInstrumentation().waitForIdleSync();

    Instrumentation.ActivityMonitor receiverActivityMonitor =
            getInstrumentation().addMonitor(HomeScreen.class.getName(), null, false);


    activity.runOnUiThread(new Runnable() {

        public void run() {
            loginbutton.performClick();
        }
    });
    getInstrumentation().waitForIdleSync();

    // Validate that ReceiverActivity is started
    HomeScreen receiverActivity = (HomeScreen)
            receiverActivityMonitor.waitForActivityWithTimeout(1000);
    assertNotNull("ReceiverActivity is null", receiverActivity);
    assertEquals("Monitor for ReceiverActivity has not been called",
                         1, receiverActivityMonitor.getHits());
    assertEquals("Activity is of wrong type",
                 HomeScreen.class, receiverActivity.getClass());

    // Remove the ActivityMonitor
    getInstrumentation().removeMonitor(receiverActivityMonitor);

    receiverActivity.finish();

}
}*/
