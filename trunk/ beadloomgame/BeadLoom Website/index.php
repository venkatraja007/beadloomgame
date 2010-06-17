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
		<div id="headerDiv" name="headerDiv">
			Bead Loom Game<br />
			<h3>Welcome <?php echo "$userid" ?>!<br />We're still under construction, so bear with us!!</h3>
			<h4>All Done Playing? <a href="logout.php" class="header">Click here to logout!</a></h4>
		</div>
		<div id="leftDiv" name="leftDiv">
			<div id="playGame" name="playGame">
				<b>Play Bead Loom Game!<b><br />
				<p class="text">Wondering How to Play?<br />
				<a class="table" href="instructions.php">Click Here for Instructions!</a></p><br />
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
			<h4>If you haven't already, <a class="header" href="feedback.php">please provide us Feedback!</a> Your input is greatly welcomed and appreciated!<br />
			Copyright 2010, University of North Carolina at Charlotte. College of Computing and Informatics.</h4>
		</div>
	</div>

</body>

</html>