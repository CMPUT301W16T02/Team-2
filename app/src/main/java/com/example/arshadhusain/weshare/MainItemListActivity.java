package com.example.arshadhusain.weshare;

import android.content.Context;
import android.support.v4.util.SparseArrayCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import android.widget.ArrayAdapter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class MainItemListActivity extends AppCompatActivity {

    private static final String FILENAME = "items.sav";
    private ListView allItemsList;

    public static ArrayList<Item> allItems = new ArrayList<Item>();
    public ArrayAdapter<Item> adapter;

    static final int CHANGE_MADE = 1;

    private String activeUser;
    private static int selectedItemPos;
    public Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_item_list);

        Intent intent = getIntent();

        if(intent.hasExtra("Username")) {
            activeUser = intent.getStringExtra("Username");
        }

        allItemsList = (ListView) findViewById(R.id.allItemsList);

        allItemsList.setOnItemClickListener(onItemClickListener);

        Button searchButton = (Button)findViewById(R.id.searchButton);

        searchButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                //
            }
        });

        Button addItemButton = (Button)findViewById(R.id.addItemButton);

        addItemButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                addItem();
            }
        });
    }

    private AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectedItemPos = position;
            if (NavigationMainActivity.allItems.get(selectedItemPos).getOwner().equals(activeUser)){
                editItem();
            } else {
                viewItemInfo();
            }

        }
    };


    public void addItem() {
        Intent intent = new Intent(this, AddItemActivity.class);
        intent.putExtra("activeUser", activeUser);
        startActivityForResult(intent, CHANGE_MADE);
    }

    public void editItem() {
        Intent intent = new Intent(this, EditItemActivity.class);
        intent.putExtra("itemPos", selectedItemPos);
        startActivityForResult(intent, CHANGE_MADE);
    }

    public void viewItemInfo(){
        Intent intent = new Intent(this, ItemInfoActivity.class);
        intent.putExtra("itemPos", selectedItemPos);
        startActivityForResult(intent, CHANGE_MADE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            adapter.notifyDataSetChanged();
            setResult(RESULT_OK);
            //NavigationMainActivity.saveInFile();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        //context = NavigationMainActivity.getContext();
        //loadFromFile(context);
        //allItems = NavigationMainActivity.allItems;
        adapter = new ArrayAdapter<Item>(this,
                R.layout.list_item, NavigationMainActivity.allItems);
        allItemsList.setAdapter(adapter);
    }

    private void loadFromFile(Context context) {
        try {
            FileInputStream fis = context.openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();

            // Took from https://google-gson.googlecode.com/svn/trunk/gson/docs/javadocs/com/google/gson/Gson.html 01-19 2016
            Type listType = new TypeToken<ArrayList<Item>>() {}.getType();
            allItems = gson.fromJson(in, listType);

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            allItems = new ArrayList<Item>();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }
    }

    // taken from lonelyTwitter
    private void saveInFile() {
        try {
            FileOutputStream fos = openFileOutput(FILENAME,
                    0);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));
            Gson gson = new Gson();
            gson.toJson(allItems, out);
            out.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }
    }

}
