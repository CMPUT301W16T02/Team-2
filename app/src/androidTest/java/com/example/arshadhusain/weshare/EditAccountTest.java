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
<<<<<<< HEAD
        super(EditProfileActivity.class);
=======
        super(Account.class);
>>>>>>> master
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
    }

<<<<<<< HEAD
    public void editEmailAddressTest(){
=======
    public void testEditEmailAddress(){
>>>>>>> master
        String origEmail = "email@address.ca";
        String newEmail = "new_email@address.ca";

        Account account = new Account("username", "city", origEmail);

<<<<<<< HEAD
        assertFalse(account.getEmail() == newEmail);
        account.editEmail(newEmail);
        assertTrue(account.getEmail() == newEmail);
    }

    public void editCityTest(){
=======
        assertFalse(account.getEmail().equals(newEmail));
        account.setEmail(newEmail);
        assertTrue(account.getEmail().equals(newEmail));
    }

    public void testEditCity(){
>>>>>>> master
        // tests the editAddress method

        String origCity = "Camrose";
        String newCity = "Edmonton";

        Account account = new Account("username", origCity, "email@address.ca");

<<<<<<< HEAD
        assertFalse(account.getCity()==newCity);
        account.editCity(newCity);
        assertTrue(account.getCity()==newCity);
=======
        assertFalse(account.getCity().equals(newCity));
        account.setCity(newCity);
        assertTrue(account.getCity().equals(newCity));
>>>>>>> master
    }

}
