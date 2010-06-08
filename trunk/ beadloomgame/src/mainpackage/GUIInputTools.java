/*******************************************************************
 * 																   *
 *	NAME:		GUIInputTools.java		                           *
 *	DATE:		4/22/08								               *
 *	VERSION:	1.0.0							                   *
 *	PURPOSE:	Create a Layer from input values and pass it	   *
 *				onto the GridPanel class for graphing.             *
 *	DOCUMENT REFERENCE(S):	                    				   *
 *											                       *
 *	PROCEDURE INVOCATION:						                   *
 *	      actionPerformed(ActionEvent)                             *
 *				                                                   *
 *	INPUT PARAMETERS:							                   *
 *		  Integers, Floats                                         *
 *								                                   *
 *	OUTPUT PARAMETERS:							                   *
 *		   Layer                                                   *
 *											                       *
 *	ASSUMPTIONS: None			                                   *
 *	LIMITATIONS: Input values must be between -50 and 50           *
 *									                               *
 *											                       *
 *******************************************************************/
package src.mainpackage;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.ImageProducer;
import java.lang.Double;
import javax.swing.*;

import java.net.*;
import java.util.ArrayList;


public class GUIInputTools extends JApplet implements ActionListener, ItemListener{
	private GridPanel grid;
	private Layer lay;

	//======= Utilities Tabbed Panel=======
	private JTabbedPane BeadLoomUtilitiesTabbedPane = new JTabbedPane();
	private JPanel BeadLoomUtilitiesPanel = new JPanel();
	private JLabel DrawPointXLabel = new JLabel();
	private JLabel DrawPointYLabel = new JLabel();
	private JLabel DrawLineX1Label = new JLabel();
	private JLabel DrawLineY1Label = new JLabel();
	private JLabel DrawLineX2Label = new JLabel();
	private JLabel DrawLineY2Label = new JLabel();
	private JTextField DrawPointXTextField = new JTextField();
	private JTextField DrawPointYTextField = new JTextField();
	private JTextField DrawLineX1TextField = new JTextField();
	private JTextField DrawLineX2TextField = new JTextField();
	private JTextField DrawLineY1TextField = new JTextField();
	private JTextField DrawLineY2TextField = new JTextField();
	private JButton DrawPointButton = new JButton();
	private JButton DrawLineButton = new JButton();

	//  ------- Layers Tabbed Pane -------
	private JTabbedPane LayersDrawTabbedPane = new JTabbedPane();
	private JLayeredPane LayersSelectPane = new JLayeredPane();
	private JComboBox LayersBox = new JComboBox();
	private JButton DeleteLayer = new JButton();
	private JLabel LayersLabel = new JLabel();


	//	------- Rectangle Tabbed Pane -------
	private JTabbedPane RectangleDrawTabbedPane = new JTabbedPane();
	private JLayeredPane RectangleDrawLayeredPane = new JLayeredPane();
	private JButton DrawRectangleButton = new JButton();
	private JLabel DrawRectangleX1Label = new JLabel();
	private JLabel DrawRectangleX2Label = new JLabel();
	private JLabel DrawRectangleY1Label = new JLabel();
	private JLabel DrawRectangleY2Label = new JLabel();
	private JTextField DrawRectangleX1TextField = new JTextField();
	private JTextField DrawRectangleX2TextField = new JTextField();
	private JTextField DrawRectangleY1TextField = new JTextField();
	private JTextField DrawRectangleY2TextField = new JTextField();

	//------- Triangle Tabbed Pane -------
	private JTabbedPane TriangleDrawTabbedPane = new JTabbedPane();
	private JPanel TriangleDrawPanel = new JPanel();
	private JLabel DrawTriangleX1Label = new JLabel();
	private JLabel DrawTriangleX2Label = new JLabel();
	private JLabel DrawTriangleX3Label = new JLabel();
	private JLabel DrawTriangleY1Label = new JLabel();
	private JLabel DrawTriangleY2Label = new JLabel();
	private JLabel DrawTriangleY3Label = new JLabel();
	private JButton DrawTriangleButton = new JButton();
	private JTextField DrawTriangleX1TextField = new JTextField();
	private JTextField DrawTriangleX2TextField = new JTextField();
	private JTextField DrawTriangleX3TextField = new JTextField();
	private JTextField DrawTriangleY1TextField = new JTextField();
	private JTextField DrawTriangleY2TextField = new JTextField();
	private JTextField DrawTriangleY3TextField = new JTextField();

	//------- Linear Iteration Components -------
	private JTabbedPane LinearIterationTabbedPane = new JTabbedPane();
	private JPanel LinearIterationPanel = new JPanel();
	private JButton DrawLinearIterationButton = new JButton();
	private JButton LinearIterationHelpButton = new JButton();
	private JLabel LinearIterationHelpLabel = new JLabel();
	private JLabel LinearIterationFunctionLabel = new JLabel();
	private JLabel LinearIterationFunctionArgsLabel = new JLabel();
	private JLabel LinearIterationFunctionArgs2Label = new JLabel();
	private JLabel LinearIterationStartXLabel = new JLabel();
	private JLabel LinearIterationStartYLabel = new JLabel();
	private JLabel LinearIterationStartLengthLabel = new JLabel();
	private JLabel LinearIterationIncLabel = new JLabel();
	private JLabel LinearIterationRowsTotalLabel = new JLabel();
	private JLabel LinearDirectionLabel = new JLabel();
	private JComboBox LinearIterationDirectionSelector = new JComboBox();
	private JTextField LinearIterationStartXTextField = new JTextField();
	private JTextField LinearIterationStartYTextField = new JTextField();
	private JTextField LinearIterationStartLengthTextField = new JTextField();
	private JTextField LinearIterationInc1TextField = new JTextField();
	private JTextField LinearIterationInc2TextField = new JTextField();
	//	private JTextField LinearIterationColorTextField = new JTextField();
	private JTextField LinearIterationRowsTotalTextField = new JTextField();

	//------- Triangle Iteration Components -------
	private JTabbedPane TriangleIterationTabbedPane = new JTabbedPane();
	private JPanel TriangleIterationPanel = new JPanel();
	private String DirectionStrings[] = {"+Y", "-Y", "+X", "-X"};
	private JButton DrawTriangleIterationButton = new JButton();
	private JButton TriangleIterationHelpButton = new JButton();
	private JLabel TriangleIterationHelpLabel = new JLabel();
	private JLabel TriangleIterationFunctionLabel = new JLabel();
	private JLabel TriangleIterationFunctionArgsLabel = new JLabel();
	private JLabel TriangleIterationFunctionArgs2Label = new JLabel();
	private JLabel TriangleIterationStartXLabel = new JLabel();
	private JLabel TriangleIterationStartYLabel = new JLabel();
	private JLabel TriangleIterationBeadsAddedLabel = new JLabel();
	private JLabel TriangleIterationStepHeightLabel = new JLabel();
	private JLabel TriangleIterationRowsTotalLabel = new JLabel();
	private JLabel TriangleDirectionLabel = new JLabel();
	private JComboBox TriangleIterationDirectionSelector = new JComboBox();
	private JTextField TriangleIterationStartXTextField = new JTextField();
	private JTextField TriangleIterationStartYTextField = new JTextField();
	private JTextField TriangleIterationStepHeightTextField = new JTextField();
	private JTextField TriangleIterationBeadsAddedTextField = new JTextField();
	//	private JTextField TriangleIterationColorTextField = new JTextField();
	private JTextField TriangleIterationRowsTotalTextField = new JTextField();

	//-------- Linear Iteration Loop Tool ---------  
	private JLayeredPane LinearItrLoopLayeredPane = new JLayeredPane();
	private JButton DrawLinearItrLoopButton = new JButton();
	private JButton LinearItrLoopItrHelpButton = new JButton();
	private JButton LinearItrLoopHelpButton = new JButton();
	private JLabel LinearItrLoopItrHelpLabel = new JLabel();
	private JLabel LinearItrLoopStartXLabel = new JLabel();
	private JLabel LinearItrLoopStartYLabel = new JLabel();
	private JLabel LinearItrLoopEndYLabel = new JLabel();
	private JLabel LinearItrLoopStartNLabel = new JLabel();
	private JLabel LinearItrLoopToNLabel = new JLabel();
	private JLabel LinearItrLoopEndNLabel = new JLabel();
	private JLabel LinearItrLoopFunctStartLabel = new JLabel();
	private JLabel LinearItrLoopFunctComma1Label = new JLabel();
	private JLabel LinearItrLoopFunctComma2Label = new JLabel();
	private JLabel LinearItrLoopFunctComma3Label = new JLabel();
	private JLabel LinearItrLoopFunctComma4Label = new JLabel();
	private JLabel LinearItrLoopFunctEndLabel = new JLabel();
	private JLabel LinearItrLoopSemiLabel = new JLabel();
	private JLabel LinearItrLoopSemi1Label = new JLabel();
	private JLabel LinearItrLoopEndBracketLabel = new JLabel();
	private JComboBox LinearItrLoopStartLengthSelector = new JComboBox();
	private JComboBox LinearItrLoopDirectionSelector = new JComboBox();
	private JComboBox LinearItrLoopStatement1Selector = new JComboBox();
	private JComboBox LinearItrLoopPlusOrMinus1Selector = new JComboBox();
	private JComboBox LinearItrLoopStatement2Selector = new JComboBox();
	private JComboBox LinearItrLoopPlusOrMinus2Selector = new JComboBox();
	private JTextField LinearItrLoopStartXTextField = new JTextField();
	private JTextField LinearItrLoopStartYTextField = new JTextField();
	private JTextField LinearItrLoopStartNTextField = new JTextField();
	private JTextField LinearItrLoopEndNTextField = new JTextField();
	private JTextField LinearItrLoopInc1TextField = new JTextField();
	private JTextField LinearItrLoopInc2TextField = new JTextField();
	private JTextField LinearItrLoopRowsTextField = new JTextField();
	private JTextField LinearItrLoopStatement1TextField = new JTextField();
	private JTextField LinearItrLoopStatement2TextField = new JTextField();

	//-------- Trigonometric Functions ---------
	private JTabbedPane TrigFunctionsTabbedPane = new JTabbedPane();
	private JLayeredPane TrigFunctionsLayeredPane = new JLayeredPane();
	private JButton GraphTrigFunctionButton = new JButton();
	private JLabel YEqualsLabel = new JLabel();
	private JTextField TrigMultiplierTextField = new JTextField();
	private JComboBox TrigFunctionSelector = new JComboBox();
	private String TrigFunctionStrings[] = {"Sin", "Cos", "Tan", "arcSin", "arcCos", "arcTan"};
	private JLabel TrigOpenParenthesisLabel = new JLabel();
	private JTextField TrigFunctionArgumentTextField = new JTextField();
	private JLabel TrigCloseParenthesisLabel = new JLabel();
	private JComboBox TrigPlusOrMinusSelector = new JComboBox();
	private String TrigPlusOrMinusStrings[] = {"+", "-"};
	private JTextField TrigAdditiveTextField = new JTextField();

	//-------- Triangle Iteration Loop Tool ---------  
	private JLayeredPane TriangleItrLoopLayeredPane = new JLayeredPane();
	private String TriangleItrLoopIncrementStrings[] = {"Y = Y", "Y = X", "X = Y", "X = X"};
	private String SelectionStrings[] = {"N", "1", "2", "3", "4", "5", "6"};
	private String TriangleItrLoopPlusOrMinusStrings[] = {"+", "-"};
	private JButton DrawTriangleItrLoopButton = new JButton();
	private JButton TriangleItrLoopItrHelpButton = new JButton();
	private JButton TriangleItrLoopHelpButton = new JButton();
	//private JLabel TriangleItrLoopImage = new JLabel();
	private JLabel TriangleItrLoopItrHelpLabel = new JLabel();
	private JLabel TriangleItrLoopStartXLabel = new JLabel();
	private JLabel TriangleItrLoopStartYLabel = new JLabel();
	private JLabel TriangleItrLoopEndYLabel = new JLabel();
	private JLabel TriangleItrLoopStartNLabel = new JLabel();
	private JLabel TriangleItrLoopToNLabel = new JLabel();
	private JLabel TriangleItrLoopEndNLabel = new JLabel();
	private JLabel TriangleItrLoopFunctStartLabel = new JLabel();
	private JLabel TriangleItrLoopFunctNLabel = new JLabel();
	private JLabel TriangleItrLoopFunctN2Label = new JLabel();
	private JLabel TriangleItrLoopFunctCommaLabel = new JLabel();
	private JLabel TriangleItrLoopFunctEndLabel = new JLabel();
	private JLabel TriangleItrLoopSemiLabel = new JLabel();
	private JLabel TriangleItrLoopSemi1Label = new JLabel();
	private JLabel TriangleItrLoopEndBracketLabel = new JLabel();
	private JComboBox TriangleItrLoopStepSelector = new JComboBox();
	private JComboBox TriangleItrLoopNSelector = new JComboBox();
	private JComboBox TriangleItrLoopDirectionSelector = new JComboBox();
	private JComboBox TriangleItrLoopStatement1Selector = new JComboBox();
	private JComboBox TriangleItrLoopPlusOrMinus1Selector = new JComboBox();
	private JComboBox TriangleItrLoopStatement2Selector = new JComboBox();
	private JComboBox TriangleItrLoopPlusOrMinus2Selector = new JComboBox();
	private JTextField TriangleItrLoopStartXTextField = new JTextField();
	private JTextField TriangleItrLoopStartYTextField = new JTextField();
	private JTextField TriangleItrLoopStartNTextField = new JTextField();
	private JTextField TriangleItrLoopEndNTextField = new JTextField();
	private JTextField TriangleItrLoopStepTextField = new JTextField();
	private JTextField TriangleItrLoopRowsTextField = new JTextField();
	private JTextField TriangleItrLoopStatement1TextField = new JTextField();
	private JTextField TriangleItrLoopStatement2TextField = new JTextField();


	//Grid stuff
	//GridPanel gp = new GridPanel();
	int GRID_SIZE = 40; // default grid size
	int PAD       = 20;
	double xInc;
	double yInc;

	Image catimage;
	double gridX;
	double gridY;
	URL u = null;
	private Color color;

	//Bead Information
	Image beadImage = null;	

	///////////////////////////////////////////////////////////////////////////////////////
	//============================== Panel implementation ===============================//
	///////////////////////////////////////////////////////////////////////////////////////



