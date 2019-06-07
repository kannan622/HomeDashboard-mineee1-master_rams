package com.demotxt.droidsrce.homedashboard;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class Home extends AppCompatActivity {
    CardView office_bears,chennai_zone;
    Intent i;
    LinearLayout ll;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ll = (LinearLayout) findViewById(R.id.ll);
        office_bears = (CardView) findViewById(R.id.officebear);
        chennai_zone = (CardView) findViewById(R.id.chennaizone);


        office_bears.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Home.this, Office_bearers.class);
                startActivity(i);

            }
        });

        chennai_zone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Home.this, Chennai_zone.class);
                startActivity(i);

            }
        });


    }


}
