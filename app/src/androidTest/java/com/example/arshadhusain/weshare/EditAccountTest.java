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
        super(EditProfileActivity.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
    }

    public void editEmailAddressTest(){
        String origEmail = "email@address.ca";
        String newEmail = "new_email@address.ca";

        Account account = new Account("username", "city", origEmail);

        assertFalse(account.getEmail() == newEmail);
        account.editEmail(newEmail);
        assertTrue(account.getEmail() == newEmail);
    }

    public void editCityTest(){
        // tests the editAddress method

        String origCity = "Camrose";
        String newCity = "Edmonton";

        Account account = new Account("username", origCity, "email@address.ca");

        assertFalse(account.getCity()==newCity);
        account.editCity(newCity);
        assertTrue(account.getCity()==newCity);
    }

}
