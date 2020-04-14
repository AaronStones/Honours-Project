<?php


include("../Model/Readings.php");

function getReadings($email){ //get a patients handshake result
    $check = null;

    return retrieveResults($email);
}

function getWeight($email){ //get a patients weight result
    $check = null;

    return retrieveWeight($email);
}
 
function getBPM($email){ //get a patients blood pressure result
    $check = null;

    return retrieveBPM($email);
}

function getTemp($email){ //get a patients temperature result
    $check = null;

    return retrieveTemp($email);
}

function getSys($email){ //get a patients systolic bp result
    $check = null;

    return retrieveSys($email);
}

function getDys($email){ //get a patients dstolic bp result
    $check = null;

    return retrieveDys($email);
}

?>