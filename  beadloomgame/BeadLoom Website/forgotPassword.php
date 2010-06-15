<?php
		include('config.php');
		include('connect.php');
		
		session_start();
		
		$userid = $_SESSION['userid'];
		if(isset($_SESSION['userid']))
		{
			header("Location: http://www.unccmakesgames.com/games/BeadLoomGame/index.php");
		}
?>

<html>
<head>
		<title>Bead Loom Game - Registration</title>
		<link rel="SHORTCUT ICON" href="http://www.uncc.edu/uncc.ico" type="image/x-icon" />
		<link rel="stylesheet" type="text/css" href="beadloomstyles.css" />

		<script type="text/javascript">
			function validateInfo(form)
			{
				var valid = true;
				/* Check that username is not blank*/
				var userID = form.userid.value;
				if(userID == "")
				{
					alert('Username cannot be blank!');
					valid = false;
				}
				
				return valid;
			}
			
			function redirect(url)
			{
				window.location = url;
		
			}
		</script>
</head>

<body>
<?php

if(isset($_POST['submitButton']))
{
	$valid = false;
	/* Gather information from registration form */
	$uid = $_POST['userid'];
	$password = $_POST['password'];
	$email = $_POST['email'];
	$password_md5 = md5($password);

	/*  Check User ID and Password Fields */
	if(empty($uid) || empty($password))
	{
		echo "<center><h3>Username field cannot be blank! Please retry!</h3></center><br>";
		$valid = false;
	}
	else
	{
		$valid = true;
	}
	
	/* Validate email matches the one in the database */
	$userInfo = "SELECT user, password, email FROM Users WHERE user='$uid' ";
	$result = $db->query($userInfo);
	$row = $result->fetch_assoc();
	if($row['email'] != $email)
	{
		echo "<center><h3>Uh oh! The Email did not match the associated email with the Username $userid! Please retry!</h3></center><br />";
		$valid = false;
	}
	else
	{
		$valid = true;
	}
	
	$db->close();
}
?>


	<br/>
	<form name="registrationForm" id="registrationForm" method="post" action="registration.php" onsubmit="return validateInfo(this)">
		<table name="mainTable" id="mainTable" class="registrationTable" width="800" border="0" align="center">
		<tr>
			<th colspan="2">Bead Loom Game</th>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<h2 class="registrationTable">Forgot Your Password?</h2><br /><h3>Fill in the information below to retrieve it!</h3>
			</td>
		</tr>
			<tr>
				<td width="400" align="right"><b>Username:</b></td>
				<td width="400" align="left"><input type="text" maxlength="20" name="userid" id="userid" value="<?php echo $_POST['userid'] ?>"/></td>
			</tr>
			<tr>
				<td width="400" align="right">Email Address:</td>
				<td width="400" align="left"><input type="text" maxlength="40" name="email" id="email" value="<?php echo $_POST['firstname'] ?>"/></td>
			</tr>
			<tr>
				<td align="center" colspan="2">
					<br /><br />
					<input type="submit" name="submitButton" id="submitButton" value="Submit" />
					<input type="reset" name="reset" id="reset" value="Reset"/>
				</td>
			</tr>
			<tr>
				<td align="center" colspan="2"><?php echo "$password"; ?></td>
			</tr>
		</table>
	</form>
</body>
</html>