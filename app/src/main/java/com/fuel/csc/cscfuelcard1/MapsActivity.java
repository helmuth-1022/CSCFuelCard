package com.fuel.csc.cscfuelcard1;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.location.Location;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    public static final List<ParseObject>allObjects = new ArrayList<ParseObject>();
    public static int flag=0;
    public static int count=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        if (flag==0) {
           // Toast.makeText(this,"Getting Your Location",Toast.LENGTH_LONG).show();

            final ProgressDialog progressDialog = new ProgressDialog(MapsActivity.this, android.R.style.Theme_DeviceDefault_Light_Dialog);
            progressDialog.setIndeterminate(true);
            progressDialog.setMessage("Getting Data...");
            progressDialog.show();
            // ParseObject testObject = new ParseObject("TestObject");
            // testObject.put("foo", "bar");
            // testObject.saveInBackground();
            Thread t = new Thread(){
                public void run(){
                    try{
                        Thread.sleep(1000*15);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                progressDialog.dismiss();
                                //Toast.makeText(MapsActivity.this,"No Signal",Toast.LENGTH_LONG).show();
                            }
                        });
                    }catch (InterruptedException e){

                    }
                }
            }; t.start();
            final ParseQuery<ParseObject> query = ParseQuery.getQuery("site");
            query.setLimit(1000);
            query.findInBackground(new FindCallback<ParseObject>() {
                @Override
                public void done(List<ParseObject> objects, ParseException e) {
                    if (e == null) {
                        ArrayList<String> courses = new ArrayList<String>();
                        double current_lat = Double.parseDouble(MainActivity.latitude);
                        double current_lon = Double.parseDouble(MainActivity.longitude);
                        Location locationA = new Location("pointA");
                        Location locationB=new Location("pointB");
                        for (ParseObject object : objects)
                        {
                            String position = object.getString("Geolocation");
                            int lastIndex=position.indexOf(' ');
                            String lat_str=position.substring(0,lastIndex-2);
                            String long_str=position.substring(position.indexOf(",")+2);
                            double lat=Double.parseDouble(lat_str);
                            double lon=Double.parseDouble(long_str);

                            locationA.setLatitude(lat);
                            locationA.setLongitude(lon);

                            locationB.setLatitude(current_lat);
                            locationB.setLongitude(current_lon);
                            double distance = locationA.distanceTo(locationB) ;
                            //if (distance<20000)
                                allObjects.add(object);
                            //Toast.makeText(MapsActivity.this, "distance"+String.valueOf(distance) ,Toast.LENGTH_LONG).show();
                            //courses.add(courseName);
                        }
                        count++;
                        //Toast.makeText(MapsActivity.this, "ccc"+String.valueOf(count) ,Toast.LENGTH_LONG).show();
                        if (count==3) {
                            updateMap();
                            progressDialog.dismiss();
                        }



                        //allObjects.addAll(objects);
                        //Toast.makeText(MapsActivity.this, "aaa" + String.valueOf(allObjects.size()), Toast.LENGTH_LONG).show();
                    } else {
                        //progressDialog.dismiss();
                        //Toast.makeText(MapsActivity.this,"No Signal",Toast.LENGTH_LONG).show();
                    }
                }
            });

            final ParseQuery<ParseObject> query1 = ParseQuery.getQuery("site");
            query1.setLimit(1000);
            query1.setSkip(1000);
            query1.findInBackground(new FindCallback<ParseObject>() {
                @Override
                public void done(List<ParseObject> objects, ParseException e) {
                    if (e == null) {
                        ArrayList<String> courses = new ArrayList<String>();
                        double current_lat = Double.parseDouble(MainActivity.latitude);
                        double current_lon = Double.parseDouble(MainActivity.longitude);
                        Location locationA = new Location("pointA");
                        Location locationB=new Location("pointB");
                        for (ParseObject object : objects)
                        {
                            String position = object.getString("Geolocation");
                            int lastIndex=position.indexOf(' ');
                            String lat_str=position.substring(0,lastIndex-2);
                            String long_str=position.substring(position.indexOf(",")+2);
                            double lat=Double.parseDouble(lat_str);
                            double lon=Double.parseDouble(long_str);

                            locationA.setLatitude(lat);
                            locationA.setLongitude(lon);

                            locationB.setLatitude(current_lat);
                            locationB.setLongitude(current_lon);
                            double distance = locationA.distanceTo(locationB) ;
                           // if (distance<20000)
                                allObjects.add(object);
                            //Toast.makeText(MapsActivity.this, "distance"+String.valueOf(distance) ,Toast.LENGTH_LONG).show();
                            //courses.add(courseName);
                        }
                        count++;
                        //Toast.makeText(MapsActivity.this, "aaa"+String.valueOf(count) ,Toast.LENGTH_LONG).show();
                        if (count==3) {
                            progressDialog.dismiss();
                            updateMap();
                        }


                    } else {
                       // progressDialog.dismiss();
                       // Toast.makeText(MapsActivity.this,"No Signal",Toast.LENGTH_LONG).show();
                    }
                }
            });

            final ParseQuery<ParseObject> query2 = ParseQuery.getQuery("site");
            query2.setLimit(1000);
            query2.setSkip(2000);
            query2.findInBackground(new FindCallback<ParseObject>() {
                @Override
                public void done(List<ParseObject> objects, ParseException e) {
                    if (e == null) {
                        ArrayList<String> courses = new ArrayList<String>();
                        double current_lat = Double.parseDouble(MainActivity.latitude);
                        double current_lon = Double.parseDouble(MainActivity.longitude);
                        Location locationA = new Location("pointA");
                        Location locationB=new Location("pointB");
                        for (ParseObject object : objects) {
                            String position = object.getString("Geolocation");
                            int lastIndex = position.indexOf(' ');
                            String lat_str = position.substring(0, lastIndex - 2);
                            String long_str = position.substring(position.indexOf(",") + 2);
                            double lat = Double.parseDouble(lat_str);
                            double lon = Double.parseDouble(long_str);

                            locationA.setLatitude(lat);
                            locationA.setLongitude(lon);

                            locationB.setLatitude(current_lat);
                            locationB.setLongitude(current_lon);
                            double distance = locationA.distanceTo(locationB);
                            //if (distance < 20000)
                                allObjects.add(object);
                            //Toast.makeText(MapsActivity.this, "distance"+String.valueOf(distance) ,Toast.LENGTH_LONG).show();
                            //courses.add(courseName);
                        }
                        count++;
                        //Toast.makeText(MapsActivity.this, "bbb"+String.valueOf(count) ,Toast.LENGTH_LONG).show();
                        if (count==3) {
                            progressDialog.dismiss();
                            updateMap();
                        }


                    } else {
                       // progressDialog.dismiss();
                       // Toast.makeText(MapsActivity.this,"No Signal",Toast.LENGTH_LONG).show();
                    }
                }
            });


           // progressDialog.dismiss();
           // Toast.makeText(MapsActivity.this, "sss" + String.valueOf(allObjects.size()), Toast.LENGTH_LONG).show();
            flag=1;
        }


        if (count==3) {
            setUpMapIfNeeded();
            updateMap();
        }
    }

    public void ToMain(View v){
        Intent i=new Intent(this, MainActivity.class);
        //startActivity(i);
        setResult(1,i);
        finish();
    }

    public void Reload(View v){
        flag=0;
        Intent i=new Intent(this, MapsActivity.class);
        startActivity(i);

    }
    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p/>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p/>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
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

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {
       // if (count==3) {
           // Toast.makeText(this, "ddd" + String.valueOf(count), Toast.LENGTH_LONG).show();
            double lat = Double.parseDouble(MainActivity.latitude);
            double lon = Double.parseDouble(MainActivity.longitude);

            //mMap.addMarker(new MarkerOptions().position(new LatLng(lat, lon)).title("My Location").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));;

            //mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
            mMap.setMyLocationEnabled(true);
            mMap.moveCamera(
                    CameraUpdateFactory.newLatLngZoom(new LatLng(lat, lon), 12));
            // Location myLocation = mMap.getMyLocation();
//        mMap.addMarker(new MarkerOptions().position(new LatLng(myLocation.getLatitude(),myLocation.getLongitude()))).setTitle("My Location");

            //Toast.makeText(this, String.valueOf(allObjects.size()), Toast.LENGTH_LONG).show();
       // }
    }

    public void updateMap()
    {
        double lat = Double.parseDouble(MainActivity.latitude);
        double lon = Double.parseDouble(MainActivity.longitude);

//        mMap.addMarker(new MarkerOptions().position(new LatLng(lat, lon)).title("My Location"));

        //mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        mMap.setMyLocationEnabled(true);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat, lon), 12));
       //Toast.makeText(this, "ddd"+String.valueOf(allObjects.size()), Toast.LENGTH_LONG).show();
        for (ParseObject object:allObjects){
            String position = object.getString("Geolocation");
            int lastIndex = position.indexOf(' ');
            String lat_str = position.substring(0, lastIndex - 2);
            String long_str = position.substring(position.indexOf(",") + 2);
            double lat_card = Double.parseDouble(lat_str);
            double lon_card = Double.parseDouble(long_str);

            String sitename=object.getString("SiteName");
            String street1=object.getString("Street1");
            String town=object.getString("Town");
            String brand=object.getString("Brand");
            mMap.addMarker(new MarkerOptions().position(new LatLng(lat_card, lon_card)).title(sitename).snippet(street1 + ", " + town + "\n" + "\n" + "Brand:" + brand)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
        }

        mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {

            @Override
            public View getInfoWindow(Marker arg0) {
                return null;
            }

            @Override
            public View getInfoContents(Marker marker) {

                View v = getLayoutInflater().inflate(R.layout.marker, null);

                TextView title= (TextView) v.findViewById(R.id.title);
                TextView snippet= (TextView) v.findViewById(R.id.snippet);

                title.setTextColor(Color.BLACK);
                title.setGravity(Gravity.CENTER);
                title.setTypeface(null, Typeface.BOLD);
                title.setText(marker.getTitle());

                snippet.setTextColor(Color.GRAY);
                snippet.setText(marker.getSnippet());

                return v;
            }
        });
    }
}
