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
	
// Check for "Creative Cat" Achievement
//Check to see if the user completed a custom puzzle that was not their own
$query = "
SELECT HighScores.user, HighScores.puzzle, HighScores.medal, CustomPuzzles.puzzle AS puzzle2, CustomPuzzles.user AS user2
FROM HighScores 
INNER JOIN CustomPuzzles 
ON CustomPuzzles.puzzle LIKE CONCAT(SUBSTR(HighScores.puzzle,LOCATE('-', HighScores.puzzle)+1), '%')
WHERE HighScores.medal = 'Custom' AND HighScores.user != CustomPuzzles.user AND HighScores.user ='$user'";
$result = $db->query($query);
if($result)
{
	$rowCount = $result->num_rows;
	if($rowCount > 0)
	{
		echo "true";
	}
	else
	{
		echo "false";
	}
}
else
{
	die("<html><h2>Error with Query.$db->error</h2></html>");
}
?>