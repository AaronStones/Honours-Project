<?php
	include("../Model/conn.php");
	$db = new dbObj();
	$conn =  $db->getConnstring();

    
function adviceDatabase($email) //function to validate a user's login details
{
    global $conn;
    $sql = $conn->prepare("SELECT * from Advice where email=? ORDER BY Timestamp DESC");
    $sql->bind_param("s", $email);
    $sql->execute();
    $sql->bind_result($email, $advice, $doctor, $time);

    $count = 0;
    while ($sql->fetch()) {
        $json[$count] =  array(
            'advice' => $advice,
            'doctor' => $doctor,
            'time' => $time);
        $count++;
    }
    return json_encode($json);

}

function sendAdvice($email, $name, $advice){
    global $conn;
    $sql = $conn->prepare("INSERT INTO Advice (email, Advice, Doctor) VALUES(?,?,?)");
    $sql->bind_param("sss", $email, $advice, $name);
    $sql->execute();
    
    $affectedRows = $sql->affected_rows;

    if ($affectedRows == 1) {
        return "Success";
    } 
    else {
        return null;
    }
}


?>