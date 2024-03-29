package src.mainpackage;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.*;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.Spring;
import javax.swing.SpringLayout;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import javax.swing.JPanel;

import javax.swing.JLabel;

import javax.swing.JTextField;

import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import sun.net.ftp.FtpClient;

import com.sun.xml.internal.ws.wsdl.writer.UsingAddressing;

public class GUIGameTools extends JPanel implements ActionListener{
	public static Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

	private String[] scoreFile;
	private int playerIndex;
	private int totalEntries;
	private int currentPuzzle;
	private boolean avatarMode;
	private int avatarPuzzle;
	
	private boolean limitedColorChoice;

	private long puzzleStartTime;

	private Color peach = new Color(255, 200, 150);
	private Color betterOrange = new Color(255,100,0);
	//GUI Parts
	//Panels
	private JPanel CustomPuzzleMenuPanel = new JPanel();
	private JPanel CustomPuzzleOptionPanel = new JPanel();
	private JPanel AvatarMenuPanel = new JPanel();
	private JPanel GamePanel = new JPanel();
	private JPanel MainMenuPanel = new JPanel();
	private JPanel InGamePanel = new JPanel();
	private JPanel ChoosePuzzlePanel = new JPanel();
	private JPanel ColorPanel = new JPanel();
	private JPanel HighScoresPanel = new JPanel();
	private JComboBox HighScoresComboBox = new JComboBox();
	private JComboBox LoadSavedCustomPuzzleComboBox = new JComboBox();
	private JScrollPane HighScoresScrollPane = new JScrollPane(
			HighScoresPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	private JPanel GameOptionsPanel = new JPanel();
	private FlowLayout HighScoresLayout = new FlowLayout(FlowLayout.LEFT);

	//GamePanel Parts
	private JButton ChoosePuzzleButton = new JButton();
	private JButton RestartButton = new JButton();
	private JButton PuzzleAvatarButton = new JButton();
	private JButton SaveGameButton = new JButton();
	private JButton ClearButton = new JButton();
	private JButton SubmitButton = new JButton();
	private JButton UndoButton = new JButton();
	private JButton SubmitAvatarButton = new JButton();
	private JButton GameOptionsButton = new JButton();
	private JButton MainMenuButton = new JButton();
	private JButton CreateCustomPuzzleButton = new JButton();
	private JButton SaveCustomPuzzleButton = new JButton();
	private JButton CreateNewCustomPuzzleButton = new JButton();
	private JButton LoadSavedCustomPuzzleButton = new JButton();
	private JButton CancelCustomPuzzleButton = new JButton();
	
	private JButton CreateAvatarButton = new JButton();
	private JButton SubmitCustomPuzzleButton = new JButton();
	private JButton ToolButton = new JButton();
	private JButton PlayGameButton = new JButton();
	private JButton HighScoresButton = new JButton();
	private JButton HighScoresCloseButton = new JButton();
	private JButton UpdateHighScoresButton = new JButton();
	private JLabel NameLabel = new JLabel();
	private JLabel BestScoreLabel = new JLabel();
	private JTextField NameTextField = new JTextField();

	private JButton LoadCustomPuzzleButton = new JButton();
	private JComboBox LoadCustomPuzzleDropBox = new JComboBox();
	private JComboBox LoadCustomPuzzleUserDropBox = new JComboBox();
	private JTextField CustomPuzzleTextField = new JTextField();
	JLabel avatarIcon = new JLabel();
	JLabel logoIcon = new JLabel();

	//Choose Puzzle Panel Parts
	JLabel puzIcon = new JLabel();
	private JButton TutButton = new JButton();
	private JButton EasyButton = new JButton();
	private JButton MediumButton = new JButton();
	private JButton HardButton = new JButton();
	private JButton LoadGameButton = new JButton();

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
	private JButton CustomPuzzleButton = new JButton();

	//High Scores Panel Label
	private JLabel HighScoresLabel = new JLabel();
	private JTextField WebRequestTextField = new JTextField(20);

	//Game Options Panel
	private JButton ColorBlindButton = new JButton();
	private JButton HintsButton = new JButton();
	private JButton NormalBeadButton = new JButton();
	private JButton AwesomeBeadButton = new JButton();
	private JButton PeaceBeadButton = new JButton();
	private JButton EightBitBeadButton = new JButton();
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
	
	private JOptionPane rating = new JOptionPane();

	private Color color = new Color(255, 0, 0);

	//Game variables
	int moveCounter = 0;
	String playerName = "Acey";
	int bestScore = 1000;
	Puzzle puz;

	//Features
	private boolean hintsOn = false;


	int[] RecordMove;
	String[] RecordMedal;
	String[] RecordMedalShort;


	//beadloom
	BeadLoom bl;

	//Choose Puzzle Button Locations
	int buttonLength = 150;
	int buttonHeight = 26;
	int vertSpace = 5;
	int horizSpace = 5;
	int startX = 5;
	Rectangle R00 = new Rectangle(startX, vertSpace, buttonLength, buttonHeight);
	Rectangle R10 = new Rectangle(startX, R00.y + buttonHeight+vertSpace, 2*buttonLength, buttonHeight);
	Rectangle R20 = new Rectangle(startX, R10.y + buttonHeight+vertSpace, 2*buttonLength, buttonHeight);
	Rectangle R30 = new Rectangle(startX, R20.y + buttonHeight+vertSpace, 2*buttonLength, buttonHeight);
	Rectangle R40 = new Rectangle(startX, R30.y + buttonHeight+vertSpace, 2*buttonLength, buttonHeight);
	Rectangle R50 = new Rectangle(startX, R40.y + buttonHeight+vertSpace, 2*buttonLength, buttonHeight);
	Rectangle R60 = new Rectangle(startX, R50.y + buttonHeight+vertSpace, 2*buttonLength, buttonHeight);
	Rectangle R70 = new Rectangle(startX, R60.y + buttonHeight+vertSpace, 2*buttonLength, buttonHeight);
	Rectangle R80 = new Rectangle(startX, R70.y + buttonHeight+vertSpace, 2*buttonLength, buttonHeight);
	Rectangle R90 = new Rectangle(startX, R80.y + buttonHeight+vertSpace, 2*buttonLength, buttonHeight);
	Rectangle RA0 = new Rectangle(startX, R90.y + buttonHeight+vertSpace, 2*buttonLength, buttonHeight);
	Rectangle RB0 = new Rectangle(startX, RA0.y + buttonHeight+vertSpace, 2*buttonLength, buttonHeight);

	Rectangle R01 = new Rectangle(R00.x +2*buttonLength+horizSpace, vertSpace, buttonLength, buttonHeight);
	Rectangle R11 = new Rectangle(R10.x +2*buttonLength+horizSpace, R01.y + buttonHeight+vertSpace, buttonLength, buttonHeight);
	Rectangle R21 = new Rectangle(R20.x +2*buttonLength+horizSpace, R11.y + buttonHeight+vertSpace, buttonLength, buttonHeight);
	Rectangle R31 = new Rectangle(R30.x +2*buttonLength+horizSpace, R21.y + buttonHeight+vertSpace, buttonLength, buttonHeight);
	Rectangle R41 = new Rectangle(R40.x +2*buttonLength+horizSpace, R31.y + buttonHeight+vertSpace, buttonLength, buttonHeight);
	Rectangle R51 = new Rectangle(R50.x +2*buttonLength+horizSpace, R41.y + buttonHeight+vertSpace, buttonLength, buttonHeight);
	Rectangle R61 = new Rectangle(R60.x +2*buttonLength+horizSpace, R51.y + buttonHeight+vertSpace, buttonLength, buttonHeight);
	Rectangle R71 = new Rectangle(R70.x +2*buttonLength+horizSpace, R61.y + buttonHeight+vertSpace, buttonLength, buttonHeight);
	Rectangle R81 = new Rectangle(R80.x +2*buttonLength+horizSpace, R71.y + buttonHeight+vertSpace, buttonLength, buttonHeight);
	Rectangle R91 = new Rectangle(R90.x +2*buttonLength+horizSpace, R81.y + buttonHeight+vertSpace, buttonLength, buttonHeight);
	Rectangle RA1 = new Rectangle(RA0.x +2*buttonLength+horizSpace, R91.y + buttonHeight+vertSpace, buttonLength, buttonHeight);
	Rectangle RB1 = new Rectangle(RB0.x +2*buttonLength+horizSpace, RA1.y + buttonHeight+vertSpace, buttonLength, buttonHeight);

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
	
	//for displaying the puzzle thumbnail
	Rectangle PuzzleThumbnail = new Rectangle(R02.x + buttonLength + horizSpace, R01.y + buttonHeight+vertSpace, 328, 328);

	//	Rectangle LeftDifR = new Rectangle(startX, RA0.y + buttonHeight + buttonHeight + vertSpace + vertSpace, buttonLength, buttonHeight);
	//	Rectangle CenterDifR = new Rectangle(LeftDifR.x + buttonLength + horizSpace, RA1.y + buttonHeight + buttonHeight + vertSpace + vertSpace, buttonLength, buttonHeight);
	//	Rectangle RightDifR = new Rectangle(CenterDifR.x + buttonLength + horizSpace, RA2.y + buttonHeight + buttonHeight + vertSpace + vertSpace, buttonLength, buttonHeight);
	Rectangle FarLeftDifR = new Rectangle(startX, RA0.y + buttonHeight + buttonHeight + vertSpace + vertSpace, buttonLength, buttonHeight);
	Rectangle LeftDifR = new Rectangle(FarLeftDifR.x + buttonLength + horizSpace, RA1.y + buttonHeight + buttonHeight + vertSpace + vertSpace, buttonLength, buttonHeight);
	Rectangle CenterDifR = new Rectangle(LeftDifR.x + buttonLength + horizSpace, RA1.y + buttonHeight + buttonHeight + vertSpace + vertSpace, buttonLength, buttonHeight);
	Rectangle RightDifR = new Rectangle(CenterDifR.x + buttonLength + horizSpace, RA2.y + buttonHeight + buttonHeight + vertSpace + vertSpace, buttonLength, buttonHeight);
	Rectangle FarRightDifR = new Rectangle(RightDifR.x + buttonLength + horizSpace, RA2.y + buttonHeight + buttonHeight + vertSpace + vertSpace, buttonLength, buttonHeight);
	Rectangle CustomR = new Rectangle(LeftDifR.x, LeftDifR.y + buttonHeight + vertSpace, buttonLength, buttonHeight);
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
		avatarPuzzle = -2;
		
		//---- game panel -----
		GamePanel.setBorder(new LineBorder(Color.red));
		GamePanel.setBackground(Color.white);
		GamePanel.setLayout(null);
		GamePanel.setBounds(4, 3, 195, 165);
		
		//---- Custom Puzzle Menu Panel ----
		CustomPuzzleMenuPanel.setBorder(new LineBorder(Color.red));
		CustomPuzzleMenuPanel.setBackground(Color.white);
		CustomPuzzleMenuPanel.setLayout(null);
		CustomPuzzleMenuPanel.setVisible(true);
		CustomPuzzleMenuPanel.setBounds(4, 3, 195, 165);
		
		//---- Custom Puzzle Option Panel ----
		CustomPuzzleOptionPanel.setBackground(Color.white);
		CustomPuzzleOptionPanel.setLayout(null);
		CustomPuzzleOptionPanel.setBounds(4, 3, 195, 165);
		
		//---- in game panel ----
		InGamePanel.setBorder(new LineBorder(Color.red));
		InGamePanel.setBackground(Color.white);
		InGamePanel.setLayout(null);
		InGamePanel.setBounds(4, 3, 195, 165);
		
		//---- avatar menu panel ----
		AvatarMenuPanel.setBorder(new LineBorder(Color.red));
		AvatarMenuPanel.setBackground(Color.white);
		AvatarMenuPanel.setLayout(null);
		
		//---- main menu panel ----
		MainMenuPanel.setBorder(new LineBorder(Color.red));
		MainMenuPanel.setBackground(Color.white);
		MainMenuPanel.setLayout(null);
		MainMenuPanel.setBounds(4, 3, 195, 165);

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
		Tut1Button.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {
				puzIcon.setBounds(0,0,0,0);
				ChoosePuzzlePanel.repaint();
			}
			public void mouseEntered(MouseEvent e) {
				bl.getGridPanel2().clear();
				puz.setTut1();
				ChoosePuzzlePanel.remove(puzIcon);
				puzIcon = new JLabel(new ImageIcon(
						bl.createImageFromGrid().getScaledInstance(
						PuzzleThumbnail.width, PuzzleThumbnail.width, 0)));
				puzIcon.setBounds(PuzzleThumbnail);
				ChoosePuzzlePanel.add(puzIcon);
				puzIcon.repaint();
			}
			public void mouseClicked(MouseEvent e) {}
		});
		ChoosePuzzlePanel.add(Tut1Button);

		//---- Tut2 Button ----
		Tut2Button.setText("Tutorial 2");
		Tut2Button.addActionListener(this);
		Tut2Button.setBounds(R20);
		Tut2Button.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {
				puzIcon.setBounds(0,0,0,0);
				ChoosePuzzlePanel.repaint();
			}
			public void mouseEntered(MouseEvent e) {
				bl.getGridPanel2().clear();
				puz.setTut2();
				ChoosePuzzlePanel.remove(puzIcon);
				puzIcon = new JLabel(new ImageIcon(
						bl.createImageFromGrid().getScaledInstance(
						PuzzleThumbnail.width, PuzzleThumbnail.width, 0)));
				puzIcon.setBounds(PuzzleThumbnail);
				ChoosePuzzlePanel.add(puzIcon);
				puzIcon.repaint();
			}
			public void mouseClicked(MouseEvent e) {}
		});
		ChoosePuzzlePanel.add(Tut2Button);

		//---- Tut3 Button ----
		Tut3Button.setText("Tutorial 3");
		Tut3Button.addActionListener(this);
		Tut3Button.setBounds(R30);
		Tut3Button.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {
				puzIcon.setBounds(0,0,0,0);
				ChoosePuzzlePanel.repaint();
			}
			public void mouseEntered(MouseEvent e) {
				bl.getGridPanel2().clear();
				puz.setTut3();
				ChoosePuzzlePanel.remove(puzIcon);
				puzIcon = new JLabel(new ImageIcon(
						bl.createImageFromGrid().getScaledInstance(
						PuzzleThumbnail.width, PuzzleThumbnail.width, 0)));
				puzIcon.setBounds(PuzzleThumbnail);
				ChoosePuzzlePanel.add(puzIcon);
				puzIcon.repaint();
			}
			public void mouseClicked(MouseEvent e) {}
		});
		ChoosePuzzlePanel.add(Tut3Button);

		//---- Tut4 Button ----
		Tut4Button.setText("Tutorial 4");
		Tut4Button.addActionListener(this);
		Tut4Button.setBounds(R40);
		Tut4Button.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {
				puzIcon.setBounds(0,0,0,0);
				ChoosePuzzlePanel.repaint();
			}
			public void mouseEntered(MouseEvent e) {
				bl.getGridPanel2().clear();
				puz.setTut4();
				ChoosePuzzlePanel.remove(puzIcon);
				puzIcon = new JLabel(new ImageIcon(
						bl.createImageFromGrid().getScaledInstance(
						PuzzleThumbnail.width, PuzzleThumbnail.width, 0)));
				puzIcon.setBounds(PuzzleThumbnail);
				ChoosePuzzlePanel.add(puzIcon);
				puzIcon.repaint();
			}
			public void mouseClicked(MouseEvent e) {}
		});
		ChoosePuzzlePanel.add(Tut4Button);

		//---- Tut5 Button ----
		Tut5Button.setText("Tutorial 5");
		Tut5Button.addActionListener(this);
		Tut5Button.setBounds(R50);
		Tut5Button.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {
				puzIcon.setBounds(0,0,0,0);
				ChoosePuzzlePanel.repaint();
			}
			public void mouseEntered(MouseEvent e) {
				bl.getGridPanel2().clear();
				puz.setTut5();
				ChoosePuzzlePanel.remove(puzIcon);
				puzIcon = new JLabel(new ImageIcon(
						bl.createImageFromGrid().getScaledInstance(
						PuzzleThumbnail.width, PuzzleThumbnail.width, 0)));
				puzIcon.setBounds(PuzzleThumbnail);
				ChoosePuzzlePanel.add(puzIcon);
				puzIcon.repaint();
			}
			public void mouseClicked(MouseEvent e) {}
		});
		ChoosePuzzlePanel.add(Tut5Button);

		//---- Tut6 Button ----
		Tut6Button.setText("Tutorial 6");
		Tut6Button.addActionListener(this);
		Tut6Button.setBounds(R60);
		Tut6Button.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {
				puzIcon.setBounds(0,0,0,0);
				ChoosePuzzlePanel.repaint();
			}
			public void mouseEntered(MouseEvent e) {
				bl.getGridPanel2().clear();
				puz.setTut6();
				ChoosePuzzlePanel.remove(puzIcon);
				puzIcon = new JLabel(new ImageIcon(
						bl.createImageFromGrid().getScaledInstance(
						PuzzleThumbnail.width, PuzzleThumbnail.width, 0)));
				puzIcon.setBounds(PuzzleThumbnail);
				ChoosePuzzlePanel.add(puzIcon);
				puzIcon.repaint();
			}
			public void mouseClicked(MouseEvent e) {}
		});
		ChoosePuzzlePanel.add(Tut6Button);

		//---- Triforce Button ----
		TriforceButton.setText("Triforce");
		TriforceButton.addActionListener(this);
		TriforceButton.setBounds(R10);
		TriforceButton.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {
				puzIcon.setBounds(0,0,0,0);
				ChoosePuzzlePanel.repaint();
			}
			public void mouseEntered(MouseEvent e) {
				bl.getGridPanel2().clear();
				puz.setTriforce();
				ChoosePuzzlePanel.remove(puzIcon);
				puzIcon = new JLabel(new ImageIcon(
						bl.createImageFromGrid().getScaledInstance(
						PuzzleThumbnail.width, PuzzleThumbnail.width, 0)));
				puzIcon.setBounds(PuzzleThumbnail);
				ChoosePuzzlePanel.add(puzIcon);
				puzIcon.repaint();
			}
			public void mouseClicked(MouseEvent e) {}
		});


		//---- Overlapping Squares Button ----
		OverlappingSquaresButton.setText("Overlapping Squares");
		OverlappingSquaresButton.addActionListener(this);
		OverlappingSquaresButton.setBounds(R50);
		OverlappingSquaresButton.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {
				puzIcon.setBounds(0,0,0,0);
				ChoosePuzzlePanel.repaint();
			}
			public void mouseEntered(MouseEvent e) {
				bl.getGridPanel2().clear();
				puz.setOverlappingSquares();
				ChoosePuzzlePanel.remove(puzIcon);
				puzIcon = new JLabel(new ImageIcon(
						bl.createImageFromGrid().getScaledInstance(
						PuzzleThumbnail.width, PuzzleThumbnail.width, 0)));
				puzIcon.setBounds(PuzzleThumbnail);
				ChoosePuzzlePanel.add(puzIcon);
				puzIcon.repaint();
			}
			public void mouseClicked(MouseEvent e) {}
		});


		//---- LoomEx14 Button ----
		LoomEx14Button.setText("Native American Example 14");
		LoomEx14Button.addActionListener(this);
		LoomEx14Button.setBounds(R80);
		LoomEx14Button.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {
				puzIcon.setBounds(0,0,0,0);
				ChoosePuzzlePanel.repaint();
			}
			public void mouseEntered(MouseEvent e) {
				bl.getGridPanel2().clear();
				puz.setLoomEx14();
				ChoosePuzzlePanel.remove(puzIcon);
				puzIcon = new JLabel(new ImageIcon(
						bl.createImageFromGrid().getScaledInstance(
						PuzzleThumbnail.width, PuzzleThumbnail.width, 0)));
				puzIcon.setBounds(PuzzleThumbnail);
				ChoosePuzzlePanel.add(puzIcon);
				puzIcon.repaint();
			}
			public void mouseClicked(MouseEvent e) {}
		});


		//---- Six Button ----
		SixButton.setText("Six");
		SixButton.addActionListener(this);
		SixButton.setBounds(R30);
		SixButton.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {
				puzIcon.setBounds(0,0,0,0);
				ChoosePuzzlePanel.repaint();
			}
			public void mouseEntered(MouseEvent e) {
				bl.getGridPanel2().clear();
				puz.setSix();
				ChoosePuzzlePanel.remove(puzIcon);
				puzIcon = new JLabel(new ImageIcon(
						bl.createImageFromGrid().getScaledInstance(
						PuzzleThumbnail.width, PuzzleThumbnail.width, 0)));
				puzIcon.setBounds(PuzzleThumbnail);
				ChoosePuzzlePanel.add(puzIcon);
				puzIcon.repaint();
			}
			public void mouseClicked(MouseEvent e) {}
		});


		//---- Can You Hear Me Now Button ----
		CanYouHearMeNowButton.setText("Can You Hear Me Now?");
		CanYouHearMeNowButton.addActionListener(this);
		CanYouHearMeNowButton.setBounds(R20);
		CanYouHearMeNowButton.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {
				puzIcon.setBounds(0,0,0,0);
				ChoosePuzzlePanel.repaint();
			}
			public void mouseEntered(MouseEvent e) {
				bl.getGridPanel2().clear();
				puz.setCanYouHearMeNow();
				ChoosePuzzlePanel.remove(puzIcon);
				puzIcon = new JLabel(new ImageIcon(
						bl.createImageFromGrid().getScaledInstance(
						PuzzleThumbnail.width, PuzzleThumbnail.width, 0)));
				puzIcon.setBounds(PuzzleThumbnail);
				ChoosePuzzlePanel.add(puzIcon);
				puzIcon.repaint();
			}
			public void mouseClicked(MouseEvent e) {}
		});

		//---- Starry Sky Button ----
		StarrySkyButton.setText("Starry Sky");
		StarrySkyButton.addActionListener(this);
		StarrySkyButton.setBounds(R70);
		StarrySkyButton.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {
				puzIcon.setBounds(0,0,0,0);
				ChoosePuzzlePanel.repaint();
			}
			public void mouseEntered(MouseEvent e) {
				bl.getGridPanel2().clear();
				puz.setStarrySky();
				ChoosePuzzlePanel.remove(puzIcon);
				puzIcon = new JLabel(new ImageIcon(
						bl.createImageFromGrid().getScaledInstance(
						PuzzleThumbnail.width, PuzzleThumbnail.width, 0)));
				puzIcon.setBounds(PuzzleThumbnail);
				ChoosePuzzlePanel.add(puzIcon);
				puzIcon.repaint();
			}
			public void mouseClicked(MouseEvent e) {}
		});

		//---- LoomEx6 Button ----
		LoomEx6Button.setText("Native American Example 6");
		LoomEx6Button.addActionListener(this);
		LoomEx6Button.setBounds(R90);
		LoomEx6Button.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {
				puzIcon.setBounds(0,0,0,0);
				ChoosePuzzlePanel.repaint();
			}
			public void mouseEntered(MouseEvent e) {
				bl.getGridPanel2().clear();
				puz.setLoomEx6();
				ChoosePuzzlePanel.remove(puzIcon);
				puzIcon = new JLabel(new ImageIcon(
						bl.createImageFromGrid().getScaledInstance(
						PuzzleThumbnail.width, PuzzleThumbnail.width, 0)));
				puzIcon.setBounds(PuzzleThumbnail);
				ChoosePuzzlePanel.add(puzIcon);
				puzIcon.repaint();
			}
			public void mouseClicked(MouseEvent e) {}
		});

		//---- LoomEx8 Button ----
		LoomEx8Button.setText("Native American Example 8");
		LoomEx8Button.addActionListener(this);
		LoomEx8Button.setBounds(R40);
		LoomEx8Button.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {
				puzIcon.setBounds(0,0,0,0);
				ChoosePuzzlePanel.repaint();
			}
			public void mouseEntered(MouseEvent e) {
				bl.getGridPanel2().clear();
				puz.setLoomEx8();
				ChoosePuzzlePanel.remove(puzIcon);
				puzIcon = new JLabel(new ImageIcon(
						bl.createImageFromGrid().getScaledInstance(
						PuzzleThumbnail.width, PuzzleThumbnail.width, 0)));
				puzIcon.setBounds(PuzzleThumbnail);
				ChoosePuzzlePanel.add(puzIcon);
				puzIcon.repaint();
			}
			public void mouseClicked(MouseEvent e) {}
		});

		//---- Circle Button ----
		CircleButton.setText("Circle");
		CircleButton.addActionListener(this);
		CircleButton.setBounds(R60);
		CircleButton.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {
				puzIcon.setBounds(0,0,0,0);
				ChoosePuzzlePanel.repaint();
			}
			public void mouseEntered(MouseEvent e) {
				bl.getGridPanel2().clear();
				puz.setCircle();
				ChoosePuzzlePanel.remove(puzIcon);
				puzIcon = new JLabel(new ImageIcon(
						bl.createImageFromGrid().getScaledInstance(
						PuzzleThumbnail.width, PuzzleThumbnail.width, 0)));
				puzIcon.setBounds(PuzzleThumbnail);
				ChoosePuzzlePanel.add(puzIcon);
				puzIcon.repaint();
			}
			public void mouseClicked(MouseEvent e) {}
		});

		//---- Flag Button ----
		FlagButton.setText("American Flag");
		FlagButton.addActionListener(this);
		FlagButton.setBounds(R40);
		FlagButton.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {
				puzIcon.setBounds(0,0,0,0);
				ChoosePuzzlePanel.repaint();
			}
			public void mouseEntered(MouseEvent e) {
				bl.getGridPanel2().clear();
				puz.setFlag();
				ChoosePuzzlePanel.remove(puzIcon);
				puzIcon = new JLabel(new ImageIcon(
						bl.createImageFromGrid().getScaledInstance(
						PuzzleThumbnail.width, PuzzleThumbnail.width, 0)));
				puzIcon.setBounds(PuzzleThumbnail);
				ChoosePuzzlePanel.add(puzIcon);
				puzIcon.repaint();
			}
			public void mouseClicked(MouseEvent e) {}
		});

		//---- UNCC Button ----
		UNCCButton.setText("UNC Charlotte");
		UNCCButton.addActionListener(this);
		UNCCButton.setBounds(R70);
		UNCCButton.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {
				puzIcon.setBounds(0,0,0,0);
				ChoosePuzzlePanel.repaint();
			}
			public void mouseEntered(MouseEvent e) {
				bl.getGridPanel2().clear();
				puz.setUNCC();
				ChoosePuzzlePanel.remove(puzIcon);
				puzIcon = new JLabel(new ImageIcon(
						bl.createImageFromGrid().getScaledInstance(
						PuzzleThumbnail.width, PuzzleThumbnail.width, 0)));
				puzIcon.setBounds(PuzzleThumbnail);
				ChoosePuzzlePanel.add(puzIcon);
				puzIcon.repaint();
			}
			public void mouseClicked(MouseEvent e) {}
		});

		//---- LoomEx10 Button ----
		LoomEx10Button.setText("Native American Example 10");
		LoomEx10Button.addActionListener(this);
		LoomEx10Button.setBounds(R60);
		LoomEx10Button.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {
				puzIcon.setBounds(0,0,0,0);
				ChoosePuzzlePanel.repaint();
			}
			public void mouseEntered(MouseEvent e) {
				bl.getGridPanel2().clear();
				puz.setLoomEx10();
				ChoosePuzzlePanel.remove(puzIcon);
				puzIcon = new JLabel(new ImageIcon(
						bl.createImageFromGrid().getScaledInstance(
						PuzzleThumbnail.width, PuzzleThumbnail.width, 0)));
				puzIcon.setBounds(PuzzleThumbnail);
				ChoosePuzzlePanel.add(puzIcon);
				puzIcon.repaint();
			}
			public void mouseClicked(MouseEvent e) {}
		});

		//---- LoomEx13 Button ----
		LoomEx13Button.setText("Native American Example 13");
		LoomEx13Button.addActionListener(this);
		LoomEx13Button.setBounds(R50);
		LoomEx13Button.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {
				puzIcon.setBounds(0,0,0,0);
				ChoosePuzzlePanel.repaint();
			}
			public void mouseEntered(MouseEvent e) {
				bl.getGridPanel2().clear();
				puz.setLoomEx13();
				ChoosePuzzlePanel.remove(puzIcon);
				puzIcon = new JLabel(new ImageIcon(
						bl.createImageFromGrid().getScaledInstance(
						PuzzleThumbnail.width, PuzzleThumbnail.width, 0)));
				puzIcon.setBounds(PuzzleThumbnail);
				ChoosePuzzlePanel.add(puzIcon);
				puzIcon.repaint();
			}
			public void mouseClicked(MouseEvent e) {}
		});

		//---- Sunrise Button ----
		SunriseButton.setText("Sunrise");
		SunriseButton.addActionListener(this);
		SunriseButton.setBounds(R30);
		SunriseButton.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {
				puzIcon.setBounds(0,0,0,0);
				ChoosePuzzlePanel.repaint();
			}
			public void mouseEntered(MouseEvent e) {
				bl.getGridPanel2().clear();
				puz.setSunRise();
				ChoosePuzzlePanel.remove(puzIcon);
				puzIcon = new JLabel(new ImageIcon(
						bl.createImageFromGrid().getScaledInstance(
						PuzzleThumbnail.width, PuzzleThumbnail.width, 0)));
				puzIcon.setBounds(PuzzleThumbnail);
				ChoosePuzzlePanel.add(puzIcon);
				puzIcon.repaint();
			}
			public void mouseClicked(MouseEvent e) {}
		});

		//---- Heart Button ----
		HeartButton.setText("Heart");
		HeartButton.addActionListener(this);
		HeartButton.setBounds(R20);
		HeartButton.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {
				puzIcon.setBounds(0,0,0,0);
				ChoosePuzzlePanel.repaint();
			}
			public void mouseEntered(MouseEvent e) {
				bl.getGridPanel2().clear();
				puz.setHeart();
				ChoosePuzzlePanel.remove(puzIcon);
				puzIcon = new JLabel(new ImageIcon(
						bl.createImageFromGrid().getScaledInstance(
						PuzzleThumbnail.width, PuzzleThumbnail.width, 0)));
				puzIcon.setBounds(PuzzleThumbnail);
				ChoosePuzzlePanel.add(puzIcon);
				puzIcon.repaint();
			}
			public void mouseClicked(MouseEvent e) {}
		});

		//---- Sergeant Button ----
		SergeantButton.setText("Sergeant");
		SergeantButton.addActionListener(this);
		SergeantButton.setBounds(R80);
		SergeantButton.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {
				puzIcon.setBounds(0,0,0,0);
				ChoosePuzzlePanel.repaint();
			}
			public void mouseEntered(MouseEvent e) {
				bl.getGridPanel2().clear();
				puz.setSergeant();
				ChoosePuzzlePanel.remove(puzIcon);
				puzIcon = new JLabel(new ImageIcon(
						bl.createImageFromGrid().getScaledInstance(
						PuzzleThumbnail.width, PuzzleThumbnail.width, 0)));
				puzIcon.setBounds(PuzzleThumbnail);
				ChoosePuzzlePanel.add(puzIcon);
				puzIcon.repaint();
			}
			public void mouseClicked(MouseEvent e) {}
		});

		//---- Tie Fighter Button ----
		TieFighterButton.setText("Tie Fighter");
		TieFighterButton.addActionListener(this);
		TieFighterButton.setBounds(R10);
		TieFighterButton.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {
				puzIcon.setBounds(0,0,0,0);
				ChoosePuzzlePanel.repaint();
			}
			public void mouseEntered(MouseEvent e) {
				bl.getGridPanel2().clear();
				puz.setTieFighter();
				ChoosePuzzlePanel.remove(puzIcon);
				puzIcon = new JLabel(new ImageIcon(
						bl.createImageFromGrid().getScaledInstance(
						PuzzleThumbnail.width, PuzzleThumbnail.width, 0)));
				puzIcon.setBounds(PuzzleThumbnail);
				ChoosePuzzlePanel.add(puzIcon);
				puzIcon.repaint();
			}
			public void mouseClicked(MouseEvent e) {}
		});

		//---- Sunny Sky Button ----
		SunnySkyButton.setText("Sunny Sky");
		SunnySkyButton.addActionListener(this);
		SunnySkyButton.setBounds(R10);
		SunnySkyButton.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {
				puzIcon.setBounds(0,0,0,0);
				ChoosePuzzlePanel.repaint();
			}
			public void mouseEntered(MouseEvent e) {
				bl.getGridPanel2().clear();
				puz.setSunnySky();
				ChoosePuzzlePanel.remove(puzIcon);
				puzIcon = new JLabel(new ImageIcon(
						bl.createImageFromGrid().getScaledInstance(
						PuzzleThumbnail.width, PuzzleThumbnail.width, 0)));
				puzIcon.setBounds(PuzzleThumbnail);
				ChoosePuzzlePanel.add(puzIcon);
				puzIcon.repaint();
			}
			public void mouseClicked(MouseEvent e) {}
		});

		//---- Stars and Stripes Button ----
		StarsAndStripesButton.setText("Stars and Stripes");
		StarsAndStripesButton.addActionListener(this);
		StarsAndStripesButton.setBounds(R70);
		StarsAndStripesButton.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {
				puzIcon.setBounds(0,0,0,0);
				ChoosePuzzlePanel.repaint();
			}
			public void mouseEntered(MouseEvent e) {
				bl.getGridPanel2().clear();
				puz.setStarsAndStripes();
				ChoosePuzzlePanel.remove(puzIcon);
				puzIcon = new JLabel(new ImageIcon(
						bl.createImageFromGrid().getScaledInstance(
						PuzzleThumbnail.width, PuzzleThumbnail.width, 0)));
				puzIcon.setBounds(PuzzleThumbnail);
				ChoosePuzzlePanel.add(puzIcon);
				puzIcon.repaint();
			}
			public void mouseClicked(MouseEvent e) {}
		});

		//---- ROSS Button ----
		ROSSButton.setText("ROSS the bunny");
		ROSSButton.addActionListener(this);
		ROSSButton.setBounds(R60);
		ROSSButton.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {
				puzIcon.setBounds(0,0,0,0);
				ChoosePuzzlePanel.repaint();
			}
			public void mouseEntered(MouseEvent e) {
				bl.getGridPanel2().clear();
				puz.setROSS();
				ChoosePuzzlePanel.remove(puzIcon);
				puzIcon = new JLabel(new ImageIcon(
						bl.createImageFromGrid().getScaledInstance(
						PuzzleThumbnail.width, PuzzleThumbnail.width, 0)));
				puzIcon.setBounds(PuzzleThumbnail);
				ChoosePuzzlePanel.add(puzIcon);
				puzIcon.repaint();
			}
			public void mouseClicked(MouseEvent e) {}
		});

		//---- Bullseye Button ----
		BullseyeButton.setText("Bullseye");
		BullseyeButton.addActionListener(this);
		BullseyeButton.setBounds(R40);
		BullseyeButton.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {
				puzIcon.setBounds(0,0,0,0);
				ChoosePuzzlePanel.repaint();
			}
			public void mouseEntered(MouseEvent e) {
				bl.getGridPanel2().clear();
				puz.setBullseye();
				ChoosePuzzlePanel.remove(puzIcon);
				puzIcon = new JLabel(new ImageIcon(
						bl.createImageFromGrid().getScaledInstance(
						PuzzleThumbnail.width, PuzzleThumbnail.width, 0)));
				puzIcon.setBounds(PuzzleThumbnail);
				ChoosePuzzlePanel.add(puzIcon);
				puzIcon.repaint();
			}
			public void mouseClicked(MouseEvent e) {}
		});

		//---- LoomEx7 Button ----
		LoomEx7Button.setText("Native American Example 7");
		LoomEx7Button.addActionListener(this);
		LoomEx7Button.setBounds(R20);
		LoomEx7Button.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {
				puzIcon.setBounds(0,0,0,0);
				ChoosePuzzlePanel.repaint();
			}
			public void mouseEntered(MouseEvent e) {
				bl.getGridPanel2().clear();
				puz.setLoomEx7();
				ChoosePuzzlePanel.remove(puzIcon);
				puzIcon = new JLabel(new ImageIcon(
						bl.createImageFromGrid().getScaledInstance(
						PuzzleThumbnail.width, PuzzleThumbnail.width, 0)));
				puzIcon.setBounds(PuzzleThumbnail);
				ChoosePuzzlePanel.add(puzIcon);
				puzIcon.repaint();
			}
			public void mouseClicked(MouseEvent e) {}
		});

		//---- LoomEx1 Button ----
		LoomEx1Button.setText("Native American Example 1");
		LoomEx1Button.addActionListener(this);
		LoomEx1Button.setBounds(R50);
		LoomEx1Button.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {
				puzIcon.setBounds(0,0,0,0);
				ChoosePuzzlePanel.repaint();
			}
			public void mouseEntered(MouseEvent e) {
				bl.getGridPanel2().clear();
				puz.setLoomEx1();
				ChoosePuzzlePanel.remove(puzIcon);
				puzIcon = new JLabel(new ImageIcon(
						bl.createImageFromGrid().getScaledInstance(
						PuzzleThumbnail.width, PuzzleThumbnail.width, 0)));
				puzIcon.setBounds(PuzzleThumbnail);
				ChoosePuzzlePanel.add(puzIcon);
				puzIcon.repaint();
			}
			public void mouseClicked(MouseEvent e) {}
		});

		//---- LoomEx5 Button ----
		LoomEx5Button.setText("Native American Example 5");
		LoomEx5Button.addActionListener(this);
		LoomEx5Button.setBounds(R80);
		LoomEx5Button.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {
				puzIcon.setBounds(0,0,0,0);
				ChoosePuzzlePanel.repaint();
			}
			public void mouseEntered(MouseEvent e) {
				bl.getGridPanel2().clear();
				puz.setLoomEx5();
				ChoosePuzzlePanel.remove(puzIcon);
				puzIcon = new JLabel(new ImageIcon(
						bl.createImageFromGrid().getScaledInstance(
						PuzzleThumbnail.width, PuzzleThumbnail.width, 0)));
				puzIcon.setBounds(PuzzleThumbnail);
				ChoosePuzzlePanel.add(puzIcon);
				puzIcon.repaint();
			}
			public void mouseClicked(MouseEvent e) {}
		});

		//---- LoomEx3 Button ----
		LoomEx3Button.setText("Native American Example 3");
		LoomEx3Button.addActionListener(this);
		LoomEx3Button.setBounds(R90);
		LoomEx3Button.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {
				puzIcon.setBounds(0,0,0,0);
				ChoosePuzzlePanel.repaint();
			}
			public void mouseEntered(MouseEvent e) {
				bl.getGridPanel2().clear();
				puz.setLoomEx3();
				ChoosePuzzlePanel.remove(puzIcon);
				puzIcon = new JLabel(new ImageIcon(
						bl.createImageFromGrid().getScaledInstance(
						PuzzleThumbnail.width, PuzzleThumbnail.width, 0)));
				puzIcon.setBounds(PuzzleThumbnail);
				ChoosePuzzlePanel.add(puzIcon);
				puzIcon.repaint();
			}
			public void mouseClicked(MouseEvent e) {}
		});

		//---- Moon Button ----
		MoonButton.setText("Moon");
		MoonButton.addActionListener(this);
		MoonButton.setBounds(R90);
		MoonButton.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {
				puzIcon.setBounds(0,0,0,0);
				ChoosePuzzlePanel.repaint();
			}
			public void mouseEntered(MouseEvent e) {
				bl.getGridPanel2().clear();
				puz.setMoon();
				ChoosePuzzlePanel.remove(puzIcon);
				puzIcon = new JLabel(new ImageIcon(
						bl.createImageFromGrid().getScaledInstance(
						PuzzleThumbnail.width, PuzzleThumbnail.width, 0)));
				puzIcon.setBounds(PuzzleThumbnail);
				ChoosePuzzlePanel.add(puzIcon);
				puzIcon.repaint();
			}
			public void mouseClicked(MouseEvent e) {}
		});

		//---- Megaman Button ----
		MegamanButton.setText("Megaman");
		MegamanButton.addActionListener(this);
		MegamanButton.setBounds(RA0);
		MegamanButton.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {
				puzIcon.setBounds(0,0,0,0);
				ChoosePuzzlePanel.repaint();
			}
			public void mouseEntered(MouseEvent e) {
				bl.getGridPanel2().clear();
				puz.setMegaman();
				ChoosePuzzlePanel.remove(puzIcon);
				puzIcon = new JLabel(new ImageIcon(
						bl.createImageFromGrid().getScaledInstance(
						PuzzleThumbnail.width, PuzzleThumbnail.width, 0)));
				puzIcon.setBounds(PuzzleThumbnail);
				ChoosePuzzlePanel.add(puzIcon);
				puzIcon.repaint();
			}
			public void mouseClicked(MouseEvent e) {}
		});

		//---- Batman Button ----
		BatmanButton.setText("Batman");
		BatmanButton.addActionListener(this);
		BatmanButton.setBounds(RB0);
		BatmanButton.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {
				puzIcon.setBounds(0,0,0,0);
				ChoosePuzzlePanel.repaint();
			}
			public void mouseEntered(MouseEvent e) {
				bl.getGridPanel2().clear();
				puz.setBatman();
				ChoosePuzzlePanel.remove(puzIcon);
				puzIcon = new JLabel(new ImageIcon(
						bl.createImageFromGrid().getScaledInstance(
						PuzzleThumbnail.width, PuzzleThumbnail.width, 0)));
				puzIcon.setBounds(PuzzleThumbnail);
				ChoosePuzzlePanel.add(puzIcon);
				puzIcon.repaint();
			}
			public void mouseClicked(MouseEvent e) {}
		});

		//---- DC Button ----
		DCButton.setText("DC");
		DCButton.addActionListener(this);
		DCButton.setBounds(R30);
		DCButton.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {
				puzIcon.setBounds(0,0,0,0);
				ChoosePuzzlePanel.repaint();
			}
			public void mouseEntered(MouseEvent e) {
				bl.getGridPanel2().clear();
				puz.setDC();
				ChoosePuzzlePanel.remove(puzIcon);
				puzIcon = new JLabel(new ImageIcon(
						bl.createImageFromGrid().getScaledInstance(
						PuzzleThumbnail.width, PuzzleThumbnail.width, 0)));
				puzIcon.setBounds(PuzzleThumbnail);
				ChoosePuzzlePanel.add(puzIcon);
				puzIcon.repaint();
			}
			public void mouseClicked(MouseEvent e) {}
		});

		if(ComponentToggle.personalHighScores)
		{
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
		}

		if(ComponentToggle.medals)
		{
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
	
			//---- MLabelB ----
			MLabelB.setBounds(RB2);
			ChoosePuzzlePanel.add(MLabelB);
		}
		
		//---- Puzzle Thumbnail ----
		puzIcon.setBounds(PuzzleThumbnail);

		//---- Tutorial Button ----
		TutButton.setText("Tutorial Puzzles");
		TutButton.addActionListener(this);
		TutButton.setBounds(FarLeftDifR);
		ChoosePuzzlePanel.add(TutButton);

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

		//---- Load Game Button ----
		LoadGameButton.setText("Load Game");
		LoadGameButton.addActionListener(this);
		LoadGameButton.setBounds(FarRightDifR);
		ChoosePuzzlePanel.add(LoadGameButton);

		//---- Cancel Button ----
		CancelButton.setText("Cancel");
		CancelButton.addActionListener(this);
		CancelButton.setBounds(CancelR);
		ChoosePuzzlePanel.add(CancelButton);

		//---- Custom Puzzle Button ----
		CustomPuzzleButton.setText("Custom Puzzle");
		CustomPuzzleButton.addActionListener(this);
		CustomPuzzleButton.setBounds(CustomR);
		ChoosePuzzlePanel.add(CustomPuzzleButton);
		
		//---- Submit Custom Puzzle Button ----
		SubmitCustomPuzzleButton.setText("Publish Puzzle");
		SubmitCustomPuzzleButton.addActionListener(this);
		SubmitCustomPuzzleButton.setBounds(25, 75, 145, SubmitCustomPuzzleButton.getPreferredSize().height);
		CustomPuzzleMenuPanel.add(SubmitCustomPuzzleButton);

		//---- Custom Puzzle Name Text Field ----
		CustomPuzzleTextField.setText("Enter Puzzle Name");
		CustomPuzzleTextField.setBounds(25, 100, 145, CustomPuzzleTextField.getPreferredSize().height);
		CustomPuzzleMenuPanel.add(CustomPuzzleTextField);
		CustomPuzzleTextField.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mousePressed(MouseEvent me) {
				if(CustomPuzzleTextField.getText().equals("Enter Puzzle Name"))
				{
					CustomPuzzleTextField.selectAll();
				}
			}
		});

		//---- Load Custom Puzzle Text Field ----
		LoadCustomPuzzleDropBox.setBounds(25, 50, 145, CustomPuzzleTextField.getPreferredSize().height);
		LoadCustomPuzzleDropBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				// TODO Auto-generated method stub
				if(LoadCustomPuzzleUserDropBox.getSelectedItem()!=null && LoadCustomPuzzleDropBox.getSelectedItem() != null)
				{
					bl.getGridPanel2().clear();
					puz.setCustomPuzzle(LoadCustomPuzzleUserDropBox.getSelectedItem()+"-"+LoadCustomPuzzleDropBox.getSelectedItem(), "CustomPuzzles", 2);
					ChoosePuzzlePanel.remove(puzIcon);
					puzIcon = new JLabel(new ImageIcon(
							bl.createImageFromGrid().getScaledInstance(
							PuzzleThumbnail.width, PuzzleThumbnail.width, 0)));
					
					puzIcon.setBounds(PuzzleThumbnail);
					ChoosePuzzlePanel.add(puzIcon);
					puzIcon.repaint();
				}
			}
		});

		//---- Load Custom Puzzle User Text Field ----
		LoadCustomPuzzleUserDropBox.setBounds(25, 25, 145, CustomPuzzleTextField.getPreferredSize().height);
		LoadCustomPuzzleUserDropBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(LoadCustomPuzzleUserDropBox.getSelectedItem()!=null)
				{
					setCustomPuzzleNames(LoadCustomPuzzleUserDropBox.getSelectedItem().toString());
				}
			}
		});
		
		//---- Load Custom Puzzle Button ----
		LoadCustomPuzzleButton.setText("Load Puzzle");
		LoadCustomPuzzleButton.addActionListener(this);
		LoadCustomPuzzleButton.setBounds(25, 75, 145, LoadCustomPuzzleButton.getPreferredSize().height);

		//---- Green Button ----
		GreenButton.setText("Green");
		GreenButton.addActionListener(this);
		GreenButton.setBackground(Color.GREEN);
		GreenButton.setBounds(CR10);
		GreenButton.setOpaque(true);
		ColorPanel.add(GreenButton);

		//---- Red Button ----
		RedButton.setText("Red");
		RedButton.addActionListener(this);
		RedButton.setBackground(Color.RED);
		RedButton.setBounds(CR00);
		RedButton.setOpaque(true);
		ColorPanel.add(RedButton);

		//---- Blue Button ----
		BlueButton.setText("Blue");
		BlueButton.setForeground(Color.WHITE);
		BlueButton.addActionListener(this);
		BlueButton.setBackground(Color.BLUE);
		BlueButton.setBounds(CR20);
		BlueButton.setOpaque(true);
		ColorPanel.add(BlueButton);

		//---- Yellow Button ----
		YellowButton.setText("Yellow");
		YellowButton.addActionListener(this);
		YellowButton.setBackground(Color.YELLOW);
		YellowButton.setBounds(CR01);
		YellowButton.setOpaque(true);
		ColorPanel.add(YellowButton);

		//---- Magenta Button ----
		MagentaButton.setText("Magenta");
		MagentaButton.addActionListener(this);
		MagentaButton.setBackground(Color.MAGENTA);
		MagentaButton.setBounds(CR21);
		MagentaButton.setOpaque(true);
		ColorPanel.add(MagentaButton);

		//---- Cyan Button ----
		CyanButton.setText("Cyan");
		CyanButton.addActionListener(this);
		CyanButton.setBackground(Color.CYAN);
		CyanButton.setBounds(CR11);
		CyanButton.setOpaque(true);
		ColorPanel.add(CyanButton);

		//---- Orange Button ----
		OrangeButton.setText("Orange");
		OrangeButton.addActionListener(this);
		OrangeButton.setBackground(betterOrange);
		OrangeButton.setBounds(CR02);
		OrangeButton.setOpaque(true);
		ColorPanel.add(OrangeButton);

		//---- Pink Button ----
		PinkButton.setText("Pink");
		PinkButton.addActionListener(this);
		PinkButton.setBackground(Color.PINK);
		PinkButton.setBounds(CR12);
		PinkButton.setOpaque(true);
		ColorPanel.add(PinkButton);

		//---- Peach Button ----
		PeachButton.setText("Peach");
		PeachButton.addActionListener(this);
		PeachButton.setBackground(peach);
		PeachButton.setBounds(CR22);
		PeachButton.setOpaque(true);
		ColorPanel.add(PeachButton);

		//-----Gray Button ----
		GrayButton.setText("Gray");
		GrayButton.addActionListener(this);
		GrayButton.setBackground(Color.GRAY);
		GrayButton.setBounds(CR23);
		GrayButton.setOpaque(true);
		ColorPanel.add(GrayButton);

		//---- Black Button ----
		BlackButton.setText("Black");
		BlackButton.setForeground(Color.WHITE);
		BlackButton.addActionListener(this);
		BlackButton.setBackground(Color.BLACK);
		BlackButton.setBounds(CR03);
		BlackButton.setOpaque(true);
		ColorPanel.add(BlackButton);

		//---- White Button ----
		WhiteButton.setText("White");
		WhiteButton.addActionListener(this);
		WhiteButton.setBackground(Color.WHITE);
		WhiteButton.setBounds(CR13);
		WhiteButton.setOpaque(true);
		ColorPanel.add(WhiteButton);

		//---- restart button ----
		RestartButton.setText("Restart");
		RestartButton.setBounds(25, 75, 145, RestartButton.getPreferredSize().height);
		RestartButton.addActionListener(this);

		//---- clear button ----
		ClearButton.setText("Clear");
		ClearButton.setBounds(25, 50, 145, ClearButton.getPreferredSize().height);
		ClearButton.addActionListener(this);

		//---- Submit button ----
		SubmitButton.setText("Submit");
		SubmitButton.setBounds(25, 100, 145, SubmitButton.getPreferredSize().height);
		SubmitButton.setBackground(Color.RED);
		SubmitButton.setForeground(Color.WHITE);
		SubmitButton.addActionListener(this);

		//---- Undo button ----
		UndoButton.setText("Undo");
		UndoButton.setBounds(25, 125, 145, UndoButton.getPreferredSize().height);
		UndoButton.addActionListener(this);

		//---- Submit Avatar button ----
		SubmitAvatarButton.setText("Submit Avatar");
		SubmitAvatarButton.setBounds(25, 100, 145, SubmitAvatarButton.getPreferredSize().height);
		SubmitAvatarButton.addActionListener(this);

		//---- Main Menu button ----
		MainMenuButton.setText("Main Menu");
		MainMenuButton.setBounds(25, 150, 145, MainMenuButton.getPreferredSize().height);
		MainMenuButton.addActionListener(this);

		//---- Save Custom Puzzle button ----
		SaveCustomPuzzleButton.setText("Save");
		SaveCustomPuzzleButton.setBounds(25, 25, 145, SaveCustomPuzzleButton.getPreferredSize().height);
		SaveCustomPuzzleButton.addActionListener(this);
		CustomPuzzleMenuPanel.add(SaveCustomPuzzleButton);

		//---- Save Puzzle button ----
		SaveGameButton.setText("Save Game");
		SaveGameButton.setBounds(25, 25, 145, SaveGameButton.getPreferredSize().height);
		SaveGameButton.addActionListener(this);
		InGamePanel.add(SaveGameButton);
		SaveGameButton.setVisible(ComponentToggle.gameSaves);

		//---- Load Saved Custom Puzzle ----
		LoadSavedCustomPuzzleButton.setText("Load Saved Puzzle");
		LoadSavedCustomPuzzleButton.setBounds(25, 125, 145, LoadSavedCustomPuzzleButton.getPreferredSize().height);
		LoadSavedCustomPuzzleButton.addActionListener(this);
		CustomPuzzleOptionPanel.add(LoadSavedCustomPuzzleButton);

		//---- Load Saved Custom Puzzle ----
		CancelCustomPuzzleButton.setText("Cancel");
		CancelCustomPuzzleButton.setBounds(25, 200, 145, CancelCustomPuzzleButton.getPreferredSize().height);
		CancelCustomPuzzleButton.addActionListener(this);
		CustomPuzzleOptionPanel.add(CancelCustomPuzzleButton);
		
		//---- Load Saved Custom Puzzle Drop Down ----
		LoadSavedCustomPuzzleComboBox.setBounds(25, 150, 145, LoadSavedCustomPuzzleComboBox.getPreferredSize().height);
		CustomPuzzleOptionPanel.add(LoadSavedCustomPuzzleComboBox);
	
		//---- Create New Custom Puzzle ----
		CreateNewCustomPuzzleButton.setText("Create New Puzzle");
		CreateNewCustomPuzzleButton.setBounds(25, 100, 145, CreateNewCustomPuzzleButton.getPreferredSize().height);
		CreateNewCustomPuzzleButton.addActionListener(this);
		CustomPuzzleOptionPanel.add(CreateNewCustomPuzzleButton);
		
		//---- Create Custom Puzzle ----
		CreateCustomPuzzleButton.setText("Create Puzzle");
		CreateCustomPuzzleButton.setBounds(25, 225, 145, CreateCustomPuzzleButton.getPreferredSize().height);
		CreateCustomPuzzleButton.addActionListener(this);

		//---- Create Avatar Puzzle ----
		CreateAvatarButton.setText("Create Avatar");
		CreateAvatarButton.setBounds(25, 250, 145, CreateAvatarButton.getPreferredSize().height);
		CreateAvatarButton.addActionListener(this);
		
		//---- Play Game button ----
		PlayGameButton.setText("Play Game");
		//GamePanel.add(PlayGameButton);
		PlayGameButton.setBounds(25, 200, 145, PlayGameButton.getPreferredSize().height);
		PlayGameButton.addActionListener(this);

		//---- Name Text Field ----
		NameTextField.setText("Enter Your Name");
		//GamePanel.add(NameTextField);
		NameTextField.setBounds(25, 175, 145, 25);
		NameTextField.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mousePressed(MouseEvent me) {
				if(NameTextField.getText().equals("Enter Your Name"))
				{
					NameTextField.selectAll();
				}
			}
		});
		if(!ComponentToggle.userAccounts)
		{
			NameTextField.setText("Guest");
			BeadLoom.playerName = "Guest";
			NameTextField.setEditable(false);
		}

		NameTextField.addActionListener(this);

		//---- choose puzzle button ----
		ChoosePuzzleButton.setText("Change Puzzle");
		ChoosePuzzleButton.setBounds(25, 50, 145, ChoosePuzzleButton.getPreferredSize().height);
		ChoosePuzzleButton.addActionListener(this);

		//---- puzzle avatar button ----
		PuzzleAvatarButton.setText("Puzzle Avatar");
		PuzzleAvatarButton.setBounds(25, 75, 145, PuzzleAvatarButton.getPreferredSize().height);
		PuzzleAvatarButton.addActionListener(this);

		//---- Tool button ----
		ToolButton.setText("Use Tool");
		ToolButton.setBounds(25, 275, 145, ToolButton.getPreferredSize().height);
		ToolButton.addActionListener(this);

		//---- High Scores Button ----
		HighScoresButton.setText("High Scores");
		HighScoresButton.setBounds(25, 300, 145, HighScoresButton.getPreferredSize().height);
		HighScoresButton.addActionListener(this);

		//---- Options button ---
		GameOptionsButton.setText("Options");
		GameOptionsButton.setBounds(25, 325, 145, GameOptionsButton.getPreferredSize().height);
		GameOptionsButton.addActionListener(this);

		//---- Color Blind Button ----
		ColorBlindButton.setText("Color Blind Mode Off");
		ColorBlindButton.setBounds(20, 25, 155, ColorBlindButton.getPreferredSize().height);
		ColorBlindButton.addActionListener(this);
		GameOptionsPanel.add(ColorBlindButton);
		ColorBlindButton.setVisible(false);	//this feature has been removed from the game.

		//---- Hints Button ----
		HintsButton.setText("Hints Off");
		HintsButton.setBounds(20, 50, 155, HintsButton.getPreferredSize().height);
		HintsButton.addActionListener(this);
		if(ComponentToggle.basicHints) { GameOptionsPanel.add(HintsButton); }

		//---- Normal Bead Button ----
		NormalBeadButton.setText("Normal Bead");
		NormalBeadButton.setBounds(20, 100, 155, NormalBeadButton.getPreferredSize().height);
		NormalBeadButton.setBackground(Color.CYAN);
		NormalBeadButton.addActionListener(this);
		if(ComponentToggle.aesthetics) { GameOptionsPanel.add(NormalBeadButton); }

		//---- Awesome Bead Button ----
		AwesomeBeadButton.setText("Awesome Bead");
		AwesomeBeadButton.setBounds(20, 125, 155, AwesomeBeadButton.getPreferredSize().height);
		AwesomeBeadButton.addActionListener(this);
		if(ComponentToggle.aesthetics) { GameOptionsPanel.add(AwesomeBeadButton); }

		//---- Peace Bead Button ----
		PeaceBeadButton.setText("Peace Bead");
		PeaceBeadButton.setBounds(20, 150, 155, PeaceBeadButton.getPreferredSize().height);
		PeaceBeadButton.addActionListener(this);
		if(ComponentToggle.aesthetics) { GameOptionsPanel.add(PeaceBeadButton); }

		//---- 8 Bit Bead Button ----
		//Changed to be acurate
		EightBitBeadButton.setText("Retro Bead");
		EightBitBeadButton.setBounds(20, 175, 155, EightBitBeadButton.getPreferredSize().height);
		EightBitBeadButton.addActionListener(this);
		if(ComponentToggle.aesthetics) { GameOptionsPanel.add(EightBitBeadButton); }

		//---- Game Options Close Button ----
		GameOptionsCloseButton.setText("Close");
		GameOptionsCloseButton.setBounds(20, 200, 155, GameOptionsCloseButton.getPreferredSize().height);
		GameOptionsCloseButton.addActionListener(this);
		GameOptionsPanel.add(GameOptionsCloseButton);

		//---- Name Label ----
		NameLabel.setBounds(25, 0, 200, 25);

		//---- Best Score Label ----
		BestScoreLabel.setBounds(25, 175, 200, 25);

		GamePanel.setBounds(4, 3, 189, 155);

		//---- Update HighScores Button ----
		UpdateHighScoresButton.setText("Update High Scores");
		UpdateHighScoresButton.setBounds(5, 5, 175, UpdateHighScoresButton.getPreferredSize().height);
		HighScoresComboBox.setBounds(5, 50, 175, HighScoresComboBox.getPreferredSize().height);
		//HighScoresPanel.add(WebRequestTextField);
		HighScoresPanel.add(UpdateHighScoresButton);
		HighScoresPanel.add(HighScoresComboBox);
		UpdateHighScoresButton.addActionListener(this);
		

		//---- High Scores Label ----
		HighScoresCloseButton.setText("Close");
		HighScoresCloseButton.setBounds(180, 5, 175, HighScoresCloseButton.getPreferredSize().height);
		HighScoresCloseButton.addActionListener(this);
		HighScoresPanel.add(HighScoresCloseButton);
		HighScoresLabel.setText("");
		HighScoresPanel.add(HighScoresLabel);
		HighScoresLabel.setBounds(5, 0, 300, 5000);
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
		bl.getInputTools().setDrawTextFieldsToZero();
		bl.getOutputWindow().getEditorPane().setText("");
		puzzleStartTime = System.currentTimeMillis();
		bl.seconds = 0;
		bl.minutes = 0;
		bl.timer.restart();
		bl.getTimerLabel().setText("Timer: " + bl.updateTime(bl.minutes, bl.seconds));
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
//			try{
//				FileWriter log = new FileWriter("log.txt", true);
//				log.write("\t\t" + getTime() + " Restart Performed\n");
//				log.close();
//			}catch (Exception ex){//Catch exception if any
//				System.err.println("Error: " + ex.getMessage());
//			}
		}
		//Clear log

		//Send Log to Server
		if(!PuzzleLog.IsEmpty() && currentPuzzle == -1)
		{
			PuzzleLog.AddLayer("\n  <restart />");
			if(ComponentToggle.userAccounts)
			{
				sendCustomPuzzlePost(PuzzleLog.GetLog(puz.getCustomPuzzleName()), BeadLoom.WEB_ADDRESS + BeadLoom.SCRIPTS_FOLDER + "/echo.php", BeadLoom.playerName, "PuzzleLogs");
			}
			else
			{
				sendGuestFilePost(PuzzleLog.GetLog(puz.getCustomPuzzleName()), BeadLoom.WEB_ADDRESS + BeadLoom.SCRIPTS_FOLDER + "/echo.php", BeadLoom.guestPlayerName, "PuzzleLogs");
			}
			PuzzleLog.Clear();
		}
		//Send Log to Server
		else if(!PuzzleLog.IsEmpty())
		{
			PuzzleLog.AddLayer("\n  <restart />");
			if(ComponentToggle.userAccounts)
			{
				sendCustomPuzzlePost(PuzzleLog.GetLog(puz.getPuzzleName(currentPuzzle)), BeadLoom.WEB_ADDRESS + BeadLoom.SCRIPTS_FOLDER + "/echo.php", BeadLoom.playerName, "PuzzleLogs");
			}
			else
			{
				sendGuestFilePost(PuzzleLog.GetLog(puz.getPuzzleName(currentPuzzle)), BeadLoom.WEB_ADDRESS + BeadLoom.SCRIPTS_FOLDER + "/echo.php", BeadLoom.guestPlayerName, "PuzzleLogs");
			}
			PuzzleLog.Clear();
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
		ChoosePuzzlePanel.remove(LoadCustomPuzzleButton);
		ChoosePuzzlePanel.remove(LoadCustomPuzzleDropBox);
		ChoosePuzzlePanel.remove(LoadCustomPuzzleUserDropBox);
		//Refreshes Panel
		ChoosePuzzlePanel.setVisible(false);
		ChoosePuzzlePanel.setVisible(true);
	}

	//Changes the layout of the box for playing the game
	//Input: Players name for log in purposes
	public void startGame(){
		
		setGamePlayMode();
		if(getHintModeStatus() && ComponentToggle.hintsOn)
		{
			String hint = getHint(puz.getPuzzleName(currentPuzzle));
			JOptionPane.showMessageDialog(null, hint, "Hint!", JOptionPane.PLAIN_MESSAGE);
		}
		//Adjust BeadLoom
		bl.getContentPanel().add(bl.getGridFrame2());
		bl.getContentPanel().remove(bl.getGoalImagesFrame());
		bl.getContentPanel().remove(bl.getMoveBeadsFrame());
		bl.getGridFrame().setBounds((int)(BeadLoom.usableDim.getWidth()*0.5) + BeadLoom.panelBorder, BeadLoom.panelBorder, (int)(BeadLoom.usableDim.getWidth()*0.5) - BeadLoom.panelBorder,(int)(BeadLoom.usableDim.getHeight()*0.65)-BeadLoom.panelBorder);
		bl.getGridFrame2().setBounds(BeadLoom.panelBorder, BeadLoom.panelBorder, (int)(BeadLoom.usableDim.getWidth()*0.5) -BeadLoom.panelBorder,(int)(BeadLoom.usableDim.getHeight()*0.65)-BeadLoom.panelBorder);
		bl.getOutputWindow().setBounds(BeadLoom.panelBorder, (int)(BeadLoom.usableDim.getHeight()*0.65) + BeadLoom.panelBorder, (int)(BeadLoom.usableDim.getWidth()*0.37) - BeadLoom.panelBorder, (int)(BeadLoom.usableDim.getHeight()*0.35) - BeadLoom.panelBorder);
		bl.getInGameFrame().setBounds((int)(BeadLoom.usableDim.getWidth()*0.37) + BeadLoom.panelBorder, (int)(BeadLoom.usableDim.getHeight()*0.65) + BeadLoom.panelBorder, (int)(BeadLoom.usableDim.getWidth()*0.20) - BeadLoom.panelBorder, (int)(BeadLoom.usableDim.getHeight()*0.35) - BeadLoom.panelBorder);
		bl.getAvatarMenuFrame().setBounds((int)(BeadLoom.usableDim.getWidth()*0.37) + BeadLoom.panelBorder, (int)(BeadLoom.usableDim.getHeight()*0.65) + BeadLoom.panelBorder, (int)(BeadLoom.usableDim.getWidth()*0.20) - BeadLoom.panelBorder, (int)(BeadLoom.usableDim.getHeight()*0.35) - BeadLoom.panelBorder);
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
		InGamePanel.add(RestartButton);
		InGamePanel.add(SubmitButton);
		InGamePanel.add(UndoButton);
		InGamePanel.add(MainMenuButton);
		InGamePanel.add(NameLabel);
		InGamePanel.add(BestScoreLabel);
		NameLabel.setText(playerName);
		BestScoreLabel.setText("Best Score:" + bestScore);
		InGamePanel.add(ChoosePuzzleButton);

		//Restart Game to reset move counter and clear grid
		//Restart(true, true, false);

		//Load Default Puzzle
		//currentPuzzle = puz.setLoomEx8();

//		try{
//			FileReader fr = new FileReader("Scores.txt"); 
//			BufferedReader br = new BufferedReader(fr); 
//			String s; 
//			s = br.readLine();
//			//First Line is total number of entries
//			totalEntries = Integer.parseInt(s);
//			int lineCounter = 0;
//			//Store the file in an array for later storing and editing
//			//Size is 3 bigger than needed in case the player is a new player and their data needs to be added
//			scoreFile = new String[((totalEntries+1)*3) + 1];
//			scoreFile[lineCounter] = ""+totalEntries;
//			lineCounter++;
//			boolean exists = false;
//			playerIndex = 0;
//			for (int i = 0; i < totalEntries; i++){
//				s = br.readLine();
//				scoreFile[lineCounter] = s;
//				lineCounter++;
//				if (!exists){
//					playerIndex++;
//					if (s.equals(playerName)){
//						exists = true;
//					}
//				}
//
//			}
//			//If its a new player add a new entry for them
//			if (!exists){
//				scoreFile[lineCounter] = playerName;
//				lineCounter++;
//				totalEntries++;
//				scoreFile[0] = ""+totalEntries;
//				playerIndex++;
//			}
//
//			//Read the rest of the file for writing purposes
//			while ((s = br.readLine()) != null){
//				scoreFile[lineCounter] = s;
//				lineCounter++;
//			}
//
//			//If new player add in default scores for them
//			if (!exists){
//				scoreFile[lineCounter] = playerName + " 1682 1682 1682 1682 1682 1682" +
//				" 1682 1682 1682 1682 1682 1682" +
//				" 1682 1682 1682 1682 1682 1682" +
//				" 1682 1682 1682 1682 1682 1682" +
//				" 1682 1682 1682 1682 1682 1682 1682 1682 1682 1682 1682";
//				lineCounter++;
//				scoreFile[lineCounter] = playerName + " N N N N N N N N N N N N N N N N N N N N N N N N N N N N N N N N N N N";
//			}
//
//			StringTokenizer st = new StringTokenizer(scoreFile[playerIndex + playerIndex + totalEntries -1]);
//			st.nextToken();
//			for (int j = 0; j < puz.getTotalPuzzles(); j++){
//				RecordMove[j] = Integer.parseInt(st.nextToken());
//			}
//			st = new StringTokenizer(scoreFile[playerIndex + playerIndex + totalEntries]);
//			st.nextToken();
//			for (int j = 0; j < puz.getTotalPuzzles(); j++){
//				RecordMedalShort[j] = st.nextToken();
//				if(RecordMedalShort[j].equals("B")){
//					RecordMedal[j] = "Bronze!";
//				}
//				else if(RecordMedalShort[j].equals("N")){
//					RecordMedal[j] = "-None-";
//				}
//				else if(RecordMedalShort[j].equals("S")){
//					RecordMedal[j] = "Silver!!";
//				}
//				else if(RecordMedalShort[j].equals("G")){
//					RecordMedal[j] = "Gold!!!";
//				}
//				else{
//					RecordMedal[j] = "Platinum!!!!!";    					
//				}
//			}
//
//
//			fr.close();
//		}
//		catch (Exception e)
//		{   System.err.println("File input error: " + e.toString());
//		}

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
//		try{
//			FileWriter log = new FileWriter("log.txt", true);
//			log.write(getTime() + " " + playerName + " logs in.\n");
//			log.write("\t" + getTime() + " Loom Example 8 Puzzle Selected.\n");
//			log.close();
//		}catch (Exception e){//Catch exception if any
//			System.err.println("Error: " + e.getMessage());
//		}
		bl.getGridPanel().clear();
		bl.getInputTools().ghostPoint();
		
		//Clear log
		PuzzleLog.Clear();
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
		//bl.getGameFrame().setBounds((int)(BeadLoom.usableDim.getWidth()*0.42)+BeadLoom.panelBorder, (int)(BeadLoom.usableDim.getHeight()*.70)+BeadLoom.panelBorder, (int)(BeadLoom.usableDim.getWidth()*0.58)-BeadLoom.panelBorder, (int)(BeadLoom.usableDim.getHeight()*0.30)-BeadLoom.panelBorder);
		bl.getTop().getFileMenu().setEnabled(true);
		bl.getTop().getOptionsMenu().setEnabled(true);
		bl.getTop().getHelpMenu().setEnabled(true);
		bl.getInputTools().getBeadLoomUtilitiesTabbedPane().addTab("Layers Function", bl.getInputTools().getLayersDrawTabbedPane());
		bl.getInputTools().getBeadLoomUtilitiesTabbedPane().addTab("Trig Functions", bl.getInputTools().getTrigFunctionsTabbedPane());
		bl.getPuzzleFrame().setVisible(false);
		bl.getColorFrame().setVisible(false);
		bl.getHighScoresFrame().setVisible(false);
		bl.getInGameFrame().setVisible(false);
		bl.getInputTools().addLoopTools();

		//Log qutting
//		try{
//			FileWriter log = new FileWriter("log.txt", true);
//			log.write(getTime() + " " + playerName + " logs out.\n");
//			log.close();
//		}catch (Exception e){//Catch exception if any
//			System.err.println("Error: " + e.getMessage());
//		}
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

		if(totalErrors == 0 && currentPuzzle == -1)
		{
			bl.stopTimer();
			float puzzleTime = (float)(System.currentTimeMillis()- puzzleStartTime)/1000.0f;

			String completeTime = (((int)puzzleTime)/60)+" minute(s) and "+puzzleTime%60;
			String message = "";
			int rating;
			String[] options = {"1","2","3","4","5"};
			rating = JOptionPane.showOptionDialog(null, "Rate " + puz.getCustomPuzzleName(), "Custom Puzzle Rating", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null,options, 0);

			String urlTime = (int)puzzleTime + "";
			JOptionPane.showMessageDialog(null, "CONGRATULATIONS\nPuzzle Solved in " + getMoveCount(), "Congratulations", JOptionPane.PLAIN_MESSAGE);
			
			String sendString = "";
			try {
				sendString = "user=" + URLEncoder.encode(NameLabel.getText(), "UTF-8") + 
				"&score=" + URLEncoder.encode((getMoveCount() + ""), "UTF-8") + 
				"&time=" + URLEncoder.encode(urlTime, "UTF-8")  + 
				"&medal=Custom" + 
				"&rating=" + URLEncoder.encode(options[rating], "UTF-8") +
				"&puzzle=" + URLEncoder.encode(puz.getCustomPuzzleName(), "UTF-8");
				if(ComponentToggle.securityEnabled){ sendString+="&token=" + URLEncoder.encode(""+Security.getSecurityToken(), "UTF-8"); }
				message = sendPost(BeadLoom.WEB_ADDRESS + BeadLoom.SCRIPTS_FOLDER + "/enterScores.php", sendString);
				if(ComponentToggle.securityEnabled){ Security.setSecurityToken(); }
				JOptionPane.showMessageDialog(null, message, "High Scores messages", JOptionPane.PLAIN_MESSAGE);
				
				//Send Log to Server
				if(!PuzzleLog.IsEmpty())
				{
					if(ComponentToggle.userAccounts)
					{
						sendCustomPuzzlePost(PuzzleLog.GetLog(puz.getCustomPuzzleName()), BeadLoom.WEB_ADDRESS + BeadLoom.SCRIPTS_FOLDER + "/echo.php", BeadLoom.playerName, "PuzzleLogs");
					}
					else
					{
						sendGuestFilePost(PuzzleLog.GetLog(puz.getCustomPuzzleName()), BeadLoom.WEB_ADDRESS + BeadLoom.SCRIPTS_FOLDER + "/echo.php", BeadLoom.guestPlayerName, "PuzzleLogs");
					}
					PuzzleLog.Clear();
				}
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		else if(totalErrors == 0)
		{
			bl.stopTimer();
			float puzzleTime = (float)(System.currentTimeMillis()- puzzleStartTime)/1000.0f;

			String completeTime = (((int)puzzleTime)/60)+" minute(s) and "+puzzleTime%60;
			String message = "";
			String urlTime = (int)puzzleTime + "";
			JOptionPane.showMessageDialog(null, "CONGRATULATIONS\nPuzzle Solved in " + getMoveCount(), "Congratulations", JOptionPane.PLAIN_MESSAGE);
			String medal;
			if (moveCounter <= puz.getIdeal()){
				JOptionPane.showMessageDialog(null, "You Earned the Platinum Medal.  You completed the puzzle in "+completeTime+" second(s).", "Congratulations", JOptionPane.PLAIN_MESSAGE);
				medal = "Platinum!!!!";
				RecordMedalShort[currentPuzzle] = "P";
			}
			else if (moveCounter <= puz.getIdeal()*1.5){
				JOptionPane.showMessageDialog(null, "You Earned the Gold Medal.  You completed the puzzle in "+completeTime+" second(s).", "Congratulations", JOptionPane.PLAIN_MESSAGE);
				medal = "Gold!!!";
				RecordMedalShort[currentPuzzle] = "G";
			}
			else if (moveCounter <= puz.getIdeal()*2){
				JOptionPane.showMessageDialog(null, "You Earned the Silver Medal.  You completed the puzzle in "+completeTime+" second(s).", "Congratulations", JOptionPane.PLAIN_MESSAGE);
				medal = "Silver!!";
				RecordMedalShort[currentPuzzle] = "S";
			}
			else{
				JOptionPane.showMessageDialog(null, "You Earned the Bronze Medal.  You completed the puzzle in "+completeTime+" second(s).", "Congratulations", JOptionPane.PLAIN_MESSAGE); 				  
				medal = "Bronze!";
				RecordMedalShort[currentPuzzle] = "B";
			}
			
			String sendString = "";
			try {
				sendString = "user=" + URLEncoder.encode(NameLabel.getText(), "UTF-8") + 
				"&score=" + URLEncoder.encode((getMoveCount() + ""), "UTF-8") + 
				"&time=" + URLEncoder.encode(urlTime, "UTF-8")  + 
				"&medal=" + URLEncoder.encode(medal, "UTF-8") + 
				"&puzzle=" + URLEncoder.encode(puz.getPuzzleName(currentPuzzle), "UTF-8");
				if(ComponentToggle.securityEnabled){ sendString+="&token=" + URLEncoder.encode(""+Security.getSecurityToken(), "UTF-8"); }
				message = sendPost(BeadLoom.WEB_ADDRESS + BeadLoom.SCRIPTS_FOLDER + "/enterScores.php", sendString);
				if(ComponentToggle.securityEnabled){ Security.setSecurityToken(); }
				JOptionPane.showMessageDialog(null, message, "High Scores messages", JOptionPane.PLAIN_MESSAGE);
				
				//Send Log to Server
				if(!PuzzleLog.IsEmpty())
				{
					if(ComponentToggle.userAccounts)
					{
						sendCustomPuzzlePost(PuzzleLog.GetLog(puz.getPuzzleName(currentPuzzle)), BeadLoom.WEB_ADDRESS + BeadLoom.SCRIPTS_FOLDER + "/echo.php", BeadLoom.playerName, "PuzzleLogs");
					}
					else
					{
						sendGuestFilePost(PuzzleLog.GetLog(puz.getPuzzleName(currentPuzzle)), BeadLoom.WEB_ADDRESS + BeadLoom.SCRIPTS_FOLDER + "/echo.php", BeadLoom.guestPlayerName, "PuzzleLogs");
					}
					PuzzleLog.Clear();
				}
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}

			
			
			//Log the Results
//			try{
//				FileWriter log = new FileWriter("log.txt", true);
//				log.write("\t\t" + getTime() + " Checked Solution.\n");
//				log.write("\t\t\t" + getTime() + " Puzzle Completed in " + moveCounter + "moves.  Earned "+medal+"\n");
//				log.close();
//			}catch (Exception e){//Catch exception if any
//				System.err.println("Error: " + e.getMessage());
//			}
			//IF new best high score
			//Log new high score
			//First in Array then in file
			try
			{
				if (getMoveCount() <RecordMove[currentPuzzle] && currentPuzzle != -1)
				{
					RecordMove[currentPuzzle] = getMoveCount();
					RecordMedal[currentPuzzle] = medal;
					String move = playerName;
					String med = playerName;
					for (int i = 0; i < puz.getTotalPuzzles(); i++)
					{
						move = move + " " + RecordMove[i];
						med = med + " " + RecordMedalShort[i];
					}
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			if(bl.bitBeadLocation.contains("Awesome"))
			{
				Achievements.AssignTempAchievement(18);
			}
			else if(bl.beadLocation.contains("Peace"))
			{
				Achievements.AssignTempAchievement(19);
			}
			else if(bl.beadLocation.contains("Bit"))
			{
				Achievements.AssignTempAchievement(20);
			}
			//Check Achievements here
			getScores();
			Achievements.retrieveMedals(RecordMedal, RecordMove, RecordMedalShort);
			Achievements.checkAchievements();
			Achievements.sendAchievements(BeadLoom.playerName);
			bl.getGridFrame().setVisible(false);
			bl.getGridFrame2().setVisible(false);
			HighScoresComboBox.removeAllItems();
			//Populate the level comboBox on click of HighScores button
			String temp = sendGuestWebRequest(BeadLoom.WEB_ADDRESS + BeadLoom.SCRIPTS_FOLDER + "/puzzles.php");
			String[] items = temp.split(",");
			for(int i=0; i<items.length; i++)
			{
				HighScoresComboBox.addItem(items[i]);
			}
			//show high score
			try{
				HighScoresLabel.setText(
						sendGuestWebRequest(BeadLoom.WEB_ADDRESS + BeadLoom.SCRIPTS_FOLDER + "/scores.php?puzzleName=" + 
								URLEncoder.encode(
										puz.getPuzzleName(currentPuzzle), "UTF-8") +
				"&token=applet&user=" + BeadLoom.playerName));
			}catch(Exception e) {}
			bl.getHighScoresFrame().setVisible(true);
		}
		else if(totalErrors > 10) 
		{
			JOptionPane.showMessageDialog(null, "Incorrect: \n Total Errors: "+ totalErrors, "Sorry", JOptionPane.PLAIN_MESSAGE);

		}
		else
		{
			String Output = "Incorrect: \n Total Errors: " + totalErrors + "\n";
			for(int i = 0; i < totalErrors; i++){
				Output = Output + "(" + errorX[i] + ", " + errorY[i] + ")\n";
			}
			JOptionPane.showMessageDialog(null, Output, "Sorry", JOptionPane.PLAIN_MESSAGE);
		}
		//Send Log to Server
		if(!PuzzleLog.IsEmpty() && currentPuzzle == -1)
		{
			PuzzleLog.AddLayer("\n  <incorrect errors='" + totalErrors + "' />");
			if(ComponentToggle.userAccounts)
			{
				sendCustomPuzzlePost(PuzzleLog.GetLog(puz.getCustomPuzzleName()), BeadLoom.WEB_ADDRESS + BeadLoom.SCRIPTS_FOLDER + "/echo.php", BeadLoom.playerName, "PuzzleLogs");
			}
			else
			{
				sendGuestFilePost(PuzzleLog.GetLog(puz.getCustomPuzzleName()), BeadLoom.WEB_ADDRESS + BeadLoom.SCRIPTS_FOLDER + "/echo.php", BeadLoom.guestPlayerName, "PuzzleLogs");
			}
			PuzzleLog.Clear();
		}
		//Send Log to Server
		else if(!PuzzleLog.IsEmpty())
		{
			PuzzleLog.AddLayer("\n  <incorrect errors='" + totalErrors + "' />");
			if(ComponentToggle.userAccounts)
			{
				sendCustomPuzzlePost(PuzzleLog.GetLog(puz.getPuzzleName(currentPuzzle)), BeadLoom.WEB_ADDRESS + BeadLoom.SCRIPTS_FOLDER + "/echo.php", BeadLoom.playerName, "PuzzleLogs");
			}
			else
			{
				sendGuestFilePost(PuzzleLog.GetLog(puz.getPuzzleName(currentPuzzle)), BeadLoom.WEB_ADDRESS + BeadLoom.SCRIPTS_FOLDER + "/echo.php", BeadLoom.guestPlayerName, "PuzzleLogs");
			}
			PuzzleLog.Clear();
		}
}

	public void setLoom(BeadLoom toSet){
		bl = toSet;
		puz = new Puzzle(toSet);
		RecordMove = new int[puz.getTotalPuzzles()];
		RecordMedal = new String[puz.getTotalPuzzles()];
		RecordMedalShort = new String[puz.getTotalPuzzles()];
	}

	//Gets
	public int getCurrentPuzzle()
	{
		return currentPuzzle;
	}
	public Color getColor()
	{
		return color;
	}
	public void setColor(Color c)
	{
		color = c;
	}
	public boolean isLimitedColorChoice() {
		return limitedColorChoice;
	}
	public boolean getAvatarMode() {
		return avatarMode;
	}
	public JPanel getGamePanel() {
		return GamePanel;
	}
	public JPanel getInGamePanel() {
		return InGamePanel;
	}
	public JPanel getAvatarMenuPanel() {
		return AvatarMenuPanel;
	}
	public JPanel getCustomPuzzleMenuPanel() {
		return CustomPuzzleMenuPanel;
	}
	public JPanel getMainMenuPanel() {
		return MainMenuPanel;
	}
	public JScrollPane getHighScoresScrollPane() {
		return HighScoresScrollPane;
	}
	public JPanel getCustomPuzzleOptionPanel(){
		return CustomPuzzleOptionPanel;
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
	public JButton getClearButton() {
		return ClearButton;
	}
	public JButton getSubmitButton() {
		return SubmitButton;
	}
	public JButton getUndoButton() {
		return UndoButton;
	}
	public JButton getSubmitAvatarButton() {
		return SubmitAvatarButton;
	}
	public JButton getMainMenuButton() {
		return MainMenuButton;
	}
	public JButton getPlayGameButton(){
		return PlayGameButton;
	}
	public JButton getToolButton(){
		return ToolButton;
	}
	public JTextField getNameTextField(){
		return NameTextField;
	}

	public int getMoveCount(){
		return moveCounter;
	}

	public static String sendWebRequest(String url) {
		if(ComponentToggle.userAccounts)
		{
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
				ex.printStackTrace();
				return builder.toString();
			}
			return builder.toString();
		}
		else
		{
			return "This action not available as a guest.";
		}
	}

	public static String sendGuestWebRequest(String url) {
		{
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
				ex.printStackTrace();
				return builder.toString();
			}
			return builder.toString();
		}
	}
	
	public void initMainMenu() {
		NameTextField.setText(BeadLoom.playerName);
		MainMenuPanel.add(NameTextField);
		MainMenuPanel.add(PlayGameButton);
		MainMenuPanel.add(CreateCustomPuzzleButton);
		MainMenuPanel.add(CreateAvatarButton);
		MainMenuPanel.add(ToolButton);
		MainMenuPanel.add(GameOptionsButton);
		if(ComponentToggle.globalHighScores){ MainMenuPanel.add(HighScoresButton); }
	}
	
	public void initAvatarMenu() {
		AvatarMenuPanel.add(SubmitAvatarButton);
		AvatarMenuPanel.add(PuzzleAvatarButton);
	}

	//Action Listener
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()== PlayGameButton || e.getSource() == NameTextField) {
			if (NameTextField.getText().equals("Enter Your Name") || NameTextField.getText().equals("")){
				JOptionPane.showMessageDialog(null, "Please Enter Your Name", "Name Error", JOptionPane.PLAIN_MESSAGE);
			}
			else{
				BeadLoom.playerName = NameTextField.getText();
				getScores();
				showTutorialButtons();
				showChoosePuzzle();
			}
		}
		
		else if (e.getSource() == ToolButton) {
			setToolMode();
		}
		
		else if (e.getSource() == HighScoresCloseButton) {
			bl.getHighScoresFrame().setVisible(false);
			if(bl.getInGameFrame().isVisible())
			{
				bl.getGridFrame().setVisible(true);
				bl.getGridFrame2().setVisible(true);
			}
			else
			{
				bl.getMainMenuFrame().setVisible(true);
			}
			bl.getMainMenuFrame().setVisible(true);
		}
		
		else if (e.getSource() == SubmitCustomPuzzleButton) {
			String text = CustomPuzzleTextField.getText();
			if(text.equalsIgnoreCase("Enter Puzzle Name"))
			{
				
				JOptionPane.showMessageDialog(null, "Enter a Valid Puzzle Name", "Custom Puzzle Message", JOptionPane.PLAIN_MESSAGE);
			}
			else if(text.length() < 3)
			{
				JOptionPane.showMessageDialog(null, "Puzzle Name must have at least 3 characters", "Custom Puzzle Message", JOptionPane.PLAIN_MESSAGE);
			}
			else if(text.length() > 30)
			{
				JOptionPane.showMessageDialog(null, "Puzzle Name must be less than 30 characters", "Custom Puzzle Message", JOptionPane.PLAIN_MESSAGE);
			}
			else if(text.contains(" ") || text.contains(",") || text.contains("/") || text.contains("\\"))
			{
				JOptionPane.showMessageDialog(null, "No Spaces,Commas or Slashes Allowed use Underscores : '_'", "Custom Puzzle Message", JOptionPane.PLAIN_MESSAGE);
				return;
			}
			else if(bl.getGridPanel().getLayers().size() < 1)
			{
				JOptionPane.showMessageDialog(null, "The Grid is Blank!", "Custom Puzzle Message", JOptionPane.PLAIN_MESSAGE);
			}
			else
			{
				String returnMessage = sendCustomPuzzlePost(getGridXML("Hey"), BeadLoom.WEB_ADDRESS + BeadLoom.SCRIPTS_FOLDER + "/echo.php", BeadLoom.playerName+"-"+text, "CustomPuzzles");
				//Only send image if the puzzle is not already published
				if(!returnMessage.contains("already published"))
					createCustomPuzzleImage();
			}
		}
		
		else if (e.getSource() == SaveCustomPuzzleButton) {
			String text = CustomPuzzleTextField.getText();
			if(text.equalsIgnoreCase("Enter Puzzle Name"))
			{
				
				JOptionPane.showMessageDialog(null, "Enter a Valid Puzzle Name", "Custom Puzzle Message", JOptionPane.PLAIN_MESSAGE);
			}
			else if(text.length() < 3)
			{
				JOptionPane.showMessageDialog(null, "Puzzle Name must have at least 3 characters", "Custom Puzzle Message", JOptionPane.PLAIN_MESSAGE);
			}
			else if(text.length() > 30)
			{
				JOptionPane.showMessageDialog(null, "Puzzle Name must be less than 30 characters", "Custom Puzzle Message", JOptionPane.PLAIN_MESSAGE);
			}
			else if(text.contains(" ") || text.contains(",") || text.contains("/") || text.contains("\\"))
			{
				JOptionPane.showMessageDialog(null, "No Spaces,Commas or Slashes Allowed use Underscores : '_'", "Custom Puzzle Message", JOptionPane.PLAIN_MESSAGE);
				return;
			}
			else if(bl.getGridPanel().getLayers().size() < 1)
			{
				JOptionPane.showMessageDialog(null, "The Grid is Blank!", "Custom Puzzle Message", JOptionPane.PLAIN_MESSAGE);
			}
			else
			{
				sendCustomPuzzlePost(getGridXML("Hey"), BeadLoom.WEB_ADDRESS + BeadLoom.SCRIPTS_FOLDER + "/echo.php?token=save&", BeadLoom.playerName+"-"+text, "CustomPuzzles");
			}
		}
		
		else if (e.getSource() == SaveGameButton) {
			if(ComponentToggle.userAccounts)
			{
				if(currentPuzzle != -1)
				{
					sendCustomPuzzlePost(getGridXML(moveCounter+","+(System.currentTimeMillis()-puzzleStartTime)+","+currentPuzzle),
						BeadLoom.WEB_ADDRESS + BeadLoom.SCRIPTS_FOLDER + "/echo.php", BeadLoom.playerName, "GameSaves");
					setMainMenuMode();
				}
			}
			else
			{
				JOptionPane.showMessageDialog(null, "You must be logged in to save a game.", "Guest Restriction", JOptionPane.PLAIN_MESSAGE);
			}
		}
		
		else if (e.getSource() == SubmitAvatarButton) {
			sendCustomPuzzlePost(getGridXML("Hey"), BeadLoom.WEB_ADDRESS + BeadLoom.SCRIPTS_FOLDER + "/echo.php", "MyAvatar-"+BeadLoom.playerName, "Avatars");
			avatarPuzzle = -1;
			sendWebRequest(BeadLoom.WEB_ADDRESS + BeadLoom.SCRIPTS_FOLDER + "/avatar.php?avatar=-1");
			createAvatarImage();
			setMainMenuMode();
			//TODO
		}
		
		else if (e.getSource() == LoadSavedCustomPuzzleButton)
		{
			if(LoadSavedCustomPuzzleComboBox.getSelectedItem() == null || LoadSavedCustomPuzzleComboBox.getSelectedItem().equals("") == true)
			{
				JOptionPane.showMessageDialog(null, "Nothing to Load.", "Error", JOptionPane.PLAIN_MESSAGE);
			}
			else
			{
				setCustomPuzzleMode(BeadLoom.playerName + "-" + LoadSavedCustomPuzzleComboBox.getSelectedItem(), ""+LoadSavedCustomPuzzleComboBox.getSelectedItem());
			}
		}
		
		else if (e.getSource() == CancelCustomPuzzleButton)
		{
			bl.getCustomPuzzleOptionsFrame().setVisible(false);
			bl.getMainMenuFrame().setVisible(true);
		}
		
		else if(e.getSource() == CreateNewCustomPuzzleButton)
		{
			setCustomPuzzleMode();
		}
		
		else if (e.getSource() == CreateCustomPuzzleButton) {
			if (NameTextField.getText().equals("Enter Your Name") || NameTextField.getText().equals("")){
				JOptionPane.showMessageDialog(null, "Please Enter Your Name", "Name Error", JOptionPane.PLAIN_MESSAGE);
			}
			else {
				if(ComponentToggle.userAccounts)
				{
					BeadLoom.playerName = NameTextField.getText();
					bl.getMainMenuFrame().setVisible(false);
					bl.getCustomPuzzleOptionsFrame().setVisible(true);
					LoadSavedCustomPuzzleComboBox.removeAllItems();
					String[] items= sendWebRequest(BeadLoom.WEB_ADDRESS + BeadLoom.SCRIPTS_FOLDER + "/customPuzzles.php?token=edit&user=" + BeadLoom.playerName).split(",");
					for(int i=0; i<items.length; i++)
					{
						LoadSavedCustomPuzzleComboBox.addItem(items[i]);
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null, "This feature has been disabled for guests.", "Guest Restriction", JOptionPane.PLAIN_MESSAGE);
				}
			}
		}
		
		else if (e.getSource() == CreateAvatarButton) {
			if (NameTextField.getText().equals("Enter Your Name") || NameTextField.getText().equals("")){
				JOptionPane.showMessageDialog(null, "Please Enter Your Name", "Name Error", JOptionPane.PLAIN_MESSAGE);
			}
			else {
				BeadLoom.playerName = NameTextField.getText();
				setCreateAvatarMode();
			}
		}

		else if (e.getSource() == UpdateHighScoresButton) {
			try {
				if (HighScoresComboBox.getSelectedItem() == null) {
					HighScoresLabel.setText(
							sendGuestWebRequest(BeadLoom.WEB_ADDRESS + BeadLoom.SCRIPTS_FOLDER + "/scores.php?puzzleName=" + 
									URLEncoder.encode(
											puz.getPuzzleName(currentPuzzle), "UTF-8") +
					"&token=applet&user=" + BeadLoom.playerName));
				}
				else
				{
					HighScoresLabel.setText(
							sendGuestWebRequest(BeadLoom.WEB_ADDRESS + BeadLoom.SCRIPTS_FOLDER + "/scores.php?puzzleName=" + 
									URLEncoder.encode(
											HighScoresComboBox.getSelectedItem().toString(),"UTF-8") +
					"&token=applet&user=" + BeadLoom.playerName));
				}
				HighScoresComboBox.removeAllItems();
				String temp = sendGuestWebRequest(BeadLoom.WEB_ADDRESS + BeadLoom.SCRIPTS_FOLDER + "/puzzles.php");
				String[] items = temp.split(",");
				for(int i=0; i<items.length; i++)
				{
					HighScoresComboBox.addItem(items[i]);
				}

			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		
		else if (e.getSource() == CustomPuzzleButton) {
			removePuzzleButtons();
			LoadCustomPuzzleDropBox.setVisible(true);
			LoadCustomPuzzleUserDropBox.setVisible(true);
			ChoosePuzzlePanel.add(LoadCustomPuzzleButton);
			ChoosePuzzlePanel.add(LoadCustomPuzzleDropBox);
			ChoosePuzzlePanel.add(LoadCustomPuzzleUserDropBox);
			setCustomPuzzleUsers();
			//TODO fix this hack
			avatarMode = true;
			hidePuzzleButtons();
			avatarMode = false;
		}

		else if (e.getSource() == LoadCustomPuzzleButton) {
			startGame();
			currentPuzzle = -1;
			Restart(true, true, true);
			puz.setCustomPuzzle(LoadCustomPuzzleUserDropBox.getSelectedItem()+"-"+LoadCustomPuzzleDropBox.getSelectedItem(), "CustomPuzzles", 2);
			BestScoreLabel.setText("Custom Puzzle");
			bl.getPuzzleFrame().setVisible(false);
			//Log the Puzzle Change
//			try{
//				FileWriter log = new FileWriter("log.txt", true);
//				log.write("\t" + getTime() + " Custom Puzzle Selected.\n");
//				log.close();
//
//				//Display Hint
//				if(hintsOn)	
//				{
//					String hint;
//					hint = getHint(puz.getPuzzleName(currentPuzzle));
//					JOptionPane.showMessageDialog(null, hint, "Hint!", JOptionPane.PLAIN_MESSAGE);
//
//				}
//				//Restart Timer
//				bl.restartTimer();
//
//			}catch (Exception ex){//Catch exception if any
//				System.err.println("Error: " + ex.getMessage());
//			}
		}

		else if (e.getSource() == NormalBeadButton) {
			BeadLoom.beadLocation = BeadLoom.WEB_ADDRESS + "/games/BeadLoomGame/bead.JPG";
			redrawGrid();
			NormalBeadButton.setBackground(Color.CYAN);
			AwesomeBeadButton.setBackground(UIManager.getColor("Button.background"));
			PeaceBeadButton.setBackground(UIManager.getColor("Button.background"));
			EightBitBeadButton.setBackground(UIManager.getColor("Button.background"));
		}

		else if (e.getSource() == AwesomeBeadButton) {
			BeadLoom.beadLocation = BeadLoom.WEB_ADDRESS + "/games/BeadLoomGame/beadAwesome.JPG";
			redrawGrid();
			NormalBeadButton.setBackground(UIManager.getColor("Button.background"));
			AwesomeBeadButton.setBackground(Color.CYAN);
			PeaceBeadButton.setBackground(UIManager.getColor("Button.background"));
			EightBitBeadButton.setBackground(UIManager.getColor("Button.background"));
		}

		else if (e.getSource() == PeaceBeadButton) {
			BeadLoom.beadLocation = BeadLoom.WEB_ADDRESS + "/games/BeadLoomGame/beadPeace.JPG";
			redrawGrid();
			NormalBeadButton.setBackground(UIManager.getColor("Button.background"));
			AwesomeBeadButton.setBackground(UIManager.getColor("Button.background"));
			PeaceBeadButton.setBackground(Color.CYAN);
			EightBitBeadButton.setBackground(UIManager.getColor("Button.background"));
		}

		else if (e.getSource() == EightBitBeadButton) {
			BeadLoom.beadLocation = BeadLoom.WEB_ADDRESS + "/games/BeadLoomGame/8BitBead.jpg";
			redrawGrid();
			NormalBeadButton.setBackground(UIManager.getColor("Button.background"));
			AwesomeBeadButton.setBackground(UIManager.getColor("Button.background"));
			PeaceBeadButton.setBackground(UIManager.getColor("Button.background"));
			EightBitBeadButton.setBackground(Color.CYAN);
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
				hintsOn = false;
			}
			else
			{
				HintsButton.setText("Hints On");
				hintsOn = true;
			}
		}

		else if (e.getSource() == GameOptionsCloseButton) {
			bl.getGameOptionsFrame().setVisible(false);
		}

		else if (e.getSource() == HighScoresButton) {
			bl.getHighScoresFrame().setVisible(!bl.getHighScoresFrame().isVisible());
			bl.decrementZVals(bl.getHighScoresFrame());
			bl.getHighScoresFrame().setBounds(bl.getPuzzleFrame().getBounds());
			HighScoresComboBox.removeAllItems();
			//Populate the level comboBox on click of HighScores button
			String temp = sendGuestWebRequest(BeadLoom.WEB_ADDRESS + BeadLoom.SCRIPTS_FOLDER + "/puzzles.php");
			String[] items = temp.split(",");
			for(int i=0; i<items.length; i++)
			{
				HighScoresComboBox.addItem(items[i]);
			}
			bl.getMainMenuFrame().setVisible(false);
		}

		else if (e.getSource() == GameOptionsButton) {
			bl.getGameOptionsFrame().setVisible(!bl.getGameOptionsFrame().isVisible());
		}

		else if (e.getSource()== SubmitButton) {
			checkSolution();
		}
		
		else if (e.getSource() == UndoButton) {
			bl.getGridPanel().undo();
		}

		else if (e.getSource()== MainMenuButton) {
			setMainMenuMode();
		}
		else if (e.getSource()== RestartButton || e.getSource() == ClearButton) {
			int choice = JOptionPane.showConfirmDialog(null, "Are you sure? This will remove ALL beads from the grid!", "Beadloom", JOptionPane.YES_NO_OPTION);
			if (choice == 0){
				Restart(true, false, true);
			}
		}
		else if (e.getSource()== ChoosePuzzleButton || e.getSource() == PuzzleAvatarButton){
			showTutorialButtons();
			bl.getGridFrame().setVisible(false);
			bl.getGridFrame2().setVisible(false);
			bl.getHighScoresFrame().setVisible(false);
			showChoosePuzzle();
		}
		else if (e.getSource() == Tut1Button && avatarMode) { avatarPuzzle = 0; setMainMenuMode(); }
		else if (e.getSource() == Tut2Button && avatarMode) { avatarPuzzle = 1; setMainMenuMode(); }
		else if (e.getSource() == Tut3Button && avatarMode) { avatarPuzzle = 2; setMainMenuMode(); }
		else if (e.getSource() == Tut4Button && avatarMode) { avatarPuzzle = 3; setMainMenuMode(); }
		else if (e.getSource() == Tut5Button && avatarMode) { avatarPuzzle = 4; setMainMenuMode(); }
		else if (e.getSource() == Tut6Button && avatarMode) { avatarPuzzle = 5; setMainMenuMode(); }

		else if (e.getSource() == TriforceButton && avatarMode) { avatarPuzzle = 6; setMainMenuMode(); }
		else if (e.getSource() == CanYouHearMeNowButton && avatarMode) { avatarPuzzle = 7; setMainMenuMode(); }
		else if (e.getSource() == SixButton && avatarMode) { avatarPuzzle = 8; setMainMenuMode(); }
		else if (e.getSource() == LoomEx8Button && avatarMode) { avatarPuzzle = 9; setMainMenuMode(); }
		else if (e.getSource() == OverlappingSquaresButton && avatarMode) { avatarPuzzle = 10; setMainMenuMode(); }
		else if (e.getSource() == CircleButton && avatarMode) { avatarPuzzle = 11; setMainMenuMode(); }
		else if (e.getSource() == StarrySkyButton && avatarMode) { avatarPuzzle = 12; setMainMenuMode(); }
		else if (e.getSource() == LoomEx14Button && avatarMode) { avatarPuzzle = 13; setMainMenuMode(); }
		else if (e.getSource() == LoomEx6Button && avatarMode) { avatarPuzzle = 14; setMainMenuMode(); }
		
		else if (e.getSource() == SunnySkyButton && avatarMode) { avatarPuzzle = 15; setMainMenuMode(); }
		else if (e.getSource() == HeartButton && avatarMode) { avatarPuzzle = 16; setMainMenuMode(); }
		else if (e.getSource() == SunriseButton && avatarMode) { avatarPuzzle = 17; setMainMenuMode(); }
		else if (e.getSource() == FlagButton && avatarMode) { avatarPuzzle = 18; setMainMenuMode(); }
		else if (e.getSource() == LoomEx13Button && avatarMode) { avatarPuzzle = 19; setMainMenuMode(); }
		else if (e.getSource() == LoomEx10Button && avatarMode) { avatarPuzzle = 20; setMainMenuMode(); }
		else if (e.getSource() == UNCCButton && avatarMode) { avatarPuzzle = 21; setMainMenuMode(); }
		else if (e.getSource() == SergeantButton && avatarMode) { avatarPuzzle = 22; setMainMenuMode(); }
		else if (e.getSource() == MoonButton && avatarMode) { avatarPuzzle = 23; setMainMenuMode(); }
		
		else if (e.getSource() == TieFighterButton && avatarMode) { avatarPuzzle = 24; setMainMenuMode(); }
		else if (e.getSource() == LoomEx7Button && avatarMode) { avatarPuzzle = 25; setMainMenuMode(); }
		else if (e.getSource() == DCButton && avatarMode) { avatarPuzzle = 26; setMainMenuMode(); }
		else if (e.getSource() == BullseyeButton && avatarMode) { avatarPuzzle = 27; setMainMenuMode(); }
		else if (e.getSource() == LoomEx1Button && avatarMode) { avatarPuzzle = 28; setMainMenuMode(); }
		else if (e.getSource() == ROSSButton && avatarMode) { avatarPuzzle = 29; setMainMenuMode(); }
		else if (e.getSource() == StarsAndStripesButton && avatarMode) { avatarPuzzle = 30; setMainMenuMode(); }
		else if (e.getSource() == LoomEx5Button && avatarMode) { avatarPuzzle = 31; setMainMenuMode(); }
		else if (e.getSource() == LoomEx3Button && avatarMode) { avatarPuzzle = 32; setMainMenuMode(); }
		else if (e.getSource() == MegamanButton && avatarMode) { avatarPuzzle = 33; setMainMenuMode(); }
		else if (e.getSource() == BatmanButton && avatarMode) { avatarPuzzle = 34; setMainMenuMode(); }

		else if (e.getSource() == Tut1Button){
			startGame();
			Restart(true, true, true);
			currentPuzzle = puz.setTut1();
			bestScore = RecordMove[currentPuzzle];
			BestScoreLabel.setText("Best Score:" + bestScore);
			bl.getPuzzleFrame().setVisible(false);
			//Log the Puzzle Change
//			try{
//				FileWriter log = new FileWriter("log.txt", true);
//				log.write("\t" + getTime() + " Tutorial 1 Puzzle Selected.\n");
//				log.close();
//
//				//Display Hint
//				if(hintsOn)	
//				{
//					String hint;
//					hint = getHint(puz.getPuzzleName(currentPuzzle));
//					JOptionPane.showMessageDialog(null, hint, "Hint!", JOptionPane.PLAIN_MESSAGE);
//
//				}
//				//Restart Timer
//				bl.restartTimer();
//
//			}catch (Exception ex){//Catch exception if any
//				System.err.println("Error: " + ex.getMessage());
//			}
		}

		else if (e.getSource() == Tut2Button){
			startGame();
			Restart(true, true, true);
			currentPuzzle = puz.setTut2();
			bestScore = RecordMove[currentPuzzle];
			BestScoreLabel.setText("Best Score:" + bestScore);
			bl.getPuzzleFrame().setVisible(false);
			//Log the Puzzle Change
//			try{
//				FileWriter log = new FileWriter("log.txt", true);
//				log.write("\t" + getTime() + " Tutorial 2 Puzzle Selected.\n");
//				log.close();
//
//				//Display Hint
//				if(hintsOn)	
//				{
//					String hint;
//					hint = getHint(puz.getPuzzleName(currentPuzzle));
//					JOptionPane.showMessageDialog(null, hint, "Hint!", JOptionPane.PLAIN_MESSAGE);
//
//				}
//				//Restart Timer
//				bl.restartTimer();
//
//			}catch (Exception ex){//Catch exception if any
//				System.err.println("Error: " + ex.getMessage());
//			}
		}
		else if (e.getSource() == Tut3Button){
			startGame();
			Restart(true, true, true);
			currentPuzzle = puz.setTut3();
			bestScore = RecordMove[currentPuzzle];
			BestScoreLabel.setText("Best Score:" + bestScore);
			bl.getPuzzleFrame().setVisible(false);
			//Log the Puzzle Change
//			try{
//				FileWriter log = new FileWriter("log.txt", true);
//				log.write("\t" + getTime() + " Tutorial 3 Puzzle Selected.\n");
//				log.close();
//
//				//Display Hint
//				if(hintsOn)	
//				{
//					String hint;
//					hint = getHint(puz.getPuzzleName(currentPuzzle));
//					JOptionPane.showMessageDialog(null, hint, "Hint!", JOptionPane.PLAIN_MESSAGE);
//
//				}
//				//Restart Timer
//				bl.restartTimer();
//
//			}catch (Exception ex){//Catch exception if any
//				System.err.println("Error: " + ex.getMessage());
//			}    
		}
		else if (e.getSource() == Tut4Button){
			startGame();
			Restart(true, true, true);
			currentPuzzle =puz.setTut4();
			bestScore = RecordMove[currentPuzzle];
			BestScoreLabel.setText("Best Score:" + bestScore);
			bl.getPuzzleFrame().setVisible(false);
			//Log the Puzzle Change
//			try{
//				FileWriter log = new FileWriter("log.txt", true);
//				log.write("\t" + getTime() + " Tutorial 4 Puzzle Selected.\n");
//				log.close();
//
//				//Display Hint
//				if(hintsOn)	
//				{
//					String hint;
//					hint = getHint(puz.getPuzzleName(currentPuzzle));
//					JOptionPane.showMessageDialog(null, hint, "Hint!", JOptionPane.PLAIN_MESSAGE);
//
//				}
//				//Restart Timer
//				bl.restartTimer();
//
//			}catch (Exception ex){//Catch exception if any
//				System.err.println("Error: " + ex.getMessage());
//			}	    
		}
		else if (e.getSource() == Tut5Button){
			startGame();
			Restart(true, true, true);
			currentPuzzle =puz.setTut5();
			bestScore = RecordMove[currentPuzzle];
			BestScoreLabel.setText("Best Score:" + bestScore);
			bl.getPuzzleFrame().setVisible(false);
			//Log the Puzzle Change
//			try{
//				FileWriter log = new FileWriter("log.txt", true);
//				log.write("\t" + getTime() + " Tutorial 5 Puzzle Selected.\n");
//				log.close();
//
//				//Display Hint
//				if(hintsOn)	
//				{
//					String hint;
//					hint = getHint(puz.getPuzzleName(currentPuzzle));
//					JOptionPane.showMessageDialog(null, hint, "Hint!", JOptionPane.PLAIN_MESSAGE);
//
//				}
//				//Restart Timer
//				bl.restartTimer();
//
//			}catch (Exception ex){//Catch exception if any
//				System.err.println("Error: " + ex.getMessage());
//			}			    
		}
		else if (e.getSource() == Tut6Button){
			startGame();
			Restart(true, true, true);
			currentPuzzle =puz.setTut6();
			bestScore = RecordMove[currentPuzzle];
			BestScoreLabel.setText("Best Score:" + bestScore);
			bl.getPuzzleFrame().setVisible(false);
			//Log the Puzzle Change
//			try{
//				FileWriter log = new FileWriter("log.txt", true);
//				log.write("\t" + getTime() + " Tutorial 6 Selected.\n");
//				log.close();
//
//				//Display Hint
//				if(hintsOn)	
//				{
//					String hint;
//					hint = getHint(puz.getPuzzleName(currentPuzzle));
//					JOptionPane.showMessageDialog(null, hint, "Hint!", JOptionPane.PLAIN_MESSAGE);
//
//				}
//				//Restart Timer
//				bl.restartTimer();
//
//			}catch (Exception ex){//Catch exception if any
//				System.err.println("Error: " + ex.getMessage());
//			}
		}
		else if (e.getSource() == TriforceButton){
			startGame();
			Restart(true, true, true);
			currentPuzzle =puz.setTriforce();
			bestScore = RecordMove[currentPuzzle];
			BestScoreLabel.setText("Best Score:" + bestScore);
			bl.getPuzzleFrame().setVisible(false);
			//Log the Puzzle Change
//			try{
//				FileWriter log = new FileWriter("log.txt", true);
//				log.write("\t" + getTime() + " Triforce Puzzle Selected.\n");
//				log.close();
//
//				//Display Hint
//				if(hintsOn)	
//				{
//					String hint;
//					hint = getHint(puz.getPuzzleName(currentPuzzle));
//					JOptionPane.showMessageDialog(null, hint, "Hint!", JOptionPane.PLAIN_MESSAGE);
//
//				}
//				//Restart Timer
//				bl.restartTimer();
//
//			}catch (Exception ex){//Catch exception if any
//				System.err.println("Error: " + ex.getMessage());
//			}    
		}
		else if (e.getSource() == OverlappingSquaresButton){
			startGame();
			Restart(true, true, true);
			currentPuzzle =puz.setOverlappingSquares();
			bestScore = RecordMove[currentPuzzle];
			BestScoreLabel.setText("Best Score:" + bestScore);
			bl.getPuzzleFrame().setVisible(false);
			//Log the Puzzle Change
//			try{
//				FileWriter log = new FileWriter("log.txt", true);
//				log.write("\t" + getTime() + " Overlapping Squares Puzzle Selected.\n");
//				log.close();
//
//				//Display Hint
//				if(hintsOn)	
//				{
//					String hint;
//					hint = getHint(puz.getPuzzleName(currentPuzzle));
//					JOptionPane.showMessageDialog(null, hint, "Hint!", JOptionPane.PLAIN_MESSAGE);
//
//				}
//				//Restart Timer
//				bl.restartTimer();
//
//			}catch (Exception ex){//Catch exception if any
//				System.err.println("Error: " + ex.getMessage());
//			}    
		}
		else if (e.getSource() == SixButton){
			startGame();
			Restart(true, true, true);
			currentPuzzle =puz.setSix();
			bestScore = RecordMove[currentPuzzle];
			BestScoreLabel.setText("Best Score:" + bestScore);
			bl.getPuzzleFrame().setVisible(false);
			//Log the Puzzle Change
//			try{
//				FileWriter log = new FileWriter("log.txt", true);
//				log.write("\t" + getTime() + "Six Puzzle Selected.\n");
//				log.close();
//
//				//Display Hint
//				if(hintsOn)	
//				{
//					String hint;
//					hint = getHint(puz.getPuzzleName(currentPuzzle));
//					JOptionPane.showMessageDialog(null, hint, "Hint!", JOptionPane.PLAIN_MESSAGE);
//
//				}
//				//Restart Timer
//				bl.restartTimer();
//
//			}catch (Exception ex){//Catch exception if any
//				System.err.println("Error: " + ex.getMessage());
//			}
		}
		else if (e.getSource() == CanYouHearMeNowButton){
			startGame();
			Restart(true, true, true);
			currentPuzzle =puz.setCanYouHearMeNow();
			bestScore = RecordMove[currentPuzzle];
			BestScoreLabel.setText("Best Score:" + bestScore);
			bl.getPuzzleFrame().setVisible(false);
			//Log the Puzzle Change
//			try{
//				FileWriter log = new FileWriter("log.txt", true);
//				log.write("\t" + getTime() + " Can You Hear Me Now Puzzle Selected.\n");
//				log.close();
//
//				//Display Hint
//				if(hintsOn)	
//				{
//					String hint;
//					hint = getHint(puz.getPuzzleName(currentPuzzle));
//					JOptionPane.showMessageDialog(null, hint, "Hint!", JOptionPane.PLAIN_MESSAGE);
//
//				}
//				//Restart Timer
//				bl.restartTimer();
//
//			}catch (Exception ex){//Catch exception if any
//				System.err.println("Error: " + ex.getMessage());
//			}    
		}
		else if (e.getSource() == StarrySkyButton){
			startGame();
			Restart(true, true, true);
			currentPuzzle =puz.setStarrySky();
			bestScore = RecordMove[currentPuzzle];
			BestScoreLabel.setText("Best Score:" + bestScore);
			bl.getPuzzleFrame().setVisible(false);
			//Log the Puzzle Change
//			try{
//				FileWriter log = new FileWriter("log.txt", true);
//				log.write("\t" + getTime() + " Starry Sky Puzzle Selected.\n");
//				log.close();
//
//				//Display Hint
//				if(hintsOn)	
//				{
//					String hint;
//					hint = getHint(puz.getPuzzleName(currentPuzzle));
//					JOptionPane.showMessageDialog(null, hint, "Hint!", JOptionPane.PLAIN_MESSAGE);
//
//				}
//				//Restart Timer
//				bl.restartTimer();
//
//			}catch (Exception ex){//Catch exception if any
//				System.err.println("Error: " + ex.getMessage());
//			}    
		}
		else if (e.getSource() == LoomEx6Button){
			startGame();
			Restart(true, true, true);
			currentPuzzle =puz.setLoomEx6();
			bestScore = RecordMove[currentPuzzle];
			BestScoreLabel.setText("Best Score:" + bestScore);
			bl.getPuzzleFrame().setVisible(false);
			//Log the Puzzle Change
//			try{
//				FileWriter log = new FileWriter("log.txt", true);
//				log.write("\t" + getTime() + " Loom Example 6 Puzzle Selected.\n");
//				log.close();
//
//				//Display Hint
//				if(hintsOn)	
//				{
//					String hint;
//					hint = getHint(puz.getPuzzleName(currentPuzzle));
//					JOptionPane.showMessageDialog(null, hint, "Hint!", JOptionPane.PLAIN_MESSAGE);
//
//				}
//				//Restart Timer
//				bl.restartTimer();
//
//			}catch (Exception ex){//Catch exception if any
//				System.err.println("Error: " + ex.getMessage());
//			}	    
		}
		else if (e.getSource() == LoomEx8Button){
			startGame();
			Restart(true, true, true);
			currentPuzzle =puz.setLoomEx8();
			bestScore = RecordMove[currentPuzzle];
			BestScoreLabel.setText("Best Score:" + bestScore);
			bl.getPuzzleFrame().setVisible(false);
			//Log the Puzzle Change
//			try{
//				FileWriter log = new FileWriter("log.txt", true);
//				log.write("\t" + getTime() + " Loom Example 8 Puzzle Selected.\n");
//				log.close();
//
//				//Display Hint
//				if(hintsOn)	
//				{
//					String hint;
//					hint = getHint(puz.getPuzzleName(currentPuzzle));
//					JOptionPane.showMessageDialog(null, hint, "Hint!", JOptionPane.PLAIN_MESSAGE);
//
//				}
//				//Restart Timer
//				bl.restartTimer();
//
//			}catch (Exception ex){//Catch exception if any
//				System.err.println("Error: " + ex.getMessage());
//			}
		}
		else if (e.getSource() == CircleButton){
			startGame();
			Restart(true, true, true);
			currentPuzzle =puz.setCircle();
			bestScore = RecordMove[currentPuzzle];
			BestScoreLabel.setText("Best Score:" + bestScore);
			bl.getPuzzleFrame().setVisible(false);
			//Log the Puzzle Change
//			try{
//				FileWriter log = new FileWriter("log.txt", true);
//				log.write("\t" + getTime() + " Circle Puzzle Selected.\n");
//				log.close();
//
//				//Display Hint
//				if(hintsOn)	
//				{
//					String hint;
//					hint = getHint(puz.getPuzzleName(currentPuzzle));
//					JOptionPane.showMessageDialog(null, hint, "Hint!", JOptionPane.PLAIN_MESSAGE);
//
//				}
//				//Restart Timer
//				bl.restartTimer();
//
//			}catch (Exception ex){//Catch exception if any
//				System.err.println("Error: " + ex.getMessage());
//			}   
		}
		else if (e.getSource() == FlagButton){
			if(mediumPuzzlesUnlocked())
			{
				startGame();
				Restart(true, true, true);
				currentPuzzle =puz.setFlag();
				bestScore = RecordMove[currentPuzzle];
				BestScoreLabel.setText("Best Score:" + bestScore);
				bl.getPuzzleFrame().setVisible(false);
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Complete all the Easy Puzzles to Unlock the Medium Ones!", "Locked!", JOptionPane.PLAIN_MESSAGE);
			}
			//Log the Puzzle Change
//			try{
//				FileWriter log = new FileWriter("log.txt", true);
//				log.write("\t" + getTime() + " Flag Puzzle Selected.\n");
//				log.close();
//
//				//Display Hint
//				if(hintsOn)	
//				{
//					String hint;
//					hint = getHint(puz.getPuzzleName(currentPuzzle));
//					JOptionPane.showMessageDialog(null, hint, "Hint!", JOptionPane.PLAIN_MESSAGE);
//
//				}
//				//Restart Timer
//				bl.restartTimer();
//
//			}catch (Exception ex){//Catch exception if any
//				System.err.println("Error: " + ex.getMessage());
//			}
		}
		else if (e.getSource() == UNCCButton){
			if(mediumPuzzlesUnlocked())
			{
				startGame();
				Restart(true, true, true);
				currentPuzzle =puz.setUNCC();
				bestScore = RecordMove[currentPuzzle];
				BestScoreLabel.setText("Best Score:" + bestScore);
				bl.getPuzzleFrame().setVisible(false);
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Complete all the Easy Puzzles to Unlock the Medium Ones!", "Locked!", JOptionPane.PLAIN_MESSAGE);
			}
			//Log the Puzzle Change
//			try{
//				FileWriter log = new FileWriter("log.txt", true);
//				log.write("\t" + getTime() + " UNCC Puzzle Selected.\n");
//				log.close();
//
//				//Display Hint
//				if(hintsOn)	
//				{
//					String hint;
//					hint = getHint(puz.getPuzzleName(currentPuzzle));
//					JOptionPane.showMessageDialog(null, hint, "Hint!", JOptionPane.PLAIN_MESSAGE);
//
//				}
//				//Restart Timer
//				bl.restartTimer();
//
//			}catch (Exception ex){//Catch exception if any
//				System.err.println("Error: " + ex.getMessage());
//			}    
		}
		else if (e.getSource() == LoomEx10Button){
			if(mediumPuzzlesUnlocked())
			{
				startGame();
				Restart(true, true, true);
				currentPuzzle =puz.setLoomEx10();
				bestScore = RecordMove[currentPuzzle];
				BestScoreLabel.setText("Best Score:" + bestScore);
				bl.getPuzzleFrame().setVisible(false);
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Complete all the Easy Puzzles to Unlock the Medium Ones!", "Locked!", JOptionPane.PLAIN_MESSAGE);
			}
			//Log the Puzzle Change
//			try{
//				FileWriter log = new FileWriter("log.txt", true);
//				log.write("\t" + getTime() + " Loom Example 10 Puzzle Selected.\n");
//				log.close();
//
//				//Display Hint
//				if(hintsOn)	
//				{
//					String hint;
//					hint = getHint(puz.getPuzzleName(currentPuzzle));
//					JOptionPane.showMessageDialog(null, hint, "Hint!", JOptionPane.PLAIN_MESSAGE);
//
//				}
//				//Restart Timer
//				bl.restartTimer();
//
//			}catch (Exception ex){//Catch exception if any
//				System.err.println("Error: " + ex.getMessage());
//			}
		}
		else if (e.getSource() == LoomEx13Button){
			if(mediumPuzzlesUnlocked())
			{
				startGame();
				Restart(true, true, true);
				currentPuzzle =puz.setLoomEx13();
				bestScore = RecordMove[currentPuzzle];
				BestScoreLabel.setText("Best Score:" + bestScore);
				bl.getPuzzleFrame().setVisible(false);
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Complete all the Easy Puzzles to Unlock the Medium Ones!", "Locked!", JOptionPane.PLAIN_MESSAGE);
			}
			//Log the Puzzle Change
//			try{
//				FileWriter log = new FileWriter("log.txt", true);
//				log.write("\t" + getTime() + " Loom Example 13 Puzzle Selected.\n");
//				log.close();
//
//				//Display Hint
//				if(hintsOn)	
//				{
//					String hint;
//					hint = getHint(puz.getPuzzleName(currentPuzzle));
//					JOptionPane.showMessageDialog(null, hint, "Hint!", JOptionPane.PLAIN_MESSAGE);
//
//				}
//				//Restart Timer
//				bl.restartTimer();
//
//			}catch (Exception ex){//Catch exception if any
//				System.err.println("Error: " + ex.getMessage());
//			}  
		}
		else if (e.getSource() == LoomEx14Button){
			startGame();
			Restart(true, true, true);
			currentPuzzle =puz.setLoomEx14();
			bestScore = RecordMove[currentPuzzle];
			BestScoreLabel.setText("Best Score:" + bestScore);
			bl.getPuzzleFrame().setVisible(false);
			//Log the Puzzle Change
//			try{
//				FileWriter log = new FileWriter("log.txt", true);
//				log.write("\t" + getTime() + " Loom Example 14 Puzzle Selected.\n");
//				log.close();
//
//				//Display Hint
//				if(hintsOn)	
//				{
//					String hint;
//					hint = getHint(puz.getPuzzleName(currentPuzzle));
//					JOptionPane.showMessageDialog(null, hint, "Hint!", JOptionPane.PLAIN_MESSAGE);
//
//				}
//				//Restart Timer
//				bl.restartTimer();
//
//			}catch (Exception ex){//Catch exception if any
//				System.err.println("Error: " + ex.getMessage());
//			}	    
		}
		else if (e.getSource() == SunriseButton){
			if(mediumPuzzlesUnlocked())
			{
				startGame();
				Restart(true, true, true);
				currentPuzzle =puz.setSunRise();
				bestScore = RecordMove[currentPuzzle];
				BestScoreLabel.setText("Best Score:" + bestScore);
				bl.getPuzzleFrame().setVisible(false);
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Complete all the Easy Puzzles to Unlock the Medium Ones!", "Locked!", JOptionPane.PLAIN_MESSAGE);
			}
			//Log the Puzzle Change
//			try{
//				FileWriter log = new FileWriter("log.txt", true);
//				log.write("\t" + getTime() + " Sunrise Puzzle Selected.\n");
//				log.close();
//
//				//Display Hint
//				if(hintsOn)	
//				{
//					String hint;
//					hint = getHint(puz.getPuzzleName(currentPuzzle));
//					JOptionPane.showMessageDialog(null, hint, "Hint!", JOptionPane.PLAIN_MESSAGE);
//
//				}
//				//Restart Timer
//				bl.restartTimer();
//
//			}catch (Exception ex){//Catch exception if any
//				System.err.println("Error: " + ex.getMessage());
//			}    
		}
		else if (e.getSource() == HeartButton){
			if(mediumPuzzlesUnlocked())
			{
				startGame();
				Restart(true, true, true);
				currentPuzzle =puz.setHeart();
				bestScore = RecordMove[currentPuzzle];
				BestScoreLabel.setText("Best Score:" + bestScore);
				bl.getPuzzleFrame().setVisible(false);
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Complete all the Easy Puzzles to Unlock the Medium Ones!", "Locked!", JOptionPane.PLAIN_MESSAGE);
			}
			//Log the Puzzle Change
//			try{
//				FileWriter log = new FileWriter("log.txt", true);
//				log.write("\t" + getTime() + " Heart Puzzle Selected.\n");
//				log.close();
//
//				//Display Hint
//				if(hintsOn)	
//				{
//					String hint;
//					hint = getHint(puz.getPuzzleName(currentPuzzle));
//					JOptionPane.showMessageDialog(null, hint, "Hint!", JOptionPane.PLAIN_MESSAGE);
//
//				}
//				//Restart Timer
//				bl.restartTimer();
//
//			}catch (Exception ex){//Catch exception if any
//				System.err.println("Error: " + ex.getMessage());
//			}
		}
		else if (e.getSource() == SunnySkyButton){
			if(mediumPuzzlesUnlocked())
			{
				startGame();
				Restart(true, true, true);
				currentPuzzle =puz.setSunnySky();
				bestScore = RecordMove[currentPuzzle];
				BestScoreLabel.setText("Best Score:" + bestScore);
				bl.getPuzzleFrame().setVisible(false);
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Complete all the Easy Puzzles to Unlock the Medium Ones!", "Locked!", JOptionPane.PLAIN_MESSAGE);
			}
			//Log the Puzzle Change
//			try{
//				FileWriter log = new FileWriter("log.txt", true);
//				log.write("\t" + getTime() + " Sunny Sky Puzzle Selected.\n");
//				log.close();
//
//				//Display Hint
//				if(hintsOn)	
//				{
//					String hint;
//					hint = getHint(puz.getPuzzleName(currentPuzzle));
//					JOptionPane.showMessageDialog(null, hint, "Hint!", JOptionPane.PLAIN_MESSAGE);
//
//				}
//				//Restart Timer
//				bl.restartTimer();
//
//			}catch (Exception ex){//Catch exception if any
//				System.err.println("Error: " + ex.getMessage());
//			}    
		}
		else if (e.getSource() == StarsAndStripesButton){
			if(hardPuzzlesUnlocked())
			{
				startGame();
				Restart(true, true, true);
				currentPuzzle =puz.setStarsAndStripes();
				bestScore = RecordMove[currentPuzzle];
				BestScoreLabel.setText("Best Score:" + bestScore);
				bl.getPuzzleFrame().setVisible(false);
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Complete all the Medium Puzzles to Unlock the Hard Ones!", "Locked!", JOptionPane.PLAIN_MESSAGE);
			}
			//Log the Puzzle Change
//			try{
//				FileWriter log = new FileWriter("log.txt", true);
//				log.write("\t" + getTime() + " Stars and Stripes Puzzle Selected.\n");
//				log.close();
//
//				//Display Hint
//				if(hintsOn)	
//				{
//					String hint;
//					hint = getHint(puz.getPuzzleName(currentPuzzle));
//					JOptionPane.showMessageDialog(null, hint, "Hint!", JOptionPane.PLAIN_MESSAGE);
//
//				}
//				//Restart Timer
//				bl.restartTimer();
//
//			}catch (Exception ex){//Catch exception if any
//				System.err.println("Error: " + ex.getMessage());
//			}
		}
		else if (e.getSource() == ROSSButton){
			if(hardPuzzlesUnlocked())
			{
				startGame();
				Restart(true, true, true);
				currentPuzzle =puz.setROSS();
				bestScore = RecordMove[currentPuzzle];
				BestScoreLabel.setText("Best Score:" + bestScore);
				bl.getPuzzleFrame().setVisible(false);
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Complete all the Medium Puzzles to Unlock the Hard Ones!", "Locked!", JOptionPane.PLAIN_MESSAGE);
			}
			//Log the Puzzle Change
//			try{
//				FileWriter log = new FileWriter("log.txt", true);
//				log.write("\t" + getTime() + " ROSS Puzzle Selected.\n");
//				log.close();
//
//				//Display Hint
//				if(hintsOn)	
//				{
//					String hint;
//					hint = getHint(puz.getPuzzleName(currentPuzzle));
//					JOptionPane.showMessageDialog(null, hint, "Hint!", JOptionPane.PLAIN_MESSAGE);
//
//				}
//				//Restart Timer
//				bl.restartTimer();
//
//			}catch (Exception ex){//Catch exception if any
//				System.err.println("Error: " + ex.getMessage());
//			}
		}
		else if (e.getSource() == BullseyeButton){
			if(hardPuzzlesUnlocked())
			{
				startGame();
				Restart(true, true, true);
				currentPuzzle =puz.setBullseye();
				bestScore = RecordMove[currentPuzzle];
				BestScoreLabel.setText("Best Score:" + bestScore);
				bl.getPuzzleFrame().setVisible(false);
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Complete all the Medium Puzzles to Unlock the Hard Ones!", "Locked!", JOptionPane.PLAIN_MESSAGE);
			}
			//Log the Puzzle Change
//			try{
//				FileWriter log = new FileWriter("log.txt", true);
//				log.write("\t" + getTime() + " Bullseye Puzzle Selected.\n");
//				log.close();
//
//				//Display Hint
//				if(hintsOn)	
//				{
//					String hint;
//					hint = getHint(puz.getPuzzleName(currentPuzzle));
//					JOptionPane.showMessageDialog(null, hint, "Hint!", JOptionPane.PLAIN_MESSAGE);
//
//				}
//				//Restart Timer
//				bl.restartTimer();
//
//			}catch (Exception ex){//Catch exception if any
//				System.err.println("Error: " + ex.getMessage());
//			}
		}
		else if (e.getSource() == LoomEx7Button){
			if(hardPuzzlesUnlocked())
			{
				startGame();
				Restart(true, true, true);
				currentPuzzle =puz.setLoomEx7();
				bestScore = RecordMove[currentPuzzle];
				BestScoreLabel.setText("Best Score:" + bestScore);
				bl.getPuzzleFrame().setVisible(false);
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Complete all the Medium Puzzles to Unlock the Hard Ones!", "Locked!", JOptionPane.PLAIN_MESSAGE);
			}
			//Log the Puzzle Change
//			try{
//				FileWriter log = new FileWriter("log.txt", true);
//				log.write("\t" + getTime() + " Loom Example 7 Puzzle Selected.\n");
//				log.close();
//
//				//Display Hint
//				if(hintsOn)	
//				{
//					String hint;
//					hint = getHint(puz.getPuzzleName(currentPuzzle));
//					JOptionPane.showMessageDialog(null, hint, "Hint!", JOptionPane.PLAIN_MESSAGE);
//
//				}
//				//Restart Timer
//				bl.restartTimer();
//
//			}catch (Exception ex){//Catch exception if any
//				System.err.println("Error: " + ex.getMessage());
//			}
		}
		else if (e.getSource() == LoomEx1Button){
			if(hardPuzzlesUnlocked())
			{
				startGame();
				Restart(true, true, true);
				currentPuzzle =puz.setLoomEx1();
				bestScore = RecordMove[currentPuzzle];
				BestScoreLabel.setText("Best Score:" + bestScore);
				bl.getPuzzleFrame().setVisible(false);
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Complete all the Medium Puzzles to Unlock the Hard Ones!", "Locked!", JOptionPane.PLAIN_MESSAGE);
			}
			//Log the Puzzle Change
//			try{
//				FileWriter log = new FileWriter("log.txt", true);
//				log.write("\t" + getTime() + " Loom Example 1 Puzzle Selected.\n");
//				log.close();
//
//				//Display Hint
//				if(hintsOn)	
//				{
//					String hint;
//					hint = getHint(puz.getPuzzleName(currentPuzzle));
//					JOptionPane.showMessageDialog(null, hint, "Hint!", JOptionPane.PLAIN_MESSAGE);
//
//				}
//				//Restart Timer
//				bl.restartTimer();
//
//			}catch (Exception ex){//Catch exception if any
//				System.err.println("Error: " + ex.getMessage());
//			}
		}
		else if (e.getSource() == LoomEx5Button){
			if(hardPuzzlesUnlocked())
			{
				startGame();
				Restart(true, true, true);
				currentPuzzle =puz.setLoomEx5();
				bestScore = RecordMove[currentPuzzle];
				BestScoreLabel.setText("Best Score:" + bestScore);
				bl.getPuzzleFrame().setVisible(false);
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Complete all the Medium Puzzles to Unlock the Hard Ones!", "Locked!", JOptionPane.PLAIN_MESSAGE);
			}
			//Log the Puzzle Change
//			try{
//				FileWriter log = new FileWriter("log.txt", true);
//				log.write("\t" + getTime() + " Loom Example 5 Puzzle Selected.\n");
//				log.close();
//
//				//Display Hint
//				if(hintsOn)	
//				{
//					String hint;
//					hint = getHint(puz.getPuzzleName(currentPuzzle));
//					JOptionPane.showMessageDialog(null, hint, "Hint!", JOptionPane.PLAIN_MESSAGE);
//
//				}
//				//Restart Timer
//				bl.restartTimer();
//
//			}catch (Exception ex){//Catch exception if any
//				System.err.println("Error: " + ex.getMessage());
//			}
		}
		else if (e.getSource() == LoomEx3Button){
			if(hardPuzzlesUnlocked())
			{
				startGame();
				Restart(true, true, true);
				currentPuzzle =puz.setLoomEx3();
				bestScore = RecordMove[currentPuzzle];
				BestScoreLabel.setText("Best Score:" + bestScore);
				bl.getPuzzleFrame().setVisible(false);
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Complete all the Medium Puzzles to Unlock the Hard Ones!", "Locked!", JOptionPane.PLAIN_MESSAGE);
			}
			//Log the Puzzle Change
//			try{
//				FileWriter log = new FileWriter("log.txt", true);
//				log.write("\t" + getTime() + " Loom Example 3 Puzzle Selected.\n");
//				log.close();
//
//				//Display Hint
//				if(hintsOn)	
//				{
//					String hint;
//					hint = getHint(puz.getPuzzleName(currentPuzzle));
//					JOptionPane.showMessageDialog(null, hint, "Hint!", JOptionPane.PLAIN_MESSAGE);
//
//				}
//				//Restart Timer
//				bl.restartTimer();
//
//			}catch (Exception ex){//Catch exception if any
//				System.err.println("Error: " + ex.getMessage());
//			}
		}
		else if (e.getSource() == MoonButton){
			if(mediumPuzzlesUnlocked())
			{
				startGame();
				Restart(true, true, true);
				currentPuzzle =puz.setMoon();
				bestScore = RecordMove[currentPuzzle];
				BestScoreLabel.setText("Best Score:" + bestScore);
				bl.getPuzzleFrame().setVisible(false);
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Complete all the Easy Puzzles to Unlock the Medium Ones!", "Locked!", JOptionPane.PLAIN_MESSAGE);
			}
			//Log the Puzzle Change
//			try{
//				FileWriter log = new FileWriter("log.txt", true);
//				log.write("\t" + getTime() + " Moon Puzzle Selected.\n");
//				log.close();
//
//				//Display Hint
//				if(hintsOn)	
//				{
//					String hint;
//					hint = getHint(puz.getPuzzleName(currentPuzzle));
//					JOptionPane.showMessageDialog(null, hint, "Hint!", JOptionPane.PLAIN_MESSAGE);
//
//				}
//				//Restart Timer
//				bl.restartTimer();
//
//			}catch (Exception ex){//Catch exception if any
//				System.err.println("Error: " + ex.getMessage());
//			}
		}
		else if (e.getSource() == MegamanButton){
			if(hardPuzzlesUnlocked())
			{
				startGame();
				Restart(true, true, true);
				currentPuzzle =puz.setMegaman();
				bestScore = RecordMove[currentPuzzle];
				BestScoreLabel.setText("Best Score:" + bestScore);
				bl.getPuzzleFrame().setVisible(false);
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Complete all the Medium Puzzles to Unlock the Hard Ones!", "Locked!", JOptionPane.PLAIN_MESSAGE);
			}
			//Log the Puzzle Change
//			try{
//				FileWriter log = new FileWriter("log.txt", true);
//				log.write("\t" + getTime() + " Megaman Puzzle Selected.\n");
//				log.close();
//
//				//Display Hint
//				if(hintsOn)	
//				{
//					String hint;
//					hint = getHint(puz.getPuzzleName(currentPuzzle));
//					JOptionPane.showMessageDialog(null, hint, "Hint!", JOptionPane.PLAIN_MESSAGE);
//
//				}
//				//Restart Timer
//				bl.restartTimer();
//
//			}catch (Exception ex){//Catch exception if any
//				System.err.println("Error: " + ex.getMessage());
//			}
		}
		else if (e.getSource() == BatmanButton){
			if(hardPuzzlesUnlocked())
			{
				startGame();
				Restart(true, true, true);
				currentPuzzle =puz.setBatman();
				bestScore = RecordMove[currentPuzzle];
				BestScoreLabel.setText("Best Score:" + bestScore);
				bl.getPuzzleFrame().setVisible(false);
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Complete all the Medium Puzzles to Unlock the Hard Ones!", "Locked!", JOptionPane.PLAIN_MESSAGE);
			}
			//Log the Puzzle Change
//			try{
//				FileWriter log = new FileWriter("log.txt", true);
//				log.write("\t" + getTime() + " Batman Puzzle Selected.\n");
//				log.close();
//
//				//Display Hint
//				if(hintsOn)	
//				{
//					String hint;
//					hint = getHint(puz.getPuzzleName(currentPuzzle));
//					JOptionPane.showMessageDialog(null, hint, "Hint!", JOptionPane.PLAIN_MESSAGE);
//
//				}
//				//Restart Timer
//				bl.restartTimer();
//
//			}catch (Exception ex){//Catch exception if any
//				System.err.println("Error: " + ex.getMessage());
//			}
		}
		else if (e.getSource() == DCButton){
			if(hardPuzzlesUnlocked())
			{
				startGame();
				Restart(true, true, true);
				currentPuzzle =puz.setDC();
				bestScore = RecordMove[currentPuzzle];
				BestScoreLabel.setText("Best Score:" + bestScore);
				bl.getPuzzleFrame().setVisible(false);
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Complete all the Medium Puzzles to Unlock the Hard Ones!", "Locked!", JOptionPane.PLAIN_MESSAGE);
			}
			//Log the Puzzle Change
//			try{
//				FileWriter log = new FileWriter("log.txt", true);
//				log.write("\t" + getTime() + " DC Puzzle Selected.\n");
//				log.close();
//
//				//Display Hint
//				if(hintsOn)	
//				{
//					String hint;
//					hint = getHint(puz.getPuzzleName(currentPuzzle));
//					JOptionPane.showMessageDialog(null, hint, "Hint!", JOptionPane.PLAIN_MESSAGE);
//
//				}
//				//Restart Timer
//				bl.restartTimer();
//
//			}catch (Exception ex){//Catch exception if any
//				System.err.println("Error: " + ex.getMessage());
//			}
		}
		else if (e.getSource() == SergeantButton){
			if(mediumPuzzlesUnlocked())
			{
				startGame();
				Restart(true, true, true);
				currentPuzzle =puz.setSergeant();
				bestScore = RecordMove[currentPuzzle];
				BestScoreLabel.setText("Best Score:" + bestScore);
				bl.getPuzzleFrame().setVisible(false);
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Complete all the Easy Puzzles to Unlock the Medium Ones!", "Locked!", JOptionPane.PLAIN_MESSAGE);
			}
			//Log the Puzzle Change
//			try{
//				FileWriter log = new FileWriter("log.txt", true);
//				log.write("\t" + getTime() + " Sergeant Puzzle Selected.\n");
//				log.close();
//
//				//Display Hint
//				if(hintsOn)	
//				{
//					String hint;
//					hint = getHint(puz.getPuzzleName(currentPuzzle));
//					JOptionPane.showMessageDialog(null, hint, "Hint!", JOptionPane.PLAIN_MESSAGE);
//
//				}
//				//Restart Timer
//				bl.restartTimer();
//
//			}catch (Exception ex){//Catch exception if any
//				System.err.println("Error: " + ex.getMessage());
//			}
		}

		else if (e.getSource() == TieFighterButton){
			if(hardPuzzlesUnlocked())
			{
				startGame();
				Restart(true, true, true);
				currentPuzzle = puz.setTieFighter();
				bestScore = RecordMove[currentPuzzle];
				BestScoreLabel.setText("Best Score:" + bestScore);
				bl.getPuzzleFrame().setVisible(false);
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Complete all the Medium Puzzles to Unlock the Hard Ones!", "Locked!", JOptionPane.PLAIN_MESSAGE);
			}
			//Log the Puzzle Change
//			try{
//				FileWriter log = new FileWriter("log.txt", true);
//				log.write("\t" + getTime() + " Tie Fighter Puzzle Selected.\n");
//				log.close();
//
//				//Display Hint
//				if(hintsOn)	
//				{
//					String hint;
//					hint = getHint(puz.getPuzzleName(currentPuzzle));
//					JOptionPane.showMessageDialog(null, hint, "Hint!", JOptionPane.PLAIN_MESSAGE);
//
//				}
//				//Restart Timer
//				bl.restartTimer();
//
//			}catch (Exception ex){//Catch exception if any
//				System.err.println("Error: " + ex.getMessage());
//			}
		}

		else if (e.getSource() == CancelButton){
			puz.setPuzzle(currentPuzzle);
			bl.getPuzzleFrame().setVisible(false);
			bl.getMainMenuFrame().setVisible(true);
			bl.getGridFrame().setVisible(true);
			bl.getGridFrame2().setVisible(true);
		}
		else if (e.getSource() == TutButton){
			//			EasyButton.setBounds(LeftDifR);
			//			ChoosePuzzlePanel.add(EasyButton);
			//			MediumButton.setBounds(CenterDifR);
			//			ChoosePuzzlePanel.add(MediumButton);
			//			HardButton.setBounds(RightDifR);
			//			ChoosePuzzlePanel.add(HardButton);
			//			ChoosePuzzlePanel.remove(TutButton);
			showTutorialButtons();
		}
		else if (e.getSource() == EasyButton){
			//			TutButton.setBounds(LeftDifR);
			//			ChoosePuzzlePanel.add(TutButton);
			//			MediumButton.setBounds(CenterDifR);
			//			ChoosePuzzlePanel.add(MediumButton);
			//			HardButton.setBounds(RightDifR);
			//			ChoosePuzzlePanel.add(HardButton);
			//			ChoosePuzzlePanel.remove(EasyButton);

			removePuzzleButtons();
			hidePuzzleButtons();

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
//			if(		(RecordMedalShort[14].equals("N") ||
//					RecordMedalShort[13].equals("N") ||	
//					RecordMedalShort[12].equals("N") ||
//					RecordMedalShort[11].equals("N") ||
//					RecordMedalShort[10].equals("N") ||
//					RecordMedalShort[9].equals("N") ||
//					RecordMedalShort[8].equals("N") ||
//					RecordMedalShort[7].equals("N") ||
//					RecordMedalShort[6].equals("N")) &&
//					ComponentToggle.unlockableDifficultyLevels) {
//				JOptionPane.showMessageDialog(null, "Complete all the Easy Puzzles to Unlock the Medium Ones!", "Locked!", JOptionPane.PLAIN_MESSAGE);
//			}
//			else
			{
				//			EasyButton.setBounds(LeftDifR);
				//			ChoosePuzzlePanel.add(EasyButton);
				//			TutButton.setBounds(CenterDifR);
				//			ChoosePuzzlePanel.add(TutButton);
				//			HardButton.setBounds(RightDifR);
				//			ChoosePuzzlePanel.add(HardButton);
				//			ChoosePuzzlePanel.remove(MediumButton);
				removePuzzleButtons();
				hidePuzzleButtons();

				ChoosePuzzlePanel.add(SunnySkyButton);
				ChoosePuzzlePanel.add(HeartButton);
				ChoosePuzzlePanel.add(SunriseButton);
				ChoosePuzzlePanel.add(FlagButton);
				ChoosePuzzlePanel.add(LoomEx13Button);
				ChoosePuzzlePanel.add(LoomEx10Button);
				ChoosePuzzlePanel.add(UNCCButton);
				ChoosePuzzlePanel.add(SergeantButton);
				ChoosePuzzlePanel.add(MoonButton);

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

				HSLabel8.setText(""+ RecordMove[22]);
				MLabel8.setText(RecordMedal[22]);

				HSLabel9.setText(""+ RecordMove[23]);
				MLabel9.setText(RecordMedal[23]);

				HSLabelA.setText(" ");
				MLabelA.setText(" ");

				HSLabelB.setText(" ");
				MLabelB.setText(" ");
			}
		}
		else if (e.getSource() == HardButton){
//			if(		(RecordMedalShort[34].equals("N") ||
//					RecordMedalShort[33].equals("N") ||
//					RecordMedalShort[21].equals("N") ||	
//					RecordMedalShort[20].equals("N") ||	
//					RecordMedalShort[19].equals("N") ||
//					RecordMedalShort[18].equals("N") ||
//					RecordMedalShort[17].equals("N") ||
//					RecordMedalShort[16].equals("N") ||
//					RecordMedalShort[15].equals("N")) &&
//					ComponentToggle.unlockableDifficultyLevels){
//				JOptionPane.showMessageDialog(null, "Complete all the Medium Puzzles to Unlock the Hard Ones!", "Locked!", JOptionPane.PLAIN_MESSAGE);
//			}
//			else
			{
				removePuzzleButtons();
				hidePuzzleButtons();

				ChoosePuzzlePanel.add(TieFighterButton);
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

				HSLabel1.setText("" + RecordMove[24]);
				MLabel1.setText(RecordMedal[24]);

				HSLabel2.setText("" + RecordMove[25]);
				MLabel2.setText(RecordMedal[25]);

				HSLabel3.setText("" + RecordMove[26]);
				MLabel3.setText(RecordMedal[26]);

				HSLabel4.setText("" + RecordMove[27]);
				MLabel4.setText(RecordMedal[27]);

				HSLabel5.setText("" + RecordMove[28]);
				MLabel5.setText(RecordMedal[28]);

				HSLabel6.setText("" + RecordMove[29]);
				MLabel6.setText(RecordMedal[29]);

				HSLabel7.setText(""+RecordMove[30]);
				MLabel7.setText(RecordMedal[30]);

				HSLabel8.setText(""+RecordMove[31]);
				MLabel8.setText(RecordMedal[31]);

				HSLabel9.setText(""+RecordMove[32]);
				MLabel9.setText(RecordMedal[32]);

				HSLabelA.setText(""+RecordMove[33]);
				MLabelA.setText(RecordMedal[33]);

				HSLabelB.setText(""+RecordMove[34]);
				MLabelB.setText(RecordMedal[34]);
			}
		}
		else if (e.getSource() == LoadGameButton) {
			if(!puz.setCustomPuzzle(BeadLoom.playerName, "GameSaves", 1))
			{
				JOptionPane.showMessageDialog(null, "Could not load game.\nNo Saved Game Found", "No Save Game!", JOptionPane.PLAIN_MESSAGE);
			}
			else
			{
				loadSavedGame();
			}
		}
		else if (e.getSource() == RedButton){
			bl.getInputTools().setColor(Color.RED);
			bl.getColorFrame().setVisible(false);
			bl.getMoveBeads().getTopColorsButton().setBackground(Color.RED);
			bl.getMoveBeads().getTopColorsButton().setForeground(Color.BLACK);
			bl.getMoveBeads().getTopColorsButton().setText("Red");
			//Log the Color Change
//			try{
//				FileWriter log = new FileWriter("log.txt", true);
//				log.write("\t\t" + getTime() + " Color Change: Red\n");
//				log.close();
//			}catch (Exception ex){//Catch exception if any
//				System.err.println("Error: " + ex.getMessage());
//			}
		}
		else if (e.getSource() == GreenButton){
			bl.getInputTools().setColor(Color.GREEN);
			bl.getColorFrame().setVisible(false);
			bl.getMoveBeads().getTopColorsButton().setBackground(Color.GREEN);
			bl.getMoveBeads().getTopColorsButton().setForeground(Color.BLACK);
			bl.getMoveBeads().getTopColorsButton().setText("Green");
			//Log the Color Change
//			try{
//				FileWriter log = new FileWriter("log.txt", true);
//				log.write("\t\t" + getTime() + " Color Change: Green\n");
//				log.close();
//			}catch (Exception ex){//Catch exception if any
//				System.err.println("Error: " + ex.getMessage());
//			}
		}
		else if (e.getSource() == BlueButton){
			bl.getInputTools().setColor(Color.BLUE);
			bl.getColorFrame().setVisible(false);
			bl.getMoveBeads().getTopColorsButton().setBackground(Color.BLUE);
			bl.getMoveBeads().getTopColorsButton().setText("Blue");
			bl.getMoveBeads().getTopColorsButton().setForeground(Color.WHITE);
			//Log the Color Change
//			try{
//				FileWriter log = new FileWriter("log.txt", true);
//				log.write("\t\t" + getTime() + " Color Change: Blue\n");
//				log.close();
//			}catch (Exception ex){//Catch exception if any
//				System.err.println("Error: " + ex.getMessage());
//			}
		}
		else if (e.getSource() == YellowButton){
			bl.getInputTools().setColor(Color.YELLOW);
			bl.getColorFrame().setVisible(false);
			bl.getMoveBeads().getTopColorsButton().setBackground(Color.YELLOW);
			bl.getMoveBeads().getTopColorsButton().setText("Yellow");
			bl.getMoveBeads().getTopColorsButton().setForeground(Color.BLACK);
			//Log the Color Change
//			try{
//				FileWriter log = new FileWriter("log.txt", true);
//				log.write("\t\t" + getTime() + " Color Change: Yellow\n");
//				log.close();
//			}catch (Exception ex){//Catch exception if any
//				System.err.println("Error: " + ex.getMessage());
//			}
		}
		else if (e.getSource() == CyanButton){
			bl.getInputTools().setColor(Color.CYAN);
			bl.getColorFrame().setVisible(false);
			bl.getMoveBeads().getTopColorsButton().setBackground(Color.CYAN);
			bl.getMoveBeads().getTopColorsButton().setText("Cyan");
			bl.getMoveBeads().getTopColorsButton().setForeground(Color.BLACK);
			//Log the Color Change
//			try{
//				FileWriter log = new FileWriter("log.txt", true);
//				log.write("\t\t" + getTime() + " Color Change: Cyan\n");
//				log.close();
//			}catch (Exception ex){//Catch exception if any
//				System.err.println("Error: " + ex.getMessage());
//			}
		}
		else if (e.getSource() == MagentaButton){
			bl.getInputTools().setColor(Color.MAGENTA);
			bl.getColorFrame().setVisible(false);
			bl.getMoveBeads().getTopColorsButton().setBackground(Color.MAGENTA);
			bl.getMoveBeads().getTopColorsButton().setText("Magenta");
			bl.getMoveBeads().getTopColorsButton().setForeground(Color.BLACK);
			//Log the Color Change
//			try{
//				FileWriter log = new FileWriter("log.txt", true);
//				log.write("\t\t" + getTime() + " Color Change: Magenta\n");
//				log.close();
//			}catch (Exception ex){//Catch exception if any
//				System.err.println("Error: " + ex.getMessage());
//			}
		}
		else if (e.getSource() == OrangeButton){
			bl.getInputTools().setColor(betterOrange);
			bl.getColorFrame().setVisible(false);
			bl.getMoveBeads().getTopColorsButton().setBackground(betterOrange);
			bl.getMoveBeads().getTopColorsButton().setText("Orange");
			bl.getMoveBeads().getTopColorsButton().setForeground(Color.BLACK);
			//Log the Color Change
//			try{
//				FileWriter log = new FileWriter("log.txt", true);
//				log.write("\t\t" + getTime() + " Color Change: Orange\n");
//				log.close();
//			}catch (Exception ex){//Catch exception if any
//				System.err.println("Error: " + ex.getMessage());
//			}
		}
		else if (e.getSource() == PinkButton){
			bl.getInputTools().setColor(Color.PINK);
			bl.getColorFrame().setVisible(false);
			bl.getMoveBeads().getTopColorsButton().setBackground(Color.PINK);
			bl.getMoveBeads().getTopColorsButton().setText("Pink");
			bl.getMoveBeads().getTopColorsButton().setForeground(Color.BLACK);
			//Log the Color Change
//			try{
//				FileWriter log = new FileWriter("log.txt", true);
//				log.write("\t\t" + getTime() + " Color Change: Pink\n");
//				log.close();
//			}catch (Exception ex){//Catch exception if any
//				System.err.println("Error: " + ex.getMessage());
//			}
		}
		else if (e.getSource() == PeachButton){
			bl.getInputTools().setColor(peach);
			bl.getColorFrame().setVisible(false);
			bl.getMoveBeads().getTopColorsButton().setBackground(peach);
			bl.getMoveBeads().getTopColorsButton().setText("Peach");
			bl.getMoveBeads().getTopColorsButton().setForeground(Color.BLACK);
			//Log the Color Change
//			try{
//				FileWriter log = new FileWriter("log.txt", true);
//				log.write("\t\t" + getTime() + " Color Change: Peach\n");
//				log.close();
//			}catch (Exception ex){//Catch exception if any
//				System.err.println("Error: " + ex.getMessage());
//			}
		}
		else if (e.getSource() == GrayButton) {
			bl.getInputTools().setColor(Color.GRAY);
			bl.getColorFrame().setVisible(false);
			bl.getMoveBeads().getTopColorsButton().setBackground(Color.GRAY);
			bl.getMoveBeads().getTopColorsButton().setText("Gray");
			bl.getMoveBeads().getTopColorsButton().setForeground(Color.BLACK);
			//Log the color change
//			try{
//				FileWriter log = new FileWriter("log.txt", true);
//				log.write("\t\t" + getTime() + " Color Change: Gray\n");
//				log.close();
//			}catch (Exception ex){ System.err.println("Error: " + ex.getMessage());
//			}
		}

		else if (e.getSource() == BlackButton){
			bl.getInputTools().setColor(Color.BLACK);
			bl.getColorFrame().setVisible(false);
			bl.getMoveBeads().getTopColorsButton().setBackground(Color.BLACK);
			bl.getMoveBeads().getTopColorsButton().setText("Black");
			bl.getMoveBeads().getTopColorsButton().setForeground(Color.WHITE);
			//Log the Color Change
//			try{
//				FileWriter log = new FileWriter("log.txt", true);
//				log.write("\t\t" + getTime() + " Color Change: Black\n");
//				log.close();
//			}catch (Exception ex){//Catch exception if any
//				System.err.println("Error: " + ex.getMessage());
//			}
		}
		else if (e.getSource() == WhiteButton){
			bl.getInputTools().setColor(Color.WHITE);
			bl.getColorFrame().setVisible(false);
			bl.getMoveBeads().getTopColorsButton().setBackground(Color.WHITE);
			bl.getMoveBeads().getTopColorsButton().setText("White");
			bl.getMoveBeads().getTopColorsButton().setForeground(Color.BLACK);
			//Log the Color Change
//			try{
//				FileWriter log = new FileWriter("log.txt", true);
//				log.write("\t\t" + getTime() + " Color Change: White\n");
//				log.close();
//			}catch (Exception ex){//Catch exception if any
//				System.err.println("Error: " + ex.getMessage());
//			}
		}
		//set avatar button

	}
	
	private void loadSavedGame() {
		startGame();
		Restart(true, true, true);
		puz.setCustomPuzzle(BeadLoom.playerName, "GameSaves", 1);

		//load info from customPuzzleName
		String[] items = puz.getCustomPuzzleName().split(",");
		if(items.length <3) {
			setMainMenuMode();
			return;
		}
		int totalMoves = Integer.parseInt(items[0]);
		long timeElapsed = Long.parseLong(items[1]);
		currentPuzzle = Integer.parseInt(items[2]);

		bestScore = RecordMove[currentPuzzle];
		BestScoreLabel.setText("Best Score:" + bestScore);
		bl.getPuzzleFrame().setVisible(false);
		puzzleStartTime = System.currentTimeMillis() - timeElapsed;
		moveCounter = totalMoves;
		bl.setPuzzleTimer(timeElapsed);
	}

	public void showChoosePuzzle() {
		bl.getMainMenuFrame().setVisible(false);
		bl.getPuzzleFrame().setVisible(true);
		bl.getPuzzleFrame().toFront();
		//Set up the Tutorial Menu
		EasyButton.setBounds(LeftDifR);	
		ChoosePuzzlePanel.add(EasyButton);
		MediumButton.setBounds(CenterDifR);
		ChoosePuzzlePanel.add(MediumButton);
		HardButton.setBounds(RightDifR);
		ChoosePuzzlePanel.add(HardButton);

		if(!(MLabel1.getText().length() > 1))
			{
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
	}

	//this redraws everything on both grids when the bead image is changed
	private void redrawGrid()
	{
		bl.getGridPanel().rebuildLayerImages();
		bl.getGridPanel2().rebuildLayerImages();
		bl.getContentPanel().repaint();
	}
	
	public String sendPost(String sendURL, String sendString)
	{
		if(ComponentToggle.userAccounts)
		{
			StringBuilder builder = new StringBuilder();
	
			try {
				URLConnection con = new URL(sendURL).openConnection();
				con.setDoOutput(true);
				OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
				wr.write(sendString);
				wr.flush();
				
				
				BufferedReader in = new BufferedReader(
						new InputStreamReader(
								con.getInputStream()));
				String inputLine;
	
				while ((inputLine = in.readLine()) != null)  {
	
					builder.append(inputLine);
				}
				wr.close();
				in.close();
			} catch (Exception ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}
			
			return builder.toString();
		}
		else
		{
			return "This action not available as a guest.";
		}
	}
	
	public void setCustomPuzzleUsers()
	{
		LoadCustomPuzzleUserDropBox.removeAllItems();
		String userlist = sendGuestWebRequest(BeadLoom.WEB_ADDRESS + BeadLoom.SCRIPTS_FOLDER + "/customPuzzles.php?token=userList");
		String[] users = userlist.split(",");
		for(int i=0; i<users.length; i++)
		{
			LoadCustomPuzzleUserDropBox.addItem(users[i]);
		}
	}
	
	public void setCustomPuzzleNames(String user)
	{
		try
		{
			LoadCustomPuzzleDropBox.removeAllItems();
		}
		catch(Exception e){}
		String puzzlelist = sendGuestWebRequest(BeadLoom.WEB_ADDRESS + BeadLoom.SCRIPTS_FOLDER + "/customPuzzles.php?token=puzzleList&user="+LoadCustomPuzzleUserDropBox.getSelectedItem());
		if(!puzzlelist.equalsIgnoreCase("error"))
		{
			String[] puzzles = puzzlelist.split(",");
			for(int i=0; i<puzzles.length; i++)
			{
				LoadCustomPuzzleDropBox.addItem(puzzles[i]);
			}
		}
	}
	
	public void sendImagePost(File file, String folder, String name)
	{
		if(ComponentToggle.userAccounts)
		{
			StringBuilder builder = new StringBuilder(); 
			try {
				String puzzleName = playerName + "-" + CustomPuzzleTextField.getText();
				URL test = new URL(BeadLoom.WEB_ADDRESS + BeadLoom.SCRIPTS_FOLDER + "/image.php?fileName=" + name + "&folderName=" + folder);
				URLConnection con = test.openConnection();
				con.setRequestProperty("Content-Type", "application/octet-stream");;
				con.setRequestProperty("Content-Disposition", "attachment; filename=image.png");
				con.setDoOutput(true);
				con.setDoInput(true);
				
				OutputStream wr = con.getOutputStream();
				DataInputStream ds = new DataInputStream(new FileInputStream(file));
				byte[] data  = new byte[(int)file.length()];
				ds.readFully(data,0, (int)file.length());
				System.out.println(file.length());
				wr.write(data);
				wr.flush();
				ds.close();
				
				BufferedReader in = new BufferedReader(
						new InputStreamReader(
								con.getInputStream()));
				String inputLine;
	
				while ((inputLine = in.readLine()) != null) 
				{
					builder.append(inputLine);
				}
				wr.close();
				in.close();
				if(builder.toString().length() > 0)
				JOptionPane.showMessageDialog(null, builder.toString(), "Echo messages", JOptionPane.PLAIN_MESSAGE);
			} catch (Exception ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}
		}
	}
	
	public String sendCustomPuzzlePost(String fileContents, String url, String puzzleName, String folderName)
	{
		if(ComponentToggle.userAccounts)
		{
			StringBuilder builder = new StringBuilder(); 
			try {
				URL test = new URL(url);
				URLConnection con = test.openConnection();
				con.setDoOutput(true);
				OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
				wr.write("folderName="+folderName);
				wr.write("&fileName="+puzzleName);
				wr.write("&file=" + URLEncoder.encode(fileContents, "UTF-8"));
				wr.flush();
				
				
				BufferedReader in = new BufferedReader(
						new InputStreamReader(
								con.getInputStream()));
				String inputLine;
	
				while ((inputLine = in.readLine()) != null)  {
	
					builder.append(inputLine);
				}
				wr.close();
				in.close();
				if(builder.toString().length() > 0)
				{
					JOptionPane.showMessageDialog(null, builder.toString(), "Echo messages", JOptionPane.PLAIN_MESSAGE);
				}
				
			} catch (Exception ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}
			return builder.toString();
		}
		else
		{
			return "This action not available as a guest.";
		}
	}
	
	public String sendGuestFilePost(String fileContents, String url, String puzzleName, String folderName)
	{
		StringBuilder builder = new StringBuilder(); 
		try {
			URL test = new URL(url);
			URLConnection con = test.openConnection();
			con.setDoOutput(true);
			OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
			wr.write("folderName="+folderName);
			wr.write("&fileName="+puzzleName);
			wr.write("&file=" + URLEncoder.encode(fileContents, "UTF-8"));
			wr.flush();
			
			
			BufferedReader in = new BufferedReader(
					new InputStreamReader(
							con.getInputStream()));
			String inputLine;

			while ((inputLine = in.readLine()) != null)  {

				builder.append(inputLine);
			}
			wr.close();
			in.close();
			if(builder.toString().length() > 0)
			{
				JOptionPane.showMessageDialog(null, builder.toString(), "Echo messages", JOptionPane.PLAIN_MESSAGE);
			}
			
		} catch (Exception ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
		return builder.toString();
	}

	public boolean getHintModeStatus()
	{
		return hintsOn;
	}

	public void setHintModeStatus(boolean isOn)
	{
		hintsOn = isOn;
	}
	
	public void getScores()
	{
		if (!NameTextField.getText().equalsIgnoreCase("admin") && ComponentToggle.userAccounts) {
			String temp = sendWebRequest(BeadLoom.WEB_ADDRESS + BeadLoom.SCRIPTS_FOLDER + "/playerScores.php?user="
					+ NameTextField.getText());
			String[] scores = temp.split(",");
			int totalPuzzles = puz.getTotalPuzzles();
			RecordMove = new int[totalPuzzles];
			RecordMedalShort = new String[totalPuzzles];
			RecordMedal = new String[totalPuzzles];
			for (int i = 0; i < RecordMove.length; i++) {
				RecordMove[i] = 1682;
				RecordMedalShort[i] = "N";
				RecordMedal[i] = "-None-";
			}
			if(scores.length > 2)
			{
				for (int i = 0; i < scores.length; i += 3) {
					if(!scores[i+2].equalsIgnoreCase("Custom"))
					{
						int index = puz.getPuzzleIndex(scores[i]);
						RecordMove[index] = Integer.parseInt(scores[i + 1]);
						RecordMedal[index] = scores[i + 2];
						RecordMedalShort[index] = scores[i + 2].substring(0, 0);
					}
				}
			}
		}
		else
		{
			int totalPuzzles = puz.getTotalPuzzles();
			RecordMove = new int[totalPuzzles];
			RecordMedalShort = new String[totalPuzzles];
			RecordMedal = new String[totalPuzzles];
			for (int i = 0; i < RecordMove.length; i++) {
				RecordMove[i] = 1;
				RecordMedalShort[i] = "P";
				RecordMedal[i] = "Platinum!!!!";
			}
		}
	}
	
	public void showTutorialButtons()
	{
		removePuzzleButtons();
		hidePuzzleButtons();

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

	public String getHint(String puzzleName)
	{
		String hint = "";
		try
		{
			puzzleName = URLEncoder.encode(puzzleName, "UTF-8");
			hint = sendWebRequest(BeadLoom.WEB_ADDRESS + BeadLoom.SCRIPTS_FOLDER + "/hints.php?puzzleName=" + puzzleName + "&user=" + BeadLoom.playerName);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return hint;
	}

	//set up the content pane for Main Menu Mode
	public void setMainMenuMode()
	{
		//Send Log to Server
		if(!PuzzleLog.IsEmpty() && currentPuzzle == -1)
		{
			PuzzleLog.AddLayer("\n  <exitNoSubmits />");
			if(ComponentToggle.userAccounts)
			{
				sendCustomPuzzlePost(PuzzleLog.GetLog(puz.getCustomPuzzleName()), BeadLoom.WEB_ADDRESS + BeadLoom.SCRIPTS_FOLDER + "/echo.php", BeadLoom.playerName, "PuzzleLogs");
			}
			else
			{
				sendGuestFilePost(PuzzleLog.GetLog(puz.getCustomPuzzleName()), BeadLoom.WEB_ADDRESS + BeadLoom.SCRIPTS_FOLDER + "/echo.php", BeadLoom.guestPlayerName, "PuzzleLogs");
			}
			PuzzleLog.Clear();
		}
		//Send Log to Server
		else if(!PuzzleLog.IsEmpty())
		{
			PuzzleLog.AddLayer("\n  <exitNoSubmits />");
			if(ComponentToggle.userAccounts)
			{
				sendCustomPuzzlePost(PuzzleLog.GetLog(puz.getPuzzleName(currentPuzzle)), BeadLoom.WEB_ADDRESS + BeadLoom.SCRIPTS_FOLDER + "/echo.php", BeadLoom.playerName, "PuzzleLogs");
			}
			else
			{
				sendGuestFilePost(PuzzleLog.GetLog(puz.getPuzzleName(currentPuzzle)), BeadLoom.WEB_ADDRESS + BeadLoom.SCRIPTS_FOLDER + "/echo.php", BeadLoom.guestPlayerName, "PuzzleLogs");
			}
			PuzzleLog.Clear();
		}
		
		if(avatarMode)
		{
			sendWebRequest(BeadLoom.WEB_ADDRESS + BeadLoom.SCRIPTS_FOLDER + "/avatar.php?avatar="+avatarPuzzle + "&user=" + BeadLoom.playerName);
			avatarMode = false;
		}
		String puzNum = sendWebRequest(BeadLoom.WEB_ADDRESS + BeadLoom.SCRIPTS_FOLDER + "/avatar.php?user=" + BeadLoom.playerName);
		if(puzNum.length()>0 && ComponentToggle.userAccounts) { avatarPuzzle = Integer.parseInt(puzNum); }
		else {avatarPuzzle = -1; }
		if(avatarPuzzle < -1) { avatarPuzzle = -1; }

		hidePuzzleButtons();
		removeAllWindows();
		
		NameTextField.setText(BeadLoom.playerName);
		JPanel panel = bl.getContentPanel();
		
		//set up options menu bead selected

		if(BeadLoom.beadLocation.equalsIgnoreCase(BeadLoom.WEB_ADDRESS + "/games/BeadLoomGame/bead.jpg"))
		{
			NormalBeadButton.setBackground(Color.CYAN);
			AwesomeBeadButton.setBackground(UIManager.getColor("Button.background"));
			PeaceBeadButton.setBackground(UIManager.getColor("Button.background"));
			EightBitBeadButton.setBackground(UIManager.getColor("Button.background"));
		}
		else if(BeadLoom.beadLocation.equalsIgnoreCase(BeadLoom.WEB_ADDRESS + "/games/BeadLoomGame/awesomeBead.jpg"))
		{
			NormalBeadButton.setBackground(UIManager.getColor("Button.background"));
			AwesomeBeadButton.setBackground(Color.CYAN);
			PeaceBeadButton.setBackground(UIManager.getColor("Button.background"));
			EightBitBeadButton.setBackground(UIManager.getColor("Button.background"));
		}
		else if(BeadLoom.beadLocation.equalsIgnoreCase(BeadLoom.WEB_ADDRESS + "/games/BeadLoomGame/peaceBead.jpg"))
		{
			NormalBeadButton.setBackground(UIManager.getColor("Button.background"));
			AwesomeBeadButton.setBackground(UIManager.getColor("Button.background"));
			PeaceBeadButton.setBackground(Color.CYAN);
			EightBitBeadButton.setBackground(UIManager.getColor("Button.background"));
		}
		else if(BeadLoom.beadLocation.equalsIgnoreCase(BeadLoom.WEB_ADDRESS + "/games/BeadLoomGame/8BitBead.jpg"))
		{
			NormalBeadButton.setBackground(UIManager.getColor("Button.background"));
			AwesomeBeadButton.setBackground(UIManager.getColor("Button.background"));
			PeaceBeadButton.setBackground(UIManager.getColor("Button.background"));
			EightBitBeadButton.setBackground(Color.CYAN);
		}
		

		//TODO insert code to add main menu here
		bl.getMainMenuFrame().setBounds(((int)BeadLoom.usableDim.getWidth())/2-100, ((int)BeadLoom.usableDim.getHeight())/2-275, 200, 550);
		bl.getMainMenuFrame().setVisible(true);
		panel.add(bl.getMainMenuFrame());
		panel.add(bl.getGameOptionsFrame());
		bl.getGameOptionsFrame().setVisible(false);
		panel.add(bl.getPuzzleFrame());
		bl.getPuzzleFrame().setVisible(false);
		panel.add(bl.getHighScoresFrame());
		bl.getHighScoresFrame().setVisible(false);
		bl.getCustomPuzzleOptionsFrame().setVisible(false);
		panel.add(bl.getCustomPuzzleOptionsFrame());
		//load Avatar and logo
		bl.getGridPanel2().clear();
		
		try {
			if(puz.setCustomPuzzle("GameLogo", "Avatars", 2))
			{
				MainMenuPanel.remove(logoIcon);
				int logoSize = 123;
				logoIcon = new JLabel(new ImageIcon(
						bl.createImageFromGrid().getScaledInstance(
						logoSize, logoSize, 0)));
				MainMenuPanel.remove(logoIcon);
				logoIcon.setVisible(true);
				logoIcon.setBounds((200-logoSize)/2, 25, logoSize, logoSize);
				MainMenuPanel.add(logoIcon);
				avatarIcon.repaint();
			}
			
			if(ComponentToggle.avatars)
			{
				bl.getGridPanel2().clear();
				if(avatarPuzzle==-1)
				{
					if(puz.setCustomPuzzle("MyAvatar-"+BeadLoom.playerName, "Avatars", 2))
					{
						MainMenuPanel.remove(avatarIcon);
						int avatarSize = 123;
						avatarIcon = new JLabel(new ImageIcon(
								bl.createImageFromGrid().getScaledInstance(
								avatarSize, avatarSize, 0)));
						MainMenuPanel.remove(avatarIcon);
						avatarIcon.setVisible(true);
						avatarIcon.setBounds((200-avatarSize)/2,355, avatarSize, avatarSize);
						MainMenuPanel.add(avatarIcon);
						avatarIcon.repaint();
					}
					else { avatarIcon.setVisible(false); }
				}
				else
				{
					puz.setPuzzle(avatarPuzzle);
					MainMenuPanel.remove(avatarIcon);
					int avatarSize = 123;
					avatarIcon = new JLabel(new ImageIcon(
							bl.createImageFromGrid().getScaledInstance(
							avatarSize, avatarSize, 0)));
					//MainMenuPanel.remove(avatarIcon);
					avatarIcon.setVisible(true);
					avatarIcon.setBounds((200-avatarSize)/2,355, avatarSize, avatarSize);
					MainMenuPanel.add(avatarIcon);
					avatarIcon.repaint();
				}
			}
			else
			{
				avatarIcon.setVisible(false);
				CreateAvatarButton.setVisible(false);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean mediumPuzzlesUnlocked()
	{
		if(		(RecordMedalShort[14].equals("N") ||
				RecordMedalShort[13].equals("N") ||	
				RecordMedalShort[12].equals("N") ||
				RecordMedalShort[11].equals("N") ||
				RecordMedalShort[10].equals("N") ||
				RecordMedalShort[9].equals("N") ||
				RecordMedalShort[8].equals("N") ||
				RecordMedalShort[7].equals("N") ||
				RecordMedalShort[6].equals("N")) &&
				ComponentToggle.unlockableDifficultyLevels)
		{
			return false;
		}
		else { return true; }
	}
	
	public boolean hardPuzzlesUnlocked()
	{
		if(		(RecordMedalShort[34].equals("N") ||
				RecordMedalShort[33].equals("N") ||
				RecordMedalShort[21].equals("N") ||	
				RecordMedalShort[20].equals("N") ||	
				RecordMedalShort[19].equals("N") ||
				RecordMedalShort[18].equals("N") ||
				RecordMedalShort[17].equals("N") ||
				RecordMedalShort[16].equals("N") ||
				RecordMedalShort[15].equals("N")) &&
				ComponentToggle.unlockableDifficultyLevels)
		{
			return false;
		}
		else { return true; }
	}

	//set up the content pane for Custom Puzzle Creation Mode
	public void setCustomPuzzleMode()
	{
		//Do this to reposition the windows
		startGame();
		getScores();
		removeAllWindows();

		JPanel panel = bl.getContentPanel();
		panel.add(bl.getCustomPuzzleMenuFrame());
		bl.getCustomPuzzleMenuFrame().setBounds((int)(BeadLoom.usableDim.getWidth()*0.37) + BeadLoom.panelBorder, (int)(BeadLoom.usableDim.getHeight()*0.65) + BeadLoom.panelBorder, (int)(BeadLoom.usableDim.getWidth()*0.20) - BeadLoom.panelBorder, (int)(BeadLoom.usableDim.getHeight()*0.35) - BeadLoom.panelBorder);
		panel.add(bl.getGridFrame());
		panel.add(bl.getColorFrame());
		bl.getColorFrame().setVisible(false);
		bl.getGridFrame().setVisible(true);
		panel.add(bl.getBeadUtilitiesFrame());
		if(ClearButton.getParent() != null){ ClearButton.getParent().remove(ClearButton); }
		CustomPuzzleMenuPanel.add(ClearButton);
		if(MainMenuButton.getParent()  !=  null) { MainMenuButton.getParent().remove(MainMenuButton); }
		CustomPuzzleMenuPanel.add(MainMenuButton);
		if(UndoButton.getParent()  !=  null) { UndoButton.getParent().remove(UndoButton); }
		CustomPuzzleMenuPanel.add(UndoButton);
		NameLabel.getParent().remove(NameLabel);
		CustomPuzzleMenuPanel.add(NameLabel);
		if(ComponentToggle.codeOuputWindow) { panel.add(bl.getOutputWindow()); }
		bl.getTimerLabel().setVisible(false);
		limitedColorChoice = true;
		setColorButtonsVisbility(true);
		panel.repaint();
		bl.getInputTools().setGrid(bl.getGridPanel());
	}
	
	//set custom puzzle mode with loadPuzzle loaded
	public void setCustomPuzzleMode(String loadPuzzle, String puzName)
	{
		setCustomPuzzleMode();
		puz.setCustomPuzzle(loadPuzzle, "CustomPuzzles", 1);
		CustomPuzzleTextField.setText(puzName);
	}

	//set up the content pane for Avatar Creation Mode
	public void setCreateAvatarMode()
	{
		if(ComponentToggle.userAccounts)
		{
			//Do this to reposition the windows
			startGame();
			getScores();
			removeAllWindows();
			
			initAvatarMenu();
			avatarMode = true;
			String puzNum = sendWebRequest(BeadLoom.WEB_ADDRESS + BeadLoom.SCRIPTS_FOLDER + "/avatar.php?user=" + BeadLoom.playerName);
			if(puzNum.length()>0) 
			{
				avatarPuzzle = Integer.parseInt(puzNum); 
			}
			else { avatarPuzzle = -1; }
	
			JPanel panel = bl.getContentPanel();
			panel.add(bl.getAvatarMenuFrame());
			bl.getCustomPuzzleMenuFrame().setBounds((int)(BeadLoom.usableDim.getWidth()*0.37) + BeadLoom.panelBorder, (int)(BeadLoom.usableDim.getHeight()*0.65) + BeadLoom.panelBorder, (int)(BeadLoom.usableDim.getWidth()*0.20) - BeadLoom.panelBorder, (int)(BeadLoom.usableDim.getHeight()*0.35) - BeadLoom.panelBorder);
			panel.add(bl.getGridFrame());
			bl.getGridFrame().setVisible(true);
			panel.add(bl.getBeadUtilitiesFrame());
			panel.add(bl.getColorFrame());
			bl.getColorFrame().setVisible(false);
			panel.add(bl.getPuzzleFrame());
			hidePuzzleButtons();
			NameLabel.getParent().remove(NameLabel);
			CustomPuzzleMenuPanel.add(NameLabel);
			if(ClearButton.getParent() != null){ ClearButton.getParent().remove(ClearButton); }
			AvatarMenuPanel.add(ClearButton);
			if(MainMenuButton.getParent() != null) { MainMenuButton.getParent().remove(MainMenuButton); }
			AvatarMenuPanel.add(MainMenuButton);
			if(UndoButton.getParent()  !=  null) { UndoButton.getParent().remove(UndoButton); }
			AvatarMenuPanel.add(UndoButton);
			if(ComponentToggle.codeOuputWindow) { panel.add(bl.getOutputWindow()); }
			limitedColorChoice = true;
			setAvatarColorButtons();
			bl.getInputTools().setColor(Color.BLACK);
			bl.getColorFrame().setVisible(false);
			bl.getMoveBeads().getTopColorsButton().setBackground(Color.BLACK);
			bl.getMoveBeads().getTopColorsButton().setText("Black");
			bl.getMoveBeads().getTopColorsButton().setForeground(Color.WHITE);
			bl.getTimerLabel().setVisible(false);
			panel.repaint();
			bl.getInputTools().setGrid(bl.getGridPanel());
			if(!puz.setCustomPuzzle("MyAvatar-"+BeadLoom.playerName, "Avatars", 1))
			{
				JOptionPane.showMessageDialog(null, "Complete more game puzzles to unlock more colors for your custom Avatar", "Custom Avatar Creation", JOptionPane.PLAIN_MESSAGE);
			}
		}
		else
		{
			JOptionPane.showMessageDialog(null, "You must be logged in to create an avatar.", "Guest Restriction", JOptionPane.PLAIN_MESSAGE);
		}
	}

	//set up the content pane for Game Play Mode
	public void setGamePlayMode()
	{
		
		removeAllWindows();

		JPanel panel = bl.getContentPanel();
		panel.add(bl.getHighScoresFrame());
		bl.getHighScoresFrame().setVisible(false);
		panel.add(bl.getGridFrame());
		panel.add(bl.getGridFrame2());
		bl.getGridFrame().setVisible(true);
		bl.getGridFrame2().setVisible(true);
		if(ComponentToggle.codeOuputWindow) { panel.add(bl.getOutputWindow()); }
		panel.add(bl.getPuzzleFrame());
		panel.add(bl.getBeadUtilitiesFrame());
		if(MainMenuButton.getParent()  !=  null) { MainMenuButton.getParent().remove(MainMenuButton); }
		InGamePanel.add(MainMenuButton);
		bl.getInGameFrame().setVisible(true);
		panel.add(bl.getInGameFrame());
		panel.add(bl.getColorFrame());
		bl.getColorFrame().setVisible(false);
		limitedColorChoice = true;
		setColorButtonsVisbility(true);
		bl.getTimerLabel().setVisible(true);
		panel.repaint();
		if(ComponentToggle.securityEnabled){ Security.setSecurityToken(); }
		Achievements.retrieveAchievements(BeadLoom.playerName);
		
		
	}

	//set up the content pane for Tool Mode
	public void setToolMode()
	{
		removeAllWindows();
		
		//TODO: remove this hack
		setGamePlayMode();
		quitGame();
		bl.getContentPanel().remove(bl.getInGameFrame());
		bl.getContentPanel().add(bl.getGameFrame());
		if(MainMenuButton.getParent()  !=  null) { MainMenuButton.getParent().remove(MainMenuButton); }
		bl.getGameFrame().add(GamePanel);
		GamePanel.add(MainMenuButton);
		limitedColorChoice = false;
		bl.getTimerLabel().setVisible(false);
	}

	//remove all windows from the main content pane
	public void removeAllWindows()
	{
		bl.getContentPanel().removeAll();
		bl.getContentPanel().repaint();
	}
	
	public String getGridXML(String puzzleName)
	{
		String puzzleXML = "<custompuzzle Name=\""+puzzleName+"\">\n";
		for(int i=0; i<bl.getGridPanel().getLayers().size(); i++)
		{
			puzzleXML += bl.getGridPanel().getLayers().get(i).toXMLString() + "\n";
		}
		puzzleXML += "</custompuzzle>";
		
		return puzzleXML;
	}
	
	//sets the players name in the text box
	public void setPlayerName(String name)
	{
		NameTextField.setText(name);
	}
	
	//Return the number of puzzles the player has completed
	public int puzzlesCompleted()
	{
		int complete = 0;
		for(int i=0; i<RecordMedalShort.length; i++)
		{
			if(!RecordMedalShort[i].equals("N")) { complete++; }
		}
		return complete;
	}
	
	public void createCustomPuzzleImage()
	{
		bl.getGridPanel().calcGameGrid();
		Image temp = bl.createImageFromGrid1().getScaledInstance(PuzzleThumbnail.width, PuzzleThumbnail.width, 0);

		BufferedImage bufferedImage = new BufferedImage(temp.getWidth(null), temp.getHeight(null), BufferedImage.TYPE_INT_RGB);
		Graphics2D graphics = bufferedImage.createGraphics();
		graphics.drawImage(temp, null, null);
		String desktopPath = System.getProperty("user.home") + "/Desktop";
		File imageFile = new File(desktopPath, playerName + "-" + CustomPuzzleTextField.getText()+ ".png");
		try
		{
			ImageIO.write(bufferedImage, "png", imageFile);
		}
		catch(Exception e1)
		{
			e1.printStackTrace();
		}
		
		sendImagePost(imageFile, "CustomPuzzleImages", playerName + "-" + CustomPuzzleTextField.getText());
		//Ask user if they would like their custom puzzle image created on the desktop
		if(JOptionPane.showConfirmDialog(null, "Would you like an image of your puzzle on the desktop", "Image", JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION)
		{
			imageFile.delete();
		}
	}
	
	public void createAvatarImage()
	{
		bl.getGridPanel().calcGameGrid();
		Image temp = bl.createImageFromGrid1().getScaledInstance(PuzzleThumbnail.width, PuzzleThumbnail.width, 0);

		BufferedImage bufferedImage = new BufferedImage(temp.getWidth(null), temp.getHeight(null), BufferedImage.TYPE_INT_RGB);
		Graphics2D graphics = bufferedImage.createGraphics();
		graphics.drawImage(temp, null, null);
		String desktopPath = System.getProperty("user.home") + "/Desktop";
		File imageFile = new File(desktopPath, playerName + ".png");
		try
		{
			ImageIO.write(bufferedImage, "png", imageFile);
		}
		catch(Exception e1)
		{
			e1.printStackTrace();
		}
		
		sendImagePost(imageFile, "AvatarImages", playerName);
		//Ask user if they would like their custom puzzle image created on the desktop
		if(JOptionPane.showConfirmDialog(null, "Would you like an image of your avatar on the desktop", "Image", JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION)
		{
			imageFile.delete();
		}
	}
	
	public void setColorButtonsVisbility(boolean visible)
	{
		GreenButton.setVisible(visible);
		RedButton.setVisible(visible);
		YellowButton.setVisible(visible);
		BlueButton.setVisible(visible);
		MagentaButton.setVisible(visible);
		CyanButton.setVisible(visible);
		OrangeButton.setVisible(visible);
		WhiteButton.setVisible(visible);
		BlackButton.setVisible(visible);
		PinkButton.setVisible(visible);
		PeachButton.setVisible(visible);
		GrayButton.setVisible(visible);
	}
	
	public void setAvatarColorButtons()
	{
		setColorButtonsVisbility(false);
		WhiteButton.setVisible(true);
		BlackButton.setVisible(true);
		int comp = puzzlesCompleted();
		if(comp>=35){ GreenButton.setVisible(true); }
		if(comp>=34){ PeachButton.setVisible(true); }
		if(comp>=32){ CyanButton.setVisible(true); }
		if(comp>=28){ PinkButton.setVisible(true); }
		if(comp>=24){ OrangeButton.setVisible(true); }
		if(comp>=20){ MagentaButton.setVisible(true); }
		if(comp>=16){ BlueButton.setVisible(true); }
		if(comp>=12){ YellowButton.setVisible(true); }
		if(comp>=8){ RedButton.setVisible(true); }
		if(comp>=4){ GrayButton.setVisible(true); }
		if(!ComponentToggle.unlockableAvatarContent) { setColorButtonsVisbility(true); }
	}
	
	public void hidePuzzleButtons()
	{
		if(!avatarMode || RecordMedal[0].equals("Platinum!!!!")) { Tut1Button.setVisible(true); }
		else { Tut1Button.setVisible(false); }
		if(!avatarMode || RecordMedal[1].equals("Platinum!!!!")) { Tut2Button.setVisible(true); }
		else { Tut2Button.setVisible(false); }
		if(!avatarMode || RecordMedal[2].equals("Platinum!!!!")) { Tut3Button.setVisible(true); }
		else { Tut3Button.setVisible(false); }
		if(!avatarMode || RecordMedal[3].equals("Platinum!!!!")) { Tut4Button.setVisible(true); }
		else { Tut4Button.setVisible(false); }
		if(!avatarMode || RecordMedal[4].equals("Platinum!!!!")) { Tut5Button.setVisible(true); }
		else { Tut5Button.setVisible(false); }
		if(!avatarMode || RecordMedal[5].equals("Platinum!!!!")) { Tut6Button.setVisible(true); }
		else { Tut6Button.setVisible(false); }
		
		if(!avatarMode || RecordMedal[6].equals("Platinum!!!!")) { TriforceButton.setVisible(true); }
		else { TriforceButton.setVisible(false); }
		if(!avatarMode || RecordMedal[7].equals("Platinum!!!!")) { CanYouHearMeNowButton.setVisible(true); }
		else { CanYouHearMeNowButton.setVisible(false); }
		if(!avatarMode || RecordMedal[8].equals("Platinum!!!!")) { SixButton.setVisible(true); }
		else { SixButton.setVisible(false); }
		if(!avatarMode || RecordMedal[9].equals("Platinum!!!!")) { LoomEx8Button.setVisible(true); }
		else { LoomEx8Button.setVisible(false); }
		if(!avatarMode || RecordMedal[10].equals("Platinum!!!!")) { OverlappingSquaresButton.setVisible(true); }
		else { OverlappingSquaresButton.setVisible(false); }
		if(!avatarMode || RecordMedal[11].equals("Platinum!!!!")) { CircleButton.setVisible(true); }
		else { CircleButton.setVisible(false); }
		if(!avatarMode || RecordMedal[12].equals("Platinum!!!!")) { StarrySkyButton.setVisible(true); }
		else { StarrySkyButton.setVisible(false); }
		if(!avatarMode || RecordMedal[13].equals("Platinum!!!!")) { LoomEx14Button.setVisible(true); }
		else { LoomEx14Button.setVisible(false); }
		if(!avatarMode || RecordMedal[14].equals("Platinum!!!!")) { LoomEx6Button.setVisible(true); }
		else { LoomEx6Button.setVisible(false); }

		if(!avatarMode || RecordMedal[15].equals("Platinum!!!!")) { SunnySkyButton.setVisible(true); }
		else { SunnySkyButton.setVisible(false); }
		if(!avatarMode || RecordMedal[16].equals("Platinum!!!!")) { HeartButton.setVisible(true); }
		else { HeartButton.setVisible(false); }
		if(!avatarMode || RecordMedal[17].equals("Platinum!!!!")) { SunriseButton.setVisible(true); }
		else { SunriseButton.setVisible(false); }
		if(!avatarMode || RecordMedal[18].equals("Platinum!!!!")) { FlagButton.setVisible(true); }
		else { FlagButton.setVisible(false); }
		if(!avatarMode || RecordMedal[19].equals("Platinum!!!!")) { LoomEx13Button.setVisible(true); }
		else { LoomEx13Button.setVisible(false); }
		if(!avatarMode || RecordMedal[20].equals("Platinum!!!!")) { LoomEx10Button.setVisible(true); }
		else { LoomEx10Button.setVisible(false); }
		if(!avatarMode || RecordMedal[21].equals("Platinum!!!!")) { UNCCButton.setVisible(true); }
		else { UNCCButton.setVisible(false); }
		if(!avatarMode || RecordMedal[22].equals("Platinum!!!!")) { SergeantButton.setVisible(true); }
		else { SergeantButton.setVisible(false); }
		if(!avatarMode || RecordMedal[23].equals("Platinum!!!!")) { MoonButton.setVisible(true); }
		else { MoonButton.setVisible(false); }
		
		if(!avatarMode || RecordMedal[24].equals("Platinum!!!!")) { TieFighterButton.setVisible(true); }
		else { TieFighterButton.setVisible(false); }
		if(!avatarMode || RecordMedal[25].equals("Platinum!!!!")) { LoomEx7Button.setVisible(true); }
		else { LoomEx7Button.setVisible(false); }
		if(!avatarMode || RecordMedal[26].equals("Platinum!!!!")) { DCButton.setVisible(true); }
		else { DCButton.setVisible(false); }
		if(!avatarMode || RecordMedal[27].equals("Platinum!!!!")) { BullseyeButton.setVisible(true); }
		else { BullseyeButton.setVisible(false); }
		if(!avatarMode || RecordMedal[28].equals("Platinum!!!!")) { LoomEx1Button.setVisible(true); }
		else { LoomEx1Button.setVisible(false); }
		if(!avatarMode || RecordMedal[29].equals("Platinum!!!!")) { ROSSButton.setVisible(true); }
		else { ROSSButton.setVisible(false); }
		if(!avatarMode || RecordMedal[30].equals("Platinum!!!!")) { StarsAndStripesButton.setVisible(true); }
		else { StarsAndStripesButton.setVisible(false); }
		if(!avatarMode || RecordMedal[31].equals("Platinum!!!!")) { LoomEx5Button.setVisible(true); }
		else { LoomEx5Button.setVisible(false); }
		if(!avatarMode || RecordMedal[32].equals("Platinum!!!!")) { LoomEx3Button.setVisible(true); }
		else { LoomEx3Button.setVisible(false); }
		if(!avatarMode || RecordMedal[33].equals("Platinum!!!!")) { MegamanButton.setVisible(true); }
		else { MegamanButton.setVisible(false); }
		if(!avatarMode || RecordMedal[34].equals("Platinum!!!!")) { BatmanButton.setVisible(true); }
		else { BatmanButton.setVisible(false); }
		
		//highscore and medal labels
		if(avatarMode) { HSLabel0.setVisible(false); }
		else { HSLabel0.setVisible(true); }
		if(avatarMode) { HSLabel1.setVisible(false); }
		else { HSLabel1.setVisible(true); }
		if(avatarMode) { HSLabel2.setVisible(false); }
		else { HSLabel2.setVisible(true); }
		if(avatarMode) { HSLabel3.setVisible(false); }
		else { HSLabel3.setVisible(true); }
		if(avatarMode) { HSLabel4.setVisible(false); }
		else { HSLabel4.setVisible(true); }
		if(avatarMode) { HSLabel5.setVisible(false); }
		else { HSLabel5.setVisible(true); }
		if(avatarMode) { HSLabel6.setVisible(false); }
		else { HSLabel6.setVisible(true); }
		if(avatarMode) { HSLabel7.setVisible(false); }
		else { HSLabel7.setVisible(true); }
		if(avatarMode) { HSLabel8.setVisible(false); }
		else { HSLabel8.setVisible(true); }
		if(avatarMode) { HSLabel9.setVisible(false); }
		else { HSLabel9.setVisible(true); }
		if(avatarMode) { HSLabelA.setVisible(false); }
		else { HSLabelA.setVisible(true); }
		if(avatarMode) { HSLabelB.setVisible(false); }
		else { HSLabelB.setVisible(true); }

		if(avatarMode) { MLabel0.setVisible(false); }
		else { MLabel0.setVisible(true); }
		if(avatarMode) { MLabel1.setVisible(false); }
		else { MLabel1.setVisible(true); }
		if(avatarMode) { MLabel2.setVisible(false); }
		else { MLabel2.setVisible(true); }
		if(avatarMode) { MLabel3.setVisible(false); }
		else { MLabel3.setVisible(true); }
		if(avatarMode) { MLabel4.setVisible(false); }
		else { MLabel4.setVisible(true); }
		if(avatarMode) { MLabel5.setVisible(false); }
		else { MLabel5.setVisible(true); }
		if(avatarMode) { MLabel6.setVisible(false); }
		else { MLabel6.setVisible(true); }
		if(avatarMode) { MLabel7.setVisible(false); }
		else { MLabel7.setVisible(true); }
		if(avatarMode) { MLabel8.setVisible(false); }
		else { MLabel8.setVisible(true); }
		if(avatarMode) { MLabel9.setVisible(false); }
		else { MLabel9.setVisible(true); }
		if(avatarMode) { MLabelA.setVisible(false); }
		else { MLabelA.setVisible(true); }
		if(avatarMode) { MLabelB.setVisible(false); }
		else { MLabelB.setVisible(true); }
	}
}
