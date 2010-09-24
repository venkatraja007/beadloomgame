<?php

if(isset($_GET['user']) && !empty($_GET['user']))
{
	$user = $_GET['user'];
}
else
{
	die("<html><h2>No user set.</h2></html>");
}
	
// Check for "Sense of Self" Achievement
// Check the Avatars directory to see if the user has a file there
$array = array();
$listing = scandir(getcwd()."/Avatars");
foreach($listing as $list)
{
	if(stripos($list, "MyAvatar-") !== false)
	{
		$split = explode("MyAvatar-", $list);
		$split = explode(".xml", $split[1]);
		array_push($array, $split[0]);
	}
}

if(in_array($user, $array))
{
	echo "true";
}
else
{
	echo "false";
}

?>