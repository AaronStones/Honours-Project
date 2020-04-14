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
    AlertDialog alertDialog;
    UpdateInformationWorker(Context ctx){

        context = ctx;
    }

    protected String doInBackground(String... params) {
        type = params[0];
        Parameter = params[3];
        if (params[0].equals("Json")){
            String urlChange = "https://mayar.abertay.ac.uk/~1600964/Honours-Project/Android/APIs/Controller/nameChange.php";
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

                    +URLEncoder.encode("file","UTF-8")+"="+URLEncoder.encode(File,"UTF-8");

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
        if (params[0].equals("Doctor") && params[3].equals("doctorChange")){
            String urlChange = "https://mayar.abertay.ac.uk/~1600964/Honours-Project/Android/APIs/Controller/doctorChange.php";
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

                        +URLEncoder.encode("email","UTF-8")+"="+URLEncoder.encode(Email,"UTF-8");

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
        alertDialog.setTitle("Change Request");
    }

    @Override
    protected void onPostExecute(String result) {
        if (type.equals("Json") && Parameter.equals("nameChange")){
            if (result.equals("success")){
                alertDialog.setMessage("Your Name has been updated");
                alertDialog.show();
            }
        }
        if (type.equals("Json") && Parameter.equals("mobileChange")){
            if (result.equals("success")){
                alertDialog.setMessage("Your Mobile has been updated");
                alertDialog.show();
            }
        }
        if (type.equals("Doctor") && Parameter.equals("doctorChange")){
            if (result.equals("success")){
                alertDialog.setMessage("Your Doctor has been updated");
                alertDialog.show();
            }
            else{
                alertDialog.setMessage("Some of your details have been rejected, Doctor's name wrong");
                alertDialog.show();
            }
        }
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    }



