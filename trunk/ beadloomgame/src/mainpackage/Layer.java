/*******************************************************************
 * 																   *
 *	NAME:		Layer.java				                           *
 *	DATE:		4/22/08								               *
 *	VERSION:	1.0.0							                   *
 *	PURPOSE:	Holds ArrayList of CoordLists to be graphed.       *
 *	DOCUMENT REFERENCE(S):	                    				   *
 *											                       *
 *	PROCEDURE INVOCATION:						                   *
 *	        toString()		                                       *
 *				                                                   *
 *	INPUT PARAMETERS:							                   *
 *		    none                                                   *
 *								                                   *
 *	OUTPUT PARAMETERS:							                   *
 *		    String                                                 *
 *											                       *
 *	ASSUMPTIONS: None				                               *
 *	LIMITATIONS:   						                           *
 *									                               *
 *											                       *
 *******************************************************************/
package src.mainpackage;

import java.awt.*;

//This is a class to store data about layers
public class Layer {
	private String type; //Tool used to create layer
	private CoordList coords;
	private Color color;
	private String beadType;
	private int xOffset; //Where to shift to
	private int yOffset;
	private int zValue;
	private Image image;
	private static int ZZzzzz = 0;
	private static GUIMovePanel movePanel;

	private int x1;
	private int y1;
	private int x2;
	private int y2;
	private int x3;
	private int y3;
	private int beadsAdded1;
	private int beadsAdded2;
	private int rowsTotal;
	private int startLength;
	private int stepHeight;
	private boolean yInc;
	private boolean positiveInc;
	//****************************************************************************
	//***************[Constructors]***********************************************
	//****************************************************************************
	public Layer(String type, CoordList coords,  String beadType) {
		this.coords=coords;
		this.type=type;
		this.color = movePanel.getColor();
		this.beadType=beadType;
		zValue=ZZzzzz++;
		this.beadType=BeadLoom.beadLocation;
	}
	public Layer() {;}
	//****************************************************************************
	//***************[Get Methods]************************************************
	//****************************************************************************
	public String getType() {
		return type;
	}
	public CoordList getCoords() {
		return coords;
	}
	public Color getColor() {
		return color;
	}
	public String getBeadType() {
		return beadType;
	}
	public int getXOffset() {
		return xOffset;
	}
	public int getYOffset() {
		return yOffset;
	}
	public int getSize() { 
		return coords.getSize();
	}
	public int getZValue() {
		return zValue;
	}
	public Image getImage() {
		return image;
	}

	public int getX1() {
		return x1;
	}
	public int getY1() {
		return y1;
	}
	public int getX2() {
		return x2;
	}
	public int getY2() {
		return y2;
	}
	public int getX3() {
		return x3;
	}
	public int getY3() {
		return y3;
	}
	public int getBeadsAdded1() {
		return beadsAdded1;
	}
	public int getBeadsAdded2() {
		return beadsAdded2;
	}
	public int getRowsTotal() {
		return rowsTotal;
	}
	public int getStartLength() {
		return startLength;
	}
	public int getStepHeight() {
		return stepHeight;
	}
	public boolean getYInc() {
		return yInc;
	}
	public boolean getPositiveInc() {
		return positiveInc;
	}
	//****************************************************************************
	//***************[Set Methods]************************************************
	//****************************************************************************
	public void setType(String type) {
		this.type=type;
	}
	public void setCoords(CoordList coords) {
		this.coords=coords;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public void setBeadType(String beadType) {
		this.beadType = beadType;
	}
	public void setXOffset(int offset) {
		xOffset = offset;
	}
	public void setYOffset(int offset) {
		yOffset = offset;
	}
	public void setZValue(int z) {
		zValue = z;
	}
	public void setImage(Image i) {
		image = i;
	}
	public void setMovePanel(GUIMovePanel p)
	{
		movePanel = p;
	}

	public void setX1(int newX1) {
		x1 = newX1;
	}
	public void setY1(int newY1) {
		y1 = newY1;
	}
	public void setX2(int newX2) {
		x2 = newX2;
	}
	public void setY2(int newY2) {
		y2 = newY2;
	}
	public void setX3(int newX3) {
		x3 = newX3;
	}
	public void setY3(int newY3) {
		y3 = newY3;
	}
	public void setBeadsAdded1(int newBeadsAdded1) {
		beadsAdded1 = newBeadsAdded1;
	}
	public void setBeadsAdded2(int newBeadsAdded2) {
		beadsAdded2 = newBeadsAdded2;
	}
	public void setRowsTotal(int newRowsTotal) {
		rowsTotal = newRowsTotal;
	}
	public void setStartLength(int newStartLength) {
		startLength = newStartLength;
	}
	public void setStepHeight(int newStepHeight) {
		stepHeight = newStepHeight;
	}
	public void setYInc(boolean newYInc) {
		yInc = newYInc;
	}
	public void setPositiveInc(boolean newPositiveInc) {
		positiveInc = newPositiveInc;
	}

	//****************************************************************************
	//***************[To String]  ************************************************
	//****************************************************************************
	public String toString() {
		String output="";
		output+="Layer " + (zValue+1);
		output+=" - Type: "+type+"\n";
		for (int i=0;i<coords.getSize();i++) {
			output+=" - Point "+i+ ":("+(xOffset + coords.getCoord(i,0)) + ","+(-1*yOffset + coords.getCoord(i,1))+" " + color+")\n";
		}
		return output;
	}
	//****************************************************************************
	//***************[Static Methods]*********************************************
	//****************************************************************************
	public static void resetZ() {
		ZZzzzz=0; //TODO CHANGED
	}
	public static void setCurrentZ(int z){
		ZZzzzz = z;
	}
}
