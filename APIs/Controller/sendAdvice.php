<?php
//Project: Honours Project 2020
//Author: Aaron Stones
//Date: 10/04/2020
//Purpose: to show the benefits of collecting lots of data about a patient
//using different devices and tests


$advice = $_POST["advice"];
$docName = $_POST["docName"];
$patientEmail = $_POST["patientEmail"]; //recieve the mobile data

include("../Model/Advice.php");

$response = sendAdvice($patientEmail, $docName, $advice); //post advice to the server

echo $response;

?>