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

    public static ArrayList<Bid> myBids = new ArrayList<Bid>();
    public ArrayAdapter<Bid> adapter;
    private String MyUsername;

    private ListView allBidsList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bidding);

        Intent intent = getIntent();


        if(intent.hasExtra("activeUser")) {
            MyUsername = intent.getStringExtra("activeUser");
        }

        allBidsList = (ListView) findViewById(R.id.listView);

        //allBidsList.setOnItemClickListener(onItemClickListener);



        //ArrayList<Bid> ItemBids = new ArrayList<Bid>();
        NavigationMainActivity.allBids.clear();

        ElasticSearchAppController.GetBidsTask getBidsTask = new ElasticSearchAppController.GetBidsTask();
        getBidsTask.execute();

        try {
            NavigationMainActivity.allBids.addAll(getBidsTask.get());

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("NUMBER OF FOUND BIDS FOR THIS USER");

        System.out.printf("%d\n", NavigationMainActivity.allBids.size());

        myBids.clear();
        for (int x=0; x<NavigationMainActivity.allBids.size(); x++) {
            //System.out.println(NavigationMainActivity.allBids.get(x).getItem());
            //System.out.printf("%s\n", NavigationMainActivity.allBids.get(x).getItem());
            System.out.printf("%s\n", NavigationMainActivity.allBids.get(x).getBidder());
            System.out.printf("%s\n", MyUsername);


            if((NavigationMainActivity.allBids.get(x).getBidder()).equals(MyUsername))
            {

                Bid bidToCopy = NavigationMainActivity.allBids.get(x);
                System.out.printf("%s\n", bidToCopy.getItem());
                myBids.add(bidToCopy);
            }

        }
        System.out.println("NUMBER OF FOUND BIDS FOR THIS USER");

        System.out.printf("%d\n", myBids.size());

        for (int x=0; x<myBids.size(); x++) {
            System.out.println(myBids.get(x).getItem());


        }

        adapter = new ArrayAdapter<Bid>(this,
                R.layout.list_item, myBids);
        allBidsList.setAdapter(adapter);



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
