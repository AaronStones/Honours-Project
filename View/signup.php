<!DOCTYPE html>
<html lang="en">
<head>
    <?php include("templates/header.php"); ?>
    <link rel="stylesheet" type="text/css" href="https://mayar.abertay.ac.uk/~1600964/Honours-Project/View/css/login.css">
</head>
    <body>
        <?php include("templates/nav.php"); ?>

        <div style="margin-top: 100px;" class="container">
            <div class="d-flex justify-content-center h-100">
                <div class="card">
                    <div class="card-header">
                        <h3>Sign Up</h3>
                    </div>
                    <div class="card-body">
                        <form>
                        <div style="margin-left: auto; margin-right: auto;" class="input-group form-group">
                                <div class="input-group-prepend">
                                    <span class="input-group-text"><i class="fas fa-user"></i></span>
                                </div>
                                <input type="text" class="form-control" placeholder="Username">
                                
                            </div>
                            <div style="margin-left: auto; margin-right: auto;" class="input-group form-group">
                                <div class="input-group-prepend">
                                    <span class="input-group-text"><i class="fas fa-key"></i></span>
                                </div>
                                <input type="password" class="form-control" placeholder="Password">
                        </div>
                        <div style="margin-left: auto; margin-right: auto;" class="input-group form-group">
                                <div class="input-group-prepend">
                                    <span class="input-group-text"><i class="fas fa-user"></i></span>
                                </div>
                                <input type="text" class="form-control" placeholder="Username">
                                
                            </div>
                            <div style="margin-left: auto; margin-right: auto;" class="input-group form-group">
                                <div class="input-group-prepend">
                                    <span class="input-group-text"><i class="fas fa-key"></i></span>
                                </div>
                                <input type="password" class="form-control" placeholder="Password">
                        </div>
                            <div class="form-group">
                                <input type="submit" value="Sign Up" class="btn float-right login_btn">
                            </div>
                        </form>
                    </div>
                    <div class="card-footer">
                        <div class="d-flex justify-content-center links">
                            Have an account?<a href="https://mayar.abertay.ac.uk/~1600964/Honours-Project/View/login">Log In</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <?php include("templates/footer.php"); ?>
    </body>
</html>