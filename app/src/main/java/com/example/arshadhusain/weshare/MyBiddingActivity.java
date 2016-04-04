package com.example.arshadhusain.weshare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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
    static final int CHANGE_MADE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bidding);

        Intent intent = getIntent();

        if(intent.hasExtra("myUsername")) {
            myUsername = intent.getStringExtra("myUsername");
        }

        ListView allBidsList = (ListView) findViewById(R.id.listView);
        allBidsList.setOnItemClickListener(onItemClickListener);

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

    /**
     * Method sets up functionality for when an item in a list is clicked.
     */
    private AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            viewItemInfo(myBids.get(position).getItem(), myBids.get(position).getItemOwner());
        }
    };

    /**
     * Allows user to view item. Passes intent to ItemInfoActivity
     *
     * @See: ItemInfoActivity
     */
    public void viewItemInfo(String itemName, String ownerName){
        Intent intent = new Intent(this, ItemInfoActivity.class);
        intent.putExtra("itemName", itemName);
        intent.putExtra("ownerName", ownerName);
        intent.putExtra("myUsername", myUsername);
        startActivityForResult(intent, CHANGE_MADE);
    }
}
