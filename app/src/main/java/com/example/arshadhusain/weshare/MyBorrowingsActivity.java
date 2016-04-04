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

public class MyBorrowingsActivity extends AppCompatActivity {
    public static ArrayList<Item> myBorrowingItems = new ArrayList<Item>();
    public ArrayAdapter<Item> adapter;
    private String myUsername;

    private ListView myBorrowingsList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_borrowings);

        Intent intent = getIntent();

        if (intent.hasExtra("myUsername")) {
            myUsername = intent.getStringExtra("myUsername");
        }
        myBorrowingsList = (ListView) findViewById(R.id.myBorrowings);

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

}
