<?php
		include('config.php');
		include('connect.php');
		include('functions.php');
		
		session_start();
		if(isset($_SESSION['userid']))
		{
			$userid = $_SESSION['userid'];
		}
		else
		{
			header("Location: http://unccmakesgames.com/games/BeadLoomGame/login.php");
		}
		
		
?>
<html>
<head>
	<title>Bead Loom Game - My Achievements</title>
	<link rel="SHORTCUT ICON" href="http://www.uncc.edu/uncc.ico" type="image/x-icon" />
	<link rel="stylesheet" type="text/css" href="beadloomstyles.css" />
	
	<script type="text/javascript">
	
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
				<b>Play Bead Loom Game!</b><br />
				<h3>Wondering How to Play?<br /><a href="instructions.php" class="table">Click Here for Instructions!</a><br /></h3>
				<input type="button" value="Click to Play!" onclick="playGame()" width="50px"/>
			</div>
			<div id="myScores" name="myScores">
				<?php
					getUserScores($db, $userid);
				?>
			</div>
			<div id="myAchievements" name="myAchievements">
				<?php
					getMyAchievements($db, $userid);
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