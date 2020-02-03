<!DOCTYPE html>
<?php
session_start();
if (isset($_SESSION['userData']) == false){
    header('Location: '.'https://mayar.abertay.ac.uk/~1600964/Honours-Project/Android/APIs/View/login.php');
}
$data = json_decode($_SESSION['userData']);
$email = $data->email;
$name = $data->name;

?>
<html lang="en">
<head>
    <link rel="stylesheet" type="text/css" href="https://mayar.abertay.ac.uk/~1600964/Honours-Project/Android/APIs/View/css/index.css">
    <?php include("templates/header.php"); ?>
</head>
    <body>
        <?php include("templates/nav.php"); ?>

            <div class="header">
                <a href="https://mayar.abertay.ac.uk/~1600964/Honours-Project/Android/APIs/View/index"><h1>Patient Messenger</h1></a>
                <h2>Dr <?=$name?></h2>
                <h3><a href="https://mayar.abertay.ac.uk/~1600964/Honours-Project/Android/APIs/View/results.php">View Patient Results</a> | <a href="https://mayar.abertay.ac.uk/~1600964/Honours-Project/Android/APIs/View/advice.php">Give Advice to Patients</a> | <a href="https://mayar.abertay.ac.uk/~1600964/Honours-Project/Android/APIs/View/landing.php">Home</a></h3>
            </div>
            <h4>Select a patient to message</h4>
            <form method="post">

                <label for="email">Email</label>
            <input class="form-control" type="text" placeholder="Enter Email" title="Please ensure the email address is valid." name="email" required>   
            <button id="submit" type="submit" class="form-control btn btn-danger mt-3">Select Patient</button>

            </form>
            <?php
            if ($_SERVER["REQUEST_METHOD"] == "POST") { //get user data

                include("../Controller/getMessagesM.php");
                
                $email = $_POST['email']; // required
              
                $message = getMessages($_POST['email']);
                $message = json_decode($message);

                if ($message == null){
                  echo "Invalid Entry";
                }
                
                else {
                    for ($i=0; $i<sizeof($message);$i++){
                        if ($message[$i]->doctor == 1){
                            echo "<b>Dr " . $name . ": </b>" . $message[$i]->message . "     " . $message[$i]->time . "</br></br>";
                        }
                        else{
                            echo "<b>Patient: </b>" . $message[$i]->message . "     " . $message[$i]->time . "</br></br>";
                        }
                    }
                }
            }

            if (isset($message)){
                echo "<form method=post>

                <label for=email>Enter Message</label>
            <input class='form-control' type='text' placeholder='Enter Message' name='message' required>   
            <button id='submit' type='submit' class='form-control btn btn-danger mt-3'>Send Message</button>

            </form>
                ";
            }

            ?>
        <?php include("templates/footer.php"); ?>
    </body>
</html>