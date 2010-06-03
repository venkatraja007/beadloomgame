
<html>

<head>
	<link rel="stylesheet" type="text/css" href="beadloomstyles.css" />
	<link rel="SHORTCUT ICON" href="http://www.uncc.edu/uncc.ico" type="image/x-icon" />
	<script type="text/javascript">
		function puzzleSelect()
		{
			var selectBox = document.getElementById("puzzle").value;
			window.location = "http://unccmakesgames.com/games/BeadLoomGame/scores.php?puzzleName=" + selectBox;
		}
	</script>

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
			<td class="off" onmouseover="this.className='on'" onmouseout="this.className='off'"><b>Puzzle</b></td>
		</tr>
	
	
	<?php
	$query = "SELECT DISTINCT puzzle FROM HighScores WHERE 1 ORDER BY puzzle";
	$result = $db->query($query);
	if($result)
	{
		
		echo "<tr><td>";
		echo "<select id='puzzle' name='puzzle' onchange='puzzleSelect()'>";
		echo "<option selected='selected' value=''>Chosse a puzzle...</option>";
		while($row = $result->fetch_array(MYSQLI_NUM))
		{
			echo "<option value='$row[0]'>$row[0]</option>";
		}
		echo "</select>";
		echo "</td></tr>";
	}
	else
	{
		
	}
	?>
	
	</table>

</body>

</html>








