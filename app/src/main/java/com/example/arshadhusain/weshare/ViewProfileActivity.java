package com.example.arshadhusain.weshare;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ViewProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);

        TextView username = (TextView) findViewById(R.id.username);
        String dispUsername = "Username: " + "hardcoded guy";
        username.setTextSize(23);
        username.setText(dispUsername);

        TextView email = (TextView) findViewById(R.id.email);
        String dispEmail = "Email: " + "hardcoded email";
        email.setTextSize(23);
        email.setText(dispEmail);

        TextView address = (TextView) findViewById(R.id.address);
        String displAddress = "Address: " + "hardcoded address";
        address.setTextSize(23);
        address.setText(displAddress);
    }
}
