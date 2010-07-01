<?php
		include('config.php');
		include('connect.php');
		include('functions.php');
		
		session_start();
		
		$userid = $_SESSION['userid'];
		if(isset($_SESSION['userid']))
		{
			header("Location: http://www.unccmakesgames.com/games/BeadLoomGame/index.php");
		}
?>

<html>
<head>
		<title>Bead Loom Game - Forgot Password</title>
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
	if(empty($uid))
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
	
	if($valid)
	{
		//create new temp password and write to database
		$tempPassword = rand(0,30000);
		$HTML = "So you forgot your password? Geez! No worries though, we've got you covered!<br /><br />Your temporary password is: $tempPassword <br />Don't forget it! Actually, you don't need to remember it at all. Just click the link below and reset your password!<br /><br /><a href=\"http:/www.unccmakesgames.com/games/BeadLoomGame/resetPassword.php?tempPassword=".$tempPassword."&username=".$uid."\">Click here to reset your password!</a>";
		$from = "Bead Loom Website";
		$subject = "Forgot Password";
		
		$query2 = "UPDATE Users SET tempPassword = '$tempPassword' WHERE user = '$uid' ";
		$result2 = $db->query($query2);
		//$row2 = $result2->fetch_assoc();
		if($result2)
		{
			//send email
			sendHTMLemail($HTML,$from,$email,$subject);
			echo "<script type=\"text/javascript\">
					alert('Your password has been reset! An email with your temporary password and instructions for reset has been sent to you!');
					redirect(\"http://www.unccmakesgames.com/games/BeadLoomGame/login.php\");
					</script>";
		}
	}
	
	
	$db->close();
}
?>


	<br/>
	<form name="registrationForm" id="registrationForm" method="post" action="forgotPassword.php" onsubmit="return validateInfo(this)">
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
				<td width="400" align="right">Username:</td>
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