<?php
		session_start();
		if(isset($_SESSION['userid']))
		{
			$userName = $_SESSION['userid'];
		}
		else
		{
			header("Location: http://unccmakesgames.com/games/BeadLoomGame/login.php");
		}
?>		

<html>
	<head>
		<title>Bead Loom Applet</title>
		<link rel="SHORTCUT ICON" href="http://www.uncc.edu/uncc.ico" type="image/x-icon" />
		<link rel="stylesheet" type="text/css" href="beadloomstyles.css" />

	</head>
	
	<body>
		<h1><center>Bead Loom Game!</center></h1>
		
		<center><applet code = 'src.mainpackage.BeadLoom'
			archive = 'BeadLoom.jar, xom-1.1.jar',
			width = 1024,
			height = 768 >
			
			<PARAM NAME="name" VALUE="<?php echo $userName; ?>">
			
			</applet>
			
			</center>
			
	</body>
</html>