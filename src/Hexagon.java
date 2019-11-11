package src;

import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class Hexagon extends Polygon {
	   public static double radius ; // the inner radius from hexagon center to outer corner
       public static double n; // the inner radius from hexagon center to middle of the axis
	   public static double Hexagon_Height ;
	   public static double Hexagon_Width ;
	   public double centerX;
	   public double centerY;
	   public boolean empty = true;
	   public ArrayList<Hexagon> neighbours = new ArrayList<Hexagon>();
	   public String code;
	   public Marble marble;


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

            
            this.setCursor(Cursor.MOVE);
            this.setOnMouseClicked(HexagonOnMouseClicked);
            // set up the visuals and a click listener for the tile
            setFill(Color.ANTIQUEWHITE);
            setStrokeWidth(3);
            setStroke(Color.BLACK);
            
            this.code = code;

            //centerpoint for marbles
            centerX = x + 0.5 * Hexagon_Width;
            centerY = y + 0.5 * radius;
        }
    	
    	//link a marble to the hexagon it is in:
    	public void setFull(Marble m){
    		this.empty = false;
    		this.marble = m;
    	}
    	
    	public void setEmpty() {
    		this.empty = true;
    		this.marble = null;
    	}
    	
    	public String getFull() {
    		if (empty) {
    			return "empty";
    		}
    		return "full";
    	}
    	
    	//returns "true" if another hexagon is adjacent
    	public boolean adjacent(Hexagon other) {
    		if (neighbours.contains(other)) {
    			return true;
    		}
    		return false;
    	}
    	
    	
    	//creates a list of all neighbours, so it's easy to check whether another hexagon is adjacent
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
    							neighbours.add(Board.hashBoard.get(code));
    						}
    				}
    				
    						
    				if (i == 1 && j != 1) {
    					tempNumber = numbercode -1+j;
    					code = Character.toString(tempLetter) + Integer.toString(tempNumber);
    						if(Board.hashBoard.containsKey(code)) {
    						neighbours.add(Board.hashBoard.get(code));
    						}
    				}
    				
    				
    				if (i == 2 && j > 0) {
    					tempNumber = numbercode -1+j;
    					code = Character.toString(tempLetter) + Integer.toString(tempNumber);
    						if(Board.hashBoard.containsKey(code)) {
    						neighbours.add(Board.hashBoard.get(code));
    						}
    				}
    			}
    		}
    	}
    	
    	EventHandler<MouseEvent> HexagonOnMouseClicked =
				new EventHandler<MouseEvent>() {

					@Override
					public void handle(MouseEvent t) {
						//System.out.println(code);
						Board.move.select(code);
						
					}
				};
		
    }
