package com.example.arshadhusain.weshare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SearchResultsActivity extends AppCompatActivity {

    private String keyword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);

        Intent intent = getIntent();

        if(intent.hasExtra("keyword")) {
            keyword = intent.getStringExtra("keyword", keyword);
        }

    }
}
