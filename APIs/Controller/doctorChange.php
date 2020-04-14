<?php
ini_set("display_errors", 1);
error_reporting(E_ALL);

include("../Model/User.php");

$check = null;

$email = $_POST["email"];
$doctor = $_POST["doctor"];

$checkDoctor = doctorCheck($doctor); //check the doctor exists

if ($checkDoctor != 1){
    echo "This doctor does not exist";
}

else {

    $check = doctorChange($email, $doctor); //doctor exists proceed with the change
    echo $check;
}
?>