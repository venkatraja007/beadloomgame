<?php

ini_set("display_erros", "1");

include('config.php');
include('connect.php');

$user = $_GET['user'];
$score = $_GET['score'];
$time = $_GET['time'];
$medal = $_GET['medal'];
$puzzle = $_GET['puzzle'];

$query = "INSERT INTO HighScores VALUES('$user', '$score', '$time', '$medal', '$puzzle', 'NOW()')";

$result = $db->query($query);

if($result)
{
	die("<html><h2>SUCCESS</h2></html>");
}
else
{
	echo mysqli_error();
	echo "<html><h2>ERROR</h2></html>";
}


?>