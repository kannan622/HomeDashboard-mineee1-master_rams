package com.demotxt.droidsrce.homedashboard;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    CollapsingToolbarLayout collapsingToolbarLayoutAndroid;
    CoordinatorLayout rootLayoutAndroid;
    GridView gridView;
    Context context;
    Dialog dialog;
    ArrayList arrayList;

    public static String[] gridViewStrings = {
            "Office Bearers",
            "Roll of Honours",
            "Chennai Zone",
            "Events",
            "Sponsors",
            "Join Us",
            "President Msg",
            "Gallery",
            "About Us",
            "Associations",
            "Contact Us",
            "Settings"

    };
    public static int[] gridViewImages = {
            R.drawable.office_block,
            R.drawable.honour,
            R.drawable.worldwide,
            R.drawable.calendar,
            R.drawable.investor,
            R.drawable.joinus,
            R.drawable.president,
            R.drawable.gallery_home,
            R.drawable.aboutus,
            R.drawable.association,
            R.drawable.contact,
            R.drawable.settings
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gridview);
        dialog = new Dialog(getApplicationContext());
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        gridView = (GridView) findViewById(R.id.grid);
        gridView.setAdapter(new GridViewAdapter(this, gridViewStrings, gridViewImages));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                if (position == 0) {

                    if (Network_config.is_Network_Connected_flag(getApplicationContext())) {
                        Intent myIntent = new Intent(view.getContext(), Office_bearers.class);
                        startActivityForResult(myIntent, 0);
                    } else {
                        Network_config.customAlert(dialog, getApplicationContext(), getResources().getString(R.string.app_name),
                                getResources().getString(R.string.connection_message));
                    }

                }

                if (position == 1) {
                    if (Network_config.is_Network_Connected_flag(getApplicationContext())) {
                        Intent myIntent = new Intent(view.getContext(), Roll_of_Honours.class);
                        startActivityForResult(myIntent, 0);
                    } else {
                        Network_config.customAlert(dialog, getApplicationContext(), getResources().getString(R.string.app_name),
                                getResources().getString(R.string.connection_message));
                    }


                }

                if (position == 2) {
                    if (Network_config.is_Network_Connected_flag(getApplicationContext())) {
                        Intent myIntent = new Intent(view.getContext(), Chennai_zone.class);
                        startActivityForResult(myIntent, 0);
                    } else {
                        Network_config.customAlert(dialog, getApplicationContext(), getResources().getString(R.string.app_name),
                                getResources().getString(R.string.connection_message));
                    }

                }

                if (position == 3) {
                    if (Network_config.is_Network_Connected_flag(getApplicationContext())) {
                        Intent myIntent = new Intent(view.getContext(), Events.class);
                        startActivityForResult(myIntent, 0);
                    } else {
                        Network_config.customAlert(dialog, getApplicationContext(), getResources().getString(R.string.app_name),
                                getResources().getString(R.string.connection_message));
                    }

                }

                if (position == 4) {
                    if (Network_config.is_Network_Connected_flag(getApplicationContext())) {
                        Intent myIntent = new Intent(view.getContext(), Sponcers.class);
                        startActivityForResult(myIntent, 0);
                    } else {
                        Network_config.customAlert(dialog, getApplicationContext(), getResources().getString(R.string.app_name),
                                getResources().getString(R.string.connection_message));
                    }

                }

                if (position == 5) {
                    if (Network_config.is_Network_Connected_flag(getApplicationContext())) {
                        Intent myIntent = new Intent(view.getContext(), Joinus.class);
                        startActivityForResult(myIntent, 0);
                    } else {
                        Network_config.customAlert(dialog, getApplicationContext(), getResources().getString(R.string.app_name),
                                getResources().getString(R.string.connection_message));
                    }

                }

                if (position == 6) {
                    if (Network_config.is_Network_Connected_flag(getApplicationContext())) {
                        Intent myIntent = new Intent(view.getContext(), President_msg.class);
                        startActivityForResult(myIntent, 0);
                    } else {
                        Network_config.customAlert(dialog, getApplicationContext(), getResources().getString(R.string.app_name),
                                getResources().getString(R.string.connection_message));
                    }

                }

                if (position == 7) {
                    if (Network_config.is_Network_Connected_flag(getApplicationContext())) {
                        Intent myIntent = new Intent(view.getContext(), Circular.class);
                        startActivityForResult(myIntent, 0);
                    } else {
                        Network_config.customAlert(dialog, getApplicationContext(), getResources().getString(R.string.app_name),
                                getResources().getString(R.string.connection_message));
                    }

                }
                if (position == 8) {

                    Intent myIntent = new Intent(view.getContext(), Aboutus.class);
                    startActivityForResult(myIntent, 0);
                }

                if (position == 9) {

                    Intent myIntent = new Intent(view.getContext(), All_Regions.class);
                    startActivityForResult(myIntent, 0);
                }
            }
        });


        initInstances();
    }

    private void initInstances() {
        rootLayoutAndroid = (CoordinatorLayout) findViewById(R.id.android_coordinator_layout);
        collapsingToolbarLayoutAndroid = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar_android_layout);
        collapsingToolbarLayoutAndroid.setTitle(" ");
    }

}
