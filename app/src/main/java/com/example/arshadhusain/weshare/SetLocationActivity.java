package com.example.arshadhusain.weshare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SetLocationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_location);

        Button SetLocation = (Button) findViewById(R.id.SetLocation);

        SetLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_OK);

                Intent intent = new Intent(SetLocationActivity.this, MapActivity.class); //YOU NEED HANSON'S EDIT AND VIEW PROFILE FUCNTIONALITY
                //intent.putExtra("Username", MyUsername);
                startActivity(intent);

            }
        });
    }
}
