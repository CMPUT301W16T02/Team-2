package com.example.arshadhusain.weshare;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ItemInfoActivity extends AppCompatActivity {

    private TextView name;
    private TextView owner;
    private TextView description;
    private TextView status;

    private EditText bidAmount;

    private int itemPos;
    private Item itemToView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_info);

        Intent intent = getIntent();

        if (intent.hasExtra("itemPos")) {
            itemPos = intent.getIntExtra("itemPos", itemPos);
        }

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
                //submitBid(v);
            }
        });


    }
}