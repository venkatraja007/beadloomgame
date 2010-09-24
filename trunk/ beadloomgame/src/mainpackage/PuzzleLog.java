package src.mainpackage;

public abstract class PuzzleLog 
{
	
	private static String puzzleLog = "";
	
	public static void AddLayer(String layer)
	{
		puzzleLog += layer;
	}
	
	public static void Clear()
	{
		puzzleLog = "";
	}

}
