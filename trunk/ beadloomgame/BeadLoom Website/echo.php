<?php

include('config.php');
include('connect.php');

if(isset($_POST['folderName']))
{
	$folderName = $_POST['folderName'];
}
else
{
	die("<html><h2>Folder Name is not set</h2></html>");
}
chdir($folderName);
$fileName = $db->escape_string($_POST['fileName']).".xml";
$fileName2 = $db->escape_string($_POST['fileName']);

if($folderName == "CustomPuzzles")
{
	$pos = stripos($fileName2, "-");
	$user = substr($fileName2, 0, ($pos));
	$puzzle = substr($fileName2, ($pos+1));
	$query = "INSERT INTO CustomPuzzles VALUES('$user','$puzzle', NOW())";
	$queryResult = $db->query($query);
	if($queryResult)
	{
		//Do nothing
	}
	else
	{
		die("Custom Puzzle $user-$puzzle probably already exist.");
	}
}

$fileContents = stripslashes($_POST['file']);
if(isset($_POST['file']))
{
	$result = file_put_contents($fileName, $fileContents);
}
if($result == FALSE)
{
	echo "Error with file writing";
}
else
{
	echo "<html><h2>Custom Puzzle ".$_POST['fileName']." added.</h2></html>";
}

?>