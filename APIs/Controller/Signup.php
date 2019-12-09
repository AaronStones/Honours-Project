<?php


include("../Model/User.php");

$check = null;
$email = $_POST["user_name"];
$password = $_POST["password"];
$json = $_POST["json"];
$doctor = $_POST["doctor"];

$check = Signup($email, $password, $json, $doctor);
echo $check;


?>