<html>
<?php

include('config.php');
include('connect.php');

$puzzleName = $_GET['puzzleName'];

//echo "$puzzleName";

$query = "SELECT hint FROM Hints WHERE puzzleName = '$puzzleName'";
$result = $db->query($query);
if($result && $result->num_rows != 0)
{
	while($row = $result->fetch_array(MYSQLI_NUM))
	{	
		echo "$row[0]";
	}

}
else
{
	echo "No Hint!";
}
?>




</html>