import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class GameGui extends Application{
    @Override
    public void start(Stage stage) {
        Board board = new Board();
        Pane TheBoard =  board.add();

        Scene scene = new Scene(TheBoard);

        stage.setTitle("Player vs Player");
        stage.setScene(scene);
        stage.show();
    }
}
