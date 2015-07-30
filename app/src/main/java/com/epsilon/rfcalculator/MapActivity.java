package com.epsilon.rfcalculator;

import android.content.Context;
import android.graphics.Color;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


public class MapActivity extends FragmentActivity implements LocationListener{

    GoogleMap googleMap;

    Marker marker;
    Circle shape;

    private String provider;
    private Location mCurrentLocation;
    private LocationManager locationManager;
    private double radius;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        Bundle b = getIntent().getExtras();
        if (b != null) {
            if (b.containsKey("radius")) {
                radius = b.getDouble("radius");
            }
        }
        SupportMapFragment mfrag = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        googleMap = mfrag.getMap();

        googleMap.setMyLocationEnabled(true);
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);

        Criteria criteria = new Criteria();
        provider = locationManager.getBestProvider(criteria, false);
        locationManager.requestLocationUpdates(provider, 5000, 5, this);

        mCurrentLocation = locationManager.getLastKnownLocation(provider);
        if (mCurrentLocation != null) {
              onLocationChanged(mCurrentLocation);
        }

    }

    /*@Override
    protected void onResume(){
        super.onResume();
        Criteria criteria = new Criteria();
        provider = locationManager.getBestProvider(criteria, false);
        locationManager.requestLocationUpdates(provider, 5000, 5, this);
    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_map, menu);
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
    public void onLocationChanged(Location location) {
        setMarker("Locality", "Country", location.getLatitude(), location.getLongitude());
        locationManager.removeUpdates(this);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
    private void setMarker(String locality, String country, double lat,double lng){

        LatLng LL = new LatLng(lat,lng);
        MarkerOptions options = new MarkerOptions()
                .title(locality)
                .position(LL)
                //.icon(BitmapDescriptor.fromResource(R.drawable.ic_mapmarker))
                .icon(BitmapDescriptorFactory.defaultMarker());

        marker = googleMap.addMarker(options);

        shape = drawCircle(LL);


    }

    private Circle drawCircle(LatLng LL) {

        CircleOptions options = new CircleOptions()
                .center(LL)
                .radius(radius)
                .fillColor(0x330000FF)
                .strokeColor(Color.BLUE)
                .strokeWidth(3);
        return googleMap.addCircle(options);
    }

    private void removeShape(){
        marker.remove();
        marker = null;
        shape.remove();
        shape = null;
    }
}
