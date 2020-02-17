<?php
ini_set("display_errors", 1);
error_reporting(E_ALL);

include("../Model/Readings.php");

function getReadings($email){
    $check = null;

    return retrieveResults($email);
}

function getWeight($email){
    $check = null;

    return retrieveWeight($email);
}

function getBPM($email){
    $check = null;

    return retrieveBPM($email);
}

function getTemp($email){
    $check = null;

    return retrieveTemp($email);
}

function getSys($email){
    $check = null;

    return retrieveSys($email);
}

function getDys($email){
    $check = null;

    return retrieveDys($email);
}

?>