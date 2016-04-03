package com.example.arshadhusain.weshare;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class KeywordSearchActivity extends AppCompatActivity {
    private EditText searchKeyword;
    private ArrayList<SearchResult> searchResults = new ArrayList<SearchResult>();
    private String activeUser;
    static final int CHANGE_MADE = 1;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.onCreateSetup();
        this.onCreateListeners();
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }
    public void onCreateSetup() {
        setContentView(R.layout.content_keyword_search);
        Intent intent = getIntent();

        if(intent.hasExtra("activeUser")) {
            activeUser = intent.getStringExtra("activeUser");
        }

        searchKeyword = (EditText) findViewById(R.id.SearchInput);
    }

    public void onCreateListeners() {
        Button keywordSearchButton = (Button) findViewById(R.id.KeywordSearchButton);
        keywordSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_OK);
                String SearchKeyboard = searchKeyword.getText().toString();
                String[] individualWords = SearchKeyboard.split(" ");

                sendSearchQuery(individualWords);


            }
        });



    }

    public void sendSearchQuery(String[] individualWords) {
        Intent intent = new Intent(this, SearchResultsActivity.class);
        intent.putExtra("stringOfWords", individualWords);

        startActivityForResult(intent, CHANGE_MADE);
        finish();
    }


}
