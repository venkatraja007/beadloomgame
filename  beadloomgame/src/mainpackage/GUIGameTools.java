package src.mainpackage;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.*;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.*;

import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;

import javax.swing.JPanel;

import javax.swing.JLabel;

import javax.swing.JTextField;

import javax.swing.border.LineBorder;

public class GUIGameTools extends JPanel implements ActionListener{
	public static Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	
	private String[] scoreFile;
	private int playerIndex;
	private int totalEntries;
	private int currentPuzzle;
	private int hintLevel;
	
	private long puzzleStartTime;
	
	private Color peach = new Color(255, 200, 150);
	private Color betterOrange = new Color(255,100,0);
	//GUI Parts
	//Panels
	private JPanel GamePanel = new JPanel();
	private JPanel ChoosePuzzlePanel = new JPanel();
	private JPanel ColorPanel = new JPanel();
	private JPanel HighScoresPanel = new JPanel();
	private JPanel GameOptionsPanel = new JPanel();
	private FlowLayout HighScoresLayout = new FlowLayout(FlowLayout.LEFT);
	
	//GamePanel Parts
	private JButton ChoosePuzzleButton = new JButton();
	private JButton RestartButton = new JButton();
	private JButton SubmitButton = new JButton();
	private JButton GameOptionsButton = new JButton();
	private JButton QuitButton = new JButton();
	private JButton PlayGameButton = new JButton();
	private JButton HighScoresButton = new JButton();
	private JButton WebRequestButton = new JButton();
	private JLabel NameLabel = new JLabel();
	private JLabel BestScoreLabel = new JLabel();
	private JTextField NameTextField = new JTextField();
	
	//Choose Puzzle Panel Parts
	private JButton TutButton = new JButton();
	private JButton EasyButton = new JButton();
	private JButton MediumButton = new JButton();
	private JButton HardButton = new JButton();
	
	private JButton Tut1Button = new JButton();
	private JButton Tut2Button = new JButton();
	private JButton Tut3Button = new JButton();
	private JButton Tut4Button = new JButton();
	private JButton Tut5Button = new JButton();
	private JButton Tut6Button = new JButton();
	private JButton TriforceButton = new JButton();
	private JButton OverlappingSquaresButton = new JButton();
	private JButton LoomEx14Button = new JButton();
	private JButton SixButton = new JButton();
	private JButton CanYouHearMeNowButton = new JButton();
	private JButton StarrySkyButton = new JButton();
	private JButton LoomEx6Button = new JButton();
	private JButton LoomEx8Button = new JButton();
	private JButton CircleButton = new JButton();
	
	private JButton FlagButton = new JButton();
	private JButton UNCCButton = new JButton();
	private JButton LoomEx10Button = new JButton();
	private JButton LoomEx13Button = new JButton();
	private JButton SunriseButton = new JButton();
	private JButton HeartButton = new JButton();
	private JButton SunnySkyButton = new JButton();
	private JButton SergeantButton = new JButton();
	private JButton TieFighterButton = new JButton();
	
	private JButton StarsAndStripesButton = new JButton();
	private JButton ROSSButton = new JButton();
	private JButton BullseyeButton = new JButton();
	private JButton LoomEx7Button = new JButton();
	private JButton LoomEx1Button = new JButton();
	private JButton LoomEx5Button = new JButton();
	private JButton LoomEx3Button = new JButton();
	private JButton MoonButton = new JButton();
	private JButton MegamanButton = new JButton();
	private JButton BatmanButton = new JButton();
	private JButton DCButton = new JButton();
	
	private JButton CancelButton = new JButton();
	
	//High Scores Panel Label
	private JLabel HighScoresLabel = new JLabel();
	private JTextField WebRequestTextField = new JTextField(20);
	
	//Game Options Panel
	private JButton ColorBlindButton = new JButton();
	private JButton HintsButton = new JButton();
	private JButton NormalBeadButton = new JButton();
	private JButton AwesomeBeadButton = new JButton();
	private JButton PeaceBeadButton = new JButton();
	private JButton GameOptionsCloseButton = new JButton();
	//
	
	//Choose Puzzle High Score Labels
	private JLabel HSLabel1 = new JLabel();
	private JLabel HSLabel2 = new JLabel();
	private JLabel HSLabel3 = new JLabel();
	private JLabel HSLabel4 = new JLabel();
	private JLabel HSLabel5 = new JLabel();
	private JLabel HSLabel6 = new JLabel();
	private JLabel HSLabel7 = new JLabel();
	private JLabel HSLabel8 = new JLabel();
	private JLabel HSLabel9 = new JLabel();
	private JLabel HSLabelA = new JLabel();
	private JLabel HSLabelB = new JLabel();
	
	//Choose Puzzle Medal Labels
	private JLabel MLabel1 = new JLabel();
	private JLabel MLabel2 = new JLabel();
	private JLabel MLabel3 = new JLabel();
	private JLabel MLabel4 = new JLabel();
	private JLabel MLabel5 = new JLabel();
	private JLabel MLabel6 = new JLabel();
	private JLabel MLabel7 = new JLabel();
	private JLabel MLabel8 = new JLabel();
	private JLabel MLabel9 = new JLabel();
	private JLabel MLabelA = new JLabel();
	private JLabel MLabelB = new JLabel();
		
	
	//Choose Puzzle Misc Labels
	private JLabel CPNameLabel = new JLabel();
	private JLabel HSLabel0 = new JLabel();
	private JLabel MLabel0 = new JLabel();
	
	
	//Color Panel Parts
	private JButton GreenButton = new JButton();
	private JButton RedButton = new JButton();
	private JButton YellowButton = new JButton();
	private JButton BlueButton = new JButton();
	private JButton MagentaButton = new JButton();
	private JButton CyanButton = new JButton();
	private JButton OrangeButton = new JButton();
	private JButton WhiteButton = new JButton();
	private JButton BlackButton = new JButton();
	private JButton PinkButton = new JButton();
	private JButton PeachButton = new JButton(); 
	private JButton GrayButton = new JButton();
	
	
	private JLabel MoveLabel = new JLabel();
	
	private Color color = new Color(255, 0, 0);
	
	//Game variables
	int moveCounter = 0;
	String playerName = "Acey";
	int bestScore = 1000;
	Puzzle puz;
	
	
	int[] RecordMove;
	String[] RecordMedal;
	String[] RecordMedalShort;
	
	
	//beadloom
	BeadLoom bl;
	
	//Choose Puzzle Button Locations
	int buttonLength = 200;
	int buttonHeight = 26;
	int vertSpace = 5;
	int horizSpace = 5;
	int startX = 5;
	Rectangle R00 = new Rectangle(startX, vertSpace, buttonLength, buttonHeight);
	Rectangle R10 = new Rectangle(startX, R00.y + buttonHeight+vertSpace, buttonLength, buttonHeight);
	Rectangle R20 = new Rectangle(startX, R10.y + buttonHeight+vertSpace, buttonLength, buttonHeight);
	Rectangle R30 = new Rectangle(startX, R20.y + buttonHeight+vertSpace, buttonLength, buttonHeight);
	Rectangle R40 = new Rectangle(startX, R30.y + buttonHeight+vertSpace, buttonLength, buttonHeight);
	Rectangle R50 = new Rectangle(startX, R40.y + buttonHeight+vertSpace, buttonLength, buttonHeight);
	Rectangle R60 = new Rectangle(startX, R50.y + buttonHeight+vertSpace, buttonLength, buttonHeight);
	Rectangle R70 = new Rectangle(startX, R60.y + buttonHeight+vertSpace, buttonLength, buttonHeight);
	Rectangle R80 = new Rectangle(startX, R70.y + buttonHeight+vertSpace, buttonLength, buttonHeight);
	Rectangle R90 = new Rectangle(startX, R80.y + buttonHeight+vertSpace, buttonLength, buttonHeight);
	Rectangle RA0 = new Rectangle(startX, R90.y + buttonHeight+vertSpace, buttonLength, buttonHeight);
	Rectangle RB0 = new Rectangle(startX, RA0.y + buttonHeight+vertSpace, buttonLength, buttonHeight);
	
	Rectangle R01 = new Rectangle(R00.x +buttonLength+horizSpace, vertSpace, buttonLength, buttonHeight);
	Rectangle R11 = new Rectangle(R10.x +buttonLength+horizSpace, R01.y + buttonHeight+vertSpace, buttonLength, buttonHeight);
	Rectangle R21 = new Rectangle(R20.x +buttonLength+horizSpace, R11.y + buttonHeight+vertSpace, buttonLength, buttonHeight);
	Rectangle R31 = new Rectangle(R30.x +buttonLength+horizSpace, R21.y + buttonHeight+vertSpace, buttonLength, buttonHeight);
	Rectangle R41 = new Rectangle(R40.x +buttonLength+horizSpace, R31.y + buttonHeight+vertSpace, buttonLength, buttonHeight);
	Rectangle R51 = new Rectangle(R50.x +buttonLength+horizSpace, R41.y + buttonHeight+vertSpace, buttonLength, buttonHeight);
	Rectangle R61 = new Rectangle(R60.x +buttonLength+horizSpace, R51.y + buttonHeight+vertSpace, buttonLength, buttonHeight);
	Rectangle R71 = new Rectangle(R70.x +buttonLength+horizSpace, R61.y + buttonHeight+vertSpace, buttonLength, buttonHeight);
	Rectangle R81 = new Rectangle(R80.x +buttonLength+horizSpace, R71.y + buttonHeight+vertSpace, buttonLength, buttonHeight);
	Rectangle R91 = new Rectangle(R90.x +buttonLength+horizSpace, R81.y + buttonHeight+vertSpace, buttonLength, buttonHeight);
	Rectangle RA1 = new Rectangle(RA0.x +buttonLength+horizSpace, R91.y + buttonHeight+vertSpace, buttonLength, buttonHeight);
	Rectangle RB1 = new Rectangle(RB0.x +buttonLength+horizSpace, RA1.y + buttonHeight+vertSpace, buttonLength, buttonHeight);
	
	Rectangle R02 = new Rectangle(R01.x +buttonLength+horizSpace, vertSpace, buttonLength, buttonHeight);
	Rectangle R12 = new Rectangle(R11.x +buttonLength+horizSpace, R02.y + buttonHeight+vertSpace, buttonLength, buttonHeight);
	Rectangle R22 = new Rectangle(R21.x +buttonLength+horizSpace, R12.y + buttonHeight+vertSpace, buttonLength, buttonHeight);
	Rectangle R32 = new Rectangle(R31.x +buttonLength+horizSpace, R22.y + buttonHeight+vertSpace, buttonLength, buttonHeight);
	Rectangle R42 = new Rectangle(R41.x +buttonLength+horizSpace, R32.y + buttonHeight+vertSpace, buttonLength, buttonHeight);
	Rectangle R52 = new Rectangle(R51.x +buttonLength+horizSpace, R42.y + buttonHeight+vertSpace, buttonLength, buttonHeight);
	Rectangle R62 = new Rectangle(R61.x +buttonLength+horizSpace, R52.y + buttonHeight+vertSpace, buttonLength, buttonHeight);
	Rectangle R72 = new Rectangle(R71.x +buttonLength+horizSpace, R62.y + buttonHeight+vertSpace, buttonLength, buttonHeight);
	Rectangle R82 = new Rectangle(R81.x +buttonLength+horizSpace, R72.y + buttonHeight+vertSpace, buttonLength, buttonHeight);
	Rectangle R92 = new Rectangle(R91.x +buttonLength+horizSpace, R82.y + buttonHeight+vertSpace, buttonLength, buttonHeight);
	Rectangle RA2 = new Rectangle(RA1.x +buttonLength+horizSpace, R92.y + buttonHeight+vertSpace, buttonLength, buttonHeight);
	Rectangle RB2 = new Rectangle(RB1.x +buttonLength+horizSpace, RA2.y + buttonHeight+vertSpace, buttonLength, buttonHeight);

	Rectangle LeftDifR = new Rectangle(startX, RA0.y + buttonHeight + buttonHeight + vertSpace + vertSpace, buttonLength, buttonHeight);
	Rectangle CenterDifR = new Rectangle(LeftDifR.x + buttonLength + horizSpace, RA1.y + buttonHeight + buttonHeight + vertSpace + vertSpace, buttonLength, buttonHeight);
	Rectangle RightDifR = new Rectangle(CenterDifR.x + buttonLength + horizSpace, RA2.y + buttonHeight + buttonHeight + vertSpace + vertSpace, buttonLength, buttonHeight);
	Rectangle CancelR = new Rectangle(CenterDifR.x, CenterDifR.y + buttonHeight + vertSpace, buttonLength, buttonHeight);
		
	//Color Button Locations
	int colorButtonLength = 85;
	int colorButtonHeight = 30;
	int colorVertSpace = 5;
	int colorHorizSpace = 5;
	int colorStartX = 5;
	Rectangle CR00 = new Rectangle(colorStartX, colorVertSpace, colorButtonLength, colorButtonHeight);
	Rectangle CR10 = new Rectangle(colorStartX, CR00.y + colorButtonHeight+colorVertSpace, colorButtonLength, colorButtonHeight);
	Rectangle CR20 = new Rectangle(colorStartX, CR10.y + colorButtonHeight+colorVertSpace, colorButtonLength, colorButtonHeight);
	
