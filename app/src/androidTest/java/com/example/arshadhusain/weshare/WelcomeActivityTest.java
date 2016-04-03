package com.example.arshadhusain.weshare;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;



/**
 * Created by hasan on 2016-03-11.
 */
public class WelcomeActivityTest extends ActivityInstrumentationTestCase2 {
    public WelcomeActivityTest(){
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

    public void testWelcomeActivity() throws Exception{
        Activity activity = getActivity();
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
    }
}
