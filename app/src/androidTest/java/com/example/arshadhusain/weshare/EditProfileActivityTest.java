package com.example.arshadhusain.weshare;

import android.test.ActivityInstrumentationTestCase2;

import java.util.ArrayList;

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

    public void testUpdateProfile() {
        String myUsername = "Jimmy";
        String myCity = "Edmonton";
        String myEmail = "boygenius@hotmail.om";

        Account fakeAccount = new Account(myUsername, myCity, myEmail);
        assertEquals(fakeAccount.getUsername(), "Jimmy");
        assertEquals(fakeAccount.getCity(), "Edmonton");
        assertEquals(fakeAccount.getEmail(), "boygenius@hotmail.com");
    }
}
