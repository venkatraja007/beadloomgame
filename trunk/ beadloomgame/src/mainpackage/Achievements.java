package src.mainpackage;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Map;

import javax.swing.JOptionPane;


public abstract class Achievements {
	
	//Total Number of achievements according to the databse
	final static int TOTAL_ACHIEVEMENTS = 
		Integer.parseInt(GUIGameTools.sendWebRequest("http://www.unccmakesgames.com/games/BeadLoomGame/achievements.php?token=num"));
	//acheivement index the current user has
	static int[] currentAchievements = new int[TOTAL_ACHIEVEMENTS];
	//achievement names the current user has
	static String[] currentAchievementNames = new String[TOTAL_ACHIEVEMENTS];
	
	static String[] currentMedals;
	static int[] currentMoves;
	
	
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
		JOptionPane.showMessageDialog(null, message, "Achievements Message", JOptionPane.PLAIN_MESSAGE);
	}
	
	public static void retrieveMedals(String[] recordMedals, int[] recordMoves)
	{
		currentMedals = recordMedals;
		currentMoves = recordMoves;
	}
	
	public static void checkAchievements()
	{
		PersistentPuzzler();	//0
		SilverPuzzler();		//1
		SoCloseYetSoFar();		//2
		PurePlatinum();			//3
		WorldChampion();		//4
		LearnedTheBasics();		//5
		MasteredTheBasics();	//6
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
				if(!currentMedals[i].equalsIgnoreCase("Bronze!") || 
						currentMedals[i].equalsIgnoreCase("Silver!!") ||
						currentMedals[i].equalsIgnoreCase("Gold!!!") ||
						currentMedals[i].equalsIgnoreCase("Platinum!!!!"))
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
				if(!currentMedals[i].equalsIgnoreCase("Silver!!") ||
						currentMedals[i].equalsIgnoreCase("Gold!!!") ||
						currentMedals[i].equalsIgnoreCase("Platinum!!!!"))
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
				if(!currentMedals[i].equalsIgnoreCase("Gold!!!") ||
						currentMedals[i].equalsIgnoreCase("Platinum!!!!"))
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
				if(!currentMedals[i].equalsIgnoreCase("Platinum!!!!"))
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
					//Achivement not earned
					return;
				}
			}
			//Achivement Earned
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
					//Achivement not earned
					return;
				}
			}
			//Achivement Earned
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
	
}

















