package src;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.WindowEvent;

public class GameGui extends Application {
	
	public Board board;
	static BorderPane MainScene;
	static Pane Screen;
	static AnchorPane pp;
	
	
	public void start( Stage stage) {
		try {
		
			GridPane SubScene = new GridPane();
			HBox hbox3 = new HBox();
			Button reset = new Button("RESET");
			hbox3.getChildren().add(reset);
			GridPane.setRowIndex(hbox3, 3);

			SubScene.getChildren().add(hbox3);
			

			// System.out.println(stage.getWidth()+" "+stage.getHeight());
			board = new Board(stage.getWidth() / 2,
					stage.getHeight() / 2);
			//MarbleStorage m = new MarbleStorage();
			Board.boardMarbles = new MarbleStorage();
			Scene scene = newScene(Board.boardMarbles, board,SubScene);
			stage.setTitle("Player vs Player");
			stage.setScene(scene);
			stage.show();
			
			reset.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent actionEvent) {
	            	stage.close();

	                Platform.runLater( () -> {
						try {
							new Main().start( new Stage() );
						} catch (Exception e) {
							e.printStackTrace();
						}
					} );
	    		    stage.setScene(newScene(board.boardMarbles, board,SubScene));

	            }
	        });

		} catch (Exception e) {
			System.out.println("Exception in GUI Creation" + e.getMessage());
		}

	}

	public static Popup createPopup(final String message) {
		final Popup popup = new Popup();
		popup.setAutoFix(true);
		popup.setAutoHide(true);
		popup.setHideOnEscape(true);
		Label label = new Label(message);
		label.setOnMouseReleased(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				popup.hide();
			}
		});
		label.getStylesheets().add("/css/styles.css");
		label.getStyleClass().add("popup");
		popup.getContent().add(label);
		return popup;
	}

	public static void showPopupMessage(final String message, final Stage stage) {
		final Popup popup = createPopup(message);
		popup.setOnShown(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent e) {
				popup.setX(stage.getX() + stage.getWidth()/2 - popup.getWidth()/2);
				popup.setY(stage.getY() + stage.getHeight()/2 - popup.getHeight()/2);
			}
		});
		popup.show(stage);
	}

	protected static Scene newScene(MarbleStorage m, Board board,GridPane SubScene) {
		Screen = new Pane();
		MainScene = board.add();
		pp = m.Balls();
		MainScene.getChildren().addAll(pp);
		
		// SubScene.setAlignment(Pos.BOTTOM_LEFT);

		HBox hbox0 = new HBox();
		Text labelLostMarbles = new Text("Lost Marble Count: ");
		hbox0.getChildren().addAll(labelLostMarbles);
		hbox0.setPadding(new Insets(50, 50, 30, 0));

		HBox hbox1 = new HBox();
		Button Player1 = new Button("Player 1");

		HBox hbox3 = new HBox();
		
		
		HBox hbox2 = new HBox();
		Button Player2 = new Button("Player 2");

		/*
		 * TODO If Player 1's turn: select P1 and viceversa
		 */

		GridPane.setRowIndex(hbox0, 0);
		GridPane.setRowIndex(hbox1, 1);
		GridPane.setRowIndex(hbox2, 2);

		hbox1.getChildren().addAll(Player1);

		for (int i = 0; i < 6; i++) {

			hbox1.getChildren().add(new CheckBox("" + (i + 1)));
		}
		hbox2.getChildren().addAll(Player2);

		for (int i = 0; i < 6; i++) {
			hbox2.getChildren().add((new CheckBox("" + (i + 1))));
		}
	  
		SubScene.getChildren().addAll(hbox0, hbox1, hbox2);
		Screen.getChildren().addAll(MainScene,SubScene);
		Scene scene = new Scene(Screen);




		return scene;
	}




}