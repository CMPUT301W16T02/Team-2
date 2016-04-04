package com.example.arshadhusain.weshare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.google.android.gms.maps.GoogleMap;

/**
 * This Activity is used to show the map where the owner
 * chooses the location to meet the borrower.
 * The owner selects the location on this map.
 *
 * @author Hanson
 * @version 1.0
 */
public class SetLocationActivity extends AppCompatActivity {
    private GoogleMap mMap;
    String itemName;
    String ownerName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_location);

        Intent intent = getIntent();

        if(intent.hasExtra("itemName")) {
            itemName = intent.getStringExtra("itemName");
        }
        if(intent.hasExtra("itemOwner")) {
            ownerName = intent.getStringExtra("itemOwner");
        }

        mMap = ((SetLocationFragment) getFragmentManager().findFragmentById(R.id.set_location_fragment)).getMap();
    }
}