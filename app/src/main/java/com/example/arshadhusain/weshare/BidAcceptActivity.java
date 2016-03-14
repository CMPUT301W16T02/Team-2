package com.example.arshadhusain.weshare;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class BidAcceptActivity extends AppCompatActivity {

    TextView bidText;
    String itemBidder;
    String itemName;
    String itemOwner;
    public Context context1;
    public Context context2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bid_accept);

        context1 = NavigationMainActivity.getContext();
        context2 = NavigationMainActivity.getContext();


        Intent intent = getIntent();

        if(intent.hasExtra("itemName")) {
            itemName = intent.getStringExtra("itemName");
        }
        if(intent.hasExtra("itemOwner")) {
            itemOwner = intent.getStringExtra("itemOwner");
        }

        bidText = (TextView) findViewById(R.id.bidInfoText);
        getBidText();

        Button acceptButton = (Button)findViewById(R.id.acceptButton);

        acceptButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                acceptBid();
            }
        });

        Button viewBidderButton = (Button)findViewById(R.id.viewBidderButton);

        viewBidderButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                viewBidderProfile();
            }
        });

        Button declineButton = (Button)findViewById(R.id.declineButton);

        declineButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                declineBid();
            }
        });


    }

    public void getBidText(){
        for(int i=0; i<NavigationMainActivity.allBids.size(); i++){
            Bid currentBid = NavigationMainActivity.allBids.get(i);
            if(currentBid.getItem().equals(itemName) &&
                    currentBid.getItemOwner().equals(itemOwner)){
                bidText.setText(currentBid.toString());
                itemBidder = currentBid.getBidder();
            }
        }
    }

    public void viewBidderProfile(){
        Intent intent = new Intent(this, ViewProfileActivity.class);
        //startActivity(intent);
        String UserName = itemBidder;
        System.out.println(UserName);
        intent.putExtra("Username", UserName);
        startActivity(intent);
    }

    public void acceptBid(){
        for(int i=0; i<NavigationMainActivity.allItems.size(); i++) {
            Item currentItem = NavigationMainActivity.allItems.get(i);
            if (currentItem.getName().equals(itemName) &&
                    currentItem.getOwner().equals(itemOwner)) {
                currentItem.setStatus(2);
                currentItem.setBorrower(itemBidder);
                NavigationMainActivity.saveInFile(context1);
            }
        }

        ArrayList<Bid> bidsToRemove = new ArrayList<Bid>();

        for(int i=0; i<NavigationMainActivity.allBids.size(); i++) {
            Bid currentBid = NavigationMainActivity.allBids.get(i);
            if (currentBid.getItem().equals(itemName) &&
                    currentBid.getItemOwner().equals(itemOwner)){
                NavigationMainActivity.allBids.remove(i);
            }
        }

        NavigationMainActivity.saveBidsToFile(context2);

        setResult(RESULT_OK);
        finish();
    }

    public void declineBid(){
        for(int i=0; i<NavigationMainActivity.allBids.size(); i++) {
            Bid currentBid = NavigationMainActivity.allBids.get(i);
            if (currentBid.getItem().equals(itemName) &&
                    currentBid.getItemOwner().equals(itemOwner) &&
                    currentBid.getBidder().equals(itemBidder)){
                NavigationMainActivity.allBids.remove(i);
            }
        }

        NavigationMainActivity.saveBidsToFile(context2);

        setResult(RESULT_OK);
        finish();
    }
}
