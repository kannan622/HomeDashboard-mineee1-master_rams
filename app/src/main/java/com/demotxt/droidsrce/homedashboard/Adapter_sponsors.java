package com.demotxt.droidsrce.homedashboard;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;


public class Adapter_sponsors extends BaseAdapter {

    Context c;
    List<sponsorsmodel> players = null;
    // CustomFilter filter;
    ArrayList<sponsorsmodel> filterList;
    boolean isSet;


    private ArrayList<sponsorsmodel> arraylist;
    private int selectedPosition = 0;

    public Adapter_sponsors(Context ctx, List<sponsorsmodel> players) {
        // TODO Auto-generated constructor stub

        this.c = ctx;
        this.players = players;

        this.arraylist = new ArrayList<sponsorsmodel>();
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
            convertView = inflater.inflate(R.layout.tab_view, null);
        }

        final TextView nameTxt = convertView.findViewById(R.id.tvTitle);
        final ImageView iv = convertView.findViewById(R.id.ivImage);


        //SET DATA TO THEM
        nameTxt.setText((players.get(pos).getName()));
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher_round)
                .error(R.mipmap.ic_launcher_round);


        // Glide.with(c).load("https://www.google.com/search?rlz=1C1CHBF_enIN848IN848&biw=1366&bih=608&tbm=isch&sa=1&ei=EcH-XMqmJ4rb9QOuu4bwBw&q=low+quality+image+nature&oq=low+quality+image+nature&gs_l=img.3...4946.9597..9917...4.0..0.204.1912.0j10j1......0....1..gws-wiz-img.......0j0i30j0i5i30j0i8i30j0i24.W46_24uHGII#imgrc=OpyzsMxZrwPrdM:").apply(options).into(iv);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(c, Sponcers.class);
                i.putExtra("link", players.get(pos).getlink());
                c.startActivity(i);

            }
        });
        return convertView;
    }


}
