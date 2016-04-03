package com.example.arshadhusain.weshare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Activity that shows the profile of another user.
 *
 * Started by ItemInfoActivity.
 * Returns to ItemInfoActivity when back button is clicked.
 *
 * @author Hanson, Christopher
 * @version 2.0
 */
public class ViewProfileActivity extends AppCompatActivity {
    Account accountToView;
    String accountUsername;
    TextView usernameTextView;
    TextView emailTextView;
    TextView cityTextView;

    final static int TEXTSIZE = 18;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);

        Intent intent = getIntent();

        if(intent.hasExtra("username")) {
            accountUsername = intent.getStringExtra("username");
        }

        ArrayList<Account> allAccounts = new ArrayList<>();

        ElasticSearchAppController.GetAccountTask getAccountTask = new ElasticSearchAppController.GetAccountTask();
        getAccountTask.execute(accountUsername);
        try {
            allAccounts.addAll(getAccountTask.get());

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        for(Account account : allAccounts) {
            if(account.getUsername().equals(accountUsername)){
                accountToView = account;
            }
        }

        usernameTextView = (TextView) findViewById(R.id.usernameTextView);
        String dispUsername = "Username: " + accountToView.getUsername();
        usernameTextView.setTextSize(TEXTSIZE);
        usernameTextView.setText(dispUsername);

        emailTextView = (TextView) findViewById(R.id.emailTextView);
        String dispEmail = "Email: " + accountToView.getEmail();
        emailTextView.setTextSize(TEXTSIZE);
        emailTextView.setText(dispEmail);

        cityTextView = (TextView) findViewById(R.id.cityTextView);
        String dispAddress = "Address: " + accountToView.getCity();
        cityTextView.setTextSize(TEXTSIZE);
        cityTextView.setText(dispAddress);
    }
}
