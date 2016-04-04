package com.example.arshadhusain.weshare;

import android.test.ActivityInstrumentationTestCase2;

/**
 * Created by Overseer on 2016-03-14.
 */
public class EditProfileActivityTest extends ActivityInstrumentationTestCase2 {

    public EditProfileActivityTest(){
        super(EditProfileActivity.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
    }


    public void testUpdateName() {
        String myName = "Jimmy";
        String myCity = "Edmonton";
        String myEmail = "boygenius@hotmail.com";

        Account fakeaccount = new Account(myName, myEmail, myCity);
        assertEquals(fakeaccount.getUsername(), myName);
        assertEquals(fakeaccount.getEmail(), myEmail);
        assertEquals(fakeaccount.getCity(), myCity);
    }
}
