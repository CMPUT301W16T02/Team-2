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
 * Activity that shows all items that active user is bidding on.
 *
 * Started from NavigationMainActivity.
 * Doesn't start any new activities(for now).
 *
 * @author Arshad
 * @version 1.0
 */

public class MyBorrowedItemsActivity extends AppCompatActivity {
    public static ArrayList<Item> myBorrowedItems = new ArrayList<Item>();
    public ArrayAdapter<Item> adapter;
    private String myUsername;

    private ListView myBorrowedItemsList;

    final static int CHANGE_MADE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_borrowed_items);

        Intent intent = getIntent();

        if (intent.hasExtra("myUsername")) {
            myUsername = intent.getStringExtra("myUsername");
        }
        myBorrowedItemsList = (ListView) findViewById(R.id.myBorrowedItems);

        myBorrowedItemsList.setOnItemClickListener(onItemClickListener);

        System.out.printf("USERNAME: %s\n", myUsername);

        ArrayList<Item> myItems = new ArrayList<Item>();
        ElasticSearchAppController.GetMyItemsTask getMyItemsTask = new ElasticSearchAppController.GetMyItemsTask();
        getMyItemsTask.execute(myUsername);

        try {
            myItems.addAll(getMyItemsTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        myBorrowedItems.clear();
        for (Item item : myItems) {
            if (item.getStatus() == 2) {
                myBorrowedItems.add(item);
            }
        }

        adapter = new ArrayAdapter<Item>(this, R.layout.list_item, myBorrowedItems);
        myBorrowedItemsList.setAdapter(adapter);

    }

    /**
     * Method sets up functionality for when an item in a list is clicked.
     */
    private AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            editItem(myBorrowedItems.get(position).getName());
        }
    };

    /**
     * Method starts the EditItemActivity to edit an item
     */
    public void editItem(String itemName) {
        Intent intent = new Intent(this, EditItemActivity.class);
        intent.putExtra("itemName", itemName);
        intent.putExtra("myUsername", myUsername);
        startActivityForResult(intent, CHANGE_MADE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        System.out.println("INSIDE onActivityResult");
        if (requestCode == CHANGE_MADE) {
            adapter.notifyDataSetChanged();
            setResult(RESULT_OK);
        }
    }
}
