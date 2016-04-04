package com.example.arshadhusain.weshare;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Created by arshadhusain on 16-03-14.
 */
public class MyItemListWithBids extends AppCompatActivity {
    public static ArrayList<Bid> myItemsWithBids = new ArrayList<>();
    public ArrayAdapter<Bid> adapter;
    private String myUsername;

    private ListView MyItemsWithBidsList;

    final static int CHANGE_MADE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_items_with_bids);

        Intent intent = getIntent();


        if(intent.hasExtra("myUsername")) {
            myUsername = intent.getStringExtra("myUsername");
        }

        MyItemsWithBidsList = (ListView) findViewById(R.id.MyItemsBids);

        MyItemsWithBidsList.setOnItemClickListener(onItemClickListener);

        System.out.printf("ITEM LIST BIDS USERNAME: %s\n", myUsername);

        ArrayList<Bid> allBids = new ArrayList<>();

        ElasticSearchAppController.GetBidsTask getBidsTask = new ElasticSearchAppController.GetBidsTask();
        getBidsTask.execute();

        try {
            allBids.addAll(getBidsTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("NUMBER OF FOUND ITEMS FOR THIS USER");

        System.out.printf("%d\n", NavigationMainActivity.allBids.size());

        myItemsWithBids.clear();
        for (Bid bid : allBids) {
            if (bid.getItemOwner().equals(myUsername)) {
                myItemsWithBids.add(bid);
            }
        }

        adapter = new ArrayAdapter<>(this, R.layout.list_item, myItemsWithBids);
        MyItemsWithBidsList.setAdapter(adapter);


    }

    /**
     * Method sets up functionality for when an item in a list is clicked.
     */
    private AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            editItem(myItemsWithBids.get(position).getItem());
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
