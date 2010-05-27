package mainpackage;
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
        //****************************************************************************
    //***************[Constructors]***********************************************
    //****************************************************************************
    public Layer(String type, CoordList coords,  String beadType) {
        this.coords=coords;
        this.type=type;
        this.color = movePanel.getColor();
        this.beadType=beadType;
        zValue=ZZzzzz++;
        this.beadType=BeadLoom.BEAD_ADDRESS;
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
