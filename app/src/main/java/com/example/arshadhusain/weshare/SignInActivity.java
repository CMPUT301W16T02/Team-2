package com.example.arshadhusain.weshare;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class SignInActivity extends AppCompatActivity {
    private EditText username;
    ArrayList<Account> Accounts = new ArrayList<Account>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.onCreateSetup();
        this.onCreateListeners();

    }

    public void onCreateSetup() {
        setContentView(R.layout.sign_in_activity);
        username = (EditText) findViewById(R.id.SignInInput1);
    }

    public void onCreateListeners() {
        Button signIn = (Button)findViewById(R.id.SignInButton);


        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            setResult(RESULT_OK);

            try {
                String UserName = username.getText().toString();

                System.out.printf("Username 2: %s\n", UserName);
                FileInputStream inputFile = openFileInput(UserName);
                BufferedReader input = new BufferedReader(new InputStreamReader(inputFile));
                System.out.println("File Loaded\n");

                //String line = input.readLine();
                Gson gson = new Gson();
                //Type listType = new TypeToken<ArrayList<FuelLog>>() {}.getType();

                Type listType = new TypeToken<ArrayList<Account>>() {
                }.getType();


                Accounts = gson.fromJson(input, listType);


                //Account account = new Account();

            /*while (line != null) {
                fuelList.add(line);
                line = input.readLine();
            }*/

            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                //e.printStackTrace();
                Accounts = new ArrayList<Account>();
                System.out.println("Username not found\n");
                Toast.makeText(getApplicationContext(), "Username not found", Toast.LENGTH_LONG).show();

            } catch (IOException e) {
                // TODO Auto-generated catch block
                //e.printStackTrace();
                throw new RuntimeException();
            }


            Intent intent = new Intent(SignInActivity.this, NavigationMainActivity.class);
            //startActivity(intent);
            startActivityForResult(intent, 1);

            setResult(RESULT_OK);
            finish();



            }

        });

    }

}