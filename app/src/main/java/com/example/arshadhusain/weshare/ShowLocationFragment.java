package com.example.arshadhusain.weshare;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;

/**
 * Map fragment that allows the owner to set a location
 * to meet the borrower.
 *
 * Appears in SetLocationActivity.
 *
 * @author Hanson
 * @version 1.0
 */
// From http://code.tutsplus.com/tutorials/getting-started-with-google-maps-for-android-basics--cms-24635
public class ShowLocationFragment extends MapFragment implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        GoogleMap.OnMarkerClickListener {

    private final int[] MAP_TYPES = {
            GoogleMap.MAP_TYPE_SATELLITE,
            GoogleMap.MAP_TYPE_NORMAL,
            GoogleMap.MAP_TYPE_HYBRID,
            GoogleMap.MAP_TYPE_TERRAIN,
            GoogleMap.MAP_TYPE_NONE };

    private int curMapTypeIndex = 1;

    private GoogleApiClient mGoogleApiClient;
    private Location mCurrentLocation;
    private String address;
    private Double latit;
    private Double longit;


    @Override
    public void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    public void onStop() {
        super.onStop();
        if( mGoogleApiClient != null && mGoogleApiClient.isConnected() ) {
            mGoogleApiClient.disconnect();
        }
    }


    /*
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        address = getArguments().getString("edttext");
        address = "test";
        latit = Double.valueOf(48.8584);
        longit = Double.valueOf(2.2945);
        return inflater.inflate(R.layout.activity_show_location, container, false);
    }
    */



    @Override
    public void onConnected(Bundle bundle) {
        //initCamera(latit, longit);
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
    }

    @Override
    public void onConnectionSuspended(int i) {
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setHasOptionsMenu(true);

        mGoogleApiClient = new GoogleApiClient.Builder( getActivity() )
                .addConnectionCallbacks( this )
                .addOnConnectionFailedListener( this )
                .addApi( LocationServices.API )
                .build();

        getMap().setOnMarkerClickListener(this);
    }


    /**
     * Method zooms and centers the map according to the coordinates
     * @param latitude
     * @param longitude
     */
    private void initCamera(Double latitude, Double longitude ) {

        // For the emulation, the default location is hardcoded, since
        // gps doesn't work for the emulator
        LatLng defaultLocation = new LatLng(latit,longit);

        CameraPosition position = CameraPosition.builder()
                .target(defaultLocation)
                .zoom(16f)
                .bearing(0.0f)
                .tilt(0.0f)
                .build();

        getMap().animateCamera(CameraUpdateFactory.newCameraPosition(position), null);
        getMap().setMapType(MAP_TYPES[curMapTypeIndex]);
        getMap().setTrafficEnabled(true);

        try{
            getMap().setMyLocationEnabled(true);
        } catch (SecurityException ex) {
        }

        getMap().getUiSettings().setZoomControlsEnabled(true);

        //getMap().addMarker();
        //Marker meetingPoint = new Marker();
    }

    /**
     * Method that runs when map marker is clicked.
     *
     * @param marker the clicked map marker.
     * @return returns boolean true on success.
     */
    @Override
    public boolean onMarkerClick(Marker marker) {
        marker.showInfoWindow();
        return true;
    }

    /**
     * method finds the address name of a location given the
     * locations latitude and longitude values
     * @param latLng LatLng object that contains the latitude and longitude
     *               of the location
     * @return the address of the location in String format
     */
    private String getAddressFromLatLng( LatLng latLng ) {
        Geocoder geocoder = new Geocoder( getActivity() );

        String address = "";
        try {
            address = geocoder
                    .getFromLocation( latLng.latitude, latLng.longitude, 1 )
                    .get( 0 ).getAddressLine( 0 );
        } catch (IOException e ) {
        }

        return address;
    }
}
