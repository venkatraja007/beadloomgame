<?php

include('config.php');
include('connect.php');

if(isset($_GET['user']) && !empty($_GET['user']))
{	
	$user = $_GET['user'];
	
	if(isset($_GET['avatar']) && !empty($_GET['avatar']))
	{
		$avatar = $_GET['avatar'];
		$query = "UPDATE Users SET avatar='$avatar' WHERE user='$user'"; 
		$result = $db->query($query);
	}
	else
	{
		$query = "SELECT avatar FROM Users WHERE user='$user'";
		$result = $db->query($query);
		if($result)
		{
			$row = $result->fetch_assoc();
			echo $row['avatar'];
		}
		else
		{
			echo "-1";
		}
	}
}


?>