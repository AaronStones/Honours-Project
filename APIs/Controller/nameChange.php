<?php
//Project: Honours Project 2020
//Author: Aaron Stones
//Date: 10/04/2020
//Purpose: to show the benefits of collecting lots of data about a patient
//using different devices and tests

include("../Model/User.php");

$check = null;

$email = $_POST["email"];
$file = $_POST["file"]; //recieve data from mobile

$check = Name($email, $file); //change a user's name
echo $check;

?>