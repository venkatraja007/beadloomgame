/*******************************************************************
* 																   *
*	NAME:		GridPanel.java			                           *
*	DATE:		4/22/08								               *
*	VERSION:	1.0.0							                   *
*	PURPOSE:	Displays layers of beads onto a graph              *
*	DOCUMENT REFERENCE(S):	                    				   *
*											                       *
*	PROCEDURE INVOCATION:						                   *
*	         paintComponent(Graphics)      			               *
*			 		                                               *
*	INPUT PARAMETERS:							                   *
*		     Graphics                                              *
*			 					                                   *
*	OUTPUT PARAMETERS:							                   *
*		     none                                                  *
*											                       *
*	ASSUMPTIONS: None				                               *
*	LIMITATIONS:   						                           *
*									                               *
*											                       *
*******************************************************************/

package src.mainpackage;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.Graphics;
import java.awt.geom.*;
import java.util.ArrayList;

class GridPanel extends JPanel {
	private ArrayList<Layer> layer = new ArrayList<Layer>();
	private ArrayList<Layer> undo = new ArrayList<Layer>(); //all inactive and active layers
	//private ArrayList<ArrayList<Layer>> undo = new ArrayList<ArrayList<Layer>>();
	//private ArrayList<ArrayList<Layer>> redo = new ArrayList<ArrayList<Layer>>();
    private int undoPtr = 0;
	private BeadLoom bl;
    private boolean repaint = true;
    private boolean hidelines = true;
    private boolean isMainGrid = true;
    private boolean isClear = false;
    
    //Single Array representation of all the Layers
    private Color[][] gameGrid = new Color[41][41];
    
    int
        GRID_SIZE = 40, // default grid size
        DRAW      = 0,
        FILL      = 1,
        PAD       = 15;
    	double w;
    	double h;
    	double xInc;
    	double yInc;
    	
    	int testCounter = 0;
        
    	Image catimage;
 
    public void calcGameGrid(){
    	//Reset to empty value
    	for(int y = 0; y < gameGrid.length; y++){
    		for (int x = 0; x < gameGrid.length; x++){
    			gameGrid[x][y] = new Color(254, 254, 254);
    		}
    	}
    	
    	Layer[] layerarray = new Layer[layer.size()];
    	layer.toArray(layerarray);
    	
    	for(int i = 0; i < layerarray.length; i++){
    		for (int j=0;j<layerarray[i].getCoords().getSize();j++) {
    			if (layerarray[i].getXOffset()+layerarray[i].getCoords().getCoord(j, 0) + (GRID_SIZE/2)>=0 &&
    					layerarray[i].getXOffset()+layerarray[i].getCoords().getCoord(j, 0) + (GRID_SIZE/2)<=GRID_SIZE	&&
    					layerarray[i].getYOffset()-layerarray[i].getCoords().getCoord(j, 1)+ (GRID_SIZE/2)>= 0 &&
    					layerarray[i].getYOffset()-layerarray[i].getCoords().getCoord(j, 1)+ (GRID_SIZE/2) <= GRID_SIZE){
    				
    				gameGrid[layerarray[i].getXOffset()+layerarray[i].getCoords().getCoord(j, 0) + (GRID_SIZE/2)]
    				         [layerarray[i].getYOffset()+layerarray[i].getCoords().getCoord(j, 1)+ (GRID_SIZE/2)] = layerarray[i].getColor();
    			}
    		}
    	}
    }
    
    public Color getColorAt(int x, int y){
    	return gameGrid[x][y];
    }
    	
    public GridPanel(BeadLoom bl) {
    	this.bl=bl;
    }
    public void setMain(boolean m){isMainGrid = m;}
    
    public void setGridSize(int size){GRID_SIZE = size;}
    
    public void setLines() {
    	if (hidelines == true) {
    		hidelines = false;
    	}
    	else {
    		hidelines = true;
    	}
    }
 
