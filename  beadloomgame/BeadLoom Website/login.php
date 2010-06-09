<html>
	<head>
		<title>Bead Loom Game - Login</title>
		<link rel="SHORTCUT ICON" href="http://www.uncc.edu/uncc.ico" type="image/x-icon" />
		<link rel="stylesheet" type="text/css" href="beadloomstyles.css" />
		
		
	</head>

	<body>
		<form id="loginForm" method="post" action="loginHandler.php">
			<table width="800" align="center" id="mainTable">
			<tr><th colspan="2">Login</th></tr>
			<tr><td colspan="2" align="center"><h2>Please Log In to Play the Game!</h2><br /><br /></td></tr>
			<tr>
				<td align="right" width="400">Username:</td>
				<td align="left" width="400"><input type="text" maxlength="30" name="username" id="username" />
				</td>
			</tr>
			<tr>
				<td align="right" width="400">Password:</td>
				<td align="left" width="400"><input type="text" maxlength="30" name="password" id="password" />
				</td>
			</tr>
			<tr>
				<td align="center" colspan="2"><input type="submit" value="Login" name="btnLogin" id="btnLogin" /> <br /><br />
				</td>
			</tr>
			<tr>
				<td align="center" colspan="2">Don't have a login? <a href="registration.php">Register Here!</a> <br /><br /></td>
			</tr>
			<tr>
				<td align="center" colspan="2" class="forgotPassword">Forgot Your Password? <a href="recoverPassword.php">Click Here to Recover!</a></td>
			</tr>
			
			</table>
		
		
		</form>
	
	
	</body>



</html>