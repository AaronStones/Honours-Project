<?php


include("../Model/Advice.php");

function getAdvice($email){

    return adviceDatabase($email); //get advoce for the medical practitioners 
}

?>