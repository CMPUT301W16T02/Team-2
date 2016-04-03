package com.example.arshadhusain.weshare;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * This is the main screen once the user has signed in. This activity
 * is never finished as long as the app is open. Navigation to most other
 * activities takes place here.
 * @author Arshad, Christopher
 * @version 2.0
 */
public class NavigationMainActivity extends AppCompatActivity {
    private String myUsername;

    public static ArrayList<Item> allItems = new ArrayList<>();
    public static ArrayList<Bid> allBids = new ArrayList<>();

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
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();

        if (intent.hasExtra("myUsername")) {
            myUsername = intent.getStringExtra("myUsername");
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
        String usernameTitleString;
        TextView usernameTitle;

        setContentView(R.layout.navigation_main_activity);

        usernameTitleString = "Signed in as: " + myUsername;
        usernameTitle = (TextView) findViewById(R.id.usernameTextView);
        usernameTitle.setText(usernameTitleString);
    }

    /**
     * Method sets up button click listeners.
     */
    public void onCreateListeners() {
        Button editProfileButton = (Button) findViewById(R.id.editProfileButton);
        Button myItemsButton = (Button) findViewById(R.id.myItemsButton);
        Button marketplaceButton = (Button) findViewById(R.id.marketplaceButton);
        Button myBidsButton = (Button) findViewById(R.id.myBidsButton);
        Button myBiddedItemsButton = (Button) findViewById(R.id.myBiddedItemsButton);
        Button myBorrowingsButton = (Button) findViewById(R.id.myBorrowingsButton);
        Button myBorrowedItemsButton = (Button) findViewById(R.id.myBorrowedItemsButton);


        editProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NavigationMainActivity.this, EditProfileActivity.class);
                intent.putExtra("myUsername", myUsername);
                startActivity(intent);
            }
        });

        myItemsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NavigationMainActivity.this, MyItemsActivity.class);
                intent.putExtra("myUsername", myUsername);
                startActivity(intent);
            }
        });

        myBidsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NavigationMainActivity.this, MyBiddingActivity.class);
                intent.putExtra("myUsername", myUsername);
                startActivity(intent);
            }
        });

        myBiddedItemsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NavigationMainActivity.this, MyItemListWithBids.class);
                intent.putExtra("myUsername", myUsername);
                startActivity(intent);
            }
        });

        myBorrowingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NavigationMainActivity.this, MyBorrowingsActivity.class);
                intent.putExtra("myUsername", myUsername);
                startActivity(intent);
            }
        });

        myBorrowedItemsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NavigationMainActivity.this, MyBorrowedItemsActivity.class);
                intent.putExtra("myUsername", myUsername);
                startActivity(intent);
            }
        });

        marketplaceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NavigationMainActivity.this, MainItemListActivity.class);
                intent.putExtra("myUsername", myUsername);
                startActivity(intent);
            }
        });
    }
}
