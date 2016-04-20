package com.fuel.csc.cscfuelcard1;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Dodobal-2 on 1/30/2016.
 */
public class ListViewAdapter extends BaseAdapter {
    Context mContext;
    LayoutInflater inflater;
    private List<SiteDetail> ManPositionlist = null;
    private ArrayList<SiteDetail> arraylist;

    public ListViewAdapter(Context context,
                           List<SiteDetail> ManPositionlist) {
        mContext = context;
        this.ManPositionlist = ManPositionlist;
        inflater = LayoutInflater.from(mContext);
        this.arraylist = new ArrayList<SiteDetail>();
        this.arraylist.addAll(ManPositionlist);
    }

    public class ViewHolder {
        TextView Title;
        TextView Info;
    }

    @Override
    public int getCount() {
        return ManPositionlist.size();
    }

    @Override
    public SiteDetail getItem(int position) {
        return ManPositionlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.activity_sites_grid, null);
            // Locate the TextViews in listview_item.xml
            holder.Title = (TextView) view.findViewById(R.id.item_app_label);
            holder.Info=(TextView)view.findViewById(R.id.item_app_name);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        // Set the results into TextViews
        holder.Title.setText(ManPositionlist.get(position).siteName);
        holder.Info.setText(ManPositionlist.get(position).siteInfo);

        view.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // Send single item click data to SingleItemView Class
                if (MainActivity.country_flag==1) {
                    Intent intent = new Intent(mContext, IrelandActivity.class);
                    // Pass all data rank
                    Ireland.SITENAME = ManPositionlist.get(position).siteName.toString();

                    Ireland.SITEINFO = ManPositionlist.get(position).siteInfo.toString();
                    Ireland.LOCATION = ManPositionlist.get(position).siteLocation.toString();
                    mContext.startActivity(intent);
                }
                if (MainActivity.country_flag==2) {
                    Intent intent = new Intent(mContext, NIreandActivity.class);
                    // Pass all data rank
                    NIreland.SITENAME = ManPositionlist.get(position).siteName.toString();

                    NIreland.SITEINFO = ManPositionlist.get(position).siteInfo.toString();
                    NIreland.LOCATION = ManPositionlist.get(position).siteLocation.toString();
                    mContext.startActivity(intent);
                }

                if (MainActivity.country_flag==3) {
                    Intent intent = new Intent(mContext, UKActivity.class);
                    // Pass all data rank
                    UK.SITENAME = ManPositionlist.get(position).siteName.toString();

                    UK.SITEINFO = ManPositionlist.get(position).siteInfo.toString();
                    UK.LOCATION = ManPositionlist.get(position).siteLocation.toString();
                    mContext.startActivity(intent);
                }
            }
        });

        return view;
    }

    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        ManPositionlist.clear();
        if (charText.length() == 0) {
            ManPositionlist.addAll(arraylist);
        } else {
            for (SiteDetail wp : arraylist) {
                if (wp.siteName.toString().toLowerCase(Locale.getDefault())
                        .contains(charText) || wp.siteInfo.toString().toLowerCase(Locale.getDefault())
                        .contains(charText)) {
                    ManPositionlist.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }
}
