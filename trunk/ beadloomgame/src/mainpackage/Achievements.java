package src.mainpackage;
import javax.swing.JOptionPane;


public abstract class Achievements {
	
	//Total Number of achievements according to the database
	final static int TOTAL_ACHIEVEMENTS = 
		Integer.parseInt(GUIGameTools.sendWebRequest("http://www.unccmakesgames.com/games/BeadLoomGame/achievements.php?token=num"));
	//acheivement index the current user has
	static int[] currentAchievements = new int[TOTAL_ACHIEVEMENTS];
	//achievement names the current user has
	static String[] currentAchievementNames = new String[TOTAL_ACHIEVEMENTS];
	
	static String[] currentMedals;
	static int[] currentMoves;
	static String[] currentMedalsShort;
	
	
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
			//System.out.println(currentAchievements[i] + "---" + currentAchievementNames[i]);
		}
	}
	
	public static void sendAchievements(String user)
	{
		String achievements = "";
		for(int i=0; i<currentAchievements.length; i++)
		{
			achievements += currentAchievements[i];
		}
		
		String message = GUIGameTools.sendWebRequest(
				"http://www.unccmakesgames.com/games/BeadLoomGame/achievements.php?token=send&user=" + 
				user + "&achievements=" + achievements);
		//JOptionPane.showMessageDialog(null, message, "Achievements Message", JOptionPane.PLAIN_MESSAGE);
	}
	
	public static void retrieveMedals(String[] recordMedals, int[] recordMoves, String[] recordMedalsShort)
	{
		currentMedals = recordMedals;
		currentMoves = recordMoves;
		currentMedalsShort = recordMedalsShort;
	}
	
	public static void checkAchievements()
	{
		PersistentPuzzler();		//0
		SilverPuzzler();			//1
		SoCloseYetSoFar();			//2
		PurePlatinum();				//3
		WorldChampion();			//4
		LearnedTheBasics();			//5
		MasteredTheBasics();		//6
		EasyAsPie();				//7
		HaveYourPieAndEatItToo(); 	//8
		MentalMedium();				//9
		MentalMediumMaster();		//10
		PuzzleMaster();				//11
		AbsolutePuzzleMaster();		//12
		CreativeCat();				//13
		NewIdeal();					//14
		RocketShip();				//15
		SenseSelf();				//16
		SpeedKing();				//17
		
		
		
		PuzzlePerfection();			//21
	}
	
	public static boolean hasAchievement(int achievementNumber)
	{
		return (currentAchievements[achievementNumber] == 1);
	}
	
	public static void viewAchievements()
	{
		String test = "";
		for(int i=0; i<currentAchievements.length; i++)
		{
			test += currentAchievements[i];
		}
		JOptionPane.showMessageDialog(null, test, "Achievements Message", JOptionPane.PLAIN_MESSAGE);
	}
	
	public static void PersistentPuzzler()
	{
		int id = 0;
		//if already obtained do not check
		if(currentAchievements[id] == 0)
		{
			for(int i=0; i< currentMedals.length; i++)
			{
				if(currentMedals[i].equalsIgnoreCase("Bronze!"))
				{
					currentAchievements[id] = 1;
					JOptionPane.showMessageDialog(null, 
							"Congratulations you have earned the 'Persistant Puzzler' Achievement", 
							"Achievements Message", 
							JOptionPane.PLAIN_MESSAGE);
					return;
				}
			}
		}
		else
		{
			return;
		}
	}
	
	public static void SilverPuzzler()
	{
		int id = 1;
		//if already obtained do not check
		if(currentAchievements[id] == 0)
		{
			for(int i=0; i< currentMedals.length; i++)
			{
				if(currentMedals[i].equalsIgnoreCase("Silver!!"))
				{
					currentAchievements[id] = 1;
					JOptionPane.showMessageDialog(null, 
							"Congratulations you have earned the 'Silver Puzzler' Achievement", 
							"Achievements Message", 
							JOptionPane.PLAIN_MESSAGE);
					return;
				}
			}
		}
		else
		{
			return;
		}
	}
	
	public static void SoCloseYetSoFar()
	{
		int id = 2;
		//if already obtained do not check
		if(currentAchievements[id] == 0)
		{
			for(int i=0; i< currentMedals.length; i++)
			{
				if(currentMedals[i].equalsIgnoreCase("Gold!!!"))
				{
					currentAchievements[id] = 1;
					JOptionPane.showMessageDialog(null, 
							"Congratulations you have earned the 'So Close Yet So Far' Achievement", 
							"Achievements Message", 
							JOptionPane.PLAIN_MESSAGE);
					return;
				}
			}
		}
		else
		{
			return;
		}
	}
	
	public static void PurePlatinum()
	{
		int id = 3;
		//if already obtained do not check
		if(currentAchievements[id] == 0)
		{
			for(int i=0; i< currentMedals.length; i++)
			{
				if(currentMedals[i].equalsIgnoreCase("Platinum!!!!"))
				{
					currentAchievements[id] = 1;
					JOptionPane.showMessageDialog(null, 
							"Congratulations you have earned the 'Pure Platinum' Achievement", 
							"Achievements Message", 
							JOptionPane.PLAIN_MESSAGE);
					return;
				}
			}
		}
		else
		{
			return;
		}
	}
	
	public static void WorldChampion()
	{
		//TODO implement this achievement have to check to see if the player has a global high score for any puzzle
		int id = 4;
		if(currentAchievements[id] == 0)
		{
			String champion = GUIGameTools.sendWebRequest("http://unccmakesgames.com/games/BeadLoomGame/worldChampion.php?user=" + BeadLoom.playerName);
			if(champion.equalsIgnoreCase("true"))
			{
				currentAchievements[id] = 1;
				JOptionPane.showMessageDialog(null, 
						"Congratulations you have earned the 'World Champion' Achievement", 
						"Achievements Message", 
						JOptionPane.PLAIN_MESSAGE);
				return;
			}
			//Achievement earned 
			else
			{
				return;
			}
		}
		else
		{
			//Achievement already gained
			return;
		}
		
	}
	
	public static void LearnedTheBasics()
	{
		int id = 5;
		//if already obtained do not check

		if (currentAchievements[id] == 0) 
		{
			for (int i = 0; i < 6; i++) {
				if (!currentMedals[i].equalsIgnoreCase("-None-")) {
					//Do nothing 
				} else {
					//Achievement not earned
					return;
				}
			}
			//Achievement Earned
			currentAchievements[id] = 1;
			JOptionPane.showMessageDialog(null, 
					"Congratulations you have earned the 'Learned the Basics' Achievement", 
					"Achievements Message", 
					JOptionPane.PLAIN_MESSAGE);
		}
		else
		{
			//Achievement already gained
			return;
		}
		
	}
	
	public static void MasteredTheBasics()
	{
		int id = 6;
		//if already obtained do not check
		if (currentAchievements[id] == 0) 
		{
			for (int i = 0; i < 6; i++) {
				if (currentMedals[i].equalsIgnoreCase("Platinum!!!!")) {
					//Do nothing 
				} else {
					//Achievement not earned
					return;
				}
			}
			//Achievement Earned
			currentAchievements[id] = 1;
			JOptionPane.showMessageDialog(null, 
					"Congratulations you have earned the 'Mastered the Basics' Achievement", 
					"Achievements Message", 
					JOptionPane.PLAIN_MESSAGE);
		}
		else
		{
			//Achievement already gained
			return;
		}
	}
	
	public static void EasyAsPie()
	{
		int id = 7;
		//if already obtained do not check
		if (currentAchievements[id] == 0) 
		{
			for (int i = 6; i < 15; i++) {
				if (!currentMedals[i].equalsIgnoreCase("-None-")) {
					//Do nothing 
				} else {
					//Achievement not earned
					return;
				}
			}
			//Achievement Earned
			currentAchievements[id] = 1;
			JOptionPane.showMessageDialog(null, 
					"Congratulations you have earned the 'Easy as Pie' Achievement", 
					"Achievements Message", 
					JOptionPane.PLAIN_MESSAGE);
		}
		else
		{
			//Achievement already gained
			return;
		}
	}
	
	public static void HaveYourPieAndEatItToo()
	{
		int id = 8;
		if(currentAchievements[id] == 0)
		{
			for(int i=6; i<15; i++)
			{
				if(currentMedals[i].equalsIgnoreCase("Platinum!!!!"))
				{
					//Do nothing
				}
				else
				{
					//Achievement not earned
					return;
				}
			}
			currentAchievements[id] = 1;
			JOptionPane.showMessageDialog(null, 
					"Congratulations you have earned the 'Have your Pie and Eat it too' Achievement", 
					"Achievements Message", 
					JOptionPane.PLAIN_MESSAGE);
		}
		else
		{
			//Achievement already gained
			return;
		}
	}
	
	public static void MentalMedium()
	{
		int id = 9;
		//if already obtained do not check
		if (currentAchievements[id] == 0) 
		{
			for (int i = 15; i < 24; i++) {
				if (!currentMedals[i].equalsIgnoreCase("-None-")) {
					//Do nothing 
				} else {
					//Achievement not earned
					return;
				}
			}
			//Achievement Earned
			currentAchievements[id] = 1;
			JOptionPane.showMessageDialog(null, 
					"Congratulations you have earned the 'Mental Medium' Achievement", 
					"Achievements Message", 
					JOptionPane.PLAIN_MESSAGE);
		}
		else
		{
			//Achievement already gained
			return;
		}
	}
	
	public static void MentalMediumMaster()
	{
		int id = 10;
		//if already obtained do not check
		if (currentAchievements[id] == 0) 
		{
			for (int i = 15; i < 24; i++) {
				if (currentMedals[i].equalsIgnoreCase("Platinum!!!!")) {
					//Do nothing 
				} else {
					//Achievement not earned
					return;
				}
			}
			//Achievement Earned
			currentAchievements[id] = 1;
			JOptionPane.showMessageDialog(null, 
					"Congratulations you have earned the 'Mental Medium Master' Achievement", 
					"Achievements Message", 
					JOptionPane.PLAIN_MESSAGE);
		}
		else
		{
			//Achievement already gained
			return;
		}
	}
	
	public static void PuzzleMaster()
	{
		int id = 11;
		//if already obtained do not check
		if (currentAchievements[id] == 0) 
		{
			for (int i = 24; i < 35; i++) {
				if (!currentMedals[i].equalsIgnoreCase("-None-")) {
					//Do nothing 
				} else {
					//Achievement not earned
					return;
				}
			}
			//Achievement Earned
			currentAchievements[id] = 1;
			JOptionPane.showMessageDialog(null, 
					"Congratulations you have earned the 'Puzzle Master' Achievement", 
					"Achievements Message", 
					JOptionPane.PLAIN_MESSAGE);
		}
		else
		{
			//Achievement already gained
			return;
		}
	}
	
	public static void AbsolutePuzzleMaster()
	{
		int id = 12;
		//if already obtained do not check
		if (currentAchievements[id] == 0) 
		{
			for (int i = 24; i < 35; i++) {
				if (currentMedals[i].equalsIgnoreCase("Platinum!!!!")) {
					//Do nothing 
				} else {
					//Achievement not earned
					return;
				}
			}
			//Achievement Earned
			currentAchievements[id] = 1;
			JOptionPane.showMessageDialog(null, 
					"Congratulations you have earned the 'Absolute Puzzle Master' Achievement", 
					"Achievements Message", 
					JOptionPane.PLAIN_MESSAGE);
		}
		else
		{
			//Achievement already gained
			return;
		}
	}
	
	public static void CreativeCat()
	{
		int id = 13;
		//if already obtained do not check
		if(currentAchievements[id] == 0)
		{
			String creativeCat = GUIGameTools.sendWebRequest("http://unccmakesgames.com/games/BeadLoomGame/creativeCat.php?user=" + BeadLoom.playerName);
			if(creativeCat.equalsIgnoreCase("true"))
			{
				currentAchievements[id] = 1;
				JOptionPane.showMessageDialog(null, 
						"Congratulations you have earned the 'Creative Cat' Achievement", 
						"Achievements Message", 
						JOptionPane.PLAIN_MESSAGE);
				return;
			}
			//Achievement earned 
			else
			{
				return;
			}
		}
		else
		{
			//Achievement already gained
			return;
		}
	}

	public static void NewIdeal()
	{
		int id = 14;
		//if already obtained do not check
		if(currentAchievements[id] == 0)
		{
			String creativeCat = GUIGameTools.sendWebRequest("http://unccmakesgames.com/games/BeadLoomGame/newIdeal.php?user=" + BeadLoom.playerName);
			if(creativeCat.equalsIgnoreCase("true"))
			{
				currentAchievements[id] = 1;
				JOptionPane.showMessageDialog(null, 
						"Congratulations you have earned the 'The New Ideal' Achievement", 
						"Achievements Message", 
						JOptionPane.PLAIN_MESSAGE);
				return;
			}
			//Achievement earned 
			else
			{
				return;
			}
		}
		else
		{
			//Achievement already gained
			return;
		}
	}
	
	public static void RocketShip()
	{
		int id = 15;
		//if already obtained do not check
		if(currentAchievements[id] == 0)
		{
			String creativeCat = GUIGameTools.sendWebRequest("http://unccmakesgames.com/games/BeadLoomGame/rocketShip.php?user=" + BeadLoom.playerName);
			if(creativeCat.equalsIgnoreCase("true"))
			{
				currentAchievements[id] = 1;
				JOptionPane.showMessageDialog(null, 
						"Congratulations you have earned the 'Was it a Rocket Ship?' Achievement", 
						"Achievements Message", 
						JOptionPane.PLAIN_MESSAGE);
				return;
			}
			//Achievement earned 
			else
			{
				return;
			}
		}
		else
		{
			//Achievement already gained
			return;
		}
	}

	public static void SenseSelf()
	{
		int id = 16;
		//if already obtained do not check
		if(currentAchievements[id] == 0)
		{
			String creativeCat = GUIGameTools.sendWebRequest("http://unccmakesgames.com/games/BeadLoomGame/senseSelf.php?user=" + BeadLoom.playerName);
			if(creativeCat.equalsIgnoreCase("true"))
			{
				currentAchievements[id] = 1;
				JOptionPane.showMessageDialog(null, 
						"Congratulations you have earned the 'A Sense of Self' Achievement", 
						"Achievements Message", 
						JOptionPane.PLAIN_MESSAGE);
				return;
			}
			//Achievement earned 
			else
			{
				return;
			}
		}
		else
		{
			//Achievement already gained
			return;
		}
	}
	
	public static void SpeedKing()
	{
		int id = 17;
		//if already obtained do not check
		if(currentAchievements[id] == 0)
		{
			String creativeCat = GUIGameTools.sendWebRequest("http://unccmakesgames.com/games/BeadLoomGame/speedKing.php?user=" + BeadLoom.playerName);
			if(creativeCat.equalsIgnoreCase("true"))
			{
				currentAchievements[id] = 1;
				JOptionPane.showMessageDialog(null, 
						"Congratulations you have earned the 'Speed King' Achievement", 
						"Achievements Message", 
						JOptionPane.PLAIN_MESSAGE);
				return;
			}
			//Achievement earned 
			else
			{
				return;
			}
		}
		else
		{
			//Achievement already gained
			return;
		}
	}

	public static void PuzzlePerfection()
	{
		int id = 21;
		//if already obtained do not check
		if(currentAchievements[id] == 0)
		{
			int achievementCount = 0;
			for(int i=0; i<currentAchievements.length; i++)
			{
				if(currentAchievements[i] == 1)
				{
					achievementCount++;
				}
			}
			if(achievementCount == currentAchievements.length - 1)
			{
				currentAchievements[id] = 1;
				JOptionPane.showMessageDialog(null, 
						"Congratulations you have earned the 'Puzzle Perfection' Achievement", 
						"Achievements Message", 
						JOptionPane.PLAIN_MESSAGE);
				return;
			}
			//Achievement earned 
			else
			{
				return;
			}
		}
		else
		{
			//Achievement already gained
			return;
		}
	}
}


















