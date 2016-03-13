package com.example.arshadhusain.weshare;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.EditText;

/**
 * Created by hasan on 2016-03-13.
 */
public class AccountTest extends ActivityInstrumentationTestCase2 {
    EditText username;
    EditText city;
    EditText email;

    public AccountTest() {
        super(Account.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
    }

    //TODO: test for exceptions
    public void testAccount() throws IllegalEmailException, NoSpacesException, TooLongException {
        Account account = new Account(username, city, email);

        account.setUsername("HasanBadran");
        account.setCity("Edmonton");
        account.setEmail("badran@gmail.com");

        assertTrue(account.getUsername().equals("HasanBadran"));
        assertTrue(account.getCity().equals("Edmonton"));
        assertTrue(account.getEmail().equals("badran@gmail.com"));
    }
}