
import java.util.Hashtable;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Polygon;

public class Board extends Pane{
	//create rows manually
   private int HexRowStart = 5;
   public double CenterX;
   public double CenterY;
   public Hashtable<String, Polygon> hashBoard = new Hashtable();

   public Board(double X, double Y) {
	   CenterX = X;
	   CenterY = Y;
   }

public Pane add() {
    Pane board = new Pane();
    double HexRow = HexRowStart;
    for (int j = 0; j < HexRowStart*2-1; j++) {
    	char hexa = getCharFromAlphabet(HexRowStart*2-1 - j - 1);
    	String letterCode = Character.toString(hexa);

    	for (int i = 0; i < HexRow; i++) {
/*
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
*/
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


    		//String key = letterCode + neededForHash;
    		//System.out.println(key);

    		Polygon Hex = new Hexagon(xCoord, yCoord);
    		//hashBoard.put(key, Hex);


    		board.getChildren().add(Hex);
    	}
    	if ( j < HexRowStart-1) {
    		HexRow++;
    	}
    	else {
    		HexRow--;
    	}
    }
    return board;
	}

	public static char getCharFromAlphabet(int i) {
		return (char)('A' + i);
	}


}

