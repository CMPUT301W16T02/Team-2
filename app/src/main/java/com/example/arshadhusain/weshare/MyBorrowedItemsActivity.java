package com.example.arshadhusain.weshare;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class MyBorrowedItemsActivity extends AppCompatActivity {
    public static ArrayList<Item> myBorrowedItems = new ArrayList<Item>();
    public ArrayAdapter<Item> adapter;
    private String myUsername;

    private ListView myBorrowedItemsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_borrowed_items);

        Intent intent = getIntent();

        if (intent.hasExtra("myUsername")) {
            myUsername = intent.getStringExtra("myUsername");
        }
        myBorrowedItemsList = (ListView) findViewById(R.id.myBorrowedItems);

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

}
