package com.example.arshadhusain.weshare;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

/**
 * Key word search
 *
 * @Author: Arshad
 * @Version: 1.0
 **/

public class KeywordSearchActivity extends AppCompatActivity {
    private EditText searchKeyword;
    private ArrayList<Item> searchResults = new ArrayList<>();
    private String myUsername;
    static final int CHANGE_MADE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.onCreateSetup();
        this.onCreateListeners();
    }
    public void onCreateSetup() {
        setContentView(R.layout.content_keyword_search);
        Intent intent = getIntent();

        if(intent.hasExtra("myUsername")) {
            myUsername = intent.getStringExtra("myUsername");
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
        intent.putExtra("myUsername", myUsername);
        intent.putExtra("stringOfWords", individualWords);

        startActivityForResult(intent, CHANGE_MADE);
        finish();
    }
}
