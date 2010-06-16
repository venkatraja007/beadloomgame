<?php

$db = new mysqli($hostname, $db_username, $db_password, $dbname);

if(mysqli_connect_error())
{
	echo "<input type='button' value='Back' onclick='history.back()'/><br>";
	die("Failed to connect to the database.".mysqli_connect_error())."<br>";	
}

?>