import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class GameGui extends Application{
    @Override
    public void start(Stage stage) {
        try {
      //  System.out.println(stage.getWidth()+"        "+stage.getHeight());
        Board board = new Board(stage.getWidth()/2,stage.getHeight()/2);
        Pane Screen = new Pane();
        BorderPane MainScene =  board.add();
        GridPane SubScene = new GridPane();
        //SubScene.setAlignment(Pos.BOTTOM_LEFT);


            HBox hbox0 = new HBox();
            Text labelLostMarbles = new Text("Lost Marble Count: ");
            hbox0.getChildren().addAll(labelLostMarbles);
            hbox0.setPadding(new Insets(50, 50, 30, 0));



            HBox hbox1 = new HBox();
            Button Player1 = new Button("Player 1");


            HBox hbox2 = new HBox();
            Button Player2 = new Button("Player 2");

            /*TODO
            If Player 1's turn: select P1 and viceversa
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


            MarbleStorage m = new MarbleStorage();
            AnchorPane pp = m.Balls();
            MainScene.getChildren().addAll(pp);

            Screen.getChildren().addAll(MainScene,SubScene);
            Scene scene = new Scene(Screen);
    
            stage.setTitle("Player vs Player");
            stage.setScene(scene);
            stage.show();

        } catch (Exception e){
            System.out.println("Exception in GUI Creation"+ e.getMessage());
        }


      /*  VBox rulesMainCont = new VBox();
        HBox rulesTitleCont = new HBox();*/


    }
}