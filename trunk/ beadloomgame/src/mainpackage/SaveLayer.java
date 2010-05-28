/*******************************************************************
* 																   *
*	NAME:		SaveLayer.java			                           *
*	DATE:		4/22/08								               *
*	VERSION:	1.0.0						                       *
*	PURPOSE:	To parse a Layer ArrayList to XML and vice versa   *
*	DOCUMENT REFERENCE(S):	                    				   *
*											                       *
*	PROCEDURE INVOCATION:						                   *
*	    saveLoom(LayerArray, xmlFileName)           			   *
*		openLoom(xmlFileName)		                               *
*	INPUT PARAMETERS:							                   *
*		XML File Name                                              *
*		Array of Layers						                       *
*	OUTPUT PARAMETERS:							                   *
*		XML File                                                   *
*											                       *
*	ASSUMPTIONS: None				                               *
*	LIMITATIONS: Filename characters valid                         *
*									                               *
*											                       *
*******************************************************************/
package src.mainpackage;

import nu.xom.*; //Place xom.jar to jre/lib/ext directory

import java.io.*;
import java.net.URL;
import java.util.*;
import java.awt.*;
import java.awt.image.*;
import java.awt.Component.*;
import java.applet.*;

import javax.swing.JOptionPane;


// **************************************************************
// ********* XML Loom Saver *************************************
// **************************************************************
 /* Format:
  <?xml version="1.0" encoding="ISO-8859-1"?>
  <Loom>
    <Layer>
        <Options LType="Square" BColor="Black" Offset="0"
        BShape="Round"/>
        <Coordinates x0="6" y0="-15" x1="-21" y1="-23"
        x2="-21" y2="-7" x3="5" y3="9" x4="16" y4="0" x5="-17"
        y5="-11" x6="12" y6="6" x7="8" y7="0" x8="5" y8="-20"
        x9="11" y9="-14"/>
    </Layer>
  </Loom>
 */

public class SaveLayer { 

	private static ArrayList<Layer> layers;
	private static ArrayList<Integer> x;
	private static ArrayList<Integer> y;
	private static CoordList c;

	
    public static void saveLoom(ArrayList<Layer> loomLayer, ArrayList<Layer> undoLayer,
    		int undoPtr, boolean isClear, String xmlFileName) throws IOException, ValidityException {
        
    	//Check if layer array is empty
        if (loomLayer.size()<1) { System.out.println("No data to save."); return;}
        
        //Create a root node and begin XML tree
        Element loom = new Element("Loom");
        for (int i=0;i<loomLayer.size();i++) {
            Element layerElement= new Element("ActiveLayer");
            Element coords = new Element("Coordinates");
            Element options = new Element("Options");
            
            //Populate options
           Attribute layerType=new Attribute("LType",loomLayer.get(i).getType());
           Attribute color=new Attribute("BColor",""+loomLayer.get(i).getColor().getRGB());
           Attribute xOffset=new Attribute("XOffset",String.valueOf(loomLayer.get(i).getXOffset()));
           Attribute yOffset=new Attribute("YOffset",String.valueOf(loomLayer.get(i).getYOffset()));
           Attribute ZValue=new Attribute("ZValue",String.valueOf(loomLayer.get(i).getZValue()));
            
           //Add options attributes to element node
            options.addAttribute(layerType);
            options.addAttribute(color);
            options.addAttribute(xOffset);
            options.addAttribute(yOffset);
            options.addAttribute(ZValue);
            layerElement.appendChild(options);
            
            //Populate coordinates
            for (int j=0;j<loomLayer.get(i).getCoords().getSize();j++) {
                String x="x" +j;
                String y="y" +j;
                coords.addAttribute(new Attribute(x,String.valueOf(loomLayer.get(i).getCoords().getCoord(j, 0))));
                coords.addAttribute(new Attribute(y,String.valueOf(loomLayer.get(i).getCoords().getCoord(j, 1))));
            }
            layerElement.appendChild(coords);
            loom.appendChild(layerElement);  
        }
        //Undo layers array
        for (int i=0;i<undoLayer.size();i++) {
            Element layerElement= new Element("UndoLayer");
            Element coords = new Element("Coordinates");
            Element options = new Element("Options");
            
            //Populate options
           Attribute layerType=new Attribute("LType",undoLayer.get(i).getType());
           Attribute color=new Attribute("BColor",""+undoLayer.get(i).getColor().getRGB());
           Attribute xOffset=new Attribute("XOffset",String.valueOf(undoLayer.get(i).getXOffset()));
           Attribute yOffset=new Attribute("YOffset",String.valueOf(undoLayer.get(i).getYOffset()));
           Attribute ZValue=new Attribute("ZValue",String.valueOf(undoLayer.get(i).getZValue()));
            
           //Add options attributes to element node
            options.addAttribute(layerType);
            options.addAttribute(color);
            options.addAttribute(xOffset);
            options.addAttribute(yOffset);
            options.addAttribute(ZValue);
            layerElement.appendChild(options);
            
            //******************
            //Populate coordinates
            for (int j=0;j<undoLayer.get(i).getCoords().getSize();j++) {
                String x="x" +j;
                String y="y" +j;
                coords.addAttribute(new Attribute(x,String.valueOf(undoLayer.get(i).getCoords().getCoord(j, 0))));
                coords.addAttribute(new Attribute(y,String.valueOf(undoLayer.get(i).getCoords().getCoord(j, 1))));
            }
            layerElement.appendChild(coords);
            loom.appendChild(layerElement);  
        }
        Element undo = new Element("UndoOptions");
        Attribute ptr=new Attribute("undoPtr", ""+undoPtr);
        Attribute clear=new Attribute("isClear", ""+isClear);
        undo.addAttribute(ptr);
        undo.addAttribute(clear);
        loom.appendChild(undo);
        //Create a document file and write XML
        Document doc = new Document(loom);
        try {
              FileOutputStream out = new FileOutputStream(xmlFileName +".xml");
              Serializer serializer = new Serializer(out, "ISO-8859-1"); //output window out=System.out
              serializer.setIndent(4);
              serializer.setMaxLength(64);
              serializer.write(doc); 
            }
            catch (Exception ex) {
               System.err.println(ex);
            }             //Save image file

    }
 // **************************************************************
 // ********* XML Loom Loader*************************************
 // **************************************************************
    
