package com.example.arshadhusain.weshare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

//MyBiddingActivity view activity

/**
 * Activity that shows all items that active user is bidding on.
 *
 * Started from NavigationMainActivity.
 * Doesn't start any new activities(for now).
 *
 * @author Arshad
 * @version 1.0
 */
public class MyBiddingActivity extends AppCompatActivity {

    public static ArrayList<Bid> myBids = new ArrayList<>();
    public ArrayAdapter<Bid> adapter;
    private String myUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bidding);

        Intent intent = getIntent();


        if(intent.hasExtra("myUsername")) {
            myUsername = intent.getStringExtra("myUsername");
        }

        ListView allBidsList = (ListView) findViewById(R.id.listView);

        ArrayList<Bid> allBids = new ArrayList<>();

        ElasticSearchAppController.GetBidsTask getBidsTask = new ElasticSearchAppController.GetBidsTask();
        getBidsTask.execute();

        try {
            allBids.addAll(getBidsTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("NUMBER OF FOUND BIDS FOR THIS USER");
        System.out.printf("%d\n", NavigationMainActivity.allBids.size());

        myBids.clear();

        for (Bid bid : allBids) {
            System.out.printf("%s\n", bid.getBidder());
            System.out.printf("%s\n", myUsername);

            if((bid.getBidder()).equals(myUsername)) {
                System.out.printf("%s\n", bid.getItem());
                myBids.add(bid);
            }
        }

        System.out.println("NUMBER OF FOUND BIDS FOR THIS USER");
        System.out.printf("%d\n", myBids.size());

        for (int x=0; x<myBids.size(); x++) {
            System.out.println(myBids.get(x).getItem());
        }
        adapter = new ArrayAdapter<>(this,
                R.layout.list_item, myBids);
        allBidsList.setAdapter(adapter);
    }
}
