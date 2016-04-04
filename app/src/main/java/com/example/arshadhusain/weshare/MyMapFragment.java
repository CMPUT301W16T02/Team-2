package com.example.arshadhusain.weshare;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.view.View;

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
 * Created by Hanson on 2016-04-03.
 */
public class MyMapFragment extends MapFragment implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        GoogleMap.OnInfoWindowClickListener,
        GoogleMap.OnMapLongClickListener,
        GoogleMap.OnMapClickListener,
        GoogleMap.OnMarkerClickListener {

    private GoogleApiClient mGoogleApiClient;
    private Location mCurrentLocation;
    private static final Double UALBERTA_LAT = Double.valueOf(53.5232);
    private static final Double UALBERTA_LONG = Double.valueOf(-113.5263);

    private final int[] MAP_TYPES = {
            GoogleMap.MAP_TYPE_SATELLITE,
            GoogleMap.MAP_TYPE_NORMAL,
            GoogleMap.MAP_TYPE_HYBRID,
            GoogleMap.MAP_TYPE_TERRAIN,
            GoogleMap.MAP_TYPE_NONE };

    private int curMapTypeIndex = 1;


    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    @Override
    public void onInfoWindowClick(Marker marker) {

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

        initListeners();
    }

    private void initListeners() {
        getMap().setOnMarkerClickListener(this);
        getMap().setOnMapLongClickListener(this);
        getMap().setOnInfoWindowClickListener(this);
        getMap().setOnMapClickListener(this);
    }
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

    @Override
    public void onConnected(Bundle bundle) {
            initCamera(mCurrentLocation);
    }


    private void initCamera( Location location ) {
        LatLng defaultLocation = new LatLng(UALBERTA_LAT,UALBERTA_LONG);
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
        } catch (SecurityException ex) {}
        getMap().getUiSettings().setZoomControlsEnabled(true);
    }

    @Override
    public void onMapClick(LatLng latLng) {
    }

    @Override
    public void onMapLongClick(LatLng latLng) {
        MarkerOptions options = new MarkerOptions().position( latLng );

        options.title(getAddressFromLatLng(latLng));
        options.draggable(false);
        options.icon( BitmapDescriptorFactory.defaultMarker() );

        getMap().addMarker( options ).showInfoWindow();
    }


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

    @Override
    public boolean onMarkerClick(Marker marker) {

        final String address = marker.getTitle();
        final double latitude = marker.getPosition().latitude;
        final double longitude = marker.getPosition().longitude;

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle(address);
        builder.setMessage("Use this location?");

        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent();
                intent.putExtra("address", address);
                intent.putExtra("latitude", latitude);
                intent.putExtra("longitude", longitude);
                getActivity().setResult(Activity.RESULT_OK, intent);
                getActivity().finish();
                // Code that is executed when clicking YES

                dialog.dismiss();
            }

        });


        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });


        AlertDialog alert = builder.create();
        alert.show();
        marker.remove();
        return true;
    }




}
