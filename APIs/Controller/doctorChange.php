<?php
//Project: Honours Project 2020
//Author: Aaron Stones
//Date: 10/04/2020
//Purpose: to show the benefits of collecting lots of data about a patient
//using different devices and tests

include("../Model/User.php");

$check = null;

$email = $_POST["email"];
$doctor = $_POST["doctor"];

$checkDoctor = doctorCheck($doctor); //check the doctor exists

if ($checkDoctor != 1){
    echo "This doctor does not exist";
}

else {

    $check = doctorChange($email, $doctor); //doctor exists proceed with the change
    echo $check;
}
?>