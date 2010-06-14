<html>
	<head>
		<title>Bead Loom Game - Registration</title>
		<link rel="SHORTCUT ICON" href="http://www.uncc.edu/uncc.ico" type="image/x-icon" />
		<link rel="stylesheet" type="text/css" href="beadloomstyles.css" />
	</head>
	
<?php

include('config.php');
include('connect.php');
// This file handles the input from the registration form passed in $_POST
$uid = $_POST['userid'];
$password = $_POST['password'];
$confirmpass = $_POST['confirmPassword'];
$lname = $_POST['lastname'];
$fname = $_POST['firstname'];
$age = $_POST['age'];
$gender = $_POST['gender'];

//Check the uid and password fields
if(empty($uid) || empty($password))
{
	echo "<h2>User ID or Password field is blank.</h2><br>";
	die("<input type='button' value='BACK' onclick='history.back()'/>");
}
//Check the password and confirmPassword fields
if($password != $confirmpass)
{
	echo "<h2>The Passwords do not match.</h2><br>";
	die("<input type='button' value='BACK' onclick='history.back()'/>");
}
//Check if the uid is already registered
$verifyQuery = "SELECT count(*) as count FROM Users WHERE user='$uid' ";
$result = $db->query($verifyQuery);

$row = $result->fetch_assoc();
if($row['count'] == '1')
{
	echo "<h2>The user: $uid already exist in the database.</h2><br>";
	die("<input type='button' value='BACK' onclick='history.back()'/>");
}
//Add the user to the database and check for errors
$query = "insert into users values('$uid', '$password', '$lname', '$fname', '$age', '$gender', '$dept', '$iknow')";
$result = $db->query($query);
if($result)
{
	echo $db->affected_rows." user succesfully inserted into the database<br />";
}
else
{
	echo "An error has occurred. ".mysqli_error($db)."<br>";
	die("<input type='button' value='BACK' onclick='history.back()'/>");
}
//Link to the login page.
echo "<a href='login.php'>Login Page</a>";
$db->close();
?>

</html>




