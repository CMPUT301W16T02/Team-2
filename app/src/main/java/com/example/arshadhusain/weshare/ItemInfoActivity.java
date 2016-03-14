package com.example.arshadhusain.weshare;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Shows the information regarding specific item.
 * Passes intent user and item.
 * @Author: Chris, Arshad
 * @Version: 1.0
 */

public class ItemInfoActivity extends AppCompatActivity {

    private TextView name;
    private TextView owner;
    private TextView description;
    private TextView status;

    private EditText bidAmount;

    private int itemPos;
    private Item itemToView;
    double doubleUserName;

    String UserName;

    public Context context2;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_info);

        Intent intent = getIntent();

        if (intent.hasExtra("itemPos")) {
            itemPos = intent.getIntExtra("itemPos", itemPos);
        }
        if(intent.hasExtra("activeUser")) {
            UserName = intent.getStringExtra("activeUser");
        }

        context2 = NavigationMainActivity.getContext();


        itemToView = NavigationMainActivity.allItems.get(itemPos);

        name = (TextView) findViewById(R.id.itemName);
        name.setText(itemToView.getName());
        owner = (TextView) findViewById(R.id.itemOwner);
        owner.setText(itemToView.getOwner());
        description = (TextView) findViewById(R.id.itemDesc);
        description.setText(itemToView.getDescription());
        status = (TextView) findViewById(R.id.itemStatus);
        status.setText("Item Status: " + itemToView.statusToString());

        bidAmount = (EditText) findViewById(R.id.bidAmount);

        Button viewOwner = (Button) findViewById(R.id.viewOwnerButton);

        viewOwner.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent(ItemInfoActivity.this, ViewProfileActivity.class);
                //startActivity(intent);
                String UserName = owner.getText().toString();
                System.out.println(UserName);
                intent.putExtra("Username", UserName);
                startActivity(intent);
            }
        });

        Button submitBid = (Button) findViewById(R.id.submitBidButton);

        submitBid.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                String BidAmount = bidAmount.getText().toString();
                doubleUserName = Double.parseDouble(BidAmount);

                addBid();
                //submitBid(v);
            }
        });


    }

    /**
     * Allows user to place bid on items.
     * Add bid to allBids.
     * Notifies user of bid
     *
     * @see NavigationMainActivity
     */
    public void addBid() {

        //Item newItem = new Item(itemName, itemDesc, owner);
        String ItemName = name.getText().toString();
        String ItemOwner = owner.getText().toString();
        String ItemDescription = description.getText().toString();
        int ItemStatus = itemToView.getStatus();

        if(ItemStatus == 2){
            Toast.makeText(getApplicationContext(), "Item is being borrowed. Cannot place bid!", Toast.LENGTH_LONG).show();
        } else {
            Bid newBid = new Bid(UserName, doubleUserName, ItemName, ItemOwner, ItemDescription);
            //NavigationMainActivity.allItems.add(newItem);
            NavigationMainActivity.allBids.add(newBid);
            NavigationMainActivity.saveBidsToFile(context2);

            itemToView.setStatus(1);

            NavigationMainActivity.saveInFile(context2);


            Toast.makeText(getApplicationContext(), "Bid added!", Toast.LENGTH_LONG).show();
            bidAmount.setText("");
        }
    }

}