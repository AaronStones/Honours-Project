<?php

include("../Model/Messages.php");

function getMessages($email){
    $check = null;

    return retrieveMessages($email); //retrieve all the messages between a doctor and a patient based on that email
}

?>