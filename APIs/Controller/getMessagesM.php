<?php
ini_set("display_errors", 1);
error_reporting(E_ALL);

include("../Model/Messages.php");
function getMessages($email){
    $check = null;

    return retrieveMessages($email);
}

?>