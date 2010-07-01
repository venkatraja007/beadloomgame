<?php
		session_start();
		include('config.php');
		include('connect.php');
		include('functions.php');
		
		
		if(isset($_SESSION['userid']))
		{
			$userid = $_SESSION['userid'];
		}
		else
		{
			header('Location: http://www.unccmakesgames.com/games/BeadLoomGame/login.php');
		}
?>
<html>
<head>
	<title>Bead Loom Game</title>
	<link rel="SHORTCUT ICON" href="http://www.uncc.edu/uncc.ico" type="image/x-icon" />
	<link rel="stylesheet" type="text/css" href="beadloomstyles.css" />
	
	<script type="text/javascript">
	
	function playGame()
	{
		document.location = "http://unccmakesgames.com/games/BeadLoomGame/BeadLoomApplet.php";
	}
	
	</script>
</head>

<body>

	<div id="mainDiv" name="mainDiv">
		<?php getHeader($userid); ?>
		<div id="leftDiv" name="leftDiv">
			<div id="playGame" name="playGame">
				<b>Play Bead Loom Game!<b><br />
				<p class="text">Wondering How to Play?<br />
				<p><a class="table" href="instructions.php">Click Here for Instructions!</a></p><br />
				<a class="table" href="puzzleScores.php">High Scores</a></p><br />
				<a href="BeadLoomApplet.php" class="table"><img src="button1" border="0" onmouseover="this.src='button2'" onmouseout="this.src='button1'" onclick="this.src='button3'"/></a>
			</div>
			<div id="myScores" name="myScores">
				<?php
					getUserScores($db, $userid);
				?>
			</div>
			<div id="myAchievements" name="myAchievements">
				<?php
					getAchievements($db, $userid);
				?>
			</div>
			<div id="myCustomPuzzles" name="myCustomPuzzles">
				<?php
					getCustomPuzzleScores($db, $userid) 
				?>
			</div>
		</div>
		<div id="rightDiv" name="rightDiv"> 
			<?php
				getLeaderBoard($db);
			?>
		</div>
		<div id="footer" name="footer">
			<h4>If you haven't already, <a class="header" href="feedback.php">please provide us Feedback!</a> Your input is greatly welcomed and appreciated!<br />
			Copyright 2010, University of North Carolina at Charlotte. College of Computing and Informatics.</h4>
		</div>
	</div>

</body>

</html>