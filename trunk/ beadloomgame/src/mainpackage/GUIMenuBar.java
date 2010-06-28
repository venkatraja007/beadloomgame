/*******************************************************************
* 																   *
*	NAME:		GUIMenuBar.java			                           *
*	DATE:		4/22/08								               *
*	VERSION:	1.0.0							                   *
*	PURPOSE:	Holds GUI options for display settings, saves,     *
*				and loads							               *
*	DOCUMENT REFERENCE(S):	                    				   *
*											                       *
*	PROCEDURE INVOCATION:						                   *
*	            GUIMenuBar()                                       *
*				                                                   *
*	INPUT PARAMETERS:							                   *
*		        none                                               *
*								                                   *
*	OUTPUT PARAMETERS:							                   *
*		        MenuItems                                          *
*											                       *
*	ASSUMPTIONS: None				                               *
*	LIMITATIONS:   						                           *
*									                               *
*											                       *
*******************************************************************/
package src.mainpackage;


import java.awt.event.*;
import javax.swing.*;



public class GUIMenuBar {
	private GridPanel gridPanel;
	private GUIInputTools tools;
	
    static final int GRID_40 = 40;
    static final int GRID_50 = 50;
    static final int GRID_60 = 60; 
    static final int GRID_70 = 70;//initial frames per second
    static final int GRID_80 = 80;
    static final int GRID_90 = 90;
    static final int GRID_100 = 100;
	//------ Top Menu Components -------
	private JMenuBar TopMenuBar = new JMenuBar();
	
	//------- File Menu Components -------
	private JMenu FileMenu = new JMenu();
	private JMenuItem NewBeadsItemMenu = new JMenuItem();
	private JMenuItem OpenBeadsItemMenu = new JMenuItem();
	private JMenu SaveBeadsMenu = new JMenu();
	private JMenuItem SaveToJPEGMenuItem = new JMenuItem();
	private JMenuItem SaveToXMLMenuItem = new JMenuItem();		
	private JMenuItem PrintMenuItem = new JMenuItem();
	
	//------- Edit Menu Components -------
	private JMenu EditMenu = new JMenu();		
	private JMenuItem UndoMenuItem = new JMenuItem();
	private JMenuItem RedoMenuItem = new JMenuItem();
	
	//------- Options Menu Components -------
	private JMenu OptionsMenu = new JMenu();		
	private JMenu GridOptionsMenu = new JMenu(); 
	private JMenu GridSizeMenu = new JMenu();
	private JCheckBoxMenuItem XYFollowMouseMenuItem = new JCheckBoxMenuItem();
	private JCheckBoxMenuItem ShowGridMenuItem = new JCheckBoxMenuItem();
	private JCheckBoxMenuItem HideGridMenuItem = new JCheckBoxMenuItem();
	private JCheckBoxMenuItem LockGridMenuItem = new JCheckBoxMenuItem();
	private JCheckBoxMenuItem AxisValuesMenuItem = new JCheckBoxMenuItem();
	private JCheckBoxMenuItem MarginValuesMenuItem = new JCheckBoxMenuItem();
	private JMenu ExamplesMenu = new JMenu();
	private JMenuItem DrawSnakeMenuItem = new JMenuItem();
	private JMenuItem DrawBearMenuItem = new JMenuItem();
	private JMenuItem DrawPatternMenuItem = new JMenuItem();
	private JMenu DevelopmentCodeMenu = new JMenu();
	
	//------- Window Menu Components -------
	private JMenu WindowMenu = new JMenu();
	private JMenuItem GridMenuItem = new JCheckBoxMenuItem();
	private JMenuItem GoalImagesMenuItem = new JCheckBoxMenuItem();
	private JMenuItem OutputWindowMenuItem = new JCheckBoxMenuItem();
	private JCheckBoxMenuItem MoveBeadsItem = new JCheckBoxMenuItem();
	private JCheckBoxMenuItem BeadUtilitiesItem =new JCheckBoxMenuItem();
	
	//------- Help Menu Components -------
	private JMenu HelpMenu = new JMenu();
	private JMenuItem PseudoCodeMenuItem = new JMenuItem();
	private JMenuItem AboutMenuItem = new JMenuItem();
	
