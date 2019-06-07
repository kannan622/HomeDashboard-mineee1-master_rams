package com.demotxt.droidsrce.homedashboard;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class President_msg extends AppCompatActivity {

    TextView name_, message_;

    String status;


    Dialog dialog;
    ProgressDialog csprogress;

    String first_name, photo, msg, year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.president_msg);


        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new
                    StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        dialog = new Dialog(getApplicationContext());
        csprogress = new ProgressDialog(President_msg.this);

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
            new GetContacts_().execute();
        } else {
            Network_config.customAlert(dialog, getApplicationContext(), getResources().getString(R.string.app_name),
                    getResources().getString(R.string.connection_message));
        }

        name_ = findViewById(R.id.name);

        message_ = findViewById(R.id.message);
    }


    private class GetContacts_ extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }


        @Override
        protected Void doInBackground(Void... arg0) {
            Handler_ sh = new Handler_(getApplicationContext());

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall_login(Iconstant.president_msg, "", "");

            Log.e("res", "Response from url: " + jsonStr);


            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);


                    status = jsonObj.getString("Status");


                    if (status.matches("200")) {
                        JSONArray myresult = jsonObj.getJSONArray("Data");


                        // looping through All Contacts
                        for (int i = 0; i < myresult.length(); i++) {
                            roll_of_honour_model lan;


                            JSONObject c = myresult.getJSONObject(i);

                            first_name = c.getString("name");


                            photo = c.getString("image");
                            msg = c.getString("msg");
                            year = c.getString("years");


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

                    name_.setText(first_name);

                    message_.setText(msg);
                }
                if (status.matches("401")) {
                    Toast.makeText(getApplicationContext(), "Invalid", Toast.LENGTH_SHORT).show();


                }

            } catch (Exception e) {

                e.printStackTrace();
                Toast.makeText(getApplicationContext(), "Failed to connect,Please Try again", Toast.LENGTH_SHORT).show();
            }


        }


    }

}