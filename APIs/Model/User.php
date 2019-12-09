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
                'doctor' => $doctor,
                'time' => $time));
        }
        else {
            return "Incorrect credentials, please try again";
        }
    }
}

function LoginM($email, $pass){
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
                'name' => $name));
        }
        else {
            return "Incorrect credentials, please try again";
        }
    }
}

function Name($email, $file) //function to validate a user's login details
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

function Signup($email, $password, $json, $doctor){

    $checkEmail = checkEmail($email);
    if ($checkEmail == true){
        return "Account present";
    }
    else{
        $passwordHash = password_hash($password, PASSWORD_DEFAULT);
        global $conn;
        $sql = $conn->prepare("INSERT INTO Patients (email, password, JSON, Doctor) Values(?,?,?,?)");
        $sql->bind_param("ssss", $email, $passwordHash, $json, $doctor);
        $sql->execute();

        $affectedRows = $sql->affected_rows;
        if ($affectedRows == 1) {
            return "Success";
        } 
        else {
            return null;
        }
    }

}

function checkEmail($email){
    global $conn;
    $sql = $conn->prepare("SELECT Primary_Key from Patients where email=? LIMIT 1");
    $sql->bind_param("s", $email);

    if (mysqli_num_rows($sql->execute()) > 0) {
        return true;
    }

}

function doctorChange($email, $doctor){
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

    
    function registerUser($email, $password){
            global $conn;
; 
      
     
            $passwordHash = password_hash($password, PASSWORD_DEFAULT);

            global $conn;

            $name = "Aaron Stones";

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