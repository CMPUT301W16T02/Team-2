package com.example.arshadhusain.weshare;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Activity that shows all items that active user is borrowing on.
 *
 * Started from NavigationMainActivity.
 * Doesn't start any new activities(for now).
 *
 * @author Arshad
 * @version 1.0
 */
public class MyBorrowingsActivity extends AppCompatActivity {
    public static ArrayList<Item> myBorrowingItems = new ArrayList<Item>();
    public ArrayAdapter<Item> adapter;
    private String myUsername;

    private ListView myBorrowingsList;
    static final int CHANGE_MADE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_borrowings);

        Intent intent = getIntent();

        if (intent.hasExtra("myUsername")) {
            myUsername = intent.getStringExtra("myUsername");
        }
        myBorrowingsList = (ListView) findViewById(R.id.myBorrowings);
        myBorrowingsList.setOnItemClickListener(onItemClickListener);


        System.out.printf("USERNAME: %s\n", myUsername);

        ArrayList<Item> allItems = new ArrayList<Item>();
        ElasticSearchAppController.GetMyItemsTask getMyItemsTask = new ElasticSearchAppController.GetMyItemsTask();
        getMyItemsTask.execute("");

        try {
            allItems.addAll(getMyItemsTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        myBorrowingItems.clear();
        for (Item item : allItems) {
            if (item.getStatus() == 2 && item.getBorrower().equals(myUsername)) {
                myBorrowingItems.add(item);
            }
        }

        adapter = new ArrayAdapter<Item>(this, R.layout.list_item, myBorrowingItems);
        myBorrowingsList.setAdapter(adapter);

    }

    /**
     * Method sets up functionality for when an item in a list is clicked.
     */
    private AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            viewItemInfo(myBorrowingItems.get(position).getName(), myBorrowingItems.get(position).getOwner());
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
