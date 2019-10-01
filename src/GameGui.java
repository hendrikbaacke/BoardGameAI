
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class GameGui extends Application{
    @Override
    public void start(Stage stage) {
        System.out.println(stage.getWidth()+"        "+stage.getHeight());
        Board board = new Board(stage.getWidth()/2,stage.getHeight()/2);
        Pane TheBoard =  board.add();
        MarbleStorage m = new MarbleStorage();
        AnchorPane pp = m.Balls();
        TheBoard.getChildren().add(pp);
        Scene scene = new Scene(TheBoard);
        stage.setTitle("Player vs Player");
        stage.setScene(scene);
        stage.show();
    }
}