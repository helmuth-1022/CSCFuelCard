package com.fuel.csc.cscfuelcard1;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class UKActivity extends FragmentActivity {

    public static int  flag=0;
    private GoogleMap mMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uk2);
        flag=1;
        TextView site_name=(TextView)findViewById(R.id.sitename);
        site_name.setText(UK.SITENAME);
        setUpMapIfNeeded();
    }

    public void ToMain(View v){
        Intent i=new Intent(this, UK.class);
        //startActivity(i);
        setResult(1,i);
        finish();
    }

    public void Reload(View v){
        Intent i=new Intent(this, MapsActivity.class);
        startActivity(i);
    }
    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }


    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    private void setUpMap() {
        // if (count==3) {
        // Toast.makeText(this, "ddd" + String.valueOf(count), Toast.LENGTH_LONG).show();
        double lat = Double.parseDouble(MainActivity.latitude);
        double lon = Double.parseDouble(MainActivity.longitude);

        String position = UK.LOCATION;
        String siteInfo = UK.SITEINFO;
        String siteName = UK.SITENAME;
        int lastIndex = position.indexOf(' ');
        String lat_str = position.substring(0, lastIndex - 2);
        String long_str = position.substring(position.indexOf(",") + 2);
        double lat_card = Double.parseDouble(lat_str);
        double lon_card = Double.parseDouble(long_str);

        mMap.addMarker(new MarkerOptions().position(new LatLng(lat, lon)).title("My Location"));

        mMap.addMarker(new MarkerOptions().position(new LatLng(lat_card, lon_card)).title(siteName).snippet(siteInfo)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
        //mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        mMap.setMyLocationEnabled(true);
        mMap.moveCamera(
                CameraUpdateFactory.newLatLngZoom(new LatLng(lat_card, lon_card), 12));
        // Location myLocation = mMap.getMyLocation();
//        mMap.addMarker(new MarkerOptions().position(new LatLng(myLocation.getLatitude(),myLocation.getLongitude()))).setTitle("My Location");

        //Toast.makeText(this, String.valueOf(allObjects.size()), Toast.LENGTH_LONG).show();
        // }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_uk, menu);
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
}
