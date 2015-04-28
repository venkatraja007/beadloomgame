# Weekly Report #

1. What was planned?

  * Create puzzle for Bead Loom Game
  * Become familiar with the Bead Loom Game code and Java
  * Add External High Scores Functionality via Database
  * Add buttons and frames to access and display high scores from within Bead Loom Game
  * Create PHP script to access the database on behalf of Bead Loom Game, rather than have Bead Loom Game access the database directly for security reasons, and the need of a JDBC driver.
  * Create a webpage to view high scores on the Bead Loom Game website
  * Create an area for the Bead Loom Game website(soon to be Drupal)
  * Move Database from playground over to unccmakesgames
  * Set up high scores page to be separated by Puzzle

2. What was done?

  * Create puzzle for Bead Loom Game
  * Become familiar with the Bead Loom Game code and Java
  * Add External High Scores Functionality via Database
  * Add buttons and frames to access and display high scores from within Bead Loom Game
  * Create PHP script to access the database on behalf of Bead Loom Game, rather than have Bead Loom Game access the database directly for security reasons, and the need of a JDBC driver.
  * Create a webpage to view high scores on the Bead Loom Game website
  * Create an area for the Bead Loom Game website(soon to be Drupal)

3. Problems encountered?
  * Originally we wanted Bead Loom Game to access the database directly and dynamically create a high scores list using labels on a frame, after a little research this turned out to be a security risk and also we would need a JDBC driver and were unsure how that would be distributed with an online applet.
  * Bead Loom Game gui is coded by hand so added any new gui elements is tedious, also we had some svn overwrites and had to re-do some work once or twice.
  * playground(which we probably will not use soon) does not display PHP errors, if there is an error in a script no matter how small the script simply does not run. Which is not very helpful, even if you enable error reporting at the top of your script. So scripts have to be perfect in order to debug, including SQL errors;

4. What did you learn this week? (esp. computer science, or anything surprising or interesting) Be sure to cite any resources you used (e.g. online tutorials, etc).

  * refresher on Java
  * some useful PHP tricks, like explode() is actually split(who names the classic string split method explode)
  * Applets aren't meant to do anything cool which makes things difficult to get them to do something cool, or simply access a local file

5. What's planned for next week?
  * Move Database from playground over to unccmakesgames
  * Set up high scores page to be separated by Puzzle
  * Familiarize with Unity

6. Hours worked.
  * 36.5