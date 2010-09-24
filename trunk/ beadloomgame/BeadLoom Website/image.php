<?php

if(isset($_GET['fileName']) && !empty($_GET['fileName']))
{
	chdir("CustomPuzzleImages");
	$myFile = $_GET['fileName'].".png";
	$fh = fopen($myFile, 'w') or die("Error. Can't open file.");
	$stringData = (file_get_contents("php://input"));
	fwrite($fh, $stringData);
	fclose($fh);
}
else
{
	echo "Error. Filename not set.";
}


?>