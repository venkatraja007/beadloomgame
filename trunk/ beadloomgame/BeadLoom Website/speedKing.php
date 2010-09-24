<?php

include ('config.php');
include ('connect.php');

if(isset($_GET['user']) && !empty($_GET['user']))
{
	$user = $_GET['user'];
}
else
{
	die("<html><h2>No user set.</h2></html>");
}
	
// Check for "Speed King" Achievement
//Check to see if the user completed any puzzle in less than 60 seconds
$query = "SELECT time FROM HighScores WHERE user='$user' AND time < '60' LIMIT 0,1";
$result = $db->query($query);
if($result)
{
	$rowCount = $result->num_rows;
	if($rowCount > 0)
	{
		echo "true";
	}
	else
	{
		echo "false";
	}
}
else
{
	die("<html><h2>Error with Query.$db->error</h2></html>");
}
?>