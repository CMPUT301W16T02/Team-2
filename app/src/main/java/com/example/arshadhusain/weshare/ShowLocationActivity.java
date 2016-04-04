package com.example.arshadhusain.weshare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * This Activity is used to show the map where the owner
 * chooses the location to meet the borrower
 *
 * @author Hanson
 * @version 1.0
 */
public class ShowLocationActivity extends AppCompatActivity {
    private GoogleMap mMap;
    private TextView addressText;

    private String address;
    private Double latit;
    private Double longit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_location);

        Intent intent = getIntent();

        if(intent.hasExtra("address")) {
            address = intent.getStringExtra("address");
        }
        if(intent.hasExtra("latitude")) {
            latit = intent.getDoubleExtra("latitude", 0);
        }
        if(intent.hasExtra("longitude")) {
            longit = intent.getDoubleExtra("longitude", 0);
        }

        addressText = (TextView) findViewById(R.id.show_address);

        addressText.setTextSize(20);
        addressText.setText("alkfjajfl");

        mMap = ((ShowLocationFragment) getFragmentManager().findFragmentById(R.id.show_location_fragment)).getMap();

        LatLng coordinates = new  LatLng(latit, longit);

        zoom(mMap, coordinates);

        mMap.addMarker(new MarkerOptions()
                        .position(coordinates)
                        .title(address)
                        .icon(BitmapDescriptorFactory.defaultMarker())

        );
    }

    /**
     * Method zooms into the given GoogleMap at the given cooridinates
     * @param googleMap
     * @param coordinates
     */
    // modified from http://code.tutsplus.com/tutorials/getting-started-with-google-maps-for-android-basics--cms-24635
    private void zoom(GoogleMap googleMap, LatLng coordinates) {

        final int[] MAP_TYPES = {
                GoogleMap.MAP_TYPE_SATELLITE,
                GoogleMap.MAP_TYPE_NORMAL,
                GoogleMap.MAP_TYPE_HYBRID,
                GoogleMap.MAP_TYPE_TERRAIN,
                GoogleMap.MAP_TYPE_NONE };

        int curMapTypeIndex = 1;

        CameraPosition position = CameraPosition.builder()
                .target(coordinates)
                .zoom(16f)
                .bearing(0.0f)
                .tilt(0.0f)
                .build();

        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(position), null);
        googleMap.setMapType(MAP_TYPES[curMapTypeIndex]);
        googleMap.setTrafficEnabled(true);

        googleMap.getUiSettings().setZoomControlsEnabled(true);

        return;
    }
}