	public GUIInputTools() {
		//======== BeadLoomUtilitiesTabbedPane ========

		//======== BeadLoomUtilitiesPanel ========
		{
			BeadLoomUtilitiesPanel.setLayout(null);

			//---- DrawPointXLabel ----
			DrawPointXLabel.setText("X =");
			BeadLoomUtilitiesPanel.add(DrawPointXLabel);
			DrawPointXLabel.setBounds(140, 15, 25, DrawPointXLabel.getPreferredSize().height);

			//---- DrawPointYLabel ----
			DrawPointYLabel.setText(" Y =");
			BeadLoomUtilitiesPanel.add(DrawPointYLabel);
			DrawPointYLabel.setBounds(210, 15, 27, 14);

			//---- DrawLineX1Label ----
			DrawLineX1Label.setText("X1 =");
			BeadLoomUtilitiesPanel.add(DrawLineX1Label);
			DrawLineX1Label.setBounds(135, 60, 25, 14);

			//---- DrawLineY1Label ----
			DrawLineY1Label.setText(" Y1 =");
			BeadLoomUtilitiesPanel.add(DrawLineY1Label);
			DrawLineY1Label.setBounds(205, 60, 30, 14);

			//---- DrawLineX2Label ----
			DrawLineX2Label.setText("X2 =");
			BeadLoomUtilitiesPanel.add(DrawLineX2Label);
			DrawLineX2Label.setBounds(135, 95, 25, 14);

			//---- DrawLineY2Label ----
			DrawLineY2Label.setText("   Y2 =");
			BeadLoomUtilitiesPanel.add(DrawLineY2Label);
			DrawLineY2Label.setBounds(200, 95, 35, 14);

			//---- DrawPointXTextField ----
			DrawPointXTextField.setText("0");
			BeadLoomUtilitiesPanel.add(DrawPointXTextField);
			DrawPointXTextField.setBounds(165, 10, 30, 26);

			//---- DrawPointYTextField ----
			DrawPointYTextField.setText("0");
			BeadLoomUtilitiesPanel.add(DrawPointYTextField);
			DrawPointYTextField.setBounds(235, 10, 30, 26);

			//---- DrawLineX1TextField ----
			DrawLineX1TextField.setText("0");
			BeadLoomUtilitiesPanel.add(DrawLineX1TextField);
			DrawLineX1TextField.setBounds(165, 55, 30, 26);

			//---- DrawLineX2TextField ----
			DrawLineX2TextField.setText("7");
			BeadLoomUtilitiesPanel.add(DrawLineX2TextField);
			DrawLineX2TextField.setBounds(165, 90, 30, 26);

			//---- DrawLineY1TextField ----
			DrawLineY1TextField.setText("0");
			BeadLoomUtilitiesPanel.add(DrawLineY1TextField);
			DrawLineY1TextField.setBounds(235, 55, 30, 26);

			//---- DrawLineY2TextField ----
			DrawLineY2TextField.setText("7");
			BeadLoomUtilitiesPanel.add(DrawLineY2TextField);
			DrawLineY2TextField.setBounds(235, 90, 30, 26);

			//---- DrawPointButton ----
			DrawPointButton.setText("Draw Point");
			BeadLoomUtilitiesPanel.add(DrawPointButton);
			DrawPointButton.setBounds(5, 10, 115, 21);
			DrawPointButton.addActionListener(this);

			//---- DrawLineButton ----
			DrawLineButton.setText("Draw Line");
			BeadLoomUtilitiesPanel.add(DrawLineButton);
			DrawLineButton.setBounds(5, 55, 115, 21);
			DrawLineButton.addActionListener(this);


		}
		BeadLoomUtilitiesTabbedPane.addTab("Point & Line Functions", BeadLoomUtilitiesPanel);


		//			======== RectangleDrawTabbedPane ========
		{

			//======== RectangleDrawLayeredPane ========
			{

				//---- DrawRectangleButton ----
				DrawRectangleButton.setText("Draw Rectangle");
				RectangleDrawLayeredPane.add(DrawRectangleButton, JLayeredPane.DEFAULT_LAYER);
				DrawRectangleButton.setBounds(5, 25, 135, 21);
				DrawRectangleButton.addActionListener(this);

				//---- DrawRectangleX1Label ----
				DrawRectangleX1Label.setText("X1 =");
				RectangleDrawLayeredPane.add(DrawRectangleX1Label, JLayeredPane.DEFAULT_LAYER);
				DrawRectangleX1Label.setBounds(160, 10, 25, 14);

				//---- DrawRectangleX2Label ----
				DrawRectangleX2Label.setText("X2 =");
				RectangleDrawLayeredPane.add(DrawRectangleX2Label, JLayeredPane.DEFAULT_LAYER);
				DrawRectangleX2Label.setBounds(160, 45, 25, 14);

				//---- DrawRectangleY1Label ----
				DrawRectangleY1Label.setText("Y1 =");
				RectangleDrawLayeredPane.add(DrawRectangleY1Label, JLayeredPane.DEFAULT_LAYER);
				DrawRectangleY1Label.setBounds(240, 10, 25, 14);

				//---- DrawRectangleY2Label ----
				DrawRectangleY2Label.setText("Y2 =");
				RectangleDrawLayeredPane.add(DrawRectangleY2Label, JLayeredPane.DEFAULT_LAYER);
				DrawRectangleY2Label.setBounds(240, 45, 25, 14);

				//---- DrawRectangleX1TextField ----
				DrawRectangleX1TextField.setText("0");
				RectangleDrawLayeredPane.add(DrawRectangleX1TextField, JLayeredPane.DEFAULT_LAYER);
				DrawRectangleX1TextField.setBounds(190, 5, 30, 26);

				//---- DrawRectangleX2TextField ----
				DrawRectangleX2TextField.setText("6");
				RectangleDrawLayeredPane.add(DrawRectangleX2TextField, JLayeredPane.DEFAULT_LAYER);
				DrawRectangleX2TextField.setBounds(190, 40, 30, 26);

				//---- DrawRectangleY1TextField ----
				DrawRectangleY1TextField.setText("0");
				RectangleDrawLayeredPane.add(DrawRectangleY1TextField, JLayeredPane.DEFAULT_LAYER);
				DrawRectangleY1TextField.setBounds(270, 5, 30, 26);

				//---- DrawRectangleY2TextField ----
				DrawRectangleY2TextField.setText("6");
				RectangleDrawLayeredPane.add(DrawRectangleY2TextField, JLayeredPane.DEFAULT_LAYER);
				DrawRectangleY2TextField.setBounds(270, 40, 30, 26);
			}
			RectangleDrawTabbedPane.addTab("Rectangle Draw", RectangleDrawLayeredPane);

		}
		BeadLoomUtilitiesTabbedPane.addTab("Rectangle Function", RectangleDrawTabbedPane);


		//			======== LayersTabbedPane ========
		{

			//======== LayersSelectLayeredPane ========
			{

				LayersLabel.setText("Select a layer to modify");
				LayersSelectPane.add(LayersLabel, JLayeredPane.DEFAULT_LAYER);
				LayersLabel.setBounds(200, 1, 300, 30);
				DeleteLayer.setBounds(5, 54, 115, 21);
				DeleteLayer.setText("Delete Layer");
				LayersSelectPane.add(DeleteLayer);
				LayersSelectPane.add(LayersBox, JLayeredPane.DEFAULT_LAYER);
				LayersBox.setBounds(5, 30, 400, 21);
				LayersBox.addItemListener(this);
				DeleteLayer.addActionListener(this);
			}
			LayersDrawTabbedPane.addTab("Modify Layers", LayersSelectPane);

		}
		BeadLoomUtilitiesTabbedPane.addTab("Layers Functions", LayersDrawTabbedPane);


		//======== TriangleDrawTabbedPane ========
		{

			//======== TriangleDrawPanel ========
			{
				TriangleDrawPanel.setLayout(null);

				//---- DrawTriangleX1Label ----
				DrawTriangleX1Label.setText("X1 =");
				TriangleDrawPanel.add(DrawTriangleX1Label);
				DrawTriangleX1Label.setBounds(130, 10, 25, 14);

				//---- DrawTriangleX2Label ----
				DrawTriangleX2Label.setText("X2 =");
				TriangleDrawPanel.add(DrawTriangleX2Label);
				DrawTriangleX2Label.setBounds(130, 45, 25, 14);

				//---- DrawTriangleX3Label ----
				DrawTriangleX3Label.setText("X3 =");
				TriangleDrawPanel.add(DrawTriangleX3Label);
				DrawTriangleX3Label.setBounds(130, 80, 25, 14);

				//---- DrawTriangleY1Label ----
				DrawTriangleY1Label.setText("Y1 =");
				TriangleDrawPanel.add(DrawTriangleY1Label);
				DrawTriangleY1Label.setBounds(210, 10, 25, 14);

				//---- DrawTriangleY2Label ----
				DrawTriangleY2Label.setText("Y2 =");
				TriangleDrawPanel.add(DrawTriangleY2Label);
				DrawTriangleY2Label.setBounds(210, 45, 25, 14);

				//---- DrawTriangleY3Label ----
				DrawTriangleY3Label.setText("Y3 =");
				TriangleDrawPanel.add(DrawTriangleY3Label);
				DrawTriangleY3Label.setBounds(210, 80, 25, 14);

				//---- DrawTriangleButton ----
				DrawTriangleButton.setText("Draw Triangle");
				TriangleDrawPanel.add(DrawTriangleButton);
				DrawTriangleButton.setBounds(5, 40, 115, 21);
				DrawTriangleButton.addActionListener(this);

				//---- DrawTriangleX1TextField ----
				DrawTriangleX1TextField.setText("0");
				TriangleDrawPanel.add(DrawTriangleX1TextField);
				DrawTriangleX1TextField.setBounds(160, 5, 30, 26);

				//---- DrawTriangleX2TextField ----
				DrawTriangleX2TextField.setText("4");
				TriangleDrawPanel.add(DrawTriangleX2TextField);
				DrawTriangleX2TextField.setBounds(160, 40, 30, 26);

				//---- DrawTriangleX3TextField ----
				DrawTriangleX3TextField.setText("0");
				TriangleDrawPanel.add(DrawTriangleX3TextField);
				DrawTriangleX3TextField.setBounds(160, 75, 30, 26);

				//---- DrawTriangleY1TextField ----
				DrawTriangleY1TextField.setText("0");
				TriangleDrawPanel.add(DrawTriangleY1TextField);
				DrawTriangleY1TextField.setBounds(240, 5, 30, 26);

				//---- DrawTriangleY2TextField ----
				DrawTriangleY2TextField.setText("0");
				TriangleDrawPanel.add(DrawTriangleY2TextField);
				DrawTriangleY2TextField.setBounds(240, 40, 30, 26);

				//---- DrawTriangleY3TextField ----
				DrawTriangleY3TextField.setText("4");
				TriangleDrawPanel.add(DrawTriangleY3TextField);
				DrawTriangleY3TextField.setBounds(240, 75, 30, 26);


			}
			TriangleDrawTabbedPane.addTab("Triangle Draw", TriangleDrawPanel);

		}
		BeadLoomUtilitiesTabbedPane.addTab("Triangle Function", TriangleDrawTabbedPane);


		//======== IterationTabbedPane ========
		{

			//======== LinearIterationPanel ========
			{
				LinearIterationPanel.setLayout(null);

				//---- DrawLinearIterationButton ----
				DrawLinearIterationButton.setText("Draw");
				LinearIterationPanel.add(DrawLinearIterationButton);
				DrawLinearIterationButton.setBounds(5, 5, 80, 25);
				DrawLinearIterationButton.addActionListener(this);

				//---- LinearIterationHelpLabel ----
				LinearIterationHelpLabel.setText(" Help with...");
				LinearIterationPanel.add(LinearIterationHelpLabel, JLayeredPane.DEFAULT_LAYER);
				LinearIterationHelpLabel.setBounds(5, 50, 90, 24);

				//---- HelpLinearItrLoopButton ----
				LinearIterationHelpButton.setText("<html>Linear<br>Iteration&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp");
				LinearIterationPanel.add(LinearIterationHelpButton, JLayeredPane.DEFAULT_LAYER);
				LinearIterationHelpButton.setBounds(5, 75, 100,40);
				LinearIterationHelpButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						LinearHelpButtonActionPerformed(e);
					}
				});

				//---- LinearIterationFunctionLabel ----
				LinearIterationFunctionLabel.setText("Function Outline:");
				LinearIterationPanel.add(LinearIterationFunctionLabel);
				LinearIterationFunctionLabel.setBounds(110, 5, 110, 24);

				//---- LinearIterationFunctionArgsLabel ----
				LinearIterationFunctionArgsLabel.setText("linearIteration(startX, startY, startLength, ");
				LinearIterationPanel.add(LinearIterationFunctionArgsLabel);
				LinearIterationFunctionArgsLabel.setBounds(110, 30, 320, 24);

				//---- LinearIterationFunctionArgs2Label ----
				LinearIterationFunctionArgs2Label.setText("beadsAdded1, beadsAdded2, rowsTotal, direction);");
				LinearIterationPanel.add(LinearIterationFunctionArgs2Label);
				LinearIterationFunctionArgs2Label.setBounds(115, 45, 300, 24);

				//---- LinearIterationStartXLabel ----
				LinearIterationStartXLabel.setText("startX:");
				LinearIterationPanel.add(LinearIterationStartXLabel);
				LinearIterationStartXLabel.setBounds(166, 75, 60, 24);

				//---- LinearIterationStartXTextField ----
				LinearIterationStartXTextField.setText("0");
				LinearIterationPanel.add(LinearIterationStartXTextField);
				LinearIterationStartXTextField.setBounds(215, 75, 25, 24);

				//---- LinearIterationStartYLabel ----
				LinearIterationStartYLabel.setText("startY:");
				LinearIterationPanel.add(LinearIterationStartYLabel);
				LinearIterationStartYLabel.setBounds(166, 100, 60, 24);

				//---- LinearIterationStartYTextField ----
				LinearIterationStartYTextField.setText("0");
				LinearIterationPanel.add(LinearIterationStartYTextField);
				LinearIterationStartYTextField.setBounds(215, 100, 25, 24);

				//---- LinearIterationStartLengthLabel ----
				LinearIterationStartLengthLabel.setText("startLength:");
				LinearIterationPanel.add(LinearIterationStartLengthLabel);
				LinearIterationStartLengthLabel.setBounds(134, 125, 75, 24);

				//---- LinearIterationStartLengthTextField ----
				LinearIterationStartLengthTextField.setText("5");
				LinearIterationPanel.add(LinearIterationStartLengthTextField);
				LinearIterationStartLengthTextField.setBounds(215, 125, 25, 24);

				//---- LinearIterationIncLabel ----
				LinearIterationIncLabel.setText("beadsAdded: ");
				LinearIterationPanel.add(LinearIterationIncLabel);
				LinearIterationIncLabel.setBounds(265, 75, 120, 24);

				//---- LinearIterationBeadsInc1TextField ----
				LinearIterationInc1TextField.setText("-1");
				LinearIterationPanel.add(LinearIterationInc1TextField);
				LinearIterationInc1TextField.setBounds(350, 75, 25, 24);

				//---- LinearIterationBeadsInc2TextField ----
				LinearIterationInc2TextField.setText("1");
				LinearIterationPanel.add(LinearIterationInc2TextField);
				LinearIterationInc2TextField.setBounds(375, 75, 25, 24);

