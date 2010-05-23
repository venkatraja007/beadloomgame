/*******************************************************************
* 																   *
*	NAME:		GUIMovePanel.java			                       *
*	DATE:		4/22/08								               *
*	VERSION:	1.0.0							                   *
*	PURPOSE:	Holds buttons for shifting layers, undo, redo,	   *
*				and clear grid                                     *
*	DOCUMENT REFERENCE(S):	                    				   *
*											                       *
*	PROCEDURE INVOCATION:						                   *
*	            GUIMovePanel()                                     *
*				                                                   *
*	INPUT PARAMETERS:							                   *
*		        none                                               *
*								                                   *
*	OUTPUT PARAMETERS:							                   *
*		        JButtons                                           *
*											                       *
*	ASSUMPTIONS: None				                               *
*	LIMITATIONS: Undo/Redo only deal with placement of layers, not *
*				 with shifting of layers.						   *
*				 Entire layers can be shifted, but not individual  *
*				 beads within those layers.                        *
*									                               *
*											                       *
*******************************************************************/

import java.awt.Color;
import java.awt.Font;


import javax.swing.JButton;

import javax.swing.JPanel;

import javax.swing.border.LineBorder;

public class GUIMovePanel extends JPanel{
	
	private JPanel MoveLastDrawnBeadsPanel = new JPanel();
	private JButton MoveUpButton = new JButton();
	private JButton MoveLeftButton = new JButton();
	private JButton MoveRightButton = new JButton();
	private JButton MoveDownButton = new JButton();
	private JButton TopColorsButton = new JButton();
	private JButton ClearGridButton = new JButton();
	private JButton UndoButton = new JButton();
	private JButton RedoButton = new JButton();
	private Color color = new Color(255, 0, 0);
		public GUIMovePanel() {
				
		//---- move beads panel -----
		MoveLastDrawnBeadsPanel.setBorder(new LineBorder(Color.red));
		MoveLastDrawnBeadsPanel.setBackground(Color.white);
		MoveLastDrawnBeadsPanel.setLayout(null);
				
		//---- clear grid ----
		ClearGridButton.setText("Clear Grid");
		MoveLastDrawnBeadsPanel.add(ClearGridButton);
		ClearGridButton.setBounds(24, 130, 145, ClearGridButton.getPreferredSize().height);

		//---- undo button ----
		UndoButton.setText("Undo");
		MoveLastDrawnBeadsPanel.add(UndoButton);
		UndoButton.setBounds(25, 95, 63, UndoButton.getPreferredSize().height);
		
		//---- top colors button ----
		TopColorsButton.setText("Colors");
		//MoveLastDrawnBeadsPanel.add(TopColorsButton);
		
		TopColorsButton.setBounds(65, 95, 71, TopColorsButton.getPreferredSize().height);
		TopColorsButton.setBackground(color);
		
		//---- undo button ----
		RedoButton.setText("Redo");
		MoveLastDrawnBeadsPanel.add(RedoButton);
		RedoButton.setBounds(105, 95, 63, RedoButton.getPreferredSize().height);

		//---- move up ----
		MoveUpButton.setText("Up");
		MoveUpButton.setFont(new Font("Verdana", Font.BOLD, 11));
		MoveLastDrawnBeadsPanel.add(MoveUpButton);
		MoveUpButton.setBounds(55, 5, 80, 20);

		//---- move right ----
		MoveRightButton.setText("Right");
		MoveRightButton.setFont(new Font("Verdana", Font.BOLD, 11));
		MoveLastDrawnBeadsPanel.add(MoveRightButton);
		MoveRightButton.setBounds(105, 35, 80, 20);

		//---- move down ----
		MoveDownButton.setText("Down");
		MoveDownButton.setFont(new Font("Verdana", Font.BOLD, 11));
		MoveLastDrawnBeadsPanel.add(MoveDownButton);
		MoveDownButton.setBounds(55, 65, 80, 20);

		//---- move left ----
		MoveLeftButton.setText("Left");
		MoveLeftButton.setFont(new Font("Verdana", Font.BOLD, 11));
		MoveLastDrawnBeadsPanel.add(MoveLeftButton);
		MoveLeftButton.setBounds(5, 35, 80, 20);					
		
	  MoveLastDrawnBeadsPanel.setBounds(4, 3, 189, 155);
	}
	
	public Color getColor()
	{
		return color;
	}
	public void setColor(Color c)
	{
		color = c;
	}
	
	/*public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == TopColorsButton)
		{
			Color c;
			JColorChooser j = new JColorChooser();
			c = j.showDialog(this, null, color);
			if(c != null)
				color = c;
			TopColorsButton.setBackground(color);
		}
	}*/
	
	public JPanel getMoveLastDrawnBeadsPanel() {
		return MoveLastDrawnBeadsPanel;
	}
	public JButton getMoveUpButton() {
		return MoveUpButton;
	}
	public JButton getMoveLeftButton() {
		return MoveLeftButton;
	}
	public JButton getMoveRightButton() {
		return MoveRightButton;
	}
	public JButton getMoveDownButton() {
		return MoveDownButton;
	}
	public JButton getTopColorsButton() {
		return TopColorsButton;
	}
	public JButton getClearGridButton() {
		return ClearGridButton;
	}
	public JButton getUndoButton() {
		return UndoButton;
	}
	public JButton getRedoButton() {
		return RedoButton;
	}
}
