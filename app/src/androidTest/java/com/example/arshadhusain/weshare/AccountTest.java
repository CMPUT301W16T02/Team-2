package com.example.arshadhusain.weshare;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Created by hasan on 2016-03-13.
 */
public class AccountTest extends ActivityInstrumentationTestCase2 {
    String username;
    String city;
    String email;
    ArrayList<Account> allAccounts = new ArrayList<Account>();

    public AccountTest() {
        super(CreateProfileActivity.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
    }

    public void testAddAccount()  {
        Account account = new Account(username, city, email);
        Account account1 = null;
        account.setUsername("HasanBadran");
        account.setCity("Edmonton");
        account.setEmail("badran@gmail.com");

        ElasticSearchAppController.AddAccountTask addAccountTask = new ElasticSearchAppController.AddAccountTask();
        addAccountTask.execute(account);

        ElasticSearchAppController.GetAccountTask getAccountTask = new ElasticSearchAppController.GetAccountTask();
        getAccountTask.execute(account.getUsername());

        try {
            allAccounts.addAll(getAccountTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        int i = 0;
        while(i < allAccounts.size()) {
            if(allAccounts.get(i).getUsername().equals(account.getUsername())) {
                account1 = allAccounts.get(i);
                break;
            }

        }
        assertTrue(account1.getUsername().equals(account.getUsername()));
    }





}
