<?php

chdir("CustomPuzzles");
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
	echo "<h2>Custom Puzzle ".$_POST['fileName']." added.</h2>";
}

?>