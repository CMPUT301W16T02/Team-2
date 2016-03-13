package com.example.arshadhusain.weshare;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by hasan on 2016-03-12.
 */
public class Bidding extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bidding);

        Bid[] bidArray = {};

        //http://www.i-programmer.info/programming/android/7849-android-adventures-listview-and-adapters.html 2016-03-12
        ArrayAdapter<Bid> bidAdapter = new
                ArrayAdapter<Bid>(this, android.R.layout.activity_list_item, bidArray);
        ListView myList = (ListView) findViewById(R.id.listView);
        myList.setAdapter(bidAdapter);



    }
}
