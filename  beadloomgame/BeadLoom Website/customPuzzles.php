<?php

include('config.php');
include('connect.php');

if(isset($_GET['token']) && !empty($_GET['token']))
{
	$token = $_GET['token'];	
}
else
{
	die("Error");
}

if($token == "userList")
{
	$query = "SELECT DISTINCT user FROM CustomPuzzles";
	$result = $db->query($query);
	if($result)
	{
		while($row = $result->fetch_assoc())
		{
			echo $row['user'].",";
		}
		die;
	}
	else
	{
		echo "Error";
	}
}
else if($token == "puzzleList" && isset($_GET['user']) && !empty($_GET['user']))
{
	$user = $_GET['user'];
	$query = "SELECT puzzle FROM CustomPuzzles WHERE user='$user' AND published='1'";
	$result = $db->query($query);
	if($result)
	{
		while($row = $result->fetch_assoc())
		{
			echo $row['puzzle'].",";
		}
		die;
	}
	else
	{
		echo "Error";
	}
}
else if($token == "edit" && isset($_GET['user']) && !empty($_GET['user']))
{
	$user = $_GET['user'];
	$query = "SELECT puzzle FROM CustomPuzzles WHERE user='$user' AND published='0'";
	$result = $db->query($query);
	if($result)
	{
		while($row = $result->fetch_assoc())
		{
			echo $row['puzzle'].",";
		}
		die;
	}
	else
	{
		echo "Error";
	}
}
else
{
	echo "Error";
}






?>