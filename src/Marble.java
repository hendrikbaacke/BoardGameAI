
import javafx.scene.Group;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

public class Marble extends Ellipse{
	private String locationKey;
	private int playerNumber;
	
	Marble(double centerX, double centerY, int radius, int player){
		Ellipse e = new Ellipse(centerX, centerY, radius, radius);
		this.playerNumber = player;
		if(player == 1) {
			e.setFill(Color.DARKMAGENTA);
		}
		if(player == 2) {
			e.setFill(Color.PINK);
		}
		MarbleStorage.pieceGroup.getChildren().add(e);
	}
	
	public void setLocationKey(String key){
		this.locationKey = key;
	}
	
	public String getLocationKey() {
		return locationKey;
	}
	
	public void updateLocation() {
		Hexagon hex = (Hexagon) Board.hashBoard.get(locationKey);
		double tempX = hex.centerX ;
		double tempY = hex.centerY;
		
		
	}
	
}