	Rectangle CR01 = new Rectangle(CR00.x + colorButtonLength + colorHorizSpace, colorVertSpace, colorButtonLength, colorButtonHeight);
	Rectangle CR11 = new Rectangle(CR10.x + colorButtonLength + colorHorizSpace, CR01.y + colorButtonHeight + colorVertSpace, colorButtonLength, colorButtonHeight);
	Rectangle CR21 = new Rectangle(CR20.x + colorButtonLength + colorHorizSpace, CR11.y + colorButtonHeight + colorVertSpace, colorButtonLength, colorButtonHeight);
	
	Rectangle CR02 = new Rectangle(CR01.x + colorButtonLength + colorHorizSpace, colorVertSpace, colorButtonLength, colorButtonHeight);
	Rectangle CR12 = new Rectangle(CR11.x + colorButtonLength + colorHorizSpace, CR02.y + colorButtonHeight + colorVertSpace, colorButtonLength, colorButtonHeight);
	Rectangle CR22 = new Rectangle(CR21.x + colorButtonLength + colorHorizSpace, CR12.y + colorButtonHeight + colorVertSpace, colorButtonLength, colorButtonHeight);
	
	Rectangle CR03 = new Rectangle(CR02.x + colorButtonLength + colorHorizSpace, colorVertSpace, colorButtonLength, colorButtonHeight);
	Rectangle CR13 = new Rectangle(CR12.x + colorButtonLength + colorHorizSpace, CR03.y + colorButtonHeight + colorVertSpace, colorButtonLength, colorButtonHeight);
	Rectangle CR23 = new Rectangle(CR22.x + colorButtonLength + colorHorizSpace, CR13.y + colorButtonHeight + colorVertSpace, colorButtonLength, colorButtonHeight);
	
