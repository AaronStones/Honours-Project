<?php
ini_set("display_errors", 1);
error_reporting(E_ALL);

function getWeight($email){
    $check = null;

    return retrieveWeight($email);
}

?>