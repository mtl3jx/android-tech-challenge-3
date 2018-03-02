package com.captechventures.techchallenge3.map;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.captechventures.techchallenge3.R;
import com.captechventures.techchallenge3.model.ZipCodeEntry;
import com.captechventures.techchallenge3.details.ZipCodeDetailsActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.parceler.Parcels;

public class MapsActivity extends FragmentActivity implements
        OnMapReadyCallback, GoogleMap.OnMarkerClickListener
{

    // bundle variables
    private ZipCodeEntry zipCodeEntry;

    // layout variables
    private GoogleMap mMap;

    // tag for logging purposes
    private static final String TAG = MapsActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        // bundle variables
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        zipCodeEntry = Parcels.unwrap(bundle.getParcelable("zipCodeEntry"));

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Log.v(TAG, zipCodeEntry.toString());

    }

    @Override
    public void onMapReady(GoogleMap map) {
        this.mMap = map;

        // put pin at lat, lon
        Double lat = zipCodeEntry.getLatitude(), lon = zipCodeEntry.getLongitude();
        LatLng pinLocation;

        // empty lat/lon bring straight to detail page
        if (lat == null || lon == null) {
            // should never run these lines bc handled in SearchResultTableActivity but just in case
            Bundle bundle = new Bundle();
            bundle.putParcelable("zipCodeEntry", Parcels.wrap(zipCodeEntry));
            Intent intent = new Intent(MapsActivity.this, ZipCodeDetailsActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);

        } else {
            // verify lat / lon are not null
            pinLocation = new LatLng(lat, lon);

            // add pin to map
            Marker marker = mMap.addMarker(new MarkerOptions()
                    .position(pinLocation)
//                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.pin))); // uses custom icon
                    .title(zipCodeEntry.getLocationText()));
            marker.showInfoWindow();

            // make pin clickable
            mMap.setOnMarkerClickListener(this);

            // move camera to pin
            float zoomLevel = 12;
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pinLocation, zoomLevel));
            mMap.setMinZoomPreference(6.0f);
            mMap.setMaxZoomPreference(17.0f);

            // enable zoom
            mMap.getUiSettings().setZoomControlsEnabled(true);

//            TODO: enable compass / play around with other features
//            mMap.getUiSettings().setMyLocationButtonEnabled(true);
//            mMap.getUiSettings().setCompassEnabled(true);
//            mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
//            mMap.setTrafficEnabled(true);
//            mMap.setIndoorEnabled(true);
//            mMap.setBuildingsEnabled(true);
        }

    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        Log.v(TAG, "Marker " + marker.getTitle() + " was clicked!");

        Bundle bundle = new Bundle();
        bundle.putParcelable("zipCodeEntry", Parcels.wrap(zipCodeEntry));
        Intent intent = new Intent(MapsActivity.this, ZipCodeDetailsActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
        return true;
    }


    // TODO: resize original icon
    public Bitmap resizeIcon(int id, int shrinkScale) {
        BitmapDrawable originalIcon = (BitmapDrawable) ContextCompat.getDrawable(this, id);
        BitmapDrawable bd = (BitmapDrawable) originalIcon.getCurrent();
        Bitmap bitmap = bd.getBitmap();
        Bitmap newIcon = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth()/shrinkScale,bitmap.getHeight()/shrinkScale, false);
        return newIcon;
    }

}
