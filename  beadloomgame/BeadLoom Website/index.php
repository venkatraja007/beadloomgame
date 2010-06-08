<html>

<head>
	<title>Bead Loom Game</title>
	<link rel="SHORTCUT ICON" href="http://www.uncc.edu/uncc.ico" type="image/x-icon" />
	<link rel="stylesheet" type="text/css" href="beadloomstyles.css" />
	
	<script type="text/javascript">
	
	function playGame(form)
	{
		var name = form.nameBox.value;
		if(name == "")
		{
			alert('Please enter your name!');
		}
		else
		{
			window.location = "http://unccmakesgames.com/games/BeadLoomGame/BeadLoomApplet.php?name=" + name;
		}
	}
	
	
	</script>
</head>

<body>
	<form id="mainTable" name="mainTable">
	<table width="800" align="center">
		<tr>
			<th colspan="2"><h2> Bead Loom Game<br /></h2><h3>Coming Soon!!</h3></th>
		</tr>
		<tr>
			<td>Play Bead Loom Game!</td>
			<td><a href="puzzleScores.php">High Scores</a></td>
		</tr>
		<tr>
		<td colspan="2">
			Please enter your name!: <br />
				<input type="text" size="50" id="nameBox" /><br />
				<input type="button" value="Play!" onclick="playGame(document.mainTable)" />
		</td>
	
	</table>
	</form>
</body>

</html>