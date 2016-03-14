package com.example.arshadhusain.weshare;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;



/**
 * Created by hasan on 2016-03-11.
 */
public class MainActivityTest extends ActivityInstrumentationTestCase2 {
    public MainActivityTest(){
        super(com.example.arshadhusain.weshare.MainActivity.class);
    }

    public void testStart() throws Exception{
        Activity activity = getActivity();
    }

}
