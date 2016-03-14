package com.example.arshadhusain.weshare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class SearchResultsActivity extends AppCompatActivity {

    private String keyword;
    private ListView searchResultsList;
    private ArrayList<SearchResult> searchResults = new ArrayList<SearchResult>();
    public ArrayAdapter<SearchResult> adapter;
    private String activeUser;
    private static int selectedItemPos;
    private static int origItemPos;
    static final int CHANGE_MADE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);

        Intent intent = getIntent();

        if(intent.hasExtra("activeUser")) {
            activeUser = intent.getStringExtra("activeUser");
        }

        searchResultsList = (ListView) findViewById(R.id.searchResultsList);

        searchResultsList.setOnItemClickListener(onItemClickListener);
    }

    public ArrayList<SearchResult> getSearchResults(){
        ArrayList<SearchResult> searchResults = new ArrayList<SearchResult>();

        /*
        switch (keyword) {
            case "":
                for (int i = 0; i < NavigationMainActivity.allItems.size(); i++) {
                    if (NavigationMainActivity.allItems.get(i).getStatus() != 2) {
                        searchResults.add(NavigationMainActivity.allItems.get(i));
                    }
                }
            default:
                for(int i=0; i<NavigationMainActivity.allItems.size(); i++){
                    if(NavigationMainActivity.allItems.get(i).getStatus()!=2 &&
                            NavigationMainActivity.allItems.get(i).getDescription().toLowerCase().contains(keyword)){
                        searchResults.add(NavigationMainActivity.allItems.get(i));
                    }
                }
        }
        */

        for(int i=0; i<NavigationMainActivity.allItems.size(); i++){
            int itemStatus = NavigationMainActivity.allItems.get(i).getStatus();
            String itemOwner = NavigationMainActivity.allItems.get(i).getOwner();
            if(itemStatus!=2 && !itemOwner.equals(activeUser)){
                SearchResult newResult = new SearchResult(NavigationMainActivity.allItems.get(i), i);
                searchResults.add(newResult);
            }
        }
        return searchResults;
    }

    private AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectedItemPos = position;
            origItemPos = searchResults.get(selectedItemPos).getOrigItemPos();
            viewItemInfo();
            /*
            if (NavigationMainActivity.allItems.get(origItemPos).getOwner().equals(activeUser)){
                editItem();
            } else {
                viewItemInfo();
            }*/
        }
    };

    public void editItem() {
        Intent intent = new Intent(this, EditItemActivity.class);
        intent.putExtra("itemPos", origItemPos);

        startActivityForResult(intent, CHANGE_MADE);
    }

    public void viewItemInfo(){
        Intent intent = new Intent(this, ItemInfoActivity.class);
        intent.putExtra("itemPos", origItemPos);
        intent.putExtra("activeUser", activeUser);

        startActivityForResult(intent, CHANGE_MADE);
    }

    @Override
    protected void onStart() {
        super.onStart();

        /*
        Intent intent = getIntent();

        if(intent.hasExtra("keyword")) {
            keyword = intent.getStringExtra("keyword").toLowerCase();
        } else {
            keyword = "";
        }
        */

        searchResults = getSearchResults();

        adapter = new ArrayAdapter<SearchResult>(this,
                R.layout.list_item, searchResults);
        searchResultsList.setAdapter(adapter);
    }
}
