package com.example.arshadhusain.weshare;

import android.content.Context;
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

/**
 * MainItemListActivity allows user to see all items.
 *
 * @Author: Christopher, Arshad
 * @Version: 2.0
 *
 */
public class MainItemListActivity extends AppCompatActivity {

    private ListView allItemsListView;
    public ArrayAdapter<Item> adapter;

    private String myUsername;
    static final int CHANGE_MADE = 1;

    private String clickedItemName;
    public Context context;


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

        Button searchButton = (Button)findViewById(R.id.searchButton);

        searchButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                searchItems();
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
            if (NavigationMainActivity.allItems.get(position).getOwner().equals(myUsername)){
                editItem(clickedItemName);
            } else {
                viewItemInfo(clickedItemName);
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
    public void viewItemInfo(String itemName){
        Intent intent = new Intent(this, ItemInfoActivity.class);
        intent.putExtra("itemName", itemName);
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
        if (requestCode == 1) {
            adapter.notifyDataSetChanged();
            setResult(RESULT_OK);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        NavigationMainActivity.allItems.clear();
        ElasticSearchAppController.GetMyItemsTask getMyItemsTask = new ElasticSearchAppController.GetMyItemsTask();
        getMyItemsTask.execute("");

        try {
            NavigationMainActivity.allItems.addAll(getMyItemsTask.get());

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        for(int i = 0; i < NavigationMainActivity.allItems.size(); i++)
        {
            if(NavigationMainActivity.allItems.get(i).getOwner().equals(myUsername))
            {
                NavigationMainActivity.allItems.remove(i);
            }
        }
        int j = 0;
        for(int i = 0; i < NavigationMainActivity.allItems.size(); i++)
        {
            System.out.println(NavigationMainActivity.allItems.get(i).toString());
        }

        adapter = new ArrayAdapter<Item>(this,
                R.layout.list_item, NavigationMainActivity.allItems);
        allItemsListView.setAdapter(adapter);
    }
}