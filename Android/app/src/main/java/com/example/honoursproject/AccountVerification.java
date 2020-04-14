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
    String Doctor;

    AccountVerification(Context ctx){

        context = ctx;
    }
    @Override
    protected String doInBackground(String... params) {
        type = params[0];
        String login_url = "https://mayar.abertay.ac.uk/~1600964/Honours-Project/Android/APIs/Controller/Login.php";
        String signup_url = "https://mayar.abertay.ac.uk/~1600964/Honours-Project/Android/APIs/Controller/Signup.php";
        if (type.equals("login")){
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
                        +URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(Password,"UTF-8");
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
        if (type.equals("signup")){
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

                String json = arr.toString();


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

                        +URLEncoder.encode("doctor","UTF-8")+"="+URLEncoder.encode(Doctor,"UTF-8");

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
            if (result.equals("Incorrect credentials, please try again") == true) {
                alertDialog.setMessage("Incorrect credentials, please try again");
                alertDialog.show();
            }
            else {

                Intent intent = new Intent(context, Account.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                intent.putExtra("EXTRA_SESSION_ID", result);
                context.startActivity(intent);
            }
        }
        if (type.equals("signup")){
            if (result.equals("Success")){
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
                    intent.putExtra("EXTRA_SESSION_ID", pack.toString());
                    intent.putExtra("newAccount", newbie);
                    context.startActivity(intent);
                }
                catch(Exception e){

                }
            }
            else{
                alertDialog.setMessage(result.toString());
                alertDialog.show();
            }
        }

    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
