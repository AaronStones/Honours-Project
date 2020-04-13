package com.example.honoursproject;

import android.app.AlertDialog;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.util.regex.Pattern;

public class FormVerfication {

    Context context;


    FormVerfication(Context ctx){

        context = ctx;
    }


    public Boolean simpleCheck(String value){
        Pattern regex = Pattern.compile("[$&+,:;=\\\\?@#|/'<>.^*()%!-]");

        if (regex.matcher(value).find()) {
            Log.e("TTT", "SPECIAL CHARS FOUND");
            return true;
        }
        else{
            return false;
        }
    }

    public Boolean stringCheck(String value){

        Pattern regex = Pattern.compile("[0-9]");

        if (regex.matcher(value).find()) {
            Log.e("TTT", "SPECIAL CHARS FOUND");
            return true;
        }
        else{
            return false;
        }
    }

    public Boolean intCheck(String value){
        Pattern regex = Pattern.compile("[a-zA-Z]");

        if (regex.matcher(value).find()) {
            Log.e("TTT", "SPECIAL CHARS FOUND");
            return true;
        }
        else{
            return false;
        }
    }

    public Boolean mobileCheck(String value){
        if (value.length() != 11 || intCheck(value) == true){

            errorFormat();

            return true;
        }
        else return false;
    }

    public Boolean nameCheck(String value){
        if (stringCheck(value) == true){

            errorFormat();

            return true;
        }
        else return false;
    }



    public void error(){
        Toast.makeText(context, "Error in your data entry",Toast.LENGTH_SHORT).show();

    }

    public void errorFormat(){
        Toast.makeText(context, "Error in the format of your data",Toast.LENGTH_SHORT).show();

    }

}
