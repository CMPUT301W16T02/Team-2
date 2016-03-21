package com.example.arshadhusain.weshare;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;



/**
 * Created by hasan on 2016-03-11.
 */
public class WelcomeActivityTest extends ActivityInstrumentationTestCase2 {
    public WelcomeActivityTest(){
        super(com.example.arshadhusain.weshare.WelcomeActivity.class);
    }

    public void testStart() throws Exception{
        Activity activity = getActivity();
    }

}
