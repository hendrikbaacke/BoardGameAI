package src;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Cell;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import java.awt.*;
import javafx.scene.control.Button;


public class GameGui extends Application{
    @Override
    public void start(Stage stage) {
        System.out.println(stage.getWidth()+"        "+stage.getHeight());
        Board board = new Board(stage.getWidth()/2,stage.getHeight()/2);
        StackPane Screen = new StackPane();
        BorderPane MainScene =  board.add();
        GridPane SubScene = new GridPane();
        SubScene.setAlignment(Pos.BOTTOM_LEFT);
        HBox hbox = new HBox();
        Button Player1 = new Button("Player 1");
        Button Player2= new Button("Player 2");
        /*TODO
        In case its Player1's turn, the button for Player1 is highlighted, and viceversa

        */

        Button dummy1 = new Button("    ");
        Button dummy2 = new Button("    ");

        GridPane.setRowIndex(Player1, 0);
        GridPane.setRowIndex(Player2, 1);
        GridPane.setConstraints(dummy1,2,0);
        GridPane.setConstraints(dummy2,2,1);

        hbox.getChildren().addAll(Player1,Player2,dummy1,dummy2);

        SubScene.getChildren().addAll(Player1,Player2,dummy1,dummy2);
        Screen.getChildren().addAll(MainScene,SubScene);


        MarbleStorage m = new MarbleStorage();
        AnchorPane pp = m.Balls();
        MainScene.getChildren().add(pp);
        Scene scene = new Scene(Screen);
        stage.setTitle("Player vs Player");
        stage.setScene(scene);
        stage.show();
        VBox rulesMainCont = new VBox();
        HBox rulesTitleCont = new HBox();


    }
}