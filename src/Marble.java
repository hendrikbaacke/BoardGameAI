package src;

import java.util.ArrayList;
import java.util.Hashtable;

import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

public class Marble extends Ellipse{
	private String locationKey;
	public int playerNumber;
	double orgSceneX, orgSceneY;
	double orgTranslateX, orgTranslateY;
	
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
	
	public void setLocationKey(String key){
		this.locationKey = key;
	}
	
	public String getLocationKey() {
		return locationKey;
	}
	
	public void updateLocation(Hashtable<String, Hexagon> board) {
		Hexagon hex = (Hexagon) board.get(locationKey);
		double tempX = hex.centerX ;
		double tempY = hex.centerY;
		this.setCenterX(tempX);
		this.setCenterY(tempY);
	}
			EventHandler<MouseEvent> EllipseOnMouseClicked =
					new EventHandler<MouseEvent>() {

						@Override
						public void handle(MouseEvent t) {
							//System.out.println(locationKey);
							Board.move.select(locationKey, Board.hashBoard);
						}
					};
					
	public Marble clone() {
		Marble marble = new Marble(this.getCenterX(), this.getCenterY(), playerNumber, locationKey, false);
		return marble;
	}
}