	//------- Grid Size Components -------	
	private JRadioButton Grid_30 = new JRadioButton("30 x 30");
	private JRadioButton Grid_40 = new JRadioButton("40 x 40");
	private JRadioButton Grid_50 = new JRadioButton("50 x 50");
	private JRadioButton Grid_60 = new JRadioButton("60 x 60");
	private JRadioButton Grid_70 = new JRadioButton("70 x 70");
	private JRadioButton Grid_80 = new JRadioButton("80 x 80");
	private JRadioButton Grid_90 = new JRadioButton("90 x 90");
	private JRadioButton Grid_100 = new JRadioButton("100 x 100");
	private ButtonGroup  GridSize = new ButtonGroup();

	
	
	public GUIMenuBar() {	
		
		DevelopmentCodeMenu.setEnabled(false);
		PseudoCodeMenuItem.setEnabled(false);
		MarginValuesMenuItem.setEnabled(false);
		AxisValuesMenuItem.setEnabled(false);
		RedoMenuItem.setEnabled(false);
		UndoMenuItem.setEnabled(false);
		ShowGridMenuItem.setEnabled(false);
		HideGridMenuItem.setSelected(false);
		LockGridMenuItem.setEnabled(false);
		Grid_40.setSelected(true); // default grid size
		
	}
	//======== TopMenuBar ========
	{

		//======== FileMenu ========
		{
			FileMenu.setText("File");

			//---- NewBeadsItemMenu ----
			NewBeadsItemMenu.setText("New Beads...");
			NewBeadsItemMenu.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					NewBeadsItemMenuActionPerformed(e);
				}
			});
			FileMenu.add(NewBeadsItemMenu);

			//---- OpenBeadsItemMenu ----
			OpenBeadsItemMenu.setText("Open Beads...");
			OpenBeadsItemMenu.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					OpenBeadsItemMenuActionPerformed(e);
				}
			});
			FileMenu.add(OpenBeadsItemMenu);

			//======== SaveBeadsMenu ========
			{
				SaveBeadsMenu.setText("Save Beads");
				
				//---- SaveToJPEGMenuItem ----
				SaveToJPEGMenuItem.setText("Save To JPEG");
				SaveBeadsMenu.add(SaveToJPEGMenuItem);

				//---- SaveToXMLMenuItem ----
				SaveToXMLMenuItem.setText("Save to XML");
				SaveToXMLMenuItem.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						SaveToXMLMenuItemActionPerformed(e);
					}
				});
				SaveBeadsMenu.add(SaveToXMLMenuItem);
			}
			FileMenu.add(SaveBeadsMenu);
			FileMenu.addSeparator();

			//---- PrintMenuItem ----
			PrintMenuItem.setText("Print");
			FileMenu.add(PrintMenuItem);
			FileMenu.addSeparator();

		}
		TopMenuBar.add(FileMenu);

		//======== EditMenu ========
		{
			EditMenu.setText("Edit");

			//---- UndoMenuItem ----
			UndoMenuItem.setText("Undo");
			EditMenu.add(UndoMenuItem);

			//---- RedoMenuItem ----
			RedoMenuItem.setText("Redo");
			EditMenu.add(RedoMenuItem);
			EditMenu.addSeparator();
		
		TopMenuBar.add(EditMenu);

		//======== OptionsMenu ========
		{
			OptionsMenu.setText("Options");

			//======== GridOptionsMenu ========
			{
				GridOptionsMenu.setText("Grid Options");
				
				//======= Grid Size Menu =======
				{ 
					GridSizeMenu.setText("Grid Size");
					GridSizeMenu.add(Grid_30);
					GridSizeMenu.add(Grid_40);
					GridSizeMenu.add(Grid_50);
					GridSizeMenu.add(Grid_60);
					GridSizeMenu.add(Grid_70);
					GridSizeMenu.add(Grid_80);
					GridSizeMenu.add(Grid_90);
					GridSizeMenu.add(Grid_100);
					
					// add to button group
				    GridSize.add (Grid_30);
				    GridSize.add (Grid_40);
				    GridSize.add (Grid_50);
				    GridSize.add (Grid_60);
				    GridSize.add (Grid_70);
				    GridSize.add (Grid_80);
				    GridSize.add (Grid_90);
				    GridSize.add (Grid_100);
		   				    
				    GridOptionsMenu.add(GridSizeMenu);			    				    
				}							    
				GridOptionsMenu.addSeparator();

				//---- XYFollowMouseMenuItem ----
				XYFollowMouseMenuItem.setText("X Y Follow Mouse");
				
				GridOptionsMenu.add(XYFollowMouseMenuItem);
				GridOptionsMenu.addSeparator();

				//---- ShowGridMenuItem ----
				ShowGridMenuItem.setText("Show Grid");
				ShowGridMenuItem.setSelected(true);
				GridOptionsMenu.add(ShowGridMenuItem);

				//---- HideGridMenuItem ----
				HideGridMenuItem.setText("Hide Grid");
				HideGridMenuItem.setSelected(true);		
				GridOptionsMenu.add(HideGridMenuItem);

				//---- LockGridMenuItem ----
				LockGridMenuItem.setText("Lock Grid");
				LockGridMenuItem.setSelected(true);			
				GridOptionsMenu.add(LockGridMenuItem);
				GridOptionsMenu.addSeparator();

				//---- AxisValuesMenuItem ----
				AxisValuesMenuItem.setText("Margin Values");
				AxisValuesMenuItem.setSelected(true);			
				GridOptionsMenu.add(AxisValuesMenuItem);

				//---- MarginValuesMenuItem ----
				MarginValuesMenuItem.setText("Axis Values");
				MarginValuesMenuItem.setSelected(true);
				GridOptionsMenu.add(MarginValuesMenuItem);
				GridOptionsMenu.addSeparator(); 
			}
			OptionsMenu.add(GridOptionsMenu);

			//======== ExamplesMenu ========
			{
				ExamplesMenu.setText("Examples");

				//---- DrawPatternMenuItem ----
				DrawSnakeMenuItem.setText("Snake");
				ExamplesMenu.add(DrawSnakeMenuItem);
				
				//---- DrawPatternMenuItem ----
				DrawBearMenuItem.setText("Bear");
				ExamplesMenu.add(DrawBearMenuItem);
				
				//---- DrawPatternMenuItem ----
				DrawPatternMenuItem.setText("Pattern");
				ExamplesMenu.add(DrawPatternMenuItem);
			}
			OptionsMenu.add(ExamplesMenu);

			//======== DevelopmentCodeMenu ========
			{
				DevelopmentCodeMenu.setText("Development");
			}
			OptionsMenu.add(DevelopmentCodeMenu);

		}
		TopMenuBar.add(OptionsMenu);

		//======== WindowMenu ========
		{
			WindowMenu.setText("Window");

			//---- GridMenuItem ----
			GridMenuItem.setText("Grid");
			GridMenuItem.setSelected(true);
			WindowMenu.add(GridMenuItem);

			//---- GoalImagesMenuItem ----
			GoalImagesMenuItem.setText("Goal Images");
			GoalImagesMenuItem.setSelected(true);
			WindowMenu.add(GoalImagesMenuItem);

			//---- OutputWindowMenuItem ----
			OutputWindowMenuItem.setText("Output Window");
			OutputWindowMenuItem.setSelected(true);
			WindowMenu.add(OutputWindowMenuItem);
			
			//---- MoveBeadsMenuItem ----
			MoveBeadsItem.setText("Adjust Beads Window");
			MoveBeadsItem.setSelected(true);
			WindowMenu.add(MoveBeadsItem);

			//------- Bead Utilities Item -------			
			BeadUtilitiesItem.setText("Bead Utilities Window");
			BeadUtilitiesItem.setSelected(true);
			WindowMenu.add(BeadUtilitiesItem);				
		}
		TopMenuBar.add(WindowMenu);

		//======== HelpMenu ========
		{
			HelpMenu.setText("Help");

			//---- PseudoCodeMenuItem ----
			PseudoCodeMenuItem.setText("Pseudo Code");
			PseudoCodeMenuItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					PseudoCodeMenuItemActionPerformed(e);
				}
			});
			HelpMenu.add(PseudoCodeMenuItem);
			HelpMenu.remove(PseudoCodeMenuItem);

			//---- AboutMenuItem ----
			AboutMenuItem.setText("About...");
			HelpMenu.add(AboutMenuItem);
		}
		TopMenuBar.add(HelpMenu);
		}
	}
	//======= Event Handlers, Action Events, Action Listeners =======
	
	//======= File Menu Actions =======
	private void Grid_40ActionPerformed(ActionEvent e){
	    //TODO add code here
		}
	private void NewBeadsItemMenuActionPerformed(ActionEvent e){
	    //TODO add code here
		}
	private void OpenBeadsItemMenuActionPerformed(ActionEvent e){
	    //TODO add code here
		}
	private void SaveToXMLMenuItemActionPerformed(ActionEvent e){
       //TODO add code here
	    }	
	private void SaveToJPEGMenuItemActionPerformed(ActionEvent e){
	       //TODO add code here
		    }	

	private void ExitMenuItemActionPerformed(ActionEvent e){
		System.exit(0);
	}
	

  
	//======= Help Menu Actions =======
	private void PseudoCodeMenuItemActionPerformed(ActionEvent e){
	    //TODO add code here
		}
		
	//======= Outside State Changing =======
    public void enableUndo(boolean x) {
    	//System.out.println("Undo called.");
    	if (x==false) { UndoMenuItem.setEnabled(false); }
    	else UndoMenuItem.setEnabled(true);
    }
    public void enableRedo(boolean x) {
    	//System.out.println("Redo called.");
    	if (x==false) { RedoMenuItem.setEnabled(false); }
    	else RedoMenuItem.setEnabled(true);
    }
	
	//======= Component Retrieval =======
	public JMenuBar getJMenuBar() {
		return TopMenuBar;
	}
	public JMenuItem getGridMenuItem(){
    	return GridMenuItem;
    }
    public JMenuItem getGoalImagesMenuItem(){
    	return GoalImagesMenuItem;
    }
    public JMenuItem getOutputWindowMenuItem(){
    	return OutputWindowMenuItem;
    }
    public JMenuItem getMoveBeadsItem(){
    	return MoveBeadsItem;
    }
    public JMenuItem getBeadUtilitiesItem(){
    	return BeadUtilitiesItem;
    }
    public JMenuItem getPrintMenuItem(){
    	return PrintMenuItem;
    }
    public JMenuItem getDrawSnakeMenuItem(){
    	return DrawSnakeMenuItem;
    }
    public JMenuItem getDrawBearMenuItem(){
    	return DrawBearMenuItem;
    }
    public JMenuItem getDrawPatternMenuItem(){
    	return DrawPatternMenuItem;
    }
    public JMenuItem getAboutMenuItem(){
    	return AboutMenuItem;
    }
	public JMenuItem getSaveToJPEGMenuItem(){
    	return SaveToJPEGMenuItem;
    }
    public JMenuItem getSaveToXMLMenuItem(){
    	return SaveToXMLMenuItem;
    }
    public JMenuItem getNewBeadsItem(){
    	return NewBeadsItemMenu;
    }
    public JMenuItem getUndoMenuItem(){
    	return UndoMenuItem;
    }
    public JMenuItem getRedoMenuItem(){
    	return RedoMenuItem;
    }
    public JMenuItem getXYFollowMouseMenuItem(){
    	return XYFollowMouseMenuItem;
    }
    public JMenuItem getShowGridMenuItem(){
    	return ShowGridMenuItem;
    }
    public JMenuItem getHideGridMenuItem(){
    	return HideGridMenuItem;
    }	
    public JMenuItem getLockGridMenuItem(){
    	return LockGridMenuItem;
    } 
    public JMenuItem getOpenBeadsMenuItem() {
    	return OpenBeadsItemMenu;
    }    
    public JRadioButton getGrid_30(){
    	return Grid_30;
    }
    public JRadioButton getGrid_40(){
    	return Grid_40;
    }
    public JRadioButton getGrid_50(){
    	return Grid_50;
    }
    public JRadioButton getGrid_60(){
    	return Grid_60;
    }
    public JRadioButton getGrid_70(){
    	return Grid_70;
    }
    public JRadioButton getGrid_80(){
    	return Grid_80;
    }
    public JRadioButton getGrid_90(){
    	return Grid_90;
    }
    public JRadioButton getGrid_100(){
    	return Grid_100;
    }
    
    public JMenu getFileMenu(){
    	return FileMenu;
    }
    public JMenu getEditMenu(){
    	return EditMenu;
    }
    public JMenu getOptionsMenu(){
    	return OptionsMenu;
    }
    public JMenu getWindowMenu(){
    	return WindowMenu;
    }
    public JMenu getHelpMenu(){
    	return HelpMenu;
    }
}

