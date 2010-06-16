<?php
include('config.php');
include('connect.php');

$token = $_POST['token'];
$user = $_POST['user'];
$query = "
UPDATE Users
SET securityToken='$token'
WHERE user ='$user'";

$result = $db->query($query);
if(!$result)
{
	die("<html><h2>invalid enry</h2></html>");
}
else
{
	die("<html><h2>It worked<br>$query</h2></html>");
}
?>