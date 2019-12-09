<?php

$advice = $_POST["advice"];

$docName = $_POST["docName"];

$patientEmail = $_POST["patientEmail"];

include("../Model/Advice.php");

$response = sendAdvice($patientEmail, $docName, $advice);

echo $response;

?>