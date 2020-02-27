package com.example.honoursproject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

    public class TakeTestWorker extends AsyncTask<String,Void,String> {

        Context context;
        String Email;
        String type;
        AlertDialog alertDialog;


        TakeTestWorker(Context ctx){

            context = ctx;
        }

        protected String doInBackground(String... params) {
            type = params[0];
            if (params[0].equals("result")){
                String urlAdvice = "https://mayar.abertay.ac.uk/~1600964/Honours-Project/Android/APIs/Controller/recordResult.php";
                Email = params[1];
                String doctor = params[2];
                String counts = params[3];
                String hr = params[4];
                String weight = params[5];
                String temp = params[6];
                String sys = params[7];
                String dys = params[8];

                try{
                    URL url = new URL(urlAdvice);
                    HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                    httpURLConnection.setRequestMethod(("POST"));
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String post_data = URLEncoder.encode("email", "UTF-8")+"="+ URLEncoder.encode(Email, "UTF-8")+"&"
                            +URLEncoder.encode("count","UTF-8")+"="+URLEncoder.encode(counts,"UTF-8") +"&"
                            +URLEncoder.encode("weight","UTF-8")+"="+URLEncoder.encode(weight,"UTF-8") +"&"
                            +URLEncoder.encode("temperature","UTF-8")+"="+URLEncoder.encode(temp,"UTF-8") +"&"
                            +URLEncoder.encode("hr","UTF-8")+"="+URLEncoder.encode(hr,"UTF-8") +"&"
                            +URLEncoder.encode("dys","UTF-8")+"="+URLEncoder.encode(dys,"UTF-8") +"&"
                            +URLEncoder.encode("sys","UTF-8")+"="+URLEncoder.encode(sys,"UTF-8") +"&"
                            +URLEncoder.encode("doctor","UTF-8")+"="+URLEncoder.encode(doctor,"UTF-8");

                    bufferedWriter.write(post_data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStream.close();
                    InputStream inputStream = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                    String result = "";
                    String line="";
                    while ((line = bufferedReader.readLine()) != null){
                        result += line;
                    }
                    bufferedReader.close();
                    inputStream.close();
                    httpURLConnection.disconnect();
                    return result;
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            return null;
        }

        @Override
        protected void onPreExecute() {
            alertDialog = new AlertDialog.Builder(context).create();
            alertDialog.setTitle("Login Status");
        }

        @Override
        protected void onPostExecute(String result) {
            alertDialog.setMessage(result);
            alertDialog.show();
        }


        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }
