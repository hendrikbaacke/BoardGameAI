package src;
import java.util.Hashtable;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Polygon;

public class Board extends Pane{
	//create rows manually
   private int HexRowStart = 5;
   public Hashtable<String, Polygon> hashBoard = new Hashtable();

   Board(){
	   //add();
   }

public Pane add() {
    Pane board = new Pane();
    for (int j = 0; j < 9; j++) {
    	char hexa = getCharFromAlphabet(9 - j - 1);
    	String letterCode = Character.toString(hexa);

    	for (int i = 0; i < HexRowStart; i++) {

    		int numberCode = i + 1;
    		if (j == 0) {
    			numberCode = numberCode + 4;
    		}
    		if(j==1) {
    			numberCode = numberCode + 3;
    		}
    		if(j ==2) {
    			numberCode = numberCode + 2;
    		}
    		if(j==3) {
    			numberCode = numberCode + 1;
    		}
    		String neededForHash = Integer.toString(numberCode);

    		double xCoord = i * Hexagon.Hexagon_Width + (j % 2) * Hexagon.n  +450;
    		double yCoord = j * Hexagon.Hexagon_Height * 0.75  + 140;


    		if (j == 0 || j == 8) {
    			xCoord = xCoord + Hexagon.Hexagon_Width *2;
    		}
    		if (j == 1 || j ==2 || j ==6 || j ==7) {
    			xCoord = xCoord + Hexagon.Hexagon_Width;
    		}

    		String key = letterCode + neededForHash;
    		System.out.println(key);

    		Polygon Hex = new Hexagon(xCoord, yCoord);
    		hashBoard.put(key, Hex);


    		board.getChildren().add(Hex);
    	}
    	if ( j < 4) {
    		HexRowStart++;
    	}
    	else {
    		HexRowStart--;
    	}
    }
    return board;
	}

	public static char getCharFromAlphabet(int i) {
		return (char)('A' + i);
	}


}

