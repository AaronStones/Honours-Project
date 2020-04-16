<?php
//Project: Honours Project 2020
//Author: Aaron Stones
//Date: 10/04/2020
//Purpose: to show the benefits of collecting lots of data about a patient
//using different devices and tests

include("../Model/Messages.php");

function getMessages($email){
    $check = null;

    return retrieveMessages($email); //retrieve all the messages between a doctor and a patient based on that email
}

?>