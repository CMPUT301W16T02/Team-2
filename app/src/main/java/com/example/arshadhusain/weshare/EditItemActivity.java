package com.example.arshadhusain.weshare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Allows user to edit item name and description.
 * Passes intent of item to edit.
 *
 * Now incorporated elastic search and photo removal functionality
 *
 * @author: Christopher, Arshad, Philip
 * @version: 2.5
 */

public class EditItemActivity extends AppCompatActivity {
    private String itemName;
    private Item itemToEdit;
    private String myUsername;

    private ListView bidsListView;
    private EditText nameField;
    private EditText descriptionField;

    public ArrayAdapter<Bid> adapter;

    public static ArrayList<Bid> bidsOnItem = new ArrayList<>();

    static final int CHANGE_MADE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);

        Intent intent = getIntent();

        if(intent.hasExtra("itemName")) {
            itemName = intent.getStringExtra("itemName");
        }
        if(intent.hasExtra("myUsername")) {
            myUsername = intent.getStringExtra("myUsername");
        }

        ElasticSearchAppController.GetMyItemsTask getMyItemsTask = new ElasticSearchAppController.GetMyItemsTask();
        getMyItemsTask.execute(myUsername);
        ArrayList<Item> myItems = new ArrayList<>();
        try {
            myItems.addAll(getMyItemsTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        for (Item item : myItems) {
            if (item.getName().equals(itemName) && item.getOwner().equals(myUsername)) {
                itemToEdit = item;
            }
        }

        nameField = (EditText) findViewById(R.id.itemNameField);
        descriptionField = (EditText) findViewById(R.id.itemDescField);
        bidsListView = (ListView) findViewById(R.id.bidsListView);

        nameField.setText(itemToEdit.getName());
        descriptionField.setText(itemToEdit.getDescription());

        ImageView itemImage = (ImageView) findViewById(R.id.itemImage);
        itemImage.setImageBitmap(itemToEdit.getThumbnail());

        Button saveButton = (Button) findViewById(R.id.saveButton);

        saveButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                saveEdit(v);
            }
        });

        Button removePicture = (Button) findViewById(R.id.removePicture);

        removePicture.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                removePicture(v);
            }
        });

        Button deleteButton = (Button)findViewById(R.id.deleteButton);

        deleteButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                deleteItem(v);
            }
        });

        Button returnButton = (Button)findViewById(R.id.returnButton);

        returnButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                returnItem(v);
            }
        });    }

    /**
     * onStart create adapter for bidlist
     */
    private AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            viewBid(bidsOnItem.get(position));
        }
    };


    @Override
    protected void onStart() {
        super.onStart();

        Intent intent = getIntent();
        if(intent.hasExtra("myUsername")) {
            myUsername = intent.getStringExtra("myUsername");
        }
        getItemsBids();
        adapter = new ArrayAdapter<>(this, R.layout.list_item, bidsOnItem);
        bidsListView.setAdapter(adapter);
        bidsListView.setOnItemClickListener(onItemClickListener);
    }

    /**
     * Setup view for edit item
     * @param view
     */
    public void saveEdit(View view){

        String newName = nameField.getText().toString();
        String newDesc = descriptionField.getText().toString();

        itemToEdit.setName(newName);
        itemToEdit.setDescription(newDesc);

        if(itemToEdit.getName().isEmpty() && itemToEdit.getDescription().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please enter your information into each field", Toast.LENGTH_LONG).show();
        } else if (itemToEdit.getName().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please enter the item name", Toast.LENGTH_LONG).show();
        } else if (itemToEdit.getDescription().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please enter the item description", Toast.LENGTH_LONG).show();
        } else {
            ElasticSearchAppController.EditItemTask editItemTask = new ElasticSearchAppController.EditItemTask();
            editItemTask.execute(itemToEdit);

            setResult(RESULT_OK);
            finish();
        }
    }

    /**
     * Removes picture from item
     *
     * @param view
     */
    public void removePicture(View view){

        String newName = nameField.getText().toString();
        String newDesc = descriptionField.getText().toString();

        itemToEdit.setName(newName);
        itemToEdit.setDescription(newDesc);
        itemToEdit.removeThumbnail();

        //PUT REQUEST

        ElasticSearchAppController.EditItemTask editItemTask = new ElasticSearchAppController.EditItemTask();
        editItemTask.execute(itemToEdit);

        setResult(RESULT_OK);
        finish();
    }

    /**
     * Remove item
     * @param view
     */
    public void deleteItem(View view){

        ElasticSearchAppController.DeleteItemTask deleteItemTask = new ElasticSearchAppController.DeleteItemTask();
        deleteItemTask.execute(itemToEdit);

        ElasticSearchAppController.GetBidsTask getBidsTask = new ElasticSearchAppController.GetBidsTask();
        getBidsTask.execute();
        ArrayList<Bid> allBids = new ArrayList<>();

        try {
            allBids.addAll(getBidsTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        for (Bid bid : allBids) {
            if (bid.getItem().equals(itemToEdit.getName()) && bid.getItemOwner().equals(itemToEdit.getOwner())){
                ElasticSearchAppController.DeleteBidTask deleteBidTask = new ElasticSearchAppController.DeleteBidTask();
                deleteBidTask.execute(bid);
            }
        }

        setResult(RESULT_OK);
        finish();
    }

    public void returnItem(View view) {

        if (itemToEdit.getStatus() == 2) {
            itemToEdit.setStatus(0);
            itemToEdit.setBorrower("");
            itemToEdit.setAddress("No address given");
            itemToEdit.setLatitude(0.0);
            itemToEdit.setLongitude(0.0);

            ElasticSearchAppController.EditItemTask editItemTask = new ElasticSearchAppController.EditItemTask();
            editItemTask.execute(itemToEdit);

            setResult(RESULT_OK);
            finish();
        } else {
            Toast.makeText(getApplicationContext(), "Item is not being borrowed\nCannot return", Toast.LENGTH_LONG).show();
        }
    }

    public void getItemsBids() {
        bidsOnItem.clear();
        ArrayList<Bid> allBids = new ArrayList<Bid>();

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
            if (bid.getItem().equals(itemToEdit.getName())){
                bidsOnItem.add(bid);
            }
        }

        System.out.println("NUMBER OF FOUND BIDS FOR THIS ITEM");

        System.out.printf("%d\n", bidsOnItem.size());

        for (int x=0; x<bidsOnItem.size(); x++) {
            System.out.println(bidsOnItem.get(x).getBidder());
        }
    }

    public void viewBid(Bid bidToView){
        String itemName = bidToView.getItem();
        String itemOwner = bidToView.getItemOwner();

        Intent intent = new Intent(this, BidAcceptActivity.class);
        intent.putExtra("itemName", itemName);
        intent.putExtra("itemOwner", itemOwner);
        intent.putExtra("bidderName", bidToView.getBidder());

        startActivityForResult(intent, CHANGE_MADE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CHANGE_MADE) {
            if (resultCode == RESULT_OK) {
                getItemsBids();
                adapter.notifyDataSetChanged();
            }
        }
    }
}