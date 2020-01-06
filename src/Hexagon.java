package src;

import java.util.ArrayList;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

/*
 * The hexagons; the board consists of these.
 * They can contain a marble.
 * Each contains a list with the codes of every adjacent hexagon.
 */

public class Hexagon extends Polygon {
	   public static double radius ; // the inner radius from hexagon center to outer corner
       public static double n; // the inner radius from hexagon center to middle of the axis
	   public static double Hexagon_Height ;
	   public static double Hexagon_Width ;
	   public double centerX;
	   public double centerY;
	   public boolean empty = true;
	   public ArrayList<String> neighbours = new ArrayList<String>();
	   public String code;
	   public Marble marble;


	   Hexagon(){
           setFill(Color.ANTIQUEWHITE);
           setStrokeWidth(3);
           setStroke(Color.BLACK);
	   }
	   
	   Hexagon(double x, double y, String code) {
            /*
            creates the polygon, defined by an array of (x,y) coordinates
    		which represents the six points of a hexagon
    		*/
            getPoints().addAll(
                    x, y,
                    x, y + radius,
                    x + n, y + radius * 1.5,
                    x + Hexagon_Width, y + radius,
                    x + Hexagon_Width, y,
                    x + n, y - radius * 0.5
            );

            setFill(Color.ANTIQUEWHITE);
            setStrokeWidth(3);
            setStroke(Color.BLACK);
            
            this.code = code;

            //center point for marbles
            centerX = x + 0.5 * Hexagon_Width;
            centerY = y + 0.5 * radius;
        }
	   		
    	//link a marble to the hexagon it is in:
    	public void setFull(Marble m){
    		this.empty = false;
    		this.marble = m;
    	}
    	
    	//remove the marble from this hexagon
    	public void setEmpty() {
    		this.empty = true;
    		this.marble = null;
    	}
    	
    	//returns "true" if another hexagon is adjacent
    	public boolean adjacent(String other) {
    		if (neighbours.contains(other)) {
    			return true;
    		}
    		return false;
    	}
    	
    	
    	//creates a list of all neighbours (strings) of a hexagon
    	public void createNeighbourList() {
    		char lettercode = this.code.charAt(0);
    		char number = this.code.charAt(1);
    		int numbercode = Character.getNumericValue(number);
    		
    		char tempLetter = 'A';
    		int tempNumber = 0;
    		String code = null;
    		
    		for (int i = 0; i < 3; i++) {
    			for (int j = 0; j < 3; j++) {
    				tempLetter = (char) ((char) lettercode - 1 + i);
    				
    				if (i == 0 && j <= 1) {
    					tempNumber = numbercode -1+j;
    					code = Character.toString(tempLetter) + Integer.toString(tempNumber);
    						if(Board.hashBoard.containsKey(code)) {
    							neighbours.add(code);
    						}
    				}
    						
    				if (i == 1 && j != 1) {
    					tempNumber = numbercode -1+j;
    					code = Character.toString(tempLetter) + Integer.toString(tempNumber);
    						if(Board.hashBoard.containsKey(code)) {
    						neighbours.add(code);
    						}
    				}
    				
    				if (i == 2 && j > 0) {
    					tempNumber = numbercode -1+j;
    					code = Character.toString(tempLetter) + Integer.toString(tempNumber);
    						if(Board.hashBoard.containsKey(code)) {
    						neighbours.add(code);
    						}
    				}
    			}
    		}
    	}
    			
		//deep clone this hexagon, including the marble inside of it - also the whole neighbours list (as it is the same for hexagons with the same code)
		public Hexagon deepClone() {
			Hexagon clone = new Hexagon();
			for (int i = 0; i < this.getPoints().size(); i++) {
				clone.getPoints().addAll(this.getPoints().get(i));
			}
			clone.centerX = this.centerX;
			clone.centerY = this.centerY;
			clone.empty = this.empty;
			clone.code = this.code;
			
			if (!clone.empty) {
				clone.marble = this.marble.deepClone();
			}
			
			ArrayList<String> neighbourcopy = new ArrayList<>();
			for (int i = 0; i < neighbours.size(); i++) {
				neighbourcopy.add(neighbours.get(i));
			}
			clone.neighbours = neighbourcopy;
			return clone;
		}
		
    }
