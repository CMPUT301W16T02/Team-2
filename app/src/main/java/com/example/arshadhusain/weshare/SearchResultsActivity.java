package com.example.arshadhusain.weshare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.regex.Pattern;

/**
 * <p>Allows user to search for specific item. Passes intent
 * user. </p>
 *
 * @Author: Chris
 * @Version: 1.0
 */
public class SearchResultsActivity extends AppCompatActivity {

    private ListView searchResultsList;
    private ArrayList<Item> searchResults = new ArrayList<>();
    public ArrayAdapter<Item> adapter;
    private String myUsername;
    private String itemName;
    private String itemOwner;
    static final int CHANGE_MADE = 1;
    String[] individualWords;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);

        Intent intent = getIntent();

        if(intent.hasExtra("myUsername")) {
            myUsername = intent.getStringExtra("myUsername");
        }
        if(intent.hasExtra("stringOfWords")) {
            individualWords = intent.getStringArrayExtra("stringOfWords");
        }

        searchResultsList = (ListView) findViewById(R.id.searchResultsList);

        searchResultsList.setOnItemClickListener(onItemClickListener);
    }

    public ArrayList<Item> getSearchResults(){
        searchResults.clear();

        ElasticSearchAppController.GetMyItemsTask getMyItemsTask = new ElasticSearchAppController.GetMyItemsTask();
        getMyItemsTask.execute("");
        ArrayList<Item> allItems = new ArrayList<>();
        try {
            allItems.addAll(getMyItemsTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        for (Item item : allItems) {
            if (item.getStatus() != 2 && !item.getOwner().equals(myUsername)) {
                searchResults.add(item);
            }
        }
        return searchResults;
    }

        public ArrayList<Item> getKeywordSearchResults() {
        searchResults.clear();

        ElasticSearchAppController.GetMyItemsTask getMyItemsTask = new ElasticSearchAppController.GetMyItemsTask();
        getMyItemsTask.execute("");
        ArrayList<Item> allItems = new ArrayList<>();
        try {
            allItems.addAll(getMyItemsTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        for (Item item : allItems) {
            System.out.printf("in the for loop!\n");
            System.out.printf(item.getStatus().toString() + "\n" + item.getOwner() + "\n");
            boolean containsWord = true;
            if (item.getStatus() != 2 && !item.getOwner().equals(myUsername)) {
                System.out.printf("in the if loop! status != 2 and username matches\n");
                int keywordIterator = 0;
                while(containsWord == true) {
                    System.out.printf("in the while loop!\n");
                    containsWord = item.getDescription().toString().toLowerCase().contains(individualWords[keywordIterator].toLowerCase());
                    if (keywordIterator == individualWords.length - 1) {
                        System.out.printf("in the second if loop!\n");
                        break;
                    }
                    keywordIterator++;
                }
                if (containsWord == true) {
                    searchResults.add(item);
                }
            }
        }

        return searchResults;

    }

        /**
         * Sets adapter of list item
         */
    private AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            itemName = searchResults.get(position).getName();
            itemOwner = searchResults.get(position).getOwner();
            viewItemInfo(itemName, itemOwner);
        }
    };

    /**
     * User sees item from search
     */
    public void viewItemInfo(String itemName, String ownerName){
        Intent intent = new Intent(this, ItemInfoActivity.class);
        intent.putExtra("itemName", itemName);
        intent.putExtra("ownerName", ownerName);
        intent.putExtra("myUsername", myUsername);
        startActivityForResult(intent, CHANGE_MADE);
    }
    @Override
    protected void onStart() {
        super.onStart();

        if(individualWords != null)
        {
            searchResults = getKeywordSearchResults();
            System.out.println("****THERE ARE SEARCH KEYWORDS****");

        } else if(individualWords == null) {
            searchResults = getSearchResults();
            System.out.println("****NO SEARCH KEYWORDS****");
        }

        adapter = new ArrayAdapter<Item>(this, R.layout.list_item, searchResults);
        searchResultsList.setAdapter(adapter);
    }
}
