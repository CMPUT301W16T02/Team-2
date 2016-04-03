package com.example.arshadhusain.weshare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Activity that shows the profile of another user.
 *
 * Started by ItemInfoActivity.
 * Returns to ItemInfoActivity when back button is clicked.
 *
 * @author Hanson
 * @version 1.0
 */
public class ViewProfileActivity extends AppCompatActivity {
    ArrayList<Account> Accounts = new ArrayList<Account>();
    Account MyAccount;
    String MyUsername;
    TextView username;
    TextView email;
    TextView city;

    public static int TEXTSIZE = 18;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);

        Intent intent = getIntent();

        if(intent.hasExtra("Username")) {
            MyUsername = intent.getStringExtra("Username");
        }

        /*try {
            FileInputStream inputFile = openFileInput(MyUsername);
            BufferedReader input = new BufferedReader(new InputStreamReader(inputFile));

            Gson gson = new Gson();

            Type listType = new TypeToken<ArrayList<Account>>() {
            }.getType();

            Accounts = gson.fromJson(input, listType);
        }

        catch(FileNotFoundException ex) {
            ex.printStackTrace();
        }*/

        ElasticSearchAppController.GetAccountTask getAccountTask = new ElasticSearchAppController.GetAccountTask();
        getAccountTask.execute(MyUsername);
        try {
            Accounts.addAll(getAccountTask.get());

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        for(Account account : Accounts) {
            if(account.getUsername().equals(MyUsername)){
                MyAccount = account;
            }
        }

        username = (TextView) findViewById(R.id.username);
        String dispUsername = "Username: " + MyAccount.getUsername();
        username.setTextSize(TEXTSIZE);
        username.setText(dispUsername);

        email = (TextView) findViewById(R.id.email);
        String dispEmail = "Email: " + MyAccount.getEmail();
        email.setTextSize(TEXTSIZE);
        email.setText(dispEmail);

        city = (TextView) findViewById(R.id.city);
        String dispAddress = "Address: " + MyAccount.getCity();
        city.setTextSize(TEXTSIZE);
        city.setText(dispAddress);

    }
}
