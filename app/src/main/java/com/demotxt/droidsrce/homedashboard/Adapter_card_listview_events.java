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


public class Adapter_card_listview_events extends BaseAdapter {

    Context c;
    List<events_model> players = null;
    // CustomFilter filter;
    ArrayList<events_model> filterList;
    boolean isSet;


    private ArrayList<events_model> arraylist;
    private int selectedPosition = 0;

    public Adapter_card_listview_events(Context ctx, List<events_model> players) {
        // TODO Auto-generated constructor stub

        this.c = ctx;
        this.players = players;

        this.arraylist = new ArrayList<events_model>();
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
            convertView = inflater.inflate(R.layout.events, null);
        }

        final TextView title = convertView.findViewById(R.id.title_);
        TextView date = convertView.findViewById(R.id.date);
        TextView venue = convertView.findViewById(R.id.location);


        TextView chairman_name_value = convertView.findViewById(R.id.chairman_name_value);
        TextView chairman_mobileno_value = convertView.findViewById(R.id.chairman_mobileno_value);
        TextView cochairman_mobileno_value = convertView.findViewById(R.id.cochairman_mobileno_value);


        //SET DATA TO THEM
        title.setText((players.get(pos).gettitle()));
        date.setText((players.get(pos).getdate()));
        venue.setText((players.get(pos).getvenue()));
        chairman_name_value.setText((players.get(pos).getmeeting_chairman()));
        chairman_mobileno_value.setText((players.get(pos).getmobile_no_meeting_chairman()));
        cochairman_mobileno_value.setText((players.get(pos).getco_chairman()));


        return convertView;
    }


}
