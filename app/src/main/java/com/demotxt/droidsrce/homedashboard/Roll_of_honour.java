package com.demotxt.droidsrce.homedashboard;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Roll_of_honour extends AppCompatActivity {

    static int statuscode;

    String status;

    String fin_result;
    Dialog dialog;
    ProgressDialog csprogress;
    String year_value;

    public ArrayList<roll_of_honour_model> countries_lang = new ArrayList<roll_of_honour_model>();

    Adapter_card_listview_roll_of_honour adapt;

    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new
                    StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        Intent intent = this.getIntent();
        if (intent != null)// to avoid the NullPointerException

            year_value = intent.getStringExtra("year");

        dialog = new Dialog(getApplicationContext());
        csprogress = new ProgressDialog(Roll_of_honour.this);
        lv = findViewById(R.id.mycardlistview);
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
            String jsonStr = sh.makeServiceCall_login(Iconstant.role_of_honour, "", "");

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
                            roll_of_honour_model lan;


                            JSONObject c = myresult.getJSONObject(i);
                            String businesssince_year = c.getString("businesssince");

                            if (businesssince_year.matches(year_value)) {
                                String first_name = c.getString("membername");


                                String photo = c.getString("photo");
                                String role = c.getString("member_type");
                                lan = new roll_of_honour_model(first_name, photo, role, businesssince_year);
                                countries_lang.add(lan);

                            }


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
                    adapt = new Adapter_card_listview_roll_of_honour(getApplicationContext(), countries_lang);

                    // offers_count.setText("We have " + countries_lang.size() + " offers for you!");

                    lv.setAdapter(adapt);
                }
                if (status.matches("401")) {
                    Toast.makeText(getApplicationContext(), "Invalid Username / password", Toast.LENGTH_SHORT).show();


                }

            } catch (Exception e) {
                if (csprogress.isShowing()) {
                    csprogress.dismiss();
                }
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), "Failed to connect,Please Try again", Toast.LENGTH_SHORT).show();
            }


        }


    }


}