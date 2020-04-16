<?php
//Project: Honours Project 2020
//Author: Aaron Stones
//Date: 10/04/2020
//Purpose: to show the benefits of collecting lots of data about a patient
//using different devices and tests

include("../Model/Advice.php");

function getAdvice($email){

    return adviceDatabase($email); //get advoce for the medical practitioners 
}

?>