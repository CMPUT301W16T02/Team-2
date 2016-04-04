package com.example.arshadhusain.weshare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Activity that allows a user to accept or decline a bid an item they own.
 *
 * Started by EditItemActivity.
 * Returns to EditItemActivity bid is accepted or declined, or when back button is pressed.
 *
 * Starts ViewProfileActivity when view bidder profile button is pressed.
 *
 * Now including elastic search functionality
 *
 * @author Hanson, Christopher
 * @version 2.5
 */
public class BidAcceptActivity extends AppCompatActivity {
    private Bid bidToView;
    private String itemBidder;
    private String itemName;
    private String itemOwner;
    private ArrayList<Bid> allBids = new ArrayList<>();

    final static int TEXTSIZE = 18;
    final static int GET_LOCATION = 4312;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bid_accept);

        Intent intent = getIntent();

        if(intent.hasExtra("itemName")) {
            itemName = intent.getStringExtra("itemName");
        }
        if(intent.hasExtra("itemOwner")) {
            itemOwner = intent.getStringExtra("itemOwner");
        }
        if(intent.hasExtra("bidderName")) {
            itemBidder = intent.getStringExtra("bidderName");
        }

        allBids.clear();
        allBids = new ArrayList<>();
        ElasticSearchAppController.GetBidsTask getBidsTask = new ElasticSearchAppController.GetBidsTask();
        getBidsTask.execute();

        try {
            allBids.addAll(getBidsTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        for (Bid bid : allBids) {
            if (bid.getBidder().equals(itemBidder) && bid.getItem().equals(itemName) &&
                    bid.getItemOwner().equals(itemOwner)) {
                bidToView = bid;
            }
        }

        TextView bidTextView = (TextView) findViewById(R.id.bidInfoText);
        bidTextView.setTextSize(TEXTSIZE);
        bidTextView.setText(bidToView.toString());

        Button acceptButton = (Button)findViewById(R.id.acceptButton);

        acceptButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent(BidAcceptActivity.this, SetLocationActivity.class);
                intent.putExtra("itemName", itemName);
                intent.putExtra("itemOwner", itemOwner);
                startActivity(intent);
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


    public void viewBidderProfile(){
        Intent intent = new Intent(this, ViewProfileActivity.class);
        intent.putExtra("username", itemBidder);
        startActivity(intent);
    }

    public void acceptBid(){
        // Set the item's status to borrowed
        ElasticSearchAppController.GetMyItemsTask getMyItemsTask = new ElasticSearchAppController.GetMyItemsTask();
        getMyItemsTask.execute("");
        ArrayList<Item> allItems = new ArrayList<>();
        try {
            allItems.addAll(getMyItemsTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        for (Item item : allItems) {
            if (item.getName().equals(itemName) && item.getOwner().equals(itemOwner)) {
                item.setStatus(2);
                item.setBorrower(itemBidder);
                ElasticSearchAppController.EditItemTask editItemTask = new ElasticSearchAppController.EditItemTask();
                editItemTask.execute(item);
            }
        }

        for (Bid bid : allBids) {
            if (bid.getItem().equals(itemName) && bid.getItemOwner().equals(itemOwner)) {
                ElasticSearchAppController.DeleteBidTask deleteBidTask = new ElasticSearchAppController.DeleteBidTask();
                deleteBidTask.execute(bid);
            }
        }

        setResult(RESULT_OK);
        finish();
    }

    public void declineBid(){
        ElasticSearchAppController.DeleteBidTask deleteBidTask = new ElasticSearchAppController.DeleteBidTask();
        deleteBidTask.execute(bidToView);

        allBids.clear();
        allBids = new ArrayList<>();
        ElasticSearchAppController.GetBidsTask getBidsTask = new ElasticSearchAppController.GetBidsTask();
        getBidsTask.execute();

        try {
            allBids.addAll(getBidsTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        // If item has zero bids, set status back to 0 ("Available")
        int bidCount = 0;
        for (Bid bid : allBids) {
            if (bid.getItem().equals(itemName) && bid.getItemOwner().equals(itemOwner)) {
                bidCount++;
            }
        }

        if(bidCount == 0) {
            ElasticSearchAppController.GetMyItemsTask getMyItemsTask = new ElasticSearchAppController.GetMyItemsTask();
            getMyItemsTask.execute("");
            ArrayList<Item> allItems = new ArrayList<>();
            try {
                allItems.addAll(getMyItemsTask.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

            for (Item item : allItems) {
                if (item.getName().equals(itemName) && item.getOwner().equals(itemOwner)) {
                    item.setStatus(0);
                    item.setBorrower("");
                    ElasticSearchAppController.EditItemTask editItemTask = new ElasticSearchAppController.EditItemTask();
                    editItemTask.execute(item);
                }
            }
        }

        Intent intent = getIntent();
        setResult(RESULT_OK, intent);
        finish();
    }
}