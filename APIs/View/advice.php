<!DOCTYPE html>
<?php
session_start();
if (isset($_SESSION['userData']) == false){
    header('Location: '.'https://mayar.abertay.ac.uk/~1600964/Honours-Project/Android/APIs/View/login.php');
}
?>
<html lang="en">
<head>
    <link rel="stylesheet" type="text/css" href="https://mayar.abertay.ac.uk/~1600964/Honours-Project/View/css/index.css">
    <?php include("templates/header.php"); ?>
</head>
    <body>
        <?php include("templates/nav.php"); ?>

            <div class="header">
                <a href="https://mayar.abertay.ac.uk/~1600964/Honours-Project/Android/APIs/View/index"><h1>Patient Advice</h1></a>
                <h3><a href="https://mayar.abertay.ac.uk/~1600964/Honours-Project/Android/APIs/View/results">View Patient Results</a> | <a href="https://mayar.abertay.ac.uk/~1600964/Honours-Project/Android/APIs/View/landing">Home</a> | <a href="https://mayar.abertay.ac.uk/~1600964/Honours-Project/Android/APIs/View/messenger">Patient Chats</a></h3>
            </div>

            <h4>Select a patient to give advice</h4>
            <form method="post">

                <label for="email">Email</label>
            <input class="form-control" type="text" placeholder="Enter Email" title="Please ensure the email address is valid." name="email" required>   
            <button id="submit" type="submit" class="form-control btn btn-success mt-3">Select Patient</button>

            </form>
            <?php
            if ($_SERVER["REQUEST_METHOD"] == "POST") { //get user data

                include("../Controller/getAdviceM.php");
                $email = $_POST['email'];
                $_SESSION['email'] = $_POST['email'];
                $advice = getAdvice($_SESSION['email']);
                $advice = json_decode($advice);

                if ($advice == null){
                  echo "Invalid Entry";
                }
                
                else {
                    $_SESSION['setAdvice'] = true;
                    for ($i=0; $i<sizeof($advice);$i++){
                        echo $advice[$i]->advice . "  " . $advice[$i]->time . "</br></br>";
                    }
                }
                if (isset($_SESSION['setAdvice'])){
                    $var = json_decode($_SESSION['userData']);

                    echo "<form method=post id='adviceSend'>
    
                        <label for=email>Enter Advice</label>
                        <input class='form-control' type='text' placeholder='Enter Advice' name='advice' required>   
                        <input name=docName type=hidden value='" . $var->name . "'>
                        <input name=patientEmail type=hidden value='" . $email . "'>
                        <button id='postMessage' type='submit' class='form-control btn btn-success mt-3'>Send Advice</button>
        
                        </form>
                    ";
                }
            }
            ?>

            <script>
                $("#postMessage").click(function(){        
                    $.post("../Controller/sendAdvice.php", $("#adviceSend").serialize(),function(data){
                        alert(data);
                    });
                });
            </script>
        <?php include("templates/footer.php"); ?>
    </body>
</html>