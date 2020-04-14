<?php


include("../Model/Advice.php");

$check = null;

$email = $_POST["email"];

echo adviceDatabase($email); //returns all the advice for that patient based in their email

?>