    protected void paintComponent(Graphics g)
    {
    	if(isMainGrid)
    	{
	    	JComboBox lb = bl.getInputTools().getLayerList();
	    	Layer temp = bl.getInputTools().getLay();
	    	lb.removeAllItems();
	    	
			try {			
				for (int i = 0; i < getLayers().size(); i++) {
					lb.addItem((getLayers().get(i)));
				}			
			}catch (Exception exc){JOptionPane.showMessageDialog(null, "No Layers");}
			lb.setSelectedItem(temp);
    	}
    	w = getWidth();
    	h = getHeight();
    	xInc = (w - 2*PAD)/getGridSize();
    	yInc = (h - 2*PAD)/getGridSize();
    	
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);
        
        if (hidelines) {
        
        	// row lines
        	double x1 = PAD, y1 = PAD, x2 = w - PAD, y2 = h - PAD;
        	BasicStroke stroke = new BasicStroke(1.0f);
        	BasicStroke wideStroke = new BasicStroke(3.0f);
        	for(int j = 0; j <= getGridSize(); j++)
        	{
        		if (j==getGridSize()/2){ g2.setStroke(wideStroke); }
        		else g2.setStroke(stroke);
        		g2.draw(new Line2D.Double(x1, y1, x2, y1));
        		y1 += yInc;
        	}
        	// col lines
        	y1 = PAD;
        	for(int j = 0; j <= getGridSize(); j++)
        	{
        		if (j==getGridSize()/2){ g2.setStroke(wideStroke); }
        		else g2.setStroke(stroke);
        		g2.draw(new Line2D.Double(x1, y1, x1, y2));
        		x1 += xInc;
        	}
        }
            
        //BEAD REPAINTING
        int iconSize=catimage.getHeight(null)/2;
        
