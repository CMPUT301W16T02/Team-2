package com.example.arshadhusain.weshare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Shows the information regarding specific item.
 * Passes intent user and item.
 *
 * Now incorporated elastic search and photo functionality
 *
 * @Author: Christopher, Arshad, Philip
 * @Version: 2.5
 */

public class ItemInfoActivity extends AppCompatActivity {

    public EditText getBidAmount() {
        return bidAmount;
    }

    public Button getViewOwner() {
        return viewOwner;
    }

    private EditText bidAmount;

    private Button viewOwner;
    private Button showLocation;

    TextView nameText;
    TextView ownerText;
    TextView descriptionText;
    TextView statusText;

    private Item itemToView;
    double doubleUserName;

    String myUsername;
    String ownerName;
    String itemName;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_info);

        Intent intent = getIntent();

        if(intent.hasExtra("itemName")) {
            itemName = intent.getStringExtra("itemName");
        }
        if(intent.hasExtra("ownerName")) {
            ownerName = intent.getStringExtra("ownerName");
        }
        if(intent.hasExtra("myUsername")) {
            myUsername = intent.getStringExtra("myUsername");
        }

        ElasticSearchAppController.GetMyItemsTask getMyItemsTask = new ElasticSearchAppController.GetMyItemsTask();
        getMyItemsTask.execute("");
        ArrayList<Item> myItems = new ArrayList<>();
        try {
            myItems.addAll(getMyItemsTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        for (Item item : myItems) {
            if (item.getName().equals(itemName) && item.getOwner().equals(ownerName)) {
                itemToView = item;
            }
        }

        nameText = (TextView) findViewById(R.id.itemNameText);
        nameText.setText(itemToView.getName());
        ownerText = (TextView) findViewById(R.id.itemOwnerText);
        ownerText.setText(itemToView.getOwner());
        descriptionText = (TextView) findViewById(R.id.itemDescText);
        descriptionText.setText(itemToView.getDescription());
        ImageView itemImage = (ImageView) findViewById(R.id.itemImage);
        itemImage.setImageBitmap(itemToView.getThumbnail());
        statusText = (TextView) findViewById(R.id.itemStatusText);
        String statusString = "Item Status: " + itemToView.statusToString();
        statusText.setText(statusString);

        bidAmount = (EditText) findViewById(R.id.bidAmountField);

        viewOwner = (Button) findViewById(R.id.viewOwnerButton);

        viewOwner.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ItemInfoActivity.this, ViewProfileActivity.class);
                String ownerName = itemToView.getOwner();
                System.out.println(ownerName);
                intent.putExtra("username", ownerName);
                startActivity(intent);
            }
        });

        Button submitBid = (Button) findViewById(R.id.submitBidButton);

        submitBid.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String BidAmount = bidAmount.getText().toString();
                doubleUserName = Double.parseDouble(BidAmount);
                addBid();
                setResult(RESULT_OK);
            }
        });

        showLocation = (Button) findViewById(R.id.showLocationButton);

        showLocation.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String address = itemToView.getAddress();
                Double latitude = itemToView.getLatitude();
                Double longitude = itemToView.getLongitude();

                Intent intent = new Intent(ItemInfoActivity.this, ShowLocationActivity.class);

                intent.putExtra("address", address);
                intent.putExtra("latitude", latitude);
                intent.putExtra("longitude", longitude);

                startActivity(intent);
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
        System.out.println("****INSIDE ADD BID****");
        String itemName = nameText.getText().toString();
        String itemOwner = ownerText.getText().toString();
        String itemDescription = descriptionText.getText().toString();
        int ItemStatus = itemToView.getStatus();

        if(ItemStatus == 2){
            Toast.makeText(getApplicationContext(), "Item is being borrowed. Cannot place bid!", Toast.LENGTH_LONG).show();
        } else {
            System.out.println(itemName);
            System.out.println(itemOwner);
            System.out.println(itemDescription);

            Bid newBid = new Bid(myUsername, doubleUserName, itemName, itemOwner, itemDescription);

            ElasticSearchAppController.AddBidTask addBidTask = new ElasticSearchAppController.AddBidTask();
            addBidTask.execute(newBid);

            itemToView.setStatus(1);
            String newStatusString = "Item Status: " + itemToView.statusToString();
            statusText.setText(newStatusString);

            ElasticSearchAppController.EditItemTask editItemTask = new ElasticSearchAppController.EditItemTask();
            editItemTask.execute(itemToView);


            Toast.makeText(getApplicationContext(), "Bid added!", Toast.LENGTH_LONG).show();
            bidAmount.setText("");
        }
    }

}