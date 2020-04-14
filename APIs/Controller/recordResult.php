<?php


include("../Model/Readings.php");

$check = null;

$email = $_POST["email"];
$count = $_POST["count"];
$weight = $_POST["weight"];
$temp = $_POST["temperature"];
$hr = $_POST["hr"];
$dys = $_POST["dys"];
$sys = $_POST["sys"];
$doctor = $_POST["doctor"]; //recieve data from mobile device

$check = recordReading($email, $doctor, $count, $weight, $temp, $hr, $dys, $sys); //record the result
echo $check;

?>