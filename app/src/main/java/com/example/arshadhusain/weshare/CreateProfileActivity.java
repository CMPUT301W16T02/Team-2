package com.example.arshadhusain.weshare;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

/**
 * Created by arshadhusain on 16-03-09.
 */
public class CreateProfileActivity extends Activity{
    boolean valid;
    private Context context;
    private EditText username;
    private EditText email;
    private EditText city;
    private ArrayList<Account> Accounts = new ArrayList<Account>();
    ArrayAdapter<Account> adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.onCreateSetup();
        this.onCreateListeners();

    }

    public void onCreateSetup() {
        setContentView(R.layout.create_profile_activity);
        username = (EditText) findViewById(R.id.CreateProfileInput1);
        email = (EditText) findViewById(R.id.CreateProfileInput2);
        city = (EditText) findViewById(R.id.CreateProfileInput3);


    }

    public void onCreateListeners() {
        Button createAccount = (Button)findViewById(R.id.button);
        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setResult(RESULT_OK);


                String UserName = username.getText().toString();
                String Email = email.getText().toString();
                String City = city.getText().toString();
                Account account = new Account(UserName, Email, City);
                Accounts.add(account);
                //adapter.notifyDataSetChanged();
                System.out.println("Before save\n");

                try {
                    FileOutputStream fos = openFileOutput(UserName, 0);
                    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));
                    Gson gson = new Gson();
                    gson.toJson(Accounts, out);
                    //fos.write(new String(text6 + " | " + text + " | " + text1 + " | " + text2 + " | " + text3 + " | " + text4 + " | " + text5 + " \n ")
                    // .getBytes());
                    out.flush();
                    fos.close();
                } catch (FileNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                System.out.println("After save\n");

                Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);

                startActivityForResult(myIntent, 0);
                //}


            }

        });




      /*  try {
        //FileOutputStream fos = openFileOutput(FILENAME,
        //Context.MODE_APPEND);\
        FileOutputStream fos = openFileOutput(FILENAME,
                0);
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));
        Gson gson = new Gson();
        gson.toJson(FuelLogs, out);
        //fos.write(new String(text6 + " | " + text + " | " + text1 + " | " + text2 + " | " + text3 + " | " + text4 + " | " + text5 + " \n ")
        // .getBytes());
        out.flush();
        fos.close();
    } catch (FileNotFoundException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }*/

    }
}
