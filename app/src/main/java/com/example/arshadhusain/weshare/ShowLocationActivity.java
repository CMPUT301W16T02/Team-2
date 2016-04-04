package com.example.arshadhusain.weshare;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

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
    private String address = "test Sytring";
    private Double latit = 45.8584;
    private Double longit=2.9343;

    private final int[] MAP_TYPES = {
            GoogleMap.MAP_TYPE_SATELLITE,
            GoogleMap.MAP_TYPE_NORMAL,
            GoogleMap.MAP_TYPE_HYBRID,
            GoogleMap.MAP_TYPE_TERRAIN,
            GoogleMap.MAP_TYPE_NONE };

    private int curMapTypeIndex = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_location);
        mMap = ((ShowLocationFragment) getFragmentManager().findFragmentById(R.id.show_location_fragment)).getMap();

        LatLng coordinates = new  LatLng(latit, longit);

        mMap.addMarker(new MarkerOptions()
                        .position(coordinates)
                        .title(address)
                        .icon(BitmapDescriptorFactory.defaultMarker())
        );

        CameraPosition position = CameraPosition.builder()
                .target(coordinates)
                .zoom(16f)
                .bearing(0.0f)
                .tilt(0.0f)
                .build();

        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(position), null);
        mMap.setMapType(MAP_TYPES[curMapTypeIndex]);
        mMap.setTrafficEnabled(true);

        mMap.getUiSettings().setZoomControlsEnabled(true);


    }
}
