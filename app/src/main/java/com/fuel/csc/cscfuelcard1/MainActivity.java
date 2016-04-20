package com.fuel.csc.cscfuelcard1;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.parse.Parse;

import java.io.IOException;
import java.util.List;
import java.util.Locale;


public class MainActivity extends ActionBarActivity {
    public static  String longitude="";
    LocationManager locationManager;
    MyCurrentLoctionListener locationListener;

    public static  String latitude="";
    public static int flag=0;
    String address;
    public static int country_flag=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (flag==0) {
            latitude = "54.35";
            longitude = "-6.4";
            Parse.enableLocalDatastore(this);
            Parse.initialize(this);
            flag=1;
        }


        startGPS();
    }

    public void SitesNearBy(View v){
        Intent i=new Intent(this, MapsActivity.class);
        startActivityForResult(i,1);

    }

    public void IrelandSiteList(View v){
        country_flag=1;
        Intent i=new Intent(this, Ireland.class);
        startActivityForResult(i,1);

    }

    public void NIrelandSiteList(View v){
        country_flag=2;
        Intent i=new Intent(this, NIreland.class);
        startActivityForResult(i,1);
    }
    public void ContactUs(View v){
        Intent i=new Intent(this, ContactActivity.class);
        startActivityForResult(i,1);
        country_flag=3;
    }

    public void UKSiteList(View v){
        country_flag=3;
        Intent i=new Intent(this, UK.class);
        startActivityForResult(i,1);
    }
    //start GPS Provider
    public void startGPS()
    {
        try
        {
            locationManager=    (LocationManager)getSystemService(Context.LOCATION_SERVICE);
            locationListener = new MyCurrentLoctionListener();
            //locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, (LocationListener) locationListener);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, (LocationListener) locationListener);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    //end GPS Provider
    public void endGPS()
    {

        try
        {
            locationManager.removeUpdates(locationListener);
            locationManager=null;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    //get address with latitude and longitude.
    public String GetAddress(String lat, String lon)
    {
        Geocoder geocoder = new Geocoder(this, Locale.ENGLISH);
        String ret = "";
        try {
            List<Address> addresses = geocoder.getFromLocation(Double.parseDouble(lat), Double.parseDouble(lon), 1);

            if(addresses != null) {
                String subThorougfare=addresses.get(0).getSubThoroughfare();
                String Thorougfare=addresses.get(0).getThoroughfare();
                String zip = addresses.get(0).getPostalCode();
                String city = addresses.get(0).getLocality();
                String state = addresses.get(0).getAdminArea();
                String country = addresses.get(0).getCountryName();

                ret=subThorougfare+" "+Thorougfare+", "+zip+" "+city+" "+state+" "+country;
            }
            else{
                ret = "No Address returned!";
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            ret = "Can't get Address!";
        }
        return ret;
    }

    public class MyCurrentLoctionListener implements LocationListener {

        @Override
        public void onLocationChanged(Location location) {

            latitude = Double.toString(location.getLatitude());
            longitude = Double.toString(location.getLongitude());

            //I make a log to see the results
            Log.e("MY CURRENT LOCATION", latitude + "," + longitude);

            //get address with latitude and longitude.
          //  address = GetAddress(latitude, longitude);
          //  Log.e("MY CURRENT ADDRESS", address);


            //change this value to user id
            //If the user whose user_id is '1' built this app ,api_key is '1'
            //The same as before, if the user whose user_id is '2' built this app ,api_key is '2'
            // String api_key = "1";
            //post http request to server
            // new httpPost().execute(new String[]{api_key, ip, android_id, longitude, latitude, address});

            //end GPS Provider
            endGPS();
        }
        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {

        }

        @Override
        public void onProviderEnabled(String s) {

        }

        @Override
        public void onProviderDisabled(String s) {

        }
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
}
