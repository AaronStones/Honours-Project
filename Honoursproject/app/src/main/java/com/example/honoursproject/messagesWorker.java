package com.example.honoursproject;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.util.Size;
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

public class messagesWorker extends AsyncTask<String,Void,String> {

    Context context;
    String Email;
    String type;
    String message;
    String docName;

    messagesWorker(Context ctx){

        context = ctx;
    }

    protected String doInBackground(String... params) {
        type = params[0];
        if (params[0].equals("getMessages")){
            docName = params[2];
            String urlAdvice = "https://mayar.abertay.ac.uk/~1600964/Honours-Project/Android/APIs/Controller/getMessages.php";
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

        if (params[0].equals("sendMessage")){
            String Message = params[2];
            String urlAdvice = "https://mayar.abertay.ac.uk/~1600964/Honours-Project/Android/APIs/Controller/sendMessage.php";
            Email = params[1];
            try{
                URL url = new URL(urlAdvice);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod(("POST"));
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("email", "UTF-8")+"="+ URLEncoder.encode(Email, "UTF-8")+"&"
                        +URLEncoder.encode("message","UTF-8")+"="+URLEncoder.encode(Message,"UTF-8");

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
        TextView txtView = (TextView) ((Activity)context).findViewById(R.id.textView12);
        JSONArray obj = null;
        if (type.equals("getMessages")) {
            try
            {
                obj = new JSONArray(result);

            }
            catch (JSONException e)
            {
                Log.d("failed","json parser failed");
            }
            List<String> Message = new ArrayList<String>(obj.length());
            List<Integer> Doctor = new ArrayList<Integer>(obj.length());
            List<String> Time = new ArrayList<String>(obj.length());

            for (int i = 0; i < obj.length(); ++i) {
                try {
                    JSONObject o = obj.getJSONObject(i);
                    Message.add((String) (o.getString("message")));
                    Doctor.add((Integer) (o.getInt("doctor")));
                    Time.add((String) (o.getString("time")));
                }
                catch (JSONException e) {
                }

            }
            for (int i = 0; i< obj.length();i++){

                if (Doctor.get(i) == 1){
                    message = "\t\t\t\t\t\tDr: " + Message.get(i) + "\n\n\n";
                    SpannableStringBuilder ssb = new SpannableStringBuilder(message);

                    ForegroundColorSpan fcsRed = new ForegroundColorSpan(Color.WHITE);
                    BackgroundColorSpan fcsBlue = new BackgroundColorSpan(Color.BLUE);

                    ssb.setSpan(fcsRed, 0, message.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    ssb.setSpan(fcsBlue, 0, message.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

                    txtView.append(ssb);
                }
                else{
                    message = "\t" + Message.get(i) + "\t\n\n";

                    SpannableStringBuilder ssb = new SpannableStringBuilder(message);

                    ForegroundColorSpan fcsBlue = new ForegroundColorSpan(Color.WHITE);
                    BackgroundColorSpan fcsGreen = new BackgroundColorSpan(Color.GREEN);

                    ssb.setSpan(fcsBlue, 0, message.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    ssb.setSpan(fcsGreen, 0, message.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);


                    txtView.append(ssb);
                }

            }



        }
    }


    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}


