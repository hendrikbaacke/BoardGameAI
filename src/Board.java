package src;


import java.util.ArrayList;
import java.util.Hashtable;
import javafx.scene.layout.BorderPane;


public class Board extends BorderPane {
	//create rows
   private int HexRowStart = 5;
   public double CenterX;
   public double CenterY;
   public static Hashtable<String, Hexagon> hashBoard = new Hashtable();
   public  ArrayList<Hexagon> everyHex = new ArrayList<Hexagon>();
   public static Move move = new Move();

   public Board(double X, double Y) {
	   CenterX = X;
	   CenterY = Y;
   }

public BorderPane add() {
    BorderPane board = new BorderPane();
    double HexRow = HexRowStart;
    for (int j = 0; j < HexRowStart*2-1; j++) {
    	char hexa = getCharFromAlphabet(HexRowStart*2-1 - j - 1);
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
    		//Hex.setOnMouseClicked(e -> System.out.println("Clicked: " + Hex));

    		hashBoard.put(key, Hex);
    		everyHex.add(Hex);
         
    		
    		board.getChildren().addAll(Hex);
    	}
    	if ( j < HexRowStart-1) {
    		HexRow++;
    	}
    	else {
    		HexRow--;
    	}
    }
    for (int i = 0; i < everyHex.size(); i++) {
    	everyHex.get(i).createNeighbourList();
    }
    
    return board;
	}


	public static char getCharFromAlphabet(int i) {
		return (char)('A' + i);
	}


}

