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
	
// Check for "Creative Cat"
//Get every puzzle name
$query = "SELECT user FROM CustomPuzzles WHERE user='$user' and published='1'";
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
	die("<html><h2>Error with Query.</h2></html>");
}
?>