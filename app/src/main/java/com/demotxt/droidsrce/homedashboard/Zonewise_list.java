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

public class Zonewise_list extends AppCompatActivity {

    static int statuscode;
    public ArrayList<zone_model> countries_lang = new ArrayList<zone_model>();
    public ArrayList<zone_area_model> countries_lang_area = new ArrayList<zone_area_model>();

    String status;
    String zone_type;
    Adapter_card_listview_zonewise adapt;
    Adapter_card_listview_zonewise_area adapt_area;
    ListView lv;
    ListView lv1;
    String fin_result;
    TextView textview_members_wise, textview_area_wise;
    Dialog dialog;
    ProgressDialog csprogress;

    public static String POST_flag_launch(String url) {
        InputStream inputStream = null;
        String result = "";
        try {

            // 1. create HttpClient
            HttpClient httpclient = new DefaultHttpClient();

            // 2. make POST request to the given URL
            HttpPost httpPost = new HttpPost(url);


            String json = "";


            // int jjson;

            // 3. build jsonObject
            JSONObject jsonObject = new JSONObject();
            // JSONArray jsonarr = new JSONArray();


            jsonObject.accumulate("office_city", "chennai");

            // jsonObject.accumulate("category", session.getUserDetails().get(SessionManager.KEY_category_id));


            // 4. convert JSONObject to JSON to String
            json = jsonObject.toString();

            Log.d("Create Response", json.toString());

            // 5. set json to StringEntity
            StringEntity se = new StringEntity(json);

            // 6. set httpPost Entity
            httpPost.setEntity(se);

            Log.d("STEP 111111111111111", "STEPPPPPPPPP111111");

            // 7. Set some headers to inform server about the type of the
            // content
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");
            Log.d("STEP 2222222222", "22222222222");

            // 8. Execute POST request to the given URL
            HttpResponse httpResponse = httpclient.execute(httpPost);

            Log.d("STEP 3333333333", "33333333333333333");

            httpResponse.getStatusLine().getStatusCode();
            statuscode = httpResponse.getStatusLine().getStatusCode();

            Log.d("Statsucode", "" + statuscode);

            // 9. receive response as inputStream
            inputStream = httpResponse.getEntity().getContent();

            Log.d("STEP 444444444444", "44444444444444444444");

            // 10. convert inputstream to string
            if (inputStream != null)
                result = convertInputStreamToString(inputStream);
            else
                result = "Did not work!";


        } catch (Exception e) {
            // LogUtils.LOGD("InputStream", e.getLocalizedMessage());
        }//

        // 11. return result
        return result;
    }

    private static String convertInputStreamToString(InputStream inputStream)
            throws IOException {
        BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while ((line = bufferedReader.readLine()) != null)
            result += line;

        inputStream.close();
        return result;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_zonewise);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new
                    StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        dialog = new Dialog(getApplicationContext());
        csprogress = new ProgressDialog(Zonewise_list.this);
        lv = findViewById(R.id.mylistview);
        lv1 = findViewById(R.id.mylistview1);
        final RelativeLayout rel_f_lang = findViewById(R.id.rel_from_lang);
        final RelativeLayout rel_t_lang = findViewById(R.id.rel_to_lang);

        textview_members_wise = findViewById(R.id.members_wise);
        textview_area_wise = findViewById(R.id.area_wise);

        Intent intent = this.getIntent();
        if (intent != null)// to avoid the NullPointerException

            zone_type = intent.getStringExtra("zone");

        textview_members_wise.setBackgroundResource(R.drawable.rect_color);
        textview_area_wise.setBackgroundResource(R.drawable.rect_whiteborder);

