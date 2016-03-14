package com.example.arshadhusain.weshare;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by arshadhusain on 16-03-14.
 */
public class MyItemListWithBids extends AppCompatActivity {
    public static ArrayList<Bid> myItemsWithBids = new ArrayList<Bid>();
    public ArrayAdapter<Bid> adapter;
    private String MyUsername;

    private ListView MyItemsWithBidsList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_items_with_bids);

        Intent intent = getIntent();


        if(intent.hasExtra("activeUser")) {
            MyUsername = intent.getStringExtra("activeUser");
        }

        MyItemsWithBidsList = (ListView) findViewById(R.id.MyItemsBids);

        System.out.printf("ITEM LIST BIDS USERNAME: %s\n", MyUsername);

        myItemsWithBids.clear();
        for (int x=0; x<NavigationMainActivity.allBids.size(); x++) {
            //System.out.println(NavigationMainActivity.allBids.get(x).getItem());
            //System.out.printf("%s\n", NavigationMainActivity.allBids.get(x).getItem());
            System.out.printf("%s\n", NavigationMainActivity.allBids.get(x).getBidder());
            System.out.printf("%s\n", MyUsername);


            if((NavigationMainActivity.allBids.get(x).getItemOwner()).equals(MyUsername))
            {

                Bid bidToCopy = NavigationMainActivity.allBids.get(x);
                System.out.printf("%s\n", bidToCopy.getItem());
                myItemsWithBids.add(bidToCopy);
            }

        }

        adapter = new ArrayAdapter<Bid>(this,
                R.layout.list_item, myItemsWithBids);
        MyItemsWithBidsList.setAdapter(adapter);


    }


}
