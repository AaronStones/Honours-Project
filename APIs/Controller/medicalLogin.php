<?php
//Project: Honours Project 2020
//Author: Aaron Stones
//Date: 10/04/2020
//Purpose: to show the benefits of collecting lots of data about a patient
//using different devices and tests

include("../Model/User.php");

function LoginMedical($email, $password){ //login functionality for medical professionals


    $check = LoginM($email, $password);

    if ($check != null){
        return $check;
    }
    else {
        return null;
    }
}


?>

