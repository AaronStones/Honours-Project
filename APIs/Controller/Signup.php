<?php
//Project: Honours Project 2020
//Author: Aaron Stones
//Date: 10/04/2020
//Purpose: to show the benefits of collecting lots of data about a patient
//using different devices and tests

include("../Model/User.php");

$check = null;
$email = $_POST["user_name"];
$password = $_POST["password"];
$json = $_POST["json"];
$doctor = $_POST["doctor"];

$checkDoctor = doctorCheck($doctor);

if ($checkDoctor != 1){
    echo "This doctor does not exist";
}

$emailCheck = emailCheck($email);

if ($emailCheck == 1){
    echo "This email has already been taken";
}

else{
    $check = Signup($email, $password, $json, $doctor);
    echo $check;
}

?>