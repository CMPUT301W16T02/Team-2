package com.example.arshadhusain.weshare;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

//BiddingActivity view activity
public class BiddingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bidding);

        //Array coontaining all of user's bids
        //Bid[] bidArray = {};

        //http://www.i-programmer.info/programming/android/7849-android-adventures-listview-and-adapters.html 2016-03-12
        /*ArrayAdapter<Bid> bidAdapter = new
                ArrayAdapter<Bid>(
                this,
                android.R.layout.activity_list_item,
                bidArray);
        ListView myList = (ListView)
                findViewById(R.id.listView);
        myList.setAdapter(bidAdapter); */



    }
}