        textview_members_wise.setTextColor(Color.parseColor("#FFFFFF"));


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
            new HttpAsyncTask().execute(Iconstant.chennaizone);
        } else {
            Network_config.customAlert(dialog, getApplicationContext(), getResources().getString(R.string.app_name),
                    getResources().getString(R.string.connection_message));
        }


        textview_members_wise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                textview_members_wise.setBackgroundResource(R.drawable.rect_color);
                textview_area_wise.setBackgroundResource(R.drawable.rect_whiteborder);


                textview_members_wise.setTextColor(Color.parseColor("#FFFFFF"));
                textview_area_wise.setTextColor(Color.parseColor("#0039e6"));

                rel_f_lang.setVisibility(View.VISIBLE);
                rel_t_lang.setVisibility(View.INVISIBLE);


            }
        });

        textview_area_wise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (zone_type.matches("north")) {
                    new GetContacts_north_area().execute();
                } else if (zone_type.matches("south")) {
                    new GetContacts_south_area().execute();
                } else if (zone_type.matches("central")) {
                    new GetContacts_central_area().execute();
                }


                textview_members_wise.setBackgroundResource(R.drawable.rect_whiteborder);
                textview_area_wise.setBackgroundResource(R.drawable.rect_color);

                textview_members_wise.setTextColor(Color.parseColor("#0039e6"));
                textview_area_wise.setTextColor(Color.parseColor("#FFFFFF"));
                rel_f_lang.setVisibility(View.INVISIBLE);

                rel_t_lang.setVisibility(View.VISIBLE);
            }
        });


    }


    private class GetContacts_north extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }


        @Override
        protected Void doInBackground(Void... arg0) {
            Handler_ sh = new Handler_(getApplicationContext());

            // Making a request to url and getting response


            Log.e("", "Response from url: " + fin_result);


            if (fin_result != null) {
                try {
                    JSONObject jsonObj = new JSONObject(fin_result);


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
                            zone_model lan;


                            JSONObject c = myresult.getJSONObject(i);
                            String zone_type = c.getString("zone");

                            if (zone_type.equalsIgnoreCase("North")) {
                                String first_name = c.getString("membername");


                                String position = c.getString("member_type");
                                String address = c.getString("officeaddress1");
                                String phonenumber = c.getString("mobilenumber1");


                                lan = new zone_model(first_name, position, address, phonenumber);

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


                if (status.matches("200")) {
                    adapt = new Adapter_card_listview_zonewise(getApplicationContext(), countries_lang);

                    // offers_count.setText("We have " + countries_lang.size() + " offers for you!");

                    lv.setAdapter(adapt);
                }
                if (status.matches("401")) {
                    Toast.makeText(getApplicationContext(), "Invalid ", Toast.LENGTH_SHORT).show();


                }

            } catch (Exception e) {

                e.printStackTrace();
                Toast.makeText(getApplicationContext(), "Failed to connect,Please Try again", Toast.LENGTH_SHORT).show();
            }


        }


    }

    private class GetContacts_north_area extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }


        @Override
        protected Void doInBackground(Void... arg0) {
            Handler_ sh = new Handler_(getApplicationContext());

            // Making a request to url and getting response


            Log.e("", "Response from url: " + fin_result);


            if (fin_result != null) {
                try {
                    JSONObject jsonObj = new JSONObject(fin_result);


                    status = jsonObj.getString("Status");


                    if (status.matches("200")) {
                        JSONArray myresult = jsonObj.getJSONArray("Data");
                        try {
                            if (countries_lang_area != null) {
                                countries_lang_area.clear();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        // looping through All Contacts
                        for (int i = 0; i < myresult.length(); i++) {
                            zone_area_model lan_area;


                            JSONObject c = myresult.getJSONObject(i);
                            String zone_type = c.getString("zone");

                            if (zone_type.equalsIgnoreCase("North")) {
                                String area_name = c.getString("officeaddress2");


                                String position = c.getString("member_type");
                                String address = c.getString("officeaddress1");
                                String phonenumber = c.getString("mobilenumber1");


                                lan_area = new zone_area_model(area_name);

                                countries_lang_area.add(lan_area);
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
                    adapt_area = new Adapter_card_listview_zonewise_area(getApplicationContext(), countries_lang_area);

                    // offers_count.setText("We have " + countries_lang.size() + " offers for you!");

                    lv1.setAdapter(adapt_area);
                }
                if (status.matches("401")) {
                    Toast.makeText(getApplicationContext(), "Invalid ", Toast.LENGTH_SHORT).show();


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


    private class GetContacts_south extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }


        @Override
        protected Void doInBackground(Void... arg0) {
            Handler_ sh = new Handler_(getApplicationContext());

            // Making a request to url and getting response


            Log.e("", "Response from url: " + fin_result);


            if (fin_result != null) {
                try {
                    JSONObject jsonObj = new JSONObject(fin_result);


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
                            zone_model lan;


                            JSONObject c = myresult.getJSONObject(i);
                            String zone_type = c.getString("zone");

                            if (zone_type.equalsIgnoreCase("South")) {
                                String first_name = c.getString("membername");


                                String position = c.getString("member_type");
                                String address = c.getString("officeaddress1");
                                String phonenumber = c.getString("mobilenumber1");


                                lan = new zone_model(first_name, position, address, phonenumber);

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
                    adapt = new Adapter_card_listview_zonewise(getApplicationContext(), countries_lang);

                    // offers_count.setText("We have " + countries_lang.size() + " offers for you!");

                    lv.setAdapter(adapt);
                }
                if (status.matches("401")) {
                    Toast.makeText(getApplicationContext(), "Invalid ", Toast.LENGTH_SHORT).show();


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

    private class GetContacts_south_area extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }


        @Override
        protected Void doInBackground(Void... arg0) {
            Handler_ sh = new Handler_(getApplicationContext());

            // Making a request to url and getting response


            Log.e("", "Response from url: " + fin_result);


            if (fin_result != null) {
                try {
                    JSONObject jsonObj = new JSONObject(fin_result);


                    status = jsonObj.getString("Status");


                    if (status.matches("200")) {
                        JSONArray myresult = jsonObj.getJSONArray("Data");
                        try {
                            if (countries_lang_area != null) {
                                countries_lang_area.clear();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        // looping through All Contacts
                        for (int i = 0; i < myresult.length(); i++) {
                            zone_area_model lan_area;


                            JSONObject c = myresult.getJSONObject(i);
                            String zone_type = c.getString("zone");

                            if (zone_type.equalsIgnoreCase("South")) {
                                String area_name = c.getString("officeaddress2");


                                lan_area = new zone_area_model(area_name);

                                countries_lang_area.add(lan_area);
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
                    adapt_area = new Adapter_card_listview_zonewise_area(getApplicationContext(), countries_lang_area);

                    // offers_count.setText("We have " + countries_lang.size() + " offers for you!");

                    lv1.setAdapter(adapt_area);
                }
                if (status.matches("401")) {
                    Toast.makeText(getApplicationContext(), "Invalid ", Toast.LENGTH_SHORT).show();


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


    private class GetContacts_central extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }


        @Override
        protected Void doInBackground(Void... arg0) {
            Handler_ sh = new Handler_(getApplicationContext());

            // Making a request to url and getting response


            Log.e("", "Response from url: " + fin_result);


            if (fin_result != null) {
                try {
                    JSONObject jsonObj = new JSONObject(fin_result);


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
                            zone_model lan;


                            JSONObject c = myresult.getJSONObject(i);
                            String zone_type = c.getString("zone");

                            if (zone_type.equalsIgnoreCase("Central")) {
                                String first_name = c.getString("membername");


                                String position = c.getString("member_type");
                                String address = c.getString("officeaddress1");
                                String phonenumber = c.getString("mobilenumber1");


                                lan = new zone_model(first_name, position, address, phonenumber);

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
                    adapt = new Adapter_card_listview_zonewise(getApplicationContext(), countries_lang);

                    // offers_count.setText("We have " + countries_lang.size() + " offers for you!");

                    lv.setAdapter(adapt);
                }
                if (status.matches("401")) {
                    Toast.makeText(getApplicationContext(), "Invalid", Toast.LENGTH_SHORT).show();


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

    private class GetContacts_central_area extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }


        @Override
        protected Void doInBackground(Void... arg0) {
            Handler_ sh = new Handler_(getApplicationContext());

            // Making a request to url and getting response


            Log.e("", "Response from url: " + fin_result);


            if (fin_result != null) {
                try {
                    JSONObject jsonObj = new JSONObject(fin_result);


                    status = jsonObj.getString("Status");


                    if (status.matches("200")) {
                        JSONArray myresult = jsonObj.getJSONArray("Data");
                        try {
                            if (countries_lang_area != null) {
                                countries_lang_area.clear();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        // looping through All Contacts
                        for (int i = 0; i < myresult.length(); i++) {
                            zone_area_model lan_area;


                            JSONObject c = myresult.getJSONObject(i);
                            String zone_type = c.getString("zone");

                            if (zone_type.equalsIgnoreCase("Central")) {
                                String area_name = c.getString("officeaddress2");


                                lan_area = new zone_area_model(area_name);

                                countries_lang_area.add(lan_area);
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
                    adapt_area = new Adapter_card_listview_zonewise_area(getApplicationContext(), countries_lang_area);

                    // offers_count.setText("We have " + countries_lang.size() + " offers for you!");

                    lv1.setAdapter(adapt_area);
                }
                if (status.matches("401")) {
                    Toast.makeText(getApplicationContext(), "Invalid ", Toast.LENGTH_SHORT).show();


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


    private class HttpAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {

            return POST_flag_launch(urls[0]);

        }

        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {

            Log.d("Flag Response", result);

            fin_result = result;


            try {

                if (result != null) {
                    try {

                        if (csprogress.isShowing()) {
                            csprogress.dismiss();
                        }
                        JSONObject jsonObj = new JSONObject(result);


                        status = jsonObj.getString("Status");


                       /* if (csprogress.isShowing()) {
                            csprogress.dismiss();

                        }*/

                        if (status.matches("200")) {

                            if (zone_type.matches("north")) {
                                new GetContacts_north().execute();
                            } else if (zone_type.matches("south")) {
                                new GetContacts_south().execute();
                            } else if (zone_type.matches("central")) {
                                new GetContacts_central().execute();
                            }

                        }


                        // Getting JSON Array node


                        // Getting JSON Array node

                    } catch (JSONException e) {

                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Failed to connect", Toast.LENGTH_SHORT).show();

                }


            } catch (Exception e) {

            }

        }
    }


}
