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
   public static BoardRows rows;
   public static int[] score = new int[3];
   public static MarbleStorage boardMarbles;
   public static int numberPlayers = 2;
   public static Traceback tb = new Traceback();
   public static ArrayList<String> hash = new ArrayList();

   public Board(double X, double Y) {
	   CenterX = X;
	   CenterY = Y;
   }

public BorderPane add() {
    BorderPane board = new BorderPane();
    double HexRow = HexRowStart;

	Hexagon.radius = CenterY/10; // the inner radius from hexagon center to outer corner
	Hexagon.n = Math.sqrt(Hexagon.radius * Hexagon.radius * 0.75); // the inner radius from hexagon center to middle of the axis
	Hexagon.Hexagon_Height = 2 * Hexagon.radius;
	Hexagon.Hexagon_Width = 2 * Hexagon.n;
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
    		hash.add(key);
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
    rows = new BoardRows();
    
    return board;
	}


	public static char getCharFromAlphabet(int i) {
		return (char)('A' + i);
	}
	
	public static Hashtable<String, Hexagon> copyHashBoard(Hashtable<String, Hexagon> hex){
		Hashtable<String, Hexagon> newBoard = new Hashtable();
		//System.out.println("board class. copying board");
		for (char i= 'A'; i < 'J'; i++) {
			for (int j = 1; j < 10; j++) {
				String code = Character.toString(i) + j;
				//System.out.println(code);
				if (hex.containsKey(code)){
					newBoard.put(code, hex.get(code).deepClone());
					//System.out.println(code);
				}
			}
		}
		return newBoard;
	}
	
	public static boolean compareHashtables(Hashtable<String, Hexagon> one, Hashtable<String, Hexagon> two) {		
		for(int i = 0; i < hash.size(); i++) {
			if(!one.get(hash.get(i)).empty && !two.get(hash.get(i)).empty) {
				if (one.get(hash.get(i)).marble.playerNumber != two.get(hash.get(i)).marble.playerNumber) {
					return false;
				}
			}
			else if((one.get(hash.get(i)).empty && !two.get(hash.get(i)).empty) || (!one.get(hash.get(i)).empty && two.get(hash.get(i)).empty)) {
				return false;
			}
		}
		return true;
	}
}

