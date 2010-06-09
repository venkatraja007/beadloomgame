<?php
		include('config.php');
		include('connect.php');
		
		session_start();
		
		$userid = $_SESSION['userid'];
		if(isset($_SESSION['userid']))
		{
			header('Location: http://www.unccmakesgames.com/games/BeadLoomGame/BeadLoomApplet.php?name=' + $userid);
		}
		?>

<html>
<head>
		<title>Bead Loom Game - Registration</title>
		<link rel="SHORTCUT ICON" href="http://www.uncc.edu/uncc.ico" type="image/x-icon" />
		<link rel="stylesheet" type="text/css" href="beadloomstyles.css" />
		
		
		<script>
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
				
				/* Check that username is > 3 */
				if(userID.length < 3)
				{
					alert('Username must be at least 3 characters!');
					valid = false;
				}
				
				/* Check that confirm password and password match  */
				var password = form.password.value;
				var confirmPassword = form.confirmPassword.value;
				if(password != confirmPassword)
				{
					alert('Password and Confirm Password values do not match!');
					valid = false;
				}
				
				/* If information is valid */
				if(valid)
				{
					document.forms["registrationForm"].submit();
				}
			}
		</script>
</head>

<body>
<?php

if(isset($_POST['submitButton']))
{
	// This file handles the input from the registration form passed in $_POST
	$uid = $_POST['userid'];
	$password = $_POST['password'];
	$confirmpass = $_POST['confirmPassword'];
	$lname = $_POST['lastname'];
	$fname = $_POST['firstname'];
	$age = $_POST['age'];
	$gender = $_POST['gender'];

	//Check the uid and password fields
	if(empty($uid) || empty($password))
	{
		echo "<h2>User ID or Password field is blank.</h2><br>";
		die("<input type='button' value='BACK' onclick='history.back()'/>");
	}
	//Check the password and confirmPassword fields
	if($password != $confirmpass)
	{
		echo "<h2>The Passwords do not match.</h2><br>";
		die("<input type='button' value='BACK' onclick='history.back()'/>");
	}
	//Check if the uid is already registered
	$verifyQuery = "SELECT count(*) as count FROM Users WHERE user='$uid' ";
	$result = $db->query($verifyQuery);

	$row = $result->fetch_assoc();
	if($row['count'] == '1')
	{
		echo "<h2>Uh oh! The user $uid already exists! Please try another username! </h2><br>";
	}
	//Add the user to the database and check for errors
	$query = "insert into users values('$uid', '$password', '$lname', '$fname', '$age', '$gender', '$dept', '$iknow')";
	$result = $db->query($query);
	if($result)
	{
		echo $db->affected_rows." user succesfully inserted into the database<br />";
	}
	else
	{
		echo "An error has occurred. ".mysqli_error($db)."<br>";
		die("<input type='button' value='BACK' onclick='history.back()'/>");
	}
	//Link to the login page.
	echo "<a href='login.php'>Login Page</a>";
	$db->close();

}
?>


	<br/>
	<form name="registrationForm" id="registrationForm" method="post" action="registration.php">
		<table name="mainTable" id="mainTable" class="registrationTable" width="800" border="0">
		<tr>
			<th colspan="2">Bead Loom Game</th>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<h2 class="registrationTable">Register for Bead Loom Game!</h2>
			</td>
		</tr>
		<tr>
			<td align="center" colspan="2"><h3 class="registrationTable">Why am I required to register for Bead Loom?</h3></td>
		</tr>
		<tr>
			<td align="center" colspan="2"><p class="text">Bead Loom is a Culturally Situated Design Tool (CSDT) that was created to educate basic Computer Science concepts and a programmatic way of thinking those with little to no knowledge of Computer Science and Computer Science related concepts. The project is funded by the Game2Learn lab in the College of Computing and Informatics at the University of North Carolina at Charlotte. We ask for registration so that we can gather information about the players of the game for use in our research, analysis and improvement of the Bead Loom Game. Your information is kept confidential and is only used for research and educational related purposes. Personal information is not required, but is encouraged to assist us in our research and demographic analysis of the tool.</p><br /></td>
			<tr>
				<td align="center" colspan="2"><p class="text"><b>Required Fields are Listed in Bold</b><br /></td>
			</tr>
			<tr>
				<td width="400" align="right"><b>Username:</b></td>
				<td width="400" align="left"><input type="text" maxlength="20" name="userid" id="userid" value="<?php echo $_POST['userid'] ?>"/></td>
			</tr>
			<tr>
				<td width="400" align="right"><b>Password:</b></td>
				<td width="400" align="left"><input type="password" maxlength="20" name="password" id="password"/></td>
			</tr>
			<tr>
				<td width="400" align="right"><b>Confirm Password:</b></td>
				<td width="400" align="left"><input type="password" maxlength="20" name="confirmPassword" id="confirmPassword"/></td>
			</tr>
			<tr>
				<td width="400" align="right">Last Name:</td>
				<td width="400" align="left"><input type="text" maxlength="20" name="lastname" id="lastname" value="<?php echo $_POST['lastname'] ?>"/></td>
			</tr>
			<tr>
				<td width="400" align="right">First Name:</td>
				<td width="400" align="left"><input type="text" maxlength="20" name="firstname" id="firstname" value="<?php echo $_POST['firstname'] ?>"/></td>
			</tr>
			<tr>
				<td width="400" align="right">Age:</td>
				<td width="400" align="left"><input type="text" maxlength="3" name="age" id="age" value="<?php $_POST['age'] ?>"/></td>
			</tr>
			<tr>
				<td width="400" align="right">Gender:</td>
				<td width="400" align="left">
					<input type="radio" checked="checked" name="gender" id="gender" value="male"/>Male
					<input type="radio" name="gender" id="gender" value="female"/>Female
					<br />
				</td>
			</tr>
			<tr>
				<td align="center" colspan="2">
					<input type="button" name="submitButton" id="submitButton" value="Submit" onclick="validateInfo(document.registrationForm)" />
					<input type="reset" name="reset" id="reset" value="Reset"/>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>