    private static Layer loadLayer(Element layer)
    {
    	Layer newLayer = new Layer();
		Node elementNode = layer.getChild(1);
		
		if (elementNode instanceof Element) {
			
			Attribute attribute = null;
			Element layerAtt = (Element) elementNode;
			String name = layerAtt.getQualifiedName().trim(); 
	    	if(name == "Options"){
	    		
	    		attribute = layerAtt.getAttribute(0);
	    		String temp = attribute.getValue();
	    		newLayer.setType(temp);
	    		
	    		attribute = layerAtt.getAttribute(1);
	    		temp = attribute.getValue();
	    		newLayer.setColor(new Color(Integer.parseInt(temp)));
	    		
	    		attribute = layerAtt.getAttribute(2);
	    		temp = attribute.getValue();
	    		newLayer.setXOffset(Integer.parseInt(temp));
	    		
	    		attribute = layerAtt.getAttribute(3);
	    		temp = attribute.getValue();
	    		newLayer.setYOffset(Integer.parseInt(temp));
	    		
	    		attribute = layerAtt.getAttribute(4);
	    		temp = attribute.getValue();
	    		newLayer.setZValue(Integer.parseInt(temp));
	    	}
	    	else return null;
	    	
	    	elementNode = layer.getChild(3);
	    	if (elementNode instanceof Element) {
	    		
	    		layerAtt = (Element) elementNode;
				name = layerAtt.getQualifiedName().trim();
				if(name == "Coordinates"){
					
					x = new ArrayList<Integer>();
	            	y = new ArrayList<Integer>();
					for (int i=0;  i<layerAtt.getAttributeCount(); i++) { 
						
						attribute = layerAtt.getAttribute(i);
						String attValue = attribute.getValue();
						if (attribute.getQualifiedName().charAt(0)=='x'){ 
							x.add(Integer.parseInt(attValue)); 
						} 
	                	else { y.add(Integer.parseInt(attValue)); }
					}
				}
				else return null;
				newLayer.setCoords(new CoordList(x,y));
				//newLayer.setImage(makeBullet(newLayer.getColor())); still need to do this
	    	}
		}
		return newLayer;
    }
    
    public static int openLoom(File xmlFile, ArrayList<Layer> layers, ArrayList<Layer> undo)  {
    	
    	System.out.println(xmlFile.getPath());
    	int undoPtr = 0;
    	layers.clear();
    	undo.clear();
    	
    	Builder builder = new Builder();
    	Document doc = null;
		try {doc = builder.build(xmlFile);}
		catch(Exception e){
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(null, "Load XML file failed");
			e.printStackTrace();
			return 0;
		}
		
    	Element docRoot = doc.getRootElement();
    	for(int i=0; i<docRoot.getChildCount(); i++)
    	{
    		Node childNode = docRoot.getChild(i);
    		if (childNode instanceof Element) {
    			
    			Element layer = (Element) childNode;
	    		String layerName = layer.getQualifiedName().trim();
	    		
	    		if(layerName == "ActiveLayer"){
	    			Layer l = loadLayer(layer);
	    			if(l != null) layers.add(l);
	    		}
	    		else if(layerName == "UndoLayer"){
	    			Layer l = loadLayer(layer);
	    			if(l != null) undo.add(l);
	    		}
	    		else if(layerName == "UndoOptions"){
	    			Attribute attribute = layer.getAttribute(0);
		    		undoPtr = Integer.parseInt(attribute.getValue());
		    		return undoPtr;
	    		}
    		}
    	}
    	return 0;
    }
    
    public static ArrayList<Layer> openLoomURL(URL xmlFileName)  {


        	layers=new ArrayList<Layer>();
        	Builder builder = new Builder();
        	Document doc = null;
    		try {doc = builder.build(xmlFileName.toString());}
    		catch(Exception e){e.printStackTrace();}
        	Element root = doc.getRootElement();
        	//layerCount=-1;
        	//listChildren(root, 0);
   
    	return layers;
    }
    
      //Prints spaces in system.out depending on node depth
      private static void printSpaces(int n) { for (int i = 0; i < n; i++) { System.out.print(' '); }}}


