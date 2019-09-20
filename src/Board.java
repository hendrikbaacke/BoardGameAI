import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Polygon;

public class Board extends AnchorPane{
	//create rows manually
   int HexRowStart = 5;
   Board(){
	   add();
   }
   

public AnchorPane add() {
    AnchorPane board = new AnchorPane();
    for (int j = 0; j < 9; j++) {
    	for (int i = 0; i < HexRowStart; i++) {

    		double xCoord = i * Hexagon.Hexagon_Width + (j % 2) * Hexagon.n  +450;
    		double yCoord = j * Hexagon.Hexagon_Height * 0.75  + 140;

    		if (j == 0 || j == 8) {
    			xCoord = xCoord + Hexagon.Hexagon_Width *2;
    		}
    		if (j == 1 || j ==2 || j ==6 || j ==7) {
    			xCoord = xCoord + Hexagon.Hexagon_Width;
    		}

    		Polygon Hex = new Hexagon(xCoord, yCoord);
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

}