        for (int i = 0; i < layer.size(); i++) {
        //int i = layer.size() - 1;
        //if (i > -1){
        	for (int j=0; j < layer.get(i).getCoords().getSize();j++) {
        		int xcoord = (int)((int)((layer.get(i).getCoords().getCoord(j, 0)  + (layer.get(i).getXOffset()))+ (getGridSize()/2)) * xInc + PAD)-iconSize;
        		int ycoord = ((int)(yInc*((getGridSize()/2) - (layer.get(i).getCoords().getCoord(j, 1) ) + layer.get(i).getYOffset())) + PAD-iconSize);
        		int x = layer.get(i).getCoords().getCoord(j, 0) + layer.get(i).getXOffset();
        		int y = layer.get(i).getCoords().getCoord(j, 1) - layer.get(i).getYOffset();
        		if(x <= GRID_SIZE / 2 && x >= (GRID_SIZE / 2) * -1 && 
        				y <= GRID_SIZE / 2 && y >= (GRID_SIZE / 2) * -1)
        			g.drawImage(layer.get(i).getImage(), xcoord, ycoord, this);       	
        	}
        	//testCounter++;
        }
    }

    
    public void  setImage(Image ii) {
    	catimage = ii;
    }
    public Image getImage(){
    	return catimage;
    }
 
   
    public void mouseClicked(MouseEvent e) { }
    
    public void mousePressed(MouseEvent e) { }
    
    public void mouseEntered(MouseEvent e) { }
    
    public void mouseExited(MouseEvent e) { }
    
    public void mouseReleased(MouseEvent e) { }
    
    public void mouseDragged(MouseEvent e) { }
    
    public void mouseMoved(MouseEvent e) {}
   
    //When mouse is moved, mouse position on the grid is found
    public JLabel findMousePosition(MouseEvent e) {
    	
    	JLabel l = new JLabel();
       int get_row = (e.getY());
       int get_col = (e.getX());

       if((e.getX()- PAD)<0)
          l = output("[ Beadloom ]");
       else if ((e.getY()- PAD)<0)
          l = output("[ Beadloom ]");
       	  double OGx = ((get_col-PAD)/xInc)-(getGridSize()/2);
       	  int roundX = (int)Math.round(OGx);
       	  double OGy = ((getGridSize()/2)-(get_row-PAD)/yInc);
       	  int roundY = (int)Math.round(OGy);
          l = output( "[ X = " + roundX+
                  " : Y = " + roundY + "]");
       return l;
    }
    
    //Find the X component of MousePosition
    //Acey Boyce
    public int findMouseX(MouseEvent e){
    	int get_col = (e.getX());
    	double OGx = ((get_col-PAD)/xInc)-(getGridSize()/2);
    	int roundX = (int)Math.round(OGx);
    	return roundX;
    }
    
  //Find the Y component of MousePosition
    //Acey Boyce
    public int findMouseY(MouseEvent e){
    	int get_row = (e.getY());
    	double OGy = ((getGridSize()/2)-(get_row-PAD)/yInc);
    	int roundY = (int)Math.round(OGy);
    	return roundY;
    }
    
	public void paint(Image i,Graphics g, int x, int y) {
		g.drawImage(i,x,y,this);
	}

    public JLabel output(String s) {
    	
    	JLabel l = new JLabel(s);  	
    	return l;
    }
    
    public void setPanelGridSize(int x){
    	GRID_SIZE = x;
    	bl.doBeadSetting(null, this);
    	for(int i = 0; i < layer.size(); i++)
    		bl.doBeadSetting(layer.get(i), this);
    	for(int i = 0; i < undo.size(); i++)
    		bl.doBeadSetting(undo.get(i), this);
    		/*for(int j = 0; j < undo.get(i).size(); j++)
    			bl.doBeadSetting(undo.get(i).get(j), this);
    	for(int i = 0; i < redo.size(); i++)
    		for(int j = 0; j < redo.get(i).size(); j++)
    			bl.doBeadSetting(redo.get(i).get(j), this);*/
    }
    
    
    public int getGridSize() {
		return GRID_SIZE;
	}
    
    public int getPad() {
		return PAD;
	}
    
    public double getXINC() {
		return xInc;
	}
    
    public double getYINC() {
		return yInc;
	}
    
    public void refreshGrid() {
    	repaint();
    }

    //*****************************************************************************
    //***************Bead Manipulation Methods*************************************
    //*****************************************************************************
    
    public void addLayer(Layer l) { 
    	System.out.println("Layer added to " + this);
    	bl.getInputTools().setLay(l);
    	bl.getTop().enableUndo(true);
    	if(isClear == true){
    		if(layer.size()==0){
    		undo.clear();
    		undoPtr = 0;
    		}
    		isClear = false;
    	}
    	
    	layer.add(l);
    	if(undo.size() == 0) undo.add(l);
    	else{
    		if(undoPtr!=undo.size()-1){
    			cloneLayers(layer,undo);
    			undoPtr = undo.size()-1;
    		}
    		else{
    			undoPtr++;
    			undo.add(l);
    		}
    	}
    	if(isClear == false) bl.getTop().enableRedo(false);
    	repaint();
    }
    
    public void undo() {
    	bl.getTop().enableRedo(true);
    	if(isClear == true){
    		if(layer.size()==0){
    			cloneLayers(undo,layer);
    			repaint();
    			return;
    		}
    	}
    	int zVal = undo.get(undoPtr).getZValue();
    	boolean found = false; //If a layer is found in both arrays, undoPtr-- and remove from layer
    	
    	for(int i=0; i<layer.size(); i++){
    		if(layer.get(i).getZValue() == zVal){
    			found = true;
    			if(undoPtr != -1) undoPtr--;
    			if(undoPtr == -1) bl.getTop().enableUndo(false);
    			layer.remove(i);
    			break;
    		}
    	}
    	if(found == false){
    		layer.add(undo.get(undoPtr));
    	}
    	repaint();
    }
    
    public void redo() {
    	bl.getTop().enableUndo(true);
    	if (undoPtr==undo.size()-1){
    		if(isClear == true)layer.clear();
    		bl.getTop().enableRedo(false);
    	}
    	else {
	    	undoPtr++;
	    	layer.add(undo.get(undoPtr));
	    	if (undoPtr==undo.size()-1 && isClear == false){
	    		bl.getTop().enableRedo(false);
	    	}
	    	repaint();
    	}
    }
    
    public void shift(String direction, int i) {
    	for (int x=0;x<layer.size();x++) {
    		if (layer.get(x).getZValue()==i){i=x; break; }
    	}
    		if (direction.equals("Left")) layer.get(i).setXOffset(layer.get(i).getXOffset()-1);
    		else if (direction.equals("Right")) layer.get(i).setXOffset(layer.get(i).getXOffset()+1);
    		else if (direction.equals("Up"))
    			layer.get(i).setYOffset(layer.get(i).getYOffset()-1);
    		else if (direction.equals("Down")) layer.get(i).setYOffset(layer.get(i).getYOffset()+1);
    	
    }
    
    public ArrayList<Layer> getLayers() {
    	return layer;
    }
    
    public ArrayList<Layer> getUndoLayers() {
    	return undo;
    }
    
    public int getUndoPtr() {
    	return undoPtr;
    }
    
    public boolean getClear() {
    	return isClear;
    }
    
    public void clear() {
    	bl.getTop().enableRedo(false);
    	cloneLayers(layer,undo);
		undoPtr = undo.size()-1;
    	layer.clear();
    	isClear = true;
    	repaint();
    }
    
    public void setLayers(ArrayList<Layer> activeLayers, ArrayList<Layer> undoLayers, int ptr) {
    	layer = activeLayers;
    	undo = undoLayers;
    	undoPtr = ptr;
    	if(ptr < undoLayers.size()-1) bl.getTop().enableRedo(true);
    	if(ptr > 0 ) bl.getTop().enableUndo(true);
    }
    
    public void setLayers(ArrayList<Layer> layers) {
    	layer = layers;
    }
    
    public void newGrid() {
    	Object[] options = { "YES", "NO", "CANCEL" };
    	int choice = JOptionPane.showOptionDialog(this, "Do you wish to save this session?", "Save?", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null,options, 0);
    	if (choice==0) bl.saveCurrentLoom();
    	if (choice==0 || choice==1) {
    		undoPtr = 0;
    		layer.clear();
    		undo.clear();
    		//redo=new ArrayList<ArrayList<Layer>>();
    		repaint();
    	}
    }
    
    public void resetBeadType()
    {
    	for(int i=0; i<layer.size(); i++)
    	{
    		//layer.get(i).setBeadType(BeadLoom.beadLocation);
    		Color beadColor = layer.get(i).getColor();
    		layer.set(i, new Layer(layer.get(i).getType(), layer.get(i).getCoords(), BeadLoom.beadLocation));
    		layer.get(i).setImage(bl.getInputTools().getMakeBullet(beadColor, getWidth(), getHeight()));
    		layer.get(i).setColor(beadColor);
    	}
    }
    
    public void setRepaintFalse() {
    	repaint = false;
    	clear();
    }
    
    public void cloneLayer(Layer source, ArrayList<Layer> dest){
    	Layer l = new Layer();
    	l.setBeadType(source.getBeadType());
    	l.setColor(source.getColor());
    	l.setCoords(source.getCoords());
    	l.setImage(source.getImage());
    	l.setType(source.getType());
    	l.setXOffset(source.getXOffset());
    	l.setYOffset(source.getYOffset());
    	l.setZValue(source.getZValue());
    	dest.add(l);
    }
    
    //Takes one ArrayList, creates new memory location
    public void cloneLayers(ArrayList<Layer> source, ArrayList<Layer> dest){
    	dest.clear();
    	for (int i=0;i<source.size();i++) {
    		cloneLayer( source.get(i),dest);
    	}
    }  
    
    public void deleteLayer(Layer lay) { //zVal needs to be restored upon load
    	bl.getTop().enableUndo(true);
    	
    	if(isClear == true){
    		if(layer.size()==0){
    		undo.clear();
    		undoPtr = 0;
    		}
    		isClear = false;
    	}

		layer.remove(lay);//No longer in the working set
    	if(undoPtr!=undo.size()-1){
    		cloneLayers(layer,undo);
    		undo.add(lay);
    		undoPtr = undo.size()-1;
    	}
    	else{
    	undo.remove(lay);
    	undo.add(lay); //Add to last entry in undo array
    	}
    	if(isClear == false) bl.getTop().enableRedo(false);
    	repaint();
    }
    
    public Color[][] getGameGrid()
    {
    	return gameGrid;
    }
    
    public void setGameGrid(Color[][] newGrid)
    {
    	if(gameGrid.length == newGrid.length && gameGrid[0].length == newGrid[0].length)
    	{
    		gameGrid = newGrid.clone();
    	}
    }
}