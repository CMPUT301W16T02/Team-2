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
 * Map fragment that allows the owner to set a location
 * to meet the borrower.
 *
 * Appears in MapActivity.
 *
 * @author Hanson
 * @version 1.0
 */
// From http://code.tutsplus.com/tutorials/getting-started-with-google-maps-for-android-basics--cms-24635
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

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
    }

    @Override
    public void onConnectionSuspended(int i) {
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

    /**
     * Method sets the interaction of the map fragment.
     */
    private void initListeners() {
        getMap().setOnMarkerClickListener(this);
        getMap().setOnMapLongClickListener(this);
        getMap().setOnInfoWindowClickListener(this);
        getMap().setOnMapClickListener(this);
    }

    /**
     * Method zooms and centers the map to the given location parameter.
     * @param location the starting location that the map zooms to.
     */
    private void initCamera( Location location ) {

        // For the emulation, the default location is hardcoded, since
        // gps doesn't work for the emulator
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
        } catch (SecurityException ex) {
        }

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

    /**
     * Method that runs when map marker is clicked. Asks the
     * user whether to use the map marker location or not.
     * If yes, the MapActivity(and map fragment) finishes and
     * returns.
     *
     * @param marker the clicked map marker.
     * @return returns boolean true on success.
     */
    @Override
    public boolean onMarkerClick(Marker marker) {

        final String address = marker.getTitle();
        final double latitude = marker.getPosition().latitude;
        final double longitude = marker.getPosition().longitude;

        // code from http://www.askingbox.com/tip/android-programming-yes-no-dialog-box
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
