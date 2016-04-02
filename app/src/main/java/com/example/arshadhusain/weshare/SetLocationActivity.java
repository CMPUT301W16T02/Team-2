package com.example.arshadhusain.weshare;

import android.location.LocationListener;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import com.google.*;

public class SetLocationActivity extends FragmentActivity implements LocationListener {

    GoogleMap googleMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_location);
    }
}
