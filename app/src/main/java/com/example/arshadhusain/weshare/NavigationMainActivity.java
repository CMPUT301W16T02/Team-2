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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.onCreateSetup();
        this.onCreateListeners();

    }

    public void onCreateSetup() {
        setContentView(R.layout.navigation_main_activity);


    }

    public void onCreateListeners() {
        Button AccountInfo = (Button)findViewById(R.id.AccountInfo);
        Button MyItems = (Button)findViewById(R.id.MyItems);
        Button MyBorrows = (Button)findViewById(R.id.MyBorrows);

        AccountInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_OK);

                Intent intent = new Intent(NavigationMainActivity.this, MainItemListActivity.class); //YOU NEED HANSON'S EDIT AND VIEW PROFILE FUCNTIONALITY
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
