package com.demotxt.droidsrce.homedashboard;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class All_Regions extends AppCompatActivity {

    Button b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11;

    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_regions);
        dialog = new Dialog(getApplicationContext());
        b1 = findViewById(R.id.region1);
        b2 = findViewById(R.id.region2);
        b3 = findViewById(R.id.region3);
        b4 = findViewById(R.id.region4);
        b5 = findViewById(R.id.region5);
        b6 = findViewById(R.id.region6);
        b7 = findViewById(R.id.region7);
        b8 = findViewById(R.id.region8);
        b9 = findViewById(R.id.region9);
        b10 = findViewById(R.id.region10);
        b11 = findViewById(R.id.region11);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Network_config.is_Network_Connected_flag(getApplicationContext())) {
                    Intent i = new Intent(All_Regions.this, Associations.class);
                    i.putExtra("type", "Region-1");
                    startActivity(i);
                } else {
                    Network_config.customAlert(dialog, getApplicationContext(), getResources().getString(R.string.app_name),
                            getResources().getString(R.string.connection_message));
                }


            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Network_config.is_Network_Connected_flag(getApplicationContext())) {
                    Intent i = new Intent(All_Regions.this, Associations.class);
                    i.putExtra("type", "Region-2");
                    startActivity(i);
                } else {
                    Network_config.customAlert(dialog, getApplicationContext(), getResources().getString(R.string.app_name),
                            getResources().getString(R.string.connection_message));
                }


            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Network_config.is_Network_Connected_flag(getApplicationContext())) {
                    Intent i = new Intent(All_Regions.this, Associations.class);
                    i.putExtra("type", "Region-3");
                    startActivity(i);
                } else {
                    Network_config.customAlert(dialog, getApplicationContext(), getResources().getString(R.string.app_name),
                            getResources().getString(R.string.connection_message));
                }


            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Network_config.is_Network_Connected_flag(getApplicationContext())) {
                    Intent i = new Intent(All_Regions.this, Associations.class);
                    i.putExtra("type", "Region-4");
                    startActivity(i);
                } else {
                    Network_config.customAlert(dialog, getApplicationContext(), getResources().getString(R.string.app_name),
                            getResources().getString(R.string.connection_message));
                }


            }
        });

        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Network_config.is_Network_Connected_flag(getApplicationContext())) {
                    Intent i = new Intent(All_Regions.this, Associations.class);
                    i.putExtra("type", "Region-5");
                    startActivity(i);
                } else {
                    Network_config.customAlert(dialog, getApplicationContext(), getResources().getString(R.string.app_name),
                            getResources().getString(R.string.connection_message));
                }


            }
        });

        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Network_config.is_Network_Connected_flag(getApplicationContext())) {
                    Intent i = new Intent(All_Regions.this, Associations.class);
                    i.putExtra("type", "Region-6");
                    startActivity(i);
                } else {
                    Network_config.customAlert(dialog, getApplicationContext(), getResources().getString(R.string.app_name),
                            getResources().getString(R.string.connection_message));
                }


            }
        });


        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Network_config.is_Network_Connected_flag(getApplicationContext())) {
                    Intent i = new Intent(All_Regions.this, Associations.class);
                    i.putExtra("type", "Region-7");
                    startActivity(i);
                } else {
                    Network_config.customAlert(dialog, getApplicationContext(), getResources().getString(R.string.app_name),
                            getResources().getString(R.string.connection_message));
                }


            }
        });

        b8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Network_config.is_Network_Connected_flag(getApplicationContext())) {
                    Intent i = new Intent(All_Regions.this, Associations.class);
                    i.putExtra("type", "Region-8");
                    startActivity(i);
                } else {
                    Network_config.customAlert(dialog, getApplicationContext(), getResources().getString(R.string.app_name),
                            getResources().getString(R.string.connection_message));
                }


            }
        });

        b9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Network_config.is_Network_Connected_flag(getApplicationContext())) {
                    Intent i = new Intent(All_Regions.this, Associations.class);
                    i.putExtra("type", "Region-9");
                    startActivity(i);
                } else {
                    Network_config.customAlert(dialog, getApplicationContext(), getResources().getString(R.string.app_name),
                            getResources().getString(R.string.connection_message));
                }


            }
        });

        b10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Network_config.is_Network_Connected_flag(getApplicationContext())) {
                    Intent i = new Intent(All_Regions.this, Associations.class);
                    i.putExtra("type", "Region-10");
                    startActivity(i);
                } else {
                    Network_config.customAlert(dialog, getApplicationContext(), getResources().getString(R.string.app_name),
                            getResources().getString(R.string.connection_message));
                }


            }
        });

        b11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Network_config.is_Network_Connected_flag(getApplicationContext())) {
                    Intent i = new Intent(All_Regions.this, Associations.class);
                    i.putExtra("type", "Region-11");
                    startActivity(i);
                } else {
                    Network_config.customAlert(dialog, getApplicationContext(), getResources().getString(R.string.app_name),
                            getResources().getString(R.string.connection_message));
                }


            }
        });


    }
}