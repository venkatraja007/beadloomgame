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
				
				/* Check that emails match */
				var email = form.email.value;
				var confirmEmail = form.confirmEmail.value;
				if(email != confirmEmail)
				{
					alert('Email and Confirm Email fields do not match!');
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
	$valid = true;
	/* Gather information from registration form */
	$uid = $_POST['userid'];
	$password = $_POST['password'];
	$confirmpass = $_POST['confirmPassword'];
	$lname = $_POST['lastname'];
	$fname = $_POST['firstname'];
	$age = $_POST['age'];
	$gender = $_POST['gender'];
	$email = $_POST['email'];
	$affiliation = $_POST['affiliation'];
	$occupation = $_POST['occupation'];
	$password_md5 = md5($password);

	/*  Check User ID and Password Fields */
	if(empty($uid) || empty($password))
	{
		echo "<center><h3>Username or Password field cannot be blank! Please retry!</h3></center><br>";
		$valid = false;
	}
	/* Check the password and confirmPassword fields */
	if($password != $confirmpass)
	{
		echo "<center><h3>The Passwords do not match! Please retry!</h3></center><br>";
		$valid = false;
	}
	/* Check if the uid is already registered */
	$verifyQuery = "SELECT count(*) as count FROM Users WHERE user='$uid' ";
	$result = $db->query($verifyQuery);
	$row = $result->fetch_assoc();
	if($row['count'] == 1)
	{
		echo "<center><h3>Uh oh! The Username $uid already exists! Please try another username! </h3></center><br />";
		$valid = false;
	}
	
	if($valid)
	{
		//Add the user to the database and check for errors
		$query = "INSERT INTO Users (user, password, firstName, lastName, email, age, gender, affiliation, occupation) VALUES ('$uid', '$password_md5', '$fname', '$lname', '$email', '$age', '$gender', '$affiliation', '$occupation')";
		$result = $db->query($query);
		if($result)
		{
			$_SESSION['userid'] = $uid;
			echo "<script type=\"text/javascript\">
					alert('$uid has been succesfully registered! Redirecting you to the home page!')
					redirect(\"http://www.unccmakesgames.com/games/BeadLoomGame/index.php\");
					</script>";
					
		}
		else
		{
			echo "<center><h3>Uh oh! An error has occurred during registration! Please try again!<br />Error message:".mysqli_error($db)."</h3></center><br />";
		}
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
					<h2 class="registrationTable">Register for Bead Loom Game!</h2>
				</td>
			</tr>
			<tr>
				<td align="center" colspan="2"><h3 class="registrationTable">Why am I required to register for Bead Loom?</h3></td>
			</tr>
			<tr>
				<td align="center" colspan="2"><p class="text">Bead Loom is a Culturally Situated Design Tool (CSDT) that was created to educate basic Computer Science concepts and a programmatic way of thinking those with little to no knowledge of Computer Science and Computer Science related concepts. The project is funded by the Game2Learn lab in the College of Computing and Informatics at the University of North Carolina at Charlotte. We ask for registration so that we can gather information about the players of the game for use in our research, analysis and improvement of the Bead Loom Game. Your information is kept confidential and is only used for research and educational related purposes. Personal information is not required, but is encouraged to assist us in our research and demographic analysis of the tool.</p><br /></td>
			</tr>
			<tr>
				<td align="center" colspan="2"><p class="text"><b>Required Fields are Listed in Bold</b><br />
				</td>
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
				<td width="400" align="right">First Name:</td>
				<td width="400" align="left"><input type="text" maxlength="20" name="firstname" id="firstname" value="<?php echo $_POST['firstname'] ?>"/></td>
			</tr>
			<tr>
				<td width="400" align="right">Last Name:</td>
				<td width="400" align="left"><input type="text" maxlength="20" name="lastname" id="lastname" value="<?php echo $_POST['lastname'] ?>"/></td>
			</tr>
			<tr>
				<td width="400" align="right"><b>Email Address:</b></td>
				<td width="400" align="left"><input type="text" maxlength="40" name="email" id="email" value="<?php echo $_POST['firstname'] ?>"/></td>
			</tr>
			<tr>
				<td width="400" align="right"><b>Confirm Email Address:</b></td>
				<td width="400" align="left"><input type="text" maxlength="40" name="confirmEmail" id="confirmEmail" value="<?php echo $_POST['firstname'] ?>"/></td>
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
					<input type="radio" name="gender" id="gender" value="Not Specified"/>Not Specified
					<br />
				</td>
			</tr>
			<tr>
				<td width="400" align="right">Affiliation (Business or University):</td>
				<td width="400" align="left"><input type="text" maxlength="40" name="affiliation" id="affiliation" value="<?php echo $_POST['firstname'] ?>"/></td>
			</tr>
			<tr>
				<td width="400" align="right">Occupation:</td>
				<td width="400" align="left">
					<input type="radio" checked="checked" name="occupation" id="occupation" value="Professor"/>Professor <br />
					<input type="radio" name="occupation" id="occupation" value="Student"/>Student <br />
					<input type="radio" name="occupation" id="occupation" value="IT Professional" />IT Professional <br />
					<input type="radio" name="occupation" id="occupation" value="Other" />Other <br />
					<br />
				</td>
			</tr>
			<tr>
				<td align="center" colspan="2">
					<br /><br />
					<input type="submit" name="submitButton" id="submitButton" value="Submit" />
					<input type="reset" name="reset" id="reset" value="Reset"/>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>