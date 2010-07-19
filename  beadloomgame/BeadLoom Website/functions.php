<?php

	function getCustomPuzzleScores($db, $user)
	{
		//create table
		echo "<table border=\"1\" align=\"center\" class=\"leaderboard\">";
		echo "<tr>";
		echo "<th colspan=\"5\">";
		echo "<h2>Custom Puzzle Leaderboard:</h2>";
		
		//print table header
		echo "<tr>";
		echo "<td class=\"off\" onmouseover=\"this.className='on'\" onmouseout=\"this.className='off'\"><b>Puzzle</b></td>";
		echo "<td class=\"off\" onmouseover=\"this.className='on'\" onmouseout=\"this.className='off'\"><b>User Name</b></td>";
		echo "<td class=\"off\" onmouseover=\"this.className='on'\" onmouseout=\"this.className='off'\"><b>Score</b></td>";
		echo "<td class=\"off\" onmouseover=\"this.className='on'\" onmouseout=\"this.className='off'\"><b>Time</b></td>";
		echo "</tr></th>";
		
		//Get every custom puzzle name
		$query = "SELECT DISTINCT puzzle FROM HighScores WHERE medal='Custom' ORDER BY puzzle";
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
		$query = "SELECT DISTINCT puzzle FROM HighScores WHERE medal!='Custom' ORDER BY puzzle";
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
		
		$query = "SELECT score, time, medal, puzzle, user FROM HighScores WHERE user='".$user."' ORDER BY puzzle";
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
	
	function getCustomPuzzleRatings($db)
	{
		//create table
		echo "<table border=\"1\" align=\"center\" class=\"leaderboard\">";
		echo "<tr>";
		echo "<th colspan=\"3\">";
		echo "<h2>Custom Puzzle Ratings:</h2>";
		
		//print table header
		echo "<tr>";
		echo "<td class=\"off\" onmouseover=\"this.className='on'\" onmouseout=\"this.className='off'\"><b>Puzzle</b></td>";
		echo "<td class=\"off\" onmouseover=\"this.className='on'\" onmouseout=\"this.className='off'\"><b>Rating</b></td>";
		echo "<td class=\"off\" onmouseover=\"this.className='on'\" onmouseout=\"this.className='off'\"><b>Votes</b></td>";
		echo "</tr></th>";
		
		$query = "SELECT DISTINCT puzzleName FROM UserCustomPuzzleRatings";
		$result = $db->query($query);
		if($result)
		{
			while($row = $result->fetch_assoc())
			{
				$query2 = "SELECT puzzleName, COUNT(rating) AS votes, AVG(rating) AS rated FROM UserCustomPuzzleRatings WHERE puzzleName = '".$row['puzzleName']."'";
				$result2 = $db->query($query2);
				if($result2)
				{
					$row2 = $result2->fetch_assoc();
				
					//print results to table as new row
					echo "<tr>";
					echo "<td class=\"off\" onmouseover=\"this.className='on'\" onmouseout=\"this.className='off'\">".$row2['puzzleName']."</td>";
					echo "<td class=\"off\" onmouseover=\"this.className='on'\" onmouseout=\"this.className='off'\">".$row2['rated']."</td>";
					echo "<td class=\"off\" onmouseover=\"this.className='on'\" onmouseout=\"this.className='off'\">".$row2['votes']."</td>";
					echo "</tr>";
				}
				else
				{
					echo "error2".$db->error;
				}
			}
		}
		else
		{
			echo "error".$db->error;
		}
		echo "</table>";
	}
	
	function getUserCustomPuzzleScores($db, $user)
	{
		//create table
		echo "<table border=\"1\" align=\"center\" class=\"leaderboard\">";
		echo "<tr>";
		echo "<th colspan=\"5\">";
		echo "<h2>".$user."'s Custom Puzzle High Scores:</h2>";
		
		//print table header
		echo "<tr>";
		echo "<td class=\"off\" onmouseover=\"this.className='on'\" onmouseout=\"this.className='off'\"><b>Puzzle</b></td>";
		echo "<td class=\"off\" onmouseover=\"this.className='on'\" onmouseout=\"this.className='off'\"><b>Score</b></td>";
		echo "<td class=\"off\" onmouseover=\"this.className='on'\" onmouseout=\"this.className='off'\"><b>Time</b></td>";
		echo "<td class=\"off\" onmouseover=\"this.className='on'\" onmouseout=\"this.className='off'\"><b>Medal</b></td>";
		echo "</tr></th>";
		
		$query = "SELECT score, time, medal, puzzle, user FROM HighScores WHERE user='".$user."' AND medal='Custom' ORDER BY puzzle";
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
		
		/* Check for "World Champion Achievement"
		$worldChamp = false;
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
					if($user == $row2['user'])
					{
						$worldChamp = true;
					}
				}
			}
		}
		//if they have a global high score ($worldChamp is true), write to table
		if($worldChamp)
		{
			echo "<tr><td align=\"center\" class=\"off\" onmouseover=\"this.className='on'\" onmouseout=\"this.className='off'\" title=\"Earn a Spot on the Leaderboard\"><b>World Champion</b></td></tr>";
		} */
		
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
					echo "<tr title=\"".$row['text']."\"><td align=\"center\" class=\"off\" onmouseover=\"this.className='on'\" onmouseout=\"this.className='off'\" >".$row['name']."</td></tr>";
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
	
	function getMyAchievements($db, $user)
	{
	
		//create table
		echo "<table border=\"1\" align=\"center\" class=\"leaderboard\">";
		echo "<tr>";
		echo "<th colspan=\"2\">";
		echo "<h2>".$user."'s Achievements:</h2></th></tr>";
		
		//create table header
		echo "<td colspan=\"2\" align=\"center\"><h4>Italics indicates you have earned the Achievement</h4></td>";
		echo "<tr>";
		echo "<td class=\"off\" onmouseover=\"this.className='on'\" onmouseout=\"this.className='off'\"><b>Achievement</b></td>";
		echo "<td class=\"off\" onmouseover=\"this.className='on'\" onmouseout=\"this.className='off'\"><b>Description</b></td>";
		echo "</tr></th>";
		
		
		/* Check for "World Champion Achievement"
		$worldChamp = false;
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
					if($user == $row2['user'])
					{
						$worldChamp = true;
					}
				}
			}
		}
		//if they have a global high score ($worldChamp is true), write to table
		if($worldChamp)
		{
			echo "<tr><td align=\"center\" class=\"off\" onmouseover=\"this.className='on'\" onmouseout=\"this.className='off'\" title=\"Earn a Spot on the Leaderboard\"><b>World Champion</b></td></tr>";
		} */
		
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
					echo "<tr title=\"".$row['text']."\"><td class=\"off\" onmouseover=\"this.className='on'\" onmouseout=\"this.className='off'\" ><em>".$row['name']."</em></td>";
					echo "<td class=\"off\" onmouseover=\"this.className='on'\" onmouseout=\"this.className='off'\" ><em>".$row['text']."</em></td></tr>";
				}
				else
				{
					echo "<tr title=\"".$row['text']."\"><td class=\"off\" onmouseover=\"this.className='on'\" onmouseout=\"this.className='off'\" >".$row['name']."</td>";
					echo "<td class=\"off\" onmouseover=\"this.className='on'\" onmouseout=\"this.className='off'\" >".$row['text']."</td></tr>";
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
	
	function sendHTMLemail($HTML,$from,$to,$subject)
	{
	// First we have to build our email headers
	// Set out "from" address

		$headers = "From: $from\r\n"; 

	// Now we specify our MIME version

		$headers .= "MIME-Version: 1.0\r\n"; 

	// Create a boundary so we know where to look for
	// the start of the data

		$boundary = uniqid("HTMLEMAIL"); 
		
	// First we be nice and send a non-html version of our email
		
		$headers .= "Content-Type: multipart/alternative;".
					"boundary = $boundary\r\n\r\n"; 

		$headers .= "This is a MIME encoded message.\r\n\r\n"; 

		$headers .= "--$boundary\r\n".
					"Content-Type: text/plain; charset=ISO-8859-1\r\n".
					"Content-Transfer-Encoding: base64\r\n\r\n"; 
					
		$headers .= chunk_split(base64_encode(strip_tags($HTML))); 

	// Now we attach the HTML version

		$headers .= "--$boundary\r\n".
					"Content-Type: text/html; charset=ISO-8859-1\r\n".
					"Content-Transfer-Encoding: base64\r\n\r\n"; 
					
		$headers .= chunk_split(base64_encode($HTML)); 

	// And then send the email ....

		if(mail($to,$subject,"",$headers))
		{
			
		}
		else
		{
		}
    
	}
	
	function getHeader($userid)
	{
		echo "<div id=\"headerDiv\" name=\"headerDiv\">
			Bead Loom Game<br />
			<h3>Welcome $userid !</h3>
			<h4>All Done Playing? <a href=\"logout.php\" class=\"header\">Click here to logout!</a></h4>
		</div>";
	
	}


?>