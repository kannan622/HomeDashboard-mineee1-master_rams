package com.demotxt.droidsrce.homedashboard;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Events_Notification extends AppCompatActivity {

    Dialog dialog;
    ProgressDialog csprogress;
    ListView lv;
    ListView lv1;
    TextView textview_latest, textview_upcoming;
    String status;
    public ArrayList<events_model> countries_lang = new ArrayList<events_model>();

    Adapter_card_listview_events adapt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_events_notification);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new
                    StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        dialog = new Dialog(getApplicationContext());
        csprogress = new ProgressDialog(Events_Notification.this);
        lv = findViewById(R.id.mylistview);
        lv1 = findViewById(R.id.mylistview1);
        final RelativeLayout rel_f_lang = findViewById(R.id.rel_from_lang);
        final RelativeLayout rel_t_lang = findViewById(R.id.rel_to_lang);

        textview_latest = findViewById(R.id.latest);
        textview_upcoming = findViewById(R.id.upcoming);



        textview_latest.setBackgroundResource(R.drawable.rect_color);
        textview_upcoming.setBackgroundResource(R.drawable.rect_whiteborder);

        textview_latest.setTextColor(Color.parseColor("#FFFFFF"));


        rel_f_lang.setVisibility(View.VISIBLE);


        if (Network_config.is_Network_Connected_flag(getApplicationContext())) {

            csprogress.setMessage("Loading...");
            csprogress.show();
            csprogress.setCanceledOnTouchOutside(false);
            new android.os.Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    //csprogress.dismiss();
//whatever you want just you have to launch overhere.


                }
            }, 0);//just mention the time when you want to launch your action
            //new Gettransaction_form_send().execute();
            new GetContacts().execute();
        } else {
            Network_config.customAlert(dialog, getApplicationContext(), getResources().getString(R.string.app_name),
                    getResources().getString(R.string.connection_message));
        }


        textview_latest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                textview_latest.setBackgroundResource(R.drawable.rect_color);
                textview_upcoming.setBackgroundResource(R.drawable.rect_whiteborder);


                textview_latest.setTextColor(Color.parseColor("#FFFFFF"));
                textview_upcoming.setTextColor(Color.parseColor("#0039e6"));

                rel_f_lang.setVisibility(View.VISIBLE);
                rel_t_lang.setVisibility(View.INVISIBLE);


            }
        });

        textview_upcoming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {




                textview_latest.setBackgroundResource(R.drawable.rect_whiteborder);
                textview_upcoming.setBackgroundResource(R.drawable.rect_color);

                textview_latest.setTextColor(Color.parseColor("#0039e6"));
                textview_upcoming.setTextColor(Color.parseColor("#FFFFFF"));
                rel_f_lang.setVisibility(View.INVISIBLE);

                rel_t_lang.setVisibility(View.VISIBLE);
            }
        });


    }

    private class GetContacts extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }


        @Override
        protected Void doInBackground(Void... arg0) {
            Handler_ sh = new Handler_(getApplicationContext());

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall_login(Iconstant.office_bear, "", "");

            Log.e("", "Response from url: " + jsonStr);


            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);


                    status = jsonObj.getString("Status");


                    if (status.matches("200")) {
                        JSONArray myresult = jsonObj.getJSONArray("Data");
                        try {
                           /* if (countries_lang != null) {
                                countries_lang.clear();
                            }*/
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        // looping through All Contacts
                        for (int i = 0; i < myresult.length(); i++) {
                            events_model lan;


                            JSONObject c = myresult.getJSONObject(i);

                           /* String first_name = c.getString("membername");


                            String position = c.getString("member_type");
                            String address = c.getString("officeaddress1");
                            String phonenumber = c.getString("mobilenumber1");*/


                            lan = new events_model("", "", "", "","","");

                            countries_lang.add(lan);

                        }
                    }


                    // Getting JSON Array node


                    // Getting JSON Array node

                } catch (final JSONException e) {


                }
            } else {

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            try {
                if (csprogress.isShowing()) {
                    csprogress.dismiss();
                }


                if (status.matches("200")) {
                    adapt = new Adapter_card_listview_events(getApplicationContext(), countries_lang);

                    // offers_count.setText("We have " + countries_lang.size() + " offers for you!");

                    lv.setAdapter(adapt);
                }
                if (status.matches("401")) {
                    Toast.makeText(getApplicationContext(), "Invalid Username / password", Toast.LENGTH_SHORT).show();


                }

            } catch (Exception e) {

                e.printStackTrace();
                Toast.makeText(getApplicationContext(), "Failed to connect,Please Try again", Toast.LENGTH_SHORT).show();
            }


        }


    }
}
