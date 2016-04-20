package com.fuel.csc.cscfuelcard1;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class Ireland extends ActionBarActivity {

    public static final List<ParseObject> allObjects = new ArrayList<ParseObject>();
    public static int flag=0;
    public static String SITENAME="";
    public static String SITEINFO="";
    public static String LOCATION="";


    ListView list;
    ListViewAdapter adapter1;
    List<SiteDetail> concertLists;
    //List<MyTask> tasks;
    EditText editsearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ireland);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        //tasks = new ArrayList<>();
        if (flag==0) {
           // Toast.makeText(this, "Getting Data", Toast.LENGTH_LONG).show();
           // Toast.makeText(this, "Just a moment", Toast.LENGTH_LONG).show();

            // ParseObject testObject = new ParseObject("TestObject");
            // testObject.put("foo", "bar");
            // testObject.saveInBackground();
            final ProgressDialog progressDialog = new ProgressDialog(Ireland.this, android.R.style.Theme_DeviceDefault_Light_Dialog);
            progressDialog.setIndeterminate(true);
            progressDialog.setMessage("Getting Data...");
            progressDialog.show();

            Thread t = new Thread(){
                public void run(){
                    try{
                        Thread.sleep(1000*15);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                progressDialog.dismiss();
                              //  Toast.makeText(Ireland.this,"No Signal",Toast.LENGTH_LONG).show();
                            }
                        });
                    }catch (InterruptedException e){

                    }
                }
            }; t.start();

            final ParseQuery<ParseObject> query = ParseQuery.getQuery("site");
            query.whereEqualTo("Country","IRELAND");
            query.setLimit(1000);final List<SiteDetail> sites;
            query.findInBackground(new FindCallback<ParseObject>() {
                @Override
                public void done(List<ParseObject> objects, ParseException e) {
                    if (e == null) {
                       allObjects.addAll(objects);

                        final List<SiteDetail> sites;
                        sites=new ArrayList<SiteDetail>();
                        for (ParseObject object:allObjects){
                            String sitename = object.getString("SiteName");
                            String brand=object.getString("Brand");
                            String street1=object.getString("Street1");
                            String town=object.getString("Town");
                            String county=object.getString("County");
                            String location=object.getString("Geolocation");
                            SiteDetail site=new SiteDetail();
                            if (brand=="null")
                                site.siteName=sitename;
                            else
                                site.siteName=sitename+", "+brand;
                            site.siteInfo=street1+", "+town+", "+county;
                            site.siteLocation=location;
                            sites.add(site);

                        }

                        ListView grid;
                        grid = (ListView)findViewById(R.id.sites_grid);
                        ArrayAdapter<SiteDetail> adapter=new ArrayAdapter<SiteDetail>(Ireland.this,R.layout.activity_ireland ,sites){
                            public View getView(int position,View convertView, ViewGroup parent){
                                if(convertView==null){
                                    convertView=getLayoutInflater().inflate(R.layout.activity_sites_grid,null);
                                }


                                TextView appLabel=(TextView)convertView.findViewById(R.id.item_app_label);
                                appLabel.setText(sites.get(position).siteName);

                                TextView appName=(TextView)convertView.findViewById(R.id.item_app_name);
                                appName.setText(sites.get(position).siteInfo);

                                return convertView;
                            }
                        };
                        grid.setAdapter(adapter);
                        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                SITENAME=sites.get(position).siteName.toString();

                                SITEINFO=sites.get(position).siteInfo.toString();
                                LOCATION=sites.get(position).siteLocation.toString();
                                Intent i=new Intent(Ireland.this, IrelandActivity.class);
                                startActivityForResult(i, 1);

                            }
                        });
                        adapter1 = new ListViewAdapter(Ireland.this, sites);
                        // Capture Text in EditText
                        editsearch = (EditText) findViewById(R.id.search);
                        grid.setAdapter(adapter1);
                        editsearch.addTextChangedListener(new TextWatcher() {

                            @Override
                            public void afterTextChanged(Editable arg0) {
                                // TODO Auto-generated method stub
                                String text = editsearch.getText().toString().toLowerCase(Locale.getDefault());
                                //Toast.makeText(Ireland.this, text, Toast.LENGTH_LONG).show();
                                adapter1.filter(text);
                            }

                            @Override
                            public void beforeTextChanged(CharSequence arg0, int arg1,
                                                          int arg2, int arg3) {
                                // TODO Auto-generated method stub
                            }

                            @Override
                            public void onTextChanged(CharSequence arg0, int arg1, int arg2,
                                                      int arg3) {
                                // TODO Auto-generated method stub
                            }
                        });
                        progressDialog.dismiss();
                       // Toast.makeText(Ireland.this, String.valueOf(allObjects.size()),Toast.LENGTH_LONG).show();
                    } else {

                    }
                }
            });


            // Toast.makeText(MapsActivity.this, "sss" + String.valueOf(allObjects.size()), Toast.LENGTH_LONG).show();
            flag=1;
        }
        else{
            final List<SiteDetail> sites;
            sites=new ArrayList<SiteDetail>();
            for (ParseObject object:allObjects){
                String sitename = object.getString("SiteName");
                String brand=object.getString("Brand");
                String street1=object.getString("Street1");
                String town=object.getString("Town");
                String county=object.getString("County");
                String location=object.getString("Geolocation");
                SiteDetail site=new SiteDetail();
                site.siteName=sitename+", "+brand;
                site.siteInfo=street1+", "+town+", "+county;
                site.siteLocation=location;
                sites.add(site);

            }
            ListView grid;
            grid = (ListView)findViewById(R.id.sites_grid);
            ArrayAdapter<SiteDetail> adapter=new ArrayAdapter<SiteDetail>(Ireland.this,R.layout.activity_ireland ,sites){
                public View getView(int position,View convertView, ViewGroup parent){
                    if(convertView==null){
                        convertView=getLayoutInflater().inflate(R.layout.activity_sites_grid,null);
                    }


                    TextView appLabel=(TextView)convertView.findViewById(R.id.item_app_label);
                    appLabel.setText(sites.get(position).siteName);

                    TextView appName=(TextView)convertView.findViewById(R.id.item_app_name);
                    appName.setText(sites.get(position).siteInfo);

                    return convertView;
                }
            };
            grid.setAdapter(adapter);
            grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    SITENAME=sites.get(position).siteName.toString();
                    SITEINFO=sites.get(position).siteInfo.toString();
                    LOCATION=sites.get(position).siteLocation.toString();
                    //Toast.makeText(Ireland.this, LOCATION, Toast.LENGTH_LONG).show();
                    Intent i=new Intent(Ireland.this, IrelandActivity.class);
                    startActivityForResult(i, 1);

                }
            });

            adapter1 = new ListViewAdapter(Ireland.this, sites);
            // Capture Text in EditText
            editsearch = (EditText) findViewById(R.id.search);
            grid.setAdapter(adapter1);
            editsearch.addTextChangedListener(new TextWatcher() {

                @Override
                public void afterTextChanged(Editable arg0) {
                    // TODO Auto-generated method stub
                    String text = editsearch.getText().toString().toLowerCase(Locale.getDefault());
                   // Toast.makeText(Ireland.this, text, Toast.LENGTH_LONG).show();
                    adapter1.filter(text);
                }

                @Override
                public void beforeTextChanged(CharSequence arg0, int arg1,
                                              int arg2, int arg3) {
                    // TODO Auto-generated method stub
                }

                @Override
                public void onTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3) {
                    // TODO Auto-generated method stub
                }
            });
        }






    }

    public void ToMain(View v){
        Intent i=new Intent(this, MainActivity.class);
        //startActivity(i);
        setResult(1,i);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ireland, menu);
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
