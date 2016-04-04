package com.example.arshadhusain.weshare;

import android.test.ActivityInstrumentationTestCase2;

/**
 * Created by hasan on 2016-03-12.
 */
public class LogInActivityTest extends ActivityInstrumentationTestCase2 {
    public LogInActivityTest() {
        super(WelcomeActivity.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
    }

    public void testActivityExists() {
        WelcomeActivity activity = (WelcomeActivity) getActivity();
        assertNotNull(activity);
    }


    public void testSignInActivity() throws Exception {
        //test
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
    }
}