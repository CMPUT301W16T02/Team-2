package com.example.arshadhusain.weshare;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.maps.GoogleMap;

/**
 * This Activity is used to show the map where the owner
 * chooses the location to meet the borrower
 *
 * @author Hanson
 * @version 1.0
 */
public class SetLocationActivity extends AppCompatActivity {
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_location);
        mMap = ((SetLocationFragment) getFragmentManager().findFragmentById(R.id.set_location_fragment)).getMap();
    }
}
