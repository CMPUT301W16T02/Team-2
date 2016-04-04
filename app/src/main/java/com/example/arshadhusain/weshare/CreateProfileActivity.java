package com.example.arshadhusain.weshare;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Activity that allows user to create an account. An account is
 * required to use this app.
 *
 * Started by WelcomeActivity.
 * Returns to WelcomeActivity with successful account creation.
 *
 * Now incorporated elastic search functionality
 *
 * @author Badran, Arshad, Christopher
 * @version 2.5
 */
public class CreateProfileActivity extends Activity{
    private EditText usernameInput;
    private EditText emailInput;
    private EditText cityInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.onCreateSetup();
        this.onCreateListeners();
    }

    /**
     * Method that sets up input text box usage.
     */
    public void onCreateSetup() {
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        setContentView(R.layout.create_profile_activity);
        usernameInput = (EditText) findViewById(R.id.usernameField);
        emailInput = (EditText) findViewById(R.id.emailField);
        cityInput = (EditText) findViewById(R.id.cityField);
    }

    /**
     * Method that sets up button click listeners.
     */
    public void onCreateListeners() {
        Button createAccount = (Button)findViewById(R.id.createAccountButton);

        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = usernameInput.getText().toString();
                String email = emailInput.getText().toString();
                String city = cityInput.getText().toString();

                // Check for empty fields and notify user to input if necessary
                if(username.isEmpty() && email.isEmpty() && city.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please enter your information into each field", Toast.LENGTH_LONG).show();
                } else if(username.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please enter a user name", Toast.LENGTH_LONG).show();
                } else if (email.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Please enter your email", Toast.LENGTH_LONG).show();
                } else if (city.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please enter your city", Toast.LENGTH_LONG).show();
                // If all fields are full, check the username against existing accounts
                } else {
                    Account account = new Account(username, email, city);

                    // Check all existing accounts with usernames that match the input.
                    // Notify user if match found, complete adding account
                    ArrayList<Account> duplicateAccounts = new ArrayList<>();

                    ElasticSearchAppController.GetAccountTask getAccountTask = new ElasticSearchAppController.GetAccountTask();
                    getAccountTask.execute(username);
                    try {
                        duplicateAccounts.addAll(getAccountTask.get());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }

                    int ifDuplicate;
                    if (duplicateAccounts.size() != 0) {
                        ifDuplicate = duplicateAccounts.get(0).getUsername().compareToIgnoreCase(account.getUsername());
                    } else {
                        ifDuplicate = 1;
                    }
                    if(ifDuplicate == 0) {
                        Toast.makeText(getApplicationContext(), "Username already exists", Toast.LENGTH_LONG).show();

                    } else {
                        ElasticSearchAppController.AddAccountTask addAccountTask = new ElasticSearchAppController.AddAccountTask();
                        addAccountTask.execute(account);
                        finish();
                    }
                }
            }
        });
    }
}