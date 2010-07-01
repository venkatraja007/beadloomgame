<?php
		session_start();
		include('config.php');
		include('connect.php');
?>

<html>
<head>
		<title>Bead Loom Game - Instructions</title>
		<link rel="SHORTCUT ICON" href="http://www.uncc.edu/uncc.ico" type="image/x-icon" />
		<link rel="stylesheet" type="text/css" href="beadloomstyles.css" />

		<script type="text/javascript">
			function playGame()
			{
				window.open('http://unccmakesgames.com/games/BeadLoomGame/BeadLoomApplet.php');
			}
			function redirect(url)
			{
				window.location = url;
		
			}
		</script>
</head>

<body>
	<br/>
		<table name="mainTable" id="mainTable" class="registrationTable" width="800" border="0" align="center">
			<tr>
				<th>Bead Loom Game</th>
			</tr>
			<tr>
				<td align="center">
					<h2 class="registrationTable">How To Play</h2>
				</td>
			</tr>
			<tr>
				<td align="center"><h3 class="registrationTable">Objective:</h3></td>
			</tr>
			<tr>
				<td align="center"><p class="text">Given a goal image on the left grid, your goal is to try to recreate it on the right grid in the fewest possible moves using the tools provided!<br /><br /></p></td>
			</tr>
			<tr>
				<td align="center"><h3 class="registrationTable">Achievements:</h3></td>
			</tr>
			<tr>
				<td align="center"><p class="text">Earn Medals by completing puzzles in the fewest possible moves!<br />
				<b>Platinum Medal:</b> Solve the puzzle with the ideal number of moves.<br />
				<b>Gold Medal:</b> Solve the puzzle in 1.5 x the ideal number of moves.<br />
				<b>Silver Medal:</b> Solve the puzzle in 2 x the ideal number of moves.<br />
				<b>Bronze Medal:</b> Solve the puzzle in any number of moves.<br />
				Earn other Achievements in the game by accomplishing specific milestones.<br /><br /></p></td>
				
			</tr>
			<tr>
				<td align="center"><h3 class="registrationTable">Pro-Tips:</h3></td>
			</tr>
			<tr>
				<td align="center"><p class="text">
				<ul id="tipsList">
					<li><p class="list">Most puzzles have a background. It is easier to start with this, rather than trying to add it at the end. This is the basis of layering.</p></li>
					<li><p class="list">Stuck? Turn Hints on in the Options Menu!</p></li>
					<li><p class="list">Learn Iteration! You will need it to earn the Platinum medal of most of the puzzles!</p></li>
					<li><p class="list">Click on a bead to pick its color instead of using the color chooser at the top! This is especially helpful if you're color-impaired!</p></li>
				</ul></p></td>
			</tr>
			<tr>
				<td align="center"><p class="text">Still unsure? Check out the original Bead Loom Tool <a href="http://csdt.rpi.edu/na/loom/index.html" class="table">here</a> to familarize yourself with the concepts!
				<br /><br />
				</td>
			</tr>
			<tr>
				<td align="center">Ready to play now?<br />
				<input type="button" value="Click to Play!" onclick="playGame()" width="50px"/>
				</td>
			</tr>
			<tr id="footer">
					<td><h4>Copyright 2010, University of North Carolina at Charlotte. College of Computing and Informatics.</h4></td>
			</tr>
		</table>
</body>
</html>