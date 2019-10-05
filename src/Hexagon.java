package src;
import java.util.ArrayList;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class Hexagon extends Polygon {
	   public static double radius = 30; // the inner radius from hexagon center to outer corner
       public static double n = Math.sqrt(radius * radius * 0.75); // the inner radius from hexagon center to middle of the axis
	   public static double Hexagon_Height = 2 * radius;
	   public static double Hexagon_Width = 2 * n;
	   public double centerX;
	   public double centerY;
	   public boolean empty = true;
	   public ArrayList<Hexagon> neighbours = new ArrayList<Hexagon>();
	   public String code;
	   public Marble marble;

    	Hexagon(double x, double y) {
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

            // set up the visuals and a click listener for the tile
            setFill(Color.ALICEBLUE);
            setStrokeWidth(3);
            setStroke(Color.BLACK);
            
            createNeighbourList();
            
            System.out.println("isSettingClicked");
            setOnMouseClicked(e -> System.out.println("Clicked"));
            
            
            centerX = x + 0.5 * Hexagon_Width;
            centerY = y + 0.5 * radius;
        }
    	
    	public void setCode(String code) {
    		this.code = code;
    	}
    	
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
    	
    	public boolean adjacent(Hexagon other) {
    		boolean adjacent = false;
    		
    		
    		return adjacent;
    	}
    	
    	public void createNeighbourList() {
    		//for ()
    		//this.neighbours.add(e)
    	}
    }
