<?php

include('config.php');
include('connect.php');

if(isset($_GET['token']))
{
	$token = $_GET['token'];
	//return number of achivements in the Achievements table
	if($token == "num")
	{
		$query = "SELECT count(*) as count FROM Achievements";
		$result = $db->query($query);
		if($result)
		{
			$row = $result->fetch_assoc();
			die($row['count']);
		}
		else
		{
			die("<html><h2>Error retrieving number of Achievements</h2></html>");
		}
		
	}
	else if($token == "retrieve")
	{
		//return the achievements of the user as a 64 length string
		if(isset($_GET['user']))
		{
			$user = $_GET['user'];
			$query = "SELECT * FROM Users WHERE user='$user'";
			$result = $db->query($query);
			if($result)
			{
				$row = $result->fetch_assoc();
				$achievements = $row['Achievements'];
				die($achievements);
			}
			else
			{
				die("<html><h2>Error retrieving user Achievements</h2></html>");
			}
		}
		else
		{
			die("<html><h2>User name missing</h2></html>");
		}
	}
	else if($token == "names")
	{
		//return achievement names in order by id
		$query = "SELECT name FROM Achievements ORDER BY id";
		$result = $db->query($query);
		if($result)
		{
			while($row = $result->fetch_assoc())
			{
				echo $row['name'].",";
			}
		}
		else
		{
			die("<html><h2>Error retrieving Achievement names.</h2></html>");
		}
	}
	else
	{
		
	}
}


?>