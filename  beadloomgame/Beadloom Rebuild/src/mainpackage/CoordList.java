package mainpackage;
/*******************************************************************
* 																   *
*	NAME:		CoordList.java			                           *
*	DATE:		4/22/08								               *
*	VERSION:	1.0.0							                   *
*	PURPOSE:	Holds arrays of coordinate values for each Layer   *
*				to be graphed onto the GridPanel                   *
*	DOCUMENT REFERENCE(S):	                    				   *
*											                       *
*	PROCEDURE INVOCATION:						                   *
*	         sortByX()      			                           *
*			 sortByY()	                                           *
*	INPUT PARAMETERS:							                   *
*		     ArrayList of Coordinates                              *
*								                                   *
*	OUTPUT PARAMETERS:							                   *
*		     Sorted ArrayList of Coordinates                       *
*											                       *
*	ASSUMPTIONS: Equal number of elements in both ArrayLists       *
*	LIMITATIONS:               							           *
*									                               *
*											                       *
*******************************************************************/

import java.util.ArrayList;

//This simple data structure saves a list of coordinate pairs
public class CoordList {
  private ArrayList<Integer> xValue; //must have same amount of elements in each
  private ArrayList<Integer> yValue;
  public CoordList(ArrayList<Integer> xValue, ArrayList<Integer> yValue) {
      this.xValue=xValue;
      this.yValue=yValue;
  }
  public int getCoord(int n, int plane) { //For plane, 0=x, 1=y.  n is index
      if (plane==0) { return xValue.get(n); }
      else {return yValue.get(n); }
  }
  public void addValues(int x, int y) {
      xValue.add(x);
      yValue.add(y);
  }
  public int getSize() {
	  return xValue.size();
  }
  public void addX(int x) {
	  xValue.add(x);
  }
  public void addY(int y) {
	  yValue.add(y);
  }
 public void sortByX(int start, int stop)
 {
	 ArrayList<Integer> newXValues = new ArrayList();
	 ArrayList<Integer> newYValues = new ArrayList();
	 for(int i = start; i <= stop; i++)
	 {
		 for(int j = 0; j < xValue.size(); j++)
		 {
			 if(xValue.get(j) == i)
			 {
				 newXValues.add(xValue.get(j));
				 newYValues.add(yValue.get(j));
			 }
		 }
	 }
	 xValue = newXValues;
	 yValue = newYValues;
 }
 
 public void sortByY()
 {
	 ArrayList<Integer> tempX = new ArrayList();
	 ArrayList<Integer> tempY = new ArrayList();
	 tempX.add(xValue.get(0));
	 tempY.add(yValue.get(0));
	 int temp;
	 for(int i = 0; i < xValue.size() - 1; i++)
	 {
		 if(xValue.get(i) == xValue.get(i + 1))
		 {
			 tempX.add(xValue.get(i + 1));
			 tempY.add(yValue.get(i + 1));
		 }
		 else
		 {
			 sort(tempX, tempY);
			 temp = tempX.size() - 1;
			 for(int j = 0; j < tempX.size(); j++)
			 {
				 xValue.set(i - temp, tempX.get(j));
				 yValue.set(i - temp, tempY.get(j));
				 --temp;
			 }
			 tempX.clear();
			 tempY.clear();
			 tempX.add(xValue.get(i + 1));
			 tempY.add(yValue.get(i + 1));
		 }
	 }
	 sort(tempX, tempY);
	 temp = tempX.size();
	 for(int j = 0; j < tempX.size(); j++)
	 {
		 xValue.set(xValue.size() - temp, tempX.get(j));
		 yValue.set(yValue.size() - temp, tempY.get(j));
		 temp--;
	 }
 }
 
 public void sort(ArrayList<Integer> x, ArrayList<Integer> y)
 {
	 int temp;
	 for(int i = 0; i < x.size() - 1; i++)
		 for(int j = i + 1; j < x.size(); j++)
			 if(y.get(j) < y.get(i))
			 {
				 temp = y.get(i);
				 y.set(i, y.get(j));
				 y.set(j, temp);
				 temp = x.get(i);
				 x.set(i, x.get(j));
				 x.set(j, temp);
			 }
 }
 
 public void fillTriangle()
 {
	 int loopCount = xValue.size() - 1; // size changes in loop
	 for(int i = 0; i < loopCount; i++)
		 if(xValue.get(i) == xValue.get(i + 1) && yValue.get(i) != yValue.get(i + 1) 
				 && yValue.get(i) != yValue.get(i + 1) - 1)
			 for(int j = 1; j < yValue.get(i + 1) - yValue.get(i); j++)
			 {
				 xValue.add(xValue.get(i));
				 yValue.add(yValue.get(i) + j);
			 }
 }
 
  public String toString() {
      String output="";
      if ((xValue.size()>0) && (yValue.size()>0)) {
    	  for (int i=0;i<xValue.size();i++) {
    		  output+="("+xValue.get(i)+","+yValue.get(i)+")";
    		  }
    	  }
      else { System.out.println("CoordList is empty!"); }
      output+="\n";
      return output;
  }
}
