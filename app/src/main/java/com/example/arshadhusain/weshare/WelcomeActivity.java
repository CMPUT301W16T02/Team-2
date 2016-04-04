package com.example.arshadhusain.weshare;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * The first activity that starts. Displays username entry field, a sign in button, and a
 * sign up button.
 * Pressing "SIGN IN" signs the user in if their username matches an existing account.
 * Pressing "SIGN UP" starts CreateProfileActivity.
 *
 * Started by launching the app.
 *
 * @author Arshad, Hasan, Christopher
 * @version 2.0
 */
public class WelcomeActivity extends AppCompatActivity {
    private EditText username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.onCreateSetup();
        this.onCreateListeners();
    }

    public void onCreateSetup() {
        setContentView(R.layout.activity_welcome);
        username = (EditText) findViewById(R.id.usernameField);
    }

    /**
     * Sets up the click listeners for each button.
     */
    protected void onCreateListeners() {

        Button signInButton = (Button) findViewById(R.id.signInButton);
        Button signUpButton = (Button) findViewById(R.id.signUpButton);

        signInButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String myUsername = username.getText().toString();
                ArrayList<Account> matchingAccounts = new ArrayList<>();

                // Retrieves a list of accounts with usernames that match the input
                ElasticSearchAppController.GetAccountTask getAccountTask = new ElasticSearchAppController.GetAccountTask();
                getAccountTask.execute(myUsername);

                try {
                    matchingAccounts.addAll(getAccountTask.get());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

                // Checks for existing account with username that matches the input.
                // Completes sign in if match found, notifies user otherwise
                if(matchingAccounts.isEmpty()) {
                    System.out.println("no user found");
                    Toast.makeText(getApplicationContext(), "Username not found", Toast.LENGTH_LONG).show();
                } else if (!(matchingAccounts.isEmpty())) {
                    String signInMsg = "Signed in as: " + myUsername;
                    Toast.makeText(getApplicationContext(), signInMsg, Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), NavigationMainActivity.class);
                    intent.putExtra("myUsername", myUsername);
                    startActivity(intent);
                    finish();
                }
            }
        });

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomeActivity.this, CreateProfileActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * Auto-generated method.
     * Inflate the menu; this adds items to the action bar if it is present.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /**
     * Auto-generated method.
     * Handle action bar item clicks here. The action bar will
     * automatically handle clicks on the Home/Up button, so long
     * as you specify a parent activity in AndroidManifest.xml.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
