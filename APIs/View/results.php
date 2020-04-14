<!DOCTYPE html>
<?php
session_start();
if (isset($_SESSION['userData']) == false){
    header('Location: '.'https://mayar.abertay.ac.uk/~1600964/Honours-Project/Android/APIs/View/login.php');
}
?>
<html lang="en">
<head>
    <link rel="stylesheet" type="text/css" href="https://mayar.abertay.ac.uk/~1600964/Honours-Project/Android/APIs/View/css/index">
    <?php include("templates/header.php"); ?>
</head>
    <body>
        <?php include("templates/nav.php"); ?>

            <div class="header">
                <a href="index"><h1>Patient Results</h1></a>
                <h3><a href="https://mayar.abertay.ac.uk/~1600964/Honours-Project/Android/APIs/View/landing">Home</a> | <a href="https://mayar.abertay.ac.uk/~1600964/Honours-Project/Android/APIs/View/advice" >Give Advice to Patients</a> | <a href="https://mayar.abertay.ac.uk/~1600964/Honours-Project/Android/APIs/View/messenger">Patient Chats</a></h3>
            </div>
            <h4>Select a patient to view their results</h4>
            <form method="post">

                <label for="email">Email</label>
            <input class="form-control" type="text" placeholder="Enter Email" title="Please ensure the email address is valid." name="email" required>   
            <button id="submit" type="submit" class="form-control btn btn-success mt-3">Select Patient</button> <!--Select a patient  -->

            </form>
            <?php
            if ($_SERVER["REQUEST_METHOD"] == "POST") { //get user data

                include("../Controller/getResultsM.php");
                
                $results = getReadings($_POST['email']);

                $heartRate = getBPM($_POST['email']);

                $weight = getWeight($_POST['email']);

                $temperature = getTemp($_POST['email']);

                $Sys = getSys($_POST['email']);

                $Dys = getDys($_POST['email']);


                if ($results == null){
                  echo "Invalid Entry";
                }
                
                else {
                    $dataPoints = json_decode($results);
                    $dataPoints2 = json_decode($heartRate);
                    $dataPoints3 = json_decode($Sys);
                    $dataPoints4 = json_decode($Dys);
                    $dataPoints5 = json_decode($weight);
                    $dataPoints6 = json_decode($temperature); //get all the different results
                }
            }

            ?>

            <script>
            window.onload = function () {
            
            var chart = new CanvasJS.Chart("chartContainer", {
                title: {
                    text: "Hand Shake test Results"
                },
                axisY: {
                    title: "Number of registered shakes"
                },
                data: [{
                    type: "line",
                    dataPoints: <?php echo json_encode($dataPoints, JSON_NUMERIC_CHECK); ?>
                }]
            });
            chart.render();

                var chart = new CanvasJS.Chart("chartContainer1", {
                title: {
                    text: "Heart Rate"
                },
                axisY: {
                    title: "Bpm (Beats per Minute)"
                },
                data: [{
                    type: "line",
                    dataPoints: <?php echo json_encode($dataPoints2, JSON_NUMERIC_CHECK); ?> //Canvas.JS functions for displaying graphs of data
                }]
            });
            chart.render();
            var chart = new CanvasJS.Chart("chartContainer2", {
                title: {
                    text: "Patient's Weight"
                },
                axisY: {
                    title: "Kilograms"
                },
                data: [{
                    type: "line",
                    dataPoints: <?php echo json_encode($dataPoints5, JSON_NUMERIC_CHECK); ?>
                }]
            });
            chart.render();

            var chart = new CanvasJS.Chart("chartContainer3", {
                title: {
                    text: "Patient's Temperature"
                },
                axisY: {
                    title: "Degrees Celsius"
                },
                data: [{
                    type: "line",
                    dataPoints: <?php echo json_encode($dataPoints6, JSON_NUMERIC_CHECK); ?>
                }]
            });
            chart.render();
            
            var chart = new CanvasJS.Chart("chartContainer4", {
                title: {
                    text: "Blood Pressure"
                },
                axisX: {
                    valueFormatString: "MMM YYYY"
                },
                axisY2: {
                    title: "Millimeters of Hg"
                },
                toolTip: {
                    shared: false
                },
                legend: {
                    cursor: "pointer",
                    verticalAlign: "top",
                    horizontalAlign: "center",
                    dockInsidePlotArea: true,
                    itemclick: toogleDataSeries
                },
                data: [{
                    type:"line",
                    axisYType: "primary",
                    name: "Systolic",
                    showInLegend: true,
                    markerSize: 6,
                    dataPoints: <?php echo json_encode($dataPoints3, JSON_NUMERIC_CHECK); ?>
                },
                {
                    type: "line",
                    axisYType: "primary",
                    name: "Dystolic",
                    showInLegend: true,
                    markerSize: 6,
                    dataPoints: <?php echo json_encode($dataPoints4, JSON_NUMERIC_CHECK); ?>
                }]
            });
            chart.render();

            function toogleDataSeries(e){
                if (typeof(e.dataSeries.visible) === "undefined" || e.dataSeries.visible) {
                    e.dataSeries.visible = false;
                } else{
                    e.dataSeries.visible = true;
                }
                chart.render();
            }
            }
            </script>
            </head>
            <body>
            <?php
            for ($i = 0; $i<5; $i++){
                if ($i == 0){
                    echo "<div id='chartContainer' style='height: 370px; width: 100%;'></div>";
                }
                else {
                    echo "<div id='chartContainer" . $i . "' style='height: 370px; width: 100%;'></div>";
                }
            }
            ?>
            <script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>
        <?php include("templates/footer.php"); ?>
    </body>
</html>