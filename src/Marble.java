package src;

import java.util.Hashtable;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

/*
 * Handles the marbles with which a game is played. 
 * Things such as color are automatically set.
 */

public class Marble extends Ellipse{
	private String locationKey;
	public int playerNumber;
	
	//creates a marble at a certain space, with a certain key
	Marble(double centerX, double centerY, int player, String key, boolean newM){
		super(centerX, centerY, Hexagon.radius*0.70, Hexagon.radius*0.70);
		this.playerNumber = player;
		this.locationKey = key;

	    this.setOnMouseClicked(EllipseOnMouseClicked);
	    if(player == 0) {
			this.setFill(Color.ANTIQUEWHITE);
		}
		if(player == 1) {
			this.setFill(Color.BLACK);
		}
		if(player == 2) {
			this.setFill(Color.GRAY);
		}
		if(player == 3) { 
			this.setFill(Color.DARKGREEN);
		}
		if (newM) {
			MarbleStorage.pieceGroup.getChildren().add(this);
		}
	}
	
	//handle location keys (that are private) with a set and get method
	public void setLocationKey(String key){
		this.locationKey = key;
	}
	
	public String getLocationKey() {
		return locationKey;
	}
	
	//can automatically put the marble in the middle of a hexagon with the same key
	public void updateLocation(Hashtable<String, Hexagon> board) {
		Hexagon hex = (Hexagon) board.get(locationKey);
		double tempX = hex.centerX ;
		double tempY = hex.centerY;
		this.setCenterX(tempX);
		this.setCenterY(tempY);
	}
			
	//make it clickable, so you can play the game
	EventHandler<MouseEvent> EllipseOnMouseClicked =
				new EventHandler<MouseEvent>() {
					public void handle(MouseEvent t) {
						if ((Move.playersTurn == 1 && !Move.player1AI) || (Move.playersTurn ==2 && !Move.player2AI) || (Move.playersTurn ==3 && !Move.player3AI)) {
							GameData.move.select(locationKey, Board.hashBoard);							}
						}
					};
					
	// make a copy of a marble, with a different reference
	public Marble deepClone() {
		Marble marble = new Marble(this.getCenterX(), this.getCenterY(), playerNumber, locationKey, false);
		return marble;
	}
}
