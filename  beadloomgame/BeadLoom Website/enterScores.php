<?php

include('config.php');
include('connect.php');

//security check
$tokenQuery = "
SELECT securityToken
FROM Users 
WHERE user='$user'";
$securityResult = $db->query($tokenQuery);

$securityRow = $securityResult->fetch_assoc();
if($securityRow['securityToken'] != $token)
{
	//rick roll
	die("<html><h2>Rick Roll</h2></html>");
}

//Check that each variable is set to a value
if(checkVariable("user", $_POST['user']))
	$user = $_POST['user'];
else
	die("<html><h2>User Name is invalid or too short.</h2></html><br/>");
if(checkVariable("score", $_POST['score']))
	$score = $_POST['score'];
else
	die("<html><h2>Score is invalid.</h2></html><br/>");
if(checkVariable("time",$_POST['time']))
	$time = $_POST['time'];
else
	die("<html><h2>Time is invalid.</h2></html><br/>");
if(checkVariable("medal",$_POST['medal']))
	$medal = $_POST['medal'];
else
	die("<html><h2>Medal is invalid.</h2></html><br/>");
if(checkVariable("puzzle", $_POST['puzzle']))
	$puzzle = $_POST['puzzle'];
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

$insertQuery = "
INSERT INTO HighScores 
VALUES('$user', '$score', '$time', '$medal', '$puzzle', NOW())";

$updateQuery = "
UPDATE HighScores
SET score='$score', time='$time', medal='$medal'
WHERE user='$user' and puzzle='$puzzle'";

//check for rating variable
if(isset($_POST['rating']) && !empty($_POST['rating']))
{
	$rating = $_POST['rating'];
	$ratingQuery = "
	INSERT INTO UserCustomPuzzleRating (user, puzzleName, rating, timeStamp)
	Values('$user', '$puzzle', '$rating' , NOW())
	ON DUPLICATE KEY
	UPDATE rating='$rating' AND timeStamp=NOW()";
	
	//Insert rating
	$result = $db->query($ratingQuery);
	if($result)
	{
		//successful query
	}
	else
	{
		echo "<html><h2>An Error Occurred sending rating. $db->error</h2></html>";
	}
}

//Query for a duplicate
$result = $db->query($countQuery);
$row = $result->fetch_assoc();
if($result)
{
	//NO Duplicate add record
	if($row['count'] < 1)
	{
		//echo "<html><h2>NO Entry for $user on $puzzle.</h2></html><br/>";
		$result = $db->query($insertQuery);
		if($result)
		{
			die("<html><h2>High Score for $user on $puzzle added.</h2></html><br/>");
		}
		else
		{
			die("<html><h2>ERROR adding High Score for $user on $puzzle.</h2></html><br/>");
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
			//echo "<html><h2>Entry exists for $user on $puzzle.</h2></html><br/>";
			$db_user = $row['user'];
			$db_score = $row['score'];
			$db_time = $row['time'];
			$db_medal = $row['medal'];
			$db_puzzle = $row['puzzle'];
			
			//New medal is better update new record
			if($db_score > $score)
			{
				//Insert new record
				$result = $db->query($updateQuery);
				if($result)
				{
					die("<html><h2>You beat your previous High Score on $puzzle.</h2></html><br/>");
				}
				else
				{
					die("<html><h2>ERROR inserting High Score for $user on $puzzle at score comparision.</h2></html><br/>");
				}
			}
			//Score is equivalent comparing times
			else if($db_score == $score)
			{
				$timeSplit = explode(":", $time, 2);
				$timeValue = intval($timeSplit[0]) * 60 + intval($timeSplit[1]);
				$timeSplit = explode(":", $db_time, 2);
				$db_timeValue = intval($timeSplit[0]) * 60 + intval($timeSplit[1]);
				
				//Update new record
				if($db_timeValue > $timeValue)
				{
					$result = $db->query($updateQuery);
					if($result)
					{
						die("<html><h2>You beat your previous High Score on $puzzle.</h2></html><br/>");
					}
					else
					{
						die("<html><h2>ERROR inserting High Score for $user on $puzzle at time comparision.</h2></html><br/>");
					}
				}
				else
				{
					die("<html><h2>You did not beat your previous High Score on $puzzle.</h2></html><br/>");
				}
			}
			//New score is lower than score in database
			//$db_score < $score
			else
			{
				die("<html><h2>You did not beat your previous High Score on $puzzle.</h2></html><br/>");
			}
		}
		else
		{
			die("<html><h2>ERROR retrieving High Score for $user on $puzzle.</h2></html><br/>");
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









