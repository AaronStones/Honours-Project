<?php

$advice = $_POST["advice"];
$docName = $_POST["docName"];
$patientEmail = $_POST["patientEmail"]; //recieve the mobile data

include("../Model/Advice.php");

$response = sendAdvice($patientEmail, $docName, $advice); //post advice to the server

echo $response;

?>