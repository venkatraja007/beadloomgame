<?php

include('config.php');
include('connect.php');

$user = $_GET['user'];
$score = $_GET['score'];
$time = $_GET['time'];
$medal = $_GET['medal'];
$puzzle = $_GET['puzzle'];
$medalValue = getMedalValue($medal);

//TODO update query UPDATE SET WHERE
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
			echo "<html><h2>Entry exist for $user on $puzzle.</h2></html><br/>";
			$db_user = $row['user'];
			$db_score = $row['score'];
			$db_time = $row['time'];
			$db_medal = $row['medal'];
			$db_puzzle = $row['puzzle'];
			$db_medalValue = getMedalValue($db_medal);
			echo "<html><h2>Medal: $db_medal -- MedalValue: $db_medalValue.</h2></html><br/>";
			
			
			//Medals are the same comparing score now
			if($db_medalValue == $medalValue)
			{
				//Score are the same comparing time now
				if($db_score == $score)
				{
					//Times are the same doing nothing leaving the record as is
					if($db_time == $time)
					{
						die("<html><h2>You did not beat your previous score on $puzzle.</h2></html><br/>");
					}
				}
				//New score is better and medals are the same updating new
				else if($db_score < $score)
				{
					//TODO update record
				}
			}
			//New medal is better insert
			else if($db_medalValue < $medalValue)
			{
				$result = $db->query($insertQuery);
				if($result)
				{
					die("<html><h2>Entry for $user on $puzzle added at medal comparison.</h2></html><br/>");
				}
				else
				{
					die("<html><h2>ERROR inserting record for $user on $puzzle at medal comparison.</h2></html><br/>");
				}
			}
		}
		else
		{
			die("<html><h2>ERROR retrieving record for $user on $puzzle.</h2></html><br/>");
		}
	}
}

function getMedalValue($medalName)
{
	switch($medalName)
	{
		case "Platinum!!!!":
			return 4;
			break;
		case "Gold!!!":
			return 3;
			break;
		case "Silver!!":
			return 2;
			break;
		case "Bronze!":
			return 1;
			break;
		default:
			return 0;
			break;
	}
}





?>









