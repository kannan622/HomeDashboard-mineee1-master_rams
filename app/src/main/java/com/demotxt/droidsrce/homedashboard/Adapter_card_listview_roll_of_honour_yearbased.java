package com.demotxt.droidsrce.homedashboard;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class Adapter_card_listview_roll_of_honour_yearbased extends BaseAdapter {

    Context c;
    List<roll_of_honour_model> players = null;
    // CustomFilter filter;

    boolean isSet;


    private ArrayList<roll_of_honour_model> arraylist;
    private int selectedPosition = 0;

    public Adapter_card_listview_roll_of_honour_yearbased(Context ctx, List<roll_of_honour_model> players) {
        // TODO Auto-generated constructor stub

        this.c = ctx;
        this.players = players;

        this.arraylist = new ArrayList<roll_of_honour_model>();
        this.arraylist.addAll(players);


        try {
            if (players.isEmpty()) {
                Toast.makeText(c, "No data", Toast.LENGTH_LONG)
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
            convertView = inflater.inflate(R.layout.roll_of_honour_yearbased, null);
        }

        final TextView nameTxt = convertView.findViewById(R.id.name_text);


        //SET DATA TO THEM
        nameTxt.setText(("Roll of Honour " + players.get(pos).getbusinesssince_year()));


        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(c, Roll_of_honour.class);
                i.putExtra("year", players.get(pos).getbusinesssince_year());
                c.startActivity(i);

            }
        });


        return convertView;
    }


}
