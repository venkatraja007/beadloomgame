<?php
$puzzles = file_get_contents("http://unccmakesgames.com/games/BeadLoomGame/customPuzzles.php?token=sitePuzzleList");
$puzzles = explode(",", $puzzles);
for($i=0 ;$i<count($puzzles); $i++)
{
  $puzzles[$i] = "<img src='http://unccmakesgames.com/games/BeadLoomGame/CustomPuzzleImages/".$puzzles[$i].".png'>";
echo $puzzles[$i];
}
return $puzzles;




?>