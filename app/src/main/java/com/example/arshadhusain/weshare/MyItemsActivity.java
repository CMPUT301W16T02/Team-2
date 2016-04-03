package com.example.arshadhusain.weshare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 *  Activity shows a list of items that belong to the active user.
 *
 *  Started from NavigationMainActivity
 *  Can start EditItemActivity
 *
 *  @author Badran, Arshad, Christopher
 *  @version  2.0
 */
public class MyItemsActivity extends AppCompatActivity {

    public static ArrayList<Item> myItems = new ArrayList<>();
    public ArrayAdapter<Item> adapter;
    private String myUsername;
    final static int CHANGE_MADE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_items);

        Intent intent = getIntent();

        if(intent.hasExtra("myUsername")) {
            myUsername = intent.getStringExtra("myUsername");
        }

        ListView myItemsList = (ListView) findViewById(R.id.myItemsListView);

        myItemsList.setOnItemClickListener(onItemClickListener);

        myItems.clear();

        ElasticSearchAppController.GetMyItemsTask getMyItemsTask = new ElasticSearchAppController.GetMyItemsTask();
        getMyItemsTask.execute(myUsername);

        try {
            myItems.addAll(getMyItemsTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("NUMBER OF FOUND ITEMS FOR THIS USER");

        System.out.printf("%d\n", myItems.size());

        for (Item item: myItems) {
            System.out.println(item.getName());
        }

        adapter = new ArrayAdapter<Item>(this, R.layout.list_item, myItems);
        myItemsList.setAdapter(adapter);
    }

    /**
     * Method sets up functionality for when an item in a list is clicked.
     */
    private AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            editItem(myItems.get(position).getName());
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
