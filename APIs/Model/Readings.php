<?php
//Project: Honours Project 2020
//Author: Aaron Stones
//Date: 10/04/2020
//Purpose: to show the benefits of collecting lots of data about a patient
//using different devices and tests

	include("conn.php");
	$db = new dbObj();
	$conn =  $db->getConnstring();


    function recordReading($email, $doctor, $count, $weight, $temp, $hr, $dys, $sys){ //store a reading indexed by a user's email
        global $conn;
        $value = 0;
        $sql = $conn->prepare("INSERT INTO Results (email, Doctor, Result, HR, Weight, Temperature, Sys, Dys) Values(?,?,?,?,?,?,?,?)");
        $sql->bind_param("ssiiiiii", $email, $doctor, $count, $hr, $weight, $temp, $sys, $dys);
        $sql->execute();

        $affectedRows = $sql->affected_rows;

            if ($affectedRows == 1) {
                return "Test Complete!";
            } 
            else {
                return null;
            }
    }

    function retrieveResults($email){ //get all the handshake results from a table based on the user's email
        global $conn;
        $sql = $conn->prepare("SELECT * from Results where email=? ORDER BY Timestamp DESC");
        $sql->bind_param("s", $email);
        $sql->execute();
        $sql->bind_result($email, $doctor, $Result, $BPM, $weight, $Temp, $Sys, $Dys, $time);

        $count = 0;
        while ($sql->fetch()) {
            $json[$count] =  array(
                'y' => $Result,
                'label' => $time);
            $count++;
        }
        return json_encode($json);
    }

    function retrieveAll($email){ //retrieve all the results from a server 
        global $conn;
        $sql = $conn->prepare("SELECT * from Results where email=? ORDER BY Timestamp DESC");
        $sql->bind_param("s", $email);
        $sql->execute();
        $sql->bind_result($email, $doctor, $Result, $BPM, $weight, $Temp, $Sys, $Dys, $time);

        $count = 0;
        while ($sql->fetch()) {
            $json[$count] =  array(
                'result' => $Result,
                'hr' => $BPM,
                'weight' => $weight,
                'temp' => $Temp,
                'sys' => $Sys,
                'dys' => $Dys,
                'time' => $time);
            $count++;
        }
        return json_encode($json);
    }

    function retrieveBPM($email){ //retrieve all the heart rate measures
        global $conn;
        $sql = $conn->prepare("SELECT * from Results where email=? ORDER BY Timestamp DESC");
        $sql->bind_param("s", $email);
        $sql->execute();
        $sql->bind_result($email, $doctor, $Result, $BPM, $weight, $Temp, $Sys, $Dys, $time);

        $count = 0;
        while ($sql->fetch()) {
            $json[$count] =  array(
                'y' => $BPM,
                'label' => $time);
            $count++;
        }
        return json_encode($json);
    }

    function retrieveWeight($email){ //retrieve all the weight measures
        global $conn;
        $sql = $conn->prepare("SELECT * from Results where email=? ORDER BY Timestamp DESC");
        $sql->bind_param("s", $email);
        $sql->execute();
        $sql->bind_result($email, $doctor, $Result, $BPM, $weight, $Temp, $Sys, $Dys, $time);

        $count = 0;
        while ($sql->fetch()) {
            $json[$count] =  array(
                'y' => $weight,
                'label' => $time);
            $count++;
        }
        return json_encode($json);
    }

    function retrieveTemp($email){ //retrieve all the temperature measures
        global $conn;
        $sql = $conn->prepare("SELECT * from Results where email=? ORDER BY Timestamp DESC");
        $sql->bind_param("s", $email);
        $sql->execute();
        $sql->bind_result($email, $doctor, $Result, $BPM, $weight, $Temp, $Sys, $Dys, $time);

        $count = 0;
        while ($sql->fetch()) {
            $json[$count] =  array(
                'y' => $Temp,
                'label' => $time);
            $count++;
        }
        return json_encode($json);
    }

    function retrieveSys($email){ //retireve all the systolic bp readings 
        global $conn;
        $sql = $conn->prepare("SELECT * from Results where email=? ORDER BY Timestamp DESC");
        $sql->bind_param("s", $email);
        $sql->execute();
        $sql->bind_result($email, $doctor, $Result, $BPM, $weight, $Temp, $Sys, $Dys, $time);

        $count = 0;
        while ($sql->fetch()) {
            $json[$count] =  array(
                'y' => $Sys,
                'label' => $time);
            $count++;
        }
        return json_encode($json);
    }
    function retrieveDys($email){ //retireve all the dystolic bp readings 
        global $conn;
        $sql = $conn->prepare("SELECT * from Results where email=? ORDER BY Timestamp DESC");
        $sql->bind_param("s", $email);
        $sql->execute();
        $sql->bind_result($email, $doctor, $Result, $BPM, $weight, $Temp, $Sys, $Dys, $time);

        $count = 0;
        while ($sql->fetch()) {
            $json[$count] =  array(
                'y' => $Dys,
                'label' => $time);
            $count++;
        }
        return json_encode($json);
    }

?>