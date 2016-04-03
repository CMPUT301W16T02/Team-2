package com.example.arshadhusain.weshare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Activity that allows a user to edit his profile. The username cannot be
 * changed. Just the email and city can be changed.
 *
 * Started by NavigationMainActivity.
 * Returns to NavigationMainActivity on successful edit.
 *
 * @author Hanson, Christopher
 * @version 2.0
 */
public class EditProfileActivity extends AppCompatActivity {
    Account myAccount;
    String myUsername;
    TextView usernameText;
    EditText emailInput;
    EditText cityInput;
    Button saveButton;
    public static int TEXTSIZE = 18;
    private ArrayList<Account> matchingAccounts = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        Intent intent = getIntent();

        if(intent.hasExtra("myUsername")) {
            myUsername = intent.getStringExtra("myUsername");
        }

        ElasticSearchAppController.GetAccountTask getAccountTask = new ElasticSearchAppController.GetAccountTask();
        getAccountTask.execute(myUsername);
        try {
            matchingAccounts.addAll(getAccountTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        for (Account account : matchingAccounts) {
            if (account.getUsername().equals(myUsername)) {
                myAccount = account;
            }
        }

        usernameText = (TextView) findViewById(R.id.username);
        emailInput = (EditText) findViewById(R.id.emailField);
        cityInput = (EditText) findViewById(R.id.cityField);
        saveButton = (Button) findViewById(R.id.saveButton);

        String usernameString = "Username: " + myAccount.getUsername();
        usernameText.setTextSize(TEXTSIZE);
        usernameText.setText(usernameString);

        emailInput.setText(myAccount.getEmail());

        cityInput.setText(myAccount.getCity());

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_OK);

                String newEmail = emailInput.getText().toString();
                String newCity = cityInput.getText().toString();
                if (newEmail.isEmpty() && newCity.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Please enter your information into each field", Toast.LENGTH_LONG).show();
                } else if (newEmail.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Please enter your email", Toast.LENGTH_LONG).show();
                } else if (newCity.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please enter your city", Toast.LENGTH_LONG).show();
                } else {
                    myAccount.setEmail(newEmail);
                    myAccount.setCity(newCity);
                    ElasticSearchAppController.EditAccountTask editAccountTask = new ElasticSearchAppController.EditAccountTask();
                    editAccountTask.execute(myAccount);
                    finish();
                }
            }
        });
    }
}
