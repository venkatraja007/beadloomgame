/*******************************************************************
* 																   *
*	NAME:		Configuration.java			                       *
*	DATE:		6/29/08								               *
*	VERSION:	1.0.0			                                   *	
*               			                                       *
*	PURPOSE:	Contains current URL definitions                   *
*	DOCUMENT REFERENCE(S):	                    				   *
*											                       *
*	PROCEDURE INVOCATION:						                   * 			         
*				                                                   *
*	INPUT PARAMETERS:							                   *
*		      config.properties                                    *
*								                                   *
*	OUTPUT PARAMETERS:							                   *
*		      URL strings                                          *
*											                       *
*	ASSUMPTIONS: None				                               *
*	LIMITATIONS:   						                           *
*									                               *
*											                       *
*******************************************************************/

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.net.URL;
import java.applet.*; 
import java.awt.*; 

import javax.swing.JOptionPane;

    public class Configuration extends Applet
    {
    	private String bead, exp, logo, goal, seq;
    	private URL baseUrl;
    	
    	public Configuration() throws IOException
    	{
    		loadProperties();
    		baseUrl = new URL("http://www.rpi.edu/~kotulc/BeadFinalWindows/project/project/");
    	}
    	
    	public Configuration(URL url) throws IOException
    	{
    		loadProperties();
    		baseUrl = url;
    	}
    	
    	
    	public String getBeadURL()
    	{return bead;}
    	
    	public String getExpURL()
    	{return exp;}
    	
    	public String getLogoURL()
    	{return logo;}
    	
    	public String getGoalURL()
    	{return goal;}
    	
    	public String getSeqURL()
    	{return seq;}
    	
    	private void loadProperties() throws IOException
    	{
    		Properties config = new Properties();
    		String param = this.getParameter("server");
	
    		if(param == null)
			{
				param = " not yet ";
			}
    		try
    		{
    				
		    		/*InputStream ifStream = new FileInputStream(param+"/config.properties");
		    		config.load(ifStream);
		    		ifStream.close();*/
    			/*
		    		bead=config.getProperty("BEAD_URL");
		    		exp=config.getProperty("EXP_URL");
		    		logo=config.getProperty("LOGO_URL");
		    		goal=config.getProperty("GOAL_URL");
		    		seq=config.getProperty("SEQ_URL");*/
		    		
    				param+="/Images/";
    				
    				//Bead url
		    		bead=param+"bead.JPG";
		    		//Base Url
		    		exp=param;
		    		//Team logo url
		    		logo=param+"RAMROD.jpg";
		    		//Goal Image url
		    		goal=param+"image2.jpg";
		    		//Sequence tool image url
		    		seq=param+"FunctImage.jpg";
    		}
    		catch (Exception ex){JOptionPane.showMessageDialog(null, "could not get TEST " + param + ex.toString()); }
    	}
    }