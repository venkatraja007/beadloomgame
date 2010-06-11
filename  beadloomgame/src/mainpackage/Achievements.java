package src.mainpackage;


public abstract class Achievements {
	
	//Total Number of achievements according to the databse
	final static int TOTAL_ACHIEVEMENTS = 
		Integer.parseInt(GUIGameTools.sendWebRequest("http://www.unccmakesgames.com/games/BeadLoomGame/achievements.php?token=num"));
	//acheivement index the current user has
	static int[] currentAchievements = new int[TOTAL_ACHIEVEMENTS];
	//achievement names the current user has
	static String[] currentAchievementNames = new String[TOTAL_ACHIEVEMENTS];
	
	
	public static void retrieveAchievements(String user)
	{
		String achievements = GUIGameTools.sendWebRequest(
				"http://www.unccmakesgames.com/games/BeadLoomGame/achievements.php?token=retrieve&user=" + user);
		String[] achievementNames = GUIGameTools.sendWebRequest(
				"http://www.unccmakesgames.com/games/BeadLoomGame/achievements.php?token=names").split(",");
		
		for(int i=0; i<currentAchievements.length; i++)
		{
			currentAchievements[i] = Integer.parseInt((achievements.charAt(i) + ""));
			currentAchievementNames[i]  = achievementNames[i];
			System.out.println(currentAchievements[i] + "---" + currentAchievementNames[i]);
		}
	}
}
