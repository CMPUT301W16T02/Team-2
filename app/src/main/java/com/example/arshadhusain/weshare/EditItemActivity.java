package com.example.arshadhusain.weshare;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Allows user to edit item name and description.
 * Passes intent of item to edit.
 *
 * @Author: Chris
 * @Version: 1.0
 */

public class EditItemActivity extends AppCompatActivity {

    private EditText name;
    private EditText description;
    private ListView bidsList;
    public static ArrayList<Bid> bidsOnItem = new ArrayList<Bid>();


    private int itemPos;
    private Item itemToEdit;
    private String activeUser;

    private int selectedItemPos;

    public Context context1;
    final Context context = this;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);

        context1 = NavigationMainActivity.getContext();

        Intent intent = getIntent();

        if(intent.hasExtra("itemPos")) {
            itemPos = intent.getIntExtra("itemPos", itemPos);
        }
        /*
        if(intent.hasExtra("activeUser")) {
            activeUser = intent.getStringExtra("activeUser");
        }*/

        //itemToEdit = NavigationMainActivity.allItems.get(itemPos);

        name = (EditText) findViewById(R.id.itemName);
        description = (EditText) findViewById(R.id.itemDesc);


        //bidsList = (ListView) findViewById(R.id.bidsList);

        bidsList = (ListView) findViewById(R.id.bidsList);

        itemToEdit = MyItemsActivity.myItems.get(itemPos);


        name.setText(itemToEdit.getName());
        description.setText(itemToEdit.getDescription());
        ImageView itemImage = (ImageView) findViewById(R.id.itemImage);
        itemImage.setImageBitmap(itemToEdit.getThumbnail());


        Button saveEdit = (Button) findViewById(R.id.saveButton);

        saveEdit.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                editItem(v);
            }
        });

        Button removePicture = (Button) findViewById(R.id.removePicture);

        removePicture.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                removePicture(v);
            }
        });

        Button delete = (Button)findViewById(R.id.deleteButton);

        delete.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                deleteItem(v);
            }
        });

        Button cancel = (Button)findViewById(R.id.cancelButton);

        cancel.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                cancel(v);
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
            selectedItemPos = position;

            viewBid(bidsOnItem.get(selectedItemPos));

        }
    };


    @Override
    protected void onStart() {
        super.onStart();

        Intent intent = getIntent();
        if(intent.hasExtra("activeUser")) {
            activeUser = intent.getStringExtra("activeUser");
        }
        //THIS COMMENTED CODE IS VERY IMPORTANT
        getItemsBids();
        ArrayAdapter<Bid> adapter = new ArrayAdapter<Bid>(this,
                R.layout.list_item, bidsOnItem);
        bidsList.setAdapter(adapter);
        bidsList.setOnItemClickListener(onItemClickListener);
    }

    /**
     * Setup view for edit item
     *
     * @param view
     */
    public void editItem(View view){

        String newName = name.getText().toString();
        String newDesc = description.getText().toString();

        itemToEdit.setName(newName);
        itemToEdit.setDescription(newDesc);

        //PUT REQUEST

        ElasticSearchAppController.EditItemTask editItemTask = new ElasticSearchAppController.EditItemTask();
        editItemTask.execute(itemToEdit);

        setResult(RESULT_OK);
        finish();
    }

    /**
     * Removes picture from item
     *
     * @param view
     */
    public void removePicture(View view){

        String newName = name.getText().toString();
        String newDesc = description.getText().toString();

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
     * Remove item for allItems
     *
     * @See MainItemListActivity
     * @param view
     */
    public void deleteItem(View view){
        //NavigationMainActivity.allItems.remove(itemToEdit);

        //NavigationMainActivity.saveInFile(context1);
        //System.out.printf("%d\n", NavigationMainActivity.allItems.size());
        //Intent intent = new Intent(this, MyItemsActivity.class);

        ElasticSearchAppController.DeleteItemTask deleteItemTask = new ElasticSearchAppController.DeleteItemTask();
        deleteItemTask.execute(itemToEdit);
        //startActivityForResult(intent, 1);
        setResult(RESULT_OK);
        finish();
    }

    /**
     * Stop item edit
     *
     * @param view
     */
    public void cancel(View view) {
        setResult(RESULT_CANCELED);
        finish();
    }

    public void returnItem(View view) {

        itemToEdit.setStatus(0);
        itemToEdit.setBorrower("");

        NavigationMainActivity.saveInFile(context1);

        //PUT REQUEST

        ElasticSearchAppController.EditItemTask editItemTask = new ElasticSearchAppController.EditItemTask();
        editItemTask.execute(itemToEdit);

        setResult(RESULT_OK);
        finish();
    }

    public void getItemsBids() {
        /*
        ArrayList<Bid> itemBids = new ArrayList<Bid>();
        for(int i=0; i<NavigationMainActivity.allBids.size(); i++){
            String itemOwner = NavigationMainActivity.allBids.get(i).getItemOwner();
            String itemName = NavigationMainActivity.allBids.get(i).getItem();
            if(itemOwner.equals(activeUser) &&  itemName.equals(itemToEdit.getName())){
                itemBids.add(NavigationMainActivity.allBids.get(i));
            }
        }
        return itemBids;*/
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

        startActivityForResult(intent, 1);
    }
}