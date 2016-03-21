package com.example.arshadhusain.weshare;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.EditText;

/**
 * Created by hasan on 2016-03-13.
 */
public class AccountTest extends ActivityInstrumentationTestCase2 {
<<<<<<< HEAD
    EditText username;
    EditText city;
    EditText email;
=======
    String username;
    String city;
    String email;
>>>>>>> master

    public AccountTest() {
        super(Account.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
    }

<<<<<<< HEAD
    //TODO: test for exceptions
    public void testAccount() throws IllegalEmailException, NoSpacesException, TooLongException {
=======
    public void testAccount()  {
>>>>>>> master
        Account account = new Account(username, city, email);

        account.setUsername("HasanBadran");
        account.setCity("Edmonton");
        account.setEmail("badran@gmail.com");

        assertTrue(account.getUsername().equals("HasanBadran"));
        assertTrue(account.getCity().equals("Edmonton"));
        assertTrue(account.getEmail().equals("badran@gmail.com"));
    }
}
