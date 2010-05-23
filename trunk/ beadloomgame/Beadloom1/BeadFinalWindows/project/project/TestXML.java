/*******************************************************************
* 																   *
*	NAME:		TestXML.java			                           *
*	DATE:		4/22/08								               *
*	VERSION:	1.0.0							                   *
*	PURPOSE:	Test Class, used to test our XML save/load         *
*	DOCUMENT REFERENCE(S):	                    				   *
*											                       *
*	PROCEDURE INVOCATION:						                   *
*	               			                                       *
*				                                                   *
*	INPUT PARAMETERS:							                   *
*		                                                           *
*								                                   *
*	OUTPUT PARAMETERS:							                   *
*		                                                           *
*											                       *
*	ASSUMPTIONS: None				                               *
*	LIMITATIONS:   						                           *
*									                               *
*											                       *
*******************************************************************/

import java.util.*;
import nu.xom.*;
import java.io.*;
public class TestXML {
    private final static int TEST_SIZE=10;
    private final static int NUM_OF_LAYERS=5;
    public static void main(String[] args) throws ValidityException, IOException, ParsingException {
        ArrayList<Integer> x = new ArrayList<Integer>();
        ArrayList<Integer> y = new ArrayList<Integer>();
        CoordList test=null;
        ArrayList<Layer> testLayer = new ArrayList<Layer>();
        Random generator = new Random();
        for (int j=0;j<NUM_OF_LAYERS;j++) {
        	x.clear();
        	y.clear();
            for (int i=0;i<TEST_SIZE;i++) {
                x.add(generator.nextInt(50) - 25);
                y.add(generator.nextInt(50) - 25);
                test = new CoordList(x,y);
            }
            testLayer.add(new Layer("Square",test,"Black"));
            System.out.println(testLayer.get(j).toString());
        }
        //SaveLayer.saveLoom(testLayer, "test");
        //testLayer=SaveLayer.openLoom("test");
        System.out.println(testLayer.get(0).toString());
        System.out.println(testLayer.get(1).toString());
            }
}