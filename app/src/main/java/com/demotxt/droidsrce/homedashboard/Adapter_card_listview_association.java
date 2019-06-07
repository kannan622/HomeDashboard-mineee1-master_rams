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


public class Adapter_card_listview_association extends BaseAdapter {

    Context c;
    List<association_model> players = null;
    // CustomFilter filter;
    ArrayList<association_model> filterList;
    boolean isSet;


    private ArrayList<association_model> arraylist;
    private int selectedPosition = 0;

    public Adapter_card_listview_association(Context ctx, List<association_model> players) {
        // TODO Auto-generated constructor stub

        this.c = ctx;
        this.players = players;

        this.arraylist = new ArrayList<association_model>();
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

        final TextView nameTxt = convertView.findViewById(R.id.area_name);



        //SET DATA TO THEM
        nameTxt.setText((players.get(pos).getassociation_name()));



        return convertView;
    }


}
