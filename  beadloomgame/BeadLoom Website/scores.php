
<html>

<head>
	<link rel="stylesheet" type="text/css" href="beadloomstyles.css" />
	<link rel="SHORTCUT ICON" href="http://www.uncc.edu/uncc.ico" type="image/x-icon" />

<?php

include('config.php');
include('connect.php');

?>
	<title>Bead Loom Game - High Scores</title>
</head>

<body>
	
	<table border="1" align="center">
		<tr>
			<th colspan="5">
				<h2>High Scores</h2>
			</th>
		</tr>
		<tr>
			<td class="off" onmouseover="this.className='on'" onmouseout="this.className='off'"><b>User Name</b></td>
			<td class="off" onmouseover="this.className='on'" onmouseout="this.className='off'"><b>Score</b></td>
			<td class="off" onmouseover="this.className='on'" onmouseout="this.className='off'"><b>Time</b></td>
			<td class="off" onmouseover="this.className='on'" onmouseout="this.className='off'"><b>Medal</b></td>
			<td class="off" onmouseover="this.className='on'" onmouseout="this.className='off'"><b>Puzzle</b></td>
		</tr>

	<?php
	$query = "SELECT user,score,time,medal,puzzle FROM HighScores WHERE 1 ORDER BY puzzle, score, user";
	$result = $db->query($query);
	if($result)
	{
		while($row = $result->fetch_array(MYSQLI_NUM))
		{
			echo "<tr>";
			foreach($row as $val)
			{
				echo "<td class=\"off\" onmouseover=\"this.className='on'\" onmouseout=\"this.className='off'\">";
				echo $val;
				echo "</td>";
			}
			echo "</tr>";
		}
	}
	else
	{
		echo "No result something happeneded maybe query is wrong</br>";
	}
	?>

	</table>

</body>

</html>
