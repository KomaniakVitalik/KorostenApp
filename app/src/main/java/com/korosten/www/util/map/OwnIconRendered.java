package com.korosten.www.util.map;

import android.content.Context;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.clustering.ClusterManager;
import com.google.maps.android.clustering.view.DefaultClusterRenderer;

public class OwnIconRendered extends DefaultClusterRenderer<MapItem> {

    public OwnIconRendered(Context context, GoogleMap map,
                           ClusterManager<MapItem> clusterManager) {
        super(context, map, clusterManager);
    }

    @Override
    protected void onBeforeClusterItemRendered(MapItem item, MarkerOptions markerOptions) {
        markerOptions.title(item.getName());
        super.onBeforeClusterItemRendered(item, markerOptions);
    }
}
