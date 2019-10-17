package src;


import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class GameGui extends Application {
	
	public Board board;
	static BorderPane MainScene;
	static Pane Screen;
	static AnchorPane pp;
	static Text winner_text = new Text("0");
    static Text player_text = new Text("0");
    static Text score_text1 = new Text("0");
    static Text score_text2 = new Text("0");

    Move move;

	public void start( Stage stage) {
		try {
		    Label winner_label = new Label("Player win:\t");
		     double MAX_FONT_SIZE = 30.0; // define max font size you need
		     winner_label.setFont(new Font(MAX_FONT_SIZE));		
		     winner_text.setFont(new Font(MAX_FONT_SIZE));		
		     player_text.setFont(new Font(MAX_FONT_SIZE));		
		     score_text1.setFont(new Font(MAX_FONT_SIZE));	
		     score_text2.setFont(new Font(MAX_FONT_SIZE));		


		     HBox winner = new HBox(winner_label, winner_text);
			Label player_label = new Label("Player turn:\t");
			player_label.setFont(new Font(MAX_FONT_SIZE));		

		    HBox playerBox = new HBox(player_label, player_text);
			Label score_label1 = new Label("Player 1 score is :\t");
			score_label1.setFont(new Font(MAX_FONT_SIZE));		
			Text tex = new Text("/6");
			tex.setFont(new Font(MAX_FONT_SIZE));		

		    HBox score = new HBox(score_label1, score_text1, tex);
		    Label score_label2 = new Label("Player 2 score is :\t");
		    score_label2.setFont(new Font(MAX_FONT_SIZE));		
		    Text tex2 = new Text("/6");
			tex2.setFont(new Font(MAX_FONT_SIZE));	
			HBox score2 = new HBox(score_label2, score_text2, tex2);
			GridPane SubScene = new GridPane();
			HBox hbox3 = new HBox();
			Button reset = new Button("RESET");
			//hbox3.getChildren().add(reset);
			GridPane.setRowIndex(hbox3, 5);
			GridPane.setRowIndex(winner, 4);
			GridPane.setRowIndex(playerBox, 1);
			GridPane.setRowIndex(score,2 );
			GridPane.setRowIndex(score2,3 );
			player_text.setText("1");
			SubScene.getChildren().addAll(hbox3,winner,playerBox,score,score2); //add reset for the reset button
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
	protected static Scene newScene(MarbleStorage m, Board board,GridPane SubScene) {
		Screen = new Pane();
		MainScene = board.add();
		pp = m.Balls();
		MainScene.getChildren().addAll(pp);
		Screen.getChildren().addAll(MainScene,SubScene);
		Scene scene = new Scene(Screen);




		return scene;
	}




}