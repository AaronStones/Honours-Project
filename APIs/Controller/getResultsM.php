<?php
ini_set("display_errors", 1);
error_reporting(E_ALL);

include("../Model/Readings.php");

function getReadings($email){
    $check = null;

    return retrieveResults($email);
}

?>