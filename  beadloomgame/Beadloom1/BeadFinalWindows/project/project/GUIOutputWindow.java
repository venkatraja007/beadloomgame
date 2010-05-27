/*******************************************************************
* 																   *
*	NAME:		GUIOutputWindow.java			                   *
*	DATE:		4/22/08								               *
*	VERSION:	1.0.0							                   *
*	PURPOSE:	Outputs Pseudocode onto a JTextArea                *
*	DOCUMENT REFERENCE(S):	                    				   *
*											                       *
*	PROCEDURE INVOCATION:						                   *
*	        GUIOutputWindow()                                      *
*				                                                   *
*	INPUT PARAMETERS:							                   *
*		    ActionEvent                                            *
*								                                   *
*	OUTPUT PARAMETERS:							                   *
*		    String                                                 *
*											                       *
*	ASSUMPTIONS: None				                               *
*	LIMITATIONS: Primitive pseudocode was hard coded, under the    *
*				 assumption that this portion is mostly for later  *
*				 development.			                           *
*									                               *
*											                       *
*******************************************************************/

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;


public class GUIOutputWindow extends JInternalFrame{
	private JInternalFrame OutputWindow = new JInternalFrame();
	//======= Output Code Window Components =======
	private JInternalFrame OutputCodeWindowFrame = new JInternalFrame();		
	private JMenuBar OutputCodeWindowMenuBar = new JMenuBar();
	private JMenu OuputCodeOptionsMenu = new JMenu();
	private JMenuItem OpenLogMenuItem = new JMenuItem();
	private JMenuItem StopLogMenuItem = new JMenuItem();
	private JMenuItem SaveOutputMenuItem = new JMenuItem();
	private JMenu OutputDevelopmentCodeMenu = new JMenu();
	private JMenu FunctionsMenu = new JMenu();
	private JMenuItem IfStatementMenuItem = new JMenuItem();
	private JMenuItem WhileLoopMenuItem = new JMenuItem();
	private JMenuItem DoLoopMenuItem = new JMenuItem();
	private JMenuItem ForLoopMenuItem = new JMenuItem();
	private JMenuItem DrawTriangleMenuItem = new JMenuItem();
	private JMenuItem DrawRectangleMenuItem = new JMenuItem();
	private JMenu SourceCodeHelpMenu = new JMenu();
	private JMenuItem CodeMeaningMenuItem = new JMenuItem();
	private JScrollPane OutputWindowScrollPane = new JScrollPane();
	private JTextArea OutputWindowPane = new JTextArea();
	private JButton ClearOutputWindowButton = new JButton();
	
	public GUIOutputWindow(){
		OutputWindow.setVisible(true);
		OutputWindow.setTitle("Output Code Window");
		OutputWindow.setBorder(new LineBorder(Color.red));
		OutputWindow.setClosable(true);
		
		//======== OutputCodeWindowMenuBar ========
		{

			//======== OuputCodeOptionsMenu ========
			{
				OuputCodeOptionsMenu.setText("Output Code Options");

				//---- OpenLogMenuItem ----
				OpenLogMenuItem.setText("Open Log");
				OuputCodeOptionsMenu.add(OpenLogMenuItem);

				//---- StopLogMenuItem ----
				StopLogMenuItem.setText("Stop Log");
				OuputCodeOptionsMenu.add(StopLogMenuItem);

				//---- SaveOutputMenuItem ----
				SaveOutputMenuItem.setText("Save Output");
				OuputCodeOptionsMenu.add(SaveOutputMenuItem);
			}
			OutputCodeWindowMenuBar.add(OuputCodeOptionsMenu);

			//======== OutputDevelopmentCodeMenu ========
			{
				OutputDevelopmentCodeMenu.setText("Development Code");

				//======== FunctionsMenu ========
				{
					FunctionsMenu.setText("Functions");

					//---- IfStatementMenuItem ----
					IfStatementMenuItem.setText("If Statement");
					FunctionsMenu.add(IfStatementMenuItem);

					//---- WhileLoopMenuItem ----
					WhileLoopMenuItem.setText("While Loop");
					FunctionsMenu.add(WhileLoopMenuItem);

					//---- DoLoopMenuItem ----
					DoLoopMenuItem.setText("Do Loop");
					FunctionsMenu.add(DoLoopMenuItem);

					//---- ForLoopMenuItem ----
					ForLoopMenuItem.setText("For  Loop");
					FunctionsMenu.add(ForLoopMenuItem);
				}
				OutputDevelopmentCodeMenu.add(FunctionsMenu);

				//---- DrawTriangleMenuItem ----
				DrawTriangleMenuItem.setText("Draw Triangle");
				OutputDevelopmentCodeMenu.add(DrawTriangleMenuItem);

				//---- DrawRectangleMenuItem ----
				DrawRectangleMenuItem.setText("Draw Rectangle");
				OutputDevelopmentCodeMenu.add(DrawRectangleMenuItem);
			}
			OutputCodeWindowMenuBar.add(OutputDevelopmentCodeMenu);

			//======== SourceCodeHelpMenu ========
			{
				SourceCodeHelpMenu.setText("Source Code Help");

				//---- CodeMeaningMenuItem ----
				CodeMeaningMenuItem.setText("Code Meaning");
				SourceCodeHelpMenu.add(CodeMeaningMenuItem);
			}
			OutputCodeWindowMenuBar.add(SourceCodeHelpMenu);
		}
		OutputCodeWindowFrame.setJMenuBar(OutputCodeWindowMenuBar);
		
		
		//======== OutputWindowScrollPane ========
		{

			//---- OutputWindowPane ----
			OutputWindowScrollPane.setViewportView(OutputWindowPane);
		}
		OutputWindowScrollPane.setBounds(5, 5, 405, 120);

		//---- ClearOutputWindowButton ----
		ClearOutputWindowButton.setText("Clear Output Window");
		ClearOutputWindowButton.setBounds(5, 125, 155, 15);
		ClearOutputWindowButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClearOutputWindowButtonActionPerformed(e);
			}
		});
	}
	
	public JMenuBar getOutputCodeWindowMenuBar() {
		return OutputCodeWindowMenuBar;
	}
	public JScrollPane getOutputWindowScrollPane() {
		return OutputWindowScrollPane;
	}
	public JButton getClearOutputWindowButton() {
		return ClearOutputWindowButton;
	}
	public JTextArea getEditorPane(){
		return OutputWindowPane;
	}
	
	public JInternalFrame getOutputWindowFrame() {
		return OutputCodeWindowFrame;
	}
	
	//ClearOutputWindowButton Listener
	public void ClearOutputWindowButtonActionPerformed(ActionEvent e){
		OutputWindowPane.setText("");
	}
}
