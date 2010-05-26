/*******************************************************************
* 																   *
*	NAME:		GUIGoalImages.java			                       *
*	DATE:		4/22/08								               *
*	VERSION:	1.0.0			                                   *	
*               			                                       *
*	PURPOSE:	To load an image from an image file or XML file    *
*	DOCUMENT REFERENCE(S):	                    				   *
*											                       *
*	PROCEDURE INVOCATION:						                   *
*	          actionPerformed(ActionEvent)     			           *
*				                                                   *
*	INPUT PARAMETERS:							                   *
*		      File                                                 *
*								                                   *
*	OUTPUT PARAMETERS:							                   *
*		      Image                                                *
*											                       *
*	ASSUMPTIONS: None				                               *
*	LIMITATIONS: File must be of a jpg, jpeg, gif, bmp, or xml     *
*				 format   						                   *
*									                               *
*											                       *
*******************************************************************/

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import java.net.URL;


public class GUIGoalImages extends JInternalFrame implements ActionListener{
	private JLabel CurrentImagesLabel = new JLabel();
	private JButton GoalImagesButton = new JButton();
	private File beadFile;
	private boolean isXML;
	private BeadLoom bl;	
	
	public GUIGoalImages() {
			
		CurrentImagesLabel.setText("Current Image");
		CurrentImagesLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		GoalImagesButton.addActionListener(this);		
	}
	public void setBeadLoom(BeadLoom b){bl = b;}
    public void actionPerformed(ActionEvent e) 
    {
    	File tempFile;
    	FileDialog chooser = new FileDialog(new Frame());
    	chooser.setVisible(true);
    	tempFile = new File(chooser.getDirectory() + chooser.getFile());
    	if(!accept(tempFile, tempFile.getName()))
    		return;
    	ImageIcon ii;
    	if(isXML)
    	{
    		ii = LoadFromXML(tempFile);
    		beadFile = null;
    	}
    	else
    	{
	    	beadFile = tempFile;
	    	ii = new ImageIcon(beadFile.toString());
    	}
    	GoalImagesButton.setIcon(getScaledImage(ii));
    }   
    public ImageIcon LoadFromXML(File tempFile)
    {	    
	    ArrayList<Layer> layers =  new ArrayList<Layer>();
	    ArrayList<Layer> undo =  new ArrayList<Layer>();
	    try{
	    	//SaveLayer.openLoom(tempFile, layers, undo);
	    }
	    catch (Exception ex) {
			System.out.println(ex); 
		} 
	    GridPanel gp = new GridPanel(bl);
	    gp.setMain(false);
	    gp.setSize(bl.getGridPanel().getSize());	 
	    gp.setImage(bl.getImage());
		gp.setLayers(layers);
	    int max = 0;
	    int Xoffset;
	    int Yoffset;
	    for(int i = 0; i < layers.size(); i++)
	    {
	    	Xoffset = layers.get(i).getXOffset();
	    	Yoffset = layers.get(i).getYOffset();
	    	for(int j = 0; j < layers.get(i).getCoords().getSize(); j++)
	    	{
	    		if(Math.abs(layers.get(i).getCoords().getCoord(j, 0) + Xoffset) > max)
	    			max = Math.abs(layers.get(i).getCoords().getCoord(j, 0) + Xoffset);
	    		if(Math.abs(layers.get(i).getCoords().getCoord(j, 1) - Yoffset) > max)
	    			max = Math.abs(layers.get(i).getCoords().getCoord(j, 1) - Yoffset);
	    	}
	    }
	    max *= 2;
	    max = (max % 10 == 0) ? max / 10 * 10 : max / 10 * 10 + 10;
	    if(max < 30) max = 30;
	    else if(max > 100) max = 100;
	    gp.setGridSize(max);
		for(int i = 0; i < gp.getLayers().size(); i++)
			bl.doBeadSetting(layers.get(i), gp);
        BufferedImage myImage = new BufferedImage(gp.getWidth(), 
        		gp.getHeight(), BufferedImage.TYPE_INT_RGB);

        Graphics2D g2 = myImage.createGraphics();
        gp.paint(g2);
        try{Thread.sleep(500);}catch(Exception e){e.printStackTrace();}
        gp.paintComponent(g2);
        
        return new ImageIcon(myImage);
    }
    public boolean accept(File f, String s)
    {
    	isXML = false;
    	if(f == null || s.length() == 0)
    		return false;
        int count = f.getName().length();
        while(s.charAt(--count) != '.')
        	if(count == 0)
        		return false;
        s = s.substring(++count);
        if(s.compareToIgnoreCase("jpg") == 0 || s.compareToIgnoreCase("jpeg") == 0 ||
        		s.compareToIgnoreCase("gif") == 0 || s.compareToIgnoreCase("bmp") == 0)
        	return true;
        if(s.compareToIgnoreCase("xml") == 0)
        {
        	isXML = true;
        	return true;
        }
    	return false;
    }
	public JButton getGoalImagesButton() {
		return GoalImagesButton;
	}	
	public JLabel getCurrentImagesLabel() {
		return CurrentImagesLabel;
	}
	public ImageIcon getScaledImage(ImageIcon myLoadedImageIcon) {
		Image myImage = myLoadedImageIcon.getImage();
		Image scaledImage  = myImage.getScaledInstance(195, 160, Image.SCALE_SMOOTH);
		ImageIcon m = new ImageIcon(scaledImage);
		return m;
	}
	public void setGoalImage(String S)
	{
		URL url = null;
		try
		{
			//Create URL with passed baseURL and append image name
			url = new URL(S+"image2.jpg");
		}
		catch(Exception ex)
		{
			//Passed URL is null; quit
			
			/* Commented out until we fix the code or decided this is unnecessary */
			//JOptionPane.showMessageDialog(null, "Invalid Image Goal URL"); 
			return;
		}
		//Set the image of the GoalImageButton
		GoalImagesButton.setIcon(new ImageIcon(url));
	}
}