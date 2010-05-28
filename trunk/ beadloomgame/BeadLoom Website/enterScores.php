<?php

include('config.php');
include('connect.php');

//Check that each variable is set to a value
if(checkVariable("user", $_GET['user']))
	$user = $_GET['user'];
else
	die("<html><h2>User Name is invalid or too short.</h2></html><br/>");
if(checkVariable("score", $_GET['score']))
	$score = $_GET['score'];
else
	die("<html><h2>Score is invalid.</h2></html><br/>");
if(checkVariable("time",$_GET['time']))
	$time = $_GET['time'];
else
	die("<html><h2>Time is invalid.</h2></html><br/>");
if(checkVariable("medal",$_GET['medal']))
	$medal = $_GET['medal'];
else
	die("<html><h2>Medal is invalid.</h2></html><br/>");
if(checkVariable("puzzle", $_GET['puzzle']))
	$puzzle = $_GET['puzzle'];
else 
	die("<html><h2>Puzzle is invalid.</h2></html><br/>");

$countQuery = "
SELECT count(*) as count 
FROM HighScores 
WHERE user='$user' and puzzle='$puzzle'";

$dataQuery = "
SELECT * 
FROM HighScores 
WHERE user='$user' and puzzle='$puzzle'";

$insertQuery = "INSERT INTO HighScores 
VALUES('$user', '$score', '$time', '$medal', '$puzzle', NOW())";

$updateQuery = "
UPDATE HighScores
SET score='$score', time='$time', medal='$medal'
WHERE user='$user' and puzzle='$puzzle'";

//Query for a duplicate
$result = $db->query($countQuery);
$row = $result->fetch_assoc();
if($result)
{
	//NO Duplicate add record
	if($row['count'] < 1)
	{
		echo "<html><h2>NO Entry for $user on $puzzle.</h2></html><br/>";
		$result = $db->query($insertQuery);
		if($result)
		{
			die("<html><h2>Entry for $user on $puzzle added.</h2></html><br/>");
		}
		else
		{
			die("<html><h2>ERROR adding entry for $user on $puzzle.</h2></html><br/>");
		}
	}
	//Duplicate Found
	else
	{
		//Retreive user data for comparision
		$result = $db->query($dataQuery);
		if($result)
		{
			$row = $result->fetch_assoc();
			echo "<html><h2>Entry exists for $user on $puzzle.</h2></html><br/>";
			$db_user = $row['user'];
			$db_score = $row['score'];
			$db_time = $row['time'];
			$db_medal = $row['medal'];
			$db_puzzle = $row['puzzle'];
			
			//New medal is better insert new record
			if($db_score < $score)
			{
				//Insert new record
				$result = $db->query($insertQuery);
				if($result)
				{
					die("<html><h2>You beat your previous record on $puzzle.</h2></html><br/>");
				}
				else
				{
					die("<html><h2>ERROR inserting record for $user on $puzzle at score comparision.</h2></html><br/>");
				}
			}
			//Score is equivalent comparing times
			else if($db_score == $score)
			{
				$timeValue = mktime()
				$db_timeValue = idate("i:s", $db_timeValue);
				die("<html><h2>timeValue: $timeValue.</h2></html><br/>");
			}
		}
		else
		{
			die("<html><h2>ERROR retrieving record for $user on $puzzle.</h2></html><br/>");
		}
	}
}


function checkVariable($name, $var)
{
	switch($name)
	{
		case "user":
			//validate user name
			if(isset($var))
			{
				//user name length has to be at least 2 characters
				if(strlen($var) < 2)
					return false;
				else
					return true;
			}
			else
				return false;
			break;
		case "medal":
			if(isset($var))
			{
				return true;
			}
			else
				return false;
			break;
		case "score":
			if(isset($var))
			{
				return true;
			}
			else
				return false;
			break;
		case "time":
			if(isset($var))
			{
				return true;
			}
			else
				return false;
			break;
		case "puzzle":
			if(isset($var))
			{
				return true;
			}
			else
				return false;
			break;
		default:
			break;
	}
}



?>









