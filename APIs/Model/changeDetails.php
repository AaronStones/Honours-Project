<?php
	include("../Model/conn.php");
	$db = new dbObj();
	$conn =  $db->getConnstring();

    
function Name($email, $file) //function to validate a user's login details
{
    echo $email;
    
    global $conn;
    $sql = $conn->prepare('UPDATE Patients SET jsonData=? WHERE email=?');
    $sql->bind_param('ss', $email, $file);
    $sql->execute();

    if ($sql->affected_rows > 0) {
        return "success";
    }

    else {
        return "fail";
    }
}


?>