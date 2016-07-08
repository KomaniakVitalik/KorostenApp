package com.korosten.www.util.map;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;


public class MapItem implements ClusterItem {
    private final LatLng mPosition;
    private final String mName;

    public MapItem(double lat, double lng, String name) {
        mPosition = new LatLng(lat, lng);
        mName = name;
    }

    @Override
    public LatLng getPosition() {
        return mPosition;
    }

    public String getName() {
        return mName;
    }

}
