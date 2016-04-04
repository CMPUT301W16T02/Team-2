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
    private Double latit = 48.8584;
    private Double longit=2.2945;

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

        zoom(mMap, coordinates);
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
