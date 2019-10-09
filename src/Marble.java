package src;

import java.util.ArrayList;

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
	
	Marble(double centerX, double centerY, int radius, int player, String key){
		super(centerX, centerY, radius, radius);
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
		MarbleStorage.pieceGroup.getChildren().add(this);
		
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
	
	  EventHandler<MouseEvent> EllipseOnMousePressedEventHandler = 
		        new EventHandler<MouseEvent>() {
		 
		        @Override
		        public void handle(MouseEvent t) {
		            orgSceneX = t.getSceneX();
		            orgSceneY = t.getSceneY();
		            orgTranslateX = ((Ellipse)(t.getSource())).getTranslateX();
		            orgTranslateY = ((Ellipse)(t.getSource())).getTranslateY();
		        }
		    };
		     
		    EventHandler<MouseEvent> EllipseOnMouseDraggedEventHandler = 
		        new EventHandler<MouseEvent>() {
		 
		        @Override
		        public void handle(MouseEvent t) {
		            double offsetX = t.getSceneX() - orgSceneX;
		            double offsetY = t.getSceneY() - orgSceneY;
		            double newTranslateX = orgTranslateX + offsetX;
		            double newTranslateY = orgTranslateY + offsetY;
		             
		            ((Ellipse)(t.getSource())).setTranslateX(newTranslateX);
		            ((Ellipse)(t.getSource())).setTranslateY(newTranslateY);
		        }
		    };

			EventHandler<MouseEvent> EllipseOnMouseClicked =
					new EventHandler<MouseEvent>() {

						@Override
						public void handle(MouseEvent t) {
							System.out.println(locationKey);
							Board.move.select(locationKey);
						}
					};
}
