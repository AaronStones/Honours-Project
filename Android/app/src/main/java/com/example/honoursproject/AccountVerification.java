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


public class AccountVerification extends AsyncTask<String,Void,String> {
    Context context;
    String type;
    String Email;
    AlertDialog alertDialog;

    JSONObject arr;
    String Doctor; //setup data structures

    AccountVerification(Context ctx){ //get the context

        context = ctx;
    }
    @Override
    protected String doInBackground(String... params) {
        type = params[0];
        String login_url = "https://mayar.abertay.ac.uk/~1600964/Honours-Project/Android/APIs/Controller/Login.php";
        String signup_url = "https://mayar.abertay.ac.uk/~1600964/Honours-Project/Android/APIs/Controller/Signup.php"; //store locations of the web API's
        if (type.equals("login")){ //type is login use that location
            try {
                Email = params[1];
                String Password = params[2];
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod(("POST"));
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("user_name", "UTF-8")+"="+ URLEncoder.encode(Email, "UTF-8")+"&"
                        +URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(Password,"UTF-8"); //encode the login parameters
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result = "";
                String line;
                while ((line = bufferedReader.readLine()) != null){ //get the reults
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect(); //disconnect the mobile app from the api
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (type.equals("signup")){ // type is signup
            try {
                Email = params[1];
                String Password = params[2];
                Doctor = params[3];
                String mobile = params[8];
                String name = params[7];

                arr=new JSONObject();

                arr.put("Dementia", params[4]);
                arr.put("Parkinsons",params[5]);
                arr.put("Other",params[6]);
                arr.put("name", name);
                arr.put("Mobile",mobile);

                String json = arr.toString(); //sort all the necessary strings etc


                URL url = new URL(signup_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod(("POST"));
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("user_name", "UTF-8")+"="+ URLEncoder.encode(Email, "UTF-8")+"&"
                        +URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(Password,"UTF-8")+"&"

                        +URLEncoder.encode("json","UTF-8")+"="+URLEncoder.encode(json,"UTF-8")+"&"

                        +URLEncoder.encode("doctor","UTF-8")+"="+URLEncoder.encode(Doctor,"UTF-8"); //encode all necessary string

                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result = "";
                String line="";
                while ((line = bufferedReader.readLine()) != null){ //fetch the result
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect(); //close connections
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
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
        if (type.equals("login")) {
            if (result.equals("Incorrect credentials, please try again") == true) { //user has entered an incorrect login
                alertDialog.setMessage("Incorrect credentials, please try again");
                alertDialog.show();
            }
            else {

                Intent intent = new Intent(context, Account.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                intent.putExtra("EXTRA_SESSION_ID", result); //login correct redirect the user
                context.startActivity(intent);
            }
        }
        if (type.equals("signup")){
            if (result.equals("Success")){ //user has entered correct criteria
                String newbie = "true";
                alertDialog.setMessage("Account Created Successfully");
                alertDialog.show();
                Intent intent = new Intent(context, Account.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                try {

                    JSONObject pack=new JSONObject();

                    pack.put("doctor", Doctor);
                    pack.put("email", Email);
                    pack.put("json", arr);
                    intent.putExtra("EXTRA_SESSION_ID", pack.toString()); //pack the relevent details into JSON for new activity
                    intent.putExtra("newAccount", newbie);
                    context.startActivity(intent);
                }
                catch(Exception e){

                }
            }
            else{
                alertDialog.setMessage(result.toString());
                alertDialog.show(); //user has entered incorrect details
            }
        }

    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
