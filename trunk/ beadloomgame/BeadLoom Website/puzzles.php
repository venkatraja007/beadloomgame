<?php

include('config.php');
include('connect.php');

$query = "SELECT DISTINCT puzzle FROM HighScores WHERE 1 ORDER BY puzzle";
$result = $db->query($query);
if($result)
{
	while($row = $result->fetch_assoc())
	{
		echo $row['puzzle'].",";
	}
}
else
{
	echo "ERROR";
}

?>