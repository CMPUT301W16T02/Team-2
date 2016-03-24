package com.example.arshadhusain.weshare;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

//import org.apache.http.client.HttpClient;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.impl.client.DefaultHttpClient;

/**
 * Activity that allows user to create an account. A profile is
 * required to use this app.
 *
 * Started by WelcomeActivity.
 * Returns to WelcomeActivity with successful account creation.
 *
 * @author Badran
 * @version 1.0
 */
public class CreateProfileActivity extends Activity{
    boolean valid;
    private Context context;
    private EditText username;
    private EditText email;
    private EditText city;
    private ArrayList<Account> Accounts = new ArrayList<Account>();
    ArrayAdapter<Account> adapter;
    //HttpClient httpclient = new DefaultHttpClient();
    //HttpPost httppost = new HttpPost("http://cmput301.softwareprocess.es:8080/");


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
        setContentView(R.layout.create_profile_activity);
        username = (EditText) findViewById(R.id.CreateProfileInput1);
        email = (EditText) findViewById(R.id.CreateProfileInput2);
        city = (EditText) findViewById(R.id.CreateProfileInput3);


    }

    /**
     * Method that sets up button clicking.
     */
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

                    File file = getFileStreamPath(UserName);



                    boolean fileExists = file.exists();
                    if(file.exists())
                    {
                        Toast.makeText(getApplicationContext(), "Username already exists", Toast.LENGTH_LONG).show();

                    }


                    if(!file.exists()) {
                        FileOutputStream fos = openFileOutput(UserName, 0);


                        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));
                        Gson gson = new Gson();
                        gson.toJson(Accounts, out);
                        //fos.write(new String(text6 + " | " + text + " | " + text1 + " | " + text2 + " | " + text3 + " | " + text4 + " | " + text5 + " \n ")
                        // .getBytes());
                        out.flush();
                        fos.close();
                        Toast.makeText(getApplicationContext(), "User Account Created!", Toast.LENGTH_LONG).show();

                    }
                } catch (FileNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Username not found", Toast.LENGTH_LONG).show();

                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                System.out.println("After save\n");



                Intent myIntent = new Intent(getApplicationContext(), WelcomeActivity.class);

                startActivityForResult(myIntent, 0);

                setResult(RESULT_OK);
                finish();

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