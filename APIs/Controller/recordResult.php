<?php
ini_set("display_errors", 1);
error_reporting(E_ALL);

include("../Model/Readings.php");

$check = null;

$email = $_POST["email"];
$result = $_POST["result"];
$doctor = $_POST["doctor"];

$check = recordReading($email, $result, $doctor);
echo $check;

?>