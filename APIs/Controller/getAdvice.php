<?php
ini_set("display_errors", 1);
error_reporting(E_ALL);

include("../Model/Advice.php");

$check = null;

$email = $_POST["email"];

echo adviceDatabase($email);
?>