<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" type="text/css" href="css/index.css">
    <?php include("templates/header.php"); ?>
</head>
    <body>
        <?php include("templates/nav.php"); ?>
        <h1>Support</h1>
<!-- Start of page content -->

<div class="container my-3" role="main">
    <div class="row">
        <div class="col-md-6 col-sm-12">
            <h2>Frequently Asked Questions</h2>

            <h6>How will my data be stored?</h6>
            <p class="small">
            Your data will be securely stored in the university's MySQL database on the 
            Mayar server.
            </p>

            <h6>Does this application ue 'real life' data/results?</h6>
            <p class="small">
            No all data/reuslts are fabricated or taken from willing volunteersto prove the 
            concept works. No patient data is ever used. 
            </p>

            <h6>Where is this project being developed?</h6>
            <p class="small">
            This project is being developed within the Univeristy of Abertay Dundee's campus.
            </p>

            <h6>What technologies does the project utilise?</h6>
            <p class="small">
            To develop this website the technologies used are the LAMP (or Linux Apache MySQL and PHP)
            stack with HTML, CSS and JavaScript to suppliment the website. 
            </p>
            <h6>What else is being developed to couple with this website?</h6>
            <p class="small">
            A mobile device to test patients Parkinson's/Dementia state and an IoT device to read a subject's
            vital signs. 
            </p>
            <h6>My question isn't answered here what should I do?</h6>
            <p class="small">
            Please fill out the form on this page and it will send an email to a member of the development team
            who will answer your query in no time.
            </p>
        </div>
        <div class="col-md-6 col-sm-12">
            <h2>Contact Us</h2>
            <form action="../Controller/contact.php" method="POST">
                <label for="subject">Subject</label>
                <select class="form-control" name="subject">
                    <option value="Feedback">Feedback</option>
                    <option value="Report">Bug Report</option>
                    <option value="Complaint">Complaint</option>
                    <option value="Other">Other</option>
                </select>
                <label for="email">Email Address</label>
                <input class="form-control" type="email" name="email" required pattern="^(?=.{5,80}$)[\S]+@[\S]+\.[\S]+$">
                <label for="message">Message</label>
                <textarea class="form-control" name="message" rows="6" required pattern="^.{1,1200}$"></textarea>
                <input class="form-control btn btn-danger mt-3" type="submit" value="Submit">
            </form>
        </div>
    </div>
</div>

<!-- End of page content -->
<?php include("templates/footer.php"); ?>
</body>
</html>