package com.example.arshadhusain.weshare;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.maps.GoogleMap;

public class MapActivity extends AppCompatActivity {
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        mMap = ((MyMapFragment) getFragmentManager().findFragmentById(R.id.map_fragment)).getMap();
    }
}
