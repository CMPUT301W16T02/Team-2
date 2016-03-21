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

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * The first activity that starts. Displays two buttons.
 * One button goes to sign in and the other creates an account.
 *
 * Started by opening the app.
 * Starts either CreateProfileActivity or SignInActivity.
 *
 * @author Arshad
 * @version 1.0
 */
public class WelcomeActivity extends AppCompatActivity {
    private EditText username;
    private boolean accountExists;
    ArrayList<Account> Accounts = new ArrayList<Account>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.onCreateSetup();
        this.onCreateListeners();
    }


    public void onCreateSetup() {
        setContentView(R.layout.activity_welcome);
        username = (EditText) findViewById(R.id.SignInInput1);
    }

    protected void onCreateListeners() {

        Button SignInButton = (Button) findViewById(R.id.SignInButton);
        Button mainCreateButton = (Button) findViewById(R.id.main_create_account);

        SignInButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                setResult(RESULT_OK);
                String UserName = username.getText().toString();
                try {
                    FileInputStream inputFile = openFileInput(UserName);
                    BufferedReader input = new BufferedReader(new InputStreamReader(inputFile));

                    //String line = input.readLine();
                    Gson gson = new Gson();
                    //Type listType = new TypeToken<ArrayList<FuelLog>>() {}.getType();

                    Type listType = new TypeToken<ArrayList<Account>>() {}.getType();

                    Accounts = gson.fromJson(input, listType);
                    accountExists = true;

                    //Account account = new Account();

            /*while (line != null) {
                fuelList.add(line);
                line = input.readLine();
            }*/

                } catch (FileNotFoundException e) {
                    // TODO Auto-generated catch block
                    //e.printStackTrace();
                    Accounts = new ArrayList<Account>();
                    System.out.println("Username not found\n");
                    accountExists = false;
                    Toast.makeText(getApplicationContext(), "Username not found", Toast.LENGTH_LONG).show();

                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    //e.printStackTrace();
                    throw new RuntimeException();
                }


                //Intent intent = new Intent(SignInActivity.this, WelcomeActivity.class);

                //startActivityForResult(intent, 1);
                if (accountExists) {
                    Toast.makeText(getApplicationContext(), "Sign In Successful", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), NavigationMainActivity.class);
                    startActivity(intent);
                    intent.putExtra("Username", UserName);
                    startActivityForResult(intent, 1);

                    setResult(RESULT_OK);
                    finish();
                }
            }
        });

        mainCreateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomeActivity.this, CreateProfileActivity.class);
                startActivity(intent);
            }
        });

       /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
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
