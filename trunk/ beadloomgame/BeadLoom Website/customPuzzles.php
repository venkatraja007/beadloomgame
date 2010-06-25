<?php

include('config.php');
include('connect.php');

$userquery = $_GET['user'];
$query = "";

if($user)
{
	$query = "SELECT user, puzzle, moves FROM CustomPuzzles Where user='$userquery'";
}
else
{
	$query = "SELECT user, puzzle, moves FROM CustomPuzzles";
}

$result = $db->query($query);

if($result)
{
	$string ="";
	while($row = $result->fetch_assoc())
	{
		$string.= $row['user'].",".$row['puzzle'].",".$row['moves'].",";
	}
	echo $string;
}
else
{
	echo "Error";
}

?>