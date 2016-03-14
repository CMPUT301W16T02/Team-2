/**
 * Activity that allows a user to edit his profile. The username cannot be
 * changed. Just the email and city can be changed.
 *
 * Started by NavigationMainActivity.
 * Returns to NavigationMainActivity on successful edit.
 *
 * @author Hanson
 */
package com.example.arshadhusain.weshare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;



public class EditProfileActivity extends AppCompatActivity {
    ArrayList<Account> Accounts = new ArrayList<Account>();
    Account MyAccount;
    String MyUsername;
    TextView username;
    EditText email;
    EditText city;
    Button SaveButton;
    public static int TEXTSIZE = 18;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        Intent intent = getIntent();

        if(intent.hasExtra("Username")) {
            MyUsername = intent.getStringExtra("Username");
        }

        try {
            FileInputStream inputFile = openFileInput(MyUsername);
            BufferedReader input = new BufferedReader(new InputStreamReader(inputFile));
            System.out.println("File Loaded\n");

            Gson gson = new Gson();

            Type listType = new TypeToken<ArrayList<Account>>() {
            }.getType();

            Accounts = gson.fromJson(input, listType);
        }

        catch(FileNotFoundException ex) {
            ex.printStackTrace();
        }

        for(Account account : Accounts) {
            if(account.getUsername().equals(MyUsername)){
                MyAccount = account;
            }
        }

        username = (TextView) findViewById(R.id.username);
        email = (EditText) findViewById(R.id.EditProfileEmail);
        city = (EditText) findViewById(R.id.EditProfileCity);
        SaveButton = (Button) findViewById(R.id.SaveButton);

        username.setTextSize(TEXTSIZE);
        username.setText("Username: " + MyAccount.getUsername());

        email.setText(MyAccount.getEmail());

        city.setText(MyAccount.getCity());

        SaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_OK);

                String newEmail = email.getText().toString();
                String newCity = city.getText().toString();

                MyAccount.setEmail(newEmail);
                MyAccount.setCity(newCity);

                try {
                    FileOutputStream fos = openFileOutput(MyUsername, 0);
                    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));

                    Gson gson = new Gson();
                    gson.toJson(Accounts, out);

                    out.flush();
                    fos.close();
                } catch (FileNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                finish();
            }
        });
    }
}
