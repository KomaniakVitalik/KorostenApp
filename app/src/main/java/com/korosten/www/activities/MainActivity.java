package com.korosten.www.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.korosten.www.CoreApp;
import com.korosten.www.R;
import com.korosten.www.util.map.BalloonAdapter;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback
        // , GoogleMap.InfoWindowAdapter
        , GoogleMap.OnMapClickListener {

    static final LatLng TutorialsPoint = new LatLng(21, 57);
    private GoogleMap googleMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

///TEST
        getAllDataFromServer();
        ///TEST
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        googleMap.setInfoWindowAdapter(new BalloonAdapter(getLayoutInflater()));
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        googleMap.setTrafficEnabled(true);
        googleMap.setIndoorEnabled(true);
        googleMap.setBuildingsEnabled(true);
        googleMap.getUiSettings().setZoomControlsEnabled(true);
        googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(37.7750, 121.4183))
                .title("San Francisco")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_2))
                .snippet("Population: 776733"));
    }

    @Override
    public void onMapClick(LatLng latLng) {
        // Clears any existing markers from the GoogleMap
        googleMap.clear();

        // Creating an instance of MarkerOptions to set position
        MarkerOptions markerOptions = new MarkerOptions();

        // Setting position on the MarkerOptions
        markerOptions.position(latLng);

        // Animating to the currently touched position
        googleMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));

        // Adding marker on the GoogleMap
        Marker marker = googleMap.addMarker(markerOptions);

        // Showing InfoWindow on the GoogleMap
        marker.showInfoWindow();
    }

    ////TEST
    private void getAllDataFromServer() {
        //CoreApp.getInstance().getDataManager().getAllData();
    }
    ////TEST
}
