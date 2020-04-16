//Project: Honours Project 2020
//Author: Aaron Stones
//Date: 10/04/2020
//Purpose: to show the benefits of collecting lots of data about a patient
//using different devices and tests

package com.example.honoursproject;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

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

public class UpdateInformationWorker extends AsyncTask<String,Void,String> {
    Context context;
    String type;
    String Parameter;
    String Email;
    String File;
    AlertDialog alertDialog; //setup globals 

    UpdateInformationWorker(Context ctx){ //get context from previous activity

        context = ctx;
    }

    protected String doInBackground(String... params) {
        type = params[0];
        Parameter = params[3];
        if (params[0].equals("Json")){
            String urlChange = "https://mayar.abertay.ac.uk/~1600964/Honours-Project/Android/APIs/Controller/nameChange.php"; //api location
            File = params[1];
            Email = params[2];
            try{
            URL url = new URL(urlChange);
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod(("POST"));
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            String post_data = URLEncoder.encode("email", "UTF-8")+"="+ URLEncoder.encode(Email, "UTF-8")+"&"

                    +URLEncoder.encode("file","UTF-8")+"="+URLEncoder.encode(File,"UTF-8"); //encode the email and new json files

            bufferedWriter.write(post_data);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
            String result = "";
            String line="";
            while ((line = bufferedReader.readLine()) != null){ //get the result
                result += line;
            }
            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();
            return result;
            } catch (MalformedURLException e) { //catch errors
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (params[0].equals("Doctor") && params[3].equals("doctorChange")){
            String urlChange = "https://mayar.abertay.ac.uk/~1600964/Honours-Project/Android/APIs/Controller/doctorChange.php"; //field for changing the user's doctor
            String Doctor = params[1];
            Email = params[2];
            try{
                URL url = new URL(urlChange);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod(("POST"));
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("doctor", "UTF-8")+"="+ URLEncoder.encode(Doctor, "UTF-8")+"&"

                        +URLEncoder.encode("email","UTF-8")+"="+URLEncoder.encode(Email,"UTF-8"); //encode the doctor and the user's email

                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result = "";
                String line="";
                while ((line = bufferedReader.readLine()) != null){ //get results
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
    protected void onPreExecute() { //setup toast message
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Change Request");
    }

    @Override
    protected void onPostExecute(String result) { //name change success
        if (type.equals("Json") && Parameter.equals("nameChange")){
            if (result.equals("success")){
                alertDialog.setMessage("Your Name has been updated");
                alertDialog.show();
            }
        }
        if (type.equals("Json") && Parameter.equals("mobileChange")){ //mobile change success
            if (result.equals("success")){
                alertDialog.setMessage("Your Mobile has been updated");
                alertDialog.show();
            }
        }
        if (type.equals("Doctor") && Parameter.equals("doctorChange")){ //doctor change a success
            if (result.equals("success")){
                alertDialog.setMessage("Your Doctor has been updated");
                alertDialog.show();
            }
            else{ //details have been rekected
                alertDialog.setMessage("Some of your details have been rejected");
                alertDialog.show();
            }
        }
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    }



