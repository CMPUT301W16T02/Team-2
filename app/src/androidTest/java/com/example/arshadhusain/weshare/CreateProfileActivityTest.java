package com.example.arshadhusain.weshare;

import android.test.ActivityInstrumentationTestCase2;

/**
 * Created by Overseer on 2016-03-14.
 */
public class CreateProfileActivityTest extends ActivityInstrumentationTestCase2 {

    public CreateProfileActivityTest(){
        super(CreateProfileActivity.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
    }

    public void testActivityExists() {
        CreateProfileActivity activity = (CreateProfileActivity) getActivity();
        assertNotNull(activity);
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
    }

    public void testCreateProfile() throws Exception {
        //test
    }
}