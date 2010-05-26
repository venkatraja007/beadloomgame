/*******************************************************************
* 																   *
*	NAME:		BeadLoom.java			                           *
*	DATE:		5/1/08, Updated 5/24/08							   *
*	VERSION:	1.0.1							                   *
*	PURPOSE:	Controller class;  Intializes all child objects	   *
*				and places them into the GUI                       *
*	DOCUMENT REFERENCE(S):	                    				   *
*											                       *
*	PROCEDURE INVOCATION:						                   *
*	          init()     			                               *
*			  OpenBeadsItemMenuActionPerformed(ActionEvent)		   *		
*			  doBeadSetting(Layer, GridPanel)                      *
*	INPUT PARAMETERS:							                   *
*		      none                                                 *
*			  XML File
*			  Html Parameter									   *
*			  Layer				                                   *
*	OUTPUT PARAMETERS:							                   *
*		      none                                                 *
*			  ArrayList of Layers								   *
*			  Image							                       *
*	ASSUMPTIONS: None				                               *
*	LIMITATIONS: Filename characters valid                         *
*									                               *
*											                       *
*******************************************************************/

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import javax.swing.*;
import javax.swing.border.*;
import com.sun.image.codec.jpeg.*;
import nu.xom.ValidityException;
import java.awt.Graphics;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Color;
import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.Image;
import java.awt.image.ImageFilter;
import java.awt.image.RGBImageFilter;
import java.awt.image.ImageProducer;
import java.awt.image.FilteredImageSource;
import java.util.ArrayList;

import java.io.FilenameFilter; 

public class BeadLoom extends JApplet implements Printable, MouseListener, MouseMotionListener, ActionListener {

	//******* Main Method *******
    public void init() {
    	setSize((int)dim.getWidth(),(int)dim.getHeight());
    	BeadLoom Gui = new BeadLoom();
    	color = Gui.getMoveBeads().getColor();
    	
    	//Base URL for image location, passed by HTML parameter "server"
    	//Set to null if no parameter is given
    	String baseURL;
    	baseURL = this.getParameter("server");
    	
    	//Set static BEAD_ADDRESS to baseURL 
    	BEAD_ADDRESS = baseURL;

    	//Pass the image baseURL to the Goal image frame
    	GoalImagesFrame.setGoalImage(baseURL);
    	
    	try
    	{
    		//Create new bead URL for makeBullet function
    		URL url = new URL(baseURL+"bead.JPG");
    		catimage = makeBullet(color, url, 1, 1);
    		
    	}catch(Exception exc){JOptionPane.showMessageDialog(null, "Invalid Image Bead URL"); }
    	
    	doBeadSetting(null, gridPanel);
    	Gui.setVisible(true);
    }
	
	//******* Variables declaration ******

    //public static Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    public static Dimension dim = new Dimension(1024, 768);
    //Set BEAD_ADDRESS to null, initialized in the the init() function
	public static String BEAD_ADDRESS = "null";
	
	private GUIGoalImages GoalImagesFrame;
	private GUIOutputWindow OutputWindow;
	private GUIInputTools InputTools;
	private GUIMovePanel MoveBeads;
	private GUIMenuBar Top;
	private GUIGameTools Game;
	private GridPanel gridPanel;
	private GridPanel gridPanel2;

	private JPanel BeadLoomBackgroundPane;
	private JPanel ContentPanel;
	private JInternalFrame MoveBeadsFrame;
	private JInternalFrame GameFrame;
	private JInternalFrame HighScoresFrame;
	private JInternalFrame PuzzleFrame;
	private JInternalFrame ColorFrame;
	private JInternalFrame BeadUtilitiesFrame;
	private JInternalFrame GridFrame;
	private JInternalFrame GridFrame2;	
	private JLabel CoordinatesLabel;
	private JLabel CoordinatesLabel2;
	private JLabel MousePosition;
	private JLabel MousePosition2;
	private JDesktopPane BeadLoomDesktopPane;
	private Color color;
	
	private JLabel MoveLabel;

	//------- Grid stuff -------
    int PAD = 20;
	double xInc;
	double yInc;
	Image catimage;
	ImageIcon img;
	

	//------- Constructor -------
	public BeadLoom() {
		//Initialize all Beadloom components
		initComponents();
	}
	private void initComponents() {

	
		//******* Components for the GUI initialization *******

		//======== Bead Loom Applet ========
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());

		//------- Background Panel Components -------
		BeadLoomBackgroundPane = new JPanel();
		ContentPanel = new JPanel();
		BeadLoomDesktopPane = new JDesktopPane();

		//------- Coordinates Menu Components -------
		CoordinatesLabel = new JLabel();
		CoordinatesLabel2 = new JLabel();
		
		//------- Move Count Components -------
		MoveLabel = new JLabel();

		//======= Move Beads, Grid Frame, Bead Utilities Frames =======
		MoveBeadsFrame = new JInternalFrame();
		GameFrame = new JInternalFrame();
		HighScoresFrame = new JInternalFrame();
		PuzzleFrame = new JInternalFrame();
		ColorFrame = new JInternalFrame();
		GridFrame = new JInternalFrame();
		GridFrame2 = new JInternalFrame();
		BeadUtilitiesFrame = new JInternalFrame();

