import javafx.scene.Group;
import javafx.scene.shape.Ellipse;

public class Marble extends Ellipse{
	private String locationKey;
	private int playerNumber;
	
	Marble(double x, double y, double radius, int player){
		super(x, y, radius, radius);
		this.playerNumber = player;
	}
	
	Marble(String location, int player, double radius){
		super(Board.hashBoard.get(location).centerX, Board.hashBoard.get(location).centerY, radius, radius);
		this.locationKey = location;
		this.playerNumber = player;
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
		
		this.setCenterX(tempX);
		this.setCenterY(tempY);
	}
	
}
