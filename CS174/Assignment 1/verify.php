<?php

try {
    $con = new PDO("mysql:host=localhost;dbname=chi", "root", "root");
    $con->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
    
    $username = $_POST['username'];
    $password = $_POST['password'];

    $sql = "SELECT username FROM users WHERE username='$username' AND password='$password'";
    $q = $con->prepare($sql);
    $q->execute();
    $rows = $q->fetchAll();
    if (count($rows) == 0) {
        echo "incorrect login credentials";
    } else {
            echo "<p>Welcome " .$rows[0]['username'] ."!</p>";
    }
} catch (PDOException $ex) {
	echo 'ERROR: '.$ex->getMessage();
    // echo "<p>Connection failed</p>";
}

?>