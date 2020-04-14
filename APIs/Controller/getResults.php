<?php
include("../Model/Readings.php");

$check = null;

$email = $_POST["email"];

echo retrieveAll($email); //retrieve all the results from different patient tests

?>