				//---- LinearIterationRowsTotalLabel ----
				LinearIterationRowsTotalLabel.setText("rowsTotal: ");
				LinearIterationPanel.add(LinearIterationRowsTotalLabel);
				LinearIterationRowsTotalLabel.setBounds(278, 100, 80, 24);

				//---- LinearIterationRowsTotalTextField ----
				LinearIterationRowsTotalTextField.setText("5");
				LinearIterationPanel.add(LinearIterationRowsTotalTextField);
				LinearIterationRowsTotalTextField.setBounds(350, 100, 25, 24);

				//---- TriangleDirectionLabel ----
				LinearDirectionLabel.setText("direction:");
				LinearIterationPanel.add(LinearDirectionLabel);
				LinearDirectionLabel.setBounds(285, 125, 100, 24);

				//---- LinearItrLoopDirectionSelector ----
				for (int i = 0; i < 4; i++){
					LinearIterationDirectionSelector.addItem(DirectionStrings[i]);}
				LinearIterationPanel.add(LinearIterationDirectionSelector, JLayeredPane.DEFAULT_LAYER);
				LinearIterationDirectionSelector.setBounds(350, 125, 40, 24);

			}
			LinearIterationTabbedPane.addTab("Linear Iteration", LinearIterationPanel);


			//======== TriangleIterationTabbedPane ========
			{

				//======== TriangleIterationPanel ========
				{
					TriangleIterationPanel.setLayout(null);

					//---- DrawTriangleIterationButton ----
					DrawTriangleIterationButton.setText("Draw");
					TriangleIterationPanel.add(DrawTriangleIterationButton);
					DrawTriangleIterationButton.setBounds(5, 5, 80, 25);
					DrawTriangleIterationButton.addActionListener(this);

					//---- TriangleIterationHelpLabel ----
					TriangleIterationHelpLabel.setText(" Help with...");
					TriangleIterationPanel.add(TriangleIterationHelpLabel, JLayeredPane.DEFAULT_LAYER);
					TriangleIterationHelpLabel.setBounds(5, 50, 90, 24);

					//---- HelpLinearItrLoopButton ----
					TriangleIterationHelpButton.setText("<html>Triangle<br>Iteration&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp");
					TriangleIterationPanel.add(TriangleIterationHelpButton, JLayeredPane.DEFAULT_LAYER);
					TriangleIterationHelpButton.setBounds(5, 75, 100,40);
					TriangleIterationHelpButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							TriangleIterHelpButtonActionPerformed(e);
						}
					});

					//---- TriangleIterationFunctionLabel ----
					TriangleIterationFunctionLabel.setText("Function Outline:");
					TriangleIterationPanel.add(TriangleIterationFunctionLabel);
					TriangleIterationFunctionLabel.setBounds(140, 5, 110, 24);

					//---- TriangleIterationFunctionArgsLabel ----
					TriangleIterationFunctionArgsLabel.setText("triangularIteration(startX, startY, stepHeight, ");
					TriangleIterationPanel.add(TriangleIterationFunctionArgsLabel);
					TriangleIterationFunctionArgsLabel.setBounds(140, 30, 300, 24);

					//---- TriangleIterationFunctionArgs2Label ----
					TriangleIterationFunctionArgs2Label.setText("beadsAdded, rowsTotal, direction);");
					TriangleIterationPanel.add(TriangleIterationFunctionArgs2Label);
					TriangleIterationFunctionArgs2Label.setBounds(195, 45, 200, 24);

					//---- TriangleIterationStartXLabel ----
					TriangleIterationStartXLabel.setText("startX:");
					TriangleIterationPanel.add(TriangleIterationStartXLabel);
					TriangleIterationStartXLabel.setBounds(166, 75, 60, 24);

					//---- TriangleIterationStartXTextField ----
					TriangleIterationStartXTextField.setText("0");
					TriangleIterationPanel.add(TriangleIterationStartXTextField);
					TriangleIterationStartXTextField.setBounds(215, 75, 25, 24);

					//---- TriangleIterationStartYLabel ----
					TriangleIterationStartYLabel.setText("startY:");
					TriangleIterationPanel.add(TriangleIterationStartYLabel);
					TriangleIterationStartYLabel.setBounds(166, 100, 60, 24);

					//---- TriangleIterationStartYTextField ----
					TriangleIterationStartYTextField.setText("0");
					TriangleIterationPanel.add(TriangleIterationStartYTextField);
					TriangleIterationStartYTextField.setBounds(215, 100, 25, 24);

					//---- TriangleIterationStepHeightLabel ----
					TriangleIterationStepHeightLabel.setText("stepHeight:");
					TriangleIterationPanel.add(TriangleIterationStepHeightLabel);
					TriangleIterationStepHeightLabel.setBounds(139, 125, 65, 24);

					//---- TriangleIterationStepHeightTextField ----
					TriangleIterationStepHeightTextField.setText("3");
					TriangleIterationPanel.add(TriangleIterationStepHeightTextField);
					TriangleIterationStepHeightTextField.setBounds(215, 125, 25, 24);

					//---- TriangleIterationBeadsAddedLabel ----
					TriangleIterationBeadsAddedLabel.setText("beadsAdded: ");
					TriangleIterationPanel.add(TriangleIterationBeadsAddedLabel);
					TriangleIterationBeadsAddedLabel.setBounds(264, 75, 80, 24);

					//---- TriangleIterationBeadsAddedTextField ----
					TriangleIterationBeadsAddedTextField.setText("1");
					TriangleIterationPanel.add(TriangleIterationBeadsAddedTextField);
					TriangleIterationBeadsAddedTextField.setBounds(350, 75, 25, 24);

					//---- TriangleIterationRowsTotalLabel ----
					TriangleIterationRowsTotalLabel.setText("rowsTotal: ");
					TriangleIterationPanel.add(TriangleIterationRowsTotalLabel);
					TriangleIterationRowsTotalLabel.setBounds(278, 100, 80, 24);

					//---- TriangleIterationRowsTotalTextField ----
					TriangleIterationRowsTotalTextField.setText("9");
					TriangleIterationPanel.add(TriangleIterationRowsTotalTextField);
					TriangleIterationRowsTotalTextField.setBounds(350, 100, 25, 24);

					//---- TriangleDirectionLabel ----
					TriangleDirectionLabel.setText("direction:");
					TriangleIterationPanel.add(TriangleDirectionLabel);
					TriangleDirectionLabel.setBounds(285, 125, 100, 24);

					//---- LinearItrLoopDirectionSelector ----
					for (int i = 0; i < 4; i++){
						TriangleIterationDirectionSelector.addItem(DirectionStrings[i]);}
					TriangleIterationPanel.add(TriangleIterationDirectionSelector, JLayeredPane.DEFAULT_LAYER);
					TriangleIterationDirectionSelector.setSelectedIndex(1);
					TriangleIterationDirectionSelector.setBounds(350, 125, 40, 24);

				}
				LinearIterationTabbedPane.addTab("Triangle Iteration", TriangleIterationPanel);
			}


		}
		//BeadLoomUtilitiesTabbedPane.addTab("Iterative Tools", LinearIterationTabbedPane);


		//======== LinearItrLoopPanel ========
		{

			//---- DrawLinearItrLoopButton ----
			DrawLinearItrLoopButton.setText("Draw");
			LinearItrLoopLayeredPane.add(DrawLinearItrLoopButton, JLayeredPane.DEFAULT_LAYER);
			DrawLinearItrLoopButton.setBounds(5, 5, 80, 25);
			DrawLinearItrLoopButton.addActionListener(this);

			//---- LinearItrLoopItrHelpLabel ----
			LinearItrLoopItrHelpLabel.setText(" Help with...");
			LinearItrLoopLayeredPane.add(LinearItrLoopItrHelpLabel, JLayeredPane.DEFAULT_LAYER);
			LinearItrLoopItrHelpLabel.setBounds(5, 50, 90, 24);

			//---- HelpLinearItrLoopButton ----
			LinearItrLoopItrHelpButton.setText("<html>Linear<br>Iteration");
			LinearItrLoopLayeredPane.add(LinearItrLoopItrHelpButton, JLayeredPane.DEFAULT_LAYER);
			LinearItrLoopItrHelpButton.setBounds(5, 75, 100,40);
			LinearItrLoopItrHelpButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					LinearHelpButtonActionPerformed(e);
				}
			});

			//---- HelpLinearItrLoopButton ----
			LinearItrLoopHelpButton.setText("<html>Iteration<br>Loop");
			LinearItrLoopLayeredPane.add(LinearItrLoopHelpButton, JLayeredPane.DEFAULT_LAYER);
			LinearItrLoopHelpButton.setBounds(5, 122, 100, 40);
			LinearItrLoopHelpButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					LinearItrHelpButtonActionPerformed(e);
				}
			});

			//---- LinearItrLoopStartXLabel ----
			LinearItrLoopStartXLabel.setText("X = ");
			LinearItrLoopLayeredPane.add(LinearItrLoopStartXLabel, JLayeredPane.DEFAULT_LAYER);
			LinearItrLoopStartXLabel.setBounds(120, 5, 35, 24);

			//---- LinearItrLoopStartXTextField ----
			LinearItrLoopStartXTextField.setText("0");
			LinearItrLoopLayeredPane.add(LinearItrLoopStartXTextField, JLayeredPane.DEFAULT_LAYER);
			LinearItrLoopStartXTextField.setBounds(140, 5, 25, 24);

			//---- LinearItrLoopStartYLabel ----
			LinearItrLoopStartYLabel.setText(";    Y = ");
			LinearItrLoopLayeredPane.add(LinearItrLoopStartYLabel, JLayeredPane.DEFAULT_LAYER);
			LinearItrLoopStartYLabel.setBounds(167, 5, 55, 24);

			//---- LinearItrLoopStartYTextField ----
			LinearItrLoopStartYTextField.setText("0");
			LinearItrLoopLayeredPane.add(LinearItrLoopStartYTextField, JLayeredPane.DEFAULT_LAYER);
			LinearItrLoopStartYTextField.setBounds(203, 5, 25, 24);

			//---- LinearItrLoopEndYLabel ----
			LinearItrLoopEndYLabel.setText(" ;");
			LinearItrLoopLayeredPane.add(LinearItrLoopEndYLabel, JLayeredPane.DEFAULT_LAYER);
			LinearItrLoopEndYLabel.setBounds(227, 5, 10, 24);

			//---- LinearItrLoopStartNLabel ----
			LinearItrLoopStartNLabel.setText("For  (   N = ");
			LinearItrLoopLayeredPane.add(LinearItrLoopStartNLabel, JLayeredPane.DEFAULT_LAYER);
			LinearItrLoopStartNLabel.setBounds(120, 35, 70, 24);

			//---- LinearItrLoopStartNTextField ----
			LinearItrLoopStartNTextField.setText("3");
			LinearItrLoopLayeredPane.add(LinearItrLoopStartNTextField, JLayeredPane.DEFAULT_LAYER);
			LinearItrLoopStartNTextField.setBounds(180, 35, 25, 24);

			//---- LinearItrLoopToNLabel ----
			LinearItrLoopToNLabel.setText(" to ");
			LinearItrLoopLayeredPane.add(LinearItrLoopToNLabel, JLayeredPane.DEFAULT_LAYER);
			LinearItrLoopToNLabel.setBounds(205, 35, 25, 24);

			//---- LinearItrLoopEndNTextField ----
			LinearItrLoopEndNTextField.setText("5");
			LinearItrLoopLayeredPane.add(LinearItrLoopEndNTextField, JLayeredPane.DEFAULT_LAYER);
			LinearItrLoopEndNTextField.setBounds(225, 35, 25, 24);

			//---- LinearItrLoopEndNLabel ----
			LinearItrLoopEndNLabel.setText(" )");
			LinearItrLoopLayeredPane.add(LinearItrLoopEndNLabel, JLayeredPane.DEFAULT_LAYER);
			LinearItrLoopEndNLabel.setBounds(255, 35, 15, 24);

			//---- LinearItrLoopFunctStartLabel ----
			LinearItrLoopFunctStartLabel.setText("linearIteration(X,Y,");
			LinearItrLoopLayeredPane.add(LinearItrLoopFunctStartLabel, JLayeredPane.DEFAULT_LAYER);
			LinearItrLoopFunctStartLabel.setBounds(134, 62, 120, 24);

			//---- LinearItrLoopStartLengthSelector ----
			for (int i = 0; i < 7; i++){
				LinearItrLoopStartLengthSelector.addItem(SelectionStrings[i]);}
			LinearItrLoopLayeredPane.add(LinearItrLoopStartLengthSelector, JLayeredPane.DEFAULT_LAYER);
			LinearItrLoopStartLengthSelector.setSelectedIndex(0);
			LinearItrLoopStartLengthSelector.setBounds(239, 62, 35, 24);

			//---- LinearItrLoopFunctCommaLabel ----
			LinearItrLoopFunctComma4Label.setText(",");
			LinearItrLoopLayeredPane.add(LinearItrLoopFunctComma4Label, JLayeredPane.DEFAULT_LAYER);
			LinearItrLoopFunctComma4Label.setBounds(275, 62, 10, 24);

			//---- LinearItrLoopInc1TextField ----
			LinearItrLoopInc1TextField.setText("-1");
			LinearItrLoopLayeredPane.add(LinearItrLoopInc1TextField, JLayeredPane.DEFAULT_LAYER);
			LinearItrLoopInc1TextField.setBounds(280, 62, 20, 24);

			//---- LinearItrLoopFunctCommaLabel ----
			LinearItrLoopFunctComma1Label.setText(",");
			LinearItrLoopLayeredPane.add(LinearItrLoopFunctComma1Label, JLayeredPane.DEFAULT_LAYER);
			LinearItrLoopFunctComma1Label.setBounds(300, 62, 10, 24);

			//---- LinearItrLoopInc2TextField ----
			LinearItrLoopInc2TextField.setText("1");
			LinearItrLoopLayeredPane.add(LinearItrLoopInc2TextField, JLayeredPane.DEFAULT_LAYER);
			LinearItrLoopInc2TextField.setBounds(305, 62, 20, 24);

			//---- LinearItrLoopFunctCommaLabel ----
			LinearItrLoopFunctComma2Label.setText(",");
			LinearItrLoopLayeredPane.add(LinearItrLoopFunctComma2Label, JLayeredPane.DEFAULT_LAYER);
			LinearItrLoopFunctComma2Label.setBounds(326, 62, 10, 24);

			//---- LinearItrLoopRowsTextField ----
			LinearItrLoopRowsTextField.setText("5");
			LinearItrLoopLayeredPane.add(LinearItrLoopRowsTextField, JLayeredPane.DEFAULT_LAYER);
			LinearItrLoopRowsTextField.setBounds(330, 62, 20, 24);

			//---- LinearItrLoopFunctComma3Label ----
			LinearItrLoopFunctComma3Label.setText(",");
			LinearItrLoopLayeredPane.add(LinearItrLoopFunctComma3Label, JLayeredPane.DEFAULT_LAYER);
			LinearItrLoopFunctComma3Label.setBounds(350, 62, 10, 24);

			//---- LinearItrLoopDirectionSelector ----
			for (int i = 0; i < 4; i++){
				LinearItrLoopDirectionSelector.addItem(DirectionStrings[i]);}
			LinearItrLoopLayeredPane.add(LinearItrLoopDirectionSelector, JLayeredPane.DEFAULT_LAYER);
			LinearItrLoopDirectionSelector.setBounds(355, 62, 40, 24);

			//---- LinearItrLoopFunctNLabel ----
			LinearItrLoopFunctEndLabel.setText(" );");
			LinearItrLoopLayeredPane.add(LinearItrLoopFunctEndLabel, JLayeredPane.DEFAULT_LAYER);
			LinearItrLoopFunctEndLabel.setBounds(395, 62, 40, 24);

			//---- LinearItrLoopStatement1Selector ----
			for (int i = 0; i < 4; i++){
				LinearItrLoopStatement1Selector.addItem(TriangleItrLoopIncrementStrings[i]);}
			LinearItrLoopLayeredPane.add(LinearItrLoopStatement1Selector, JLayeredPane.DEFAULT_LAYER);
			LinearItrLoopStatement1Selector.setBounds(135, 90, 80, 24);

			//---- LinearItrLoopPlusOrMinus1Selector ----
			LinearItrLoopPlusOrMinus1Selector.addItem(TriangleItrLoopPlusOrMinusStrings[0]);
			LinearItrLoopPlusOrMinus1Selector.addItem(TriangleItrLoopPlusOrMinusStrings[1]);
			LinearItrLoopLayeredPane.add(LinearItrLoopPlusOrMinus1Selector, JLayeredPane.DEFAULT_LAYER);
			LinearItrLoopPlusOrMinus1Selector.setBounds(215, 90, 40, 24);

			//---- LinearItrLoopStatement1TextField ----
			LinearItrLoopStatement1TextField.setText("4");
			LinearItrLoopLayeredPane.add(LinearItrLoopStatement1TextField, JLayeredPane.DEFAULT_LAYER);
			LinearItrLoopStatement1TextField.setBounds(255, 90, 25, 24);

			//---- LinearItrLoopSemiLabel ----
			LinearItrLoopSemiLabel.setText(" ;");
			LinearItrLoopLayeredPane.add(LinearItrLoopSemiLabel, JLayeredPane.DEFAULT_LAYER);
			LinearItrLoopSemiLabel.setBounds(280, 90, 15, 24);

			//---- LinearItrLoopStatement2Selector ----
			for (int i = 0; i < 4; i++){
				LinearItrLoopStatement2Selector.addItem(TriangleItrLoopIncrementStrings[i]);}
			LinearItrLoopStatement2Selector.setSelectedIndex(3);
			LinearItrLoopLayeredPane.add(LinearItrLoopStatement2Selector, JLayeredPane.DEFAULT_LAYER);
			LinearItrLoopStatement2Selector.setBounds(135, 115, 80, 24);

			//---- LinearItrLoopPlusOrMinus2Selector ----
			LinearItrLoopPlusOrMinus2Selector.addItem(TriangleItrLoopPlusOrMinusStrings[0]);
			LinearItrLoopPlusOrMinus2Selector.addItem(TriangleItrLoopPlusOrMinusStrings[1]);
			LinearItrLoopLayeredPane.add(LinearItrLoopPlusOrMinus2Selector, JLayeredPane.DEFAULT_LAYER);
			LinearItrLoopPlusOrMinus2Selector.setBounds(215, 115, 40, 24);

			//---- LinearItrLoopStatement2TextField ----
			LinearItrLoopStatement2TextField.setText("0");
			LinearItrLoopLayeredPane.add(LinearItrLoopStatement2TextField, JLayeredPane.DEFAULT_LAYER);
			LinearItrLoopStatement2TextField.setBounds(255, 115, 25, 24);

			//---- LinearItrLoopSemi1Label ----
			LinearItrLoopSemi1Label.setText(" ;");
			LinearItrLoopLayeredPane.add(LinearItrLoopSemi1Label, JLayeredPane.DEFAULT_LAYER);
			LinearItrLoopSemi1Label.setBounds(280, 115, 15, 24);

			//---- LinearItrLoopEndBracketLabel ----
			LinearItrLoopEndBracketLabel.setText("End for loop");
			LinearItrLoopLayeredPane.add(LinearItrLoopEndBracketLabel, JLayeredPane.DEFAULT_LAYER);
			LinearItrLoopEndBracketLabel.setBounds(120, 140, 80, 24);
		}
		LinearIterationTabbedPane.addTab("Linear Iteration Loop", LinearItrLoopLayeredPane);


		//	======== TrigTabbedPane ========
		{

			//======== TrigLayeredPane ========
			{

				//---- GraphTrigFunctionButton ----
				GraphTrigFunctionButton.setText("Graph");
				TrigFunctionsLayeredPane.add(GraphTrigFunctionButton, JLayeredPane.DEFAULT_LAYER);
				GraphTrigFunctionButton.setBounds(5, 5, 80, 25);
				GraphTrigFunctionButton.addActionListener(this);

				//---- YEqualsLabel ----
				YEqualsLabel.setText("y = ");
				TrigFunctionsLayeredPane.add(YEqualsLabel, JLayeredPane.DEFAULT_LAYER);
				YEqualsLabel.setBounds(100, 5, 20, 25);

				//---- TrigMultiplierTextField ----
				TrigMultiplierTextField.setText("1");
				TrigFunctionsLayeredPane.add(TrigMultiplierTextField, JLayeredPane.DEFAULT_LAYER);
				TrigMultiplierTextField.setBounds(125, 5, 30, 25);

				//---- TrigFunctionSelector ----
				for(int i = 0; i < 6; i++)
					TrigFunctionSelector.addItem(TrigFunctionStrings[i]);
				TrigFunctionsLayeredPane.add(TrigFunctionSelector, JLayeredPane.DEFAULT_LAYER);
				TrigFunctionSelector.setBounds(160, 5, 75, 25);

				//---- TrigOpenParenthesisLabel ----
				TrigOpenParenthesisLabel.setText("(");
				TrigFunctionsLayeredPane.add(TrigOpenParenthesisLabel, JLayeredPane.DEFAULT_LAYER);
				TrigOpenParenthesisLabel.setBounds(240, 5, 10, 25);

				//---- TrigFunctionArgumentTextField ----
				TrigFunctionArgumentTextField.setText("1");
				TrigFunctionsLayeredPane.add(TrigFunctionArgumentTextField, JLayeredPane.DEFAULT_LAYER);
				TrigFunctionArgumentTextField.setBounds(255, 5, 30, 25);

				//---- TrigCloseParenthesisLabel ----
				TrigCloseParenthesisLabel.setText("x)");
				TrigFunctionsLayeredPane.add(TrigCloseParenthesisLabel, JLayeredPane.DEFAULT_LAYER);
				TrigCloseParenthesisLabel.setBounds(290, 5, 15, 25);

				//---- TrigPlusOrMinusSelector ----
				TrigPlusOrMinusSelector.addItem(TrigPlusOrMinusStrings[0]);
				TrigPlusOrMinusSelector.addItem(TrigPlusOrMinusStrings[1]);
				TrigFunctionsLayeredPane.add(TrigPlusOrMinusSelector, JLayeredPane.DEFAULT_LAYER);
				TrigPlusOrMinusSelector.setBounds(310, 5, 40, 25);

				//---- TrigAdditiveTextField ----
				TrigAdditiveTextField.setText("0");
				TrigFunctionsLayeredPane.add(TrigAdditiveTextField, JLayeredPane.DEFAULT_LAYER);
				TrigAdditiveTextField.setBounds(355, 5, 30, 25);
			}
			TrigFunctionsTabbedPane.addTab("Trigonometric Functions", TrigFunctionsLayeredPane);
		}
		BeadLoomUtilitiesTabbedPane.addTab("Trig Functions", TrigFunctionsTabbedPane);


		//  ======== TriangleItrLoopTabbedPane ========
		{

			//======== TriangleItrLoopLayeredPane ========
			{
				//---- DrawTriangleItrLoopButton ----
				DrawTriangleItrLoopButton.setText("Draw");
				TriangleItrLoopLayeredPane.add(DrawTriangleItrLoopButton, JLayeredPane.DEFAULT_LAYER);
				DrawTriangleItrLoopButton.setBounds(5, 5, 80, 25);
				DrawTriangleItrLoopButton.addActionListener(this);

				//---- TriangleItrLoopItrHelpLabel ----
				TriangleItrLoopItrHelpLabel.setText(" Help with...");
				TriangleItrLoopLayeredPane.add(TriangleItrLoopItrHelpLabel, JLayeredPane.DEFAULT_LAYER);
				TriangleItrLoopItrHelpLabel.setBounds(5, 50, 90, 24);

				//---- HelpTriangleItrLoopButton ----
				TriangleItrLoopItrHelpButton.setText("<html>Triangle<br>Iteration");
				TriangleItrLoopLayeredPane.add(TriangleItrLoopItrHelpButton, JLayeredPane.DEFAULT_LAYER);
				TriangleItrLoopItrHelpButton.setBounds(5, 75, 100,40);
				TriangleItrLoopItrHelpButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						TriangleIterHelpButtonActionPerformed(e);
					}
				});

				//---- HelpTriangleItrLoopButton ----
				TriangleItrLoopHelpButton.setText("<html>Iteration<br>Loop");
				TriangleItrLoopLayeredPane.add(TriangleItrLoopHelpButton, JLayeredPane.DEFAULT_LAYER);
				TriangleItrLoopHelpButton.setBounds(5, 122, 100, 40);
				TriangleItrLoopHelpButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						TriangleCycleHelpButtonActionPerformed(e);
					}
				});

				//---- TriangleItrLoopImage ----
				/*TriangleItrLoopImage.setBounds(6, 42, 125, 125);
				TriangleItrLoopLayeredPane.add(TriangleItrLoopImage, JLayeredPane.DEFAULT_LAYER);
				repaint();*/

				//---- TriangleItrLoopStartXLabel ----
				TriangleItrLoopStartXLabel.setText("X = ");
				TriangleItrLoopLayeredPane.add(TriangleItrLoopStartXLabel, JLayeredPane.DEFAULT_LAYER);
				TriangleItrLoopStartXLabel.setBounds(120, 5, 35, 24);

				//---- TriangleItrLoopStartXTextField ----
				TriangleItrLoopStartXTextField.setText("0");
				TriangleItrLoopLayeredPane.add(TriangleItrLoopStartXTextField, JLayeredPane.DEFAULT_LAYER);
				TriangleItrLoopStartXTextField.setBounds(140, 5, 25, 24);

				//---- TriangleItrLoopStartYLabel ----
				TriangleItrLoopStartYLabel.setText(";    Y = ");
				TriangleItrLoopLayeredPane.add(TriangleItrLoopStartYLabel, JLayeredPane.DEFAULT_LAYER);
				TriangleItrLoopStartYLabel.setBounds(167, 5, 55, 24);

				//---- TriangleItrLoopStartYTextField ----
				TriangleItrLoopStartYTextField.setText("10");
				TriangleItrLoopLayeredPane.add(TriangleItrLoopStartYTextField, JLayeredPane.DEFAULT_LAYER);
				TriangleItrLoopStartYTextField.setBounds(203, 5, 25, 24);

				//---- TriangleItrLoopEndYLabel ----
				TriangleItrLoopEndYLabel.setText(" ;");
				TriangleItrLoopLayeredPane.add(TriangleItrLoopEndYLabel, JLayeredPane.DEFAULT_LAYER);
				TriangleItrLoopEndYLabel.setBounds(227, 5, 10, 24);

				//---- TriangleItrLoopStartNLabel ----
				TriangleItrLoopStartNLabel.setText("For  (   N = ");
				TriangleItrLoopLayeredPane.add(TriangleItrLoopStartNLabel, JLayeredPane.DEFAULT_LAYER);
				TriangleItrLoopStartNLabel.setBounds(120, 35, 70, 24);

				//---- TriangleItrLoopStartNTextField ----
				TriangleItrLoopStartNTextField.setText("1");
				TriangleItrLoopLayeredPane.add(TriangleItrLoopStartNTextField, JLayeredPane.DEFAULT_LAYER);
				TriangleItrLoopStartNTextField.setBounds(180, 35, 25, 24);

				//---- TriangleItrLoopToNLabel ----
				TriangleItrLoopToNLabel.setText(" to ");
				TriangleItrLoopLayeredPane.add(TriangleItrLoopToNLabel, JLayeredPane.DEFAULT_LAYER);
				TriangleItrLoopToNLabel.setBounds(205, 35, 25, 24);

				//---- TriangleItrLoopEndNTextField ----
				TriangleItrLoopEndNTextField.setText("4");
				TriangleItrLoopLayeredPane.add(TriangleItrLoopEndNTextField, JLayeredPane.DEFAULT_LAYER);
				TriangleItrLoopEndNTextField.setBounds(225, 35, 25, 24);

				//---- TriangleItrLoopEndNLabel ----
				TriangleItrLoopEndNLabel.setText(" )");
				TriangleItrLoopLayeredPane.add(TriangleItrLoopEndNLabel, JLayeredPane.DEFAULT_LAYER);
				TriangleItrLoopEndNLabel.setBounds(255, 35, 15, 24);

				//---- TriangleItrLoopFunctStartLabel ----
				TriangleItrLoopFunctStartLabel.setText("triangularIteration(X,Y,");
				TriangleItrLoopLayeredPane.add(TriangleItrLoopFunctStartLabel, JLayeredPane.DEFAULT_LAYER);
				TriangleItrLoopFunctStartLabel.setBounds(134, 62, 140, 24);

				//---- TriangleItrLoopStepSelector ----
				for (int i = 0; i < 7; i++){
					TriangleItrLoopStepSelector.addItem(SelectionStrings[i]);}
				TriangleItrLoopLayeredPane.add(TriangleItrLoopStepSelector, JLayeredPane.DEFAULT_LAYER);
				TriangleItrLoopStepSelector.setSelectedIndex(0);
				TriangleItrLoopStepSelector.setBounds(260, 62, 35, 24);

				//---- TriangleItrLoopFunctNLabel ----
				TriangleItrLoopFunctNLabel.setText(",");
				TriangleItrLoopLayeredPane.add(TriangleItrLoopFunctNLabel, JLayeredPane.DEFAULT_LAYER);
				TriangleItrLoopFunctNLabel.setBounds(295, 62, 5, 24);

				//---- TriangleItrLoopAddedSelector ----
				for (int i = 0; i < 7; i++){
					TriangleItrLoopNSelector.addItem(SelectionStrings[i]);}
				TriangleItrLoopLayeredPane.add(TriangleItrLoopNSelector, JLayeredPane.DEFAULT_LAYER);
				TriangleItrLoopNSelector.setSelectedIndex(0);
				TriangleItrLoopNSelector.setBounds(299, 62, 35, 24);

				//---- TriangleItrLoopFunctNLabel ----
				TriangleItrLoopFunctN2Label.setText(",");
				TriangleItrLoopLayeredPane.add(TriangleItrLoopFunctN2Label, JLayeredPane.DEFAULT_LAYER);
				TriangleItrLoopFunctN2Label.setBounds(334, 62, 5, 24);

				//---- TriangleItrLoopRowsTextField ----
				TriangleItrLoopRowsTextField.setText("6");
				TriangleItrLoopLayeredPane.add(TriangleItrLoopRowsTextField, JLayeredPane.DEFAULT_LAYER);
				TriangleItrLoopRowsTextField.setBounds(338, 62, 20, 24);

				//---- LinearItrLoopFunctComma3Label ----
				TriangleItrLoopFunctCommaLabel.setText(",");
				TriangleItrLoopLayeredPane.add(TriangleItrLoopFunctCommaLabel, JLayeredPane.DEFAULT_LAYER);
				TriangleItrLoopFunctCommaLabel.setBounds(359, 62, 10, 24);

				//---- TriangleItrLoopDirectionSelector ----
				for (int i = 0; i < 4; i++){
					TriangleItrLoopDirectionSelector.addItem(DirectionStrings[i]);}
				TriangleItrLoopLayeredPane.add(TriangleItrLoopDirectionSelector, JLayeredPane.DEFAULT_LAYER);
				TriangleItrLoopDirectionSelector.setSelectedIndex(0);
				TriangleItrLoopDirectionSelector.setBounds(362, 62, 39, 24);

				//---- TriangleItrLoopFunctNLabel ----
				TriangleItrLoopFunctEndLabel.setText(" );");
				TriangleItrLoopLayeredPane.add(TriangleItrLoopFunctEndLabel, JLayeredPane.DEFAULT_LAYER);
				TriangleItrLoopFunctEndLabel.setBounds(400, 62, 40, 24);

				//---- TriangleItrLoopStatement1Selector ----
				for (int i = 0; i < 4; i++){
					TriangleItrLoopStatement1Selector.addItem(TriangleItrLoopIncrementStrings[i]);}
				TriangleItrLoopLayeredPane.add(TriangleItrLoopStatement1Selector, JLayeredPane.DEFAULT_LAYER);
				TriangleItrLoopStatement1Selector.setBounds(135, 90, 80, 24);

				//---- TriangleItrLoopPlusOrMinus1Selector ----
				TriangleItrLoopPlusOrMinus1Selector.addItem(TriangleItrLoopPlusOrMinusStrings[0]);
				TriangleItrLoopPlusOrMinus1Selector.addItem(TriangleItrLoopPlusOrMinusStrings[1]);
				TriangleItrLoopPlusOrMinus1Selector.setSelectedIndex(1);
				TriangleItrLoopLayeredPane.add(TriangleItrLoopPlusOrMinus1Selector, JLayeredPane.DEFAULT_LAYER);
				TriangleItrLoopPlusOrMinus1Selector.setBounds(215, 90, 40, 24);

				//---- TriangleItrLoopStatement1TextField ----
				TriangleItrLoopStatement1TextField.setText("8");
				TriangleItrLoopLayeredPane.add(TriangleItrLoopStatement1TextField, JLayeredPane.DEFAULT_LAYER);
				TriangleItrLoopStatement1TextField.setBounds(255, 90, 25, 24);

				//---- TriangleItrLoopSemiLabel ----
				TriangleItrLoopSemiLabel.setText(" ;");
				TriangleItrLoopLayeredPane.add(TriangleItrLoopSemiLabel, JLayeredPane.DEFAULT_LAYER);
				TriangleItrLoopSemiLabel.setBounds(280, 90, 15, 24);

				//---- TriangleItrLoopStatement2Selector ----
				for (int i = 0; i < 4; i++){
					TriangleItrLoopStatement2Selector.addItem(TriangleItrLoopIncrementStrings[i]);}
				TriangleItrLoopStatement2Selector.setSelectedIndex(3);
				TriangleItrLoopLayeredPane.add(TriangleItrLoopStatement2Selector, JLayeredPane.DEFAULT_LAYER);
				TriangleItrLoopStatement2Selector.setBounds(135, 115, 80, 24);

				//---- TriangleItrLoopPlusOrMinus2Selector ----
				TriangleItrLoopPlusOrMinus2Selector.addItem(TriangleItrLoopPlusOrMinusStrings[0]);
				TriangleItrLoopPlusOrMinus2Selector.addItem(TriangleItrLoopPlusOrMinusStrings[1]);
				TriangleItrLoopLayeredPane.add(TriangleItrLoopPlusOrMinus2Selector, JLayeredPane.DEFAULT_LAYER);
				TriangleItrLoopPlusOrMinus2Selector.setBounds(215, 115, 40, 24);

				//---- TriangleItrLoopStatement2TextField ----
				TriangleItrLoopStatement2TextField.setText("0");
				TriangleItrLoopLayeredPane.add(TriangleItrLoopStatement2TextField, JLayeredPane.DEFAULT_LAYER);
				TriangleItrLoopStatement2TextField.setBounds(255, 115, 25, 24);

				//---- TriangleItrLoopSemi1Label ----
				TriangleItrLoopSemi1Label.setText(" ;");
				TriangleItrLoopLayeredPane.add(TriangleItrLoopSemi1Label, JLayeredPane.DEFAULT_LAYER);
				TriangleItrLoopSemi1Label.setBounds(280, 115, 15, 24);

				//---- TriangleItrLoopEndBracketLabel ----
				TriangleItrLoopEndBracketLabel.setText("End for loop");
				TriangleItrLoopLayeredPane.add(TriangleItrLoopEndBracketLabel, JLayeredPane.DEFAULT_LAYER);
				TriangleItrLoopEndBracketLabel.setBounds(120, 140, 80, 24);
			}
			LinearIterationTabbedPane.addTab("Triangle Iteration Loop", TriangleItrLoopLayeredPane);
		}
		BeadLoomUtilitiesTabbedPane.addTab("Iterative Functions", LinearIterationTabbedPane);

		//Make the bead so it is stored in memory
		redrawBead();
		
		
	}





	///////////////////////////////////////////////////////////////////////////////////////
	//============================== Main drawing functions =============================// 
	///////////////////////////////////////////////////////////////////////////////////////




	public void setGrid(GridPanel gp)
	{
		grid = gp;
	}

	//Draw a single point
	public void drawPoint(int x, int y)
	{	
		//Initialize the coordinate arrays
		ArrayList<Integer> xValue = new ArrayList(1);
		ArrayList<Integer> yValue = new ArrayList(1);

		//Add x and y coordinates
		xValue.add(x);
		yValue.add(y);

		//Draw the bead
		CoordList coords = new CoordList(xValue, yValue);
		Layer l = new Layer("POINT", coords," ");
		l.setColor(color);
		l.setImage(makeBullet(l.getColor(), grid.getWidth(), grid.getHeight()));

		//write coordinate and type data
		l.setX1(x);
		l.setY1(y);
		grid.addLayer(l);		
	}

	//Draw a line consisting of multiple beads
	public void drawLine(int x1, int y1, int x2, int y2, ArrayList<Integer> xValue, ArrayList<Integer> yValue) 
	{	
		if(x1 == x2 && y1 == y2) // single point
		{ 
			//Add x and y coordinates to passed arrays
			xValue.add(x1);
			yValue.add(y1);
		}
		else if(x1 == x2) // vertical line (undefined slope)
		{
			//If coordinate one is bigger, swap
			if(y2 < y1) {
				int temp = y1;
				y1 = y2;
				y2 = temp;
			}
			//Add all x and y coordinates
			for(int i = y1; i <= y2; i++) {
				xValue.add(x1);
				yValue.add(i);
			}
		}
		else if(y1 == y2) // horizontal line (slope == 0)
		{
			//If coordinate one is bigger, swap
			if(x2 < x1) {
				int temp = x1;
				x1 = x2;
				x2 = temp;
			}
			//Add all x an y coordinates
			for(int i = x1; i <= x2; i++) {
				xValue.add(i);
				yValue.add(y1);
			}
		}
		//Find the slope of the line and add coordinates
		else {
			double m = (y2 - y1) / (double)(x2 - x1); //slope
			double b = y1 - m * x1;
			if(Math.abs(m) <= 1) {
				int startX = (x1 < x2) ? x1 : x2;
				int endX = (x1 < x2) ? x2 : x1;
				int intY;
				double doubleY;
				for(int i = startX; i <= endX; i++) {
					xValue.add(i);
					doubleY = m * i + b;
					intY = (int)doubleY;
					if(Math.abs(doubleY - intY) >= 0.5)
						intY = (doubleY >= 0) ? intY + 1 : intY - 1;
					yValue.add(intY);
				}
			}
			else {
				int startY = (y1 < y2) ? y1 : y2;
				int endY = (y1 < y2) ? y2 : y1;
				int intX;
				double doubleX;
				for(int i = startY; i <= endY; i++) {
					yValue.add(i);
					doubleX = (i - b) / (double)m;
					intX = (int)doubleX;
					if(Math.abs(doubleX - intX) >= 0.5)
						intX = (doubleX >= 0) ? intX + 1 : intX - 1;
					xValue.add(intX);
				}
			}
		}
	}

	//Draw a solid rectangle
	public void drawRectangle(int x1, int x2, int y1, int y2)
	{
		//Initialize the coordinate arrays
		ArrayList<Integer> xValue = new ArrayList();
		ArrayList<Integer> yValue = new ArrayList();

		//If the 1st x coordinate is bigger, swap
		if(x1 > x2) {
			int temp = x1;
			x1 = x2;
			x2 = temp;
		}
		//If the 1st y coordinate is bigger, swap
		if(y1 > y2) {
			int temp = y1;
			y1 = y2;
			y2 = temp;
		}
		//Add coordinates to arrays
		for(int y = y1; y <= y2; y++)
			for(int x = x1; x <= x2; x++) {
				xValue.add(x);
				yValue.add(y);
			}
		//Draw rectangle
		CoordList coords = new CoordList(xValue, yValue);
		Layer l = new Layer("RECTANGLE", coords," ");
		l.setColor(color);
		l.setImage(makeBullet(l.getColor(), grid.getWidth(), grid.getHeight()));
		l.setX1(x1);
		l.setY1(y1);
		l.setX2(x2);
		l.setY2(y2);
		grid.addLayer(l);
	}

	//Draw a solid triangle, relies on the drawLine method
	public void drawTriangle(int x1, int x2, int x3, int y1, int y2, int y3)
	{
		//Initialize the coordinate arrays
		ArrayList<Integer> xValue = new ArrayList();
		ArrayList<Integer> yValue = new ArrayList();

		//Draw the three sides of the triangle
		drawLine(x1, y1, x2, y2, xValue, yValue);
		drawLine(x2, y2, x3, y3, xValue, yValue);
		drawLine(x1, y1, x3, y3, xValue, yValue);

		//Find the fill start point
		int start = x1;
		if(x2 < start) start = x2;
		if(x3 < start) start = x3;

		//Find the fill end point
		int stop = x1;
		if(x2 > stop) stop = x2;
		if(x3 > stop) stop = x3;

		//Fill the triangle
		CoordList coords = new CoordList(xValue, yValue);
		coords.sortByX(start, stop);
		coords.sortByY();
		coords.fillTriangle();

		//Draw the triangle
		Layer l = new Layer("TRIANGLE", coords," ");
		l.setColor(color);
		l.setImage(makeBullet(l.getColor(), grid.getWidth(), grid.getHeight()));
		l.setX1(x1);
		l.setY1(y1);
		l.setX2(x2);
		l.setY2(y2);
		l.setX3(x3);
		l.setY3(y3);
		grid.addLayer(l);
	}

	//Draw a linear sequence of iterative beads
	public void linearIteration(int startX, int startY, int startLength, int inc1, 
			int inc2, int rows, boolean incY, boolean posDir, ArrayList<Integer> xValue, ArrayList<Integer> yValue )
	{
		int newX, newY;

		//If the iteration is in the vertical direction...
		if(incY == true)
		{
			newX = startX + startLength - 1;
			for(int i = 0; i < rows; i++)
			{
				//If the iteration is in the positive direction...
				if(newX < startX)
					for(int j = newX; j <= startX; j++)
					{
						//If arguments are out of bound skip this loop
						if(j < -50 || j > 50 || startY < -50 || startY > 50)
							continue;
						//Add x and y coordinates 
						xValue.add(j);
						yValue.add(startY);
					}
				//The iteration is in the negative direction
				else
					for(int j = startX; j <= newX; j++)
					{
						//If arguments are out of bound skip this loop
						if(j < -50 || j > 50 || startY < -50 || startY > 50)
							continue;
						//Add x and y coordinates 
						xValue.add(j);
						yValue.add(startY);
					}
				//If iteration is in the positive direction increment else decrement
				if(posDir == true)
					startY++;
				else
					startY--;
				startX -= inc1;
				newX += inc2;
			}
		}
		//The iteration is in the horizontal direction
		else
		{
			newY = startY - startLength + 1;
			for(int i = 0; i < rows; i++)
			{
				//If the iteration is in the positive direction...
				if(newY < startY)
					for(int j = newY; j <= startY; j++)
					{
						//If arguments are out of bound skip this loop
						if(startX > 50 || startX < -50 || j > 50 || j < -50)
							continue;
						//Add x and y coordinates 
						xValue.add(startX);
						yValue.add(j);
					}
				//The iteration is in the negative direction
				else
					for(int j = startY; j <= newY; j++)
					{
						//If arguments are out of bound skip this loop
						if(startX < -50 || startX > 50 || j < -50 || j > 50)
							continue;
						//Add x and y coordinates 
						xValue.add(startX);
						yValue.add(j);
					}
				//If iteration is in the positive direction increment else decrement
				if(posDir == true)
					startX++;
				else
					startX--;
				startY += inc1;
				newY -= inc2;
			}
		}
	}

	public void LinearIterationLoop(int startX, int startY, int nStart, int nEnd, int inc1, int inc2, int rows, boolean incY, boolean posDir)
	{
		//Initialize the coordinate arrays
		ArrayList<Integer> xValue = new ArrayList();
		ArrayList<Integer> yValue = new ArrayList();
		int startLength = 1;
		//Assume loop is increments in the positive direction
		boolean posLoop = true;

		//If the start value is greater than the end value decrement loop
		if (nStart > nEnd)
		{
			//Negative loop direction
			posLoop = false;
			//Account for extra cycle
			nEnd--;
		}
		//The start value is less than the end value
		else
			//Account for extra cycle
			nEnd++;

		//Do-while loop in order to get at least one iteration
		do 
		{	
			startLength = LinearItrLoopStartLengthSelector.getSelectedIndex(); //Set the startLength
			if(startLength == 0) //If N is not selected
				startLength = nStart; //Set startLength to N

			try
			{
				//If arguments are not in bounds throw an exception
				if(startX < -50 || startX > 50 || startY < -50 || startY > 50 || nStart < -50 || nStart > 50 ||
						nEnd > 50 || nEnd < -50 || inc1 > 50 || inc1 < -50 || inc2 > 50 || inc2 < -50 )
					throw new NumberFormatException();
			}
			catch(Exception exc)
			{
				//Display argument error to user
				JOptionPane.showMessageDialog(null, "Values must be integers between -50 and 50");
			}

			//Pass arguments to the triangleIteration method to draw a single triangle 
			linearIteration(startX, startY, startLength, inc1, inc2, rows, incY, posDir, xValue, yValue);

			//Before the loop ends parse the statements and use the 
			//StatementCreator functions to act appropriately...
			switch(LinearItrLoopStatement1Selector.getSelectedIndex()) {
			case 0:
				startY = LinearItrLoopStatement1(startY);
				break;
			case 1:
				startY = LinearItrLoopStatement1(startX);
				break;
			case 2:
				startX = LinearItrLoopStatement1(startY);
				break;
			case 3:
				startX = LinearItrLoopStatement1(startX);
				break;
			}

			switch(LinearItrLoopStatement2Selector.getSelectedIndex()) {
			case 0:
				startY = LinearItrLoopStatement2(startY);
				break;
			case 1:
				startY = LinearItrLoopStatement2(startX);
				break;
			case 2:
				startX = LinearItrLoopStatement2(startY);
				break;
			case 3:
				startX = LinearItrLoopStatement2(startX);
				break;
			}

			//If the loop is in the positive direction increment
			if (posLoop == true)
				nStart++;
			//Else decrement
			else 
				nStart--;
			//Continue if the start does not equal the end
		} while (nStart != nEnd);

		//Draw the iteration
		CoordList coords = new CoordList(xValue, yValue);
		Layer l = new Layer("LINEAR_ITERATION_CYCLES", coords," ");
		l.setColor(color);
		l.setImage(makeBullet(l.getColor(), grid.getWidth(), grid.getHeight()));
		grid.addLayer(l);
	}

	//Draw a sequence of iterative triangles
	public void triangleIteration(int startX, int startY, int steps, int exSteps, int width, double cycles, 
			boolean incY, boolean fill, ArrayList<Integer> xValue, ArrayList<Integer> yValue)
	{	
		int tempSteps = 0;
		boolean lastLoop = false;

		//Loop through the cycles (Steps)
		for(int i = 0; i < cycles; i++)
		{
			//Loop through the Step height
			for(int j = 0; j <= tempSteps; j++)
			{
				//If incrementing in the y direction...
				if(!incY)
				{
					//Loop through the individual beads
					for(int k = 0; k < Math.abs(width); k++)
					{
						//Usual bead logic, check bounds and add bead...
						if(startX + k > 50 || startX + k < -50)
							break;
						else if(startY + j > 50 || startY + j < -50)
							continue;
						if(width < 0)
							xValue.add(startX - k);
						else
							xValue.add(startX + k);
						yValue.add(startY + j);
						if(j != 0)
						{
							if(startY - j < -50 || startY - j > 50)
								continue;
							if(width < 0)
								xValue.add(startX - k);
							else
								xValue.add(startX + k);
							yValue.add(startY - j);
						}
						if(!fill)
							break;
					}
				}
				//Incrementing in the x direction
				else
				{
					for(int k = 0; k < Math.abs(width); k++)
					{
						//Usual bead logic, check bounds and add bead...
						if((width < 0 && (startY - k < -50 || startY - k > 50)) ||
								(width >= 0 && (startY + k > 50 || startY + k < -50)))
							break;
						if(startX + j < -50 || startX + j > 50)
							continue;
						xValue.add(startX + j);
						if(width < 0)
							yValue.add(startY - k);
						else
							yValue.add(startY + k);
						if(j != 0 )
						{
							if(startX - j < -50 || startX - j > 50)
								continue;
							xValue.add(startX - j);
							if(width < 0)
								yValue.add(startY - k);
							else
								yValue.add(startY + k);
						}
						if(!fill)
							break;
					}
				}
			}

			//Increment appropriate fields
			tempSteps += steps;
			if(incY)
				startY += width;
			else
				startX += width;
			if (((int)cycles-i) <= 1) //Account for unfilled steps
			{
				if(width < 0)
				{
					exSteps = exSteps*-1;
					width = exSteps;
				}
				else
				{
					width = exSteps;
				}
			}
			if(lastLoop)
				break;
			/*if(i == (int)cycles - 1 && cycles > (int)cycles)
				{
					int temp = Math.abs(Integer.parseInt(TriangleIterationRowsTotalTextField.getText())) % Math.abs(width);
					width = (width >= 0) ? temp : temp * -1;
					lastLoop = true;
					i--;
				}*/
		}
	}

	//Draw a sequence of user defined iterative triangles, relies of triangleIteration method
	public void TriangleIterationLoop(int startX, int startY, int nStart, int nEnd, int exSteps, double cycles, boolean incY)
	{
		//Assume loop is increments in the positive direction
		boolean posLoop = true;
		double tempCycle = 0;
		int tempN = 0, tempStep = 0;
		//Initialize the coordinate arrays
		ArrayList<Integer> xValue = new ArrayList();
		ArrayList<Integer> yValue = new ArrayList();

		//If the start value is greater than the end value decrement loop
		if (nStart > nEnd)
		{
			//Negative loop direction
			posLoop = false;
			//Account for extra cycle
			nEnd--;
		}
		//The start value is less than the end value
		else
			nEnd++; //Account for extra cycle

		//Do-while loop in order to get at least one iteration
		do 
		{	
			tempCycle = cycles; //Set the temporary cycle
			tempStep = TriangleItrLoopStepSelector.getSelectedIndex(); //Set the temporary step
			if(tempStep == 0) //If N is not selected
				tempStep = nStart; //Set tempStep to N

			try
			{
				//If arguments are not in bounds throw an exception
				if(startX < -50 || startX > 50 || startY < -50 || startY > 50 || nStart < -50
						|| nStart > 50 || nEnd > 50 || nEnd < -50 || cycles > 50 )
					throw new NumberFormatException();

				//If stepHeight > totalRows only draw the total number of rows
				if ((tempCycle/(double)tempStep) < 1)
				{
					tempStep = (int)tempCycle;
					tempCycle= 1; 
				}
				else //Check to make sure extra rows are accounted for
				{
					exSteps = (int)tempCycle % tempStep;
					tempCycle = tempCycle/(double)tempStep;
				}

				//Set incY to false if horizontal iteration is selected
				incY = !(TriangleItrLoopDirectionSelector.getSelectedIndex() == 2 || TriangleItrLoopDirectionSelector.getSelectedIndex() == 3);
				if(TriangleItrLoopDirectionSelector.getSelectedIndex() == 3 || TriangleItrLoopDirectionSelector.getSelectedIndex() == 1)
					tempStep *= -1;
			}
			catch(Exception exc)
			{
				//Display argument error to user
				JOptionPane.showMessageDialog(null, "Values must be integers between -50 and 50");
			}

			//Pass arguments to the triangleIteration method to draw a single triangle
			tempN = TriangleItrLoopNSelector.getSelectedIndex();
			if(tempN == 0) //N is not selected
				tempN = nStart;

			triangleIteration(startX, startY, tempN, exSteps, tempStep, tempCycle, incY, true, xValue, yValue);

			//Before the loop ends parse the statements and use the 
			//StatementCreator functions to act appropriately...
			switch(TriangleItrLoopStatement1Selector.getSelectedIndex()) {
			case 0:
				startY = TriangleItrLoopStatement1(startY);
				break;
			case 1:
				startY = TriangleItrLoopStatement1(startX);
				break;
			case 2:
				startX = TriangleItrLoopStatement1(startY);
				break;
			case 3:
				startX = TriangleItrLoopStatement1(startX);
				break;
			}

			switch(TriangleItrLoopStatement2Selector.getSelectedIndex()) {
			case 0:
				startY = TriangleItrLoopStatement2(startY);
				break;
			case 1:
				startY = TriangleItrLoopStatement2(startX);
				break;
			case 2:
				startX = TriangleItrLoopStatement2(startY);
				break;
			case 3:
				startX = TriangleItrLoopStatement2(startX);
				break;
			}

			//If the loop is in the positive direction increment
			if (posLoop == true)
				nStart++;
			//Else decrement
			else 
				nStart--;
			//Continue if the start does not equal the end
		} 
		while (nStart != nEnd);

		//Draw the iteration
		CoordList coords = new CoordList(xValue, yValue);
		Layer l = new Layer("TRIANGLE_ITERATION_CYCLES", coords," ");
		l.setColor(color);
		l.setImage(makeBullet(l.getColor(), grid.getWidth(), grid.getHeight()));
		grid.addLayer(l);
	}

	//Draw specified trigonometric function
	public void trigFunction (int additive, float multiplier, float argument)
	{
		//Initialize the coordinate arrays
		ArrayList<Integer> xValue = new ArrayList(101);
		ArrayList<Integer> yValue = new ArrayList(101);

		double functionValue = 0, tempSum = 0, yGraph = 0;
		//Loop through as long as x is in bounds
		for(int x = -50; x < 51; x++)
		{
			//Get the index and choose the trig function appropriately 
			switch(TrigFunctionSelector.getSelectedIndex()) 
			{
			case 0:
				functionValue = Math.sin(argument * x);
				break;
			case 1:
				functionValue = Math.cos(argument * x);
				break;
			case 2:
				functionValue = Math.tan(argument * x);
				break;
			case 3:
				functionValue = Math.asin(argument * x);
				break;
			case 4:
				functionValue = Math.acos(argument * x);
				break;
			case 5:
				functionValue = Math.atan(argument * x);
				break;
			}
			//If the result is a number (not an imaginary number)...
			if(!Double.isNaN(functionValue)) 
			{
				tempSum = functionValue * multiplier;
				//If adding the results...
				if(TrigPlusOrMinusSelector.getSelectedIndex() == 0)
					yGraph = tempSum + additive;
				//Else subtracting results
				else
					yGraph = tempSum - additive;
				//Add x and y coordinates
				xValue.add(x);
				yValue.add((int)(Math.round(yGraph)));
			}
		}

		final int size = xValue.size() - 1;
		int increment = 0;

		//When loop ends find the y values for coordinates
		for(int i = 0; i < size; i++) 
		{
			if(yValue.get(i + 1) - yValue.get(i) > 1) {
				increment = 0;
				for(int j = yValue.get(i); j < yValue.get(i + 1) - 1; j++) {
					xValue.add(xValue.get(i));
					yValue.add(yValue.get(i) + ++increment);
				}
			}
			else if(yValue.get(i) - yValue.get(i + 1) > 1 && TrigFunctionSelector.getSelectedIndex() != 2) 
			{
				increment = 0;
				for(int j = yValue.get(i + 1); j < yValue.get(i) - 1; j++) {
					xValue.add(xValue.get(i + 1));
					yValue.add(yValue.get(i) - ++increment);
				}
			}
			if(yValue.get(i + 1) < yValue.get(i) && TrigFunctionSelector.getSelectedIndex() == 2) 
			{
				increment = 0;
				for(int j = yValue.get(i) + 1; j < 50; j++) {
					xValue.add(xValue.get(i));
					yValue.add(yValue.get(i) + ++increment);
				}
				for(int j = -50; j < yValue.get(i + 1); j++) {
					xValue.add(xValue.get(i + 1));
					yValue.add(j);
				}
			}
		}
		//Draw the trigFunction
		CoordList coords = new CoordList(xValue, yValue);
		Layer l = new Layer("TRIG_FUNCTION", coords," ");
		l.setColor(color);
		l.setImage(makeBullet(l.getColor(), grid.getWidth(), grid.getHeight()));
		grid.addLayer(l);
	}

	public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			lay=(Layer)e.getItem();
		}
	}

	//Initializes the coordinate List for draw Line button
	//Draws the Iteration for Linear and Triangle Iteration
	//This is done so draw line, linear iteration, and triangle iteration can be called by the game
	//type is either LINE, LINEAR_ITERATION, or TRIANGLE_ITERATION
	public Layer CoordListAction(String type, ArrayList<Integer> xValue, ArrayList<Integer> yValue){

		CoordList coords = new CoordList(xValue, yValue);
		Layer l = new Layer(type, coords, " ");
		l.setColor(color);
		l.setImage(makeBullet(l.getColor(), grid.getWidth(), grid.getHeight()));
		return l;
	}

	public void addGridLayer(Layer l) {
		grid.addLayer(l);
	}




	///////////////////////////////////////////////////////////////////////////////////////
	//=============================== Action event functions ============================//
	///////////////////////////////////////////////////////////////////////////////////////




	public void actionPerformed(ActionEvent e) {
		//Fires when delete button is pressed
		if (e.getSource() == DeleteLayer) 
		{
			grid.deleteLayer(lay);
		}

		//Fires when the draw point button is pressed
		//Utilizes the drawPoint method
		if(e.getSource() == DrawPointButton) 
		{
			int x = 0;
			int y = 0;

			try 
			{
				x = Integer.parseInt((DrawPointXTextField.getText()));
				y = Integer.parseInt((DrawPointYTextField.getText()));

				//If the arguments are not in bounds throw an exception
				if(x > GRID_SIZE/2 || x < -1*GRID_SIZE/2 || y > GRID_SIZE/2 || y < -1*GRID_SIZE/2)
					throw new NumberFormatException();
				else{
					drawPoint(x, y);
				}

			} catch(Exception exc){JOptionPane.showMessageDialog(null, "Values must be integers between " + -1*GRID_SIZE/2 + " and " + GRID_SIZE/2);}
		}

		//Fires when the draw line button is pressed
		//Utilizes the drawLine method
		else if(e.getSource() == DrawLineButton) 
		{
			int x1 = 0, x2 = 1;
			int y1 = 0, y2 = 1;

			//Initialize the coordinate arrays
			ArrayList<Integer> xValue = new ArrayList();
			ArrayList<Integer> yValue = new ArrayList();

			try {
				x1 = Integer.parseInt(DrawLineX1TextField.getText());
				x2 = Integer.parseInt(DrawLineX2TextField.getText());
				y1 = Integer.parseInt(DrawLineY1TextField.getText());
				y2 = Integer.parseInt(DrawLineY2TextField.getText());

				//If the arguments are not in bounds throw an exception
				if(x1 < -1*GRID_SIZE/2 || x1 > GRID_SIZE/2 || y1 > GRID_SIZE/2 || y1 < -1*GRID_SIZE/2 || x2 > GRID_SIZE/2 || x2 < -1*GRID_SIZE/2 ||
						y2 > GRID_SIZE/2 || y2 < -1*GRID_SIZE/2)
					throw new NumberFormatException();

				//Initialize the coordinate list and layer
				Layer l = CoordListAction("LINE", xValue, yValue);
				grid.addLayer(l);

			} catch(Exception exc){JOptionPane.showMessageDialog(null, "Values must be integers between " + -1*GRID_SIZE/2 + " and " + GRID_SIZE/2);}

			drawLine(x1, y1, x2, y2, xValue, yValue);
		}

		//Fires when the draw rectangle button is pressed
		//Utilizes the drawRectangle method
		else if(e.getSource() == DrawRectangleButton) {

			int x1 = 0, x2 = 1;
			int y1 = 0, y2 = 1;

			try {
				x1 = Integer.parseInt(DrawRectangleX1TextField.getText());
				x2 = Integer.parseInt(DrawRectangleX2TextField.getText());
				y1 = Integer.parseInt(DrawRectangleY1TextField.getText());
				y2 = Integer.parseInt(DrawRectangleY2TextField.getText());

				//If the arguments are not in bounds throw an exception
				if(x1 < -1*GRID_SIZE/2 || x1 > GRID_SIZE/2 || y1 > GRID_SIZE/2 || y1 < -1*GRID_SIZE/2 || x2 > GRID_SIZE/2 || x2 < -1*GRID_SIZE/2 ||
						y2 > GRID_SIZE/2 || y2 < -1*GRID_SIZE/2)
					throw new NumberFormatException();
				else{
					//Call the drawRectangle method
					drawRectangle(x1, x2, y1, y2);
				}

			} catch(Exception exc){JOptionPane.showMessageDialog(null, "Values must be integers between " + -1*GRID_SIZE/2 + " and " + GRID_SIZE/2);}
		}

		//Fires when the draw triangle button is pressed
		//Utilizes the drawTriangle method
		else if(e.getSource() == DrawTriangleButton) {

			int x1=0, x2=1, x3=0;
			int y1=0, y2=1, y3=0;

			try {
				x1 = Integer.parseInt(DrawTriangleX1TextField.getText());
				x2 = Integer.parseInt(DrawTriangleX2TextField.getText());
				x3 = Integer.parseInt(DrawTriangleX3TextField.getText());
				y1 = Integer.parseInt(DrawTriangleY1TextField.getText());
				y2 = Integer.parseInt(DrawTriangleY2TextField.getText());
				y3 = Integer.parseInt(DrawTriangleY3TextField.getText());

				//If the arguments are not in bounds throw an exception
				if(x1 < -1*GRID_SIZE/2 || x1 > GRID_SIZE/2 || y1 > GRID_SIZE/2 || y1 < -1*GRID_SIZE/2 || x2 > GRID_SIZE/2 || x2 < -1*GRID_SIZE/2 ||
						y2 > GRID_SIZE/2 || y2 < -1*GRID_SIZE/2 || x3 > GRID_SIZE/2 || x3 < -1*GRID_SIZE/2 || y3 > GRID_SIZE/2 || y3 < -1*GRID_SIZE/2)
					throw new NumberFormatException();
				else{
					//Call the drawTriangle method
					drawTriangle(x1, x2, x3, y1, y2, y3);
				}

			}catch(Exception exc){JOptionPane.showMessageDialog(null, "Values must be integers between " + -1*GRID_SIZE/2 + " and " + GRID_SIZE/2);}

		}

		//Fires when the [triangle]iterate button is pressed
		//Utilizes the triangleIteration method
		else if(e.getSource() == DrawTriangleIterationButton) 
		{
			int width = 0, height = 0, startX = 0, startY = 0, exSteps = 0;
			double cycles = 0;
			boolean incY = false;
			//Initialize the coordinate arrays
			ArrayList<Integer> xValue = new ArrayList();
			ArrayList<Integer> yValue = new ArrayList();

			try {
				startX = Integer.parseInt(TriangleIterationStartXTextField.getText());
				startY = Integer.parseInt(TriangleIterationStartYTextField.getText());
				width = Integer.parseInt(TriangleIterationBeadsAddedTextField.getText());
				height = Integer.parseInt(TriangleIterationStepHeightTextField.getText());
				cycles = Integer.parseInt(TriangleIterationRowsTotalTextField.getText());

				//If the arguments are not in bounds throw an exception
				if(width < 0 || height < 0 || cycles < 0)
					throw new NumberFormatException();
				if(startX < -1*GRID_SIZE/2 || startX > GRID_SIZE/2 || startY < -1*GRID_SIZE/2 || startY > GRID_SIZE/2 ||
						width > GRID_SIZE+1 || height > GRID_SIZE+1 || height < -1*GRID_SIZE+1 || cycles > GRID_SIZE+1)
					throw new NumberFormatException();
			}
			catch(Exception exc)
			{
				if(width < 0 || height < 0 || cycles < 0)
					JOptionPane.showMessageDialog(null, "Values must not be negative");
				else
					JOptionPane.showMessageDialog(null, "Values must be integers between " + -1*GRID_SIZE/2 + " and " + GRID_SIZE/2);
			}

			//If stepHeight > totalRows only draw the total number of rows
			if ((cycles/(double)height) < 1)
			{
				cycles = 1;
				height = Integer.parseInt(TriangleIterationRowsTotalTextField.getText());
			}
			else //Check to make sure extra rows are accounted for
			{
				exSteps = (int)cycles % height;
				cycles = cycles/(double)height;
			}

			//Set incY to false if horizontal iteration is selected
			incY = !(TriangleIterationDirectionSelector.getSelectedIndex() == 2 || TriangleIterationDirectionSelector.getSelectedIndex() == 3);
			if(TriangleIterationDirectionSelector.getSelectedIndex() == 3 || TriangleIterationDirectionSelector.getSelectedIndex() == 1)
				height *= -1;

			//fill = TriangleIterationFilledCheckBox.isSelected();

			//Call the triangleIteration method
			triangleIteration(startX, startY, width, exSteps, height, cycles, incY, true, xValue, yValue);

			//Draw the iteration
			Layer l = CoordListAction("TRIANGLE_ITERATION", xValue, yValue);
			l.setX1(startX);
			l.setY1(startY);
			l.setStepHeight(Math.abs(height));
			l.setBeadsAdded1(width);
			l.setRowsTotal((int)cycles);
			l.setYInc(incY);
			l.setPositiveInc(height>=0);
			grid.addLayer(l);
		}

		//Fires when the [Linear]draw button is pressed
		//Utilizes the linearIteration method
		else if(e.getSource() == DrawLinearIterationButton)
		{
			int startLength = 5, startX = 0, startY = 0, inc1 = 1, inc2 = -1, rows = 5;
			boolean incY = true, posDir = true;
			//Initialize the coordinate arrays
			ArrayList<Integer> xValue = new ArrayList();
			ArrayList<Integer> yValue = new ArrayList();

			try
			{
				startLength = Math.abs(Integer.parseInt(LinearIterationStartLengthTextField.getText()));
				startX = Integer.parseInt(LinearIterationStartXTextField.getText());
				startY = Integer.parseInt(LinearIterationStartYTextField.getText());
				inc1 = Integer.parseInt(LinearIterationInc1TextField.getText());
				inc2 = Integer.parseInt(LinearIterationInc2TextField.getText());
				rows = Math.abs(Integer.parseInt(LinearIterationRowsTotalTextField.getText()));

				//If the arguments are not in bounds throw an exception
				if(startX < -1*GRID_SIZE/2 || startX > GRID_SIZE/2 || startY < -1*GRID_SIZE/2 || startY > GRID_SIZE/2 || startLength > GRID_SIZE+1 ||
						rows > GRID_SIZE+1 || inc1 < -1*GRID_SIZE+1 || inc1 > GRID_SIZE+1 || inc2 < -1*GRID_SIZE+1 || inc2 > GRID_SIZE+1)
					throw new NumberFormatException();
			}
			catch(Exception exc){JOptionPane.showMessageDialog(null, "Values must be integers between " + -1*GRID_SIZE/2 + " and " + GRID_SIZE/2);}

			incY = !(LinearIterationDirectionSelector.getSelectedIndex() == 2 || LinearIterationDirectionSelector.getSelectedIndex() == 3);
			if(LinearIterationDirectionSelector.getSelectedIndex() == 3 || LinearIterationDirectionSelector.getSelectedIndex() == 1)
			{
				posDir = false;
			}

			//Call the linearIteration method
			linearIteration(startX, startY, startLength, inc1, inc2, rows, incY, posDir, xValue, yValue);

			//Draw the iteration
			Layer l = CoordListAction("LINEAR_ITERATION", xValue, yValue);
			l.setX1(startX);
			l.setY1(startY);
			l.setStartLength(startLength);
			l.setBeadsAdded1(inc1);
			l.setBeadsAdded2(inc2);
			l.setRowsTotal(rows);
			l.setYInc(incY);
			l.setPositiveInc(posDir);
			grid.addLayer(l);
		}

		//Fires when the [Linear Sequence]draw button is pressed
		//Utilizes the linearIteration method
		else if(e.getSource() == DrawLinearItrLoopButton) 
		{
			int startX = 0, startY = 0, nStart = 5, nEnd = 8, inc1 = 0, inc2 = 1, rows = 5;
			boolean incY = true, posDir = true;

			try 
			{
				startX = Integer.parseInt(LinearItrLoopStartXTextField.getText());
				startY = Integer.parseInt(LinearItrLoopStartYTextField.getText());
				nStart = Integer.parseInt(LinearItrLoopStartNTextField.getText());
				nEnd = Integer.parseInt(LinearItrLoopEndNTextField.getText());
				inc1 = Integer.parseInt(LinearItrLoopInc1TextField.getText());
				inc2 = Integer.parseInt(LinearItrLoopInc2TextField.getText());
				rows = Integer.parseInt(LinearItrLoopRowsTextField.getText());

				//If the arguments are not in bounds throw an exception
				if( rows < 0 || nStart < 0)
					throw new NumberFormatException();

				//Set incY to false if horizontal iteration is selected
				incY = !(LinearItrLoopDirectionSelector.getSelectedIndex() == 2 || LinearItrLoopDirectionSelector.getSelectedIndex() == 3);
				if(LinearItrLoopDirectionSelector.getSelectedIndex() == 3 || LinearItrLoopDirectionSelector.getSelectedIndex() == 1)
				{
					posDir = false;
				}
			}
			catch(Exception exc)
			{
				//Display argument errors to user
				if(rows < 0 || nStart < 0)
					JOptionPane.showMessageDialog(null, "Values must not be negative");
				else
					JOptionPane.showMessageDialog(null, "Values must be integers between " + -1*GRID_SIZE/2 + " and " + GRID_SIZE/2);
			}

			//Call the sequenceIteration method
			LinearIterationLoop(startX, startY, nStart, nEnd, inc1, inc2, rows, incY, posDir);
		}

		//Fires when the [Triangle Sequence]draw button is pressed
		//Utilizes the TriangleItrLoopuenceIteration method
		else if(e.getSource() == DrawTriangleItrLoopButton) 
		{
			int startX = 0, startY = 0, nStart = 0, nEnd = 1, exSteps = 0;
			double cycles = 0;
			boolean incY = true;

			try 
			{
				startX = Integer.parseInt(TriangleItrLoopStartXTextField.getText());
				startY = Integer.parseInt(TriangleItrLoopStartYTextField.getText());
				nStart = Integer.parseInt(TriangleItrLoopStartNTextField.getText());
				nEnd = Integer.parseInt(TriangleItrLoopEndNTextField.getText());
				cycles = Integer.parseInt(TriangleItrLoopRowsTextField.getText());

				//If the arguments are not in bounds throw an exception
				if(cycles < 0 || nStart < 0)
					throw new NumberFormatException();

			}
			catch(Exception exc)
			{
				//Display argument errors to user
				if(cycles < 0 || nStart < 0)
					JOptionPane.showMessageDialog(null, "Values must not be negative");
				else
					JOptionPane.showMessageDialog(null, "Values must be integers between " + -1*GRID_SIZE/2 + " and " + GRID_SIZE/2);
			}

			//Call the sequenceIteration method
			TriangleIterationLoop(startX, startY, nStart, nEnd, exSteps, cycles, incY);
		}

		//Fires when the GraphTrigFunctionButton is pressed
		else if(e.getSource() == GraphTrigFunctionButton) 
		{

			String multiplierString = TrigMultiplierTextField.getText();
			String argumentString = TrigFunctionArgumentTextField.getText();
			boolean legalMultiplier = true, legalFunctionArgument = true, additiveIsInt = true;

			float multiplier = 0, argument = 0;
			int additive = 0;

			try 
			{multiplier = Float.parseFloat(multiplierString);} 
			catch(Exception exc)
			{//multiplier must be fraction or float
				try {
					if(multiplierString.indexOf('/') == -1)
						throw new NumberFormatException();
					int numerator = Integer.parseInt(multiplierString.substring(0, multiplierString.indexOf('/')));
					int denominator = Integer.parseInt(multiplierString.substring(multiplierString.indexOf('/') + 1, multiplierString.length()));
					if(denominator != 0)
						multiplier = (float)numerator / denominator;
					else
						throw new NumberFormatException();

				} catch(Exception exc2) {
					legalMultiplier = false;
					JOptionPane.showMessageDialog(this, "Multiplier must be a decimal or fraction");
				}
			}
			try{argument = Float.parseFloat(argumentString);} //function argument must be fraction or float
			catch(Exception exc)
			{
				try {
					if(argumentString.indexOf('/') == -1)
						throw new NumberFormatException();
					int numerator = Integer.parseInt(argumentString.substring(0, argumentString.indexOf('/')));
					int denominator = Integer.parseInt(argumentString.substring(argumentString.indexOf('/') + 1, argumentString.length()));
					if(denominator != 0)
						argument = (float)numerator / denominator;
					else
						throw new NumberFormatException();
				}
				catch(Exception exc2)
				{
					legalFunctionArgument = false;
					JOptionPane.showMessageDialog(this, "Trig Function Argument must be decimal or fraction");
				}
			}
			try {
				additive = Integer.parseInt(TrigAdditiveTextField.getText());
			} catch(Exception exc)
			{
				additiveIsInt = false;
				JOptionPane.showMessageDialog(this, "Numerical additive must be an integer");
			}
			if(legalMultiplier && legalFunctionArgument && additiveIsInt) 
			{
				//Call the trigFunction method
				trigFunction(additive, multiplier, argument);
			}
		}

		/*else if(e.getSource() == LinearIterationPYDirectionButton || e.getSource() == LinearIterationNYDirectionButton
				|| e.getSource() == LinearIterationPXDirectionButton || e.getSource() == LinearIterationNXDirectionButton)
		{
			LinearIterationTotalRowsLabel.setText("rows in total in the " +
					getLinearIterationDirection().getText() + " direction.");
			System.out.println("Radio button clicked.");
		}*/
	}


	//Removes the Loop Tools from the GUI
	//Do not want them to have access to them in the game... for now
	public void removeLoopTools(){
		LinearIterationTabbedPane.remove(TriangleItrLoopLayeredPane);
		LinearIterationTabbedPane.remove(LinearItrLoopLayeredPane);
	}

	//Add the Loop Tools back into tjhe GUI
	public void addLoopTools(){
		LinearIterationTabbedPane.addTab("Linear Iteration Loop", LinearItrLoopLayeredPane);
		LinearIterationTabbedPane.addTab("Triangle Iteration Loop", TriangleItrLoopLayeredPane);
	}


	//------------Other Get and Set Methods--------------//


	public JTabbedPane getBeadLoomUtilitiesTabbedPane() {
		return BeadLoomUtilitiesTabbedPane;
	}
	//	Set Grid stuff
	public void setGridSize(int x) {
		GRID_SIZE = x;
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

	public int getGridSize() {
		return GRID_SIZE;
	}
	public int getPad() {
		return PAD;
	}
	public double getxInc() {
		return xInc;
	}
	public double getyInc() {
		return yInc;
	}

	public JButton getDrawPointButton() {
		return DrawPointButton;
	}
	public JButton getDrawLineButton() {
		return DrawLineButton;
	}
	public JButton getRectangleButton() {
		return DrawRectangleButton;
	}
	public JButton getDrawTriangleIterationButton() {
		return DrawTriangleIterationButton;
	}

	public JButton getDrawLinearIterationButton() {
		return DrawLinearIterationButton;
	}

	public JButton getDrawTriangleButton() {
		return DrawTriangleButton;
	}

	public JButton getGraphTrigFunctionButton()
	{
		return GraphTrigFunctionButton;
	}
	public JTextField getDrawPointXTextField(){
		return DrawPointXTextField;
	}
	public JTextField getDrawPointYTextField(){
		return DrawPointYTextField;
	}
	public JTextField getDrawLineX1TextField(){
		return DrawLineX1TextField;
	}
	public JTextField getDrawLineX2TextField(){
		return DrawLineX2TextField;
	}
	public JTextField getDrawLineY1TextField(){
		return DrawLineY1TextField;
	}
	public JTextField getDrawLineY2TextField(){
		return DrawLineY2TextField;
	}
	public JTextField getDrawRectangleX1TextField(){
		return DrawRectangleX1TextField;
	}
	public JTextField getDrawRectangleX2TextField(){
		return DrawRectangleX2TextField;
	}
	public JTextField getDrawRectangleY1TextField(){
		return DrawRectangleY1TextField;
	}
	public JTextField getDrawRectangleY2TextField(){
		return DrawRectangleY2TextField;
	}

	public JTextField getTriangleIterationStartXTextField(){
		return TriangleIterationStartXTextField;
	}
	public JTextField getTriangleIterationStartYTextField(){
		return TriangleIterationStartYTextField;
	}
	public JTextField getTriangleIterationStepHeightTextField(){
		return TriangleIterationStepHeightTextField;
	}
	public JTextField getTriangleIterationBeadsAddedTextField(){
		return TriangleIterationBeadsAddedTextField;
	}
	public JTextField getTriangleIterationRowsTotalTextField(){
		return TriangleIterationRowsTotalTextField;
	}
	public JComboBox getTriangleIterationDirectionLabel(){
		return TriangleIterationDirectionSelector;
	}

	public JTextField getLinearIterationStartXTextField(){
		return LinearIterationStartXTextField;
	}
	public JTextField getLinearIterationStartYTextField(){
		return LinearIterationStartYTextField;
	}
	public JTextField getLinearIterationStepsTextField(){
		return LinearIterationRowsTotalTextField;
	}
	public JTextField getLinearIterationInc1TextField(){
		return LinearIterationInc1TextField;
	}
	public JTextField getLinearIterationInc2TextField(){
		return LinearIterationInc2TextField;
	}
	public JTextField getLinearIterationRowsTotalTextField(){
		return LinearIterationRowsTotalTextField;
	}
	public JTextField getLinearIterationStartLengthTextField(){
		return LinearIterationStartLengthTextField;
	}

	public JTextField getDrawTriangleX1TextField(){
		return DrawTriangleX1TextField;
	}
	public JTextField getDrawTriangleX2TextField(){
		return DrawTriangleX2TextField;
	}
	public JTextField getDrawTriangleX3TextField(){
		return DrawTriangleX3TextField;
	}
	public JTextField getDrawTriangleY1TextField(){
		return DrawTriangleY1TextField;
	}
	public JTextField getDrawTriangleY2TextField(){
		return DrawTriangleY2TextField;
	}
	public JTextField getDrawTriangleY3TextField(){
		return DrawTriangleY3TextField;
	}

	public JTabbedPane getLayersDrawTabbedPane(){
		return LayersDrawTabbedPane;
	}
	public JTabbedPane getTrigFunctionsTabbedPane(){
		return TrigFunctionsTabbedPane;
	}

	public void setImage(Image ii){
		catimage = ii;
		System.out.println("setImage called");
	}

	//Used for getting layer
	public int getSelectedZ() {
		return lay.getZValue();
	}
	public JComboBox getLayerList() {
		return LayersBox;
	}
	public void setLay(Layer l) {
		lay=l;
	}
	public Layer getLay() {
		return lay;
	}
	public JButton getLinearIterationDrawButton(){
		return DrawLinearIterationButton;
	}


	private synchronized Image makeBullet(Color fg, int panelWidth, int panelHeight) {

		Image bullet = null;
		try
		{
			beadImage = beadImage.getScaledInstance(panelWidth/GRID_SIZE+1,
					panelHeight/GRID_SIZE+1, 0);
			ImageFilter imgf = new HueFilter(fg);
			ImageProducer imgp = new FilteredImageSource(beadImage.getSource(), imgf);
			bullet = createImage(imgp);
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "Error! Error Message: " + e.getCause());
		}


		/*Image bullet;
        Image sourceImage = Toolkit.getDefaultToolkit().getImage(BeadLoom.beadLocation);

        //scale beads to fit panel width and height
        sourceImage = sourceImage.getScaledInstance(panelWidth/GRID_SIZE+1,
        		panelHeight/GRID_SIZE+1, 0);

    	ImageFilter imgf = new HueFilter(fg);
    	ImageProducer imgp = new FilteredImageSource(sourceImage.getSource(),imgf);
    	bullet = createImage(imgp);
    	JOptionPane.showMessageDialog(null, "makeBullet in GUIInputTools was called");*/
		return bullet;
	}

	public Image getMakeBullet(Color fg, int panelWidth, int panelHeight) {
		return makeBullet(fg, panelWidth, panelHeight);
	}

	public void setColor(Color c) {
		color = c;
	}

	public JRadioButton getLinearIterationDirection()
	{
		/*if(LinearIterationPYDirectionButton.isSelected())
    		return LinearIterationPYDirectionButton;
    	if(LinearIterationNYDirectionButton.isSelected())
    		return LinearIterationNYDirectionButton;
    	if(LinearIterationPXDirectionButton.isSelected())
    		return LinearIterationPXDirectionButton;
    	if(LinearIterationNXDirectionButton.isSelected())
    		return LinearIterationNXDirectionButton;*/
		return null;
	}

	//Linear Iteration "Help" message button action
	private void LinearHelpButtonActionPerformed(ActionEvent e)
	{
		URL url;

		try 
		{
			url = new URL(BeadLoom.BEAD_ADDRESS+"LinearItrHelp.jpg");
			ImageIcon ii = new ImageIcon(url);
			Icon i = ii;

			//Set image URL to baseURL 'BEAD_ADDRESS' and append image name 
			JOptionPane.showMessageDialog(null, i,"Linear iteration help", JOptionPane.INFORMATION_MESSAGE);

		}
		catch (Exception ex) 
		{
			//If BEAD_ADDRESS is null set to my default location
			try
			{
				url = new URL("http://www.rpi.edu/~kotulc/Images/LinearItrHelp.jpg");
				ImageIcon ii = new ImageIcon(url);
				Icon i = ii;

				JOptionPane.showMessageDialog(null, i,"Linear iteration help", JOptionPane.INFORMATION_MESSAGE);
			}
			catch(MalformedURLException exp){return;}//Else quit
		}
	}

	//Linear Iteration Cycle "Help" message button action
	private void LinearItrHelpButtonActionPerformed(ActionEvent e)
	{
		URL url;

		try 
		{
			url = new URL(BeadLoom.BEAD_ADDRESS+"LinearItrLoopHelp.jpg");
			ImageIcon ii = new ImageIcon(url);
			Icon i = ii;

			//Set image URL to baseURL 'BEAD_ADDRESS' and append image name 
			JOptionPane.showMessageDialog(null, i,"Linear iteration loop help", JOptionPane.INFORMATION_MESSAGE);

		}
		catch (Exception ex) 
		{
			//If BEAD_ADDRESS is null set to my default location
			try
			{
				url = new URL("http://www.rpi.edu/~kotulc/Images/LinearItrLoopHelp.jpg");
				ImageIcon ii = new ImageIcon(url);
				Icon i = ii;

				JOptionPane.showMessageDialog(null, i,"Linear iteration loop help", JOptionPane.INFORMATION_MESSAGE);
			}
			catch(MalformedURLException exp){return;}//Else quit
		}
	}

	//Triangle iteration "Help" message button action
	private void TriangleIterHelpButtonActionPerformed(ActionEvent e)
	{
		URL url;

		try 
		{
			url = new URL(BeadLoom.BEAD_ADDRESS+"TriangularItrHelp.jpg");
			ImageIcon ii = new ImageIcon(url);
			Icon i = ii;

			//Set image URL to baseURL 'BEAD_ADDRESS' and append image name 
			JOptionPane.showMessageDialog(null, i,"Triangle iteration help", JOptionPane.INFORMATION_MESSAGE);

		}
		catch (Exception ex) 
		{
			//If BEAD_ADDRESS is null set to my default location
			try
			{
				url = new URL("http://www.rpi.edu/~kotulc/Images/TriangularItrHelp.jpg");
				ImageIcon ii = new ImageIcon(url);
				Icon i = ii;

				JOptionPane.showMessageDialog(null, i,"Triangle iteration help", JOptionPane.INFORMATION_MESSAGE);
			}
			catch(MalformedURLException exp){return;}//Else quit
		}
	}

	private void TriangleCycleHelpButtonActionPerformed(ActionEvent e)
	{
		URL url;

		try 
		{
			url = new URL(BeadLoom.BEAD_ADDRESS+"TriangularItrLoopHelp.jpg");
			ImageIcon ii = new ImageIcon(url);
			Icon i = ii;

			//Set image URL to baseURL 'BEAD_ADDRESS' and append image name 
			JOptionPane.showMessageDialog(null, i,"Triangle Iteration loop help", JOptionPane.INFORMATION_MESSAGE);

		}
		catch (Exception ex) 
		{
			//If BEAD_ADDRESS is null set to my default location
			try
			{
				url = new URL("http://www.rpi.edu/~kotulc/Images/TriangularItrLoopHelp.jpg");
				ImageIcon ii = new ImageIcon(url);
				Icon i = ii;

				JOptionPane.showMessageDialog(null, i,"Triangle Iteration loop help", JOptionPane.INFORMATION_MESSAGE);
			}
			catch(MalformedURLException exp){return;}//Else quit
		}
	}

	//Linear sequence tool Statement1 method
	public int LinearItrLoopStatement1(int x)
	{
		int y = 0;

		//If the statement is adding a number...
		if (LinearItrLoopPlusOrMinus1Selector.getSelectedIndex() == 0)
		{
			try {
				//Get the number being added
				y = Integer.parseInt(LinearItrLoopStatement1TextField.getText());
				//Add this number to the argument and return the result
				x = x+y;
				return x;
			}
			catch(Exception exc)
			{JOptionPane.showMessageDialog(null, "Values must be integers between -50 and 50");}
			return 0;
		}
		//The statement is subtracting a number
		else 
		{
			try {
				//Get the number being subtracted
				y = Integer.parseInt(LinearItrLoopStatement1TextField.getText());
				//Subtract this number from the argument and return the result
				x = x-y;
				return x;
			}
			catch(Exception exc)
			{JOptionPane.showMessageDialog(null, "Values must be integers between -50 and 50");}
			return 0;
		} 	
	}

	public int LinearItrLoopStatement2(int x)
	{
		int y = 0;

		//If the statement is adding a number...
		if (LinearItrLoopPlusOrMinus2Selector.getSelectedIndex() == 0)
		{
			try {
				//Get the number being added
				y = Integer.parseInt(LinearItrLoopStatement2TextField.getText());
				//Add this number to the argument and return the result
				x = x+y;
				return x;
			}
			catch(Exception exc)
			{JOptionPane.showMessageDialog(null, "Values must be integers between -50 and 50");}
			return 0;
		}
		//The statement is subtracting a number
		else 
		{
			try {
				//Get the number being subtracted
				y = Integer.parseInt(LinearItrLoopStatement2TextField.getText());
				//Subtract this number from the argument and return the result
				x = x-y;
				return x;
			}
			catch(Exception exc)
			{JOptionPane.showMessageDialog(null, "Values must be integers between -50 and 50");}
			return 0;
		} 	
	}

	//Triangle sequence tool Statement1 method
	public int TriangleItrLoopStatement1(int x)
	{
		int y = 0;

		//If the statement is adding a number...
		if (TriangleItrLoopPlusOrMinus1Selector.getSelectedIndex() == 0)
		{
			try {
				//Get the number being added
				y = Integer.parseInt(TriangleItrLoopStatement1TextField.getText());
				//Add this number to the argument and return the result
				x = x+y;
				return x;
			}
			catch(Exception exc)
			{JOptionPane.showMessageDialog(null, "Values must be integers between -50 and 50");}
			return 0;
		}
		//The statement is subtracting a number
		else 
		{
			try {
				//Get the number being subtracted
				y = Integer.parseInt(TriangleItrLoopStatement1TextField.getText());
				//Subtract this number from the argument and return the result
				x = x-y;
				return x;
			}
			catch(Exception exc)
			{JOptionPane.showMessageDialog(null, "Values must be integers between -50 and 50");}
			return 0;
		} 	
	}

	//Triangle sequence tool Statement2 method
	public int TriangleItrLoopStatement2(int x)
	{
		int y = 0;

		//If the statement is adding a number...
		if (TriangleItrLoopPlusOrMinus2Selector.getSelectedIndex() == 0)
		{
			try {
				//Get the number being added
				y = Integer.parseInt(TriangleItrLoopStatement2TextField.getText());
				//Add this number to the argument and return the result
				x = x+y;
				return x;
			}
			catch(Exception exc)
			{JOptionPane.showMessageDialog(null, "Values must be integers between -50 and 50");}
			return 0;
		}
		//The statement is subtracting a number
		else 
		{
			try {
				//Get the number being subtracted
				y = Integer.parseInt(TriangleItrLoopStatement2TextField.getText());
				//Subtract this number from the argument and return the result
				x = x-y;
				return x;
			}
			catch(Exception exc)
			{JOptionPane.showMessageDialog(null, "Values must be integers between -50 and 50");}
			return 0;
		} 	
	}

	public void redrawBead()
	{
		try
		{
			URL beadURL = new URL(BeadLoom.beadLocation);
			JOptionPane.showMessageDialog(null, "Bead Location is " + BeadLoom.beadLocation);
			java.awt.Toolkit.getDefaultToolkit();
			beadImage = Toolkit.getDefaultToolkit().createImage(beadURL);

		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "Error Creating Bead!");
		}
	}


}


