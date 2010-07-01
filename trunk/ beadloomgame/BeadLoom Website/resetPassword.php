<?php

		include('config.php');
		include('connect.php');
		
		session_start();
?>
<html>
<head>
		<title>Bead Loom Game - Reset Password</title>
		<link rel="SHORTCUT ICON" href="http://www.uncc.edu/uncc.ico" type="image/x-icon" />
		<link rel="stylesheet" type="text/css" href="beadloomstyles.css" />

		<script type="text/javascript">
			function redirect(url)
			{
				window.location = url;
		
			}
		</script>
</head>
<?php


		$userid = $_GET['username'];
		$tempPassword = $_GET['tempPassword'];
		

		if(isset($_POST['submitButton']))
		{
			
			$valid = false;
			/* Gather information from registration form */
			$newPassword = $_POST['newPassword'];
			$confirmNewPassword = $_POST['confirmNewPassword'];
			$tempPasswordTextBox = $_POST['tempPasswordBox'];
			$password_md5 = md5($newPassword);

			/* Get info from database */
			$getPasswordQuery = "SELECT user, password, tempPassword FROM Users WHERE user='$userid' ";
			$result = $db->query($getPasswordQuery);
			$row = $result->fetch_assoc();
			
			/* Make sure tempPassword from DB is not 0 (meaning the user did actually initiate a password reset) */
			if($row['tempPassword'] == 0)
			{
				echo "<center>Your temporary password has not been set! Please click <a href=\"forgotPassword.php\">here</a> to initiate a password reset.</center>";
				$valid = false;
			}
			else
			{
				$valid = true;
			}
			
			/* Check that temp password & username matches the password in the database */
			if($valid)
			{
				if($row['tempPassword'] != $tempPassword || $row['user'] != $userid)
				{
					echo "<center><h3>Uh oh! The temporary password and username did not match! Please check your email and validate that it is the correct password!</h3></center><br />";
					$valid = false;
				}
				else
				{
					$valid = true;
				}
			}
			
			/* Make sure that the temporary password in the text box matches the one provided */
			if($valid)
			{
				if($tempPassword != $tempPasswordTextBox)
				{
					echo "<center><h3>Uh oh! The temporary password and in the text field did not match the one provided! Please check your email and validate that it is the correct password!</h3></center><br />";
					$valid = false;
				}
				else
				{
					$valid = true;
				}
			}
				
			/* Check that newPassword and confirmNewPasswords match */
			if($valid)
			{
				if($newPassword == $confirmNewPassword)
				{
					$valid = true;
				}
				else
				{
					echo "<script type=\"text/javascript\">
						alert('Password and Confirm Password's do not match! Please try again!');
						</script>";
					$valid = false;
				}
			}
			
			/* If all checks have passed */
			if($valid)
			{
				//Update database with new password
				$query = "UPDATE Users SET password = '$password_md5', tempPassword = '0' WHERE user = '$userid' ";
				$result = $db->query($query);
				if($result)
				{
					echo "<script type=\"text/javascript\">
					alert('Your password has been changed!');
					redirect(\"http://www.unccmakesgames.com/games/BeadLoomGame/login.php\");
					</script>";
				}
				else
				{
					echo "Uh oh! There's been an error changing your password! Error Message: ".mysqli_error($db);
				}
			}
		}
			$db->close();
?>

<body>
	<form name="registrationForm" id="registrationForm" method="post" action="resetPassword.php?username=<?php echo $userid; ?>&tempPassword=<?php echo $tempPassword; ?>" onsubmit="return validateInfo(this)">
		<table name="mainTable" id="mainTable" class="registrationTable" width="800" border="0" align="center">
		<tr>
			<th colspan="2">Bead Loom Game</th>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<h2 class="registrationTable">Reset Password</h2><br /><h3>Fill in the boxes below with your new password.</h3>
			</td>
		</tr>
			<tr>
				<td width="400" align="right">Temporary Password:</td>
				<td width="400" align="left"><input type="text" maxlength="20" name="tempPasswordBox" id="tempPasswordBox" value="<?php echo $tempPassword ?>"/></td>
			</tr>
			<tr>
				<td width="400" align="right">New Password:</td>
				<td width="400" align="left"><input type="password" maxlength="20" name="newPassword" id="newPassword" /></td>
			</tr>
			<tr>
				<td width="400" align="right">Confirm New Password:</td>
				<td width="400" align="left"><input type="password" maxlength="40" name="confirmNewPassword" id="confirmNewPassword"/></td>
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