<?php
//Project: Honours Project 2020
//Author: Aaron Stones
//Date: 10/04/2020
//Purpose: to show the benefits of collecting lots of data about a patient
//using different devices and tests

	include("../Model/conn.php");
	$db = new dbObj();
	$conn =  $db->getConnstring();

    
function retrieveMessages($email) //retrieve all the messages in ascending order based on a user's email
{
    global $conn;
    $sql = $conn->prepare("SELECT * from Messages where email=? ORDER BY Timestamp ASC");
    $sql->bind_param("s", $email);
    $sql->execute();
    $sql->bind_result($email, $doctor, $message, $time);

    $count = 0;
    while ($sql->fetch()) {
        $json[$count] =  array(
            'message' => $message,
            'doctor' => $doctor,
            'time' => $time);
        $count++;
    }
    return json_encode($json);
    $conn->close;


}

function sendMessageP($email, $message){ //send a messade to the database to be later processed by the server
    global $conn;
    $value = 0;
    $sql = $conn->prepare("INSERT INTO Messages (email, Doctor, Message) Values(?,?,?)");
    $sql->bind_param("sis", $email, $value, $message);
    $sql->execute();

    $affectedRows = $sql->affected_rows;

    if ($affectedRows == 1) {
        return "Success";
    } 
    else {
        return null;
    }

    $conn->close;

}

?>