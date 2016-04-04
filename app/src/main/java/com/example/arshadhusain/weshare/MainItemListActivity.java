package com.example.arshadhusain.weshare;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import android.widget.ArrayAdapter;
import android.widget.Toast;

/**
 * MainItemListActivity allows user to see all items.
 *
 * @Author: Christopher, Arshad
 * @Version: 2.0
 *
 */
public class MainItemListActivity extends AppCompatActivity {

    private ArrayList<Item> allItems = new ArrayList<>();
    private ListView allItemsListView;
    public ArrayAdapter<Item> adapter;

    private String myUsername;
    static final int CHANGE_MADE = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_item_list);

        Intent intent = getIntent();

        if(intent.hasExtra("myUsername")) {
            myUsername = intent.getStringExtra("myUsername");
        }

        allItemsListView = (ListView) findViewById(R.id.allItemsListView);

        allItemsListView.setOnItemClickListener(onItemClickListener);

        Button keywordSearchButton = (Button)findViewById(R.id.keywordSearchButton);

        keywordSearchButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                searchItemDescriptions();
            }
        });

        Button addItemButton = (Button)findViewById(R.id.addItemButton);

        addItemButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                addItem();
            }
        });
    }

    /**
     * Create adapter for allItems
     */
    private AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String ownerName = allItems.get(position).getOwner();
            String itemName = allItems.get(position).getName();
            if (ownerName.equals(myUsername)){
                editItem(itemName);
            } else {
                viewItemInfo(itemName, ownerName);
            }
        }
    };

    /**
     * Allows user to add item. Passes intent to AddItemActivity
     *
     * @See: AddItemActivity
     */
    public void addItem() {
        Intent intent = new Intent(this, AddItemActivity.class);
        intent.putExtra("myUsername", myUsername);
        startActivityForResult(intent, CHANGE_MADE);
    }

    /**
     * Allows user to edit item. Passes intent to EditItemActivity
     *
     * @See: EditItemActivity
     */
    public void editItem(String itemName) {
        Intent intent = new Intent(this, EditItemActivity.class);
        intent.putExtra("itemName", itemName);
        intent.putExtra("myUsername", myUsername);
        startActivityForResult(intent, CHANGE_MADE);
    }

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

    public void searchItemDescriptions(){
        Intent intent = new Intent(this, KeywordSearchActivity.class);
        intent.putExtra("myUsername", myUsername);
        startActivity(intent);
    }

    public void searchItems(){
        Intent intent = new Intent(this, SearchResultsActivity.class);
        intent.putExtra("myUsername", myUsername);
        startActivity(intent);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CHANGE_MADE && resultCode == RESULT_OK) {

            allItems.clear();

            ElasticSearchAppController.GetMyItemsTask getMyItemsTask = new ElasticSearchAppController.GetMyItemsTask();

            getMyItemsTask.execute("");

            try {
                allItems.addAll(getMyItemsTask.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        allItems.clear();

        ElasticSearchAppController.GetMyItemsTask getMyItemsTask = new ElasticSearchAppController.GetMyItemsTask();
        getMyItemsTask.execute("");

        try {
            allItems.addAll(getMyItemsTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        adapter = new ArrayAdapter<Item>(this, R.layout.list_item, allItems);
        allItemsListView.setAdapter(adapter);
    }
}