		public GUIGameTools() {
		
		hintLevel = 0;
				
		//---- game panel -----
		GamePanel.setBorder(new LineBorder(Color.red));
		GamePanel.setBackground(Color.white);
		GamePanel.setLayout(null);
		GamePanel.setBounds(4, 3, 195, 165);
		
		//---- high scores panel ----
		HighScoresPanel.setBorder(new LineBorder(Color.red));
		HighScoresPanel.setBackground(Color.white);
		HighScoresPanel.setBounds(ChoosePuzzlePanel.getBounds());
		HighScoresPanel.setLayout(HighScoresLayout);
		
		//---- high scores panel ----
		GameOptionsPanel.setBorder(new LineBorder(Color.red));
		GameOptionsPanel.setBackground(Color.white);
		GameOptionsPanel.setBounds(ChoosePuzzlePanel.getBounds());
		GameOptionsPanel.setLayout(null);
		
		//---- choose puzzle panel -----
		ChoosePuzzlePanel.setBorder(new LineBorder(Color.red));
		ChoosePuzzlePanel.setBackground(Color.white);
		ChoosePuzzlePanel.setLayout(null);
		ChoosePuzzlePanel.setBounds(4, 3, 200, 300);
		
		//---- choose Color Panel ----
		ColorPanel.setBorder(new LineBorder(Color.red));
		ColorPanel.setBackground(Color.white);
		ColorPanel.setLayout(null);
		ColorPanel.setBounds((int)BeadLoom.dim.getWidth()/2, (int)BeadLoom.dim.getHeight()/2, 200, 200);
		
		//---- Choose puzzle name label ----
		CPNameLabel.setText("Puzzle:");
		CPNameLabel.setBounds(R00);
		ChoosePuzzlePanel.add(CPNameLabel);
		
		//---- Tut1 Button -----
		Tut1Button.setText("Tutorial 1");
		Tut1Button.addActionListener(this);
		Tut1Button.setBounds(R10);
		ChoosePuzzlePanel.add(Tut1Button);
		
		//---- Tut2 Button ----
		Tut2Button.setText("Tutorial 2");
		Tut2Button.addActionListener(this);
		Tut2Button.setBounds(R20);
		ChoosePuzzlePanel.add(Tut2Button);
		
		//---- Tut3 Button ----
		Tut3Button.setText("Tutorial 3");
		Tut3Button.addActionListener(this);
		Tut3Button.setBounds(R30);
		ChoosePuzzlePanel.add(Tut3Button);
		
		//---- Tut4 Button ----
		Tut4Button.setText("Tutorial 4");
		Tut4Button.addActionListener(this);
		Tut4Button.setBounds(R40);
		ChoosePuzzlePanel.add(Tut4Button);
		
		//---- Tut5 Button ----
		Tut5Button.setText("Tutorial 5");
		Tut5Button.addActionListener(this);
		Tut5Button.setBounds(R50);
		ChoosePuzzlePanel.add(Tut5Button);
		
		//---- Tut6 Button ----
		Tut6Button.setText("Tutorial 6");
		Tut6Button.addActionListener(this);
		Tut6Button.setBounds(R60);
		ChoosePuzzlePanel.add(Tut6Button);
		
		//---- Triforce Button ----
		TriforceButton.setText("Triforce");
		TriforceButton.addActionListener(this);
		TriforceButton.setBounds(R10);
		
		
		//---- Overlapping Squares Button ----
		OverlappingSquaresButton.setText("Overlapping Squares");
		OverlappingSquaresButton.addActionListener(this);
		OverlappingSquaresButton.setBounds(R50);
		
		
		//---- LoomEx14 Button ----
		LoomEx14Button.setText("Native American Example 14");
		LoomEx14Button.addActionListener(this);
		LoomEx14Button.setBounds(R80);
		
		
		//---- Six Button ----
		SixButton.setText("Six");
		SixButton.addActionListener(this);
		SixButton.setBounds(R30);
		
		
		//---- Can You Hear Me Now Button ----
		CanYouHearMeNowButton.setText("Can You Hear Me Now?");
		CanYouHearMeNowButton.addActionListener(this);
		CanYouHearMeNowButton.setBounds(R20);
		
		//---- Starry Sky Button ----
		StarrySkyButton.setText("Starry Sky");
		StarrySkyButton.addActionListener(this);
		StarrySkyButton.setBounds(R70);
		
		//---- LoomEx6 Button ----
		LoomEx6Button.setText("Native American Example 6");
		LoomEx6Button.addActionListener(this);
		LoomEx6Button.setBounds(R90);
		
		//---- LoomEx8 Button ----
		LoomEx8Button.setText("Native American Example 8");
		LoomEx8Button.addActionListener(this);
		LoomEx8Button.setBounds(R40);
		
		//---- Circle Button ----
		CircleButton.setText("Circle");
		CircleButton.addActionListener(this);
		CircleButton.setBounds(R60);
		
		//---- Flag Button ----
		FlagButton.setText("American Flag");
		FlagButton.addActionListener(this);
		FlagButton.setBounds(R40);
		
		//---- UNCC Button ----
		UNCCButton.setText("UNC Charlotte");
		UNCCButton.addActionListener(this);
		UNCCButton.setBounds(R70);
		
		//---- LoomEx10 Button ----
		LoomEx10Button.setText("Native American Example 10");
		LoomEx10Button.addActionListener(this);
		LoomEx10Button.setBounds(R60);
		
		//---- LoomEx13 Button ----
		LoomEx13Button.setText("Native American Example 13");
		LoomEx13Button.addActionListener(this);
		LoomEx13Button.setBounds(R50);
		
		//---- Sunrise Button ----
		SunriseButton.setText("Sunrise");
		SunriseButton.addActionListener(this);
		SunriseButton.setBounds(R30);
		
		//---- Heart Button ----
		HeartButton.setText("Heart");
		HeartButton.addActionListener(this);
		HeartButton.setBounds(R20);
		
		//---- Sergeant Button ----
		SergeantButton.setText("Sergeant");
		SergeantButton.addActionListener(this);
		SergeantButton.setBounds(R80);
		
		//---- Tie Fighter Button ----
		TieFighterButton.setText("Tie Fighter");
		TieFighterButton.addActionListener(this);
		TieFighterButton.setBounds(R90);
		
		//---- Sunny Sky Button ----
		SunnySkyButton.setText("Sunny Sky");
		SunnySkyButton.addActionListener(this);
		SunnySkyButton.setBounds(R10);
		
		//---- Stars and Stripes Button ----
		StarsAndStripesButton.setText("Stars and Stripes");
		StarsAndStripesButton.addActionListener(this);
		StarsAndStripesButton.setBounds(R70);
		
		//---- ROSS Button ----
		ROSSButton.setText("ROSS the bunny");
		ROSSButton.addActionListener(this);
		ROSSButton.setBounds(R60);
		
		//---- Bullseye Button ----
		BullseyeButton.setText("Bullseye");
		BullseyeButton.addActionListener(this);
		BullseyeButton.setBounds(R40);
		
		//---- LoomEx7 Button ----
		LoomEx7Button.setText("Native American Example 7");
		LoomEx7Button.addActionListener(this);
		LoomEx7Button.setBounds(R20);
		
		//---- LoomEx1 Button ----
		LoomEx1Button.setText("Native American Example 1");
		LoomEx1Button.addActionListener(this);
		LoomEx1Button.setBounds(R50);
		
		//---- LoomEx5 Button ----
		LoomEx5Button.setText("Native American Example 5");
		LoomEx5Button.addActionListener(this);
		LoomEx5Button.setBounds(R80);
		
		//---- LoomEx3 Button ----
		LoomEx3Button.setText("Native American Example 3");
		LoomEx3Button.addActionListener(this);
		LoomEx3Button.setBounds(R90);
		
		//---- Moon Button ----
		MoonButton.setText("Moon");
		MoonButton.addActionListener(this);
		MoonButton.setBounds(R10);
		
		//---- Megaman Button ----
		MegamanButton.setText("Megaman");
		MegamanButton.addActionListener(this);
		MegamanButton.setBounds(RA0);
		
		//---- Batman Button ----
		BatmanButton.setText("Batman");
		BatmanButton.addActionListener(this);
		BatmanButton.setBounds(RB0);
		
		//---- DC Button ----
		DCButton.setText("DC");
		DCButton.addActionListener(this);
		DCButton.setBounds(R30);
		
		//---- HSLabel0 ----
		HSLabel0.setBounds(R01);
		ChoosePuzzlePanel.add(HSLabel0);
		HSLabel0.setText("Your Best Score:");
		
		//---- HSLabel1 ----
		HSLabel1.setBounds(R11);
		ChoosePuzzlePanel.add(HSLabel1);
		
		//---- HSLabel2 ----
		HSLabel2.setBounds(R21);
		ChoosePuzzlePanel.add(HSLabel2);
		
		//---- HSLabel3 ----
		HSLabel3.setBounds(R31);
		ChoosePuzzlePanel.add(HSLabel3);
		
		//---- HSLabel4 ----
		HSLabel4.setBounds(R41);
		ChoosePuzzlePanel.add(HSLabel4);
		
		//---- HSLabel5 ----
		HSLabel5.setBounds(R51);
		ChoosePuzzlePanel.add(HSLabel5);
		
		//---- HSLabel6 ----
		HSLabel6.setBounds(R61);
		ChoosePuzzlePanel.add(HSLabel6);
		
		//---- HSLabel7 ----
		HSLabel7.setBounds(R71);
		ChoosePuzzlePanel.add(HSLabel7);
		
		//---- HSLabel8 ----
		HSLabel8.setBounds(R81);
		ChoosePuzzlePanel.add(HSLabel8);
		
		//---- HSLabel9 ----
		HSLabel9.setBounds(R91);
		ChoosePuzzlePanel.add(HSLabel9);
		
		//---- HSLabelA ----
		HSLabelA.setBounds(RA1);
		ChoosePuzzlePanel.add(HSLabelA);
		
		//---- HSLabelB ----
		HSLabelB.setBounds(RB1);
		ChoosePuzzlePanel.add(HSLabelB);
		
		//---- MLabel0 ----
		MLabel0.setBounds(R02);
		ChoosePuzzlePanel.add(MLabel0);
		MLabel0.setText("Medal:");
		
		//---- MLabel1 ----
		MLabel1.setBounds(R12);
		ChoosePuzzlePanel.add(MLabel1);
		
		//---- MLabel2 ----
		MLabel2.setBounds(R22);
		ChoosePuzzlePanel.add(MLabel2);
		
		//---- MLabel3 ----
		MLabel3.setBounds(R32);
		ChoosePuzzlePanel.add(MLabel3);
		
		//---- MLabel4 ----
		MLabel4.setBounds(R42);
		ChoosePuzzlePanel.add(MLabel4);
		
		//---- MLabel5 ----
		MLabel5.setBounds(R52);
		ChoosePuzzlePanel.add(MLabel5);
		
		//---- MLabel6 ----
		MLabel6.setBounds(R62);
		ChoosePuzzlePanel.add(MLabel6);
		
		//---- MLabel7 ----
		MLabel7.setBounds(R72);
		ChoosePuzzlePanel.add(MLabel7);
		
		//---- MLabel8 ----
		MLabel8.setBounds(R82);
		ChoosePuzzlePanel.add(MLabel8);
		
		//---- MLabel9 ----
		MLabel9.setBounds(R92);
		ChoosePuzzlePanel.add(MLabel9);
		
		//---- MLabelA ----
		MLabelA.setBounds(RA2);
		ChoosePuzzlePanel.add(MLabelA);
		
		//---- MLabelA ----
		MLabelB.setBounds(RB2);
		ChoosePuzzlePanel.add(MLabelB);
		
		//---- Tutorial Button ----
		TutButton.setText("Tutorial Puzzles");
		TutButton.addActionListener(this);
		
		//---- Easy Button ----
		EasyButton.setText("Easy Puzzles");
		EasyButton.addActionListener(this);
		EasyButton.setBounds(LeftDifR);
		ChoosePuzzlePanel.add(EasyButton);
		
		//---- Medium Button ----
		MediumButton.setText("Medium Puzzles");
		MediumButton.addActionListener(this);
		MediumButton.setBounds(CenterDifR);
		ChoosePuzzlePanel.add(MediumButton);
		
		//---- Hard Button ----
		HardButton.setText("Hard Puzzles");
		HardButton.addActionListener(this);
		HardButton.setBounds(RightDifR);
		ChoosePuzzlePanel.add(HardButton);
		
		//---- Cancel Button ----
		CancelButton.setText("Cancel");
		CancelButton.addActionListener(this);
		CancelButton.setBounds(CancelR);
		ChoosePuzzlePanel.add(CancelButton);
		
		//---- Green Button ----
		GreenButton.setText("Green");
		GreenButton.addActionListener(this);
		GreenButton.setBackground(Color.GREEN);
		GreenButton.setBounds(CR10);
		ColorPanel.add(GreenButton);
		
		//---- Red Button ----
		RedButton.setText("Red");
		RedButton.addActionListener(this);
		RedButton.setBackground(Color.RED);
		RedButton.setBounds(CR00);
		ColorPanel.add(RedButton);
		
		//---- Blue Button ----
		BlueButton.setText("Blue");
		BlueButton.setForeground(Color.WHITE);
		BlueButton.addActionListener(this);
		BlueButton.setBackground(Color.BLUE);
		BlueButton.setBounds(CR20);
		ColorPanel.add(BlueButton);
		
		//---- Yellow Button ----
		YellowButton.setText("Yellow");
		YellowButton.addActionListener(this);
		YellowButton.setBackground(Color.YELLOW);
		YellowButton.setBounds(CR01);
		ColorPanel.add(YellowButton);
		
		//---- Magenta Button ----
		MagentaButton.setText("Magenta");
		MagentaButton.addActionListener(this);
		MagentaButton.setBackground(Color.MAGENTA);
		MagentaButton.setBounds(CR21);
		ColorPanel.add(MagentaButton);
		
		//---- Cyan Button ----
		CyanButton.setText("Cyan");
		CyanButton.addActionListener(this);
		CyanButton.setBackground(Color.CYAN);
		CyanButton.setBounds(CR11);
		ColorPanel.add(CyanButton);
		
		//---- Orange Button ----
		OrangeButton.setText("Orange");
		OrangeButton.addActionListener(this);
		OrangeButton.setBackground(betterOrange);
		OrangeButton.setBounds(CR02);
		ColorPanel.add(OrangeButton);
		
		//---- Pink Button ----
		PinkButton.setText("Pink");
		PinkButton.addActionListener(this);
		PinkButton.setBackground(Color.PINK);
		PinkButton.setBounds(CR12);
		ColorPanel.add(PinkButton);
		
		//---- Peach Button ----
		PeachButton.setText("Peach");
		PeachButton.addActionListener(this);
		PeachButton.setBackground(peach);
		PeachButton.setBounds(CR22);
		ColorPanel.add(PeachButton);
		
		//-----Gray Button ----
		GrayButton.setText("Gray");
		GrayButton.addActionListener(this);
		GrayButton.setBackground(Color.GRAY);
		GrayButton.setBounds(CR23);
		ColorPanel.add(GrayButton);
		
		//---- Black Button ----
		BlackButton.setText("Black");
		BlackButton.setForeground(Color.WHITE);
		BlackButton.addActionListener(this);
		BlackButton.setBackground(Color.BLACK);
		BlackButton.setBounds(CR03);
		ColorPanel.add(BlackButton);
		
		//---- White Button ----
		WhiteButton.setText("White");
		WhiteButton.addActionListener(this);
		WhiteButton.setBackground(Color.WHITE);
		WhiteButton.setBounds(CR13);
		ColorPanel.add(WhiteButton);
		
		//---- High Scores Button ----
		HighScoresButton.setText("High Scores");
		HighScoresButton.setBounds(25, 25, 145, HighScoresButton.getPreferredSize().height);
		HighScoresButton.addActionListener(this);
		
		//---- choose puzzle button ----
		ChoosePuzzleButton.setText("Choose Puzzle");
		ChoosePuzzleButton.setBounds(25, 50, 145, ChoosePuzzleButton.getPreferredSize().height);
		ChoosePuzzleButton.addActionListener(this);

		//---- restart button ----
		RestartButton.setText("Restart");
		RestartButton.setBounds(25, 75, 145, RestartButton.getPreferredSize().height);
		RestartButton.addActionListener(this);
		
		//---- Submit button ----
		SubmitButton.setText("Submit");
		SubmitButton.setBounds(25, 100, 145, SubmitButton.getPreferredSize().height);
		SubmitButton.setBackground(Color.RED);
		SubmitButton.addActionListener(this);
		
		//---- Options button ---
		GameOptionsButton.setText("Options");
		GameOptionsButton.setBounds(25, 125, 145, GameOptionsButton.getPreferredSize().height);
		GameOptionsButton.addActionListener(this);
		
		//---- Quit button ----
		QuitButton.setText("Quit");
		QuitButton.setBounds(25, 150, 145, QuitButton.getPreferredSize().height);
		QuitButton.addActionListener(this);
		
		//---- Play Game button ----
		PlayGameButton.setText("Play Game");
		GamePanel.add(PlayGameButton);
		PlayGameButton.setBounds(25, 75, 145, PlayGameButton.getPreferredSize().height);
		PlayGameButton.addActionListener(this);
		
		//---- Name Text Field ----
		NameTextField.setText("Enter Your Name");
		GamePanel.add(NameTextField);
		NameTextField.setBounds(25, 50, 145, 25);
		NameTextField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(MouseEvent me) {
            	if(NameTextField.getText().equals("Enter Your Name"))
            	{
            		NameTextField.selectAll();
            	}
            }
        });

		NameTextField.addActionListener(this);
		
		//---- Color Blind Button ----
		ColorBlindButton.setText("Color Blind Mode Off");
		ColorBlindButton.setBounds(20, 25, 155, ColorBlindButton.getPreferredSize().height);
		ColorBlindButton.addActionListener(this);
		GameOptionsPanel.add(ColorBlindButton);
		
		//---- Hints Button ----
		HintsButton.setText("Hints Off");
		HintsButton.setBounds(20, 50, 155, HintsButton.getPreferredSize().height);
		HintsButton.addActionListener(this);
		GameOptionsPanel.add(HintsButton);
		
		//---- Normal Bead Button ----
		NormalBeadButton.setText("Normal Bead");
		NormalBeadButton.setBounds(20, 100, 155, NormalBeadButton.getPreferredSize().height);
		NormalBeadButton.setBackground(Color.CYAN);
		NormalBeadButton.addActionListener(this);
		GameOptionsPanel.add(NormalBeadButton);
		
		//---- Awesome Bead Button ----
		AwesomeBeadButton.setText("Awesome Bead");
		AwesomeBeadButton.setBounds(20, 125, 155, AwesomeBeadButton.getPreferredSize().height);
		AwesomeBeadButton.addActionListener(this);
		GameOptionsPanel.add(AwesomeBeadButton);
		
		//---- Peace Bead Button ----
		PeaceBeadButton.setText("Peace Bead");
		PeaceBeadButton.setBounds(20, 150, 155, PeaceBeadButton.getPreferredSize().height);
		PeaceBeadButton.addActionListener(this);
		GameOptionsPanel.add(PeaceBeadButton);
		
		//---- Peace Bead Button ----
		GameOptionsCloseButton.setText("Close");
		GameOptionsCloseButton.setBounds(20, 175, 155, GameOptionsCloseButton.getPreferredSize().height);
		GameOptionsCloseButton.addActionListener(this);
		GameOptionsPanel.add(GameOptionsCloseButton);
		
		//---- Name Label ----
		NameLabel.setBounds(25, 0, 200, 25);
		
		//---- Best Score Label ----
		BestScoreLabel.setBounds(25, 175, 200, 25);
		
		GamePanel.setBounds(4, 3, 189, 155);
		
		//---- Web Request Button ----
		WebRequestButton.setText("Sumbit Web Request");
		WebRequestTextField.setAlignmentX(Component.CENTER_ALIGNMENT);
		WebRequestButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		HighScoresPanel.add(WebRequestTextField);
		HighScoresPanel.add(WebRequestButton);
		WebRequestButton.addActionListener(this);
		
		//---- High Scores Label ----
		HighScoresLabel.setText("High Scores: Acey has 1000 points");
		HighScoresLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		HighScoresPanel.add(HighScoresLabel);
		HighScoresLabel.setMaximumSize(HighScoresPanel.getMaximumSize());
	}
		
    	public void incrementMove(){
    		moveCounter++;
    		MoveLabel.setText("Total Actions: " + moveCounter);
    	}
    	
    	//Clears Grids
    	//Clears grid 1 if clear1 is true
    	//Clears grid 2 if clear2 is true
    	//Logs the restart if log is true (only doesn't log during start and when not in game)
    	public void Restart(Boolean clear1, Boolean clear2, Boolean dolog){
    		puzzleStartTime = System.currentTimeMillis();
    		moveCounter = 0;
    		MoveLabel.setText("Total Actions: " + moveCounter);
    		if (clear2){
    			bl.getGridPanel2().clear();
    		}
    		if (clear1){
    			bl.getGridPanel().clear();
    		}
    		
    		if (dolog){
    		//Log the Restart
    		try{
 				FileWriter log = new FileWriter("log.txt", true);
 				log.write("\t\t" + getTime() + " Restart Performed\n");
 				log.close();
 				}catch (Exception ex){//Catch exception if any
 				      System.err.println("Error: " + ex.getMessage());
 				}
    		}
    	}
    	
    	public void setMoveLabel(JLabel toSet){
    		MoveLabel = toSet;
    	}
    	
    	public String getTime(){
    		Calendar cal = new GregorianCalendar();
    		int year = cal.get(Calendar.YEAR);             // 2002
    	    int month = cal.get(Calendar.MONTH);           // 0=Jan, 1=Feb, ...
    	    month++;									   //1 = Jan, 2=Feb, ...
    	    int day = cal.get(Calendar.DAY_OF_MONTH);      // 1...
    	    int hour24 = cal.get(Calendar.HOUR_OF_DAY);     // 0..23
    	    int min = cal.get(Calendar.MINUTE);             // 0..59
    	    int sec = cal.get(Calendar.SECOND);             // 0..59
    	    
    	    String time = "<" + month + "/" + day + "/" + year + " " + hour24 + ":" + min + ":" + sec + ">";
    	    return time;
    	}
    	
    	//Removes all the buttons from the Choose Puzzle Panel
    	private void removePuzzleButtons(){
    		ChoosePuzzlePanel.remove(Tut1Button);
    		ChoosePuzzlePanel.remove(Tut2Button);
    		ChoosePuzzlePanel.remove(Tut3Button);
    		ChoosePuzzlePanel.remove(Tut4Button);
    		ChoosePuzzlePanel.remove(Tut5Button);
    		ChoosePuzzlePanel.remove(Tut6Button);
    		ChoosePuzzlePanel.remove(TriforceButton);
    		ChoosePuzzlePanel.remove(OverlappingSquaresButton);
    		ChoosePuzzlePanel.remove(LoomEx14Button);
    		ChoosePuzzlePanel.remove(SixButton);
    		ChoosePuzzlePanel.remove(CanYouHearMeNowButton);
    		ChoosePuzzlePanel.remove(StarrySkyButton);
    		ChoosePuzzlePanel.remove(LoomEx6Button);
    		ChoosePuzzlePanel.remove(LoomEx8Button);
    		ChoosePuzzlePanel.remove(CircleButton);
    		ChoosePuzzlePanel.remove(FlagButton);
    		ChoosePuzzlePanel.remove(UNCCButton);
    		ChoosePuzzlePanel.remove(DCButton);
    		ChoosePuzzlePanel.remove(LoomEx10Button);
    		ChoosePuzzlePanel.remove(LoomEx13Button);
    		ChoosePuzzlePanel.remove(SunriseButton);
    		ChoosePuzzlePanel.remove(HeartButton);
    		ChoosePuzzlePanel.remove(SunnySkyButton);
    		ChoosePuzzlePanel.remove(SergeantButton);
    		ChoosePuzzlePanel.remove(TieFighterButton);
    		ChoosePuzzlePanel.remove(StarsAndStripesButton);
    		ChoosePuzzlePanel.remove(ROSSButton);
    		ChoosePuzzlePanel.remove(BullseyeButton);
    		ChoosePuzzlePanel.remove(LoomEx7Button);
    		ChoosePuzzlePanel.remove(LoomEx1Button);
    		ChoosePuzzlePanel.remove(LoomEx5Button);
    		ChoosePuzzlePanel.remove(LoomEx3Button);
    		ChoosePuzzlePanel.remove(MoonButton);
    		ChoosePuzzlePanel.remove(MegamanButton);
    		ChoosePuzzlePanel.remove(BatmanButton);
    		//Refreshes Panel
    		ChoosePuzzlePanel.setVisible(false);
    		ChoosePuzzlePanel.setVisible(true);
    	}
    	
    	//Changes the layout of the box for playing the game
    	//Input: Players name for log in purposes
    	public void startGame(){
    		//Adjust BeadLoom
    		bl.getContentPanel().add(bl.getGridFrame2());
			bl.getContentPanel().remove(bl.getGoalImagesFrame());
			bl.getContentPanel().remove(bl.getMoveBeadsFrame());
			bl.getGridFrame().setBounds((int)(BeadLoom.usableDim.getWidth()*0.5) + BeadLoom.panelBorder, BeadLoom.panelBorder, (int)(BeadLoom.usableDim.getWidth()*0.5) - BeadLoom.panelBorder,(int)(BeadLoom.usableDim.getHeight()*0.65)-BeadLoom.panelBorder);
			bl.getGridFrame2().setBounds(BeadLoom.panelBorder, BeadLoom.panelBorder, (int)(BeadLoom.usableDim.getWidth()*0.5) -BeadLoom.panelBorder,(int)(BeadLoom.usableDim.getHeight()*0.65)-BeadLoom.panelBorder);
			bl.getOutputWindow().setBounds(BeadLoom.panelBorder, (int)(BeadLoom.usableDim.getHeight()*0.65) + BeadLoom.panelBorder, (int)(BeadLoom.usableDim.getWidth()*0.37) - BeadLoom.panelBorder, (int)(BeadLoom.usableDim.getHeight()*0.35) - BeadLoom.panelBorder);
			bl.getGameFrame().setBounds((int)(BeadLoom.usableDim.getWidth()*0.37) + BeadLoom.panelBorder, (int)(BeadLoom.usableDim.getHeight()*0.65) + BeadLoom.panelBorder, (int)(BeadLoom.usableDim.getWidth()*0.20) - BeadLoom.panelBorder, (int)(BeadLoom.usableDim.getHeight()*0.35) - BeadLoom.panelBorder);
			bl.getBeadUtilitiesFrame().setBounds((int)(BeadLoom.usableDim.getWidth()*0.57) + BeadLoom.panelBorder, (int)(BeadLoom.usableDim.getHeight()*0.65) + BeadLoom.panelBorder, (int)(BeadLoom.usableDim.getWidth()*0.43) - BeadLoom.panelBorder, (int)(BeadLoom.usableDim.getHeight()*0.35) - BeadLoom.panelBorder);
			bl.getTop().getFileMenu().setEnabled(false);
			bl.getTop().getOptionsMenu().setEnabled(false);
			bl.getTop().getHelpMenu().setEnabled(false);
			bl.getInputTools().getBeadLoomUtilitiesTabbedPane().remove(bl.getInputTools().getLayersDrawTabbedPane());
			bl.getInputTools().getBeadLoomUtilitiesTabbedPane().remove(bl.getInputTools().getTrigFunctionsTabbedPane());
    		bl.getInputTools().removeLoopTools();
    		bl.setGridSize(40);
    		bl.setGridSize2(40);
			
			
			//Adjust Game GUI
    		playerName = NameTextField.getText();
    		GamePanel.add(ChoosePuzzleButton);
    		GamePanel.add(RestartButton);
    		GamePanel.add(SubmitButton);
    		GamePanel.add(GameOptionsButton);
    		GamePanel.add(QuitButton);
    		GamePanel.add(NameLabel);
    		GamePanel.add(BestScoreLabel);
    		GamePanel.add(HighScoresButton);
    		GamePanel.remove(PlayGameButton);
    		GamePanel.remove(NameTextField);
    		NameLabel.setText(playerName);
    		BestScoreLabel.setText("Best Score:" + bestScore);
    		
    		//Restart Game to reset move counter and clear grid
    		Restart(true, true, false);
    		
    		//Load Default Puzzle
    		currentPuzzle = puz.setLoomEx8();
    		
    		//Load Player Data
    		try{
    		FileReader fr = new FileReader("Scores.txt"); 
    		BufferedReader br = new BufferedReader(fr); 
    		String s; 
    		s = br.readLine();
    		//First Line is total number of entries
    		totalEntries = Integer.parseInt(s);
    		int lineCounter = 0;
    		//Store the file in an array for later storing and editing
    		//Size is 3 bigger than needed in case the player is a new player and their data needs to be added
    		scoreFile = new String[((totalEntries+1)*3) + 1];
    		scoreFile[lineCounter] = ""+totalEntries;
    		lineCounter++;
    		boolean exists = false;
    		playerIndex = 0;
    		for (int i = 0; i < totalEntries; i++){
    			s = br.readLine();
    			scoreFile[lineCounter] = s;
    			lineCounter++;
    			if (!exists){
    				playerIndex++;
    				if (s.equals(playerName)){
    					exists = true;
    				}
    			}
    			
    		}
    		//If its a new player add a new entry for them
    		if (!exists){
    			scoreFile[lineCounter] = playerName;
    			lineCounter++;
    			totalEntries++;
    			scoreFile[0] = ""+totalEntries;
    			playerIndex++;
    		}
    		
    		//Read the rest of the file for writing purposes
    		while ((s = br.readLine()) != null){
    			scoreFile[lineCounter] = s;
    			lineCounter++;
    		}
    		
    		//If new player add in default scores for them
    		if (!exists){
    		scoreFile[lineCounter] = playerName + " 1682 1682 1682 1682 1682 1682" +
    				                             " 1682 1682 1682 1682 1682 1682" +
    				                             " 1682 1682 1682 1682 1682 1682" +
    				                             " 1682 1682 1682 1682 1682 1682" +
    				                             " 1682 1682 1682 1682 1682 1682 1682 1682 1682 1682 1682";
    		lineCounter++;
    		scoreFile[lineCounter] = playerName + " N N N N N N N N N N N N N N N N N N N N N N N N N N N N N N N N N N N";
    		}
    		
    		StringTokenizer st = new StringTokenizer(scoreFile[playerIndex + playerIndex + totalEntries -1]);
    			st.nextToken();
    			for (int j = 0; j < puz.getTotalPuzzles(); j++){
    				RecordMove[j] = Integer.parseInt(st.nextToken());
    			}
    			st = new StringTokenizer(scoreFile[playerIndex + playerIndex + totalEntries]);
    			st.nextToken();
    			for (int j = 0; j < puz.getTotalPuzzles(); j++){
    				RecordMedalShort[j] = st.nextToken();
    				if(RecordMedalShort[j].equals("B")){
    					RecordMedal[j] = "Bronze!";
    				}
    				else if(RecordMedalShort[j].equals("N")){
    					RecordMedal[j] = "-None-";
    				}
    				else if(RecordMedalShort[j].equals("S")){
    					RecordMedal[j] = "Silver!!";
    				}
    				else if(RecordMedalShort[j].equals("G")){
    					RecordMedal[j] = "Gold!!!";
    				}
    				else{
    					RecordMedal[j] = "Platinum!!!!!";    					
    				}
    			}
    			
    		
    		fr.close();
    		}
    		catch (Exception e)
            {   System.err.println("File input error: " + e.toString());
            }
    		
    		//Set Tutorial Scores to be displayed
    		HSLabel1.setText("" + this.RecordMove[0]);
			MLabel1.setText(RecordMedal[0]);
			
			HSLabel2.setText("" + this.RecordMove[1]);
			MLabel2.setText(RecordMedal[1]);
			
			HSLabel3.setText("" + this.RecordMove[2]);
			MLabel3.setText(RecordMedal[2]);
			
			HSLabel4.setText("" + this.RecordMove[3]);
			MLabel4.setText(RecordMedal[3]);
			
			HSLabel5.setText("" + this.RecordMove[4]);
			MLabel5.setText(RecordMedal[4]);
			
			HSLabel6.setText("" + this.RecordMove[5]);
			MLabel6.setText(RecordMedal[5]);
			
			//Log the Log in and initial puzzle
			   try{
				    FileWriter log = new FileWriter("log.txt", true);
				    log.write(getTime() + " " + playerName + " logs in.\n");
				    log.write("\t" + getTime() + " Loom Example 8 Puzzle Selected.\n");
				    log.close();
				    }catch (Exception e){//Catch exception if any
				      System.err.println("Error: " + e.getMessage());
				    }
    	}
    	
    	//Changes the layout of the box free play
    	public void quitGame(){
    		//Adjust BeadLoom
    		bl.getContentPanel().remove(bl.getGridFrame2());
    		bl.getContentPanel().remove(bl.getGameOptionsFrame());
    		bl.getContentPanel().add(bl.getGoalImagesFrame());
    		bl.getContentPanel().add(bl.getMoveBeadsFrame());
    		bl.getGridFrame().setBounds((int)(BeadLoom.usableDim.getWidth()*0.42)+BeadLoom.panelBorder, BeadLoom.panelBorder, (int)(BeadLoom.usableDim.getWidth()*0.58)-BeadLoom.panelBorder,(int)(BeadLoom.usableDim.getHeight()*0.70)-BeadLoom.panelBorder);
    		bl.getOutputWindow().setBounds(BeadLoom.panelBorder, (int)(BeadLoom.usableDim.getHeight()*0.65)+BeadLoom.panelBorder, (int)(BeadLoom.usableDim.getWidth()*0.42)-BeadLoom.panelBorder, (int)(BeadLoom.usableDim.getHeight()*0.35)-BeadLoom.panelBorder);
    		bl.getBeadUtilitiesFrame().setBounds(BeadLoom.panelBorder, (int)(BeadLoom.usableDim.getHeight()*0.27)+BeadLoom.panelBorder, (int)(BeadLoom.usableDim.getWidth()*0.42)-BeadLoom.panelBorder, (int)(BeadLoom.usableDim.getHeight()*0.38)-BeadLoom.panelBorder);
    		bl.getGameFrame().setBounds((int)(BeadLoom.usableDim.getWidth()*0.42)+BeadLoom.panelBorder, (int)(BeadLoom.usableDim.getHeight()*.70)+BeadLoom.panelBorder, (int)(BeadLoom.usableDim.getWidth()*0.58)-BeadLoom.panelBorder, (int)(BeadLoom.usableDim.getHeight()*0.30)-BeadLoom.panelBorder);
    		bl.getTop().getFileMenu().setEnabled(true);
			bl.getTop().getOptionsMenu().setEnabled(true);
			bl.getTop().getHelpMenu().setEnabled(true);
			bl.getInputTools().getBeadLoomUtilitiesTabbedPane().addTab("Layers Tool", bl.getInputTools().getLayersDrawTabbedPane());
			bl.getInputTools().getBeadLoomUtilitiesTabbedPane().addTab("Trig Functions Tool", bl.getInputTools().getTrigFunctionsTabbedPane());
			bl.getPuzzleFrame().setVisible(false);
			bl.getColorFrame().setVisible(false);
			bl.getInputTools().addLoopTools();
			
			
    		//Adjust Game GUI
    		GamePanel.remove(ChoosePuzzleButton);
    		GamePanel.remove(RestartButton);
    		GamePanel.remove(SubmitButton);
    		GamePanel.remove(QuitButton);
    		GamePanel.remove(NameLabel);
    		GamePanel.remove(BestScoreLabel);
    		GamePanel.remove(HighScoresButton);
    		GamePanel.remove(GameOptionsButton);
    		GamePanel.add(PlayGameButton);
    		GamePanel.add(NameTextField);
    		NameTextField.setText("Enter Your Name");
    		
    		//Log qutting
			   try{
				    FileWriter log = new FileWriter("log.txt", true);
				    log.write(getTime() + " " + playerName + " logs out.\n");
				    log.close();
				    }catch (Exception e){//Catch exception if any
				      System.err.println("Error: " + e.getMessage());
				    }
    	}
    	
    	public void checkSolution(){    		
    		int errorX[] = new int[41*41];
    		int errorY[] = new int[41*41];
    		bl.getGridPanel().calcGameGrid();
    		int totalErrors = 0;
    		for(int y = 0; y < 41; y++){
        		for (int x = 0; x < 41; x++){
        			if(!bl.getGridPanel().getColorAt(x, y).equals(bl.getGridPanel2().getColorAt(x, y))){
        				errorX[totalErrors] = x-20;
    					errorY[totalErrors] = y-20;
    					totalErrors++; 
        			}
        		}
    		}
				    
    		if(totalErrors == 0){
    			float puzzleTime = (float)(System.currentTimeMillis()- puzzleStartTime)/1000.0f;
    			
    			String completeTime = (((int)puzzleTime)/60)+" minute(s) and "+puzzleTime%60;
    			String seconds = "";
    			String message = "";
    			if(((int)puzzleTime % 60) < 10)
    			{
    				seconds = "0" + ((int)(puzzleTime % 60));
    			}
    			else
    			{
    				seconds = ((int)(puzzleTime % 60)) + "";
    			}
    			
    			String urlTime = ((int)puzzleTime/60) + ":" + seconds;
    			JOptionPane.showMessageDialog(null, "CONGRATULATIONS\nPuzzle Solved in " + getMoveCount(), "Congratulations", JOptionPane.PLAIN_MESSAGE);
    			String medal;
    			if (moveCounter <= puz.getIdeal()){
    				JOptionPane.showMessageDialog(null, "You Earned the Platinum Medal.  You completed the puzzle in "+completeTime+" second(s).", "Congratulations", JOptionPane.PLAIN_MESSAGE);
    				medal = "Platinum!!!!";
    				RecordMedalShort[currentPuzzle] = "P";
    			}
    			else if (moveCounter <= puz.getIdeal()*2){
    				JOptionPane.showMessageDialog(null, "You Earned the Gold Medal.  You completed the puzzle in "+completeTime+" second(s).", "Congratulations", JOptionPane.PLAIN_MESSAGE);
    				medal = "Gold!!!";
    				RecordMedalShort[currentPuzzle] = "G";
    			}
    			else if (moveCounter <= puz.getIdeal()*3){
    				JOptionPane.showMessageDialog(null, "You Earned the Silver Medal.  You completed the puzzle in "+completeTime+" second(s).", "Congratulations", JOptionPane.PLAIN_MESSAGE);
    				medal = "Silver!!";
    				RecordMedalShort[currentPuzzle] = "S";
    			}
    			else{
    				JOptionPane.showMessageDialog(null, "You Earned the Bronze Medal.  You completed the puzzle in "+completeTime+" second(s).", "Congratulations", JOptionPane.PLAIN_MESSAGE); 				  
    				medal = "Bronze!";
    				RecordMedalShort[currentPuzzle] = "B";
    			}
    			
    			//Send this medal to the website
				try {
					message = sendWebRequest("http://playground.uncc.edu/BeadLoomGame/enterScores.php?" +
							"user=" + URLEncoder.encode(NameLabel.getText(), "UTF-8") + 
							"&score=" + URLEncoder.encode((getMoveCount() + ""), "UTF-8") + 
							"&time=" + URLEncoder.encode(urlTime, "UTF-8")  + 
							"&medal=" + URLEncoder.encode(medal, "UTF-8") + 
							"&puzzle=" + URLEncoder.encode(puz.getPuzzleName(currentPuzzle), "UTF-8"));
					JOptionPane.showMessageDialog(null, message, "High Scores messages", JOptionPane.PLAIN_MESSAGE);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
    			//Log the Results
				   try{
					    FileWriter log = new FileWriter("log.txt", true);
					    log.write("\t\t" + getTime() + " Checked Solution.\n");
					    log.write("\t\t\t" + getTime() + " Puzzle Completed in " + moveCounter + "moves.  Earned "+medal+"\n");
					    log.close();
					    }catch (Exception e){//Catch exception if any
					      System.err.println("Error: " + e.getMessage());
					    }
					    //IF new best high score
					    //Log new high score
					    //First in Array then in file
					    if (getMoveCount() <RecordMove[currentPuzzle]){
					    	RecordMove[currentPuzzle] = getMoveCount();
					    	RecordMedal[currentPuzzle] = medal;
					    	String move = playerName;
					    	String med = playerName;
					    	for (int i = 0; i < puz.getTotalPuzzles(); i++){
					    		move = move + " " + RecordMove[i];
					    		med = med + " " + RecordMedalShort[i];
					    	}
					    	scoreFile[playerIndex+playerIndex+totalEntries-1] = move;
					    	scoreFile[playerIndex+playerIndex+totalEntries] = med;
					    	try{
					    		FileWriter log = new FileWriter("Scores.txt", false);
					    		int i = 0;
					    		System.out.println(scoreFile[i]);
					    		log.write(scoreFile[i]);
					    		i++;
					    		while (i < (totalEntries*3) +1){
					    			log.write("\n" + scoreFile[i]);
					    			i++;
					    		}
					    		log.close();
						    	}catch (Exception e){//Catch exception if any
						    		System.err.println("Error: " + e.getMessage());
						    }
					    }
    		}
    		else if(totalErrors > 10) {
    			JOptionPane.showMessageDialog(null, "Incorrect: \n Total Errors: "+ totalErrors, "Sorry", JOptionPane.PLAIN_MESSAGE);
    			//Log the Results
				   try{
					    FileWriter log = new FileWriter("log.txt", true);
					    log.write("\t\t" + getTime() + " Checked Solution.\n");
					    log.write("\t\t\t" + getTime() + " Puzzle Incomplete. Errors: " + totalErrors + "\n");
					    log.close();
					    }catch (Exception e){//Catch exception if any
					      System.err.println("Error: " + e.getMessage());
					    }
    		}
    		else{
    			String Output = "Incorrect: \n Total Errors: " + totalErrors + "\n";
    			for(int i = 0; i < totalErrors; i++){
    				Output = Output + "(" + errorX[i] + ", " + errorY[i] + ")\n";
    			}
    			JOptionPane.showMessageDialog(null, Output, "Sorry", JOptionPane.PLAIN_MESSAGE);
    			//Log the Results
				   try{
					    FileWriter log = new FileWriter("log.txt", true);
					    log.write("\t\t" + getTime() + " Checked Solution.\n");
					    log.write("\t\t\t" + getTime() + " Puzzle Incomplete. Errors: " + totalErrors + "\n");
					    log.close();
					    }catch (Exception e){//Catch exception if any
					      System.err.println("Error: " + e.getMessage());
					    }
    		}
    	}
	
	public void setLoom(BeadLoom toSet){
		bl = toSet;
		puz = new Puzzle(toSet);
		RecordMove = new int[puz.getTotalPuzzles()];
		RecordMedal = new String[puz.getTotalPuzzles()];
		RecordMedalShort = new String[puz.getTotalPuzzles()];
	}
    	
    public Color getColor()
	{
		return color;
	}
	public void setColor(Color c)
	{
		color = c;
	}
	//Gets
	public JPanel getGamePanel() {
		return GamePanel;
	}
	public JPanel getHighScoresPanel() {
		return HighScoresPanel;
	}
	public JPanel getGameOptionsPanel() {
		return GameOptionsPanel;
	}
	public JPanel getChoosePuzzlePanel(){
		return ChoosePuzzlePanel;
	}
	public JPanel getColorPanel(){
		return ColorPanel;
	}
	public JButton getChoosePuzzleButton() {
		return ChoosePuzzleButton;
	}
	public JButton getRestartButton() {
		return RestartButton;
	}
	public JButton getSubmitButton() {
		return SubmitButton;
	}
	public JButton getQuitButton() {
		return QuitButton;
	}
	public JButton getPlayGameButton(){
		return PlayGameButton;
	}
	public JTextField getNameTextField(){
		return NameTextField;
	}
	
	public int getMoveCount(){
		return moveCounter;
	}
	
	public String sendWebRequest(String url) {
		return sendWebRequest(url, "token");
	}
	
	public String sendWebRequest(String url, String token) {
		StringBuilder builder = new StringBuilder(); 
		try {
				URL test = new URL(url);
				    URLConnection con = test.openConnection();
				    BufferedReader in = new BufferedReader(
				                            new InputStreamReader(
				                            con.getInputStream()));
				    String inputLine;
				    

				    while ((inputLine = in.readLine()) != null)  {
				    	
				    	builder.append(inputLine);
				    }
				    in.close();
			} catch (Exception ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
				return builder.toString();
			}
			return builder.toString();
	}
	
	//Action Listener
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()== PlayGameButton || e.getSource() == NameTextField) {
    		if (NameTextField.getText().equals("Enter Your Name") || NameTextField.getText().equals("")){
    			JOptionPane.showMessageDialog(null, "Please Enter Your Name", "Name Error", JOptionPane.PLAIN_MESSAGE);
    		}
    		else{
    			startGame();
    		}
		}
		
		else if (e.getSource() == WebRequestButton) {
			HighScoresLabel.setText(
					sendWebRequest("http://playground.uncc.edu/BeadLoomGame/scores.php"));
			
		}
		
		else if (e.getSource() == NormalBeadButton) {
			BeadLoom.beadLocation = "bead.JPG";
			redrawGrid();
			NormalBeadButton.setBackground(Color.CYAN);
			AwesomeBeadButton.setBackground(UIManager.getColor("Button.background"));
			PeaceBeadButton.setBackground(UIManager.getColor("Button.background"));
		}
		
		else if (e.getSource() == AwesomeBeadButton) {
			BeadLoom.beadLocation = "beadAwesome.JPG";
			redrawGrid();
			NormalBeadButton.setBackground(UIManager.getColor("Button.background"));
			AwesomeBeadButton.setBackground(Color.CYAN);
			PeaceBeadButton.setBackground(UIManager.getColor("Button.background"));
		}
		
		else if (e.getSource() == PeaceBeadButton) {
			BeadLoom.beadLocation = "beadPeace.JPG";
			redrawGrid();
			NormalBeadButton.setBackground(UIManager.getColor("Button.background"));
			AwesomeBeadButton.setBackground(UIManager.getColor("Button.background"));
			PeaceBeadButton.setBackground(Color.CYAN);
		}
		
		else if (e.getSource() == ColorBlindButton) {
			bl.toggleColorBlindMode();
			if(ColorBlindButton.getText().equals("Color Blind Mode On"))
			{
				ColorBlindButton.setText("Color Blind Mode Off");
			}
			else
			{
				ColorBlindButton.setText("Color Blind Mode On");
			}
		}
		
		else if (e.getSource() == HintsButton) {
			if(HintsButton.getText().equals("Hints On"))
			{
				HintsButton.setText("Hints Off");
				hintLevel = 0;
			}
			else
			{
				HintsButton.setText("Hints On");
				hintLevel = 1;
			}
		}
		
		else if (e.getSource() == GameOptionsCloseButton) {
			bl.getGameOptionsFrame().setVisible(false);
		}
		
		else if (e.getSource() == HighScoresButton) {
			bl.getHighScoresFrame().setVisible(!bl.getHighScoresFrame().isVisible());
			bl.getHighScoresFrame().toFront();
			bl.getHighScoresFrame().setBounds(bl.getGridFrame2().getBounds());
		}
		
		else if (e.getSource() == GameOptionsButton) {
			bl.getGameOptionsFrame().setVisible(!bl.getGameOptionsFrame().isVisible());
		}
		
		else if (e.getSource()== SubmitButton) {
    		checkSolution();
    	}
		
		else if (e.getSource()== QuitButton) {
    		quitGame();
    	}
		else if (e.getSource()== RestartButton) {
    		int choice = JOptionPane.showConfirmDialog(null, "Are you sure? This will remove ALL beads from the grid!", "Beadloom", JOptionPane.YES_NO_OPTION);
    		if (choice == 0){
    			Restart(true, false, true);
    		}
    	}
		else if (e.getSource()== ChoosePuzzleButton){			
			bl.getPuzzleFrame().setVisible(true);
			bl.getPuzzleFrame().toFront();
			//Set up the Tutorial Menu
			EasyButton.setBounds(LeftDifR);
			ChoosePuzzlePanel.add(EasyButton);
			MediumButton.setBounds(CenterDifR);
			ChoosePuzzlePanel.add(MediumButton);
			HardButton.setBounds(RightDifR);
			ChoosePuzzlePanel.add(HardButton);
			ChoosePuzzlePanel.remove(TutButton);
			removePuzzleButtons();
			
			ChoosePuzzlePanel.add(Tut1Button);
			ChoosePuzzlePanel.add(Tut2Button);
			ChoosePuzzlePanel.add(Tut3Button);
			ChoosePuzzlePanel.add(Tut4Button);
			ChoosePuzzlePanel.add(Tut5Button);
			ChoosePuzzlePanel.add(Tut6Button);
			
			HSLabel1.setText("" + RecordMove[0]);
			MLabel1.setText(RecordMedal[0]);
			
			HSLabel2.setText("" + RecordMove[1]);
			MLabel2.setText(RecordMedal[1]);
			
			HSLabel3.setText("" + RecordMove[2]);
			MLabel3.setText(RecordMedal[2]);
			
			HSLabel4.setText("" + RecordMove[3]);
			MLabel4.setText(RecordMedal[3]);
			
			HSLabel5.setText("" + RecordMove[4]);
			MLabel5.setText(RecordMedal[4]);
			
			HSLabel6.setText("" + RecordMove[5]);
			MLabel6.setText(RecordMedal[5]);
			
			HSLabel7.setText(" ");
			MLabel7.setText(" ");
			
			HSLabel8.setText(" ");
			MLabel8.setText(" ");
			
			HSLabel9.setText(" ");
			MLabel9.setText(" ");
			
			HSLabelA.setText(" ");
			MLabelA.setText(" ");	
			
			HSLabelB.setText(" ");
			MLabelB.setText(" ");
		}
		
		else if (e.getSource() == Tut1Button){
			Restart(true, true, true);
			currentPuzzle = puz.setTut1();
    		bestScore = RecordMove[currentPuzzle];
    		BestScoreLabel.setText("Best Score:" + bestScore);
			bl.getPuzzleFrame().setVisible(false);
			//Log the Puzzle Change
			   try{
				    FileWriter log = new FileWriter("log.txt", true);
				    log.write("\t" + getTime() + " Tutorial 1 Puzzle Selected.\n");
				    log.close();
				    }catch (Exception ex){//Catch exception if any
				      System.err.println("Error: " + ex.getMessage());
				    }
		}
		
		else if (e.getSource() == Tut2Button){
			Restart(true, true, true);
			currentPuzzle = puz.setTut2();
    		bestScore = RecordMove[currentPuzzle];
    		BestScoreLabel.setText("Best Score:" + bestScore);
			bl.getPuzzleFrame().setVisible(false);
			//Log the Puzzle Change
			   try{
				    FileWriter log = new FileWriter("log.txt", true);
				    log.write("\t" + getTime() + " Tutorial 2 Puzzle Selected.\n");
				    log.close();
				    }catch (Exception ex){//Catch exception if any
				      System.err.println("Error: " + ex.getMessage());
				    }
		}
		else if (e.getSource() == Tut3Button){
			Restart(true, true, true);
			currentPuzzle = puz.setTut3();
    		bestScore = RecordMove[currentPuzzle];
    		BestScoreLabel.setText("Best Score:" + bestScore);
			bl.getPuzzleFrame().setVisible(false);
			//Log the Puzzle Change
			   try{
				    FileWriter log = new FileWriter("log.txt", true);
				    log.write("\t" + getTime() + " Tutorial 3 Puzzle Selected.\n");
				    log.close();
				    }catch (Exception ex){//Catch exception if any
				      System.err.println("Error: " + ex.getMessage());
				    }
		}
		else if (e.getSource() == Tut4Button){
			Restart(true, true, true);
			currentPuzzle =puz.setTut4();
    		bestScore = RecordMove[currentPuzzle];
    		BestScoreLabel.setText("Best Score:" + bestScore);
			bl.getPuzzleFrame().setVisible(false);
			//Log the Puzzle Change
			   try{
				    FileWriter log = new FileWriter("log.txt", true);
				    log.write("\t" + getTime() + " Tutorial 4 Puzzle Selected.\n");
				    log.close();
				    }catch (Exception ex){//Catch exception if any
				      System.err.println("Error: " + ex.getMessage());
				    }
		}
		else if (e.getSource() == Tut5Button){
			Restart(true, true, true);
			currentPuzzle =puz.setTut5();
    		bestScore = RecordMove[currentPuzzle];
    		BestScoreLabel.setText("Best Score:" + bestScore);
			bl.getPuzzleFrame().setVisible(false);
			//Log the Puzzle Change
			   try{
				    FileWriter log = new FileWriter("log.txt", true);
				    log.write("\t" + getTime() + " Tutorial 5 Puzzle Selected.\n");
				    log.close();
				    }catch (Exception ex){//Catch exception if any
				      System.err.println("Error: " + ex.getMessage());
				    }
		}
		else if (e.getSource() == Tut6Button){
			Restart(true, true, true);
			currentPuzzle =puz.setTut6();
    		bestScore = RecordMove[currentPuzzle];
    		BestScoreLabel.setText("Best Score:" + bestScore);
			bl.getPuzzleFrame().setVisible(false);
			//Log the Puzzle Change
			   try{
				    FileWriter log = new FileWriter("log.txt", true);
				    log.write("\t" + getTime() + " Tutorial 6 Selected.\n");
				    log.close();
				    }catch (Exception ex){//Catch exception if any
				      System.err.println("Error: " + ex.getMessage());
				    }
		}
		else if (e.getSource() == TriforceButton){
			Restart(true, true, true);
			currentPuzzle =puz.setTriforce();
    		bestScore = RecordMove[currentPuzzle];
    		BestScoreLabel.setText("Best Score:" + bestScore);
			bl.getPuzzleFrame().setVisible(false);
			//Log the Puzzle Change
			   try{
				    FileWriter log = new FileWriter("log.txt", true);
				    log.write("\t" + getTime() + " Triforce Puzzle Selected.\n");
				    log.close();
				    }catch (Exception ex){//Catch exception if any
				      System.err.println("Error: " + ex.getMessage());
				    }
		}
		else if (e.getSource() == OverlappingSquaresButton){
			Restart(true, true, true);
			currentPuzzle =puz.setOverlappingSquares();
    		bestScore = RecordMove[currentPuzzle];
    		BestScoreLabel.setText("Best Score:" + bestScore);
			bl.getPuzzleFrame().setVisible(false);
			//Log the Puzzle Change
			   try{
				    FileWriter log = new FileWriter("log.txt", true);
				    log.write("\t" + getTime() + " Overlapping Squares Puzzle Selected.\n");
				    log.close();
				    }catch (Exception ex){//Catch exception if any
				      System.err.println("Error: " + ex.getMessage());
				    }
		}
		else if (e.getSource() == SixButton){
			Restart(true, true, true);
			currentPuzzle =puz.setSix();
    		bestScore = RecordMove[currentPuzzle];
    		BestScoreLabel.setText("Best Score:" + bestScore);
			bl.getPuzzleFrame().setVisible(false);
			//Log the Puzzle Change
			   try{
				    FileWriter log = new FileWriter("log.txt", true);
				    log.write("\t" + getTime() + "Six Puzzle Selected.\n");
				    log.close();
				    }catch (Exception ex){//Catch exception if any
				      System.err.println("Error: " + ex.getMessage());
				    }
		}
		else if (e.getSource() == CanYouHearMeNowButton){
			Restart(true, true, true);
			currentPuzzle =puz.setCanYouHearMeNow();
    		bestScore = RecordMove[currentPuzzle];
    		BestScoreLabel.setText("Best Score:" + bestScore);
			bl.getPuzzleFrame().setVisible(false);
			//Log the Puzzle Change
			   try{
				    FileWriter log = new FileWriter("log.txt", true);
				    log.write("\t" + getTime() + " Can You Hear Me Now Puzzle Selected.\n");
				    log.close();
				    }catch (Exception ex){//Catch exception if any
				      System.err.println("Error: " + ex.getMessage());
				    }
		}
		else if (e.getSource() == StarrySkyButton){
			Restart(true, true, true);
			currentPuzzle =puz.setStarrySky();
    		bestScore = RecordMove[currentPuzzle];
    		BestScoreLabel.setText("Best Score:" + bestScore);
			bl.getPuzzleFrame().setVisible(false);
			//Log the Puzzle Change
			   try{
				    FileWriter log = new FileWriter("log.txt", true);
				    log.write("\t" + getTime() + " Starry Sky Puzzle Selected.\n");
				    log.close();
				    }catch (Exception ex){//Catch exception if any
				      System.err.println("Error: " + ex.getMessage());
				    }
		}
		else if (e.getSource() == LoomEx6Button){
			Restart(true, true, true);
			currentPuzzle =puz.setLoomEx6();
    		bestScore = RecordMove[currentPuzzle];
    		BestScoreLabel.setText("Best Score:" + bestScore);
			bl.getPuzzleFrame().setVisible(false);
			//Log the Puzzle Change
			   try{
				    FileWriter log = new FileWriter("log.txt", true);
				    log.write("\t" + getTime() + " Loom Example 6 Puzzle Selected.\n");
				    log.close();
				    }catch (Exception ex){//Catch exception if any
				      System.err.println("Error: " + ex.getMessage());
				    }
		}
		else if (e.getSource() == LoomEx8Button){
			Restart(true, true, true);
			currentPuzzle =puz.setLoomEx8();
    		bestScore = RecordMove[currentPuzzle];
    		BestScoreLabel.setText("Best Score:" + bestScore);
			bl.getPuzzleFrame().setVisible(false);
			//Log the Puzzle Change
			   try{
				    FileWriter log = new FileWriter("log.txt", true);
				    log.write("\t" + getTime() + " Loom Example 8 Puzzle Selected.\n");
				    log.close();
				    }catch (Exception ex){//Catch exception if any
				      System.err.println("Error: " + ex.getMessage());
				    }
		}
		else if (e.getSource() == CircleButton){
			Restart(true, true, true);
			currentPuzzle =puz.setCircle();
    		bestScore = RecordMove[currentPuzzle];
    		BestScoreLabel.setText("Best Score:" + bestScore);
			bl.getPuzzleFrame().setVisible(false);
			//Log the Puzzle Change
			   try{
				    FileWriter log = new FileWriter("log.txt", true);
				    log.write("\t" + getTime() + " Circle Puzzle Selected.\n");
				    log.close();
				    }catch (Exception ex){//Catch exception if any
				      System.err.println("Error: " + ex.getMessage());
				    }
		}
		else if (e.getSource() == FlagButton){
			Restart(true, true, true);
			currentPuzzle =puz.setFlag();
    		bestScore = RecordMove[currentPuzzle];
    		BestScoreLabel.setText("Best Score:" + bestScore);
			bl.getPuzzleFrame().setVisible(false);
			//Log the Puzzle Change
			   try{
				    FileWriter log = new FileWriter("log.txt", true);
				    log.write("\t" + getTime() + " Flag Puzzle Selected.\n");
				    log.close();
				    }catch (Exception ex){//Catch exception if any
				      System.err.println("Error: " + ex.getMessage());
				    }
		}
		else if (e.getSource() == UNCCButton){
			Restart(true, true, true);
			currentPuzzle =puz.setUNCC();
    		bestScore = RecordMove[currentPuzzle];
    		BestScoreLabel.setText("Best Score:" + bestScore);
			bl.getPuzzleFrame().setVisible(false);
			//Log the Puzzle Change
			   try{
				    FileWriter log = new FileWriter("log.txt", true);
				    log.write("\t" + getTime() + " UNCC Puzzle Selected.\n");
				    log.close();
				    }catch (Exception ex){//Catch exception if any
				      System.err.println("Error: " + ex.getMessage());
				    }
		}
		else if (e.getSource() == LoomEx10Button){
			Restart(true, true, true);
			currentPuzzle =puz.setLoomEx10();
    		bestScore = RecordMove[currentPuzzle];
    		BestScoreLabel.setText("Best Score:" + bestScore);
			bl.getPuzzleFrame().setVisible(false);
			//Log the Puzzle Change
			   try{
				    FileWriter log = new FileWriter("log.txt", true);
				    log.write("\t" + getTime() + " Loom Example 10 Puzzle Selected.\n");
				    log.close();
				    }catch (Exception ex){//Catch exception if any
				      System.err.println("Error: " + ex.getMessage());
				    }
		}
		else if (e.getSource() == LoomEx13Button){
			Restart(true, true, true);
			currentPuzzle =puz.setLoomEx13();
    		bestScore = RecordMove[currentPuzzle];
    		BestScoreLabel.setText("Best Score:" + bestScore);
			bl.getPuzzleFrame().setVisible(false);
			//Log the Puzzle Change
			   try{
				    FileWriter log = new FileWriter("log.txt", true);
				    log.write("\t" + getTime() + " Loom Example 13 Puzzle Selected.\n");
				    log.close();
				    }catch (Exception ex){//Catch exception if any
				      System.err.println("Error: " + ex.getMessage());
				    }
		}
		else if (e.getSource() == LoomEx14Button){
			Restart(true, true, true);
			currentPuzzle =puz.setLoomEx14();
    		bestScore = RecordMove[currentPuzzle];
    		BestScoreLabel.setText("Best Score:" + bestScore);
			bl.getPuzzleFrame().setVisible(false);
			//Log the Puzzle Change
			   try{
				    FileWriter log = new FileWriter("log.txt", true);
				    log.write("\t" + getTime() + " Loom Example 14 Puzzle Selected.\n");
				    log.close();
				    }catch (Exception ex){//Catch exception if any
				      System.err.println("Error: " + ex.getMessage());
				    }
		}
		else if (e.getSource() == SunriseButton){
			Restart(true, true, true);
			currentPuzzle =puz.setSunRise();
    		bestScore = RecordMove[currentPuzzle];
    		BestScoreLabel.setText("Best Score:" + bestScore);
			bl.getPuzzleFrame().setVisible(false);
			//Log the Puzzle Change
			   try{
				    FileWriter log = new FileWriter("log.txt", true);
				    log.write("\t" + getTime() + " Sunrise Puzzle Selected.\n");
				    log.close();
				    }catch (Exception ex){//Catch exception if any
				      System.err.println("Error: " + ex.getMessage());
				    }
		}
		else if (e.getSource() == HeartButton){
			Restart(true, true, true);
			currentPuzzle =puz.setHeart();
    		bestScore = RecordMove[currentPuzzle];
    		BestScoreLabel.setText("Best Score:" + bestScore);
			bl.getPuzzleFrame().setVisible(false);
			//Log the Puzzle Change
			   try{
				    FileWriter log = new FileWriter("log.txt", true);
				    log.write("\t" + getTime() + " Heart Puzzle Selected.\n");
				    log.close();
				    }catch (Exception ex){//Catch exception if any
				      System.err.println("Error: " + ex.getMessage());
				    }
		}
		else if (e.getSource() == SunnySkyButton){
			Restart(true, true, true);
			currentPuzzle =puz.setSunnySky();
    		bestScore = RecordMove[currentPuzzle];
    		BestScoreLabel.setText("Best Score:" + bestScore);
			bl.getPuzzleFrame().setVisible(false);
			//Log the Puzzle Change
			   try{
				    FileWriter log = new FileWriter("log.txt", true);
				    log.write("\t" + getTime() + " Sunny Sky Puzzle Selected.\n");
				    log.close();
				    }catch (Exception ex){//Catch exception if any
				      System.err.println("Error: " + ex.getMessage());
				    }
		}
		else if (e.getSource() == StarsAndStripesButton){
			Restart(true, true, true);
			currentPuzzle =puz.setStarsAndStripes();
    		bestScore = RecordMove[currentPuzzle];
    		BestScoreLabel.setText("Best Score:" + bestScore);
			bl.getPuzzleFrame().setVisible(false);
			//Log the Puzzle Change
			   try{
				    FileWriter log = new FileWriter("log.txt", true);
				    log.write("\t" + getTime() + " Stars and Stripes Puzzle Selected.\n");
				    log.close();
				    }catch (Exception ex){//Catch exception if any
				      System.err.println("Error: " + ex.getMessage());
				    }
		}
		else if (e.getSource() == ROSSButton){
			Restart(true, true, true);
			currentPuzzle =puz.setROSS();
    		bestScore = RecordMove[currentPuzzle];
    		BestScoreLabel.setText("Best Score:" + bestScore);
			bl.getPuzzleFrame().setVisible(false);
			//Log the Puzzle Change
			   try{
				    FileWriter log = new FileWriter("log.txt", true);
				    log.write("\t" + getTime() + " ROSS Puzzle Selected.\n");
				    log.close();
				    }catch (Exception ex){//Catch exception if any
				      System.err.println("Error: " + ex.getMessage());
				    }
		}
		else if (e.getSource() == BullseyeButton){
			Restart(true, true, true);
			currentPuzzle =puz.setBullseye();
    		bestScore = RecordMove[currentPuzzle];
    		BestScoreLabel.setText("Best Score:" + bestScore);
			bl.getPuzzleFrame().setVisible(false);
			//Log the Puzzle Change
			   try{
				    FileWriter log = new FileWriter("log.txt", true);
				    log.write("\t" + getTime() + " Bullseye Puzzle Selected.\n");
				    log.close();
				    }catch (Exception ex){//Catch exception if any
				      System.err.println("Error: " + ex.getMessage());
				    }
		}
		else if (e.getSource() == LoomEx7Button){
			Restart(true, true, true);
			currentPuzzle =puz.setLoomEx7();
    		bestScore = RecordMove[currentPuzzle];
    		BestScoreLabel.setText("Best Score:" + bestScore);
			bl.getPuzzleFrame().setVisible(false);
			//Log the Puzzle Change
			   try{
				    FileWriter log = new FileWriter("log.txt", true);
				    log.write("\t" + getTime() + " Loom Example 7 Puzzle Selected.\n");
				    log.close();
				    }catch (Exception ex){//Catch exception if any
				      System.err.println("Error: " + ex.getMessage());
				    }
		}
		else if (e.getSource() == LoomEx1Button){
			Restart(true, true, true);
			currentPuzzle =puz.setLoomEx1();
    		bestScore = RecordMove[currentPuzzle];
    		BestScoreLabel.setText("Best Score:" + bestScore);
			bl.getPuzzleFrame().setVisible(false);
			//Log the Puzzle Change
			   try{
				    FileWriter log = new FileWriter("log.txt", true);
				    log.write("\t" + getTime() + " Loom Example 1 Puzzle Selected.\n");
				    log.close();
				    }catch (Exception ex){//Catch exception if any
				      System.err.println("Error: " + ex.getMessage());
				    }
		}
		else if (e.getSource() == LoomEx5Button){
			Restart(true, true, true);
			currentPuzzle =puz.setLoomEx5();
    		bestScore = RecordMove[currentPuzzle];
    		BestScoreLabel.setText("Best Score:" + bestScore);
			bl.getPuzzleFrame().setVisible(false);
			//Log the Puzzle Change
			   try{
				    FileWriter log = new FileWriter("log.txt", true);
				    log.write("\t" + getTime() + " Loom Example 5 Puzzle Selected.\n");
				    log.close();
				    }catch (Exception ex){//Catch exception if any
				      System.err.println("Error: " + ex.getMessage());
				    }
		}
		else if (e.getSource() == LoomEx3Button){
			Restart(true, true, true);
			currentPuzzle =puz.setLoomEx3();
    		bestScore = RecordMove[currentPuzzle];
    		BestScoreLabel.setText("Best Score:" + bestScore);
			bl.getPuzzleFrame().setVisible(false);
			//Log the Puzzle Change
			   try{
				    FileWriter log = new FileWriter("log.txt", true);
				    log.write("\t" + getTime() + " Loom Example 3 Puzzle Selected.\n");
				    log.close();
				    }catch (Exception ex){//Catch exception if any
				      System.err.println("Error: " + ex.getMessage());
				    }
		}
		else if (e.getSource() == MoonButton){
			Restart(true, true, true);
			currentPuzzle =puz.setMoon();
    		bestScore = RecordMove[currentPuzzle];
    		BestScoreLabel.setText("Best Score:" + bestScore);
			bl.getPuzzleFrame().setVisible(false);
			//Log the Puzzle Change
			   try{
				    FileWriter log = new FileWriter("log.txt", true);
				    log.write("\t" + getTime() + " Moon Puzzle Selected.\n");
				    log.close();
				    }catch (Exception ex){//Catch exception if any
				      System.err.println("Error: " + ex.getMessage());
				    }
		}
		else if (e.getSource() == MegamanButton){
			Restart(true, true, true);
			currentPuzzle =puz.setMegaman();
    		bestScore = RecordMove[currentPuzzle];
    		BestScoreLabel.setText("Best Score:" + bestScore);
			bl.getPuzzleFrame().setVisible(false);
			//Log the Puzzle Change
			   try{
				    FileWriter log = new FileWriter("log.txt", true);
				    log.write("\t" + getTime() + " Megaman Puzzle Selected.\n");
				    log.close();
				    }catch (Exception ex){//Catch exception if any
				      System.err.println("Error: " + ex.getMessage());
				    }
		}
		else if (e.getSource() == BatmanButton){
			Restart(true, true, true);
			currentPuzzle =puz.setBatman();
    		bestScore = RecordMove[currentPuzzle];
    		BestScoreLabel.setText("Best Score:" + bestScore);
			bl.getPuzzleFrame().setVisible(false);
			//Log the Puzzle Change
			   try{
				    FileWriter log = new FileWriter("log.txt", true);
				    log.write("\t" + getTime() + " Batman Puzzle Selected.\n");
				    log.close();
				    }catch (Exception ex){//Catch exception if any
				      System.err.println("Error: " + ex.getMessage());
				    }
		}
		else if (e.getSource() == DCButton){
			Restart(true, true, true);
			currentPuzzle =puz.setDC();
    		bestScore = RecordMove[currentPuzzle];
    		BestScoreLabel.setText("Best Score:" + bestScore);
			bl.getPuzzleFrame().setVisible(false);
			//Log the Puzzle Change
			   try{
				    FileWriter log = new FileWriter("log.txt", true);
				    log.write("\t" + getTime() + " DC Puzzle Selected.\n");
				    log.close();
				    }catch (Exception ex){//Catch exception if any
				      System.err.println("Error: " + ex.getMessage());
				    }
		}
		else if (e.getSource() == SergeantButton){
			Restart(true, true, true);
			currentPuzzle =puz.setSergeant();
    		bestScore = RecordMove[currentPuzzle];
    		BestScoreLabel.setText("Best Score:" + bestScore);
			bl.getPuzzleFrame().setVisible(false);
			//Log the Puzzle Change
			   try{
				    FileWriter log = new FileWriter("log.txt", true);
				    log.write("\t" + getTime() + " Sergeant Puzzle Selected.\n");
				    log.close();
				    }catch (Exception ex){//Catch exception if any
				      System.err.println("Error: " + ex.getMessage());
				    }
		}
		
		else if (e.getSource() == TieFighterButton){
			Restart(true, true, true);
			currentPuzzle = puz.setTieFighter();
    		bestScore = RecordMove[currentPuzzle];
    		BestScoreLabel.setText("Best Score:" + bestScore);
			bl.getPuzzleFrame().setVisible(false);
			//Log the Puzzle Change
			   try{
				    FileWriter log = new FileWriter("log.txt", true);
				    log.write("\t" + getTime() + " Tie Fighter Puzzle Selected.\n");
				    log.close();
				    }catch (Exception ex){//Catch exception if any
				      System.err.println("Error: " + ex.getMessage());
				    }
		}
		
		else if (e.getSource() == CancelButton){
			bl.getPuzzleFrame().setVisible(false);
		}
		else if (e.getSource() == TutButton){
			EasyButton.setBounds(LeftDifR);
			ChoosePuzzlePanel.add(EasyButton);
			MediumButton.setBounds(CenterDifR);
			ChoosePuzzlePanel.add(MediumButton);
			HardButton.setBounds(RightDifR);
			ChoosePuzzlePanel.add(HardButton);
			ChoosePuzzlePanel.remove(TutButton);
			removePuzzleButtons();
			
			ChoosePuzzlePanel.add(Tut1Button);
			ChoosePuzzlePanel.add(Tut2Button);
			ChoosePuzzlePanel.add(Tut3Button);
			ChoosePuzzlePanel.add(Tut4Button);
			ChoosePuzzlePanel.add(Tut5Button);
			ChoosePuzzlePanel.add(Tut6Button);
			
			HSLabel1.setText("" + RecordMove[0]);
			MLabel1.setText(RecordMedal[0]);
			
			HSLabel2.setText("" + RecordMove[1]);
			MLabel2.setText(RecordMedal[1]);
			
			HSLabel3.setText("" + RecordMove[2]);
			MLabel3.setText(RecordMedal[2]);
			
			HSLabel4.setText("" + RecordMove[3]);
			MLabel4.setText(RecordMedal[3]);
			
			HSLabel5.setText("" + RecordMove[4]);
			MLabel5.setText(RecordMedal[4]);
			
			HSLabel6.setText("" + RecordMove[5]);
			MLabel6.setText(RecordMedal[5]);
			
			HSLabel7.setText(" ");
			MLabel7.setText(" ");
			
			HSLabel8.setText(" ");
			MLabel8.setText(" ");
			
			HSLabel9.setText(" ");
			MLabel9.setText(" ");
			
			HSLabelA.setText(" ");
			MLabelA.setText(" ");	
			
			HSLabelB.setText(" ");
			MLabelB.setText(" ");
		}
		else if (e.getSource() == EasyButton){
			TutButton.setBounds(LeftDifR);
			ChoosePuzzlePanel.add(TutButton);
			MediumButton.setBounds(CenterDifR);
			ChoosePuzzlePanel.add(MediumButton);
			HardButton.setBounds(RightDifR);
			ChoosePuzzlePanel.add(HardButton);
			ChoosePuzzlePanel.remove(EasyButton);
			
			removePuzzleButtons();
			
			ChoosePuzzlePanel.add(TriforceButton);
			ChoosePuzzlePanel.add(CanYouHearMeNowButton);
			ChoosePuzzlePanel.add(SixButton);
			ChoosePuzzlePanel.add(LoomEx8Button);
			ChoosePuzzlePanel.add(OverlappingSquaresButton);
			ChoosePuzzlePanel.add(CircleButton);
			ChoosePuzzlePanel.add(StarrySkyButton);
			ChoosePuzzlePanel.add(LoomEx14Button);
			ChoosePuzzlePanel.add(LoomEx6Button);
			
			HSLabel1.setText("" + RecordMove[6]);
			MLabel1.setText(RecordMedal[6]);
			
			HSLabel2.setText("" + RecordMove[7]);
			MLabel2.setText(RecordMedal[7]);
			
			HSLabel3.setText("" + RecordMove[8]);
			MLabel3.setText(RecordMedal[8]);
			
			HSLabel4.setText("" + RecordMove[9]);
			MLabel4.setText(RecordMedal[9]);
			
			HSLabel5.setText("" + RecordMove[10]);
			MLabel5.setText(RecordMedal[10]);
			
			HSLabel6.setText("" + RecordMove[11]);
			MLabel6.setText(RecordMedal[11]);
			
			HSLabel7.setText(""+RecordMove[12]);
			MLabel7.setText(RecordMedal[12]);
			
			HSLabel8.setText(""+RecordMove[13]);
			MLabel8.setText(RecordMedal[13]);
			
			HSLabel9.setText(""+RecordMove[14]);
			MLabel9.setText(RecordMedal[14]);

			HSLabelA.setText(" ");
			MLabelA.setText(" ");
			
			HSLabelB.setText(" ");
			MLabelB.setText(" ");
		}
		else if (e.getSource() == MediumButton){
			if(RecordMedalShort[14].equals("N") ||
			   RecordMedalShort[13].equals("N") ||	
			   RecordMedalShort[12].equals("N") ||
			   RecordMedalShort[11].equals("N") ||
			   RecordMedalShort[10].equals("N") ||
			   RecordMedalShort[9].equals("N") ||
			   RecordMedalShort[8].equals("N") ||
			   RecordMedalShort[7].equals("N") ||
			   RecordMedalShort[6].equals("N")){
				JOptionPane.showMessageDialog(null, "Complete all the Easy Puzzles to Unlock the Medium Ones!", "Locked!", JOptionPane.PLAIN_MESSAGE);
			}
			else{
			EasyButton.setBounds(LeftDifR);
			ChoosePuzzlePanel.add(EasyButton);
			TutButton.setBounds(CenterDifR);
			ChoosePuzzlePanel.add(TutButton);
			HardButton.setBounds(RightDifR);
			ChoosePuzzlePanel.add(HardButton);
			ChoosePuzzlePanel.remove(MediumButton);
			removePuzzleButtons();
			
			ChoosePuzzlePanel.add(SunnySkyButton);
			ChoosePuzzlePanel.add(HeartButton);
			ChoosePuzzlePanel.add(SunriseButton);
			ChoosePuzzlePanel.add(FlagButton);
			ChoosePuzzlePanel.add(LoomEx13Button);
			ChoosePuzzlePanel.add(LoomEx10Button);
			ChoosePuzzlePanel.add(UNCCButton);
			ChoosePuzzlePanel.add(SergeantButton);
			ChoosePuzzlePanel.add(TieFighterButton);
			
			HSLabel1.setText("" + RecordMove[15]);
			MLabel1.setText(RecordMedal[15]);
			
			HSLabel2.setText("" + RecordMove[16]);
			MLabel2.setText(RecordMedal[16]);
			
			HSLabel3.setText("" + RecordMove[17]);
			MLabel3.setText(RecordMedal[17]);
			
			HSLabel4.setText("" + RecordMove[18]);
			MLabel4.setText(RecordMedal[18]);
			
			HSLabel5.setText("" + RecordMove[19]);
			MLabel5.setText(RecordMedal[19]);
			
			HSLabel6.setText("" + RecordMove[20]);
			MLabel6.setText(RecordMedal[20]);
			
			HSLabel7.setText(""+ RecordMove[21]);
			MLabel7.setText(RecordMedal[21]);
			
			HSLabel8.setText(""+ RecordMove[33]);
			MLabel8.setText(RecordMedal[33]);
			
			HSLabel9.setText(""+ RecordMove[34]);
			MLabel9.setText(RecordMedal[34]);
			
			HSLabelA.setText(" ");
			MLabelA.setText(" ");
			
			HSLabelB.setText(" ");
			MLabelB.setText(" ");
			}
		}
		else if (e.getSource() == HardButton){
			if(		   RecordMedalShort[21].equals("N") ||	
					   RecordMedalShort[20].equals("N") ||	
					   RecordMedalShort[19].equals("N") ||
					   RecordMedalShort[18].equals("N") ||
					   RecordMedalShort[17].equals("N") ||
					   RecordMedalShort[16].equals("N") ||
					   RecordMedalShort[15].equals("N")){
						JOptionPane.showMessageDialog(null, "Complete all the Medium Puzzles to Unlock the Hard Ones!", "Locked!", JOptionPane.PLAIN_MESSAGE);
					}
					else{
			EasyButton.setBounds(LeftDifR);
			ChoosePuzzlePanel.add(EasyButton);
			MediumButton.setBounds(CenterDifR);
			ChoosePuzzlePanel.add(MediumButton);
			TutButton.setBounds(RightDifR);
			ChoosePuzzlePanel.add(TutButton);
			ChoosePuzzlePanel.remove(HardButton);
			removePuzzleButtons();
			
			ChoosePuzzlePanel.add(MoonButton);
			ChoosePuzzlePanel.add(LoomEx7Button);
			ChoosePuzzlePanel.add(DCButton);
			ChoosePuzzlePanel.add(BullseyeButton);
			ChoosePuzzlePanel.add(LoomEx1Button);
			ChoosePuzzlePanel.add(ROSSButton);
			ChoosePuzzlePanel.add(StarsAndStripesButton);
			ChoosePuzzlePanel.add(LoomEx5Button);
			ChoosePuzzlePanel.add(LoomEx3Button);
			ChoosePuzzlePanel.add(MegamanButton);
			ChoosePuzzlePanel.add(BatmanButton);
			
			HSLabel1.setText("" + RecordMove[22]);
			MLabel1.setText(RecordMedal[22]);
			
			HSLabel2.setText("" + RecordMove[23]);
			MLabel2.setText(RecordMedal[23]);
			
			HSLabel3.setText("" + RecordMove[32]);
			MLabel3.setText(RecordMedal[32]);
			
			HSLabel4.setText("" + RecordMove[24]);
			MLabel4.setText(RecordMedal[24]);
			
			HSLabel5.setText("" + RecordMove[25]);
			MLabel5.setText(RecordMedal[25]);
			
			HSLabel6.setText("" + RecordMove[26]);
			MLabel6.setText(RecordMedal[26]);
			
			HSLabel7.setText(""+RecordMove[27]);
			MLabel7.setText(RecordMedal[27]);
			
			HSLabel8.setText(""+RecordMove[28]);
			MLabel8.setText(RecordMedal[28]);
			
			HSLabel9.setText(""+RecordMove[29]);
			MLabel9.setText(RecordMedal[29]);
			
			HSLabelA.setText(""+RecordMove[30]);
			MLabelA.setText(RecordMedal[30]);
			
			HSLabelB.setText(""+RecordMove[31]);
			MLabelB.setText(RecordMedal[31]);
					}
		}
		else if (e.getSource() == RedButton){
			bl.getInputTools().setColor(Color.RED);
			bl.getColorFrame().setVisible(false);
			bl.getMoveBeads().getTopColorsButton().setBackground(Color.RED);
			bl.getMoveBeads().getTopColorsButton().setText("Red");
			//Log the Color Change
			   try{
				    FileWriter log = new FileWriter("log.txt", true);
				    log.write("\t\t" + getTime() + " Color Change: Red\n");
				    log.close();
				    }catch (Exception ex){//Catch exception if any
				      System.err.println("Error: " + ex.getMessage());
				    }
		}
		else if (e.getSource() == GreenButton){
			bl.getInputTools().setColor(Color.GREEN);
			bl.getColorFrame().setVisible(false);
			bl.getMoveBeads().getTopColorsButton().setBackground(Color.GREEN);
			bl.getMoveBeads().getTopColorsButton().setText("Green");
			//Log the Color Change
			   try{
				    FileWriter log = new FileWriter("log.txt", true);
				    log.write("\t\t" + getTime() + " Color Change: Green\n");
				    log.close();
				    }catch (Exception ex){//Catch exception if any
				      System.err.println("Error: " + ex.getMessage());
				    }
		}
		else if (e.getSource() == BlueButton){
			bl.getInputTools().setColor(Color.BLUE);
			bl.getColorFrame().setVisible(false);
			bl.getMoveBeads().getTopColorsButton().setBackground(Color.BLUE);
			bl.getMoveBeads().getTopColorsButton().setText("Blue");
			bl.getMoveBeads().getTopColorsButton().setForeground(Color.WHITE);
			//Log the Color Change
			   try{
				    FileWriter log = new FileWriter("log.txt", true);
				    log.write("\t\t" + getTime() + " Color Change: Blue\n");
				    log.close();
				    }catch (Exception ex){//Catch exception if any
				      System.err.println("Error: " + ex.getMessage());
				    }
		}
		else if (e.getSource() == YellowButton){
			bl.getInputTools().setColor(Color.YELLOW);
			bl.getColorFrame().setVisible(false);
			bl.getMoveBeads().getTopColorsButton().setBackground(Color.YELLOW);
			bl.getMoveBeads().getTopColorsButton().setText("Yellow");
			//Log the Color Change
			   try{
				    FileWriter log = new FileWriter("log.txt", true);
				    log.write("\t\t" + getTime() + " Color Change: Yellow\n");
				    log.close();
				    }catch (Exception ex){//Catch exception if any
				      System.err.println("Error: " + ex.getMessage());
				    }
		}
		else if (e.getSource() == CyanButton){
			bl.getInputTools().setColor(Color.CYAN);
			bl.getColorFrame().setVisible(false);
			bl.getMoveBeads().getTopColorsButton().setBackground(Color.CYAN);
			bl.getMoveBeads().getTopColorsButton().setText("Cyan");
			//Log the Color Change
			   try{
				    FileWriter log = new FileWriter("log.txt", true);
				    log.write("\t\t" + getTime() + " Color Change: Cyan\n");
				    log.close();
				    }catch (Exception ex){//Catch exception if any
				      System.err.println("Error: " + ex.getMessage());
				    }
		}
		else if (e.getSource() == MagentaButton){
			bl.getInputTools().setColor(Color.MAGENTA);
			bl.getColorFrame().setVisible(false);
			bl.getMoveBeads().getTopColorsButton().setBackground(Color.MAGENTA);
			bl.getMoveBeads().getTopColorsButton().setText("Magenta");
			//Log the Color Change
			   try{
				    FileWriter log = new FileWriter("log.txt", true);
				    log.write("\t\t" + getTime() + " Color Change: Magenta\n");
				    log.close();
				    }catch (Exception ex){//Catch exception if any
				      System.err.println("Error: " + ex.getMessage());
				    }
		}
		else if (e.getSource() == OrangeButton){
			bl.getInputTools().setColor(betterOrange);
			bl.getColorFrame().setVisible(false);
			bl.getMoveBeads().getTopColorsButton().setBackground(betterOrange);
			bl.getMoveBeads().getTopColorsButton().setText("Orange");
			//Log the Color Change
			   try{
				    FileWriter log = new FileWriter("log.txt", true);
				    log.write("\t\t" + getTime() + " Color Change: Orange\n");
				    log.close();
				    }catch (Exception ex){//Catch exception if any
				      System.err.println("Error: " + ex.getMessage());
				    }
		}
		else if (e.getSource() == PinkButton){
			bl.getInputTools().setColor(Color.PINK);
			bl.getColorFrame().setVisible(false);
			bl.getMoveBeads().getTopColorsButton().setBackground(Color.PINK);
			bl.getMoveBeads().getTopColorsButton().setText("Pink");
			//Log the Color Change
			   try{
				    FileWriter log = new FileWriter("log.txt", true);
				    log.write("\t\t" + getTime() + " Color Change: Pink\n");
				    log.close();
				    }catch (Exception ex){//Catch exception if any
				      System.err.println("Error: " + ex.getMessage());
				    }
		}
		else if (e.getSource() == PeachButton){
			bl.getInputTools().setColor(peach);
			bl.getColorFrame().setVisible(false);
			bl.getMoveBeads().getTopColorsButton().setBackground(peach);
			bl.getMoveBeads().getTopColorsButton().setText("Peach");
			//Log the Color Change
			   try{
				    FileWriter log = new FileWriter("log.txt", true);
				    log.write("\t\t" + getTime() + " Color Change: Peach\n");
				    log.close();
				    }catch (Exception ex){//Catch exception if any
				      System.err.println("Error: " + ex.getMessage());
				    }
		}
		else if (e.getSource() == GrayButton) {
			bl.getInputTools().setColor(Color.GRAY);
			bl.getColorFrame().setVisible(false);
			bl.getMoveBeads().getTopColorsButton().setBackground(Color.GRAY);
			bl.getMoveBeads().getTopColorsButton().setText("Gray");
			//Log the color change
				try{
						FileWriter log = new FileWriter("log.txt", true);
						log.write("\t\t" + getTime() + " Color Change: Gray\n");
						log.close();
				}catch (Exception ex){ System.err.println("Error: " + ex.getMessage());
				}
		}
		
		else if (e.getSource() == BlackButton){
			bl.getInputTools().setColor(Color.BLACK);
			bl.getColorFrame().setVisible(false);
			bl.getMoveBeads().getTopColorsButton().setBackground(Color.BLACK);
			bl.getMoveBeads().getTopColorsButton().setText("Black");
			bl.getMoveBeads().getTopColorsButton().setForeground(Color.WHITE);
			//Log the Color Change
			   try{
				    FileWriter log = new FileWriter("log.txt", true);
				    log.write("\t\t" + getTime() + " Color Change: Black\n");
				    log.close();
				    }catch (Exception ex){//Catch exception if any
				      System.err.println("Error: " + ex.getMessage());
				    }
		}
		else if (e.getSource() == WhiteButton){
			bl.getInputTools().setColor(Color.WHITE);
			bl.getColorFrame().setVisible(false);
			bl.getMoveBeads().getTopColorsButton().setBackground(Color.WHITE);
			bl.getMoveBeads().getTopColorsButton().setText("White");
			//Log the Color Change
			   try{
				    FileWriter log = new FileWriter("log.txt", true);
				    log.write("\t\t" + getTime() + " Color Change: White\n");
				    log.close();
				    }catch (Exception ex){//Catch exception if any
				      System.err.println("Error: " + ex.getMessage());
				    }
		}
	}
	
	private void redrawGrid()
	{
		bl.getGridPanel().rebuildLayerImages();
		bl.getGridPanel2().rebuildLayerImages();
		bl.getContentPanel().repaint();
	}
	
}
