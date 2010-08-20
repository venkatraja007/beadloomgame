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


if($token == "sitePuzzleList")
{
	if(isset($_GET['user']) && !empty($_GET['user']))
	{
		$user = $_GET['user'];
		$query = "SELECT * FROM CustomPuzzles WHERE published='1' AND user='$user' ORDER BY user";
		$result = $db->query($query);
		if($result)
		{
			while($row = $result->fetch_assoc())
			{
				echo $row['user']."-".$row['puzzle'].",";
			}
			die;
		}
		else
		{
			echo "Error";
		}
	}
}
//return comma seperated list of custom puzzles
else if($token == "puzzles")
{
	$query = "SELECT DISTINCT puzzle FROM CustomPuzzles";
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
else if($token == "userList")
{
	$query = "SELECT DISTINCT user FROM CustomPuzzles WHERE published='1' ORDER BY user";
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