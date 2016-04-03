package com.example.arshadhusain.weshare;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.EditText;

/**
 * Created by hasan on 2016-03-12.
 */
public class EditAccountTest extends ActivityInstrumentationTestCase2 {
    EditText username;
    EditText city;
    EditText email;

    public EditAccountTest(){
        super(Account.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
    }

    public void testActivityExists() {
        EditProfileActivity activity = (EditProfileActivity) getActivity();
        assertNotNull(activity);
    }

    public void testEditEmailAddress(){
        String origEmail = "email@address.ca";
        String newEmail = "new_email@address.ca";

        Account account = new Account("username", "city", origEmail);

        assertFalse(account.getEmail().equals(newEmail));
        account.setEmail(newEmail);
        assertTrue(account.getEmail().equals(newEmail));
    }

    public void testEditCity(){
        String origCity = "Camrose";
        String newCity = "Edmonton";

        Account account = new Account("username", origCity, "email@address.ca");

        assertFalse(account.getCity().equals(newCity));
        account.setCity(newCity);
        assertTrue(account.getCity().equals(newCity));
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
    }
}
