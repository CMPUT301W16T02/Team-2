package com.example.arshadhusain.weshare;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by arshadhusain on 16-03-12.
 */
public class NavigationMainActivity extends AppCompatActivity {

    private String MyUsername;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();

        if(intent.hasExtra("Username")) {
            MyUsername = intent.getStringExtra("Username");
        }

        this.onCreateSetup();
        this.onCreateListeners();

    }

    public void onCreateSetup() {
        setContentView(R.layout.navigation_main_activity);


    }

    public void onCreateListeners() {
        Button EditProfile = (Button)findViewById(R.id.EditProfile);
        Button MyItems = (Button)findViewById(R.id.MyItems);
        Button MyBorrows = (Button)findViewById(R.id.MyBorrows);

        EditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_OK);

                Intent intent = new Intent(NavigationMainActivity.this, EditProfileActivity.class); //YOU NEED HANSON'S EDIT AND VIEW PROFILE FUCNTIONALITY
                intent.putExtra("Username", MyUsername);
                startActivity(intent);

            }
        });

        MyItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_OK);

                Intent intent = new Intent(NavigationMainActivity.this, MainItemListActivity.class); //YOU NEED CHRIS' LIST ITEM FUNCTIONALITY
                startActivity(intent);

            }
        });

        MyBorrows.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_OK);

                Intent intent = new Intent(NavigationMainActivity.this, MainItemListActivity.class); //YOU NEED CHRIS' BORROWING FUNCTIONALITY
                startActivity(intent);

            }
        });
    }




}
