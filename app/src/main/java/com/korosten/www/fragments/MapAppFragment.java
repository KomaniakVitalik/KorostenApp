package com.korosten.www.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.clustering.ClusterManager;
import com.google.maps.android.clustering.algo.NonHierarchicalDistanceBasedAlgorithm;
import com.korosten.www.R;
import com.korosten.www.model.Place;
import com.korosten.www.util.map.BalloonAdapter;
import com.korosten.www.util.map.MapItem;
import com.korosten.www.util.map.OwnIconRendered;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link MapAppFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MapAppFragment extends BaseFragment implements OnMapReadyCallback
        // , GoogleMap.InfoWindowAdapter
        , GoogleMap.OnMapClickListener {

    private View mView;
    private GoogleMap googleMap;
    private ClusterManager<MapItem> mClusterManager;
    private MapFragment mapFragment;


    public MapAppFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MapAppFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MapAppFragment newInstance(String param1, String param2) {
        MapAppFragment fragment = new MapAppFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        mView = inflater.inflate(R.layout.fragment_map_app, null);
        setupView();
        return mView;
    }
    

    private void initView() {
        mapFragment = (MapFragment) getActivity().getFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    private void setupView() {
        initView();
    }


    private void updateMarkersLocation() {
        googleMap.clear();
        mClusterManager.clearItems();
        int size = 0;
        LatLng lastPos = new LatLng(0, 0);
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        for (int i = 0; i < 1000; i++) {
            try {

                    double lat = (double) (i + 121) * 0.5221;
                    double lon = (double) (i + 565) * 0.432;
                    builder.include(new LatLng(lat, lon));
                    MapItem mapItem = new MapItem(lat, lon, "NAME" + i);
                    lastPos = new LatLng(lat, lon);

                    mClusterManager.addItem(mapItem);
                    size++;

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        mClusterManager.cluster();

        if (size == 1) {
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(lastPos, 10));
        } else if (size > 1) {
            try {
                // offset from edges of the map in pixels
                int padding = 100;
                LatLngBounds bounds = builder.build();
                CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, padding);
                googleMap.animateCamera(cu);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;

        mClusterManager = new ClusterManager<>(mView.getContext(), googleMap);
        mClusterManager.setAlgorithm(new NonHierarchicalDistanceBasedAlgorithm<MapItem>());
        googleMap.setOnCameraChangeListener(mClusterManager);
        mClusterManager.setRenderer(new OwnIconRendered(mView.getContext().getApplicationContext(), googleMap, mClusterManager));


        googleMap.setInfoWindowAdapter(new BalloonAdapter(getActivity().getLayoutInflater()));
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        googleMap.setTrafficEnabled(true);
        googleMap.setIndoorEnabled(true);
        googleMap.setBuildingsEnabled(true);
        googleMap.getUiSettings().setZoomControlsEnabled(true);
        for (int i = 0; i < 20; i++) {
            googleMap.addMarker(new MarkerOptions()
                    .position(new LatLng(i * 0.748 + (i * 11), i * 0.5431 + (i * 3)))
                    .title("San Francisco " + i)
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_2))
                    .snippet("Population: 776733"));
        }

        updateMarkersLocation();
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
}
