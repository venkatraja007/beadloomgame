<?php
		include('config.php');
		include('connect.php');
		include('functions.php');
		
		session_start();
		if(!isset($_SESSION['userid']))
		{
			header('Location: http://www.unccmakesgames.com/games/BeadLoomGame/login.php');
		}
		
		$userid = $_SESSION['userid'];
?>
<html>
<head>
	<title>Bead Loom Game</title>
	<link rel="SHORTCUT ICON" href="http://www.uncc.edu/uncc.ico" type="image/x-icon" />
	<link rel="stylesheet" type="text/css" href="beadloomstyles.css" />
	
	<script type="text/javascript">
	
	function playGame()
	{
			window.location = "http://unccmakesgames.com/games/BeadLoomGame/BeadLoomApplet.php";
	}
	function puzzleSelect()
	{
		var selectBox = document.getElementById("puzzle").value;
		window.location = "http://unccmakesgames.com/games/BeadLoomGame/scores.php?puzzleName=" + selectBox;
	}
	
	</script>
</head>

<body>

	<div id="mainDiv" name="mainDiv">
		<div id="headerDiv" name="headerDiv">
			Bead Loom Game<br />
			<h3>Welcome <?php echo "$userid" ?>!<br />We're still under construction, so bear with us!!</h3>
			<h4>All Done Playing? <a href="logout.php" class="header">Click here to logout!</a></h4>
		</div>
		<div id="leftDiv" name="leftDiv">
			<div id="playGame" name="playGame">
				Play Bead Loom Game!<br />
				<input type="button" value="Play!" onclick="playGame()" width="50px"/>
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
			</div>
		</div>
		<div id="rightDiv" name="rightDiv"> 
			<?php
				getLeaderBoard($db);
			?>
		</div>
		<div id="footer" name="footer">
			<h4>Copyright 2010, University of North Carolina at Charlotte. College of Computing and Informatics.</h4>
		</div>
	</div>

</body>

</html>