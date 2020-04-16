<?php
//Project: Honours Project 2020
//Author: Aaron Stones
//Date: 10/04/2020
//Purpose: to show the benefits of collecting lots of data about a patient
//using different devices and tests

include("../Model/Advice.php");

$check = null;

$email = $_POST["email"];

echo adviceDatabase($email); //returns all the advice for that patient based in their email

?>