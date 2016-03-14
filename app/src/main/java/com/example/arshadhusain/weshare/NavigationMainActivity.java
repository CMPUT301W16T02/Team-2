package com.example.arshadhusain.weshare;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

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

/**
 * Created by arshadhusain on 16-03-12.
 */
public class NavigationMainActivity extends AppCompatActivity {

    private String MyUsername;
    private static final String FILENAME = "items.sav";
    private static final String FILENAME1 = "bids.sav";


    public static ArrayList<Item> allItems = new ArrayList<Item>();
    public static ArrayList<Bid> allBids = new ArrayList<Bid>();

    public static Context context;
    public static Context context1;




    @Override
    protected void onStart() {
        super.onStart();
        //loadItemsFromFile();
        //loadBidsFromFile();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getApplicationContext();
        context1 = getApplicationContext();

        loadItemsFromFile(context);
        loadBidsFromFile(context1);



        Intent intent = getIntent();

        if(intent.hasExtra("Username")) {
            MyUsername = intent.getStringExtra("Username");
        }

        this.onCreateSetup();
        this.onCreateListeners();

    }

    public void onCreateSetup() {
        setContentView(R.layout.navigation_main_activity);


    }

    public void onCreateListeners() {
        Button EditProfile = (Button)findViewById(R.id.EditProfile);
        Button MyItems = (Button)findViewById(R.id.MyItems);
        Button MyBorrows = (Button)findViewById(R.id.MyBorrows);
        Button ItemMarketplace = (Button)findViewById(R.id.ShowAllItems);
        Button MyBids = (Button)findViewById(R.id.MyBids);


        EditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_OK);

                Intent intent = new Intent(NavigationMainActivity.this, EditProfileActivity.class); //YOU NEED HANSON'S EDIT AND VIEW PROFILE FUCNTIONALITY
                intent.putExtra("Username", MyUsername);
                startActivity(intent);

            }
        });

        MyItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_OK);
                /*
                Intent intent = new Intent(NavigationMainActivity.this, MainItemListActivity.class); //YOU NEED CHRIS' LIST ITEM FUNCTIONALITY
                startActivity(intent);
                */
            }
        });

        MyBids.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_OK);

                Intent intent = new Intent(NavigationMainActivity.this, MyBiddingActivity.class); //YOU NEED CHRIS' LIST ITEM FUNCTIONALITY
                intent.putExtra("Username", MyUsername);

                startActivity(intent);

            }
        });



        MyBorrows.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_OK);
                /*
                Intent intent = new Intent(NavigationMainActivity.this, MainItemListActivity.class); //YOU NEED CHRIS' BORROWING FUNCTIONALITY
                startActivity(intent);
                */
            }
        });

        ItemMarketplace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_OK);

                Intent intent = new Intent(NavigationMainActivity.this, MainItemListActivity.class); //YOU NEED CHRIS' BORROWING FUNCTIONALITY
                intent.putExtra("Username", MyUsername);
                startActivityForResult(intent, 1);

            }
        });
    }

    public static Context getContext() {
        return context;
    }

    private void loadItemsFromFile(Context context) {
        try {
            FileInputStream fis = context.openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();

            // Took from https://google-gson.googlecode.com/svn/trunk/gson/docs/javadocs/com/google/gson/Gson.html 01-19 2016
            Type listType = new TypeToken<ArrayList<Item>>() {}.getType();
            allItems = gson.fromJson(in, listType);
            System.out.printf("LOADING ITEMS FROM FILE\n");

            System.out.printf("%d\n", NavigationMainActivity.allItems.size());

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            allItems = new ArrayList<Item>();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }
    }

    private void loadBidsFromFile(Context context) {
        try {
            FileInputStream fis = context.openFileInput(FILENAME1);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();

            // Took from https://google-gson.googlecode.com/svn/trunk/gson/docs/javadocs/com/google/gson/Gson.html 01-19 2016
            Type listType = new TypeToken<ArrayList<Bid>>() {}.getType();
            allBids = gson.fromJson(in, listType);

            System.out.printf("LOADING BIDS FROM FILE\n");

            System.out.printf("%d\n", NavigationMainActivity.allBids.size());

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            allBids = new ArrayList<Bid>();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }
    }

    public static void saveInFile(Context context) {
        try {
            FileOutputStream fos = context.openFileOutput(FILENAME,
                    0);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));
            Gson gson = new Gson();
            gson.toJson(allItems, out);
            out.flush();
            System.out.println("saved all items into file");
            fos.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }

    }
    /*public void addAndSaveToBid(Item newItem) {
        NavigationMainActivity.allItems.add(newItem); //WRONG NEEDS TO BE BID
        saveBidsToFile();

    }*/

    public static void addAndSaveToItems(Item newItem) {
        NavigationMainActivity.allItems.add(newItem);
        saveInFile(context);

    }
    public static void saveBidsToFile(Context context) {
        try {
            FileOutputStream fos = context.openFileOutput(FILENAME1,
                    0);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));
            Gson gson = new Gson();
            gson.toJson(allBids, out);
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




    /*@Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            saveInFile();
        }
    }*/
}
