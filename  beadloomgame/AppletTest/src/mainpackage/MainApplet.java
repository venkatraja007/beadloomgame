package mainpackage;

import javax.swing.JApplet;
import javax.swing.SwingUtilities;

public class MainApplet extends JApplet
{
	//Called when applet is loaded into browser
	public void init()
	{
		//Execute a job on the event-dispatching threa
		try
		{
			SwingUtilities.invokeAndWait(new Runnable(){
				public void run()
				{
					SwingClass swingObj = new SwingClass();
					//swingObj.createAndShowGUI();
					createGUI();
				}
			});
		}
		catch (Exception e)
		{
			System.err.println("error! error!");
		}
		
	}
	
	private void createGUI()
	{
		SwingClass swingObj = new SwingClass();
		swingObj.setOpaque(true);
		setContentPane(swingObj);
	}
}
