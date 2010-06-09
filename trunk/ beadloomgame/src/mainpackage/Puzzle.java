package src.mainpackage;

import java.awt.Color;
import java.io.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Puzzle {

	private int ideal;
	private BeadLoom bl;
	private GUIInputTools InputTools;
	private Color defaultColor = Color.RED;
	private ArrayList<Integer> xValue;
	private ArrayList<Integer> yValue;

	private Color peach;
	private Color betterOrange;

	private final int TOTALPUZZLES = 35;
	private String[] PuzzleNames = {
			"Tutorial 1",						//0
			"Tutorial 2",						//1
			"Tutorial 3",						//2
			"Tutorial 4",						//3					
			"Tutorial 5",						//4
			"Tutorial 6",						//5
			"TriForce",							//6
			"Can You Hear Me Now",				//7		
			"Six",								//8
			"Native American Example 8",		//9			
			"Overlapping Squares",				//10		
			"Circle",							//11
			"Starry Sky",						//12
			"Native American Example 14",		//13				
			"Native American Example 6",		//14
			
			"Sunny Sky",						//15
			"Heart",							//16
			"Sun Rise",							//17
			"Flag",								//18
			"Native American Example 13",		//19				
			"Native American Example 10",		//20				
			"UNCC",								//21				
			"Sergeant",							//22
			"Tie Fighter",						//23

			"Moon",								//24
			"Native American Example 7",		//25			
			"DC",								//26			
			"Bullseye",							//27
			"Native American Example 1",		//28			
			"ROSS",								//29
			"Stars and Stripes",				//30	
			"Native American Example 5",		//31			
			"Native American Example 3",		//32			
			"Megaman",							//33
			"Batman"							//34
	};
	//Constructor.  Takes the beadloom 
	public Puzzle(BeadLoom bl){
		this.bl = bl;
		InputTools = bl.getInputTools();
		peach = new Color(255, 200, 150);
		betterOrange = new Color(255, 100, 0);
		//ReadFromXML();
	}

	public int getPuzzleIndex(String puzzleName)
	{
		for(int i=0; i<PuzzleNames.length; i++)
		{
			if(puzzleName.equals(PuzzleNames[i]))
			{
				return i;
			}
		}
		return -1;
	}
	
	//Returns the name of a puzzle based on an int index
	public String getPuzzleName(int index) {
		return PuzzleNames[index];
	}

	//Returns the total number of puzzle types
	public int getTotalPuzzles(){
		return TOTALPUZZLES;
	}

	//returns the number of moves of ideal solution of current puzzle
	public int getIdeal(){
		return ideal;
	}

	//Resets the input color and color button to default value
	public void resetColor(){
		InputTools.setColor(defaultColor);
		bl.getMoveBeads().setColor(defaultColor);
		bl.getMoveBeads().getTopColorsButton().setBackground(defaultColor);
	}

	//Mimics the calculations in GUIInputTools performed when the user clicks the Triangle Iteration Button
	//x = starting x
	//y = starting y
	//every = every ___ rows
	//add = add ___ beads to both ends
	//total = for ___ rows in total
	//incY = true if +Y or -Y.  False if +X or -X
	//isPositive = true if +X or +Y.  false if -X or -Y
	public void doTriangleIt(int x, int y, int every, int add, int total, boolean incY, boolean isPositive){
		//Initialize the coordinate arrays
		xValue = new ArrayList();
		yValue = new ArrayList();
		int width = 0, height = 0, startX = 0, startY = 0, exSteps = 0;
		double cycles = 0;

		startX = x;
		startY = y;
		width = add;
		height = every;
		cycles = total;
		exSteps = 0;

		//If stepHeight > totalRows only draw the total number of rows
		if ((cycles/(double)height) < 1)
		{
			cycles = 1;
			height = (int)cycles;
		}
		else //Check to make sure extra rows are accounted for
		{
			exSteps = (int)cycles % height;
			cycles = cycles/height;
		}

		if(!isPositive)
			height *= -1;

		InputTools.triangleIteration(startX, startY, width, exSteps, height, cycles, incY, true, xValue, yValue);
		Layer l = InputTools.CoordListAction("TRIANGLE_ITERATION", xValue, yValue);
		l.setX1(x);
		l.setY1(y);
		l.setStepHeight(every);
		l.setBeadsAdded1(add);
		l.setRowsTotal(total);
		l.setYInc(incY);
		l.setPositiveInc(isPositive);
		InputTools.addGridLayer(l);
	}

	public void doLinearIt(int x, int y, int startLength, int inc1, int inc2, int totalRows, boolean incY, boolean isPositive) {
		xValue = new ArrayList();
		yValue = new ArrayList();
		InputTools.linearIteration(x, y, startLength, inc1, inc2, totalRows, incY, isPositive, xValue, yValue);
		Layer l = InputTools.CoordListAction("LINEAR_ITERATION", xValue, yValue);
		l.setX1(x);
		l.setY1(y);
		l.setStartLength(startLength);
		l.setBeadsAdded1(inc1);
		l.setBeadsAdded2(inc2);
		l.setRowsTotal(totalRows);
		l.setYInc(incY);
		l.setPositiveInc(isPositive);
		InputTools.addGridLayer(l);
	}

	public void doLine(int x1, int y1, int x2, int y2) {
		xValue = new ArrayList();
		yValue = new ArrayList();
		Layer l = InputTools.CoordListAction("LINEAR", xValue, yValue);
		l.setX1(x1);
		l.setY1(y1);
		l.setX2(x2);
		l.setY2(y2);
		InputTools.addGridLayer(l);
		InputTools.drawLine(x1, y1, x2, y2, xValue, yValue);
	}

	//Tutorial 1
	//Shows how to make a Basic Point Call
	//Returns array pos
	//Array Pos = 0
	public int setTut1(){
		//Set Ideal value
		ideal = 4;
		//Change grid to grid Panel 2
		//This makes us edit the solution grid instead of the puzzle grid
		InputTools.setGrid(bl.getGridPanel2());

		//A Basic Point Call
		//Set Color then call draw Point
		InputTools.setColor(Color.GREEN);
		InputTools.drawPoint(-5, 5);

		InputTools.setColor(Color.RED);
		InputTools.drawPoint(5, 5);

		InputTools.setColor(Color.YELLOW);
		InputTools.drawPoint(-5, -5);

		InputTools.setColor(Color.BLUE);
		InputTools.drawPoint(5, -5);

		//Calculate the Goal Images bead array
		bl.getGridPanel2().calcGameGrid();

		//Change grid back so they can work on the puzzle
		//Reset the Current Selected Color to default
		InputTools.setGrid(bl.getGridPanel());
		resetColor();
		return 0;
	}

	//Tutorial 2
	//Shows how to make a basic line call
	//More Complex than you would think
	//Returns array pos
	//Array Pos = 1
	public int setTut2(){
		//Set Ideal value
		ideal = 2;
		//Change grid to grid Panel 2
		//This makes us edit the solution grid instead of the puzzle grid
		InputTools.setGrid(bl.getGridPanel2());

		//A Basic Line Call
		//First Set Color
		//Then initialize xValue and yValue.  
		//Call CoordListAction to initialize the CoordinateList
		//Finally call draw line with the points and xValue an yValue
		InputTools.setColor(Color.RED);
		doLine(-5, -5, 5, 5);

		//Note that you MUST reinitialize xValue and yValue for each drawLine in the puzzle
		doLine(-5, 5, 5, -5);

		//Calculate the Goal Images bead array
		bl.getGridPanel2().calcGameGrid();

		//Change grid back so they can work on the puzzle
		//Reset the Current Selected Color to default
		InputTools.setGrid(bl.getGridPanel());
		resetColor();
		return 1;
	}

	//Tutorial 3
	//Shows how to make a basic Rectangle Call
	//Returns array pos
	//Array Pos = 2
	public int setTut3(){
		//Set Ideal value
		ideal = 2;
		//Change grid to grid Panel 2
		//This makes us edit the solution grid instead of the puzzle grid
		InputTools.setGrid(bl.getGridPanel2());

		//A Basic Rectangle Call
		//First Set Color
		//Then Call drawRectangle (Careful the input order is x1 x2 y1 y2)

		InputTools.setColor(Color.RED);
		InputTools.drawRectangle(-10, -5, 10, 5);

		InputTools.setColor(Color.BLUE);
		InputTools.drawRectangle(0, 10, -5, -10);

		//Calculate the Goal Images bead array
		bl.getGridPanel2().calcGameGrid();

		//Change grid back so they can work on the puzzle
		//Reset the Current Selected Color to default
		InputTools.setGrid(bl.getGridPanel());
		resetColor();
		return 2;
	}

	//Tutorial 4
	//Shows how to make a basic Triangle Call
	//Returns array pos
	//Array Pos = 3
	public int setTut4(){
		//Set Ideal value
		ideal = 2;
		//Change grid to grid Panel 2
		//This makes us edit the solution grid instead of the puzzle grid
		InputTools.setGrid(bl.getGridPanel2());

		//A Basic Triangle Call
		//First Set Color
		//Then Call drawTriangle (Careful input order is x1 x2 x3 y1 y2 y3
		InputTools.setColor(Color.BLUE);
		InputTools.drawTriangle(8, 5, 11, 8, 5, 5);

		InputTools.setColor(Color.MAGENTA);
		InputTools.drawTriangle(-10, -10, -2, 0, -14, -7);

		//Calculate the Goal Images bead array
		bl.getGridPanel2().calcGameGrid();

		//Change grid back so they can work on the puzzle
		//Reset the Current Selected Color to default
		InputTools.setGrid(bl.getGridPanel());
		resetColor();
		return 3;
	}

	//Tutorial 5
	//Shows how to make a basic Linear Iteration Call
	//Returns array pos
	//Array Pos = 4
	public int setTut5(){
		//Set Ideal value
		ideal = 2;
		//Change grid to grid Panel 2
		//This makes us edit the solution grid instead of the puzzle grid
		InputTools.setGrid(bl.getGridPanel2());

		//A Basic Linear It Call
		//First Set Color
		//Then initialize xValue and yValue.
		//Then Call linearIteration
		InputTools.setColor(Color.BLUE);
		doLinearIt(-10, -10, 5, -1, 1, 5, true, true);

		//Note that you MUST reinitialize xValue and yValue for each linear iteration in the puzzle
		InputTools.setColor(Color.GREEN);
		doLinearIt(0, 15, 5, 2, 2, 5, true, false);

		//Calculate the Goal Images bead array
		bl.getGridPanel2().calcGameGrid();

		//Change grid back so they can work on the puzzle
		//Reset the Current Selected Color to default
		InputTools.setGrid(bl.getGridPanel());
		resetColor();
		return 4;
	}

	//Tutorial 6
	//Shows how to make a basic Triangle Iteration Call
	//Returns array pos
	//Array Pos = 5
	public int setTut6(){
		//Set Ideal value
		ideal = 2;
		//Change grid to grid Panel 2
		//This makes us edit the solution grid instead of the puzzle grid
		InputTools.setGrid(bl.getGridPanel2());

		//First Set Color
		//Then call the doTriangle It method
		//doTriangleIt does all the ugly stuff for you
		InputTools.setColor(Color.RED);
		doTriangleIt(-10, -5, 3, 1, 9, false, true);

		InputTools.setColor(Color.BLUE);
		doTriangleIt(10, 5, 9, 5, 11, true, true);

		//Calculate the Goal Images bead array
		bl.getGridPanel2().calcGameGrid();

		//Change grid back so they can work on the puzzle
		//Reset the Current Selected Color to default
		InputTools.setGrid(bl.getGridPanel());
		resetColor();
		return 5;
	}

	//Array Pos = 6
	//Returns array pos
	public int setTriforce(){
		ideal = 3;
		InputTools.setGrid(bl.getGridPanel2());

		InputTools.setColor(Color.WHITE);
		InputTools.drawRectangle(-20, 20, -20, 20);

		InputTools.setColor(Color.YELLOW);
		InputTools.drawTriangle(0, -20, 20, 19, -20, -20);

		InputTools.setColor(Color.WHITE);
		InputTools.drawTriangle(9, -9, 0, -1, -1, -19);

		bl.getGridPanel2().calcGameGrid();

		InputTools.setGrid(bl.getGridPanel());
		resetColor();
		return 6;
	}

	//Array Pos = 7
	//Returns array pos
	public int setCanYouHearMeNow(){
		ideal = 4;
		InputTools.setGrid(bl.getGridPanel2());

		InputTools.setColor(Color.GREEN);
		InputTools.drawRectangle(-20, -15, 5, 0);

		InputTools.drawRectangle(-10, -5, 10, 0);

		InputTools.drawRectangle(0, 5, 15, 0);

		InputTools.drawRectangle(10, 15, 20, 0);

		bl.getGridPanel2().calcGameGrid();

		InputTools.setGrid(bl.getGridPanel());
		resetColor();
		return 7;
	}

	//Array Pos = 8
	//Returns array pos
	public int setSix(){
		ideal = 5;
		InputTools.setGrid(bl.getGridPanel2());

		InputTools.setColor(Color.BLACK);
		InputTools.drawRectangle(-20, 20, -20, 20);

		InputTools.setColor(Color.WHITE);
		InputTools.drawRectangle(-10, 10, 15, -15);

		InputTools.setColor(Color.BLACK);
		InputTools.drawRectangle(-5, 5, 15, -15);

		InputTools.drawRectangle(-10, 10, 10, 3);

		InputTools.drawRectangle(-10, 10, -3, -10);

		bl.getGridPanel2().calcGameGrid();

		InputTools.setGrid(bl.getGridPanel());
		resetColor();
		return 8;
	}

	//Array Pos = 9
	//Returns array pos
	public int setLoomEx8(){
		ideal = 6;
		InputTools.setGrid(bl.getGridPanel2());

		InputTools.setColor(Color.WHITE);
		InputTools.drawRectangle(-4, 4, -9, 3);

		InputTools.setColor(Color.BLACK);
		doTriangleIt(0, 3, 3, 1, 12, true, false);

		InputTools.setColor(Color.RED);
		doTriangleIt(0, 0, 3, 1, 10, true, false);

		InputTools.setColor(betterOrange);
		doTriangleIt(0, -3, 3, 1, 7, true, false);

		InputTools.setColor(Color.YELLOW);
		doTriangleIt(0, -6, 3, 1, 4, true, false);

		InputTools.setColor(Color.WHITE);
		InputTools.drawPoint(0, -9);

		bl.getGridPanel2().calcGameGrid();

		InputTools.setGrid(bl.getGridPanel());
		resetColor();
		return 9;
	}

	//Array Pos = 10
	//Returns array pos
	public int setOverlappingSquares(){
		ideal = 6;
		InputTools.setGrid(bl.getGridPanel2());

		InputTools.setColor(Color.WHITE);
		InputTools.drawRectangle(-20, 20, -20, 20);

		InputTools.setColor(Color.RED);
		InputTools.drawRectangle(-5, 5, 11, 1);

		InputTools.setColor(Color.BLUE);
		InputTools.drawRectangle(1,11, 5, -5);

		InputTools.setColor(Color.GREEN);
		InputTools.drawRectangle(5,-5, -11, -1);

		InputTools.setColor(Color.YELLOW);
		InputTools.drawRectangle(-11,-1, 5, -5);

		InputTools.setColor(Color.RED);
		InputTools.drawRectangle(-1, -5, 1, 5);

		bl.getGridPanel2().calcGameGrid();

		InputTools.setGrid(bl.getGridPanel());
		resetColor();
		return 10;
	}

	//Array Pos = 11
	//Returns array pos
	public int setCircle(){
		ideal = 7;
		InputTools.setGrid(bl.getGridPanel2());

		InputTools.setColor(Color.BLUE);
		InputTools.drawRectangle(-20, 20, -20, 20);

		InputTools.setColor(Color.RED);
		InputTools.drawRectangle(-3, 3, 14, -14);

		InputTools.drawRectangle(-6, 6, 13, -13);

		InputTools.drawRectangle(-9, 9, 11, -11);

		InputTools.drawRectangle(-11, 11, 9, -9);

		InputTools.drawRectangle(-13, 13, 6, -6);

		InputTools.drawRectangle(-14, 14, 3, -3);

		bl.getGridPanel2().calcGameGrid();

		InputTools.setGrid(bl.getGridPanel());
		resetColor();
		return 11;
	}

	//Array Pos = 12
	//Returns array pos
	public int setStarrySky(){
		ideal = 9;
		InputTools.setGrid(bl.getGridPanel2());

		InputTools.setColor(Color.BLACK);
		InputTools.drawRectangle(-20, 20, 20, 0);

		InputTools.setColor(Color.GREEN);
		InputTools.drawRectangle(-20, 20, 0, -20);

		InputTools.setColor(Color.WHITE);
		InputTools.drawPoint(-13, 15);

		InputTools.drawPoint(-5, 15);

		InputTools.drawPoint(0,12);

		InputTools.drawPoint(4, 9);

		InputTools.drawPoint(6, 3);

		InputTools.drawPoint(16, 8);

		InputTools.drawPoint(13, 2);

		bl.getGridPanel2().calcGameGrid();

		InputTools.setGrid(bl.getGridPanel());
		resetColor();
		return 12;
	}

	//Array Pos = 13
	//Returns array pos
	public int setLoomEx14(){
		ideal = 9;
		InputTools.setGrid(bl.getGridPanel2());

		InputTools.setColor(Color.BLUE);
		InputTools.drawRectangle(-20, 20, -20, 20);

		InputTools.setColor(Color.BLACK);
		InputTools.drawRectangle(-11, 11, 14, -14);

		InputTools.setColor(Color.BLUE);
		InputTools.drawRectangle(-4, 4, 14, -14);

		InputTools.setColor(Color.BLACK);
		InputTools.drawRectangle(-15, 15, 9, -9);

		InputTools.setColor(Color.BLUE);
		InputTools.drawTriangle(-16, -16, -9, 9, -9, 0);

		InputTools.drawTriangle(16, 16, 9, 9, -9, 0);

		InputTools.setColor(Color.WHITE);
		InputTools.drawTriangle(-7, 0, 0, 0, 9, -9);

		InputTools.drawTriangle(7, 0, 0, 0, 9, -9);

		InputTools.setColor(Color.BLUE);
		doLine(0, 9, 0, -9);

		bl.getGridPanel2().calcGameGrid();

		InputTools.setGrid(bl.getGridPanel());
		resetColor();
		return 13;
	}

	//Array Pos = 14
	//Returns array pos
	public int setLoomEx6(){
		ideal = 10;
		InputTools.setGrid(bl.getGridPanel2());

		InputTools.setColor(Color.BLACK);
		InputTools.drawRectangle(-20, 20, 10, -10);

		InputTools.setColor(Color.WHITE);
		doTriangleIt(0, -9, 4, 3, 5, true, true);

		InputTools.drawRectangle(-4, 4, -1, -4);

		InputTools.drawTriangle(4, -4, 0, 0, 0, 9);

		doLine(5, -1, 20, 0);

		doLine(-5,-1, -20, 0);
		InputTools.drawLine(-5, -1, -20, 0, xValue, yValue);

		InputTools.drawRectangle(-9, -16, -5, 6);

		InputTools.drawRectangle(9, 16, -5, 6);

		InputTools.setColor(Color.BLACK);
		InputTools.drawRectangle(-10, -15, 5, -4);

		InputTools.drawRectangle(10, 15, 5, -4);

		bl.getGridPanel2().calcGameGrid();

		InputTools.setGrid(bl.getGridPanel());
		resetColor();
		return 14;
	}

	//Array Pos = 15
	//Returns array pos
	public int setSunnySky(){

		ideal = 5;
		InputTools.setGrid(bl.getGridPanel2());

		InputTools.setColor(Color.BLUE);
		InputTools.drawRectangle(20, -20, 20, -20);

		InputTools.setColor(Color.RED);
		InputTools.drawRectangle(7, 20, 7, 20);

		InputTools.setColor(Color.BLUE);
		doTriangleIt(5, 20, 3, 2, 15, true, false);

		doTriangleIt(20, 5, 3, 2, 15, false, false);

		InputTools.setColor(Color.GREEN);
		InputTools.drawRectangle(-20, 20, 0, -20);

		bl.getGridPanel2().calcGameGrid();

		InputTools.setGrid(bl.getGridPanel());
		resetColor();
		return 15;
	}

	//Array Pos = 16
	//Returns array pos
	public int setHeart(){

		ideal = 5;
		InputTools.setGrid(bl.getGridPanel2());

		InputTools.setColor(Color.BLUE);
		InputTools.drawRectangle(-20, 20, -1, -20);

		InputTools.setColor(Color.RED);
		doTriangleIt( 0, -20, 1, 1, 41, true, true);

		InputTools.setColor(Color.BLUE);
		InputTools.drawTriangle(0, -20, 20, 11, 20, 20);

		InputTools.setColor(Color.BLUE);

		doTriangleIt(-13, 16, 2, 1, 8, false, false);

		doTriangleIt(13, 16, 2, 1, 8, false, true);

		bl.getGridPanel2().calcGameGrid();

		InputTools.setGrid(bl.getGridPanel());
		resetColor();
		return 16;
	}

	//Array Pos = 17
	//Returns array pos
	public int setSunRise(){
		ideal = 7;
		InputTools.setGrid(bl.getGridPanel2());

		InputTools.setColor(betterOrange);
		InputTools.drawRectangle(-20, 20, -20, 20);

		InputTools.setColor(Color.RED);
		InputTools.drawRectangle(14, -14, 14, 0);

		InputTools.setColor(betterOrange);
		doTriangleIt(16, 1, 3, 2, 14, true, true);

		doTriangleIt(-16, 1, 3, 2, 14, true, true);

		doTriangleIt(1, 16, 3, 2, 14, false, true);

		doTriangleIt(-1, 16, 3, 2, 14, false, false);

		InputTools.setColor(Color.GREEN);
		InputTools.drawRectangle(-20, 20, 0, -20);

		bl.getGridPanel2().calcGameGrid();

		InputTools.setGrid(bl.getGridPanel());
		resetColor();
		return 17;
	}

	//Array Pos = 18
	//Returns array pos
	public int setFlag(){
		ideal = 8;
		InputTools.setGrid(bl.getGridPanel2());

		InputTools.setColor(Color.RED);
		InputTools.drawRectangle(20, -20, 20, -18);

		InputTools.setColor(Color.WHITE);
		InputTools.drawRectangle(-20, 20, 17, 15);

		InputTools.drawRectangle(-20, 20, 11, 9);

		InputTools.drawRectangle(-20, 20, 5, 3);

		InputTools.drawRectangle(-20, 20, -1, -3);

		InputTools.drawRectangle(-20, 20, -7, -9);

		InputTools.drawRectangle(-20, 20, -13, -15);

		InputTools.setColor(Color.BLUE);
		InputTools.drawRectangle(-20, 0, 20, 0);

		bl.getGridPanel2().calcGameGrid();

		InputTools.setGrid(bl.getGridPanel());
		resetColor();
		return 18;
	}

	//Array Pos = 19
	//Returns array pos
	public int setLoomEx13(){

		ideal = 10;
		InputTools.setGrid(bl.getGridPanel2());

		InputTools.setColor(Color.RED);
		InputTools.drawRectangle(-20, 20, -20, 20);

		InputTools.setColor(Color.CYAN);
		InputTools.drawRectangle(-20, 20, 6, -6);

		InputTools.setColor(Color.blue);
		doTriangleIt( 17, 0, 7, 3, 18, false, false);

		doTriangleIt( -17, 0 ,7, 3, 18, false, true);

		InputTools.setColor(Color.RED);
		doTriangleIt(10, 0, 7, 3, 11, false, false);

		doTriangleIt(-10, 0, 7, 3, 11, false, true);

		InputTools.setColor(betterOrange);
		doLinearIt(-1, 7, 3, 1, 1, 14, true, true);

		doLinearIt(-1, -7, 3, 1, 1, 14, true, false);

		InputTools.setColor(Color.GREEN);
		doLinearIt(-1, -11, 3, 1, 1, 10, true, false);

		doLinearIt(-1, 11, 3, 1, 1, 10, true, true);

		bl.getGridPanel2().calcGameGrid();

		InputTools.setGrid(bl.getGridPanel());
		resetColor();
		return 19;

	}

	//Array Pos = 20
	//Returns array pos
	public int setLoomEx10(){
		ideal = 11;
		InputTools.setGrid(bl.getGridPanel2());

		InputTools.setColor(Color.WHITE);
		InputTools.drawRectangle(-20, 20, 20, 0);

		InputTools.setColor(Color.CYAN);
		InputTools.drawRectangle(-20, 20, 18, 2);

		InputTools.setColor(Color.BLUE);
		InputTools.drawTriangle(-13, 13, 0, 2, 2, 15);

		InputTools.drawTriangle(-13, 13, 0, 18, 18, 5);

		InputTools.setColor(Color.GREEN);
		InputTools.drawRectangle(-5, 5, 2, 18);

		InputTools.setColor(Color.RED);
		doTriangleIt(11, 10, 1, 1, 8, false, true);

		doTriangleIt(-11, 10, 1, 1, 8, false, false);

		InputTools.setColor(Color.WHITE);
		doLinearIt(-2, 11, 3, 1, 1, 3, false, true);

		doLinearIt(2, 11, 3, 1, 1, 3, false, false);

		InputTools.setColor(Color.RED);
		doLine(0, 11, 0, 9);

		InputTools.drawRectangle(-20, 20, -1, -20);

		bl.getGridPanel2().calcGameGrid();

		InputTools.setGrid(bl.getGridPanel());
		resetColor();
		return 20;
	}

	//Array Pos = 21
	//Returns array pos
	public int setUNCC(){
		ideal = 13;
		InputTools.setGrid(bl.getGridPanel2());

		InputTools.setColor(Color.GREEN);
		InputTools.drawRectangle(-20, 20, -20, 20);

		InputTools.setColor(Color.WHITE);
		InputTools.drawRectangle(-17, 17, 17, -17);

		InputTools.setColor(Color.GREEN);
		InputTools.drawRectangle(-2, 2, 17, -17);

		InputTools.drawRectangle(17, -17, 2, -2);

		InputTools.drawRectangle(-14, -6, 17, 6);

		InputTools.drawTriangle(6, 6, 14, 13, 3, 3);

		InputTools.drawTriangle(6, 14, 14, 17, 7, 17);

		InputTools.drawRectangle(-14, -3, -14, -6);

		InputTools.drawRectangle(17, 6, -14, -6);

		InputTools.setColor(Color.WHITE);
		InputTools.drawPoint(-1, 3);

		InputTools.drawPoint(19, 3);

		InputTools.drawPoint(-1, -17);

		InputTools.drawPoint(19, -17);

		bl.getGridPanel2().calcGameGrid();

		InputTools.setGrid(bl.getGridPanel());
		resetColor();
		return 21;
	}

	public int setSergeant(){

		ideal = 15;
		InputTools.setGrid(bl.getGridPanel2());

		InputTools.setColor(Color.BLACK);
		InputTools.drawRectangle(-20, 20, -20, 20);

		InputTools.setColor(betterOrange);
		InputTools.drawRectangle(-12, 12, 2, -12);

		InputTools.drawTriangle(0, -12, 12, 18, 3, 3);

		doLinearIt(-11, -13, 23, -1, -1, 7, true, false);

		InputTools.setColor(Color.BLUE);
		InputTools.drawRectangle(-10, 10, 2, -12);

		InputTools.drawTriangle(0, -10, 10, 15, 3, 3);

		doLinearIt(-9, -13, 19, -1, -1, 5, true, false);

		InputTools.setColor(betterOrange);
		doTriangleIt(0, 12, 1, 1, 11, true, false);

		InputTools.setColor(Color.BLUE);
		doTriangleIt(0, 10, 1, 1, 9, true, false);

		InputTools.setColor(betterOrange);
		doTriangleIt(0, 8, 1, 1, 11, true, false);

		InputTools.setColor(Color.BLUE);
		doTriangleIt(0, 6, 1, 1, 9, true, false);

		InputTools.setColor(betterOrange);
		doTriangleIt(0, 4, 1, 1, 11, true, false);

		InputTools.setColor(Color.BLUE);
		doTriangleIt(0, 2, 1, 1, 9, true, false);

		InputTools.setColor(betterOrange);
		doLinearIt(-2, -15, 5, 1, 1, 9, true, true);

		InputTools.setColor(Color.BLUE);
		doLinearIt(-2, -13, 5, 1, 1, 7, true, true);

		//Calculate the Goal Images bead array
		bl.getGridPanel2().calcGameGrid();

		//Change grid back so they can work on the puzzle
		//Reset the Current Selected Color to default
		InputTools.setGrid(bl.getGridPanel());
		resetColor();
		return 22;
	}

	//Array Position = 34
	//Returns array position
	public int setTieFighter(){

		ideal = 21;
		InputTools.setGrid(bl.getGridPanel2());

		/* Draw Black Background */
		InputTools.setColor(Color.BLACK);
		InputTools.drawRectangle(-20, 20, -20, 20);


		/*Draw Outer Wings*/
		InputTools.setColor((Color.WHITE));

		doLinearIt(-19, 4, 9, 1, 1, 10, false, true);

		doLinearIt(19, 4, 9, 1, 1, 10, false, false);

		InputTools.setColor((Color.BLACK));

		doLinearIt(-17, 4, 9, 1, 1, 8, false, true);

		doLinearIt(17, 4, 9, 1, 1, 8, false, false);


		/* Draw Inner Wings */
		InputTools.setColor(Color.GRAY);
		InputTools.drawRectangle(-11, 11, 3, -3);

		InputTools.drawRectangle(-14, 14, 2, -2);

		InputTools.drawRectangle(-17, 17, 1, -1);


		/* Draw Body */
		InputTools.setColor(Color.WHITE);


		doLinearIt(-7, 5, 15, -1, -1, 4, true, true);

		doLinearIt(-7, -5, 15, -1, -1, 4, true, false);

		InputTools.drawRectangle(-8, 8, 4, -4);

		/* Draw Outer Wings */
		InputTools.setColor(Color.WHITE);

		//Rectangles for either side of vertical edges of wings
		InputTools.drawRectangle(-19, -18, 4, -4);

		InputTools.drawRectangle(18, 19, 4, -4);

		/* Draw Outer Window */

		InputTools.setColor(Color.GRAY);

		//Draw rectangle in center of window area
		InputTools.drawRectangle(-5, 5, 2, -2);

		//Linear Iteration for area above center rectangle
		doLinearIt(-4, 3, 9, -1, -1, 3, true, true);

		//Linear Iteration for area below center rectangle
		doLinearIt(-4, -3, 9, -1, -1, 3, true, false);

		/* Draw Inner Window */

		InputTools.setColor(Color.BLACK);

		//Draw rectangles for inner window
		InputTools.drawRectangle(-2, 2, 4, -4);

		InputTools.drawRectangle(-4, 4, 2, -2);

		/* Draw Pieces of Outer Window that overlap Inner Window */

		InputTools.setColor(Color.GRAY);

		doLine(0, 4, 0, -4);

		doLine(-4, 0, 4, 0);

		doLine(-3, 3, 3, -3);

		doLine(3, 3, -3, -3);

		/* Draw Final black dot in center */

		InputTools.setColor(Color.BLACK);
		InputTools.drawPoint(0, 0);

		//Calculate the Goal Images bead array
		bl.getGridPanel2().calcGameGrid();

		//Change grid back so they can work on the puzzle
		//Reset the Current Selected Color to default
		InputTools.setGrid(bl.getGridPanel());
		resetColor();
		return 23;  
	}

	//Array Pos = 22
	//Returns array pos
	public int setMoon(){

		ideal = 11;
		InputTools.setGrid(bl.getGridPanel2());

		InputTools.setColor(Color.BLACK);
		InputTools.drawRectangle(-20, 20, -20, 20);

		InputTools.setColor(Color.YELLOW);
		InputTools.drawRectangle(-3, 3, 14, -14);

		InputTools.drawRectangle(-6, 6, 13, -13);

		InputTools.drawRectangle(-9, 9, 11, -11);

		InputTools.drawRectangle(-11, 11, 9, -9);

		InputTools.drawRectangle(-13, 13, 6, -6);

		InputTools.drawRectangle(-14, 14, 3, -3);

		InputTools.setColor(Color.BLACK);
		InputTools.drawRectangle(-6, -1, 13, -6);

		InputTools.drawRectangle(-9, 2, 11, -4);

		InputTools.drawRectangle(-11, 4, 9, -2);

		InputTools.drawRectangle(-13, 6, 6, 1);

		bl.getGridPanel2().calcGameGrid();

		InputTools.setGrid(bl.getGridPanel());
		resetColor();
		return 24;
	}

	//Array Pos = 23
	//Returns array pos
	public int setLoomEx7(){

		ideal = 14; 
		InputTools.setGrid(bl.getGridPanel2());

		InputTools.setColor(betterOrange);
		InputTools.drawRectangle(-20, 20, -20, 20);

		InputTools.setColor(Color.RED);
		doLinearIt(-20, 20, 41, -1, -1, 41, false, true);

		InputTools.setColor(Color.MAGENTA);
		InputTools.drawTriangle(7, 20, 20, 0, 13, -13);

		InputTools.drawTriangle(-7, -20, -20, 0, 13, -13);

		InputTools.setColor(Color.BLUE);
		InputTools.drawTriangle(12, 20, 20, 0, 8, -8);

		InputTools.drawTriangle(-12, -20, -20, 0, 8, -8);

		InputTools.setColor(Color.GREEN);
		InputTools.drawTriangle(17, 20, 20, 0, 3, -3);

		InputTools.drawTriangle(-17, -20, -20, 0, 3, -3);

		InputTools.setColor(Color.BLACK);
		InputTools.drawTriangle(-8, 8, 0, -18, -18, -10);

		InputTools.drawTriangle(-8, 8, 0, 18, 18, 10);

		InputTools.setColor(Color.YELLOW);
		InputTools.drawTriangle(-3, 3, 0, -16, -16, -13);

		InputTools.drawTriangle(-3, 3, 0, 16, 16, 13);

		InputTools.setColor(betterOrange);
		doLine(-7, 0, -11, 0);

		doLine(7, 0, 11, 0);

		bl.getGridPanel2().calcGameGrid();

		InputTools.setGrid(bl.getGridPanel());
		resetColor();
		return 25;
	}

	//Array Pos = 32
	//Returns array pos
	public int setDC(){

		ideal = 15;
		InputTools.setGrid(bl.getGridPanel2());

		InputTools.setColor(Color.BLACK);
		InputTools.drawRectangle(-20, 20, -20, 20);

		InputTools.setColor(Color.GREEN);
		InputTools.drawRectangle(-15, 15, -15, 15);

		InputTools.setColor(Color.BLACK);
		doTriangleIt(0, 12, 1, 2, 4, true, true);
		doTriangleIt(0, -12, 1, 2, 4, true, false);

		doLinearIt(-3, 10, 1, 0, 3, 3, false, false);

		doLinearIt(-3, -10, 1, 3, 0, 3, false, false);

		InputTools.drawRectangle(-8, -6, 11, -11);
		InputTools.drawRectangle(-12, -9, -12, 12);

		doLinearIt(3, 10, 1, 0, 3, 3, false, true);

		doLinearIt(3, -10, 1, 3, 0, 3, false, true);

		InputTools.drawRectangle(8, 6, 11, -11);
		InputTools.drawRectangle(12, 9, -12, 12);

		InputTools.drawTriangle(0, -2, 2, 8, -4, -4);
		InputTools.drawTriangle(0, -2, 2, -8, 4, 4);

		InputTools.drawRectangle(13, 15, 7, -7);

		//Calculate the Goal Images bead array
		bl.getGridPanel2().calcGameGrid();

		//Change grid back so they can work on the puzzle
		//Reset the Current Selected Color to default
		InputTools.setGrid(bl.getGridPanel());
		resetColor();
		return 26;
	}

	//Array Pos = 24
	//Returns array pos
	public int setBullseye(){

		ideal = 16;
		InputTools.setGrid(bl.getGridPanel2());

		InputTools.setColor(Color.BLUE);
		InputTools.drawRectangle(-20, 20, -20, 20);

		InputTools.setColor(Color.RED);
		InputTools.drawRectangle(-3, 3, 14, -14);

		InputTools.drawRectangle(-6, 6, 13, -13);

		InputTools.drawRectangle(-9, 9, 11, -11);

		InputTools.drawRectangle(-11, 11, 9, -9);

		InputTools.drawRectangle(-13, 13, 6, -6);

		InputTools.drawRectangle(-14, 14, 3, -3);

		InputTools.setColor(Color.CYAN);
		InputTools.drawRectangle(-3, 3, 10, -10);

		InputTools.drawRectangle(-6, 6, 8, -8);

		InputTools.drawRectangle(-8, 8, 6, -6);

		InputTools.drawRectangle(-10, 10, 3, -3);

		InputTools.setColor(Color.GREEN);
		InputTools.drawRectangle(-3, 3, 5, -5);

		InputTools.drawRectangle(-5, 5, 3, -3);

		InputTools.setColor(Color.BLACK);
		InputTools.drawRectangle(-2, 2, 2, -2);

		InputTools.setColor(Color.YELLOW);
		doLine(-20, 20, 20, -20);

		doLine(20, 20, -20, -20);

		bl.getGridPanel2().calcGameGrid();

		InputTools.setGrid(bl.getGridPanel());
		resetColor();
		return 27;
	}

	//Array Pos = 25
	//Returns array pos
	public int setLoomEx1(){

		ideal = 19;
		InputTools.setGrid(bl.getGridPanel2());

		InputTools.setColor(Color.RED);
		InputTools.drawRectangle(-20, 20, -20, 20);

		InputTools.setColor(Color.BLACK);
		doLinearIt(-8, -7, 3, -1,1, 15, true, true);

		doLinearIt(-8, 7, 3, -1, 1, 15, true, false);

		InputTools.setColor(betterOrange);

		doTriangleIt(6, 0, 1, 1, 15, false, true);

		doTriangleIt(-6, 0, 1, 1, 15, false, false);

		doTriangleIt(0, 6, 1, 1, 15, true, true);

		doTriangleIt(0, -6, 1, 1, 15, true, false);

		InputTools.setColor(Color.YELLOW);
		doTriangleIt(10, 0, 1, 1, 11, false, true);

		doTriangleIt(-10, 0, 1, 1, 11, false, false);

		doTriangleIt(0, 10, 1, 1, 11, true, true);

		doTriangleIt(0, -10, 1, 1, 11, true, false);

		InputTools.setColor(Color.BLACK);
		doTriangleIt(14, 0, 1, 1, 7, false, true);

		doTriangleIt(-14, 0, 1, 1, 7, false, false);

		doTriangleIt(0, 14, 1, 1, 7, true, true);

		doTriangleIt(0, -14, 1, 1, 7, true, false);

		InputTools.drawRectangle(20, 7, 7, 20);

		InputTools.drawRectangle(-20, -7, 7, 20);

		InputTools.drawRectangle(20, 7, -7, -20);

		InputTools.drawRectangle(-20, -7, -7, -20);

		bl.getGridPanel2().calcGameGrid();

		InputTools.setGrid(bl.getGridPanel());
		resetColor();
		return 28;
	}

	//Array Pos = 26
	//Returns array pos
	public int setROSS(){

		ideal = 19;
		InputTools.setGrid(bl.getGridPanel2());

		InputTools.setColor(Color.CYAN);
		InputTools.drawRectangle(-20, 20, -20, 20);

		InputTools.setColor(Color.PINK);
		InputTools.drawRectangle(-9, -8, 15, 11);

		doLinearIt(-5, 14, 2, 1 ,-1, 4, true, false);

		InputTools.drawRectangle(-12, -7, 10, 5);

		doLinearIt(-14, 8, 3, 1, 1, 2, false, true);

		InputTools.setColor(Color.BLACK);
		InputTools.drawPoint(-14, 7);

		InputTools.drawPoint(-10, 9);

		InputTools.setColor(Color.PINK);
		InputTools.drawRectangle(-9, 8, 7, 2);

		doLine(-8, 1, 7, 1);

		InputTools.drawRectangle(-9, 6, 0, -1);

		InputTools.setColor(Color.CYAN);
		doLine(-6, -1, 1, -1);

		InputTools.setColor(Color.PINK);
		doLine(3, -2, 5, -2);

		doLine(-4, 8, 3, 8);

		InputTools.setColor(Color.WHITE);
		InputTools.drawRectangle(7, 8, 9, 6);

		InputTools.drawRectangle(6, 9, 8, 7);

		InputTools.setColor(Color.BLACK);
		InputTools.drawRectangle(0, 2, 13, 9);

		InputTools.drawRectangle(-1, 3, 12, 10);

		InputTools.setColor(Color.BLACK);
		InputTools.drawPoint(1, 14);

		InputTools.setColor(Color.RED);
		InputTools.drawPoint(2, 15);

		bl.getGridPanel2().calcGameGrid();

		InputTools.setGrid(bl.getGridPanel());
		resetColor();
		return 29;
	}

	//Array Pos = 27
	//Returns array pos
	public int setStarsAndStripes(){

		ideal = 21;
		InputTools.setGrid(bl.getGridPanel2());

		InputTools.setColor(Color.RED);
		InputTools.drawRectangle(20, -20, 20, -18);

		InputTools.setColor(Color.WHITE);
		InputTools.drawRectangle(-20, 20, 17, 15);

		InputTools.drawRectangle(-20, 20, 11, 9);

		InputTools.drawRectangle(-20, 20, 5, 3);

		InputTools.drawRectangle(-20, 20, -1, -3);

		InputTools.drawRectangle(-20, 20, -7, -9);

		InputTools.drawRectangle(-20, 20, -13, -15);

		InputTools.setColor(Color.BLUE);
		InputTools.drawRectangle(-20, 0, 20, 0);

		InputTools.setColor(Color.WHITE);

		doLine(-19, 19, -1, 19);

		doLine(-19, 16, -1, 16);

		doLine(-19, 13, -1, 13);

		doLine(-19, 10, -1, 10);

		doLine(-19, 7, -1, 7);

		doLine(-19, 4, -1, 4);

		doLine(-19, 1, -1, 1);

		InputTools.setColor(Color.BLUE);
		InputTools.drawRectangle(-18, -17, 19, 1);

		InputTools.drawRectangle(-15, -14, 19, 1);

		InputTools.drawRectangle(-12, -11, 19, 1);

		InputTools.drawRectangle(-9, -8, 19, 1);

		InputTools.drawRectangle(-6, -5, 19, 1);

		InputTools.drawRectangle(-3, -2, 19, 1);

		bl.getGridPanel2().calcGameGrid();

		InputTools.setGrid(bl.getGridPanel());
		resetColor();
		return 30;
	}

	//Array Pos = 28
	//Returns array pos
	public int setLoomEx5(){

		ideal = 22;
		InputTools.setGrid(bl.getGridPanel2());

		InputTools.setColor(Color.BLUE);
		InputTools.drawRectangle(-12, 12, 15, -15);

		InputTools.setColor(Color.GREEN);
		InputTools.drawTriangle(-12, -12, -9, 15, 12, 15);

		InputTools.drawTriangle(-12, -12, -9, -15, -12, -15);

		InputTools.drawTriangle(12, 12, 9, 15, 12, 15);

		InputTools.drawTriangle(12, 12, 9, -15, -12, -15);

		InputTools.drawTriangle(0, -3, 3, 12, 15, 15);

		InputTools.drawTriangle(0, -3, 3, -12, -15, -15);

		InputTools.setColor(Color.MAGENTA);
		InputTools.drawTriangle(-6, -10, -2, 12, 8, 8);

		InputTools.drawTriangle(-6, -10, -2, -12, -8, -8);

		InputTools.drawTriangle(6, 10, 2, 12, 8, 8);

		InputTools.drawTriangle(6, 10, 2, -12, -8, -8);

		InputTools.setColor(Color.RED);
		InputTools.drawRectangle(-12, 12, 7, -7);

		InputTools.setColor(betterOrange);
		InputTools.drawTriangle(2, 9, 9, 0, 6, -6);

		InputTools.drawTriangle(-2, -9, -9, 0, 6, -6);

		InputTools.setColor(Color.BLUE);
		InputTools.drawTriangle(5, 8, 8, 0, 3, -3);

		InputTools.drawTriangle(-5, -8, -8, 0, 3, -3);

		InputTools.setColor(Color.YELLOW);
		InputTools.drawTriangle(6, 7, 7, 0, 1, -1);

		InputTools.drawTriangle(-6, -7, -7, 0, 1, -1);

		InputTools.setColor(Color.BLACK);
		InputTools.drawRectangle(-1, 1, 7, -7);

		InputTools.setColor(Color.BLUE);
		doLine(0, 7, 0, -7);

		InputTools.setColor(betterOrange);
		doLine(0, 6, 0, -6);

		InputTools.setColor(Color.YELLOW);
		InputTools.drawRectangle(1, -1, 1, -1);

		bl.getGridPanel2().calcGameGrid();

		InputTools.setGrid(bl.getGridPanel());
		resetColor();
		return 31;
	}

	//Array Pos = 29
	//Returns array pos
	public int setLoomEx3(){

		ideal = 24;
		InputTools.setGrid(bl.getGridPanel2());

		InputTools.setColor(Color.YELLOW);
		InputTools.drawRectangle(19, -19, 19, -19);

		InputTools.setColor(Color.BLUE);
		doTriangleIt(19, 0, 3, 2, 21, false,false);

		doTriangleIt(19, -1, 3, 2, 21, false,false);

		doTriangleIt(19, +1, 3, 2, 21, false,false);

		doTriangleIt(-19, 0, 3, 2, 21, false,true);

		doTriangleIt(-19, -1, 3, 2, 21, false,true);

		doTriangleIt(-19, 1, 3, 2, 21, false,true);

		InputTools.setColor(Color.RED);
		InputTools.drawRectangle(-1, 1, 14, 19);

		InputTools.drawRectangle(-1, 1, -14, -19);

		InputTools.drawRectangle(-4, 4, 19, 16);

		InputTools.drawRectangle(-4, 4, -19, -16);

		InputTools.drawRectangle(-7, 7, 19, 18);

		InputTools.drawRectangle(-7, 7, -19, -18);

		InputTools.setColor(Color.BLUE);
		InputTools.drawRectangle(-1, 1, -18, -19);

		InputTools.drawRectangle(-1, 1, 18, 19);

		InputTools.setColor(Color.MAGENTA);
		doTriangleIt(16, 0, 3, 2, 18, false, false);

		doTriangleIt(16, -1, 3, 2, 18, false, false);

		doTriangleIt(16, 1, 3, 2, 18, false, false);

		doTriangleIt(-16, 0, 3, 2, 18, false, true);

		doTriangleIt(-16, -1, 3, 2, 18, false, true);

		doTriangleIt(-16, 1, 3, 2, 18, false, true);

		InputTools.setColor(Color.GREEN);
		InputTools.drawRectangle(-3, 3, 1, -1);

		InputTools.drawRectangle(-1, 1, 3, -3);

		InputTools.setColor(Color.RED);
		InputTools.drawRectangle(-1, 1, -1, 1);

		bl.getGridPanel2().calcGameGrid();

		InputTools.setGrid(bl.getGridPanel());
		resetColor();
		return 32;
	}

	//Array Pos = 30
	//Returns array pos
	public int setMegaman(){

		ideal = 26;
		InputTools.setGrid(bl.getGridPanel2());

		InputTools.setColor(betterOrange);
		InputTools.drawRectangle(-20, 20, -20, 20);

		InputTools.setColor(Color.BLACK);
		InputTools.drawRectangle(-4, 4, 12, 11);

		InputTools.drawRectangle(-14, 14, 10, -17);           

		InputTools.setColor(betterOrange);
		doTriangleIt(7, 11, 2, 2, 8, false, true);

		doTriangleIt(-7, 11, 2, 2, 8, false, false);

		InputTools.setColor(Color.BLUE);
		InputTools.drawRectangle(-8, 8, -8, 7);

		InputTools.drawRectangle(-10, 10, 6, 5);

		InputTools.drawRectangle(-12, 12, 4, -1);

		InputTools.setColor(Color.CYAN);
		InputTools.drawRectangle(-2, 2, 10, -1);

		InputTools.setColor(Color.BLACK);
		InputTools.drawRectangle(-2, 2, 4, 3);

		InputTools.drawRectangle(-16, 16, -2, -9);

		InputTools.setColor(Color.CYAN);
		InputTools.drawRectangle(-14, -13, -2, -9);

		InputTools.drawRectangle(14, 13, -2, -9);

		InputTools.setColor(peach);
		InputTools.drawRectangle(-10, 10, -8, -13);

		InputTools.setColor(Color.WHITE);
		InputTools.drawRectangle(-8, 8, -2, -9);

		InputTools.drawRectangle(-10, 10, -4, -7);

		InputTools.setColor(Color.BLACK);
		InputTools.drawRectangle(-6, -3, -6, -9);

		InputTools.drawRectangle(6, 3, -6, -9);

		InputTools.drawRectangle(-4, 4, -2, -3);

		InputTools.drawRectangle(-2, 2, -4, -5);

		InputTools.setColor(betterOrange);
		doTriangleIt(-5, -20, 2, 2, 10, false, false);

		doTriangleIt(5, -20, 2, 2, 10, false, true);

		InputTools.setColor(peach);
		InputTools.drawRectangle(-8, 8, -14, -15);

		InputTools.setColor(Color.BLACK);
		InputTools.drawRectangle(-4, 4, -14, -19);

		InputTools.setColor(peach);
		InputTools.drawRectangle(-4, 4, -16, -17);

		InputTools.setColor(Color.BLACK);
		InputTools.drawRectangle(-6, 6, -12, -13);

		bl.getGridPanel2().calcGameGrid();

		InputTools.setGrid(bl.getGridPanel());
		resetColor();
		return 33;
	}

	//Array Pos = 31
	//Returns array pos
	public int setBatman(){

		ideal = 27;
		InputTools.setGrid(bl.getGridPanel2());

		InputTools.setColor(Color.BLACK);
		InputTools.drawRectangle(-20, 20, -20, 20);

		InputTools.setColor(Color.YELLOW);
		doLinearIt(-7, 5, 15, 1, 1, 4, true, true);

		doLinearIt(-12, 13, 2, -2, 2, 2, true, true);

		doLinearIt(11, 13, 2, 2, -2, 2, true, true);

		InputTools.drawRectangle(-8, 8, 15, 14);

		doLinearIt(-7, 13, 15, 1, 1, 3, true, false);

		doLinearIt(-9, 10, 19, 1, 1, 2, true, false);

		InputTools.setColor(Color.BLACK);
		doLinearIt(-5, 5, 11, -1, -1, 3, true, true);

		InputTools.drawRectangle(-3, 3, 14, 8);

		InputTools.setColor(Color.YELLOW);
		doLinearIt(-2, 14, 5, 1, 1, 3, true, true);

		doLinearIt(-2, 13, 5, -1, -1, 2, true, false);

		InputTools.drawRectangle(-12, 12, -5, -9);

		InputTools.drawRectangle(-13, 13, -6, -7);

		doLinearIt(-11, -10, 23, -1, -1, 2, true, false);

		doLinearIt(-12, -12, 25, -1, -1, 3, true, false);

		doLinearIt(-8, -15, 17, -4, -4, 2, true, false);

		doLine(13, 12, 17, 8);

		doLine(-13, 12, -17, 8);

		doLine(-12, -13, -17, -8);

		doLine(12, -13, 17, -8);

		doLinearIt(-20, 3, 7, 2, 2, 3, false, true);

		doLinearIt(20, 3, 7, 2, 2, 3, false, false);


		InputTools.setColor(Color.BLACK);
		doLinearIt(-19, 3, 7, 2, 2, 2, false, true);

		doLinearIt(19, 3, 7, 2, 2, 2, false, false);

		InputTools.drawTriangle(0, -3, 3, -11, -5, -5);

		InputTools.drawTriangle(-8, -6, -7, -5, -5, -7);

		InputTools.drawTriangle(8, 6, 7, -5, -5, -7);

		bl.getGridPanel2().calcGameGrid();

		InputTools.setGrid(bl.getGridPanel());
		resetColor();
		return 34;
	}

	public InputStream getStream(String url) {
		try {
			URL test = new URL(url);
			URLConnection con = test.openConnection();
			return con.getInputStream();
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	public void setCustomPuzzle(String puzzleName)
	{
		//Set the active grid to the goal image grid
		InputTools.setGrid(bl.getGridPanel2());
		try {

			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			InputStream stream = getStream(
					"http://unccmakesgames.com/games/BeadLoomGame/CustomPuzzles/"+puzzleName+".xml");
			Document doc = db.parse(stream);
			stream.close();
			doc.getDocumentElement().normalize();
			NodeList nodeLst = doc.getElementsByTagName("layer");

			for (int s = 0; s < nodeLst.getLength(); s++)
			{

				Node fstNode = nodeLst.item(s);

				if (fstNode.getNodeType() == Node.ELEMENT_NODE) 
				{
					Element layer = (Element) fstNode;
					Color newColor = bl.getColorFromString(layer.getAttribute("color"));
					if(newColor != null)
					{
						InputTools.setColor(newColor);
						//check for point
						if(layer.getAttribute("typeID").equalsIgnoreCase("point"))
						{
							int x = Integer.parseInt(layer.getAttribute("x"));
							int y = Integer.parseInt(layer.getAttribute("y"));
							InputTools.drawPoint(x, y);
						}
						//check for line
						else if(layer.getAttribute("typeID").equalsIgnoreCase("line"))
						{
							int x1 = Integer.parseInt(layer.getAttribute("x1"));
							int y1 = Integer.parseInt(layer.getAttribute("y1"));
							int x2 = Integer.parseInt(layer.getAttribute("x2"));
							int y2 = Integer.parseInt(layer.getAttribute("y2"));
							doLine(x1, y1, x2, y2);
						}
						//check for triangle
						else if(layer.getAttribute("typeID").equalsIgnoreCase("triangle"))
						{
							int x1 = Integer.parseInt(layer.getAttribute("x1"));
							int y1 = Integer.parseInt(layer.getAttribute("y1"));
							int x2 = Integer.parseInt(layer.getAttribute("x2"));
							int y2 = Integer.parseInt(layer.getAttribute("y2"));
							int x3 = Integer.parseInt(layer.getAttribute("x3"));
							int y3 = Integer.parseInt(layer.getAttribute("y3"));
							InputTools.drawTriangle(x1, x2, x3, y1, y2, y3);
						}
						//check for rectangle
						else if(layer.getAttribute("typeID").equalsIgnoreCase("rectangle"))
						{
							int x1 = Integer.parseInt(layer.getAttribute("x1"));
							int y1 = Integer.parseInt(layer.getAttribute("y1"));
							int x2 = Integer.parseInt(layer.getAttribute("x2"));
							int y2 = Integer.parseInt(layer.getAttribute("y2"));
							InputTools.drawRectangle(x1, x2, y1, y2);
						}
						//check for triangle iteration
						else if(layer.getAttribute("typeID").equalsIgnoreCase("triangle_iteration"))
						{
							int x = Integer.parseInt(layer.getAttribute("x"));
							int y = Integer.parseInt(layer.getAttribute("y"));
							int every = Integer.parseInt(layer.getAttribute("stepheight"));
							int add = Integer.parseInt(layer.getAttribute("beadsadded"));
							int total = Integer.parseInt(layer.getAttribute("rowstotal"));
							boolean incY=false;
							if(layer.getAttribute("direction").contains("Y")) { incY=true; }
							boolean isPositive=true;
							if(layer.getAttribute("direction").contains("-")) { isPositive=false; }
							doTriangleIt(x, y, every, add, total, incY, isPositive);
						}
						//check for linear iteration
						else if(layer.getAttribute("typeID").equalsIgnoreCase("linear_iteration"))
						{
							int x = Integer.parseInt(layer.getAttribute("x"));
							int y = Integer.parseInt(layer.getAttribute("y"));
							int startLength = Integer.parseInt(layer.getAttribute("startlength"));
							int inc1 = Integer.parseInt(layer.getAttribute("beadsadded1"));
							int inc2 = Integer.parseInt(layer.getAttribute("beadsadded2"));
							int totalRows = Integer.parseInt(layer.getAttribute("rowstotal"));

							boolean incY=false;
							if(layer.getAttribute("direction").contains("Y")) { incY=true; }
							boolean isPositive=true;
							if(layer.getAttribute("direction").contains("-")) { isPositive=false; }
							doLinearIt(x, y, startLength, inc1, inc2, totalRows, incY, isPositive);
						}
					}
				}

			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		//Calculate the Goal Images bead array
		bl.getGridPanel2().calcGameGrid();

		//Change grid back so they can work on the puzzle
		//Reset the Current Selected Color to default
		InputTools.setGrid(bl.getGridPanel());
		resetColor();
	}

}
