
<html>

<head>
	<link rel="stylesheet" type="text/css" href="beadloomstyles.css" />
	<link rel="SHORTCUT ICON" href="http://www.uncc.edu/uncc.ico" type="image/x-icon" />
	<script type="text/javascript">
		function puzzleSelect()
		{
			var selectBox = document.getElementById("puzzle").value;
			window.location = "http://playground.uncc.edu/BeadLoomGame/scores.php?puzzleName=" + selectBox;
		}
	</script>
<?php

include('config.php');
include('connect.php');

$puzzleName = $_GET['puzzleName'];

?>
	<title>Bead Loom Game - High Scores - <?php echo "$puzzleName"; ?> </title>
</head>

<body>
	
	<table border="1" align="center">
		<tr>
			<?php
				if(isset($puzzleName) && !empty($puzzleName))
				{
					echo "<th colspan=\"5\">";
				} 
				else
				{
					echo "<th colspan=\"6\">";
				}
			?>
				<h2>High Scores</h2>
				<?php 
					echo "$puzzleName";
					echo "<br/>";
					if(!isset($_GET['token']))
					{
						$query = "SELECT DISTINCT puzzle FROM HighScores WHERE 1 ORDER BY puzzle";
						$result = $db->query($query);
						if($result)
						{
							
							//echo "<tr><td>";
							echo "<select id='puzzle' name='puzzle' onchange='puzzleSelect()'>";
							echo "<option selected='selected' value=''>Chosse a puzzle...</option>";
							while($row = $result->fetch_array(MYSQLI_NUM))
							{
								echo "<option value='$row[0]'>$row[0]</option>";
							}
							echo "</select>";
							//echo "</td></tr>";
						}
						else
						{
							
						}
					}
					?>
			</th>
		</tr>
		<tr>
			<td class="off" onmouseover="this.className='on'" onmouseout="this.className='off'"><b>Rank</b></td>
			<td class="off" onmouseover="this.className='on'" onmouseout="this.className='off'"><b>User Name</b></td>
			<td class="off" onmouseover="this.className='on'" onmouseout="this.className='off'"><b>Score</b></td>
			<td class="off" onmouseover="this.className='on'" onmouseout="this.className='off'"><b>Time</b></td>
			<td class="off" onmouseover="this.className='on'" onmouseout="this.className='off'"><b>Medal</b></td>
			<?php
				//If no puzzle was selected show all
				if(!isset($_GET['puzzleName']) || empty($_GET['puzzleName']))
				{
					echo "<td class=\"off\" onmouseover=\"this.className='on'\" onmouseout=\"this.className='off'\"><b>Puzzle</b></td>";
				}
			?>
		</tr>

	<?php
		if(isset($_GET['puzzleName']) && !empty($_GET['puzzleName']))
		{
			$query = "SELECT user,score,time,medal FROM HighScores WHERE puzzle='$puzzleName' ORDER BY puzzle, score, time, user";
		}
		else
		{
			$query = "SELECT user,score,time,medal,puzzle FROM HighScores WHERE 1 ORDER BY puzzle, score, user";
		}
		$result = $db->query($query);
		if($result)
		{
			$rank = 1;
			while($row = $result->fetch_array(MYSQLI_NUM))
			{
				echo "<tr><td class=\"off\" onmouseover=\"this.className='on'\" onmouseout=\"this.className='off'\">$rank</td>";
				foreach($row as $val)
				{
					echo "<td class=\"off\" onmouseover=\"this.className='on'\" onmouseout=\"this.className='off'\">";
					echo $val;
					echo "</td>";
				}
				echo "</tr>";
				$rank++;
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
