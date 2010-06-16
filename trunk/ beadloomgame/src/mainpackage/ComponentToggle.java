package src.mainpackage;

public class ComponentToggle {
	public static boolean scoring = true;						//done
	public static boolean medals = true;						//done
	public static boolean personalHighScores = true;			//done
	public static boolean globalHighScores = true;				//done
	public static boolean userCreatedContent = true;			//TODO: implement this
	public static boolean otherUserCreatedContent = true;		//TODO: implement this
	public static boolean avatars = true;						//done
	public static boolean unlockableAvatar = true;				//done
	public static boolean achievements = true;					//TODO: implement this
	public static boolean aesthetics = true;					//done
	public static boolean basicHints = true;					//done
	public static boolean advancedHints = true;					//TODO: implement this
	public static boolean codeOuputWindow = true;				//Done
	public static boolean userAccounts = true;					//TODO: implement this
	public static boolean gameSaves = true;						//TODO: implement this
	public static boolean unlockableDifficultyLevels = true;	//done
	public static boolean hintsOn = true;						//done
	public static boolean timerEnabled = true;					//done
	
	/*
	 * When security is enabled you need an extra file that is not included in the svn.
	 * This is to help prevent people from inserting false values into the current bead
	 * loom game database. If you do not have this file make sure that securityEnabled
	 * is set to false.
	 * 
	 * Remove all references to this if you do not have the extra file
	 */
	public static boolean securityEnabled = true;
}
