package com.example.arshadhusain.weshare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MyItemsActivity extends AppCompatActivity {

    public static ArrayList<Item> myItems = new ArrayList<Item>();
    public ArrayAdapter<Item> adapter;
    private String MyUsername;

    private ListView myItemsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_items);


        Intent intent = getIntent();


        if(intent.hasExtra("Username")) {
            MyUsername = intent.getStringExtra("Username");
        }

        myItemsList = (ListView) findViewById(R.id.MyItemList);

        myItems.clear();
        for (int x=0; x<NavigationMainActivity.allItems.size(); x++) {
            //System.out.println(NavigationMainActivity.allBids.get(x).getItem());
            //System.out.printf("%s\n", NavigationMainActivity.allBids.get(x).getItem());
            //System.out.printf("%s\n", NavigationMainActivity.allBids.get(x).getBidder());
            //System.out.printf("%s\n", MyUsername);

            //System.out.printf("INSIDE ALLITEMS ARRAYLIST INTERATION\n");
            System.out.printf("allItems: %s MyUsername %s\n", NavigationMainActivity.allItems.get(x).getOwner(), MyUsername);

            if((NavigationMainActivity.allItems.get(x).getOwner()).equals(MyUsername))
            {

                Item ItemToCopy = NavigationMainActivity.allItems.get(x);
                System.out.printf("allItems: %s MyUsername %s\n", NavigationMainActivity.allItems.get(x).getOwner(), MyUsername);
                myItems.add(ItemToCopy);
            }

        }
        System.out.println("NUMBER OF FOUND BIDS FOR THIS USER");

        System.out.printf("%d\n", myItems.size());

        for (int x=0; x<myItems.size(); x++) {
            System.out.println(myItems.get(x).getName());


        }

        adapter = new ArrayAdapter<Item>(this,
                R.layout.list_item, myItems);
        myItemsList.setAdapter(adapter);
    }
}
