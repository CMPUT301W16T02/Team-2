package com.example.arshadhusain.weshare;

import android.test.ActivityInstrumentationTestCase2;

/**
 * Created by hasan on 2016-03-13.
 */
public class AccountTest extends ActivityInstrumentationTestCase2 {
    String username;
    String city;
    String email;

    public AccountTest() {
        super(Account.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
    }

    public void testAccount()  {
        Account account = new Account(username, city, email);

        account.setUsername("HasanBadran");
        account.setCity("Edmonton");
        account.setEmail("badran@gmail.com");

        assertTrue(account.getUsername().equals("HasanBadran"));
        assertTrue(account.getCity().equals("Edmonton"));
        assertTrue(account.getEmail().equals("badran@gmail.com"));
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
    }
}
