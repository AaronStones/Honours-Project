package com.example.honoursproject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

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
    String type2;
    String results;
    LineChart lineChart;

    List<String> Result;
    List<String> Time;
    List<String> HR ;
    List<String> Weight ;
    List<String> Temp ;
    List<String> Sys ;
    List<String> Dys;


    TestResultsWorker(Context ctx){

        context = ctx;
    }

    protected String doInBackground(String... params) {
        type = params[0];
        type2 = params[1];
        if (type.equals("getResults")){
            String urlAdvice = "https://mayar.abertay.ac.uk/~1600964/Honours-Project/Android/APIs/Controller/getResults.php";
            Email = params[2];
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
        if (type2.equals("getResults")) {
            try
            {
                obj = new JSONArray(result);

            }
            catch (JSONException e)
            {
                Log.d("failed","json parser failed");
            }
            Result = new ArrayList<String>(obj.length());
            HR = new ArrayList<String>(obj.length());
            Weight = new ArrayList<String>(obj.length());
            Temp = new ArrayList<String>(obj.length());
            Sys = new ArrayList<String>(obj.length());
            Dys = new ArrayList<String>(obj.length());
            Time = new ArrayList<String>(obj.length());

            for (int i = 0; i < obj.length(); ++i) {
                try {
                    JSONObject o = obj.getJSONObject(i);

                    Result.add((String) (o.getString("result")));
                    HR.add((String) (o.getString("hr")));
                    Weight.add((String) (o.getString("weight")));
                    Temp.add((String) (o.getString("temp")));
                    Sys.add((String) (o.getString("sys")));
                    Dys.add((String) (o.getString("dys")));
                    Time.add((String) (o.getString("time")));
                }
                catch (JSONException e) {
                }

            }

            setupMultiGraphs();



        }

        if (type2.equals("retrieveResults")) {
            try
            {
                obj = new JSONArray(result);

            }
            catch (JSONException e)
            {
                Log.d("failed","json parser failed");
            }
            Result = new ArrayList<String>(obj.length());
            List<String> HR = new ArrayList<String>(obj.length());
            List<String> Weight = new ArrayList<String>(obj.length());
            List<String> Temp = new ArrayList<String>(obj.length());
            List<String> Sys = new ArrayList<String>(obj.length());
            List<String> Dys = new ArrayList<String>(obj.length());
            Time = new ArrayList<String>(obj.length());

            for (int i = 0; i < obj.length(); ++i) {
                try {
                    JSONObject o = obj.getJSONObject(i);

                    Result.add((String) (o.getString("result")));
                    HR.add((String) (o.getString("hr")));
                    Weight.add((String) (o.getString("weight")));
                    Temp.add((String) (o.getString("temp")));
                    Sys.add((String) (o.getString("sys")));
                    Dys.add((String) (o.getString("dys")));
                    Time.add((String) (o.getString("time")));
                }
                catch (JSONException e) {
                }

            }
            LineChart lineChart = ((Activity)context).findViewById(R.id.lineChart);
            setupGraphs(lineChart, Result, "Hand Shake Test");


        }
    }

    private void setupMultiGraphs(){

        LineChart HeartR = ((Activity)context).findViewById(R.id.HeartR);
        LineChart Hand = ((Activity)context).findViewById(R.id.Hand);
        LineChart Weights = ((Activity)context).findViewById(R.id.Weight);
        LineChart Temperature = ((Activity)context).findViewById(R.id.Temp);
        LineChart Systolic = ((Activity)context).findViewById(R.id.Sys);
        LineChart Dystolic = ((Activity)context).findViewById(R.id.Dys);

        setupGraphs(HeartR, HR, "Heart Rate BPM");
        setupGraphs(Hand, Result, "Hand Shake Result");
        setupGraphs(Weights, Weight, "Weight Kg");
        setupGraphs(Temperature, Temp, "Temperature Degrees Celsius");
        setupGraphs(Systolic, Sys, "Systolic BP");
        setupGraphs(Dystolic, Dys, "Dystolic BP");




    }

    private void setupGraphs(LineChart linechart, List<String> list, String type){

        ArrayList<String> xAXES = new ArrayList<>();
        ArrayList<Entry> yAXEScos = new ArrayList<>();

        int numDataPoints = list.size();
        for(int i=0;i<numDataPoints;i++){
            Float cosFunction = Float.parseFloat(list.get(i));

            yAXEScos.add(new Entry(cosFunction,i));

            xAXES.add(i, Time.get(i));
        }


        String[] xaxes = new String[xAXES.size()];

        for(int i=0; i<xAXES.size();i++){
            xaxes[i] = String.valueOf(xAXES.get(i));
        }


        ArrayList<ILineDataSet> lineDataSets = new ArrayList<>();

        LineDataSet lineDataSet1 = new LineDataSet(yAXEScos,type);
        lineDataSet1.setDrawCircles(false);
        lineDataSet1.setColor(Color.BLUE);


        lineDataSets.add(lineDataSet1);


        linechart.setData(new LineData(xaxes, lineDataSets));

        linechart.setVisibleXRangeMaximum(65f);
    }


    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}


