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


public class TestResultsWorker extends AsyncTask<String,Void,String>{

    Context context;
    String Email;
    String type;
    String results;
    TestResultsWorker(Context ctx){

        context = ctx;
    }

    protected String doInBackground(String... params) {
        type = params[0];
        if (type.equals("getResults")){
            Log.d("test", "test1");
            String urlAdvice = "https://mayar.abertay.ac.uk/~1600964/Honours-Project/Android/APIs/Controller/getResults.php";
            Email = params[1];
            try{
                URL url = new URL(urlAdvice);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod(("POST"));
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("email", "UTF-8")+"="+ URLEncoder.encode(Email, "UTF-8");

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

    }

    @Override
    protected void onPostExecute(String result) {
        JSONArray obj = null;
        if (type.equals("getResults")) {
            try
            {
                obj = new JSONArray(result);

            }
            catch (JSONException e)
            {
                Log.d("failed","json parser failed");
            }
            List<String> Result = new ArrayList<String>(obj.length());
            List<String> Time = new ArrayList<String>(obj.length());

            for (int i = 0; i < obj.length(); ++i) {
                try {
                    JSONObject o = obj.getJSONObject(i);

                    Result.add((String) (o.getString("result")));
                    Time.add((String) (o.getString("time")));
                }
                catch (JSONException e) {
                }

            }
            for (int i = 0; i< obj.length();i++){
                results += Email + ": " + Result.get(i) + "\t" + Time.get(i) + "\n\n";

            }
            TextView txtView = (TextView) ((Activity)context).findViewById(R.id.textView17);
            txtView.setText(results.substring(4));



        }
    }


    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
