<?php
//Project: Honours Project 2020
//Author: Aaron Stones
//Date: 10/04/2020
//Purpose: to show the benefits of collecting lots of data about a patient
//using different devices and tests


    $email = $_GET["email"];
    $count = $_GET["count"];
    $weight = $_GET["weight"];
    $temp = $_GET["temperature"];
    $hr = $_GET["hr"];
    $dys = $_GET["dys"];
    $sys = $_GET["sys"];
    $doctor = $_GET["doctor"]; //recieve data from iot device

    
    include("../Model/Readings.php");

    if(!is_numeric($weight) && !is_numeric($temp) && !is_numeric($count)  && !is_numeric($hr)  && !is_numeric($dys)  && !is_numeric($sys)){  // return **TRUE** if it is numeric
        echo "Inavlid Inputs";
    }

    else{
        recordReading($email, $doctor, $count, $weight, $temp, $hr, $dys, $sys);
    }



?>