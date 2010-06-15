<?php

	function getLeaderBoard($db)
	{
		//create table
		echo "<table border=\"1\" align=\"center\" class=\"leaderboard\">";
		echo "<tr>";
		echo "<th colspan=\"5\">";
		echo "<h2>Leaderboard:</h2>";
		
		//print table header
		echo "<tr>";
		echo "<td class=\"off\" onmouseover=\"this.className='on'\" onmouseout=\"this.className='off'\"><b>Puzzle</b></td>";
		echo "<td class=\"off\" onmouseover=\"this.className='on'\" onmouseout=\"this.className='off'\"><b>User Name</b></td>";
		echo "<td class=\"off\" onmouseover=\"this.className='on'\" onmouseout=\"this.className='off'\"><b>Score</b></td>";
		echo "<td class=\"off\" onmouseover=\"this.className='on'\" onmouseout=\"this.className='off'\"><b>Time</b></td>";
		echo "<td class=\"off\" onmouseover=\"this.className='on'\" onmouseout=\"this.className='off'\"><b>Medal</b></td>";
		echo "</tr></th>";
					
		//Get every puzzle name
		$query = "SELECT DISTINCT puzzle FROM HighScores WHERE 1 ORDER BY puzzle";
		$result = $db->query($query);
		if($result)
		{
			while($row = $result->fetch_assoc())
			{
				$query2 = "SELECT user, score, time, medal FROM HighScores WHERE puzzle  = '".$row['puzzle']."' ORDER BY score, time LIMIT 0, 1";
				$result2 = $db->query($query2);
				if($result2)
				{
					$row2 = $result2->fetch_assoc();
					//print results to table
					echo "<tr>";
					echo "<td class=\"off\" onmouseover=\"this.className='on'\" onmouseout=\"this.className='off'\">".$row['puzzle']."</td>";
					echo "<td class=\"off\" onmouseover=\"this.className='on'\" onmouseout=\"this.className='off'\">".$row2['user']."</td>";
					echo "<td class=\"off\" onmouseover=\"this.className='on'\" onmouseout=\"this.className='off'\">".$row2['score']."</td>";
					echo "<td class=\"off\" onmouseover=\"this.className='on'\" onmouseout=\"this.className='off'\">".$row2['time']."</td>";
					echo "<td class=\"off\" onmouseover=\"this.className='on'\" onmouseout=\"this.className='off'\">".$row2['medal']."</td>";
					echo "</tr>";
				}
				else
				{
					echo "Uh Oh! There's been a PHP Error! Error message: ".mysqli_error($db);
				}
						
			}
		}
		else
		{
		}
		echo "</table>";
	
	}
	
	function getUserScores($db, $user)
	{
		//create table
		echo "<table border=\"1\" align=\"center\" class=\"leaderboard\">";
		echo "<tr>";
		echo "<th colspan=\"5\">";
		echo "<h2>".$user."'s High Scores:</h2>";
		
		//print table header
		echo "<tr>";
		echo "<td class=\"off\" onmouseover=\"this.className='on'\" onmouseout=\"this.className='off'\"><b>Puzzle</b></td>";
		echo "<td class=\"off\" onmouseover=\"this.className='on'\" onmouseout=\"this.className='off'\"><b>Score</b></td>";
		echo "<td class=\"off\" onmouseover=\"this.className='on'\" onmouseout=\"this.className='off'\"><b>Time</b></td>";
		echo "<td class=\"off\" onmouseover=\"this.className='on'\" onmouseout=\"this.className='off'\"><b>Medal</b></td>";
		echo "</tr></th>";
		
		$query = "SELECT score, time, medal, puzzle FROM HighScores WHERE user='".$user."' ORDER BY puzzle";
		$result = $db->query($query);
		if($result)
		{
			while($row = $result->fetch_assoc())
			{
					//print results to table as new row
					echo "<tr>";
					echo "<td class=\"off\" onmouseover=\"this.className='on'\" onmouseout=\"this.className='off'\">".$row['puzzle']."</td>";
					echo "<td class=\"off\" onmouseover=\"this.className='on'\" onmouseout=\"this.className='off'\">".$row['score']."</td>";
					echo "<td class=\"off\" onmouseover=\"this.className='on'\" onmouseout=\"this.className='off'\">".$row['time']."</td>";
					echo "<td class=\"off\" onmouseover=\"this.className='on'\" onmouseout=\"this.className='off'\">".$row['medal']."</td>";
					echo "</tr>";
			}
		}
		else
		{
		}
		echo "</table>";
	
	}
	
	function getAchievements($db, $user)
	{
	
		//create table
		echo "<table border=\"1\" align=\"center\" class=\"leaderboard\">";
		echo "<tr>";
		echo "<th>";
		echo "<h2>".$user."'s Achievements:</h2></th></tr>";
		
		//get all achievements from database
		$query = "SELECT * FROM Achievements ORDER BY id";
		$result = $db->query($query);
		if($result)
		{
			//get users achievement blob
			$userAchievementsQuery = "SELECT Achievements FROM Users WHERE user='".$user."' LIMIT 0, 1";
			$result2 = $db->query($userAchievementsQuery);
			$row2 = $result2->fetch_assoc();
			$achievements = str_split($row2['Achievements'], 1);
			
			$index = 0;
			//loop through each achivement in achievement array
			while($row = $result->fetch_assoc())
			{
				//this is each achievement
				//echo $row['name']."<br />";
				
				//this is the user's achievment blob
				//echo $row2['Achievements'];
				
				if($achievements[$index] == 1)
				{
					echo "<tr><td align=\"center\" class=\"off\" onmouseover=\"this.className='on'\" onmouseout=\"this.className='off'\">".$row['name']."</td></tr>";
				}
				
				//increase index
				$index = $index + 1;
			}
		}
		else
		{
		}
		echo "</table>";
	}

?>