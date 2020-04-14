<?php
	include("../Model/conn.php");
	$db = new dbObj();
	$conn =  $db->getConnstring();

    
function Login($email, $pass) //function to validate a user's login details
{
    global $conn;
    $sql = $conn->prepare("SELECT * from Patients where email=? LIMIT 1");
    $sql->bind_param("s", $email);
    $sql->execute();
    $sql->bind_result($key, $email, $password, $json, $doctor, $time);

    if ($email == null){
        return "Incorrect credentials, please try again";
    }    
    while ($sql->fetch()) {
        if (password_verify($pass, $password) != false){
            return json_encode(array(
                'id' => $key,
                'email' => $email,
                'json' => json_decode($json),
                'doctor' => $doctor));
        }
        else {
            return "Incorrect credentials, please try again";
        }
    }

}

function retriveAccountResults($email){ //retrieves the results for the handshake test only 
        global $conn;
        $sql = $conn->prepare("SELECT * from Results where email=? ORDER BY Timestamp DESC");
        $sql->bind_param("s", $email);
        $sql->execute();
        $sql->bind_result($email, $doctor, $Result, $BPM, $weight, $Temp, $Sys, $Dys, $time);

        $count = 0;
        while ($sql->fetch()) {
            $array[$count] = array(
                'result' => $Result,
                'time' => $time);
            $count++;
        }
        return json_encode($array);
        
}

function LoginM($email, $pass){ //login section for the medical professionals
    global $conn;
    $sql = $conn->prepare("SELECT * from Medical where email=? LIMIT 1");
    $sql->bind_param("s", $email);
    $sql->execute();
    $sql->bind_result($email, $name, $password);

    if ($email == null){
        return "Incorrect credentials, please try again";
    }    

    while ($sql->fetch()) {
        if (password_verify($pass, $password) != false){
            return json_encode(array(
                'email' => $email,
                'name' => $name,
                'password' => $password));
        }
        else {
            return "Incorrect credentials, please try again";
        }
    }
}

function Name($email, $file) //function to update a user's name
{
    $file = json_encode($file);
    global $conn;
    $sql = $conn->prepare("UPDATE Patients SET jsonData=? WHERE email=?");
    $sql->bind_param("ss", $file, $email);
    $status = $sql->execute();

    if ($status === false) {
        return "fail";
    }

    else {
        return "success";
    }
}

function Signup($email, $password, $json, $doctor){ //sign up function 

    $passwordHash = password_hash($password, PASSWORD_DEFAULT);
    global $conn;
    $sql = $conn->prepare("INSERT INTO Patients (email, password, jsonData, Doctor) Values(?,?,?,?)");
    $sql->bind_param("ssss", $email, $passwordHash, $json, $doctor);
    $sql->execute();

    $affectedRows = $sql->affected_rows;
    if ($affectedRows == 1) {
        return "Success";
    } 
    else {
        return "fail";
    }
}



function checkEmail($email){ //a check to ensure the email has not been used prior to registrations
    global $conn;
    $sql = $conn->prepare("SELECT Primary_Key from Patients where email=? LIMIT 1");
    $sql->bind_param("s", $email);

    if (mysqli_num_rows($sql->execute()) > 0) {
        return true;
    }

}

function doctorChange($email, $doctor){ //function to change the user's doctor 
    global $conn;
    $sql = $conn->prepare("UPDATE Patients SET Doctor=? WHERE email=?");
    $sql->bind_param("ss", $doctor, $email);
    $status = $sql->execute();

    if ($status === false) {
        return "fail";
    }

    else {
        return "success";
    }
}

function doctorCheck($doctor){ //check to ensure the doctor exists 
    global $conn;
    $sql = $conn->prepare("SELECT * from Medical where name=?");
    $sql->bind_param("s", $doctor);
    $sql->execute();

    $count = 0;
    while ($sql->fetch()) {
        $count++;
    }
    if ($count > 0){
        return 1;
    }
    else{
        return 0;
    }
}

function emailCheck($email){ //check to see if the email is already in use 
    global $conn;
    $sql = $conn->prepare("SELECT * from Patients where email=?");
    $sql->bind_param("s", $email);
    $sql->execute();

    $count = 0;
    while ($sql->fetch()) {
        $count++;
    }
    if ($count > 0){
        return 1;
    }
    else{
        return 0;
    }
}

    
function registerUser($email, $password, $name){ //registers a medical professional
    global $conn;
    $passwordHash = password_hash($password, PASSWORD_DEFAULT);

    $sql = $conn->prepare("INSERT INTO Medical (email, name, password) Values(?,?,?)");
    $sql->bind_param("sss", $email, $name, $passwordHash);
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