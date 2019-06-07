package com.demotxt.droidsrce.homedashboard;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Joinus extends AppCompatActivity {

    Button submit_;

    static EditText name_, email_, mobile_, address_, localarea_;
    Dialog dialog;
    ProgressDialog csprogress;
    String status;
    static int statuscode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.joinus);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new
                    StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        dialog = new Dialog(getApplicationContext());
        csprogress = new ProgressDialog(Joinus.this);
        submit_ = findViewById(R.id.submit);
        name_ = findViewById(R.id.name);
        email_ = findViewById(R.id.email);
        mobile_ = findViewById(R.id.mobile);
        address_ = findViewById(R.id.address);
        localarea_ = findViewById(R.id.localarea);

        submit_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Network_config.is_Network_Connected_flag(getApplicationContext())) {

                    if (name_.getText().toString().trim().matches("")) {
                        Toast.makeText(getApplicationContext(), "User Name required", Toast.LENGTH_SHORT).show();
                    } else if (email_.getText().toString().trim().matches("")) {
                        Toast.makeText(getApplicationContext(), "Email required", Toast.LENGTH_SHORT).show();
                    } else if (!email_.getText().toString().trim().matches("[a-zA-Z0-9._-]+@[a-z]+.[a-z]+")) {
                        Toast.makeText(getApplicationContext(), "Invalid Email", Toast.LENGTH_SHORT).show();

                    } else if (mobile_.getText().toString().trim().matches("")) {
                        Toast.makeText(getApplicationContext(), "Mobile no required", Toast.LENGTH_SHORT).show();
                    } else if (address_.getText().toString().trim().matches("")) {
                        Toast.makeText(getApplicationContext(), "Address required", Toast.LENGTH_SHORT).show();
                    } else if (localarea_.getText().toString().trim().matches("")) {
                        Toast.makeText(getApplicationContext(), "Local area name required", Toast.LENGTH_SHORT).show();
                    } else {


                        csprogress.setMessage("Loading...");
                        csprogress.show();
                        csprogress.setCanceledOnTouchOutside(false);


                       /* if (checkbox_.isChecked()) {
                            checkbox_value = "1";
                        } else {
                            checkbox_value = "0";
                        }*/


                        new android.os.Handler().postDelayed(new Runnable() {

                            @Override
                            public void run() {
                                //csprogress.dismiss();
//whatever you want just you have to launch overhere.


                            }
                        }, 1000);//just mention the time when you want to launch your action
                        //new Gettransaction_form_send().execute();

                        new HttpAsyncTask().execute(Iconstant.joinus);
                    }

                } else {
                    Network_config.customAlert(dialog, getApplicationContext(), getResources().getString(R.string.app_name),
                            getResources().getString(R.string.connection_message));
                }


            }
        });
    }


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


            jsonObject.accumulate("name", name_.getText().toString().trim());
            jsonObject.accumulate("email", email_.getText().toString().trim());
            jsonObject.accumulate("mobile", mobile_.getText().toString().trim());
            jsonObject.accumulate("address", address_.getText().toString().trim());
            jsonObject.accumulate("zone", localarea_.getText().toString().trim());


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

    private class HttpAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {

            return POST_flag_launch(urls[0]);

        }

        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {

            Log.d("Flag Response", result);


            try {

                if (result != null) {
                    try {

                        if (csprogress.isShowing()) {
                            csprogress.dismiss();
                        }
                        JSONObject jsonObj = new JSONObject(result);


                        status = jsonObj.getString("status");


                       /* if (csprogress.isShowing()) {
                            csprogress.dismiss();

                        }*/

                        if (status.matches("200")) {


                            Toast.makeText(getApplicationContext(), "Successfully Joined", Toast.LENGTH_SHORT).show();

                            Intent i = new Intent(Joinus.this, MainActivity.class);
                            startActivity(i);
                            finish();

                        }


                        // Getting JSON Array node


                        // Getting JSON Array node

                    } catch (JSONException e) {

                        e.printStackTrace();
                    }
                } else {
                    if (csprogress.isShowing()) {
                        csprogress.dismiss();
                    }
                    Toast.makeText(getApplicationContext(), "Failed to connect", Toast.LENGTH_SHORT).show();

                }


            } catch (Exception e) {

            }

        }
    }
}