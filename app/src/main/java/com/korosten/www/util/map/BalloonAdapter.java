package com.korosten.www.util.map;

/**
 * Created by vitaliy.komaniak on 7/8/16.
 */
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
import com.google.android.gms.maps.model.Marker;
import com.korosten.www.R;

public class BalloonAdapter implements InfoWindowAdapter {
    LayoutInflater inflater = null;
    private TextView textViewTitle;

    public BalloonAdapter(LayoutInflater inflater) {
        this.inflater = inflater;
    }

    @Override
    public View getInfoWindow(Marker marker) {
        View v = inflater.inflate(R.layout.layout_custom_marker_dialog, null);
        if (marker != null) {
            textViewTitle = (TextView) v.findViewById(R.id.tv_marker_title);
            textViewTitle.setText(marker.getTitle());
        }
        return (v);
    }

    @Override
    public View getInfoContents(Marker marker) {
        return (null);
    }
}