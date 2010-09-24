<?php
//migrate content from BeadLoom Game to Drupal

include('config.php');
include('connect.php');

$array = array();
$query = "SELECT * from CustomPuzzles WHERE published='1'";

$result = $db->query($query);
if($result)
{
	while($row = $result->fetch_assoc())
	{
		$key = $row['user']."-".$row['puzzle'];
		$array[$key] = getImageLink($key);
	}
	$json = json_encode($array);
	echo $json;
}
else
{
	die("Error. $db->error");
}

function getImageLink($name)
{
	$value = "<img width=\"164px\" height=\"164px\" src=\"http://unccmakesgames.com/games/BeadLoomGame/CustomPuzzleImages/$name.png\">";
	return $value;
}

?>