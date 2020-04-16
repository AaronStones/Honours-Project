package com.example.honoursproject;

import android.app.AlertDialog;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.util.regex.Pattern;

public class FormVerfication {

    Context context; //get the context


    FormVerfication(Context ctx){

        context = ctx;
    }


    public Boolean simpleCheck(String value){ //checks for special characters
        Pattern regex = Pattern.compile("[$&+,:;=\\\\?@#|/'<>.^*()%!-]");

        if (regex.matcher(value).find()) {
            Log.e("TTT", "SPECIAL CHARS FOUND");
            return true;
        }
        else{
            return false;
        }
    }

    public Boolean stringCheck(String value){ //checks for integer characters

        Pattern regex = Pattern.compile("[0-9]");

        if (regex.matcher(value).find()) {
            Log.e("TTT", "SPECIAL CHARS FOUND");
            return true;
        }
        else{
            return false;
        }
    }

    public Boolean intCheck(String value){ //checks for string characters
        Pattern regex = Pattern.compile("[a-zA-Z]");

        if (regex.matcher(value).find()) {
            Log.e("TTT", "SPECIAL CHARS FOUND");
            return true;
        }
        else{
            return false;
        }
    }

    public Boolean mobileCheck(String value){ //checks for a correct mobile number
        if (value.length() != 11 || intCheck(value)){

            errorFormat();

            return true;
        }
        else return false;
    }

    public void error(){ //general error messages
        Toast.makeText(context, "Error in your data entry",Toast.LENGTH_SHORT).show();

    }

    public void errorFormat(){ //incorrect format error message
        Toast.makeText(context, "Error in the format of your data",Toast.LENGTH_SHORT).show();

    }

}
