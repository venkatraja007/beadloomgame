<?php

include ('config.php');
include ('connect.php');

if(isset($_GET['user']) && !empty($_GET['user']))
{
	$user = $_GET['user'];
}
else
{
	die("<html><h2>No user set.</h2></html>");
}
	
// Check for "The New Ideal" Achievement
//Check to see if user has a global high score on a custom puzzle
$query = "SELECT puzzle FROM HighScores WHERE medal='Custom' AND user='$user'";
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
				echo "true";
				return;
			}
		}
	}
	echo "false";
}
else
{
	die("<html><h2>Error with Query.$db->error</h2></html>");
}
?>