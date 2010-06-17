<?php

include ('config.php');
include ('connect.php');

if(isset($_GET['user']) && !empty($_GET['user']))
{
	$user = $_GET['user'];
}
	
// Check for "World Champion Achievement"
$worldChamp = false;
//Get every puzzle name
$query = "SELECT DISTINCT puzzle FROM HighScores WHERE 1 ORDER BY puzzle";
$result = $db->query($query);
if($result)
{
	while($row = $result->fetch_assoc())
	{
		$query2 = "SELECT user, score, time, medal FROM HighScores WHERE puzzle  = '".$row['puzzle']."' ORDER BY score, time LIMIT 0, 1";
		$result2 = $db->query($query2);
		if($result2)
		{
			$row2 = $result2->fetch_assoc();
			if($user == $row2['user'])
			{
				$worldChamp = true;
				echo "true";
				return;
			}
		}
	}
	echo "false";
}
?>