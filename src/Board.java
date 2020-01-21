package src;

import java.util.ArrayList;
import java.util.Hashtable;
import javafx.scene.layout.BorderPane;


/*
 * A class to create the original board, stores information about it.
 * Methods to manipulate different boards are in BoardMethods (general, for all hashtables similar to this one).
 */

public class Board extends BorderPane {
   
	//the board itself
   private double CenterX;
   private double CenterY;
   public static Hashtable<String, Hexagon> hashBoard = new Hashtable<>();
   public static MarbleStorage boardMarbles;
   public static ArrayList<String> hash = new ArrayList<>();
   
   // the board is created starting with its center
   public Board(double X, double Y) {
	   CenterX = X;
	   CenterY = Y;
   }
   
   //adding the marbles manually
   public BorderPane add() {
	   int HexRowStart = 5;
	   BorderPane board = new BorderPane();
	    double HexRow = HexRowStart;
	
		Hexagon.radius = CenterY/10; // the inner radius from hexagon center to outer corner
		Hexagon.n = Math.sqrt(Hexagon.radius * Hexagon.radius * 0.75); // the inner radius from hexagon center to middle of the axis
		Hexagon.Hexagon_Height = 2 * Hexagon.radius;
		Hexagon.Hexagon_Width = 2 * Hexagon.n;
	    for (int j = 0; j < HexRowStart*2-1; j++) {
	    	char hexa = (char)('A' + (HexRowStart*2-1 - j - 1));
	    	String letterCode = Character.toString(hexa);
	
	    	for (int i = 0; i < HexRow; i++) {
	
	    		int numberCode = i + 1;
	    		if (j >= 0 && j <=3) {
	    			numberCode = numberCode + 4-j;
	    		}
			
	    		String neededForHash = Integer.toString(numberCode);
	    		double xCoord = i * Hexagon.Hexagon_Width + (j % 2) * Hexagon.n  + CenterX-Hexagon.Hexagon_Width*(HexRowStart-0.5);
	    		double yCoord = j * Hexagon.Hexagon_Height * 0.75  + CenterY-Hexagon.Hexagon_Height*2-3*Hexagon.radius;
	
	    		if (j == 0 || j == HexRowStart*2-2) {
	    			xCoord = xCoord + Hexagon.Hexagon_Width *2;
	    		}
	    		
	    		if (j == 1 || j ==2 || j ==HexRowStart*2-4 || j ==HexRowStart*2-3) {
	    			xCoord = xCoord + Hexagon.Hexagon_Width;
	    		}
	    		
	    		if(HexRowStart>5){
	    			if(j>=5 && j<HexRowStart){
						xCoord = xCoord - Hexagon.Hexagon_Width*((int)(j-3)/2);
					}
					if(j>=HexRowStart && j<HexRowStart*2-6){
						xCoord = xCoord - Hexagon.Hexagon_Width*((int)(j-HexRowStart+2)/2);
					}
				}
	    		
	    		String key = letterCode + neededForHash;
	    		Hexagon Hex = new Hexagon(xCoord, yCoord, key);
	    		hashBoard.put(key, Hex);
	    		hash.add(key);
	    		
	    		board.getChildren().addAll(Hex);
	    	}
	    	if ( j < HexRowStart-1) {
	    		HexRow++;
	    	}
	    	else {
	    		HexRow--;
	    	}
	    }
	    for (int i = 0; i < hash.size(); i++) {
	    	hashBoard.get(hash.get(i)).createNeighbourList();
	    }
	    
	    GameData.rows = new BoardRows();

	    return board;
   }
}

