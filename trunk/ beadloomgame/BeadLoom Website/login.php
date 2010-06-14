<?php
		include('config.php');
		include('connect.php');
		
		session_start();
		
		$userid = $_SESSION['userid'];
		if(isset($_SESSION['userid']))
		{
			header('Location: http://www.unccmakesgames.com/games/BeadLoomGame/index.php');
		}
		
		if(isset($_POST['btnLogin']))
		{
			/* Handle Login Information */
			$uid = $_POST['username'];
			$password = $_POST['password'];
			$password_md5 = md5($password);
			
			/* Get username and password from database */
			$verifyQuery = "SELECT password FROM Users WHERE user='$uid' ";
			$result = $db->query($verifyQuery);
			$row = $result->fetch_assoc();
			$tempPassword = $row['password'];
			
			/* Compare Passwords */
			if($tempPassword == $password_md5)
			{
				header("Location: http://www.unccmakesgames.com/games/BeadLoomGame/index.php");
				$_SESSION['userid'] = $uid;
			}
			else
			{
				echo "<script type=\"text/javascript\">
						alert('Invalid password! Please retry!')
						</script>";
			}

		}
?>

<html>
	<head>
		<title>Bead Loom Game - Login</title>
		<link rel="SHORTCUT ICON" href="http://www.uncc.edu/uncc.ico" type="image/x-icon" />
		<link rel="stylesheet" type="text/css" href="beadloomstyles.css" />
		
		
	</head>

	<body>
		<form id="loginForm" method="post" action="login.php">
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
				<td align="left" width="400"><input type="password" maxlength="30" name="password" id="password" />
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
				<td align="center" colspan="2" class="forgotPassword">Forgot Your Password? <a href="forgotPassword.php">Click Here to Recover!</a></td>
			</tr>
			
			</table>
		
		
		</form>
	
	
	</body>



</html>