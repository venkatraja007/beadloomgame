<?php
		include('config.php');
		include('connect.php');
		
		session_start();
		
		
		if(isset($_SESSION['userid']))
		{
			$uid = $_SESSION['userid'];
		}
?>

<html>
<head>
		<title>Bead Loom Game - Feedback</title>
		<link rel="SHORTCUT ICON" href="http://www.uncc.edu/uncc.ico" type="image/x-icon" />
		<link rel="stylesheet" type="text/css" href="beadloomstyles.css" />

		<script type="text/javascript">			
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
	//gather information from form
	$uid = $_POST['userid'];
	$fname = $_POST['firstname'];
	$lname = $_POST['lastname'];
	$email = $_POST['email'];
	$difficulty = $_POST['difficulty'];
	$content = $_POST['educationalContent'];
	$funLevel = $_POST['funLevel'];
	$comments = $_POST['comments'];
	
	//create query to write to database
	$query = "INSERT INTO Feedback (user, firstName, lastName, email, difficulty, content, funLevel, comments) VALUES ('$uid', '$fname', '$lname', '$email', '$difficulty', '$content', '$funLevel', '$comments')";
	$result = $db->query($query);
	if($result)
	{
		echo "<script type=\"text/javascript\">
				alert('Thank you for your feedback! You are being redirected back to the main page!');
				redirect('http://www.unccmakesgames.com/games/BeadLoomGame');
				</script>";
				
	}
	else
	{
		echo "Uh oh! Theres been an error processing your feedback! Error message: ".mysqli_error($db);
		die;
	}
	
	//generate data for email
	$to = "shaun.pickford@gmail.com";
	$subject = "Bead Loom Feedback";
	$body = "Username: ".$uid."\nFirst Name: ".$fname."\nLast Name: ".$lname."\nEmail: ".$email."\nDifficulty: ".$difficulty."\nContent: ".$content."\nFun Level: ".$funLevel."\nComments: ".$comments;
	$headers = "From : Bead Loom Website\r\n";
	if(mail($to, $subject, $body, $headers))
	{
		
	}
	else
	{
	
	}
	$db->close();
}
?>


	<br/>
	<form name="feedbackForm" id="feedbackForm" method="post" action="feedback.php" onsubmit="return validateInfo(this)">
		<table name="mainTable" id="mainTable" class="registrationTable" width="800" border="0" align="center">
			<tr>
				<th colspan="2">Bead Loom Game - Feedback</th>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<h2 class="registrationTable">Tell Us What You Think!</h2>
				</td>
			</tr>
			<tr>
				<td align="center" colspan="2"><h3 class="registrationTable">Why should I give you feedback?</h3></td>
			</tr>
			<tr>
				<td align="center" colspan="2"><p class="text">Bead Loom is a Culturally Situated Design Tool (CSDT) that was created to educate basic Computer Science concepts and a programmatic way of thinking those with little to no knowledge of Computer Science and Computer Science related concepts. The project is funded by the Game2Learn lab in the College of Computing and Informatics at the University of North Carolina at Charlotte. We ask for your feedback so that we can correct issues and improve the overall quality of the game. Your information is kept confidential and is only used for research and educational related purposes. We appreciate any and all feedback you provide!</p><br /></td>
			</tr>
			<tr>
				<td align="center" colspan="2"><h3 class="registrationTable">About Yourself<br /></h3></td>
			</tr>
			<tr>
				<td width="400" align="right"><b>Username:</b><br /></td>
				<td width="400" align="left"><input type="text" maxlength="20" name="userid" id="userid" value="<?php echo $uid ?>"/><br />
				<h4>(please use the Username that you registered with!)</h4></td>
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
				<td width="400" align="right">Email Address:</td>
				<td width="400" align="left"><input type="text" maxlength="40" name="email" id="email" value="<?php echo $_POST['firstname'] ?>"/></td>
			</tr>
			<br /><br />
			<tr>
				<td align="center" colspan="2"><h3 class="registrationTable">Rate the Game!</h3><h4>(1 being the lowest, 5 being the highest)</h4></td>
			</tr>
			<tr>
				<td width="400" align="right">Game Difficulty:</td>
				<td width="400" align="left">
					<input type="radio" checked="checked" name="difficulty" id="difficulty" value="1"/>1
					<input type="radio" name="difficulty" id="difficulty" value="2"/>2
					<input type="radio" name="difficulty" id="difficulty" value="3"/>3
					<input type="radio" name="difficulty" id="difficulty" value="4"/>4
					<input type="radio" name="difficulty" id="difficulty" value="5"/>5
					<br />
				</td>
			</tr>
			<tr>
				<td width="400" align="right">Educational Content:</td>
				<td width="400" align="left">
					<input type="radio" checked="checked" name="educationalContent" id="educationalContent" value="1"/>1
					<input type="radio" name="educationalContent" id="educationalContent" value="2"/>2
					<input type="radio" name="educationalContent" id="educationalContent" value="3"/>3
					<input type="radio" name="educationalContent" id="educationalContent" value="4"/>4
					<input type="radio" name="educationalContent" id="educationalContent" value="5"/>5
					<br />
				</td>
			</tr>
			<tr>
				<td width="400" align="right">Fun Level:</td>
				<td width="400" align="left">
					<input type="radio" checked="checked" name="funLevel" id="funLevel" value="1"/>1
					<input type="radio" name="funLevel" id="funLevel" value="2"/>2
					<input type="radio" name="funLevel" id="funLevel" value="3"/>3
					<input type="radio" name="funLevel" id="funLevel" value="4"/>4
					<input type="radio" name="funLevel" id="funLevel" value="5"/>5
				</td>
			</tr>
			<br /><br />
			<tr>
				<td align="center" colspan="2"><h3 class="registrationTable">Give Us Your Thoughts!<br /></h3></td>
			</tr>
			<tr>
				<td width="400" align="right"><b>Comments:</b><br /></td>
				<td width="400" align="left"><textarea rows="10" cols="20" name="comments" id="comments"/>Enter your comments here!</textarea><br />
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