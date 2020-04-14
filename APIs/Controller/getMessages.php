<?php

include("../Model/Messages.php");

$check = null;

$email = $_POST["email"];

echo retrieveMessages($email); //retrieve all the messages between a doctor and a patient based on that email

?>