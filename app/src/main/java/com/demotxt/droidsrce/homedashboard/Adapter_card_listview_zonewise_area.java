package com.demotxt.droidsrce.homedashboard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class Adapter_card_listview_zonewise_area extends BaseAdapter {

    Context c;
    List<zone_area_model> players = null;
    // CustomFilter filter;
    ArrayList<offers_model> filterList;
    boolean isSet;


    private ArrayList<zone_area_model> arraylist;
    private int selectedPosition = 0;

    public Adapter_card_listview_zonewise_area(Context ctx, List<zone_area_model> players) {
        // TODO Auto-generated constructor stub

        this.c = ctx;
        this.players = players;

        this.arraylist = new ArrayList<zone_area_model>();
        this.arraylist.addAll(players);


        try {
            if (players.isEmpty()) {
                Toast.makeText(c, "No data Found", Toast.LENGTH_LONG)
                        .show();
            }
        } catch (Exception e) {

        }

    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return players.size();
    }

    @Override
    public Object getItem(int pos) {
        // TODO Auto-generated method stub
        return players.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        // TODO Auto-generated method stub


        return players.indexOf(getItem(pos));
    }

    @Override
    public View getView(final int pos, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub


        LayoutInflater inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.zonewise_area, null);
        }

        final TextView area = convertView.findViewById(R.id.area_name);


        //SET DATA TO THEM
        area.setText((players.get(pos).getareaname()));


        return convertView;
    }


}