		//======= Grid Panel Components =======
		gridPanel = new GridPanel(this);
		gridPanel.setMain(false);
		gridPanel.addMouseListener(new MouseListener(){
			public void mouseClicked(MouseEvent e){ 
				if ((Math.abs(gridPanel.findMouseX(e)) <=gridPanel.getGridSize())&&(Math.abs(gridPanel.findMouseY(e)) <=gridPanel.getGridSize())){
					gridPanel.calcGameGrid();
					Color colorAt = gridPanel.getColorAt(gridPanel.findMouseX(e)+20, gridPanel.findMouseY(e)+20);
					Color blank = new Color(254, 254, 254);
					if (!colorAt.equals(blank)){
						InputTools.setColor(colorAt);
						MoveBeads.getTopColorsButton().setBackground(colorAt);
					}
				}
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {}

			@Override
			public void mouseExited(MouseEvent arg0) {}

			@Override
			public void mousePressed(MouseEvent arg0) {}

			@Override
			public void mouseReleased(MouseEvent arg0) {}
		});
		gridPanel.addMouseMotionListener(new MouseMotionListener() {
			    public void mouseDragged(MouseEvent e) { }
				public void mouseMoved(MouseEvent e) {	
					//------- XY Follow Mouse -------
					MousePosition = gridPanel.findMousePosition(e);			    	 
				    if(Top.getXYFollowMouseMenuItem().isSelected()){
					    gridPanel.setToolTipText(MousePosition.getText());
					}
				    else {				    	
				    	gridPanel.setToolTipText(null);
				    }
				    CoordinatesLabel.setForeground(Color.blue);
				    CoordinatesLabel.setText("Coordinates: " + MousePosition.getText());
				  }
			});
		
		//======= Grid Panel 2 Components =======
		gridPanel2 = new GridPanel(this);
		gridPanel2.setMain(false);
		gridPanel2.addMouseListener(new MouseListener(){
			public void mouseClicked(MouseEvent e){
				if ((Math.abs(gridPanel2.findMouseX(e)) <=gridPanel2.getGridSize())&&(Math.abs(gridPanel2.findMouseY(e)) <=gridPanel2.getGridSize())){
					Color colorAt = gridPanel2.getColorAt(gridPanel2.findMouseX(e)+20, gridPanel2.findMouseY(e)+20);
					Color blank = new Color(254, 254, 254);
					if (!colorAt.equals(blank)){
						InputTools.setColor(colorAt);
						MoveBeads.getTopColorsButton().setBackground(colorAt);
					}
				}
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {}

			@Override
			public void mouseExited(MouseEvent arg0) {}

			@Override
			public void mousePressed(MouseEvent arg0) {}

			@Override
			public void mouseReleased(MouseEvent arg0) {}
		});
		gridPanel2.addMouseMotionListener(new MouseMotionListener() {
			    public void mouseDragged(MouseEvent e) { }
				public void mouseMoved(MouseEvent e) {	
					//------- XY Follow Mouse -------
					MousePosition2 = gridPanel2.findMousePosition(e);			    	 
				    if(Top.getXYFollowMouseMenuItem().isSelected()){
					    gridPanel2.setToolTipText(MousePosition2.getText());
					}
				    else {				    	
				    	gridPanel2.setToolTipText(null);
				    }
				    CoordinatesLabel2.setForeground(Color.blue);
				    CoordinatesLabel2.setText("Coordinates: " + MousePosition2.getText());
				  }
			});
		
        //******* Add and Set Components *******

		//======== TopMenuBar ========
		Top = new GUIMenuBar();
		setJMenuBar(Top.getJMenuBar());

		//------- TopBar Menu Actions -------
		Top.getGridMenuItem().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GridMenuItemActionPerformed(e);
			}
		});
		Top.getGoalImagesMenuItem().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GoalImagesMenuItemActionPerformed(e);
			}
		});
		Top.getOutputWindowMenuItem().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OutputWindowMenuItemActionPerformed(e);
			}
		});
		Top.getMoveBeadsItem().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MoveBeadsItemActionPerformed(e);
			}
		});
		Top.getBeadUtilitiesItem().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BeadUtilitiesItemActionPerformed(e);
			}
		});
		Top.getPrintMenuItem().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PrintMenuActionPerformed(e);
			}
		});
		Top.getAboutMenuItem().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AboutMenuItemActionPerformed(e);
			}
		});
		Top.getSaveToXMLMenuItem().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SaveToXMLMenuItemActionPerformed(e);
			}
		});
		Top.getNewBeadsItem().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NewBeadsItemMenuActionPerformed(e);
			}
		});
		Top.getOpenBeadsMenuItem().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OpenBeadsItemMenuActionPerformed(e);
			}
		});
		Top.getUndoMenuItem().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UndoMenuItemActionPerformed(e);
			}
		});
		Top.getRedoMenuItem().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RedoMenuItemActionPerformed(e);
			}
		});
		Top.getDrawSnakeMenuItem().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DrawExampleMenuItemActionPerformed(e, "snake.xml");
			}
		});
		
		//####################IMAGES ARE UNAVAILABLE###############
		
		/*Top.getDrawBearMenuItem().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DrawExampleMenuItemActionPerformed(e, "bear.xml");
			}
		});
		Top.getDrawPatternMenuItem().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DrawExampleMenuItemActionPerformed(e, "pattern.xml");
			}
		});*/
		
		//##########################################################
		
		Top.getSaveToJPEGMenuItem().addActionListener(this);
		Top.getHideGridMenuItem().addActionListener(this);
		
		
		//======= Grid Options Menu Bar =======
		Top.getGrid_30().addActionListener(this); // default grid size
		Top.getGrid_40().addActionListener(this);
		Top.getGrid_50().addActionListener(this);
		Top.getGrid_60().addActionListener(this);
		Top.getGrid_70().addActionListener(this);
		Top.getGrid_80().addActionListener(this);
		Top.getGrid_90().addActionListener(this);
		Top.getGrid_100().addActionListener(this);


		//======== BeadLoomBackgroundPane ========
		{
			BeadLoomBackgroundPane.setBorder(new EmptyBorder(12, 12, 12, 12));
			BeadLoomBackgroundPane.setLayout(new BorderLayout());

			//======== ContentPanel ========
			{
				ContentPanel.setBackground(new Color(0, 102, 153));
				ContentPanel.setLayout(null);

				//======== GridFrame ========
				{
					GridFrame.setVisible(true);
					GridFrame.setResizable(false);
					GridFrame.setBorder(new LineBorder(Color.red));
					GridFrame.setTitle("Grid");
					GridFrame.setMaximizable(false);

					Container internalFrame1ContentPane = GridFrame.getContentPane();
					internalFrame1ContentPane.setLayout(new BorderLayout(1, 1));
				}
				ContentPanel.add(GridFrame);
				GridFrame.setBounds((int)(dim.getWidth()*0.42)+3, 5, (int)(dim.getWidth()*0.555)-8,(int)(dim.getHeight()*.65)-3);
				GridFrame.getContentPane().add(gridPanel);
				Dimension d = new Dimension();
				d.setSize((dim.getWidth()/2) -15,(dim.getHeight()/2)-15);
				//This needs to be adjusted for auto resizing -Acey
				//GridFrame.setMinimumSize(d);
				GridFrame.toFront();
				
				//======== GridFrame 2 ========
				{
					GridFrame2.setVisible(true);
					GridFrame2.setResizable(false);
					GridFrame2.setBorder(new LineBorder(Color.red));
					GridFrame2.setTitle("Goal");
					GridFrame2.setMaximizable(false);

					Container internalFrame1ContentPane = GridFrame2.getContentPane();
					internalFrame1ContentPane.setLayout(new BorderLayout(1, 1));
				}
				GridFrame2.setBounds(0, 0, (int)(dim.getWidth()/2) -15,(int)(dim.getHeight()/2)-15);
				GridFrame2.getContentPane().add(gridPanel2);
				Dimension d2 = new Dimension();
				d2.setSize((dim.getWidth()/2) -15,(dim.getHeight()/2)-15);
				//This needs to be adjusted for auto resizing -Acey
				//GridFrame2.setMinimumSize(d2);
				GridFrame2.toFront();
				

				//======== OutputCodeWindowFrame ========
				{
					OutputWindow = new GUIOutputWindow();
					OutputWindow.setVisible(true);
					OutputWindow.setTitle("Output Code Window");
					OutputWindow.setBorder(new LineBorder(Color.red));
					Container internalFrame2ContentPane = OutputWindow.getContentPane();
					internalFrame2ContentPane.setLayout(null);

					//======== OutputCodeWindowMenuBar ========
					OutputWindow.setJMenuBar(OutputWindow.getOutputCodeWindowMenuBar());

					//======== OutputWindowScrollPane ========
					internalFrame2ContentPane.add(OutputWindow.getOutputWindowScrollPane());
					OutputWindow.getOutputWindowScrollPane().setBounds(5, 5, 405, 120);

					//---- ClearOutputWindowButton ----
					internalFrame2ContentPane.add(OutputWindow.getClearOutputWindowButton());
					OutputWindow.getClearOutputWindowButton().setBounds(5, 125, 155, 15);
				}
				ContentPanel.add(OutputWindow);
				OutputWindow.setBounds(5, (int)(dim.getHeight()*0.62)+3, (int)(dim.getWidth()*0.42)-8, (int)(dim.getHeight()*0.32)-10);
				ContentPanel.add(BeadLoomDesktopPane);
				BeadLoomDesktopPane.setBounds(new Rectangle(new Point(815, 75), BeadLoomDesktopPane.getPreferredSize()));

				//======= Bead Utilities Frame =======
				{
					InputTools = new GUIInputTools();
					
					InputTools.setGrid(gridPanel);
					InputTools.getDrawPointButton().addActionListener(this);
					InputTools.getDrawLineButton().addActionListener(this);
					InputTools.getRectangleButton().addActionListener(this);
					InputTools.getDrawTriangleIterationButton().addActionListener(this);
					InputTools.getDrawTriangleButton().addActionListener(this);
					InputTools.getGraphTrigFunctionButton().addActionListener(this);
					InputTools.getLinearIterationDrawButton().addActionListener(this);
					BeadUtilitiesFrame.getContentPane().add(InputTools.getBeadLoomUtilitiesTabbedPane());
					InputTools.getBeadLoomUtilitiesTabbedPane().setBounds(0, 5, 420, 310);
					BeadUtilitiesFrame.setResizable(true);
					BeadUtilitiesFrame.setVisible(true);
					BeadUtilitiesFrame.setBorder(new LineBorder(Color.red));
					BeadUtilitiesFrame.setTitle("Bead Utilities");
					
					Container internalFrame3ContentPane = BeadUtilitiesFrame.getContentPane();
					internalFrame3ContentPane.setLayout(new BorderLayout(1, 1));
				}
				ContentPanel.add(BeadUtilitiesFrame);
				BeadUtilitiesFrame.setBounds(5, (int)(dim.getHeight()*0.25)+3, (int)(dim.getWidth()*0.42)-8, (int)(dim.getHeight()*0.37)-6);

				//======== GoalImagesFrame ========
				{
					GoalImagesFrame = new GUIGoalImages();
					GoalImagesFrame.setBeadLoom(this);
					GoalImagesFrame.setVisible(true);
					GoalImagesFrame.setBorder(new LineBorder(Color.red));
					GoalImagesFrame.setTitle("Click for Goal Images");
					GoalImagesFrame.setBackground(Color.white);
					Container GoalImagesFrameContentPane = GoalImagesFrame.getContentPane();
					GoalImagesFrameContentPane.setLayout(new BorderLayout(1, 1));

					//---- GoalImagesButton ----
					Dimension e = new Dimension();
					e.setSize(210,195);
					GoalImagesFrame.setMinimumSize(e);
					GoalImagesFrameContentPane.add(GoalImagesFrame.getGoalImagesButton());
					GoalImagesFrame.getGoalImagesButton().setBounds(5, 5, 195, 160);
				}
				ContentPanel.add(GoalImagesFrame);
				GoalImagesFrame.setBounds(5, 5, (int)(dim.getWidth()*0.21)-6, (int)(dim.getHeight()*0.25)-6);

				//======== MoveBeadsFrame ========
				{
				    MoveBeads = new GUIMovePanel();
				    InputTools.setColor(MoveBeads.getColor()); 
				    new Layer().setMovePanel(MoveBeads);
				    MoveBeadsFrame.getContentPane().add(MoveBeads.getMoveLastDrawnBeadsPanel());
				    MoveBeads.getClearGridButton().addActionListener(this);
				    MoveBeads.getMoveUpButton().addActionListener(this);
				    MoveBeads.getMoveDownButton().addActionListener(this);
				    MoveBeads.getMoveLeftButton().addActionListener(this);
				    MoveBeads.getMoveRightButton().addActionListener(this);
				    MoveBeads.getTopColorsButton().addActionListener(this);
				    MoveBeads.getUndoButton().addActionListener(this);
				    MoveBeads.getRedoButton().addActionListener(this);
					MoveBeads.getMoveLastDrawnBeadsPanel().setBounds(4, 3, 195, 165);
					MoveBeadsFrame.setVisible(true);
					MoveBeadsFrame.setBorder(new LineBorder(Color.red));
					MoveBeadsFrame.setTitle("Adjust Beads");

					Container MoveBeadsFrameContentPane = MoveBeadsFrame.getContentPane();
					MoveBeadsFrameContentPane.setLayout(null);
				}
				ContentPanel.add(MoveBeadsFrame);
				MoveBeadsFrame.setBounds((int)(dim.getWidth()*0.21)+3, 5, (int)(dim.getWidth()*0.21)-6, (int)(dim.getHeight()*0.25)-6);
				
				//======== GameFrame ========
				{
				    Game = new GUIGameTools();
				    Game.setLoom(this);
				    
				    
				    
				    GameFrame.getContentPane().add(Game.getGamePanel());
					GameFrame.setVisible(true);
					GameFrame.setBorder(new LineBorder(Color.red));
					GameFrame.setTitle("Game");
					GameFrame.setResizable(true);
					
					PuzzleFrame.getContentPane().add(Game.getChoosePuzzlePanel());
					PuzzleFrame.setVisible(false);
					PuzzleFrame.setBorder(new LineBorder(Color.red));
					PuzzleFrame.setTitle("Choose a Puzzle");
					PuzzleFrame.setResizable(true);
					
					ColorFrame.getContentPane().add(Game.getColorPanel());
					ColorFrame.setVisible(false);
					ColorFrame.setBorder(new LineBorder(Color.red));
					ColorFrame.setTitle("Choose a Color");
					GameFrame.setResizable(true);

				}
				ContentPanel.add(GameFrame);
				ContentPanel.add(PuzzleFrame);
				ContentPanel.add(ColorFrame);
				GameFrame.setBounds((int)(dim.getWidth()*0.42)+3, (int)(dim.getHeight()*.65)+8, (int)(dim.getWidth()*0.555)-8, (int)(dim.getHeight()*.285)-12);
				PuzzleFrame.setBounds(0, 0, (int)(dim.getWidth()*0.6),(int)(dim.getHeight()*0.6));
				ColorFrame.setBounds((int)(dim.getWidth()/8), (int)(dim.getHeight()/4), 375, 140);

				GridFrame.setBounds((int)(dim.getWidth()*0.42)+3, 5, (int)(dim.getWidth()*0.555)-8,(int)(dim.getHeight()*.65)-3);
				//------- Coordinate Label -------				
				JPanel holder = new JPanel();
				BorderLayout bl = new BorderLayout();
				bl.setVgap(10);
				holder.setLayout(bl);
				holder.add(MoveBeads.getTopColorsButton(), BorderLayout.WEST);
				CoordinatesLabel.setText("Coordinates");
				CoordinatesLabel.setForeground(Color.gray);
				holder.add(CoordinatesLabel,BorderLayout.AFTER_LINE_ENDS);
				GridFrame.add(holder,BorderLayout.NORTH );
				
				//------- Coordinate Label 2 -------				
				JPanel holder2 = new JPanel();
				BorderLayout bl2 = new BorderLayout();
				bl2.setVgap(10);
				holder2.setLayout(bl2);
				MoveLabel.setText("Total Actions: 0");
				MoveLabel.setForeground(Color.gray);
				holder2.add(MoveLabel, BorderLayout.WEST);
				Game.setMoveLabel(MoveLabel);
				CoordinatesLabel2.setText("Coordinates 2");
				CoordinatesLabel2.setForeground(Color.gray);
				holder2.add(CoordinatesLabel2,BorderLayout.AFTER_LINE_ENDS);
				GridFrame2.add(holder2,BorderLayout.NORTH );
				
				


				{ //------- Compute preferred size and dimensions for content pane (entire program) -------
//					Dimension preferredSize = new Dimension();
//					for(int i = 0; i < ContentPanel.getComponentCount(); i++) {
//						Rectangle bounds = ContentPanel.getComponent(i).getBounds();
//						preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
//						preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
//					}
//					Insets insets = ContentPanel.getInsets();
//					preferredSize.width += insets.right;
//					preferredSize.height += insets.bottom;
//					ContentPanel.setMinimumSize(preferredSize);
//					ContentPanel.setPreferredSize(preferredSize);
				}
			}
			BeadLoomBackgroundPane.add(ContentPanel, BorderLayout.CENTER);
		}
		contentPane.add(BeadLoomBackgroundPane, BorderLayout.CENTER);
	}
	
	//******* Methods *******
	
    /*
     * Create a bullet bitmap from a new foreground color and a color image.
     */
    private synchronized Image makeBullet(Color fg, URL url, int panelWidth, int panelHeight) {
        Image bullet;
        
        //This needs to be here to work offline -Acey
        Image sourceImage = Toolkit.getDefaultToolkit().getImage("bead.JPG");
        //This needs to be uncommented to work with online applet -Acey
        //Image sourceImage = getImage(url);
        
        //scale beads to fit panel width and height
        sourceImage = sourceImage.getScaledInstance(panelWidth/InputTools.GRID_SIZE+1,
        		panelHeight/InputTools.GRID_SIZE+1, 0);
        ImageFilter imgf = new HueFilter(fg);
        ImageProducer imgp = new FilteredImageSource(sourceImage.getSource(),imgf);
        	bullet = createImage(imgp);
        	return bullet;
        }
              
    public boolean accept(File f, String s) {
    	if(f == null || s.length() == 0)
    		return false;
        int count = f.getName().length();
        while(s.charAt(--count) != '.')
        	if(count == 0)
        		return false;
        s = s.substring(++count);
        if(s.compareToIgnoreCase(s) == 0)
        	return true;
    	return false;
    }	
    
    public Image getImage(){return catimage;}
    
    public int print(Graphics g, PageFormat pf, int page) throws PrinterException {
		/* We have only one page, and 'page' is zero-based */
		if (page > 0) {
			return NO_SUCH_PAGE;
		}
		/* User (0,0) is typically outside the image area, so we must
		 * translate by the X and Y values in the PageFormat to avoid clipping */
		Graphics2D g2d = (Graphics2D)g;
		g2d.translate(pf.getImageableX(), pf.getImageableY());

		/* Obtain a scale factor to print entire remote desktop on a page
	       regardless of page orientation or desktop size */
		double g2dWidth = dim.getWidth();
	    double g2dHeight = dim.getHeight();
	    double pageWidth = pf.getImageableWidth();
	    double pageHeight = pf.getImageableHeight();
	    double scaleFactor = 1.0; // default scale factor doesn't change image size

	    if (pf.getOrientation() == PageFormat.LANDSCAPE) {
	    	scaleFactor = pageHeight / g2dHeight;
	    } else if (pf.getOrientation() == PageFormat.PORTRAIT) {
	    	scaleFactor = pageWidth / g2dWidth;
	    }
	    /* Enhances the speed and quality of printing */
	    disableDoubleBuffering(this);

	    /* Scale the image relevant to the scale factor to fit it on one page */
	    g2d.scale(scaleFactor,scaleFactor);

		/* Now print the window and its visible contents */
	    BeadLoomBackgroundPane.printAll(g);

		/* Tell the caller that this page is part of the printed document */
		return PAGE_EXISTS;
	}

    public void saveCurrentLoom() {
    	
    	System.out.println("Saving..");
		//String filename = File.separator+"xml";
		String saveName;
		File file = null;
		
	    /*JFileChooser fc = new JFileChooser(new File(filename));
	    fc.showSaveDialog(ContentPanel);
	    File file = fc.getSelectedFile();*/
	    
    	FileDialog fc = new FileDialog(new Frame(), "Save Beadloom", FileDialog.SAVE);
    	fc.setVisible(true);
	    
    	try{
    		file = new File(fc.getDirectory() + fc.getFile());
    	}
    	catch(Exception ex){
    		JOptionPane.showMessageDialog(null, "Invalid file name"); 
    	}
    	
    	if (file != null) {
	    	saveName=file.getAbsolutePath().replace(".xml", "");
	    	try {
	    		SaveLayer.saveLoom(gridPanel.getLayers(), gridPanel.getUndoLayers(), 
	    				gridPanel.getUndoPtr(), gridPanel.getClear(), saveName);
	    	} catch (ValidityException e1) {
	    		e1.printStackTrace();
	    	} catch (IOException e1) {
	    		e1.printStackTrace();
	    	}
		}
    }
    
    //Sets the undeclared Images in the active and undo layer arrays
    private void setLayerImage(ArrayList<Layer> activeLayers, ArrayList<Layer> undoLayers){
    	
    	int zVal = 0, currZVal = 0;
    	for(int i=0; i<activeLayers.size(); i++)
    	{
    		doBeadSetting(activeLayers.get(i), gridPanel);
    	}
    	
    	for(int i=0; i<undoLayers.size(); i++)
    	{
    		doBeadSetting(undoLayers.get(i), gridPanel);
    		currZVal = undoLayers.get(i).getZValue();
    		if(currZVal > zVal) zVal = currZVal; 
    	}
    	zVal++;
    	Layer.setCurrentZ(zVal);
    }
    
    //Parses given XML file and loads layer data from file
	private void OpenBeadsItemMenuActionPerformed(ActionEvent e){
		
		File file;
		try {
		    /*JFileChooser fc = new JFileChooser(); //use jFileDialog
		    fc.addChoosableFileFilter(new XMLFileFilter());
		    fc.showOpenDialog(ContentPanel);
		    file = fc.getSelectedFile();*/
			File tempFile;
	    	FileDialog chooser = new FileDialog(new Frame());
	    	chooser.setFilenameFilter(new XMLFileFilter());
	    	chooser.setVisible(true);
	    	tempFile = new File(chooser.getDirectory() + chooser.getFile());
	
	    	JOptionPane.showMessageDialog(null, tempFile.getName()+" "+tempFile.canRead()+" "+tempFile.length());
	    	
		    ArrayList<Layer> layers = new ArrayList<Layer>();
		    ArrayList<Layer> undo = new ArrayList<Layer>();
		    int undoPtr = 0;
		    
		    undoPtr = SaveLayer.openLoom(tempFile, layers, undo); //Images not set
		    setLayerImage(layers, undo);
		    gridPanel.setLayers(layers, undo, undoPtr);
		}
		catch (Exception ex) {
	    	JOptionPane.showMessageDialog(null, ex.toString());
			System.out.println(e);
			return;
		} 	
		gridPanel.repaint();
	}
	
    public void saveComponentAsJPEG(Component myComponent, String filename) {
        Dimension size = myComponent.getSize();
        BufferedImage myImage =  new BufferedImage(size.width, size.height,
          BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = myImage.createGraphics();
        myComponent.paint(g2);
        try {
        	String filetype = ".JPEG";
        	String saveName;
        	JFileChooser fc = new JFileChooser();
        	fc.showSaveDialog(ContentPanel);
        	File file = fc.getSelectedFile();
        	if (file !=null) {
        		saveName = file.getAbsolutePath()+filetype;
        		OutputStream out = new FileOutputStream(saveName);
        		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
        		encoder.encode(myImage);
        		out.close();
        	}
        		
        } catch (Exception e) {
          System.out.println(e); 
       }
     }
    
    public void doBeadSetting(Layer layer, GridPanel gp) {
    	//URL to set the server location of image files
    	URL url = null;
    	Image m;
    	try 
    	{
    		//Set URL to the baseURL 'BEAD_ADDRESS' and append image name
    	    url = new URL(BEAD_ADDRESS+"bead.JPG");
    	} 
    	catch (MalformedURLException e) 
    	{
    		//JOptionPane.showMessageDialog(null, "Invalid Image Bead URL"); DISABLED FOR DEBUG
    		//If BEAD_ADDRESS is null set to my default location
    		try{url = new URL("http://www.rpi.edu/~kotulc/Images/bead.JPG");}
    		catch(MalformedURLException ex){return;}//Else quit
    	}
    	//Bead color manipulation
    	Color fg;
    	if(layer == null)
    		fg = color;//(attr != null) ? lookupColor(attr) : getForeground();
    	else
    		fg = layer.getColor();
    	     m = makeBullet(fg, url, gp.getWidth(), gp.getHeight());
    	     
    	    float temp = (float)30 / gp.getGridSize();
        
    	 // Set the bead image to certain height and width with respect to screen resolution
		 if (dim.height == 600 && dim.width == 800){m = m.getScaledInstance(Math.round(temp * 11), Math.round(temp * 11), m.SCALE_REPLICATE);}
		 if (dim.height == 768 && dim.width == 1024){m = m.getScaledInstance(Math.round(temp * 15), Math.round(temp * 15), m.SCALE_REPLICATE);}
		 if (dim.height == 864 && dim.width == 1152){m = m.getScaledInstance(Math.round(temp * 16), Math.round(temp * 16), m.SCALE_REPLICATE);}
		 if (dim.height == 600 && dim.width == 1280){m = m.getScaledInstance(Math.round(temp * 17), Math.round(temp * 17), m.SCALE_REPLICATE);}
		 if (dim.height == 720 && dim.width == 1280){m = m.getScaledInstance(Math.round(temp * 20), Math.round(temp * 20), m.SCALE_REPLICATE);}
		 if (dim.height == 768 && dim.width == 1280){m = m.getScaledInstance(Math.round(temp * 19), Math.round(temp * 19), m.SCALE_REPLICATE);}
		 if (dim.height == 960 && dim.width == 1280){m = m.getScaledInstance(Math.round(temp * 20), Math.round(temp * 20), m.SCALE_REPLICATE);}
		 if (dim.height == 1024 && dim.width == 1280){m = m.getScaledInstance(Math.round(temp * 20), Math.round(temp * 20), m.SCALE_REPLICATE);}
		 if (dim.height == 800 && dim.width == 1280){m = m.getScaledInstance(Math.round(temp * 18), Math.round(temp * 18), m.SCALE_REPLICATE);}
		 if (dim.height == 1200 && dim.width == 1920){m = m.getScaledInstance(Math.round(temp * 30), Math.round(temp * 30), m.SCALE_REPLICATE);}

		 
		 if(layer == null) {
    		 InputTools.setImage(m);
    		 gridPanel.setImage(m);
    		 gridPanel2.setImage(m);
		 }
		 else
			 layer.setImage(m);
    }    
        //------- Print function for better quality and speed -------
    	public static void disableDoubleBuffering(Component c) {
    		RepaintManager currentManager = RepaintManager.currentManager(c);
    		currentManager.setDoubleBufferingEnabled(false);
    	}
    	       
//******* Set and Get Methods *******
        
   public GridPanel getGridPanel() {
      return gridPanel;
   }
   public GridPanel getGridPanel2() {
	      return gridPanel2;
	   }
   public GUIMenuBar getTop() {
      return Top;
   }             
   public GUIMovePanel getMoveBeads(){
      return MoveBeads;
   }
   public GUIGameTools getGame(){
      return Game;
   }
   public JPanel  getContentPanel(){
	   return ContentPanel;
   }
   public JInternalFrame getMoveBeadsFrame(){
	   return MoveBeadsFrame;
   }
   public JInternalFrame getGridFrame(){
	   return GridFrame;
   }
   public JInternalFrame getGridFrame2(){
	   return GridFrame2;
   }
   public JInternalFrame getBeadUtilitiesFrame(){
	   return BeadUtilitiesFrame;
   }
   public JInternalFrame getGameFrame(){
	   return GameFrame;
   }
   public JInternalFrame getPuzzleFrame(){
	   return PuzzleFrame;
   }
   public JInternalFrame getColorFrame(){
	   return ColorFrame;
   }
   public GUIGoalImages getGoalImagesFrame(){
	   return GoalImagesFrame;
   }
   public GUIOutputWindow getOutputWindow(){
	   return OutputWindow;
   }
   
   //------- Set Grid stuff -------
    public void setGridSize(int x) {
    	gridPanel.setPanelGridSize(x);
    }
    public void setGridSize2(int x) {
    	gridPanel2.setPanelGridSize(x);
    } 
    public void setPad(int x) {
    	PAD = x;
    }
    public void setxInc(double x) {
    	xInc = x;
    }
    public void setyInc(double x) {
    	yInc = x;
    }
    //-------Get Grid stuff for InputTools -------
    public void getGridSizeToInput() {
    	InputTools.setGridSize(gridPanel.getGridSize()) ;
    }
    public void getGridSize2ToInput() {
    	InputTools.setGridSize(gridPanel2.getGridSize()) ;
    }
    public void getPanelGridSize(){
        gridPanel.getGridSize();
    }
    public void getPadToInput() {
    	InputTools.setPad(PAD);
    }
    public void getXINCToInput() {
        InputTools.setxInc(xInc);
    }
    public void getYINCToInput() {
        InputTools.setyInc(yInc);
    }
    public GUIInputTools getInputTools() {
    	return InputTools;
    }
    
        
 //******* Event Handlers, Action Events, Action Listeners *******

    	//======= File Menu Actions =======*/
    	private void NewBeadsItemMenuActionPerformed(ActionEvent e){
    	    gridPanel.newGrid();
    		}
        private void SaveToXMLMenuItemActionPerformed(ActionEvent e) {
    		saveCurrentLoom();
    	}
    	private void PrintMenuActionPerformed(ActionEvent e){

    		PrinterJob job = PrinterJob.getPrinterJob();
    		job.setPrintable(this);
    		boolean ok = job.printDialog();
    		if (ok) {
    			try {
    				job.print();
    			}
    			catch (PrinterException ex) {
    				/* The job did not successfully complete */
    			}
    		}
    	}
    	
    	private void UndoMenuItemActionPerformed(ActionEvent e){
    		gridPanel.undo();
    		if (gridPanel2.isShowing()){
    			//Log the Undo
 			   try{
 				    FileWriter log = new FileWriter("log.txt", true);
 				    log.write("\t\t" + Game.getTime() + " Undo Performed\n");
 				    log.close();
 				    }catch (Exception ex){//Catch exception if any
 				      System.err.println("Error: " + ex.getMessage());
 				    }
    		}
    	}
    	private void RedoMenuItemActionPerformed(ActionEvent e){
    		gridPanel.redo();
    		if (gridPanel2.isShowing()){
    			//Log the Redo
 			   try{
 				    FileWriter log = new FileWriter("log.txt", true);
 				    log.write("\t\t" + Game.getTime() + " Redo Performed\n");
 				    log.close();
 				    }catch (Exception ex){//Catch exception if any
 				      System.err.println("Error: " + ex.getMessage());
 				    }
    		}
    	}

       //======= Window Menu Actions =======
    	
    	//TODO add different example xml patterns here
    	private void DrawExampleMenuItemActionPerformed(ActionEvent e, String name) {
    		//Top.getGrid_50().doClick();
    		URL url;
    		try {
    			 //Set image URL to baseURL 'BEAD_ADDRESS' and append image name argument
    			 url = new URL(BEAD_ADDRESS+name);
    			 //Initialize pattern from XML file
    			 ArrayList<Layer> layers = SaveLayer.openLoomURL(url); //Doesn't currently work
    	    		//gridPanel.setLayers(layers);
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
    	    	    gridPanel.setGridSize(max);
    	    		for(int i = 0; i < layers.size(); i++)
    	    			doBeadSetting(layers.get(i), gridPanel);		
    	    		gridPanel.repaint();
        	} catch (MalformedURLException ex) {
        		System.out.println(e);
        	    return;
        	}    		
    		
    	}


    	   //------- Grid Menu Actions -------
    	    private void GridMenuItemActionPerformed(ActionEvent e){
    	        if (Top.getGridMenuItem().isSelected()) {
    		        GridFrame.setVisible(true);
    		        Top.getGridMenuItem().setSelected(true);
    	           }
    	       else
    	           GridFrame.setVisible(false);
            }
    	    //------- Goal Images Menu Actions -------
    		private void GoalImagesMenuItemActionPerformed(ActionEvent e){
    			if (Top.getGoalImagesMenuItem().isSelected()) {
    				GoalImagesFrame.setVisible(true);
    				Top.getGoalImagesMenuItem().setSelected(true);
    			   }
    		   else
    				GoalImagesFrame.setVisible(false);
    		}
    	    //------- Output Window Menu Actions -------
    		private void OutputWindowMenuItemActionPerformed(ActionEvent e){
    			if (Top.getOutputWindowMenuItem().isSelected()) {
    				OutputWindow.setVisible(true);
    				Top.getOutputWindowMenuItem().setSelected(true);
    			   }
    		   else
    				OutputWindow.setVisible(false);
    		}
    	    //------- Move Beads Menu Actions -------
    		private void MoveBeadsItemActionPerformed(ActionEvent e){
    			if (Top.getMoveBeadsItem().isSelected()) {
    				MoveBeadsFrame.setVisible(true);
    				Top.getMoveBeadsItem().setSelected(true);
    			   }
    		   else
    				MoveBeadsFrame.setVisible(false);
    		}
    	    //------- Bead Utilities Menu Actions -------
    		private void BeadUtilitiesItemActionPerformed(ActionEvent e){
    			if (Top.getBeadUtilitiesItem().isSelected()) {
    				BeadUtilitiesFrame.setVisible(true);
    				Top.getBeadUtilitiesItem().setSelected(true);
    			   }
    		   else
    			   BeadUtilitiesFrame.setVisible(false);
    		}


    	private void AboutMenuItemActionPerformed(ActionEvent e)
    	{
    		try 
    		{
    			//Set image URL to baseURL 'BEAD_ADDRESS' and append image name 
    			URL url = new URL(BEAD_ADDRESS+"RAMROD.jpg");
    			ImageIcon ii = new ImageIcon(url);
        	    Icon i = ii;
        	    JOptionPane.showMessageDialog(null, "Brought to you by Team Ramrod.","About", JOptionPane.INFORMATION_MESSAGE, i);

    		}
        	catch (Exception ex) 
        	{
        		//URL passed is null
        		JOptionPane.showMessageDialog(null, "Invalid Image ActionMenu URL");
        		return;
        	}
    	}

    	//MouseListener and MouseMotionListener methods
    	public void mouseClicked(MouseEvent e){}
        public void mousePressed(MouseEvent e){}
        public void mouseEntered(MouseEvent e){}
        public void mouseExited(MouseEvent e){}
        public void mouseReleased(MouseEvent e){}
        public void mouseDragged(MouseEvent e){}
  	
        public void mouseMoved(MouseEvent e) {
        	 MousePosition = gridPanel.findMousePosition(e);
        	 CoordinatesLabel.setText(CoordinatesLabel.getText() + MousePosition.getText());
        	 MousePosition2 = gridPanel2.findMousePosition(e);
        	 CoordinatesLabel2.setText(CoordinatesLabel2.getText() + MousePosition2.getText());
        }

        public void actionPerformed(ActionEvent e) {
        	repaint();
        	if (e.getSource()==MoveBeads.getMoveUpButton()){
        		gridPanel.shift("Up", InputTools.getSelectedZ());
        	}
        	else if (e.getSource()==MoveBeads.getMoveDownButton()){
        		gridPanel.shift("Down", InputTools.getSelectedZ());
        	}
        	else if (e.getSource()==MoveBeads.getMoveLeftButton()){
        		gridPanel.shift("Left", InputTools.getSelectedZ());
        	} 
        	else if (e.getSource()==MoveBeads.getMoveRightButton()){
        		gridPanel.shift("Right", InputTools.getSelectedZ());
        	}
        	else if (e.getSource()==MoveBeads.getUndoButton()){
        		if (Top.getUndoMenuItem().isEnabled() == true){
        			gridPanel.undo();
        		}
        	}
        	else if (e.getSource()==MoveBeads.getRedoButton()){
        		if (Top.getRedoMenuItem().isEnabled() == true){
        			gridPanel.redo();
        		}
        	}
        	else if (e.getSource()==MoveBeads.getClearGridButton()) {
        		//TODO changed
        		int choice = JOptionPane.showConfirmDialog(null, "Are you sure? This will remove ALL beads from the grid!", "Beadloom", JOptionPane.YES_NO_OPTION);
        		if (choice == 0){
        			Game.Restart(true, false, false);
        		}
        	}
        	
        	else if (e.getSource() == Top.getSaveToJPEGMenuItem()) {
        		saveComponentAsJPEG(gridPanel, "gridpic.JPEG");
        	}    
        	else if (e.getSource() == Top.getHideGridMenuItem()) {
        		gridPanel.setLines();
        	}
        	else if (e.getSource() == InputTools.getDrawPointButton()) {
        		OutputWindow.getEditorPane().append
        		("draw.BeadPoint(x,y,color)\n"
        				+"    add bead("+InputTools.getDrawPointXTextField().getText()+","
        				+InputTools.getDrawPointYTextField().getText()+","
        				+MoveBeads.getColor().toString()+")\n");
        		if (gridPanel2.isShowing()){
        			Game.incrementMove();
        			//Log the Draw Point
     			   try{
     				    FileWriter log = new FileWriter("log.txt", true);
     				    log.write("\t\t" + Game.getTime() + " Draw Point:\n");
     				    log.write("\t\t\tx= " + InputTools.getDrawPointXTextField().getText() +"\n");
     				    log.write("\t\t\ty= " + InputTools.getDrawPointYTextField().getText() + "\n");
     				    log.close();
     				    }catch (Exception ex){//Catch exception if any
     				      System.err.println("Error: " + ex.getMessage());
     				    }
        		}
        	}
        	else if (e.getSource() == InputTools.getDrawLineButton()) {
        		OutputWindow.getEditorPane().append
        		("draw.BeadLine(x1,y1,x2,y2,color)\n"
        				+"    add bead("+InputTools.getDrawLineX1TextField().getText()+","+InputTools.getDrawLineY1TextField().getText()+","
        				+InputTools.getDrawLineX2TextField().getText()+","+InputTools.getDrawLineY2TextField().getText()+","
        				+MoveBeads.getColor().toString()+")\n");
        		if (gridPanel2.isShowing()){
        			Game.incrementMove();
        			//Log the Draw Line
     			   try{
     				    FileWriter log = new FileWriter("log.txt", true);
     				    log.write("\t\t" + Game.getTime() + " Draw Line:\n");
     				    log.write("\t\t\tx1= " + InputTools.getDrawLineX1TextField().getText() +"\n");
     				    log.write("\t\t\tx2= " + InputTools.getDrawLineX2TextField().getText() +"\n");
     				    log.write("\t\t\ty1= " + InputTools.getDrawLineY1TextField().getText() + "\n");
     				    log.write("\t\t\ty2= " + InputTools.getDrawLineY2TextField().getText() + "\n");
     				    log.close();
     				    }catch (Exception ex){//Catch exception if any
     				      System.err.println("Error: " + ex.getMessage());
     				    }
        		}
        	}
        	else if (e.getSource() == InputTools.getRectangleButton()) {
        		OutputWindow.getEditorPane().append
        		("draw.BeadRectangle(x1,y1,x2,y2,color)\n"
        				+"    add bead("+InputTools.getDrawRectangleX1TextField().getText()+","+InputTools.getDrawRectangleY1TextField().getText()+","
        				+InputTools.getDrawRectangleX2TextField().getText()+","+InputTools.getDrawRectangleY2TextField().getText()+","
        				+MoveBeads.getColor().toString()+")\n");
        		if (gridPanel2.isShowing()){
        			Game.incrementMove();
        			//Log the Draw Rectangle
     			   try{
     				    FileWriter log = new FileWriter("log.txt", true);
     				    log.write("\t\t" + Game.getTime() + " Draw Rectangle:\n");
     				    log.write("\t\t\tx1= " + InputTools.getDrawRectangleX1TextField().getText() +"\n");
     				    log.write("\t\t\tx2= " + InputTools.getDrawRectangleX2TextField().getText() +"\n");
     				    log.write("\t\t\ty1= " + InputTools.getDrawRectangleY1TextField().getText() + "\n");
     				    log.write("\t\t\ty2= " + InputTools.getDrawRectangleY2TextField().getText() + "\n");
     				    log.close();
     				    }catch (Exception ex){//Catch exception if any
     				      System.err.println("Error: " + ex.getMessage());
     				    }
        		}
        	}
        	else if (e.getSource() == InputTools.getDrawTriangleButton()) {
        		OutputWindow.getEditorPane().append
        		("draw.BeadTriangle(x1,y1,x2,y2,x3,y3,color)\n"
        				+"    add bead("+InputTools.getDrawTriangleX1TextField().getText()+","+InputTools.getDrawTriangleY1TextField().getText()+","
        				+InputTools.getDrawTriangleX2TextField().getText()+","+InputTools.getDrawTriangleY2TextField().getText()+","
        				+InputTools.getDrawTriangleX3TextField().getText()+","+InputTools.getDrawTriangleY3TextField().getText()+","
        				+MoveBeads.getColor().toString()+")\n");
        		if (gridPanel2.isShowing()){
        			Game.incrementMove();
        			//Log the Draw Triangle 
        			try{
        				FileWriter log = new FileWriter("log.txt", true);
        				log.write("\t\t" + Game.getTime() + " Draw Triangle:\n");
        				log.write("\t\t\tx1= " + InputTools.getDrawTriangleX1TextField().getText() +"\n");
        				log.write("\t\t\tx2= " + InputTools.getDrawTriangleX2TextField().getText() +"\n");
        				log.write("\t\t\tx3= " + InputTools.getDrawTriangleX3TextField().getText() +"\n");
        				log.write("\t\t\ty1= " + InputTools.getDrawTriangleY1TextField().getText() + "\n");
        				log.write("\t\t\ty2= " + InputTools.getDrawTriangleY2TextField().getText() + "\n");
        				log.write("\t\t\ty3= " + InputTools.getDrawTriangleY3TextField().getText() + "\n");
        				log.close();
        				}catch (Exception ex){//Catch exception if any
        					System.err.println("Error: " + ex.getMessage());
 				    }
        		}
        	}
        	else if (e.getSource() == InputTools.getDrawTriangleIterationButton()) {
        		OutputWindow.getEditorPane().append
        		("draw.TriangleBeadIteration(x,y,width,color)\n"
        				+"         for Cycles("+InputTools.getTriangleIterationRowsTotalTextField().getText()+")\n"
        				+"            for Steps("+InputTools.getTriangleIterationStepHeightTextField().getText()+")\n"
        				+"               add bead("+InputTools.getTriangleIterationStartXTextField().getText()+","
        				                           +InputTools.getTriangleIterationStartYTextField().getText()+","
        				                           +InputTools.getTriangleIterationBeadsAddedTextField().getText()+","
        				                           +MoveBeads.getColor().toString()+")\n");
        		if (gridPanel2.isShowing()){
        			Game.incrementMove();
        		//Log the Draw Triangle Iteration Tool
        			try{
        				FileWriter log = new FileWriter("log.txt", true);
        				log.write("\t\t" + Game.getTime() + " Draw Triangle Iteration:\n");
        				log.write("\t\t\tx= " + InputTools.getTriangleIterationStartXTextField().getText() +"\n");
        				log.write("\t\t\ty= " + InputTools.getTriangleIterationStartYTextField().getText() + "\n");
        				log.write("\t\t\tstep height= " + InputTools.getTriangleIterationStepHeightTextField().getText() + "\n");
        				log.write("\t\t\tadd= " + InputTools.getTriangleIterationBeadsAddedTextField().getText() + "\n");
        				log.write("\t\t\trows total= " + InputTools.getTriangleIterationRowsTotalTextField().getText() + "\n");
        				log.close();
        				}catch (Exception ex){//Catch exception if any
        					System.err.println("Error: " + ex.getMessage());
 				    }
        		}
        	}
        	else if (e.getSource() == InputTools.getDrawLinearIterationButton()) {
        		OutputWindow.getEditorPane().append
        		("draw.BeadIteration(x,y,width,color)\n"
        				+"         for Cycles("+InputTools.getLinearIterationRowsTotalTextField().getText()+")\n"
        				+"            for Inc("+InputTools.getLinearIterationInc1TextField().getText()+ " " + InputTools.getLinearIterationInc2TextField().getText()+ ")\n"
        				+"               add bead("+InputTools.getLinearIterationStartXTextField().getText()+","
        				                           +InputTools.getLinearIterationStartYTextField().getText()+","
        				                           +InputTools.getLinearIterationStartLengthTextField().getText()+","
        				                           +MoveBeads.getColor().toString()+")\n");
        		
        		if (gridPanel2.isShowing()){
        			Game.incrementMove();
        			//Log the Draw Linear Iteration Tool
        			try{
        				FileWriter log = new FileWriter("log.txt", true);
        				log.write("\t\t" + Game.getTime() + " Draw Linear Iteration:\n");
        				log.write("\t\t\tx= " + InputTools.getLinearIterationStartXTextField().getText() +"\n");
        				log.write("\t\t\ty= " + InputTools.getLinearIterationStartYTextField().getText() + "\n");
        				log.write("\t\t\tstart length= " + InputTools.getLinearIterationStartLengthTextField().getText() + "\n");
        				log.write("\t\t\tincrement 1= " + InputTools.getLinearIterationInc1TextField().getText() + "\n");
        				log.write("\t\t\tincrement 2= " + InputTools.getLinearIterationInc2TextField().getText() + "\n");
        				log.write("\t\t\trows total= " + InputTools.getLinearIterationRowsTotalTextField().getText() + "\n");
        				log.close();
 				    	}catch (Exception ex){//Catch exception if any
 				    		System.err.println("Error: " + ex.getMessage());
 				    	}
        		}
        	}
        	else if(e.getSource() == MoveBeads.getTopColorsButton()) {
        		if (!gridPanel2.isShowing()){
        			Color c;
        			JColorChooser j = new JColorChooser();
        			c = j.showDialog(this, null, MoveBeads.getColor());
        			if(c != null)
        				MoveBeads.setColor(c);
        			MoveBeads.getTopColorsButton().setBackground(MoveBeads.getColor());
        		
    			
        			color = MoveBeads.getColor();
        			InputTools.setColor(color);
        			doBeadSetting(null, gridPanel);    
        			//repaint();
        		}
        		else{
        			ColorFrame.setVisible(true);
        			ColorFrame.moveToFront();
        			GridFrame.moveToBack();
        		}
        		
        	}
        	else if(e.getSource()== Top.getGrid_30()) {
        		gridPanel.setPanelGridSize(30);
        		gridPanel2.setPanelGridSize(30);
        	}
        	else if(e.getSource()== Top.getGrid_40()) {
        		gridPanel.setPanelGridSize(40);
        		gridPanel2.setPanelGridSize(40);
        		//TODO lower scale on image according to resize
        		
        	}
        	else if(e.getSource()== Top.getGrid_50()) {
        		gridPanel.setPanelGridSize(50);
        		gridPanel2.setPanelGridSize(50);
        	}
        	else if(e.getSource()== Top.getGrid_60()) {
        		gridPanel.setPanelGridSize(60);
        		gridPanel2.setPanelGridSize(60);
        	}
        	else if(e.getSource()== Top.getGrid_70()) {
        		gridPanel.setPanelGridSize(70);
        		gridPanel2.setPanelGridSize(70);
        	}
        	else if(e.getSource()== Top.getGrid_80()) {
        		gridPanel.setPanelGridSize(80);
        		gridPanel2.setPanelGridSize(80);
        	}
        	else if(e.getSource()== Top.getGrid_90()) {
        		gridPanel.setPanelGridSize(90);
        		gridPanel2.setPanelGridSize(90);
        	}
        	else if(e.getSource()== Top.getGrid_100()) {
        		gridPanel.setPanelGridSize(100);
        		gridPanel2.setPanelGridSize(100);
        	}
        }
        
        
/** The XMLFileFilter class opens directories and only sees xml files **/        
    
    public class XMLFileFilter implements FilenameFilter  {  
    	 public boolean accept(File directory, String filename) {
    		 
    		if (filename.endsWith(".xml")) return true;
    		return false;
    		 
    	}
    }  
        
    /*private class XMLFileFilter1 extends javax.swing.filechooser.FileFilter {
        
    	public boolean accept(File file) {
            String filename = file.getName();
            return filename.endsWith(".xml");
        }
        public String getDescription() {
            return "*.xml";
        }
    }*/
    
}
/**
  * The HueFilter class implements in ImageFilter which recolors
  * the pixels in an image to have a new primary hue. */

    class HueFilter extends RGBImageFilter 
    {
        /*
         * A private variable used to hold hue/saturation/brightness
         * values returned from the static conversion methods in Color.
         */
        private float hsbvals[] = new float[3];

        /*
         * the Hue of the indicated new foreground color.
         */
        float fgHue;

        /*
         * the Saturation of the indicated new foreground color.
         */
        float fgSaturation;

        /*
         * the Brightness of the indicated new foreground color.
         */
        float fgBrightness;

        /*
         * Construct a HueFilter object which performs color modifications
         * to warp existing image colors to have a new primary hue.
         */
        public HueFilter(Color fg) {
    	Color.RGBtoHSB(fg.getRed(), fg.getGreen(), fg.getBlue(), hsbvals);
    	fgHue        = hsbvals[0];
    	fgSaturation = hsbvals[1];
    	fgBrightness = hsbvals[2];
    	canFilterIndexColorModel = true;
        }

        /*
         * Filter an individual pixel in the image by modifying its
         * hue, saturation, and brightness components to be similar
         * to the indicated new foreground color.
         */
        public int filterRGB(int x, int y, int rgb) {
    	int alpha = (rgb >> 24) & 0xff;
    	int red   = (rgb >> 16) & 0xff;
    	int green = (rgb >>  8) & 0xff;
    	int blue  = (rgb      ) & 0xff;
    	Color.RGBtoHSB(red, green, blue, hsbvals);
    	float newHue = fgHue;
    	float newSaturation = hsbvals[1] * fgSaturation;
    	float newBrightness = hsbvals[2] *
    	    (hsbvals[1] * fgBrightness + (1 - hsbvals[1]));
    	rgb = Color.HSBtoRGB(newHue, newSaturation, newBrightness);
    	return (rgb & 0x00ffffff) | (alpha << 24);
    }

}

    
//******* Code not used *******
	/*

	//======= Options Menu Actions =======

		private void LockGridMenuItemActionPerformed(ActionEvent e){
		//TODO add code here
		}
	    private void MarginValuesMenuItemActionPerformed(ActionEvent e){
	    //TODO add code here
		}
		private void AxisValuesMenuItemActionPerformed(ActionEvent e){
		//TODO add code here
	    }
	        	//======= Help Menu Actions =======
    	private void PseudoCodeMenuItemActionPerformed(ActionEvent e){
    	    //TODO add code here
    		}
	*/








