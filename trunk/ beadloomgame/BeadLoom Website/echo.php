<?php

if(isset($_POST['folderName']))
{
	$folderName = $_POST['folderName'];
}
else
{
	die("<html><h2>Folder Name is not set</h2></html>");
}
chdir($folderName);
$fileName = $_POST['fileName'].".xml";
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