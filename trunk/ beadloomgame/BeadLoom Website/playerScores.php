<?php

include('config.php');
include('connect.php');

$user = $_GET['user'];

$query = "SELECT puzzle, score, medal FROM HighScores Where user='$user'";

$result = $db->query($query);

if($result)
{
	$string ="";
	while($row = $result->fetch_assoc())
	{
		$string.= $row['puzzle'].",".$row['score'].",".$row['medal'].",";
	}
	echo $string;
}
else
{
	echo "Error";
}

?>