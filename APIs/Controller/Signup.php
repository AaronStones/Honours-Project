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

    $error_message = "";
    $email_exp = '/^[A-Za-z0-9._%-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,4}$/';

    if (!preg_match($email_exp, $email))
        $error_message .= 'The Email Address you entered does not appear to be valid.<br />';
    if (strlen($password) < 8)
        $error_message .= 'You have entered too weak a password, please ensure the password is 8 characters and contains at least one letter and number. <br />';
    if (!preg_match("#[0-9]+#", $password))
        $error_message .= 'Password must include at least one number. <br />';
    if (!preg_match("#[a-zA-Z]+#", $password))
         $error_message .= 'Password must include at least one letter. <br />'; 

    if(strlen($error_message) > 0){
        echo $error_message;
    }

    else{
        $check = Signup($email, $password, $json, $doctor);
        echo $check;
    }

?>