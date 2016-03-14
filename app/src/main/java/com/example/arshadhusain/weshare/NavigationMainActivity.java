package com.example.arshadhusain.weshare;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

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
 * This is the main screen once the user has signed in. This activity
 * is never finished as long as the app is open. Navigation to most other
 * activities takes place here.
 * Created by arshadhusain on 16-03-12.
 */
public class NavigationMainActivity extends AppCompatActivity {

    private String MyUsername;
    private static final String FILENAME = "items.sav";
    private static final String FILENAME1 = "bids.sav";


    public static ArrayList<Item> allItems = new ArrayList<Item>();
    public static ArrayList<Bid> allBids = new ArrayList<Bid>();
    public static ArrayList<Bid> bidNotify = new ArrayList<Bid>();


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

    /**
     * Method sets up the screen.
     * Also performs the functionality where the owner gets a notification
     * about a new bid on one of their items.
     */
    public void onCreateSetup() {
        setContentView(R.layout.navigation_main_activity);
        bidNotify.clear();
        for (int x=0; x<NavigationMainActivity.allBids.size(); x++) {
            //System.out.println(NavigationMainActivity.allBids.get(x).getItem());
            //System.out.printf("%s\n", NavigationMainActivity.allBids.get(x).getItem());
            System.out.printf("%s\n", NavigationMainActivity.allBids.get(x).getBidder());
            System.out.printf("%s\n", MyUsername);


            if((NavigationMainActivity.allBids.get(x).getItemOwner()).equals(MyUsername))
            {

                Bid bidToCopy = NavigationMainActivity.allBids.get(x);
                System.out.printf("%s\n", bidToCopy.getItem());
                bidNotify.add(bidToCopy);
            }

        }
        if(!bidNotify.isEmpty()) {
            Toast.makeText(getApplicationContext(), "You've got new bid requests!", Toast.LENGTH_LONG).show();

        }
    }

    /**
     * Method connects to all the buttons and sets up their usage.
     */
    public void onCreateListeners() {
        Button EditProfile = (Button)findViewById(R.id.EditProfile);
        Button MyItems = (Button)findViewById(R.id.MyItems);
        Button MyBorrows = (Button)findViewById(R.id.MyBorrows);
        Button ItemMarketplace = (Button)findViewById(R.id.ShowAllItems);
        Button MyBids = (Button)findViewById(R.id.MyBids);
        Button MyItemsWithBids = (Button)findViewById(R.id.MyItemsWithBids);



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

                Intent intent = new Intent(NavigationMainActivity.this, MyItemsActivity.class); //YOU NEED CHRIS' LIST ITEM FUNCTIONALITY
                intent.putExtra("Username", MyUsername);

                startActivity(intent);

            }
        });

        MyBids.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_OK);

                Intent intent = new Intent(NavigationMainActivity.this, MyBiddingActivity.class); //YOU NEED CHRIS' LIST ITEM FUNCTIONALITY
                intent.putExtra("activeUser", MyUsername);

                startActivity(intent);

            }
        });

        MyItemsWithBids.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_OK);

                Intent intent = new Intent(NavigationMainActivity.this, MyItemListWithBids.class); //YOU NEED CHRIS' LIST ITEM FUNCTIONALITY
                intent.putExtra("activeUser", MyUsername);

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

    /**
     * @return context returns the context
     */
    public static Context getContext() {
        return context;
    }

    /**
     * Method loads the list of all items from the local save file.
     * @param context the context of the calling activity.
     */
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

    /**
     * Method loads the list of all bids from the local save file.
     * @param context the context of the calling activity.
     */
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

    /**
     * saves the list of all items to the local save file.
     * @param context the context of the calling activity.
     */
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

    /**
     * Method adds an item to the list of all items and saves to the local
     * save file.
     *
     * @deprecated
     * @param newItem the item to be added
     */
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
