package com.example.arshadhusain.weshare;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void onCreateSetup() {
        setContentView(R.layout.sign_in_activity);
        username = (EditText) findViewById(R.id.SignInInput1);
        String UserName = username.getText().toString();

        try {
            FileInputStream inputFile = openFileInput(UserName);
            BufferedReader input = new BufferedReader(new InputStreamReader(inputFile));
            //String line = input.readLine();
            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<FuelLog>>() {}.getType();
            FuelLogs = gson.fromJson(input, listType);
            /*while (line != null) {
                fuelList.add(line);
                line = input.readLine();
            }*/

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            //e.printStackTrace();
            FuelLogs = new ArrayList<FuelLog>();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            //e.printStackTrace();
            throw new RuntimeException();
        }




    }

    public void onCreateListeners() {


    }

}