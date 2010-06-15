<?php
		include('config.php');
		include('connect.php');
		
		session_start();

		$_SESSION = array();

		// Note: This will destroy the session, and not just the session data!
		if (ini_get("session.use_cookies")) {
			$params = session_get_cookie_params();
			setcookie(session_name(), '', time() - 42000,
				$params["path"], $params["domain"],
				$params["secure"], $params["httponly"]
			);
		}

		// Finally, destroy the session.
		session_destroy();
	
?>

<html>
	<head>
		<title>Bead Loom Game - Logout</title>
		<link rel="SHORTCUT ICON" href="http://www.uncc.edu/uncc.ico" type="image/x-icon" />
		<link rel="stylesheet" type="text/css" href="beadloomstyles.css" />
		
		
	</head>

	<body>
		<form id="loginForm" method="post" action="login.php">
			<table width="800" align="center" id="mainTable">
			<tr><th colspan="2">Bead Loom Game</th></tr>
			<tr><td colspan="2" align="center"><h2>You have been successfully logged out!<br />Thanks for playing!</h2><br /><br /></td></tr>
			
			<tr>
				<td align="center" colspan="2">Wanna play more?! <a href="login.php">Click here to log back in!</a><br /><br /></td>
			</tr>		
			</table>
		
		
		</form>
	
	
	</body>



</html>