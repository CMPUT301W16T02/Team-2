package com.example.arshadhusain.weshare;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Activity that shows the profile of another user.
 *
 * Started by ItemInfoActivity.
 * Returns to ItemInfoActivity when back button is clicked.
 *
 * @author Hanson
 * @version 1.0
 */
public class ViewProfileActivity extends AppCompatActivity {
    ArrayList<Account> Accounts = new ArrayList<Account>();
    Account MyAccount;
    String MyUsername;
    Float average;
    TextView username;
    TextView email;
    TextView city;
    TextView rating;

    private static Button Submit_button;
    private static TextView starText_View;
    private static RatingBar star_bar;

    public static int TEXTSIZE = 18;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);
        listenerForRating();
        onButtonClickListener();

        Intent intent = getIntent();

        if(intent.hasExtra("Username")) {
            MyUsername = intent.getStringExtra("Username");
        }

        /*try {
            FileInputStream inputFile = openFileInput(MyUsername);
            BufferedReader input = new BufferedReader(new InputStreamReader(inputFile));

            Gson gson = new Gson();

            Type listType = new TypeToken<ArrayList<Account>>() {
            }.getType();

            Accounts = gson.fromJson(input, listType);
        }

        catch(FileNotFoundException ex) {
            ex.printStackTrace();
        }*/

        ElasticSearchAppController.GetAccountTask getAccountTask = new ElasticSearchAppController.GetAccountTask();
        getAccountTask.execute(MyUsername);
        try {
            Accounts.addAll(getAccountTask.get());

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        for(Account account : Accounts) {
            if(account.getUsername().equals(MyUsername)){
                MyAccount = account;
            }
        }

        username = (TextView) findViewById(R.id.username);
        String dispUsername = "Username: " + MyAccount.getUsername();
        username.setTextSize(TEXTSIZE);
        username.setText(dispUsername);

        email = (TextView) findViewById(R.id.email);
        String dispEmail = "Email: " + MyAccount.getEmail();
        email.setTextSize(TEXTSIZE);
        email.setText(dispEmail);

        city = (TextView) findViewById(R.id.city);
        String dispAddress = "Address: " + MyAccount.getCity();
        city.setTextSize(TEXTSIZE);
        city.setText(dispAddress);

        rating = (TextView) findViewById(R.id.ratingSys);
        String dispRate = "User Rating: " + MyAccount.showAverage();
        rating.setTextSize(TEXTSIZE);
        rating.setText(dispRate);

        //average = MyAccount



    }

    public void listenerForRating() {
        star_bar = (RatingBar) findViewById(R.id.ratingBar);
        starText_View = (TextView) findViewById(R.id.startextView);

        star_bar.setOnRatingBarChangeListener(
                new RatingBar.OnRatingBarChangeListener() {
                    @Override
                    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                        starText_View.setText(String.valueOf(rating));
                    }
                }
        );

    }

    public void onButtonClickListener() {
        star_bar = (RatingBar) findViewById(R.id.ratingBar);
        Submit_button = (Button) findViewById(R.id.submitRateButton);

        Submit_button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        MyAccount.addRate(star_bar.getRating());

                    }
                }
        );
    }


}
